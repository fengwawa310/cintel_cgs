package com.controller.ticket;

import com.alibaba.fastjson.TypeReference;
import com.common.consts.Const;
import com.common.services.api.APIClientRequest;
import com.common.utils.DataSet;
import com.common.utils.DatatablesResponse;
import com.common.utils.ExceUtil;
import com.common.utils.IDGenerator;
import com.common.utils.PageVO;
import com.controller.communal.JdbcHandler;
import com.entity.search.SearchEntity;
import com.entity.ticket.EtCommunication;
import com.entity.ticket.EtTicket;
import com.vo.ticket.ESTicketResponse;
import com.vo.ticket.TicketVo;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.codehaus.jackson.map.util.Comparators;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * 话单分析
 * Created by weipc on 2018/1/31.
 */
@Controller
@RequestMapping("/ticket")
public class TicketHandler {

    private static final Logger logger = LoggerFactory.getLogger(TicketHandler.class);

    @RequestMapping(value = "/importCommunication", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject importExcel(HttpServletResponse response, MultipartFile file) throws IOException {
        //错误提示
        JSONObject map= new JSONObject();
        JSONObject map1= new JSONObject();
        map1.put("flag",false);
        //文件名
        String filename = file.getOriginalFilename();
        if("".equals(filename)||filename==null){
            map1.put("msg","请选择Excel文件！");
            map.put("data",map1);
            return map;
        }
        //后缀名
        String subfix = filename.lastIndexOf(".") == -1 ? "" : filename
                .substring(filename.lastIndexOf(".") + 1);
        List result;
        if (subfix.equals("xls")) {
            result = ExceUtil.readExcel2003(file,3);
        } else if (subfix.equals("xlsx")) {
            result = ExceUtil.readExcel2007(file,3);
        }else{
            map1.put("msg","只能上传Excel文件！");
            map.put("data",map1);
            return map;
        }
        if(result.size()==0){
            map1.put("msg","Excel中没有数据！");
            map.put("data",map1);
            return map;
        }
        List<EtCommunication> paramList = new ArrayList<>();
        for (int i = 0; i < result.size(); i ++) {
            List list= (List) result.get(i);
            EtCommunication etc = new EtCommunication(IDGenerator.getorderNo(),
                    list.get(0)+"",
                    list.get(1)+"",
                    list.get(2)+"");
            paramList.add(etc);
        }
        JSONObject paramJson = new JSONObject();
        paramJson.put("list",paramList);
        String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TICKET, "ticket/importCommunication", paramJson);
        return JSONObject.fromObject(jsonStr);
    }

    @RequestMapping(value = "/importTicket", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject importTicket(HttpServletResponse response, MultipartFile file) throws IOException {
        //错误提示
        JSONObject map= new JSONObject();
        JSONObject map1= new JSONObject();
        map1.put("flag",false);
        //文件名
        String filename = file.getOriginalFilename();
        if("".equals(filename)||filename==null){
            map1.put("msg","请选择Excel文件！");
            map.put("data",map1);
            return map;
        }
        //后缀名
        String subfix = filename.lastIndexOf(".") == -1 ? "" : filename
                .substring(filename.lastIndexOf(".") + 1);
        List result;
        if (subfix.equals("xls")) {
            result = ExceUtil.readExcel2003(file,7);
        } else if (subfix.equals("xlsx")) {
            result = ExceUtil.readExcel2007(file,7);
        }else{
            map1.put("msg","只能上传Excel文件！");
            map.put("data",map1);
            return map;
        }
        if(result.size()==0){
            map1.put("msg","Excel中没有数据！");
            map.put("data",map1);
            return map;
        }
        List<EtTicket> paramList = new ArrayList<>();
        for (int i = 0; i < result.size(); i ++) {
            List list= (List) result.get(i);
            EtTicket ett = new EtTicket();
            ett.setId(IDGenerator.getorderNo());
            ett.setStartTimeStr(list.get(0)+"");//通话开始时间
            ett.setEndTimeStr(list.get(1)+"");//通话结束时间
            ett.setEventType(1);//事件类型
            ett.setCallingNumber(list.get(3)+"");//主叫号码
            ett.setEndNumber(list.get(4)+"");//被叫号码
            ett.setCallingHome(list.get(5)+"");//主叫归属地
            ett.setEndHome(list.get(6)+"");//被叫归属地
            paramList.add(ett);
        }
        JSONObject paramJson = new JSONObject();
        paramJson.put("list",paramList);
        String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TICKET, "ticket/importTicket", paramJson);
        return JSONObject.fromObject(jsonStr);
    }
    
    @RequestMapping(value = "/frequencyOrCallingTimeCount", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ESTicketResponse frequencyOrCallingTimeCount(TicketVo ticketVo) throws IllegalAccessException {
        JSONObject paramJson = new JSONObject();
        Field[] fieldsOfTicketVo = FieldUtils.getAllFields(TicketVo.class);
        for (Field field : fieldsOfTicketVo) {
            Field subField = FieldUtils.getDeclaredField(ticketVo.getClass(), field.getName(), true);
            paramJson.put(field.getName(), subField.get(ticketVo));
        }
        String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TICKET, "ticket/frequencyOrCallingTimeCount", paramJson);
        return com.alibaba.fastjson.JSONObject.parseObject(jsonStr, new TypeReference<ESTicketResponse>(){});
    }

    @RequestMapping(value = "/mutualContactsAnalyze", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody DatatablesResponse mutualContactsAnalyze(@RequestParam String phoneNumbers,@RequestParam String start_time) throws  ExecutionException {
        try{
            if(StringUtils.isEmpty(start_time)&&StringUtils.isEmpty(phoneNumbers)){
                DataSet<Map<String,Object>> dataSet = new DataSet<Map<String,Object>>(new  ArrayList<Map<String,Object>>(), 0L, 0L);
                DatatablesResponse<Map<String,Object>> resp = DatatablesResponse.build(dataSet);
                return resp;
            }
            HashMap<String, Object> paramJson = new HashMap<String,Object>();
            paramJson.put("phoneNumbers", phoneNumbers);
            paramJson.put("start_time", start_time);
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TICKET, "ticket/mutualContactsAnalyze", paramJson);
            JSONObject result = JSONObject.fromObject(jsonStr);
            List<Map<String,Object>> list =(List<Map<String,Object>>)result.get("data");
            //添加运营商和地区
            String sql =null;
            for(Map<String,Object> map:list){
                String opositeNumber = map.get("opositeNumber").toString();
                String prefix=null;
                if(opositeNumber!=null){
                    prefix=opositeNumber.substring(0,7);
                }
                
                sql ="SELECT a.PROVINCE,a.CITY,a.OPERATE,a.AREACODE FROM dic_to_tel a WHERE a.PREFIXCODE='"+prefix+"'";
                List<Map<String, Object>> tab = JdbcHandler.selectTab(sql);
              
                map.put("domain", tab.size()==0?"未知":tab.get(0).get("OPERATE"));
                map.put("location", tab.size()==0?"未知":tab.get(0).get("CITY"));
                map.put("areacode", tab.size()==0?"未知":tab.get(0).get("AREACODE"));
            }
            
            //------------------------------------type==1,图形
          /*  Integer total = null;
            DataSet<Map<String,Object>> dataSet = null;
           List<Map<String,Object>>list2 =new ArrayList<Map<String,Object>>();
            if(type==1){
                for(Map<String,Object> map:list){
                    list2.add(map);
                    Object calledFrequency = map.get("calledFrequency");
                    if(calledFrequency==null||"".equals(calledFrequency)||calledFrequency.toString().matches(".*null.*")){
                        continue;
                    }
                    else if((Integer.valueOf(calledFrequency.toString())>0)){
                        Object tmp = map.get("inputNumber");
                        map.put("inputNumber", map.get("opositeNumber"));
                        map.put("opositeNumber", tmp);
                        list2.add(map);
                    }
                    
               }
                total= list2.size();
                dataSet = new DataSet<Map<String,Object>>(list2, (long) total, (long) total);
            }else{
                total= list.size();
                dataSet = new DataSet<Map<String,Object>>(list, (long) total, (long) total);
            }*/
            //---------------------------------------
            Integer total = list.size();
            DataSet<Map<String,Object>> dataSet = new DataSet<Map<String,Object>>(list, (long) total, (long) total);
            DatatablesResponse<Map<String,Object>> resp = DatatablesResponse.build(dataSet);
            return resp;
        }catch(Exception e){
            e.printStackTrace();
            DataSet<Map<String,Object>> dataSet = new DataSet<Map<String,Object>>(new ArrayList<>(), 0L, 0L);
            DatatablesResponse<Map<String,Object>> resp = DatatablesResponse.build(dataSet);
            return resp;
        }
    }
    
    /**
     * 话单查询
     * @param
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @RequestMapping(value = "/ticketQuery",produces = "application/json;charset=UTF-8" )
    public @ResponseBody DatatablesResponse ticketQuery(@RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length, String param) throws ExecutionException, InterruptedException {
        try{
            if (StringUtils.isBlank(param)){
                return null;
            }
            HashMap<String, Object> paramJson = new HashMap<String,Object>();
            paramJson.put("start", start);
            paramJson.put("length", length);
            paramJson.put("param", param);
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TICKET, "ticket/ticketQuery", paramJson);
            JSONObject result = JSONObject.fromObject(jsonStr);
            String resultstr = result.get("data").toString();
            List<SearchEntity> list = com.alibaba.fastjson.JSONObject.parseArray(resultstr, SearchEntity.class);
            int total = list.size();
           
            DataSet<SearchEntity> dataSet = new DataSet<SearchEntity>(list, (long) total, (long) total);
            DatatablesResponse<SearchEntity> resp = DatatablesResponse.build(dataSet);
            return resp;
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
    public @ResponseBody DatatablesResponse abnormalCallQuery(@RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length, String param) throws ExecutionException, InterruptedException {
        try{
            if (StringUtils.isBlank(param)){
                return null;
            }
            HashMap<String, Object> paramJson = new HashMap<String,Object>();
            paramJson.put("start", start);
            paramJson.put("length", length);
            paramJson.put("param", param);
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TICKET, "ticket/abnormalCallQuery", paramJson);
            JSONObject result = JSONObject.fromObject(jsonStr);
            String resultstr = result.get("data").toString();
            List<SearchEntity> list = com.alibaba.fastjson.JSONObject.parseArray(resultstr, SearchEntity.class);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Collections.sort(list, new Comparator<SearchEntity>() {

                @Override
                public int compare(SearchEntity o1, SearchEntity o2) {
                    try {
                        long time1 = sf.parse(o1.getParam1()).getTime();
                        long time2 = sf.parse(o2.getParam1()).getTime();
                        return (int) (time2-time1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return 0;
                    }
                }
            });
            int total = list.size();
            for(SearchEntity s:list){
                System.out.println(s.getParam1()+"------------"+sf.parse(s.getParam1()).getTime());
            }
            DataSet<SearchEntity> dataSet = new DataSet<SearchEntity>(list, (long) total, (long) total);
            DatatablesResponse<SearchEntity> resp = DatatablesResponse.build(dataSet);
            return resp;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
