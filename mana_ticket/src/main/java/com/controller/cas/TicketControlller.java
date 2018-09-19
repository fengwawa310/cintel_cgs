package com.controller.cas;

import com.alibaba.fastjson.JSONArray;
import com.common.utils.DataSet;
import com.common.utils.DatatablesResponse;
import com.common.utils.PageVO;
import com.entity.search.SearchEntity;
import com.entity.ticket.EtCommunication;
import com.entity.ticket.EtTicket;
import com.service.ticket.ElasticSearchHelper;
import com.service.ticket.EsTicketService;
import com.service.ticket.TicketService;
import com.service.ticket.impl.EsTicketServiceImpl;
import com.vo.ticket.TicketVo;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 话单分析
 * Created by weipc on 2018/1/2.
 */
@Controller
@RequestMapping("/ticket")
public class TicketControlller {
    @Resource
    private TicketService ticketService;
    @Autowired
    private EsTicketService esTicketService;

    /**
     * 导入通讯录信息
     */
    @RequestMapping(value="/importCommunication",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject importCommunication(HttpSession httpSession, HttpServletRequest request,String list ){
        JSONObject result =new JSONObject();
        try {
            List<EtCommunication> jsonArray=(List<EtCommunication>) JSONArray.parse(list);
            ticketService.insertCommunication(jsonArray);
            result.put("flag",true);
            result.put("msg","导入成功！");
        } catch (Exception e) {
            result.put("flag",false);
            result.put("msg","导入失败！原因："+e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 导入话单信息
     */
    @RequestMapping(value="/importTicket",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject insert(HttpSession httpSession, HttpServletRequest request,String list ){
        JSONObject result =new JSONObject();
        try {
            List<EtTicket> jsonArray=(List<EtTicket>) JSONArray.parse(list);
            ticketService.importTicket(jsonArray);
            result.put("flag",true);
            result.put("msg","导入成功！");
        } catch (Exception e) {
            result.put("flag",false);
            result.put("msg","导入失败！原因："+e);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/frequencyOrCallingTimeCount",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public List<Map<String, Object>> frequencyOrCallingTimeCount(TicketVo ticketVo) throws ExecutionException, InterruptedException {
        ElasticSearchHelper.formatTime(ticketVo);
        if(ticketVo.getMoveMessage() == 1) { //通话频次
            List<Map<String, Object>> maps = esTicketService.frequencyCount(ticketVo);
            return maps;
        } else if (ticketVo.getMoveMessage() == 2) { //通话时长
            List<Map<String, Object>> maps = esTicketService.callingTimeCount(ticketVo);
            return maps;
        }

        return null;
    }
    /**
     * 话单查询
     * @param
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping(value = "/ticketQuery",produces = "application/json;charset=UTF-8" )
    public @ResponseBody List<SearchEntity> ticketQuery(@RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length, String param) throws ExecutionException, InterruptedException {
        try{
            if (StringUtils.isBlank(param)){
                return null;
            }
            PageVO pageVO= new PageVO(start,length);
            List<String> list = new ArrayList<>();
            list.add("CALLING");
            list.add("CALLED");
            SearchHits hits = esTicketService.ticketQuery(param, list, pageVO);
            int total=(int) hits.getTotalHits();//总记录数
            int totalPage = total/length;//总页数
            if(total % length != 0){
                totalPage += 1;
            }
          
            List<SearchEntity> objects = new ArrayList<>();
            for (SearchHit searchHit : hits) {
                SearchEntity searchEntity = new SearchEntity();
                searchEntity.setParam1((String) searchHit.getSourceAsMap().get("START_TIME")); //开始时间
                searchEntity.setParam2((String) searchHit.getSourceAsMap().get("CALLING"));//本机号码
                searchEntity.setParam3((String) searchHit.getSourceAsMap().get("CALLED"));//对方号码
                searchEntity.setParam4((String) searchHit.getSourceAsMap().get("CALLING_PLACE"));//对方位置
                searchEntity.setParam5(String.valueOf((Integer) searchHit.getSourceAsMap().get("CALLING_TIME"))); //通话时长
                searchEntity.setParam6((String) searchHit.getSourceAsMap().get("REMARK"));//备注
                objects.add(searchEntity);
            }
            return objects;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 异常通话查询
     * @param
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping(value = "/abnormalCallQuery",produces = "application/json;charset=UTF-8" )
    public @ResponseBody List<SearchEntity> abnormalCallQuery(@RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length, String param) throws ExecutionException, InterruptedException {
        try{
            if (StringUtils.isBlank(param)){
                return null;
            }
            PageVO pageVO= new PageVO(start,length);
            List<String> list = new ArrayList<>();
            list.add("CALLING");
            list.add("CALLED");
            SearchHits hits = esTicketService.abnormalCallQuery(param, list, pageVO);
            int total=(int) hits.getTotalHits();//总记录数
            int totalPage = total/length;//总页数
            if(total % length != 0){
                totalPage += 1;
            }
          
            List<SearchEntity> objects = new ArrayList<>();
            for (SearchHit searchHit : hits) {
                SearchEntity searchEntity = new SearchEntity();
                searchEntity.setParam1((String) searchHit.getSourceAsMap().get("START_TIME")); //日期
                searchEntity.setParam2((String) searchHit.getSourceAsMap().get("CALLED"));//对方号码
                searchEntity.setParam3("");//对方姓名
                searchEntity.setParam4("");  //时间类型
                searchEntity.setParam5((String) searchHit.getSourceAsMap().get("CALLING_TYPE"));//通话类型
                searchEntity.setParam6((String) searchHit.getSourceAsMap().get("CALLING_PLACE"));//对方位置
                objects.add(searchEntity);
            }
            return objects;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 共同联系人
     * @param
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     *  paramMap.put("phoneNumbers", phoneNumbers);
        paramMap.put("start_time", start_time);
     */
    @RequestMapping(value = "/mutualContactsAnalyze",produces = "application/json;charset=UTF-8" )
    public @ResponseBody  List<Map<String,Object>> mutualContactsAnalyze(@RequestParam(required=true) String phoneNumbers,@RequestParam(required=true) String start_time) throws ExecutionException, InterruptedException {
        try{
            HashMap<String,Object> paramMap=new HashMap<String,Object>();
            
            if(StringUtils.isEmpty(phoneNumbers)){
                return null;
            }
            phoneNumbers=phoneNumbers.replaceAll("^[^0-9]+", "");
            String[] split = phoneNumbers.split("[^0-9]+");
            paramMap.put("phoneNumbers", split);
            
            if(StringUtils.isEmpty(start_time)){
                return null;
            }
            if (start_time.trim().length() == 10) {
                start_time=start_time + " 00:00:00";
            }
            paramMap.put("start_time", start_time);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sf.parse(start_time);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)+1);
            Date endDate = cal.getTime();
            String end_time=sf.format(endDate);
            paramMap.put("end_time", end_time);
            JSONObject result =new EsTicketServiceImpl().mutualContactsAnalyze(paramMap);
            List<Map<String,Object>> object = (List<Map<String, Object>>) result.get("result");
            ArrayList<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
            for(Map<String,Object> map:object){
                Map<String,Object>resultMap=new HashMap<String,Object>();
                for(Map.Entry<String, Object> entry :map.entrySet()){
                    resultMap.put(entry.getKey().replace("\"", ""),entry.getValue().toString().replace("\"", ""));
                }
                resultList.add(resultMap);
            }
            
            return resultList;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
