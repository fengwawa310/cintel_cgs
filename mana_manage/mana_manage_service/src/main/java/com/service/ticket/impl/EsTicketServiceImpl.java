package com.service.ticket.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.common.utils.ElasticSearchUtils;
import com.common.utils.PageVO;

import com.entity.ticket.ESTicket;
import com.google.common.collect.Lists;
import com.service.ticket.ElasticSearchHelper;
import com.service.ticket.EsTicketService;
import com.vo.ticket.TicketVo;

import net.sf.json.JSONObject;

@Service("esTicketService")
public class EsTicketServiceImpl implements EsTicketService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("cgs_call_record")
    private String index;
    @Value("cgs")
    private String type;

    /**
     * 频次统计
     * @param ticketVo
     */
    @Override
    public List<Map<String, Object>> frequencyCount(TicketVo ticketVo) {
        List<Map<String, Object>> returnList = Lists.newArrayList();
        ElasticSearchHelper.addHeadElementToArray(ticketVo, returnList); //添加头结点

        //主叫号码:查询被叫号码top10
        Map<String, Long> callingCountMap = ElasticSearchHelper.frequencyCount(index, type,
                "CALLING", ticketVo.getPhoneNumber(),
                "START_TIME", "yyyy-MM-dd HH:mm:ss", ticketVo.getStartTime()
                , ticketVo.getFinishTime(), "frequencyCount", "CALLED",ticketVo.getPrevNumber());
        //被叫号码:查询主叫号码top10
        Map<String, Long> calledCountMap = ElasticSearchHelper.frequencyCount(index, type,
                "CALLED", ticketVo.getPhoneNumber(),
                "START_TIME", "yyyy-MM-dd HH:mm:ss", ticketVo.getStartTime()
                , ticketVo.getFinishTime(), "frequencyCount", "CALLING",ticketVo.getPrevNumber());
        Map<String, Long> combinedMap = ElasticSearchHelper.combineTwoMaps(callingCountMap, calledCountMap);
        ElasticSearchHelper.addMapToArray(combinedMap, returnList, ticketVo.getPhoneNumber());

        if(ticketVo.getShowLevel() == 3) {
           combinedMap.forEach((k, v) -> {
               //主叫号码:查询被叫号码top10
               Map<String, Long> subCallingCountMap = ElasticSearchHelper.frequencyCount(index, type,
                       "CALLING", k,
                       "START_TIME", "yyyy-MM-dd HH:mm:ss", ticketVo.getStartTime()
                       , ticketVo.getFinishTime(), "frequencyCount", "CALLED",ticketVo.getPhoneNumber());
               //被叫号码:查询主叫号码top10
               Map<String, Long> subCalledCountMap = ElasticSearchHelper.frequencyCount(index, type,
                       "CALLED", k,
                       "START_TIME", "yyyy-MM-dd HH:mm:ss", ticketVo.getStartTime()
                       , ticketVo.getFinishTime(), "frequencyCount", "CALLING",ticketVo.getPhoneNumber());
               Map<String, Long> subCombinedMap = ElasticSearchHelper.combineTwoMaps(subCallingCountMap, subCalledCountMap);
               ElasticSearchHelper.addMapToArray(subCombinedMap, returnList, k);
           });
        }

        return returnList;
    }

    /**
     * 时长统计
     * @param ticketVo
     */
    @Override
    public List<Map<String, Object>> callingTimeCount(TicketVo ticketVo) throws ExecutionException, InterruptedException {
        List<Map<String, Object>> returnList = Lists.newArrayList();
        ElasticSearchHelper.addHeadElementToArray(ticketVo, returnList); //添加头结点

        //通话时长top10
        List<ESTicket> esTickets = ElasticSearchHelper.callingTimeCount(index, type,
                "START_TIME", "yyyy-MM-dd HH:mm:ss", ticketVo.getStartTime()
                , ticketVo.getFinishTime(), ticketVo.getPhoneNumber(),ticketVo.getPrevNumber());
        
        Integer[] id = {0};
        esTickets.forEach(es -> {
        	returnList.add(ElasticSearchHelper.esTicketToJSON(es, ticketVo.getPhoneNumber(), id[0]++));
        });
        
        if(ticketVo.getShowLevel() == 3) {
            esTickets.forEach(esTicket -> {
                //获取接下来需要查询的号码
                final String phoneNumber;
                if(!esTicket.getCalling().equalsIgnoreCase(ticketVo.getPhoneNumber())) {
                    phoneNumber = esTicket.getCalling();
                } else {
                    phoneNumber = esTicket.getCalled();
                }
                try {
                    List<ESTicket> subEsTickets = ElasticSearchHelper.callingTimeCount(index, type,
                            "START_TIME", "yyyy-MM-dd HH:mm:ss", ticketVo.getStartTime()
                            , ticketVo.getFinishTime()
                            , phoneNumber,ticketVo.getPhoneNumber());
                    Integer[] id1 = {0};
                    subEsTickets.forEach(subEsTicket -> {
                    	returnList.add(ElasticSearchHelper.esTicketToJSON(subEsTicket, phoneNumber, id1[0]++));
                    });
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            });
        }

        return returnList;
    }

    @Override
    public SearchHits ticketQuery(String param,List<String> fieldList,PageVO pageVO) {
       SearchResponse response= ElasticSearchUtils.getSearch(param, pageVO.getStart(), pageVO.getLength(), index, type, fieldList.get(0),fieldList.get(1));
       return response.getHits();
    }
    

    @Override
    public SearchHits abnormalCallQuery(String param, List<String> fieldList, PageVO pageVO) {
        try{
            SearchResponse abnormalCallQuery = ElasticSearchHelper.abnormalCallQuery(param,fieldList, pageVO.getStart(), pageVO.getLength(), index, type);
            return abnormalCallQuery.getHits();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
      
    }

    @Override
    public JSONObject mutualContactsAnalyze(HashMap<String, Object> paramMap) {
        JSONObject result =new JSONObject();
        String[]  phoneNumbers=(String[]) paramMap.get("phoneNumbers");
        HashMap<String, List<Collection<ESTicket>>> map = new HashMap<String,List<Collection<ESTicket>>>();
        //key值是对方号码,value值为同一对方号码的用户号码的集合
        HashMap<String, HashSet<String>> combine = new HashMap<String,HashSet<String>>();
        for(String phoneNumber :phoneNumbers ){
            // TermsQueryBuilder termsQuery1 = QueryBuilders.termsQuery("CALLING", phoneNumbers);
            // TermsQueryBuilder termsQuery2 = QueryBuilders.termsQuery("CALLED", phoneNumbers);
             BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchPhraseQuery("CALLING_TYPE", "叫"))
                     .must(QueryBuilders.multiMatchQuery(phoneNumber, "CALLING", "CALLED"))//.must(termsQuery1).should(termsQuery2)
                     .filter(QueryBuilders.rangeQuery("START_TIME").format("yyyy-MM-dd HH:mm:ss").gte(paramMap.get("start_time")).lt(paramMap.get("end_time")));
             Set<ESTicket> eSTickets = ElasticSearchHelper.getSearchList("cgs_call_record", "cgs", queryBuilder);
             //查询号码是主叫
             ArrayList<ESTicket> callingList = new ArrayList<ESTicket>();
             //查询号码是被叫
             ArrayList<ESTicket> calledList = new ArrayList<ESTicket>();
             //某个对方号码关联输入手机号码的集合
             //-------------------------------------------------------------------------
             for(ESTicket eSTicket:eSTickets){
                 String callingnumber=eSTicket.getCalling();
                 String callednumber=eSTicket.getCalled();
                 if(phoneNumber.equals(callingnumber)){
                   //去除400开头号码
                     if("400".equals(callednumber.substring(0, 3))||callednumber.length()<7){
                         continue;
                     }
                     if(combine.containsKey(callednumber)){
                         HashSet<String> set2 = combine.get(callednumber);
                         set2.add(phoneNumber);
                         combine.put(callednumber,set2);
                     }else{
                         HashSet<String> set2=new HashSet<String>();
                         set2.add(phoneNumber);
                         combine.put(callednumber,set2);
                     }
                                 
                 }else{
                   //去除400开头号码
                     if("400".equals(callingnumber.substring(0, 3))||callingnumber.length()<7){
                         continue;
                     }
                     if(combine.containsKey(callingnumber)){
                         HashSet<String> set = combine.get(callingnumber);
                         set.add(phoneNumber);
                         combine.put(callingnumber, set);
                     }else{
                         HashSet<String> set=new HashSet<String>();
                         set.add(phoneNumber);
                         combine.put(callingnumber,set);
                     }
                 }
                 if(eSTicket.getCalling_type().indexOf("主叫")!=-1){
                     if(phoneNumber.equals(callingnumber)){
                         //查询的号码发起通话
                         callingList.add(eSTicket);
                         
                     }else{
                         //查询的号码接受电话
                         calledList.add(eSTicket);
                     }
                 }
                 if(eSTicket.getCalling_type().indexOf("被叫")!=-1){
                     if(phoneNumber .equals(callednumber)){
                       //查询的号码发起通话
                         callingList.add(eSTicket);
                     }else{
                       //查询的号码接受电话
                         calledList.add(eSTicket);
                     }
                 }
                 
                 
             }
            //-------------------------------------------------------------------------
          ArrayList<Collection<ESTicket>> list = new ArrayList<Collection<ESTicket>>();
          list.add(callingList);
          list.add(calledList);
          list.add(eSTickets);
          map.put(phoneNumber, list);
        }
        //-----------------------------------------------------------------------------
        //不符合要求的对方号码集合
        ArrayList<String> removeList = new ArrayList<String>();
        for(Map.Entry<String,HashSet<String>> entry:combine.entrySet()){
            HashSet<String> set2 = entry.getValue();
            if(set2.size()<2){
                removeList.add(entry.getKey());
            }
        }
        
        ArrayList<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(Map.Entry<String, List<Collection<ESTicket>>> entry:map.entrySet()){
            String inputNumber=entry.getKey();
            List<ESTicket> callingList = (List<ESTicket>) entry.getValue().get(0);
            HashMap<String, Integer> callingFrequency = new HashMap<String,Integer>();
            for(ESTicket e:callingList){
                String calling = e.getCalling();
                String called = e.getCalled();
                if(e.getCalling_type().indexOf("主叫")!=-1){
                    if(removeList.contains(called)){
                        continue;
                    }
                    callingFrequency.put(called, callingFrequency.containsKey(called)?callingFrequency.get(called)+1:1);
                }else{
                    if(removeList.contains(calling)){
                        continue;
                    }
                    callingFrequency.put(calling, callingFrequency.containsKey(calling)?callingFrequency.get(calling)+1:1);
                }
            }
            List<ESTicket> calledList = (List<ESTicket>) entry.getValue().get(1);
            HashMap<String, Integer> calledFrequency = new HashMap<String,Integer>();
            for(ESTicket e:calledList){
                String calling = e.getCalling();
                String called = e.getCalled();
                if(e.getCalling_type().indexOf("主叫")!=-1){
                    if(removeList.contains(calling)){
                        continue;
                    }
                    calledFrequency.put(calling, calledFrequency.containsKey(calling)?calledFrequency.get(calling)+1:1);
                }else{
                    if(removeList.contains(called)){
                        continue;
                    }
                    calledFrequency.put(called, calledFrequency.containsKey(called)?calledFrequency.get(called)+1:1);
                }
            }
            HashSet<ESTicket> allList = (HashSet<ESTicket>) entry.getValue().get(2);
            HashMap<String, Integer> allFrequency = new HashMap<String,Integer>();
            for(ESTicket e:allList){
                String calling = e.getCalling();
                String called = e.getCalled();
                if(inputNumber.equals(calling)){
                    if(removeList.contains(called)||"400".equals(called.substring(0, 3))||called.length()<7){
                        continue;
                    }
                    allFrequency.put(called, allFrequency.containsKey(called)?allFrequency.get(called)+1:1);
                }else{
                    if(removeList.contains(calling)||"400".equals(calling.substring(0, 3))||calling.length()<7){
                        continue;
                    }
                    allFrequency.put(calling, allFrequency.containsKey(calling)?allFrequency.get(calling)+1:1);
                }
            }
            
            for(String opositeNumber:allFrequency.keySet()){
                HashMap<String, Object> result1 = new HashMap<String,Object>();
                result1.put("inputNumber", inputNumber);//输入号码
                result1.put("opositeNumber", opositeNumber);//对端号码
                result1.put("domain", "");//运营商
                result1.put("location", "");//归属地
                result1.put("casetag", "");//备注
                result1.put("callingFrequency", callingFrequency.get(opositeNumber));//主叫频次
                result1.put("calledFrequency", calledFrequency.get(opositeNumber));//被叫频次
                result1.put("allFrequency", allFrequency.get(opositeNumber));
                resultList.add(result1);
            }
        }
        result.put("result", resultList);
        return result;
    }

  
   
}
