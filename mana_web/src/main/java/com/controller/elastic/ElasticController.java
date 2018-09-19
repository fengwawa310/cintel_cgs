package com.controller.elastic;

import com.common.consts.Const;
import com.common.consts.Global;
import com.common.services.api.APIClientRequest;
import com.common.utils.*;
import com.controller.communal.JdbcHandler;
import com.entity.DicUnit;
import com.entity.TagEnum;
import com.entity.caseInfo.ApStaff;
import com.entity.caseInfo.EtCase;
import com.entity.search.SearchEntity;
import com.entity.suspect.EtSuspect;
import com.entity.sys.SysUser;
import com.entity.task.EpHotel;
import com.entity.task.EpHotelDistance;
import com.entity.task.EpHotelPerson;
import com.service.communal.DicUtilsService;
import com.util.SysUserHelp;
import com.vo.taskStu.EtAnalyse;
import com.vo.taskStu.HotelAnalyse;
import com.vo.taskStu.ResultData;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Controller;
import org.springframework.util.IdGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author admin
 * @create 2018-03-12 11:41
 **/
@RequestMapping("/elastic")
@Controller
public class ElasticController {

    @Resource
    DicUtilsService dicUtilsService;

    //话单集合
    public static List contactList = new ArrayList();
    //通联人集合
    public static List contactPersonList = new ArrayList();


    /**
     *       案件搜索
     *      /elastic/searchCase?page=1&pagesize=10
     */
    @RequestMapping("/searchCase")
    public @ResponseBody DatatablesResponse getSearch(@RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length, String param){
        if (StringUtils.isBlank(param)){
            param="案件 lv1";
        }
        PageVO pageVO= new PageVO(start,length);
        SearchResponse response = ElasticSearchUtils.getSearch(param,pageVO.getStart(), pageVO.getLength(),"cgs_et_case","cgs",
                "CASE_TITLE","CASE_REMARKS","CASE_DESC","DETAL_ADDRESS");
        SearchHits hits = response.getHits();
        int total=(int) hits.getTotalHits();//总记录数
        int totalPage = total/length;//总页数
        if(total % length != 0){
            totalPage += 1;
        }
        List<SearchEntity> objects = new ArrayList<>();
        List<EtCase> etCases = new ArrayList<>();
        for (SearchHit searchHit : hits) {
            float score = searchHit.getScore();
            SearchEntity searchEntity = new SearchEntity();
            searchEntity.setId((String) searchHit.getSourceAsMap().get("ID"));//id
            searchEntity.setParam1((String) searchHit.getSourceAsMap().get("CASE_NO"));//案件编号
            searchEntity.setParam2((String) searchHit.getSourceAsMap().get("CASE_TITLE"));//案件名称
            searchEntity.setParam3((String) searchHit.getSourceAsMap().get("INPUTER_NAME"));//录入人
            searchEntity.setParam4((String) searchHit.getSourceAsMap().get("ACCEPT_UNIT_NAME"));//录入单位
            objects.add(searchEntity);
            /*业务修改二改动*/
//            EtCase caseById = caseService.getCaseById(o.toString());
//            etCases.add(caseById);
        }
        DataSet<SearchEntity> dataSet = new DataSet<SearchEntity>(objects, (long) total, (long) total);
        DatatablesResponse<SearchEntity> resp = DatatablesResponse.build(dataSet);
        return resp;
    }

    /**
     *     警情搜索
     *      /elastic/searchAlarm?page=1&pagesize=10
     */
    @RequestMapping("/searchAlarm")
    public @ResponseBody DatatablesResponse searchAlarm(Integer start, Integer length,String param){
        if (StringUtils.isBlank(param)){
            param="我只是";
        }
        PageVO pageVO= new PageVO(start,length);
        SearchResponse response = ElasticSearchUtils.getSearch(param,pageVO.getStart(), pageVO.getLength(),"cgs_et_alarm","cgs",
                "ALARM_PERSON_NAME","ALARM_PERSON_PHONE","LOCATION_CASE","ALARM_DESC");
        SearchHits hits = response.getHits();
        int total=(int) hits.getTotalHits();//总记录数
        int totalPage = total/length;//总页数
        if(total % length != 0){
            totalPage += 1;
        }
        List<SearchEntity> objects = new ArrayList<>();
        for (SearchHit searchHit : hits) {
            float score = searchHit.getScore();
            SearchEntity searchEntity = new SearchEntity();
            searchEntity.setId((String) searchHit.getSourceAsMap().get("ID"));//id
            searchEntity.setParam1((String) searchHit.getSourceAsMap().get("ALARM_NO"));//警情编号
            searchEntity.setParam2((String) searchHit.getSourceAsMap().get("UNIT_NAME"));//接警单位
            searchEntity.setParam3((String) searchHit.getSourceAsMap().get("LOCATION_CASE"));//发案地点
            searchEntity.setParam4((String) searchHit.getSourceAsMap().get("ALARM_PERSON_NAME"));//报警人
            searchEntity.setParam5((String) searchHit.getSourceAsMap().get("ALARM_PERSON_PHONE"));//报警人电话
            objects.add(searchEntity);
        }
        DataSet<SearchEntity> dataSet = new DataSet<SearchEntity>(objects, (long) total, (long) total);
        DatatablesResponse<SearchEntity> resp = DatatablesResponse.build(dataSet);
        return resp;
    }
    /**
     *  重点人员搜索
     *      /elastic/searchPerson?page=1&pagesize=10
     */
    @RequestMapping("/searchPerson")
    public @ResponseBody DatatablesResponse searchPerson(int start, int length,String param){
        if (StringUtils.isBlank(param)){
            param="我只是";
        }
        PageVO pageVO= new PageVO(start,length);
        SearchResponse response = ElasticSearchUtils.getSearch(param,pageVO.getStart(), pageVO.getLength(),"cgs_et_suspect","cgs",
                "NAME","BYNAME","WEIXIN_NO","IDCARD_NUM","QQ_NO","MOBILEPHONE","DEMO");
        SearchHits hits = response.getHits();
        int total=(int) hits.getTotalHits();//总记录数
        int totalPage = total/length;//总页数
        if(total % length != 0){
            totalPage += 1;
        }
        List<SearchEntity> objects = new ArrayList<>();
        for (SearchHit searchHit : hits) {
            SearchEntity searchEntity = new SearchEntity();
            searchEntity.setId((String) searchHit.getSourceAsMap().get("ID"));//id
            searchEntity.setParam1((String) searchHit.getSourceAsMap().get("NAME"));//姓名
            searchEntity.setParam2((String) searchHit.getSourceAsMap().get("SUSPECT_ID"));//重点人员编号
            searchEntity.setParam3((String) searchHit.getSourceAsMap().get("IDCARD_NUM"));//身份证号
            searchEntity.setParam4((String) searchHit.getSourceAsMap().get("ENTRY_NAME"));//录入人
            searchEntity.setParam5((String) searchHit.getSourceAsMap().get("ENTRY_UNIT_NAME"));//录入单位
            searchEntity.setParam6((String) searchHit.getSourceAsMap().get("CREAT_TIME"));//录入时间
            objects.add(searchEntity);
        }
        DataSet<SearchEntity> dataSet = new DataSet<SearchEntity>(objects, (long) total, (long) total);
        DatatablesResponse<SearchEntity> resp = DatatablesResponse.build(dataSet);
        return resp;
    }
    /**
     *   嫌疑人搜素
     *      /elastic/searchSuspect?page=1&pagesize=10
     */
    @RequestMapping("/searchSuspect")
    public @ResponseBody DatatablesResponse searchSuspect(int start, int length,String param){
        if (StringUtils.isBlank(param)){
            param="我只是";
        }
        PageVO pageVO= new PageVO(start,length);
        List<String> list = new ArrayList<>();
        list.add("CASE_TITLE");
        list.add("CASE_DESC");
        SearchResponse response = ElasticSearchUtils.getSearch(param,pageVO.getStart(), pageVO.getLength(),"cgs_ap_staff","cgs",
                "NAME","IDCARD_NUM","PHONE_NUM","REMARK");
        SearchHits hits = response.getHits();
        int total=(int) hits.getTotalHits();//总记录数
        int totalPage = total/length;//总页数
        if(total % length != 0){
            totalPage += 1;
        }
        List<SearchEntity> objects = new ArrayList<>();
        for (SearchHit searchHit : hits) {
            SearchEntity searchEntity = new SearchEntity();
            searchEntity.setId((String) searchHit.getSourceAsMap().get("ID"));//id
            searchEntity.setParam1((String) searchHit.getSourceAsMap().get("NAME"));//姓名
            searchEntity.setParam2((String) searchHit.getSourceAsMap().get("IDCARD_TYPE"));//证件类型
            searchEntity.setParam3((String) searchHit.getSourceAsMap().get("IDCARD_NUM"));//证件编号
            searchEntity.setParam4((String) searchHit.getSourceAsMap().get("PHONE_NUM"));//联系电话
            searchEntity.setParam5("1000".equals((String) searchHit.getSourceAsMap().get("SUSPECT_CLASS"))?"嫌疑人":"受害人");//涉及人员类型
            searchEntity.setParam6((String) searchHit.getSourceAsMap().get("CREAT_TIME"));//录入时间
            objects.add(searchEntity);
        }
        DataSet<SearchEntity> dataSet = new DataSet<SearchEntity>(objects, (long) total, (long) total);
        DatatablesResponse<SearchEntity> resp = DatatablesResponse.build(dataSet);
        return resp;
    }

    /**
     *   详情点击时查看权限
     * @param httpSession
     * @param request
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value="/searchPermission",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String searchPermission(HttpSession httpSession, HttpServletRequest request, String id,String type){
        SysUser user = (SysUser) request.getAttribute("user");
        //获取用户地区编码
        String userDicUnit = SysUserHelp.getSysUserDicUnit(user);
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isBlank(id)){
//            id="160EDBF64C1F00";
        }
//        id="160D9E94FDBF07";//警情测试数据
//        id="160D4A3827BF00";//嫌疑人测试
        boolean contains=false;
        String jsonStr = "";
        jsonObject.put("id",id);
        if (type.equals("1")) {
            try {
                jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_CASE, "case/findCaseById", jsonObject);
                JSONObject jsonObjectRep = JSONObject.fromObject(jsonStr);
                JSONObject respContent = (JSONObject) jsonObjectRep.get("data");
                JSONObject etCase = (JSONObject) respContent.get("etCase");
                String inputUnitCode = (String) etCase.get("inputUnitCode");
                //获取案件的归属地域
                DicUnit dicUnit = dicUtilsService.findDicUnitByCode(inputUnitCode);
                String caseLevel = "";
                if (dicUnit != null){
                    caseLevel = dicUnit.getGrade();//案件level
                    if (Integer.parseInt(user.getLevel()) < Integer.parseInt(caseLevel)){
                        return "1";
                    }else if(Integer.parseInt(user.getLevel()) == Integer.parseInt(caseLevel)){
                        //同等级判断地域编码是否相同
                        if (inputUnitCode.equals(userDicUnit)){
                            return "1";
                        }else {
                            return "2";
                        }
                    }else {
                        return "2";
                    }
                }
//                contains = zone_code.contains(userDicUnit);
            } catch (Exception e) {
                e.printStackTrace();
                return "2";
            }
        }else if (type.equals("2")){
            Map<String, String> mapResult = new HashMap<>();
            HashMap<String, Object> map = new HashMap<>();
            map.put("alarmNo",id);  //我艹，原来竟然没有根据id查询而是根据警情编号查询，真是醉了。
            String servicesCode = "alarm";
            String serviceUrl = "/ETAlarmCon/selectByPrimaryKey";
           try {
               String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, serviceUrl, map);
               JSONObject jsonObjectRep = JSONObject.fromObject(result);
               JSONObject respContent = (JSONObject) jsonObjectRep.get("data");
               String unit_code = (String) respContent.get("unitCode");
//               contains = unit_code.contains(userDicUnit);
               DicUnit dicUnit = dicUtilsService.findDicUnitByCode(unit_code);
               String alarmLevel = "";
               if (dicUnit != null){
                   alarmLevel = dicUnit.getGrade();//警情level
                   if (Integer.parseInt(user.getLevel()) < Integer.parseInt(alarmLevel)){
                       return "1";
                   }else if(Integer.parseInt(user.getLevel()) == Integer.parseInt(alarmLevel)){
                       //同等级判断地域编码是否相同
                       if (unit_code.equals(userDicUnit)){
                           return "1";
                       }else {
                           return "2";
                       }
                   }else {
                       return "2";
                   }
               }
           }catch (Exception e){
               e.printStackTrace();
               return "2";
           }
        }else if (type.equals("3")){
            JSONObject jsonObjectre = new JSONObject();
            jsonObjectre.put("suspectNo", id);
            jsonObjectre.put("userNo",user.getId());
            try {
                jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT,
                        "OperPermissionCon/selectOperPermissionBySuspectNoAndUserNo", jsonObjectre);
                JSONObject jsonObjectRep = JSONObject.fromObject(jsonStr);
                JSONObject respContent = (JSONObject) jsonObjectRep.get("data");
                if(respContent == null){
                    return "2";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "2";
            }
        }else if(type.equals("4")){//笔录及住宿信息暂时不做权限验证

        }else if(type.equals("5")){

        }
        return "1";
    }


    /**
     *  笔录信息搜索
     *      /elastic/searchPerson?page=1&pagesize=10
     */
    @RequestMapping("/searchNotes")
    public @ResponseBody DatatablesResponse searchNotes(@RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length, String param){
        if (StringUtils.isBlank(param)){
            param="黑恶";
        }
        PageVO pageVO= new PageVO(start,length);
        SearchResponse response = ElasticSearchUtils.getSearch(param,pageVO.getStart(), pageVO.getLength(),"cgs_ep_asj_bl","cgs",
                "AJBH","NAME","TARGETXM","TARGETZJHM");
        SearchHits hits = response.getHits();
        int total=(int) hits.getTotalHits();//总记录数
        int totalPage = total/length;//总页数
        if(total % length != 0){
            totalPage += 1;
        }
        List<SearchEntity> objects = new ArrayList<>();
        for (SearchHit searchHit : hits) {
            SearchEntity searchEntity = new SearchEntity();
            searchEntity.setId((String) searchHit.getSourceAsMap().get("SYSTEMID"));//主键--SYSTEMID
            searchEntity.setParam1((String) searchHit.getSourceAsMap().get("AJBH"));//案件编号
            searchEntity.setParam2((String) searchHit.getSourceAsMap().get("NAME"));//笔录名称
            searchEntity.setParam3((String) searchHit.getSourceAsMap().get("TARGETXM"));//笔录对象姓名
            searchEntity.setParam4((String) searchHit.getSourceAsMap().get("TARGETZJHM"));//笔录对象证件号码
            searchEntity.setParam5((String) searchHit.getSourceAsMap().get("STARTTIME"));//笔录创建时间
            searchEntity.setParam6((String) searchHit.getSourceAsMap().get("JLDD"));//笔录记录地点
            objects.add(searchEntity);
        }
        DataSet<SearchEntity> dataSet = new DataSet<SearchEntity>(objects, (long) total, (long) total);
        DatatablesResponse<SearchEntity> resp = DatatablesResponse.build(dataSet);
        return resp;
    }

    /**
     *  住宿信息搜索
     *      /elastic/searchPerson?page=1&pagesize=10
     */
    @RequestMapping("/searchHotel")
    public @ResponseBody DatatablesResponse searchHotel(@RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length, String param){
        if (StringUtils.isBlank(param)){
            param="黑恶";
        }
        PageVO pageVO= new PageVO(start,length);
        SearchResponse response = ElasticSearchUtils.getSearch(param,pageVO.getStart(), pageVO.getLength(),"cgs_ep_hotel_person","cgs",
                "CCODE","NAME","IDCODE","NOHOTEL");
        SearchHits hits = response.getHits();
        int total=(int) hits.getTotalHits();//总记录数
        int totalPage = total/length;//总页数
        if(total % length != 0){
            totalPage += 1;
        }
        List<SearchEntity> objects = new ArrayList<>();
        for (SearchHit searchHit : hits) {
            SearchEntity searchEntity = new SearchEntity();
            searchEntity.setId((String) searchHit.getSourceAsMap().get("CCODE"));//CCODE--编号
            searchEntity.setParam1((String) searchHit.getSourceAsMap().get("NAME"));//姓名
            searchEntity.setParam2((String) searchHit.getSourceAsMap().get("SEX"));//性别
            searchEntity.setParam3((String) searchHit.getSourceAsMap().get("IDCODE"));//身份证号
            searchEntity.setParam4((String) searchHit.getSourceAsMap().get("XZQH"));//行政区划
            searchEntity.setParam5((String) searchHit.getSourceAsMap().get("NOHOTEL"));//入住旅馆
            searchEntity.setParam6((String) searchHit.getSourceAsMap().get("LTIME"));//入住时间
            searchEntity.setParam7((String) searchHit.getSourceAsMap().get("ETIME"));//退房时间
            objects.add(searchEntity);
        }
        DataSet<SearchEntity> dataSet = new DataSet<SearchEntity>(objects, (long) total, (long) total);
        DatatablesResponse<SearchEntity> resp = DatatablesResponse.build(dataSet);
        return resp;
    }



    /**
     * @Author: sky
     * @Description:
     * @Date: 下午3:29 2018/4/2
     * @param: liveTime 入住时间  2018-04-02
    idNumHotel 入住身份证号
     */
    @RequestMapping("/analyseHotelPerson")
    @ResponseBody
    public DatatablesResponse analyseHotelPerson(@RequestParam(required=true,defaultValue="1")Integer start,
                                                 @RequestParam(required=false,defaultValue="10")Integer length,
                                                 String startTime,String endTime,String idNumHotel,String resultType){


//        String startTime = "2018-03-20";
//        String endTime = "2018-03-26";

        //返回数据集合
        List<HotelAnalyse> analyseList = new ArrayList<>();
        //重点人员集合
        List<EtSuspect> suspects = new ArrayList<>();
        //同案警人员集合
        List<ApStaff> apStaffs = new ArrayList<>();
        //重点人员身份证号码集合
        Set<String> idCards = new HashSet<>();

        //转化为字符串类型"yyyy-MM-dd HH:mm:ss格式"
        String stTime03 = startTime + " 00:00:00";
        String etTime03 = endTime + " 23:59:59";

//        根据条件获取入住人信息
        //根据身份证号码和输入日期的前后三天 查询住宿人信息 创建es查询体
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        // idNumHotel = 身份证号码
        query.must(new QueryStringQueryBuilder(idNumHotel).field("IDCODE"));
        //查询时间范围
//        query.must(new RangeQueryBuilder("LTIME").format("yyyyMMddHHmmss").from(stTime03).to(etTime03));
        query.must(new RangeQueryBuilder("LTIME").from(stTime03).to(etTime03));

        SearchResponse response = ElasticSearchUtils.getSearchs("cgs_ep_hotel_person","cgs",query);

        SearchHits hits = response.getHits();
        //入住记录集合
        List<EpHotelPerson> epHotelPeopleList = new ArrayList<>();
        int total=(int) hits.getTotalHits();//总记录数
        if (0 == total){
            //未查到数据
            DataSet<HotelAnalyse> dataSet = new DataSet<HotelAnalyse>(analyseList, (long) analyseList.size(), (long) analyseList.size());
            DatatablesResponse<HotelAnalyse> resp = DatatablesResponse.build(dataSet);
            return resp;
        }else{
            epHotelPeopleList = getEpHotePersonList(hits);
            //若有多条记录取第一条记录 //入住人信息
            EpHotelPerson epHotelPerson = epHotelPeopleList.get(0);

            //如果有数据，根据人员姓名以及身份证号查询人员是否为重点人员并查询起团伙成员
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",epHotelPerson.getName());
            jsonObject.put("idcardNum",idNumHotel);
            try {
                String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/findGang", jsonObject);
                JSONObject object = JSONObject.fromObject(jsonStr);
                JSONObject respContent = (JSONObject) object.get("data");
                if ("200".equals((String) respContent.get("code"))){
                    suspects = net.sf.json.JSONArray.toList(net.sf.json.JSONArray.fromObject(respContent.get("gangList"))
                            , new EtSuspect(), new JsonConfig());
                    for (EtSuspect suspect:suspects){
                        idCards.add(suspect.getIdcardNum());
                    }
                }else {
                    String str  = (String) respContent.get("code");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //如果有数据，根据身份证号查询涉案或者涉警的人员列表
            try {
                String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_CASE, "case/findInvolveByIdcard", jsonObject);
                JSONObject object = JSONObject.fromObject(jsonStr);
                JSONArray jsonArray = (JSONArray) object.get("data");
                //将jsonarray转化为list
                apStaffs = JSONArray.toList(jsonArray, ApStaff.class);// 过时方法
                System.out.println(apStaffs.size());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        for (EpHotelPerson epHotelPerson : epHotelPeopleList){
            // 查询同房间人员
            List<EpHotelPerson> epRoomList = getSameRoom(epHotelPerson.getNohotel(),epHotelPerson.getNoroom(),
                    epHotelPerson.getLtime(),epHotelPerson.getEtime());

//        查询同天入住人员 根据<旅馆ID>、入住当天期间（<实际入住时间>对应的当天0时0分0秒，到当天的23时59分59秒）查询住宿人信息
            //同酒店同入住人员list
            List<EpHotelPerson> epSameDayInList = getSameDayIn(epHotelPerson.getNohotel(),epHotelPerson.getLtime());

//        查询同天离开人员
            //同酒店同离开人员list
            List<EpHotelPerson> epSameDayGoList = getSameDayGo(epHotelPerson.getNohotel(),epHotelPerson.getEtime());


            //遍历同入住人员，将同房间以及与同离开交叉的人员打上标签，同时去除同离开人员集合中包含同入住的人员集合
            Map<String,Object> map = tagSameDayIn(epSameDayInList,epSameDayGoList,epRoomList,idCards,apStaffs,analyseList,false);
            analyseList = (List<HotelAnalyse>) map.get("analyseList");
            List<EpHotelPerson> epNewSameDayGoList = (List<EpHotelPerson>) map.get("newEpSameDayGoList");

            //遍历同离开人员，同时去除同入住的人员集合
            analyseList = tagSameDayGo(epNewSameDayGoList,idCards,apStaffs,analyseList,false);


//        根据当前据点查询附近酒店列表
            List<EpHotelDistance> hotelIds = new ArrayList<>();
            //创建set存储附近酒店id
            Set<String> nearHotels = new HashSet<>();
            //发送请求获取附近酒店信息
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("hotelId",epHotelPerson.getNohotel());
            try {
                String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "hotelDistance/selectByHotelId", jsonObject);
                JSONObject object = JSONObject.fromObject(jsonStr);
                JSONObject respContent = (JSONObject) object.get("data");
                if ("200".equals((String) respContent.get("code"))){
                    hotelIds = net.sf.json.JSONArray.toList(net.sf.json.JSONArray.fromObject(respContent.get("hotelList"))
                            , new EpHotelDistance(), new JsonConfig());
                    for (EpHotelDistance hotelDistance:hotelIds){
                        nearHotels.add(hotelDistance.getNearlyHotelId());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //遍历数组 查询入住人信息
            for(String hotelId:nearHotels){
                //备注  同酒店人员一定包含同入住跟同离开人员
                //查询同附近酒店人员
                List<EpHotelPerson> epSameHotelNearList = getSameHotel(hotelId,stTime03,etTime03);
                //附近酒店同入住人员list
                List<EpHotelPerson> epSameDayInNearList = getSameDayIn(hotelId,epHotelPerson.getLtime());
                //附近酒店同离开人员list
                List<EpHotelPerson> epSameDayGoNearList = getSameDayGo(hotelId,epHotelPerson.getEtime());

                //遍历同入住人员，将同房间以及与同离开交叉的人员打上标签，同时去除同离开人员集合中包含同入住的人员集合
                Map<String,Object> map02 = tagSameDayIn(epSameDayInNearList,epSameDayGoNearList,
                        new ArrayList<EpHotelPerson>(),idCards,apStaffs,analyseList,true);
                analyseList = (List<HotelAnalyse>) map02.get("analyseList");
                List<EpHotelPerson> epNewSameDayGoNearList = (List<EpHotelPerson>) map02.get("newEpSameDayGoList");

                //遍历同离开人员，同时去除同入住的人员集合
                analyseList = tagSameDayGo(epNewSameDayGoNearList,idCards,apStaffs,analyseList,true);


                //通过遍历后仅保存同附近酒店的人员列表（包含了所有标签）
                //如果是图形展示的话，返回本酒店与附近酒店的关系list
                if ("2".equals(resultType)&& !epSameHotelNearList.isEmpty()){
                    HotelAnalyse hotelAnalyse = new HotelAnalyse();
                    //在HOTELINFOBean的hotelno存入本酒店id
                    HotelAnalyse.HOTELINFOBean hotelinfoBean = new HotelAnalyse.HOTELINFOBean();
                    hotelinfoBean.setNOHOTEL(epHotelPerson.getNohotel());
                    hotelAnalyse.setHOTELINFO(hotelinfoBean);
                    //在PERSONINFOBean中身份证号中存入附近酒店id
                    HotelAnalyse.PERSONINFOBean personinfoBean = new HotelAnalyse.PERSONINFOBean();
                    personinfoBean.setIDCODE(hotelId);
                    //定义酒店图标类型，性别为10
                    personinfoBean.setSEX("10");
                    hotelAnalyse.setPERSONINFO(personinfoBean);
                    //存入结果list中
                    analyseList.add(hotelAnalyse);
                }
            }

        }

        DataSet<HotelAnalyse> dataSet = new DataSet<HotelAnalyse>(analyseList, (long) analyseList.size(), (long) analyseList.size());
        DatatablesResponse<HotelAnalyse> resp = DatatablesResponse.build(dataSet);
        return resp;

    }


    /**
     * @Author: sky
     * @Description: 给同住人集合添加标签
     * @Date: 下午3:18 2018/4/20
     * @param: epSameDayInList 同入住人员集合
    epSameDayGoList 同离开人员集合
    epRoomList 同房间人员集合
    idCards 团伙人员集合
    apStaffs 同案警集合
    analyseList 结果集合
     */
    public Map<String,Object> tagSameDayIn(List<EpHotelPerson> epSameDayInList,List<EpHotelPerson> epSameDayGoList,
                             List<EpHotelPerson> epRoomList,Set<String> idCards,List<ApStaff> apStaffs,
                                           List<HotelAnalyse> analyseList,boolean isNearByHotel){

        List<EpHotelPerson> newEpSameDayGoList = new ArrayList<>();
        for (EpHotelPerson epHotelPerson : epSameDayGoList){
            newEpSameDayGoList.add(epHotelPerson);
        }
        Map<String,Object> resultMap = new HashMap<>();


        for (EpHotelPerson sameDayInPerson : epSameDayInList){
            //人员标签集合
            List<String> tags = new ArrayList<>();
            tags.add(TagEnum.SAME_DAY_IN.getTag());

            for (EpHotelPerson sameDayGoPerson : epSameDayGoList){
                //同离开
                if (sameDayGoPerson.getCcode().equals(sameDayInPerson.getCcode())){
                    tags.add(TagEnum.SAME_DAY_GO.getTag());
                    try {
                        if (!newEpSameDayGoList.isEmpty() && newEpSameDayGoList.contains(sameDayGoPerson)){
                            newEpSameDayGoList.remove(sameDayGoPerson);
                        }
                    }catch (ConcurrentModificationException e){
                        e.printStackTrace();
                    }
                }
            }

            for (EpHotelPerson sameRoomPerson : epRoomList){
                //同房间
                if (sameRoomPerson.getCcode().equals(sameDayInPerson.getCcode())){
                    tags.add(TagEnum.SAME_ROOM.getTag());
                }
            }

            if (!idCards.isEmpty()){
                for (String idCardNo : idCards){
                    //同团伙
                    if (idCardNo.equals(sameDayInPerson.getIdcode())){
                        tags.add(TagEnum.SAME_GANG.getTag());
                    }
                }
            }

            if (isNearByHotel){
                tags.add(TagEnum.SAME_NEAR_HOTEL.getTag());
            }

            //同案警
            String casetag = isSameCase(tags,apStaffs,sameDayInPerson);

            //存入结果集合
            HotelAnalyse hotelAnalyse = getRestultEntity(tags,casetag,sameDayInPerson);
            analyseList.add(hotelAnalyse);
        }

        if (newEpSameDayGoList.isEmpty()){
            newEpSameDayGoList = new ArrayList<>();
        }
        resultMap.put("analyseList",analyseList);
        resultMap.put("newEpSameDayGoList",newEpSameDayGoList);
        return resultMap;
    }


    /**
     * @Author: sky
     * @Description:给同离开人集合添加标签
     * @Date: 下午3:28 2018/4/20
     * @param: epSameDayGoList 同离开人员集合
    idCards 团伙人员集合
    apStaffs 同案警集合
    analyseList 结果集合
     */
    public List<HotelAnalyse> tagSameDayGo(List<EpHotelPerson> epSameDayGoList,Set<String> idCards,
                             List<ApStaff> apStaffs,List<HotelAnalyse> analyseList,boolean isNearByHotel){

        for (EpHotelPerson sameDayGoPerson : epSameDayGoList){
            //人员标签集合
            List<String> tags = new ArrayList<>();
            tags.add(TagEnum.SAME_DAY_GO.getTag());

            if (!idCards.isEmpty()){
                for (String idCardNo : idCards){
                    //同团伙
                    if (idCardNo.equals(sameDayGoPerson.getIdcode())){
                        tags.add(TagEnum.SAME_GANG.getTag());
                    }
                }
            }

            if (isNearByHotel){
                tags.add(TagEnum.SAME_NEAR_HOTEL.getTag());
            }

            //同案警
            String casetag = isSameCase(tags,apStaffs,sameDayGoPerson);

            //存入结果集合
            HotelAnalyse hotelAnalyse = getRestultEntity(tags,casetag,sameDayGoPerson);
            analyseList.add(hotelAnalyse);
        }

        return analyseList;
    }

    /**
     * @Author: sky
     * @Description:判断是否同案警 返回标签集合
     * @Date: 下午3:00 2018/4/20
     * @param: tags
    apStaff
    sameDayGoPerson
     */
    public String isSameCase(List<String> tags,List<ApStaff> apStaffs,EpHotelPerson sameDayGoPerson){
        String casetag = "";
        if (!apStaffs.isEmpty()){
            for(ApStaff apStaff :apStaffs){
                //同案警
                if (apStaff.getIdcardNum().equals(sameDayGoPerson.getIdcode())){
                    switch (apStaff.getTagType()){
                        case "1":
                            //同案件
                            casetag  = "1";
                            break;
                        case "2":
                            casetag  = "2";
                            //同警情
                            break;
                        case "3":
                            //同案警
                            casetag  = "3";
                            break;
                    }
                }
            }
        }

        return casetag;
    }

    /**
     * @Author: sky
     * @Description:获取结果对象
     * @Date:2018/4/3
     * @param:
     */
    public HotelAnalyse getRestultEntity(List<String> tags,String casetag,EpHotelPerson hotelPerson){
        HotelAnalyse hotelAnalyse = new HotelAnalyse();
        hotelAnalyse.setTAG(tags);
        hotelAnalyse.setNOROOM(hotelPerson.getNoroom());
        hotelAnalyse.setCASETAG(casetag);
        //需改动
        String inDate = DateHelp.date2Str(hotelPerson.getLtime(),"yyyy-MM-dd HH:mm:ss");
        //需改动
        hotelAnalyse.setLTIME(inDate);
        //转换时间格式
//        String goDate = DateHelp.date2Str(
//                DateHelp.str2Date(hotelPerson.getEtime(),"yyyyMMddHHmmss"),"yyyy-MM-dd HH:mm:ss");
        String goDate = hotelPerson.getEtime();
        hotelAnalyse.setETIME(goDate);
        //填充入住人员信息
        HotelAnalyse.PERSONINFOBean personinfoBean = new HotelAnalyse.PERSONINFOBean();
        personinfoBean.setNAME(hotelPerson.getName());
        personinfoBean.setSEX(hotelPerson.getSex());
        personinfoBean.setBDATE(hotelPerson.getBdate());
        personinfoBean.setADDRESS(hotelPerson.getAddress());
        personinfoBean.setIDCODE(hotelPerson.getIdcode());
        hotelAnalyse.setPERSONINFO(personinfoBean);
        //填充酒店信息
        HotelAnalyse.HOTELINFOBean hotelinfoBean = findHotelByNo(hotelPerson.getNohotel());
        hotelAnalyse.setHOTELINFO(hotelinfoBean);
        //计算入住天数
        //将etime转为date格式
        String days = "";
        try {
            Date eDate =  DateHelp.str2Date(hotelPerson.getEtime(),"yyyy-MM-dd HH:mm:ss");
            days = DateHelp.differentDays(hotelPerson.getLtime(),eDate)+"";
        }catch (Exception e){
            e.printStackTrace();
        }
        hotelAnalyse.setDAYS(days);

        return hotelAnalyse;
    }


    /**
     * @Author: sky
     * @Description:发送http请求获取酒店信息
     * @Date: 上午9:57 2018/4/4
     * @param: hotelId
     */
    public HotelAnalyse.HOTELINFOBean findHotelByNo(String hotelId){

        HotelAnalyse.HOTELINFOBean hotelinfoBean = new HotelAnalyse.HOTELINFOBean();

        //发送请求
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",hotelId);
        try {
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "/hotel/selectById", jsonObject);
            JSONObject object = JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) object.get("data");
            EpHotel epHotel  = GsonUtils.toBean(respContent.toString(), EpHotel.class);
            if (epHotel != null){
                hotelinfoBean.setNOHOTEL(epHotel.getHnohotel());
                hotelinfoBean.setHNAME(epHotel.getHname());
                //如果酒店星级为空，填充星级为"0"
                if(StringUtils.isBlank(epHotel.getStars())){
                    hotelinfoBean.setSTARS("0");
                }else {
                    hotelinfoBean.setSTARS(epHotel.getStars());
                }
                hotelinfoBean.setHADDRESS(epHotel.getHaddress());
                hotelinfoBean.setSJD(epHotel.getSjd());
                hotelinfoBean.setSWD(epHotel.getSwd());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hotelinfoBean;
    }

    /**
     * @Author: sky
     * @Description: 根据date类型获取对应当天的00：00：00 到 23：59：59
     * @Date: 下午6:39 2018/4/2
     * @param: date
     */
    public Map<String,String> getDateStr(Date date){
        Map<String,String> dateMap = new HashMap<>();
        String dateAll = DateHelp.date2Str(date,"yyyy-MM-dd HH:mm:ss");
        dateMap.put("stTime",dateAll.substring(0,10)+" 00:00:00");
        dateMap.put("enTime",dateAll.substring(0,10)+" 23:59:59");
        return dateMap;
    }

    /**
     * @Author: sky
     * @Description:根据String类型获取对应当天的00：00：00 到 23：59：59
     * @Date: 下午6:39 2018/4/2
     * @param: date
     */
    public Map<String,String> getDateStr(String date){
        Map<String,String> dateMap = new HashMap<>();
        dateMap.put("stTime",date.substring(0,10)+" 00:00:00");
        dateMap.put("enTime",date.substring(0,10)+" 23:59:59");
        return dateMap;
    }


    /**
     * @Author: sky
     * @Description:根据es返回数据得到酒店人员列表 公共方法
     * @Date: 下午6:39 2018/4/2
     * @param: hits
     */
    public List<EpHotelPerson> getEpHotePersonList(SearchHits hits){
        List<EpHotelPerson> epHotelPeopleList = new ArrayList<>();
        for (SearchHit searchHit : hits) {
            EpHotelPerson entity = new EpHotelPerson();
            //从es中获取的为date类型 入住时间和离开时间
            Date ltimeDate = DateHelp.str2Date((String)searchHit.getSourceAsMap().get("LTIME"),"yyyy-MM-dd HH:mm:ss");
            entity.setLtime(ltimeDate);//入住时间 需改动
            entity.setEtime((String) searchHit.getSourceAsMap().get("ETIME"));//退房时间 需改动
            entity.setNoroom((String) searchHit.getSourceAsMap().get("NOROOM"));//入住房间号
            entity.setNohotel((String) searchHit.getSourceAsMap().get("NOHOTEL"));//入住旅馆
            entity.setIdcode((String) searchHit.getSourceAsMap().get("IDCODE"));//入住人员身份证
            entity.setName((String) searchHit.getSourceAsMap().get("NAME"));//姓名
            entity.setSex((String) searchHit.getSourceAsMap().get("SEX"));//性别
            entity.setBdate((String) searchHit.getSourceAsMap().get("BDATE"));//出生年月
            entity.setAddress((String) searchHit.getSourceAsMap().get("ADDRESS"));//户籍 地址
            entity.setCcode((String) searchHit.getSourceAsMap().get("CCODE"));//编号  标识
            epHotelPeopleList.add(entity);
        }
        return epHotelPeopleList;
    }


    /**
     * @Author: sky
     * @Description:查询同房间人员<旅馆ID>、<房间ID>、<实际入住时间>、<实际离开时间>
     * @Date: 下午7:18 2018/4/2
     * @param: hotelId
    roomId
    lTime
    eTime
     */
    public List<EpHotelPerson> getSameRoom(String hotelId,String roomId,Date lTime,String eTime){
        //根据<旅馆ID>、<房间ID>、<实际入住时间>、<实际离开时间>住宿人信息 创建es查询体
        BoolQueryBuilder queryRoom = QueryBuilders.boolQuery();
        // NOHOTEL = 酒店ID
        queryRoom.must(new QueryStringQueryBuilder(hotelId).field("NOHOTEL"));
        // NOROOM = 房间ID
        queryRoom.must(new QueryStringQueryBuilder(roomId).field("NOROOM"));
        //<实际入住时间> 需改动
        String inDate = DateHelp.date2Str(lTime,"yyyy-MM-dd HH:mm:ss");
        //构造时间查询条件 需改动
//        queryRoom.must(new RangeQueryBuilder("LTIME").format("yyyyMMddHHmmss")
//                .from(inDate).to(inDate));
        queryRoom.must(new RangeQueryBuilder("LTIME").from(inDate).to(inDate));
        //<实际离开时间> 需改动
//        queryRoom.must(new RangeQueryBuilder("ETIME").format("yyyyMMddHHmmss")
//                .from(eTime).to(eTime));
        queryRoom.must(new RangeQueryBuilder("ETIME").from(eTime).to(eTime));

        SearchResponse responseRoom = ElasticSearchUtils.getSearchs("cgs_ep_hotel_person","cgs",queryRoom);

        SearchHits hitsRoom = responseRoom.getHits();
        //同房间人员list
        List<EpHotelPerson> epRoomList = new ArrayList<>();
        if (hitsRoom.getTotalHits() == 0){
            //无同房间人员
        }else {
            epRoomList = getEpHotePersonList(hitsRoom);
        }

        return epRoomList;
    }

    /**
     * @Author: sky
     * @Description:根据酒店id、入住时间同天入住人员查询
     * @Date: 下午6:40 2018/4/2
     * @param: hotelId
    lTime
     */
    public List<EpHotelPerson> getSameDayIn(String hotelId,Date lTime){
        //根据<旅馆ID>、入住当天期间（<实际入住时间>对应的当天0时0分0秒，到当天的23时59分59秒）查询住宿人信息 创建es查询体
        BoolQueryBuilder querySameDayIn = QueryBuilders.boolQuery();
        // NOHOTEL = 酒店ID
        querySameDayIn.must(new QueryStringQueryBuilder(hotelId).field("NOHOTEL"));
        //查询入住时间范围 对应的当天0时0分0秒，到当天的23时59分59秒
        Map<String,String> dateInMap = getDateStr(lTime);
        //构造时间查询条件 需改动
//        querySameDayIn.must(new RangeQueryBuilder("LTIME").format("yyyyMMddHHmmss")
//                .from(dateInMap.get("stTime")).to(dateInMap.get("enTime")));
        querySameDayIn.must(new RangeQueryBuilder("LTIME").from(dateInMap.get("stTime")).to(dateInMap.get("enTime")));

        SearchResponse responseSameDayIn = ElasticSearchUtils.getSearchs("cgs_ep_hotel_person","cgs",querySameDayIn);
        SearchHits hitsSameDayIn = responseSameDayIn.getHits();
        //同天入住同一酒店人员list
        List<EpHotelPerson> epSameDayInList = new ArrayList<>();
        if (hitsSameDayIn.getTotalHits() == 0){
            //无同天入住人员
        }else {
            epSameDayInList = getEpHotePersonList(hitsSameDayIn);
        }
        return epSameDayInList;
    }

    /**
     * @Author: sky
     * @Description:根据局酒店id、离开时间同天离开人员查询
     * @Date: 下午6:41 2018/4/2
     * @param: hotelId
    eTime
     */
    public List<EpHotelPerson> getSameDayGo(String hotelId,String eTime){
        //根据<旅馆ID>、离开当天期间（<实际离开时间>对应的当天0时0分0秒，到当天的23时59分59秒）查询住宿人信息 创建es查询体
        BoolQueryBuilder querySameDayGo = QueryBuilders.boolQuery();
        // NOHOTEL = 酒店ID
        querySameDayGo.must(new QueryStringQueryBuilder(hotelId).field("NOHOTEL"));
        //查询离开时间范围 对应的当天0时0分0秒，到当天的23时59分59秒 需改动
        Map<String,String> dateGoMap = getDateStr(eTime);
        //构造时间查询条件 需改动
//        querySameDayGo.must(new RangeQueryBuilder("ETIME").format("yyyyMMddHHmmss")
//                .from(dateGoMap.get("stTime")).to(dateGoMap.get("enTime")));
        querySameDayGo.must(new RangeQueryBuilder("ETIME").from(dateGoMap.get("stTime")).to(dateGoMap.get("enTime")));

        SearchResponse responseSameDayGo = ElasticSearchUtils.getSearchs("cgs_ep_hotel_person","cgs",querySameDayGo);
        SearchHits hitsSameDayGo = responseSameDayGo.getHits();
        //同天离开同一酒店人员list
        List<EpHotelPerson> epSameDayGoList = new ArrayList<>();
        if (hitsSameDayGo.getTotalHits() == 0){
            //无同天离开人员
        }else {
            epSameDayGoList = getEpHotePersonList(hitsSameDayGo);
        }

        return epSameDayGoList;
    }

    /**
     * @Author: sky
     * @Description:同酒店人员查询
     * @Date: 下午6:41 2018/4/2
     * @param: hotelId
    stTime03
    etTime03
    stTime02
    etTime02
     */
    public List<EpHotelPerson> getSameHotel(String hotelId,String stTime03,String etTime03){
        //根据<旅馆ID>、<开始时间>、<结束时间>查询住宿人信息 创建es查询体
        BoolQueryBuilder querySameHotel = QueryBuilders.boolQuery();
        // NOHOTEL = 酒店ID
        querySameHotel.must(new QueryStringQueryBuilder(hotelId).field("NOHOTEL"));
        //查询时间范围 前后三天 需改动
//        querySameHotel.must(new RangeQueryBuilder("LTIME").format("yyyyMMddHHmmss").from(stTime03).to(etTime03));
        querySameHotel.must(new RangeQueryBuilder("LTIME").from(stTime03).to(etTime03));

        SearchResponse responseSameHotel = ElasticSearchUtils.getSearchs("cgs_ep_hotel_person","cgs",querySameHotel);
        SearchHits hitsSameHotel = responseSameHotel.getHits();
        //同天离开同一酒店人员list
        List<EpHotelPerson> epSameHotelList = new ArrayList<>();
        if (hitsSameHotel.getTotalHits() == 0){
            //无同天离开人员
        }else {
            epSameHotelList = getEpHotePersonList(hitsSameHotel);
        }

        return epSameHotelList;
    }


    /**
     * 话单统计
     * @param httpSession
     * @param request
     * @param paramMap
     * @return
     */
    @RequestMapping(value="/contact",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject contact(HttpSession httpSession, HttpServletRequest request,@RequestParam HashMap<String,Object> paramMap){
        contactList = new ArrayList();
        JSONObject result =new JSONObject();
        String phoneNumber = paramMap.get("phoneNumber").toString();
        //查询手机号
        result.put("phoneNumber",phoneNumber);
        //查询条件
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        //开始时间
        query.must(new RangeQueryBuilder("START_TIME").from(paramMap.get("startTime").toString()+" 00:00:00").to(paramMap.get("finishTime").toString()+" 23:59:59"));
        //手机号
//        query.must(new QueryStringQueryBuilder(phoneNumber).field("CALLING"));
        query.must(QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("CALLING", phoneNumber))
                .should(QueryBuilders.matchQuery("CALLED", phoneNumber)));
        //话单集合
        List<HashMap<String, Object>> list = ElasticSearchUtils.getSearchList("cgs_call_record", "cgs", query);
        //话单集合为空时
        if(list==null||list.size()==0){
            result.put("statistics",null);
            return result;
        }
        //逻辑处理
        List lists = contactLogic(list, paramMap);
        List mapList = new ArrayList();
        for (Object maps:lists) {
            Map<String, Object> map= (Map<String, Object>) maps;
            if(Integer.parseInt(map.get("calling").toString())>0){
                mapList.add(map);
            }
            if(Integer.parseInt(map.get("called").toString())>0){
                HashMap<String, Object> mainMap = new HashMap<>();
                mainMap.put("all",map.get("all"));
                mainMap.put("duration",map.get("duration"));
                mainMap.put("durationSort",map.get("durationSort"));
                mainMap.put("phoneNumber",map.get("phone"));
                mainMap.put("operate",map.get("operate"));
                mainMap.put("called",map.get("called"));
                mainMap.put("phone",map.get("phoneNumber"));
                mainMap.put("city",map.get("city"));
                mainMap.put("calling",map.get("calling"));
                mainMap.put("casetag",map.get("casetag"));
                mainMap.put("areacode",map.get("areacode"));
                mainMap.put("allSort",map.get("allSort"));
                mapList.add(mainMap);
            }
        }
        result.put("statistics",mapList);
        return result;
    }
    
    /**
     * 通联人、共同关系人、话单统计功能图形页面，弹框展示话单详情列表
     * @param request
     * @param paramMap
     * @return
     */
    @RequestMapping(value="/callDetailsList",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public DatatablesResponse<SearchEntity> callDetailsList(Integer start,Integer length,HttpServletRequest request,
            @RequestParam HashMap<String,Object> paramMap){
        String startTime= request.getParameter("startTime");//格式：年月日
        String endTime= request.getParameter("endTime");//格式：年月日
        String callNum= request.getParameter("callNum");
        String calledNum= request.getParameter("calledNum");

        //查询条件
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        //开始时间
        query.must(new RangeQueryBuilder("START_TIME").from(startTime+" 00:00:00").to(endTime+" 23:59:59"));
        //手机号
//        query.must(new QueryStringQueryBuilder(phoneNumber).field("CALLING"));
        query.must(QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("CALLING", callNum))
                .should(QueryBuilders.matchQuery("CALLED", callNum)));
        query.must(QueryBuilders.boolQuery()
        		.should(QueryBuilders.matchQuery("CALLING", calledNum))
        		.should(QueryBuilders.matchQuery("CALLED", calledNum)));
        if(start==0){
            start = 1;
        }else {
            start=start/length+1;
        }
        
        //话单集合
        SearchResponse response = ElasticSearchUtils.getCallDetailsList(start,length,"cgs_call_record", "cgs", query);
        SearchHits hits = response.getHits();
        int total=(int) hits.getTotalHits();//总记录数
        List<SearchEntity> objects = new ArrayList<>();
        for (SearchHit searchHit : hits) {
            //时间、本号码、对方号码、类型、时长(秒)、通话地
            //{CALLED=13802561339, CALLING_TYPE=被叫,START_TIME=2015-09-14 08:59:05, 
            //CALLING_PLACE=深圳, CALLING=13823604011, CALLING_TIME=121, REMAKE=}
            SearchEntity searchEntity = new SearchEntity();
            searchEntity.setParam1(searchHit.getSourceAsMap().get("START_TIME").toString());//时间
            searchEntity.setParam2(searchHit.getSourceAsMap().get("CALLING").toString());//本号码
            searchEntity.setParam3(searchHit.getSourceAsMap().get("CALLED").toString());//对方号码
            searchEntity.setParam4(searchHit.getSourceAsMap().get("CALLING_TYPE").toString());//类型
            searchEntity.setParam5(searchHit.getSourceAsMap().get("CALLING_TIME").toString());//时长(秒)
            searchEntity.setParam6(searchHit.getSourceAsMap().get("CALLING_PLACE").toString());//通话地
            objects.add(searchEntity);
        }
        DataSet<SearchEntity> dataSet = new DataSet<SearchEntity>(objects, (long) total, (long) total);
        DatatablesResponse<SearchEntity> resp = DatatablesResponse.build(dataSet);
        return resp;
    }

    /**
     * 话单统计逻辑
     * @param list
     * @param paramMap
     * @return
     */
    public List  contactLogic(List<HashMap<String, Object>> list,HashMap<String,Object> paramMap){
        //查询手机号
        String phoneNumber = paramMap.get("phoneNumber").toString();
        String tonlyTime = paramMap.get("TonlyTime").toString();
        int start=0;
        int end=0;
        if(!"-1".equals(tonlyTime)){
            String[] split = tonlyTime.split("-");
            start=Integer.parseInt(split[0]+"0000");
            end=Integer.parseInt(split[1]+"0000");
        }else{
            end=999999;
        }

        ArrayList<Map<String, Object>> lists =new ArrayList<>();
        HashMap<String, Object> maps = new HashMap<>();
        int size = list.size();
        for(int i=0;i<size;i++){
            HashMap<String, Object> map = list.get(i);
            String calling = map.get("CALLING").toString();
            String called = map.get("CALLED").toString();
            if(calling.equals(phoneNumber)){
                calling=called;
            }
            //时间
            String start_time = map.get("START_TIME").toString();
            String time = start_time.substring(start_time.indexOf(" ")+1,start_time.length());
            time=time.replace(":","");
            int timenum = Integer.parseInt(time);

//            if(!"400".equals(calling.substring(0, 3))&&calling.length()>=7){
                if("23-07".equals(tonlyTime)){
                    if(!(start<=timenum||timenum<=end)){
                        continue;
                    }
                }else{
                    if(!(start<=timenum&&timenum<=end)){
                        continue;
                    }
                }
                //呼叫类型
                String calling_type = map.get("CALLING_TYPE").toString();
                //呼叫时长
                int calling_time =Integer.parseInt(map.get("CALLING_TIME").toString());
                if("主叫".equals(calling_type)||"被叫".equals(calling_type)){
                    //频次-全部
                    if(maps.containsKey(calling)){
                        HashMap<String, Object> mainMap = (HashMap<String, Object>) maps.get(calling);
                        Integer all = Integer.parseInt(mainMap.get("all").toString());
                        mainMap.put("all",++all);
                        Integer duration = Integer.parseInt(mainMap.get("duration").toString());
                        duration+=calling_time;
                        mainMap.put("duration",duration);
                        maps.put(calling,mainMap);
                    }else{
                        HashMap<String, Object> mainMap = new HashMap<>();
                        mainMap.put("phone",calling);//手机号
                        mainMap.put("all",1);//频次-全部
                        mainMap.put("calling",0);//频次-全部
                        mainMap.put("called",0);//频次-全部
                        mainMap.put("duration",calling_time);//时长-全部
                        mainMap.put("phoneNumber",phoneNumber);
                        maps.put(calling,mainMap);
                    }
                    //频次-主叫
                    if("主叫".equals(calling_type)){
                        HashMap<String, Integer> mainMap = (HashMap<String, Integer>) maps.get(calling);
                        Integer ii = Integer.parseInt(mainMap.get("calling").toString());
                        mainMap.put("calling",++ii);
                        maps.put(calling,mainMap);
                    }
                    //频次-主叫
                    if("被叫".equals(calling_type)){
                        HashMap<String, Integer> mainMap = (HashMap<String, Integer>) maps.get(calling);
                        Integer ii = Integer.parseInt(mainMap.get("called").toString());
                        mainMap.put("called",++ii);
                        maps.put(calling,mainMap);
                    }
                }
//            }
        }
        for (String key:maps.keySet()) {
            lists.add((Map<String, Object>) maps.get(key));
        }
        //频次排名
        Collections.sort(lists, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o2, Map<String, Object> o1) {
                int all1 = Integer.parseInt(o1.get("all").toString());
                int all2 = Integer.parseInt(o2.get("all").toString());
                if((all1-all2)>0){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
        for(int i=0;i<lists.size();i++){
            Map<String, Object> map = lists.get(i);
            map.put("allSort",++i);
            lists.set(--i,map);
        }
        //时长排名
        Collections.sort(lists, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o2, Map<String, Object> o1) {
                int duration1 = Integer.parseInt(o1.get("duration").toString());
                int duration2 = Integer.parseInt(o2.get("duration").toString());
                if((duration1-duration2)>0){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
        for(int i=0;i<lists.size();i++){
            Map<String, Object> map = lists.get(i);
            map.put("durationSort",++i);
            lists.set(--i,map);
        }
        HashSet<Map<String, Object>> set = new HashSet<>();
        //TOP数量
        int topnumber = Integer.parseInt(paramMap.get("Topnumber").toString());
        for(int i=0;i<lists.size();i++){
            Map<String, Object> map = lists.get(i);
            int allSort = Integer.parseInt(map.get("allSort").toString());
            int durationSort = Integer.parseInt(map.get("durationSort").toString());
            if(allSort<=topnumber||durationSort<=topnumber){
                String phone = map.get("phone").toString();
                map.put("casetag","");
                if(phone.length()>=11){
                    phone=phone.substring(0,7);
                    String sql ="SELECT a.PROVINCE,a.CITY,a.OPERATE,a.AREACODE FROM dic_to_tel a WHERE a.PREFIXCODE='"+phone+"'";
                    List<Map<String, Object>> tab = JdbcHandler.selectTab(sql);
                    map.put("city",(tab.size()==0?"未知":tab.get(0).get("CITY")));
                    map.put("operate",(tab.size()==0?"未知":tab.get(0).get("OPERATE")));
                    map.put("areacode",(tab.size()==0?"未知":tab.get(0).get("AREACODE")));
                }else{
                    map.put("city",("未知"));
                    map.put("operate",("未知"));
                    map.put("areacode",("未知"));
                }
                set.add(map);
            }
        }
        contactList= new ArrayList(set);
        return contactList;
    }

    /**
     * 话单统计列表
     * @param httpSession
     * @param request
     * @param search
     * @param start
     * @param length
     * @return
     */
    @RequestMapping(value="/contactList",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public DatatablesResponse contactList(HttpSession httpSession, HttpServletRequest request, @RequestParam(value = "search[value]") String search, @RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length){
        List list;
        long size = contactList.size();
        if(size>0){
            if((start + length)>size){
                list=contactList.subList(start,(int)size);
            }else{
                list=contactList.subList(start,(start + length));
            }
        }else{
            list=new ArrayList();
        }
        DataSet dataSet = new DataSet(list,size,size);
        DatatablesResponse resp = DatatablesResponse.build(dataSet);
        return resp;
    }


    /**
     * 通联人
     * @param httpSession
     * @param request
     * @param paramMap
     * @return
     */
    @RequestMapping(value="/contactPerson",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject contactPerson(HttpSession httpSession, HttpServletRequest request,@RequestParam HashMap<String,Object> paramMap){
        contactPersonList = new ArrayList();
        JSONObject result =new JSONObject();
        result.put("flag",false);
        String phoneNumber = paramMap.get("phoneNumber").toString();
        //查询手机号
        result.put("phoneNumber",phoneNumber);
        //查询条件
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        //开始时间
        query.must(new RangeQueryBuilder("START_TIME").from(paramMap.get("startTime").toString()+" 00:00:00").to(paramMap.get("finishTime").toString()+" 23:59:59"));
        //手机号
//        query.must(new QueryStringQueryBuilder(phoneNumber).field("CALLING"));
        query.must(QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("CALLING", phoneNumber))
                .should(QueryBuilders.matchQuery("CALLED", phoneNumber)));
        //话单集合
        List<HashMap<String, Object>> list = new ArrayList<>();
        try {
            list = ElasticSearchUtils.getSearchList("cgs_call_record", "cgs", query);
            result.put("flag",true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //话单集合为空时
        if(list==null||list.size()==0){
            result.put("statistics",null);
            return result;
        }
        //逻辑处理
        List lists = contactPersonLogic(list, paramMap);
        contactPersonList=lists;
        List mapList = new ArrayList();
        for (Object maps:lists) {
            Map<String, Object> map= (Map<String, Object>) maps;
            if(Integer.parseInt(map.get("calling").toString())>0){
                mapList.add(map);
            }
            if(Integer.parseInt(map.get("called").toString())>0){
                HashMap<String, Object> mainMap = new HashMap<>();
                mainMap.put("all",map.get("all"));
                mainMap.put("exception",map.get("exception"));
                mainMap.put("phoneNumber",map.get("phone"));
                mainMap.put("operate",map.get("operate"));
                mainMap.put("called",map.get("called"));
                mainMap.put("phone",map.get("phoneNumber"));
                mainMap.put("city",map.get("city"));
                mainMap.put("integral",map.get("integral"));
                mainMap.put("calling",map.get("calling"));
                mainMap.put("casetag",map.get("casetag"));
                mainMap.put("integralSort",map.get("integralSort"));
                mainMap.put("areacode",map.get("areacode"));
                mapList.add(mainMap);
            }
        }
        result.put("statistics",mapList);
        return result;
    }

    /**
     * 通联人逻辑
     * @param list
     * @param paramMap
     * @return
     */
    public static List  contactPersonLogic(List<HashMap<String, Object>> list, HashMap<String, Object> paramMap){
        //获取时段异常时段和对应的频次
        String timeInterval1 = Global.getConfig("timeInterval");
        if(timeInterval1==null||"".equals(timeInterval1)){
            timeInterval1="(23-00]_10,(00-07]_10,(13-19]_20";
        }
        Map<String, String> timeIntervalException =toMap(timeInterval1);
        //查询手机号
        String phoneNumber = paramMap.get("phoneNumber").toString();
//        String tonlyTime = paramMap.get("TonlyTime").toString();
//        int start=0;
//        int end=0;
//        if(!"-1".equals(tonlyTime)){
//            String[] split = tonlyTime.split("-");
//            end=Integer.parseInt(split[1]+"0000");
//            start=Integer.parseInt(split[0]+"0000");
//        }else{
//            end=999999;
//        }

        ArrayList<Map<String, Object>> lists =new ArrayList<>();

        HashMap<String, Object> maps = new HashMap<>();
        int size = list.size();
        for(int i=0;i<size;i++){
            //每条话单数据
            HashMap<String, Object> map = list.get(i);
            //主叫
            String calling = map.get("CALLING").toString();
            //被叫
            String called = map.get("CALLED").toString();
            if(calling.equals(phoneNumber)){
                calling=called;
            }
            //通话时间
            String start_time = map.get("START_TIME").toString();
            String time = start_time.substring(start_time.indexOf(" ")+1,start_time.length());
            time=time.replace(":","");
            int timenum = Integer.parseInt(time);

//            if(!"400".equals(calling.substring(0, 3))&&calling.length()>=11){
//                if("23-07".equals(tonlyTime)){
//                    if(!(start<=timenum||timenum<=end)){
//                        continue;
//                    }
//                }else{
//                    if(!(start<=timenum&&timenum<=end)){
//                        continue;
//                    }
//                }
                //呼叫类型
                String calling_type = map.get("CALLING_TYPE").toString();
                //呼叫时长
                String calling_time =map.get("CALLING_TIME").toString();
                //获取时长积分
                String duration = Global.getConfig("durationIntegral");
                if(duration==null||"".equals(duration)){
                    duration="(0-60]_1,(60-120]_2,(120-max]_3";
                }
                int durationIntegral = gainIntegral( duration, calling_time);
                //获取时段积分
                String timeInterval = Global.getConfig("timeIntervalIntegral");
                if(timeInterval==null||"".equals(timeInterval)){
                    timeInterval="(07-11]_1,(11-13]_2,(13-19]_3,(19-23]_4,(23-00]_5,(00-07]_6";
                }
                timeInterval=timeInterval.replace("-","0000-");
                timeInterval=timeInterval.replace("]","0000]");
                int timeIntervalIntegral = gainIntegral(timeInterval, time);
                int integral=durationIntegral+timeIntervalIntegral;

                if("主叫".equals(calling_type)||"被叫".equals(calling_type)){
                    //频次-全部
                    if(maps.containsKey(calling)){
                        HashMap<String, Object> mainMap = (HashMap<String, Object>) maps.get(calling);
                        //积分和
                        Integer integralSum = Integer.parseInt(mainMap.get("integral").toString());
                        mainMap.put("integral",integralSum+integral);
                        //频次和
                        Integer all = Integer.parseInt(mainMap.get("all").toString());
                        mainMap.put("all",++all);
                        //时长异常--呼叫时长大于时长阈值时为异常
                        if(Integer.parseInt(Global.getConfig("duration")==null||"".equals(Global.getConfig("duration"))?"600":Global.getConfig("duration"))<Integer.parseInt(calling_time)
                                &&(mainMap.get("exception").toString()).indexOf("1")==-1){
                            mainMap.put("exception",mainMap.get("exception")+",1");
                        }
                        //异常时段频次统计
                        for (String key:timeIntervalException.keySet()) {
                            String[] split = key.split("-");
                            int startTime=Integer.parseInt(split[0]+"0000");
                            int endTime=Integer.parseInt(split[1]+"0000");
                            if(startTime<=timenum&&timenum<=endTime){
                                int i1 = Integer.parseInt(mainMap.get(key).toString());
                                mainMap.put(key,++i1);
                            }
                        }
                        maps.put(calling,mainMap);
                    }else{
                        HashMap<String, Object> mainMap = new HashMap<>();
                        mainMap.put("phone",calling);//手机号
                        mainMap.put("integral",integral);//频次-全部
                        mainMap.put("all",1);//频次-全部
                        mainMap.put("exception","0");//时长没异常
                        if(Integer.parseInt(Global.getConfig("duration")==null||"".equals(Global.getConfig("duration"))?"600":Global.getConfig("duration"))<Integer.parseInt(calling_time)){
                            mainMap.put("exception",mainMap.get("exception")+",1");//时长异常
                        }
                        //异常时段频次统计
                        for (String key:timeIntervalException.keySet()) {
                            String[] split = key.split("-");
                            int startTime=Integer.parseInt(split[0]+"0000");
                            int endTime=Integer.parseInt(split[1]+"0000");
                            if(startTime<=timenum&&timenum<=endTime){
                                mainMap.put(key,1);
                            }else{
                                mainMap.put(key,0);
                            }
                        }
                        mainMap.put("calling",0);//频次-主叫
                        mainMap.put("called",0);//频次-被叫
                        mainMap.put("phoneNumber",phoneNumber);//主机
                        maps.put(calling,mainMap);
                    }
                    //频次-主叫
                    if("主叫".equals(calling_type)){
                        HashMap<String, Object> mainMap = (HashMap<String, Object>) maps.get(calling);
                        Integer ii = Integer.parseInt(mainMap.get("calling").toString());
                        mainMap.put("calling",++ii);
                        maps.put(calling,mainMap);
                    }
                    //频次-被叫
                    if("被叫".equals(calling_type)){
                        HashMap<String, Object> mainMap = (HashMap<String, Object>) maps.get(calling);
                        Integer ii = Integer.parseInt(mainMap.get("called").toString());
                        mainMap.put("called",++ii);
                        maps.put(calling,mainMap);
                    }
                }
//            }
        }
        //转换成list
        for (String key:maps.keySet()) {
            Map<String, Object> map = (Map<String, Object>) maps.get(key);
            //频次异常
            String frequency = Global.getConfig("frequency");
            if(frequency==null||"".equals(frequency)){
                frequency="100";
            }
            if(Integer.parseInt(map.get("all").toString())>=Integer.parseInt(frequency)){
                map.put("exception",map.get("exception")+",2");//频次异常
            }
            //异常时段频次统计
            for (String key1:timeIntervalException.keySet()) {
                //配置中时段异常次数
                int i1 = Integer.parseInt(timeIntervalException.get(key1).toString());
                //实际中时段异常次数
                int i2 = Integer.parseInt(map.get(key1).toString());
                if(i1<=i2){
                    map.put("exception",map.get("exception")+",3");//时段异常
                }
                map.remove(key1);
            }

            lists.add(map);
        }
        //积分排名
        Collections.sort(lists, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o2, Map<String, Object> o1) {
                int all1 = Integer.parseInt(o1.get("integral").toString());
                int all2 = Integer.parseInt(o2.get("integral").toString());
                if((all1-all2)>0){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
        for(int i=0;i<lists.size();i++){
            Map<String, Object> map = lists.get(i);
            map.put("integralSort",++i);
            lists.set(--i,map);
        }
        //取值
        ArrayList<Map<String, Object>> set = new ArrayList<>();
        //TOP数量
//        int topnumber = Integer.parseInt(paramMap.get("Topnumber").toString());
        for(int i=0;i<lists.size();i++){
            Map<String, Object> map = lists.get(i);
            String exception = map.get("exception").toString();
            if(/*i<topnumber||*/exception.indexOf("1")!=-1||exception.indexOf("2")!=-1||exception.indexOf("3")!=-1){
                String phone = map.get("phone").toString();
                map.put("casetag","");
                if(phone.length()>=11){
                    phone=phone.substring(0,7);
                    String sql ="SELECT a.PROVINCE,a.CITY,a.OPERATE,a.AREACODE FROM dic_to_tel a WHERE a.PREFIXCODE='"+phone+"'";
                    List<Map<String, Object>> tab = JdbcHandler.selectTab(sql);
                    map.put("city",(tab.size()==0?"未知":tab.get(0).get("CITY")));
                    map.put("operate",(tab.size()==0?"未知":tab.get(0).get("OPERATE")));
                    map.put("areacode",(tab.size()==0?"未知":tab.get(0).get("AREACODE")));
                    map.put("exceptionValue",Global.getConfig("duration")+";"+Global.getConfig("frequency")+";"+Global.getConfig("timeInterval"));
                }else{
                    map.put("city",("未知"));
                    map.put("operate",("未知"));
                    map.put("areacode",("未知"));
                }
                set.add(map);
            }
        }

        return set;
    }

    /**
     * 通联人列表
     * @param httpSession
     * @param request
     * @param search
     * @param start
     * @param length
     * @return
     */
    @RequestMapping(value="/contactPersonList",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public DatatablesResponse contactPersonList(HttpSession httpSession, HttpServletRequest request, @RequestParam(value = "search[value]") String search, @RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length){
        List list=new ArrayList();
        long size = contactPersonList.size();
        if(size>0){
            if((start + length)>size){
                list=contactPersonList.subList(start,(int)size);
            }else{
                list=contactPersonList.subList(start,(start + length));
            }
        }
        DataSet dataSet = new DataSet(list,size,size);
        DatatablesResponse resp = DatatablesResponse.build(dataSet);
        return resp;
    }

    @RequestMapping(value="/clean",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject clean(HttpSession httpSession, HttpServletRequest request){
        contactPersonList=new ArrayList();
        contactList=new ArrayList();
        JSONObject result =new JSONObject();
        result.put("msg","清除成功！");
        return result;
    }

    public static Map<String,String> toMap(String param){
        Map<String ,String> map = new HashMap<>();
        String[] split = param.split(",");
        for(int i=0;i<split.length;i++){
            String[] split1 = split[i].split("_");
            String s = split1[0];
            s =s.replace("(","");
            s =s.replace("]","");
            map.put(s,split1[1]);
        }
        return map;
    }


    /**
     * 获取对应积分
     * @param duration 配置 (0-60)_1,(60-120)_2,(120-max)_3
     * @param param 参数
     * @return
     */
    public static int gainIntegral(String duration,String param){
        int result = 0;
        int parameter = Integer.parseInt(param);
        if("".equals(duration)){
            return result;
        }
        String[] parts = duration.split(",");
        for(int i=0;i<parts.length;i++){
            int i1 = parts[i].indexOf("(");
            int i2 = parts[i].indexOf("-");
            int i3 = parts[i].indexOf("]");
            int i4 = parts[i].indexOf("_");
            if(i1==-1){System.out.println("配置文件出错!");break;}
            if(i2==-1){System.out.println("配置文件出错!");break;}
            if(i3==-1){System.out.println("配置文件出错!");break;}
            if(i4==-1){System.out.println("配置文件出错!");break;}
            int min = Integer.parseInt(parts[i].substring(i1+1, i2));
            String maxStr = parts[i].substring(i2 + 1, i3);
            if(maxStr.indexOf("max")!=-1){
                if(min<=parameter){
                    result=Integer.parseInt(parts[i].substring(i4+1));
                    break;
                }
            }else{
                int max = Integer.parseInt(maxStr);
                if(min<=parameter&&parameter<max){
                    result=Integer.parseInt(parts[i].substring(i4+1));
                    break;
                }
            }
        }
        return result;
    }


    /**
     * 对照详单
     * @param httpSession
     * @param request
     * @param paramMap
     * @return
     */
    @RequestMapping(value="/contrast",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public Map contrast(HttpSession httpSession, HttpServletRequest request,@RequestParam HashMap<String,Object> paramMap){
        JSONObject result =new JSONObject();
        String phoneA = paramMap.get("phoneA").toString();
        String phoneB = paramMap.get("phoneB").toString();
        //查询手机号
        result.put("phoneA",phoneA);
        //查询条件
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        String[] phones = phoneB.split(",");
        //开始时间
        RangeQueryBuilder time = new RangeQueryBuilder("START_TIME").from(paramMap.get("startTime").toString() + " 00:00:00").to(paramMap.get("finishTime").toString() + " 23:59:59");
        for(int i=0;i<phones.length;i++){
            BoolQueryBuilder boolQueryBuilder1 = QueryBuilders.boolQuery();
            boolQueryBuilder1.must(QueryBuilders.matchQuery("CALLING", phones[i]));
            boolQueryBuilder1.must(QueryBuilders.matchQuery("CALLED", phoneA));
            boolQueryBuilder1.must(time);
            BoolQueryBuilder boolQueryBuilder2 = QueryBuilders.boolQuery();
            boolQueryBuilder2.must(QueryBuilders.matchQuery("CALLING", phoneA));
            boolQueryBuilder2.must(QueryBuilders.matchQuery("CALLED", phones[i]));
            boolQueryBuilder2.must(time);
            query.should(boolQueryBuilder1);
            query.should(boolQueryBuilder2);
        }
        //话单集合
        List<HashMap<String, Object>> list = ElasticSearchUtils.getSearchList("cgs_call_record", "cgs", query);
        if(list==null||list.size()==0){
            result.put("statistics",null);
        }
        HashMap<String, Object> maps = new HashMap<>();
        int size = list.size();
        for(int i=0;i<size;i++){
            //每条话单数据
            HashMap<String, Object> map = list.get(i);
            //主叫
            String calling = map.get("CALLING").toString();
            //被叫
            String called = map.get("CALLED").toString();
            if(calling.equals(phoneA)){
                calling=called;
            }
            //频次-全部
            if(maps.containsKey(calling)){
                ArrayList<Map<String, Object>> lists = (ArrayList<Map<String, Object>>) maps.get(calling);
                lists.add(map);
                maps.put(calling,lists);
            }else{
                ArrayList<Map<String, Object>> lists =new ArrayList<>();
                lists.add(map);
                maps.put(calling,lists);
            }
        }

        List<Map<String, Object>> listMap = new LinkedList(maps.entrySet());
        Collections.sort(listMap, new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                Map.Entry<String, Object> o21 = (Map.Entry<String, Object>) (o2);
                ArrayList<Map<String, Object>> list2= (ArrayList<Map<String, Object>>) maps.get(o21.getKey());
                int size2 = list2.size();
                Map.Entry<String, Object> o11 = (Map.Entry<String, Object>) (o1);
                ArrayList<Map<String, Object>> list1= (ArrayList<Map<String, Object>>) maps.get(o11.getKey());
                int size1 = list1.size();
                if((size2-size1)>0){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
        Map sortedMap = new LinkedHashMap();

        for (Iterator it = listMap.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry)it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        //时间倒序排序
        for (Object key:sortedMap.keySet()) {
            ArrayList<Map<String, Object>> lists= (ArrayList<Map<String, Object>>) sortedMap.get(key);
            //时长排名
            Collections.sort(lists, new Comparator<Map<String, Object>>() {
                public int compare(Map<String, Object> o2, Map<String, Object> o1) {
                    String start_time1 = o1.get("START_TIME").toString();
                    start_time1=start_time1.replace("-","");
                    start_time1=start_time1.replace(":","");
                    start_time1=start_time1.replace(" ","");
                    String start_time2 = o2.get("START_TIME").toString();
                    start_time2=start_time2.replace("-","");
                    start_time2=start_time2.replace(":","");
                    start_time2=start_time2.replace(" ","");
                    Long o11=Long.parseLong(start_time1);
                    Long o22=Long.parseLong(start_time2);
                    if((o11-o22)>0){
                        return 1;
                    }else{
                        return -1;
                    }
                }
            });
        }
        result.put("statistics",sortedMap);
        return sortedMap;
    }

    /**
     * @Author: sky
     * @Description: 保存模块分析结果
     * @Date: 上午11:50 2018/4/23
     * @param:
     */
    @RequestMapping(value="/saveAnalyse",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultData saveAnalyse(HttpServletRequest request, HttpServletResponse response, EtAnalyse etAnalyse){
        //创建返回结果实体类
        ResultData resultData = new ResultData();

        SysUser sysUser = (SysUser) request.getAttribute("user");
        if (StringUtils.isBlank(etAnalyse.getId())){
            //新增操作
            etAnalyse.setUserId(sysUser.getId());//添加userID
            etAnalyse.setCreateUser(sysUser.getId());//添加创建人
            etAnalyse.setUpdateUser(sysUser.getId());//更新更新人
        }else {
            //更新操作
            etAnalyse.setUpdateUser(sysUser.getId());//更新更新人
        }

        String etAnalyseStr = GsonUtils.getGson().toJson(etAnalyse);
        JSONObject etAnalyseObj = JSONObject.fromObject(etAnalyseStr);
        try {
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "analyse/saveAnalyse", etAnalyseObj);
            JSONObject object=JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) object.get("data");
            resultData = (ResultData)JSONObject.toBean(respContent, ResultData.class);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return resultData;
    }


    /**
     * @Author: sky
     * @Description:根据id删除历史保存数据
     * @Date: 下午4:11 2018/4/23
     * @param: request
    response
    id
     */
    @RequestMapping(value="/deleteAnalyse",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultData deleteAnalyse(HttpServletRequest request, HttpServletResponse response, String id){

        //创建返回结果实体类
        ResultData resultData = new ResultData();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        try {
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "analyse/deleteAnalyse", jsonObject);
            JSONObject object=JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) object.get("data");
            resultData = (ResultData)JSONObject.toBean(respContent, ResultData.class);
            System.out.println(jsonStr);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return resultData;
    }


    /**
     * @Author: sky
     * @Description: 根据模块场景、用户id获取列表
     * @Date: 下午4:11 2018/4/23
     * @param: request
    response
    analyseType
     */
    @RequestMapping(value="/analyseList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DatatablesResponse<EtAnalyse> analyseList(HttpServletRequest request, HttpServletResponse response, String analyseType){

        SysUser sysUser = (SysUser) request.getAttribute("user");
        List<EtAnalyse> etAnalyses = new ArrayList<>();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",sysUser.getId());
        jsonObject.put("analyseType",analyseType);
        //如果有数据，根据身份证号查询涉案或者涉警的人员列表
        try {
            String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "analyse/analyseList", jsonObject);
            JSONObject object = JSONObject.fromObject(jsonStr);
            JSONArray jsonArray = (JSONArray) object.get("data");

            String[] dateFormats = new String[] {"yyyy-MM-dd HH:mm:ss"};
            JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));

            //将jsonarray转化为list
            etAnalyses = (List<EtAnalyse>) JSONArray.toCollection(jsonArray, EtAnalyse.class);// 过时方法
            System.out.println(etAnalyses.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataSet<EtAnalyse> dataSet = new DataSet<EtAnalyse>(etAnalyses, (long) etAnalyses.size(), (long) etAnalyses.size());
        DatatablesResponse<EtAnalyse> resp = DatatablesResponse.build(dataSet);

        return resp;
    }


    /**
     * @Author: sky
     * @Description: 根据id获取分析结果
     * @Date: 下午4:36 2018/4/23
     * @param: request
    response
    id
     */
    @RequestMapping(value="/findAnalyseById",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public EtAnalyse findAnalyseById(HttpServletRequest request, HttpServletResponse response, String id){
        EtAnalyse etAnalyse = new EtAnalyse();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        try {
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "analyse/findAnalyseById", jsonObject);
            JSONObject object=JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) object.get("data");
            etAnalyse = (EtAnalyse)JSONObject.toBean(respContent, EtAnalyse.class);
            System.out.println(jsonStr);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return etAnalyse;

    }


}
