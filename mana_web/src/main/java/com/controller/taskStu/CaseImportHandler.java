package com.controller.taskStu;

import com.common.utils.*;
import com.entity.caseInfo.EtCase;
import com.entity.sys.SysUser;
import com.entity.taskStu.EpCaseGang;
import com.service.taskStu.CaseImportService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 导入团伙和个人案件
 * Created by weipc on 2018/3/15.
 */
@Controller
@RequestMapping("/importCase")
public class CaseImportHandler {

    @Resource
    private CaseImportService caseImportService;
    @RequestMapping(value = "/importCase", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject importExcel(HttpServletResponse response, HttpServletRequest request, MultipartFile file,String type) throws IOException {
        SysUser user = (SysUser) request.getAttribute("user");
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
        String subfix = filename.lastIndexOf(".") == -1 ? "" : filename.substring(filename.lastIndexOf(".") + 1);
        List result;
        if (subfix.equals("xls")){
            result = ExceUtil.readExcel2003(file,9);
        } else if (subfix.equals("xlsx")){
            result = ExceUtil.readExcel2007(file,9);
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
        List<EpCaseGang> paramList = new ArrayList<>();
        StringBuffer msg= new StringBuffer();
        for (int i = 0; i < result.size(); i ++) {
            if(i==0){
                continue;
            }
            List<String> list= (List) result.get(i);
            String id = IDGenerator.getorderNo();
            EpCaseGang ecg = new EpCaseGang();
            ecg.setId(id);
            ecg.setPerpetratorClass(Integer.parseInt(type));
            ecg.setCaseTitle(list.get(1));
            ecg.setCaseNo(list.get(2));
            ecg.setCaseDesc(list.get(3));
            ecg.setCaseSuspect(list.get(4));
            ecg.setUntreatedPersonnel(list.get(5));
            ecg.setWitness(list.get(6));
            ecg.setExistingProblems(list.get(7));
            ecg.setRemark(list.get(8));
            //录入人姓名
            ecg.setInputerName(user.getName());
            //录入人编码
            ecg.setInputerCode(user.getId());
            //录入单位编码
            ecg.setInputUnitCode((user.getLevel()==null||"".equals(user.getLevel())?"":"1".equals(user.getLevel())?user.getProvince():"2".equals(user.getLevel())?user.getCity():"3".equals(user.getLevel())?user.getArea():user.getPoliceStation()));
            //录入单位名称
            ecg.setInputUnitName((user.getLevel()==null||"".equals(user.getLevel())?"":"1".equals(user.getLevel())?user.getProvinceName():"2".equals(user.getLevel())?user.getCityName():"3".equals(user.getLevel())?user.getAreaName():user.getPoliceStationName()));
            paramList.add(ecg);
        }
        try {
            caseImportService.importCase(paramList);
            map1.put("flag",true);
            map1.put("msg","导入成功");
            map.put("data",map1);
        } catch (Exception e) {
            e.printStackTrace();
            map1.put("flag",false);
            map1.put("msg","导入失败");
            map.put("data",map1);
        }
        return map;
    }

    /**
     * 查询所有案件信息
     * @return
     */
    @RequestMapping(value="/findImportCase",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public DatatablesResponse findImportCase(HttpSession httpSession, HttpServletRequest request, @RequestParam(value = "search[value]") String search, @RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length,String name,String byname,String idcardNum,String mobilephone){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("byname",byname);
        map.put("idcardNum",idcardNum);
        map.put("mobilephone",mobilephone);
        //分页条件
        PageVO pageVO = new PageVO(start, length);
        PageHelpVO pageHelpVO = caseImportService.findImportCase(pageVO,map);
        DataSet dataSet = new DataSet(pageHelpVO.getList(),pageHelpVO.getTotal(),pageHelpVO.getTotal());
        DatatablesResponse<EpCaseGang> resp = DatatablesResponse.build(dataSet);
        return resp;
    }


    /*@RequestMapping(value = "/importCase", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject importExcel1(HttpServletResponse response, MultipartFile file,String type) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
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
        String subfix = filename.lastIndexOf(".") == -1 ? "" : filename.substring(filename.lastIndexOf(".") + 1);
        List result;
        if (subfix.equals("xls")){
            result = ExceUtil.readExcel2003(file,9);
        } else if (subfix.equals("xlsx")){
            result = ExceUtil.readExcel2007(file,9);
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
        //sql
        String sql= "INSERT  INTO  ep_case_gang " +
                "(ID, PERPETRATOR_CLASS, CASE_TITLE, CASE_NO, CASE_DESC, CASE_SUSPECT, UNTREATED_PERSONNEL," +
                " WITNESS, EXISTING_PROBLEMS, REMARK, CREAT_TIME, MODIFY_TIME) VALUES ";
        StringBuffer msg= new StringBuffer();
        for (int i = 0; i < result.size(); i ++) {
            List<String> list= (List) result.get(i);
            String id = IDGenerator.getorderNo();
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("ID",id);
            paramMap.put("PERPETRATOR_CLASS",Integer.parseInt(type));
            paramMap.put("CASE_TITLE",list.get(1));
            paramMap.put("CASE_NO",list.get(2));
            paramMap.put("CASE_DESC",list.get(3));
            paramMap.put("CASE_SUSPECT",list.get(4));
            paramMap.put("UNTREATED_PERSONNEL",list.get(5));
            paramMap.put("WITNESS",list.get(6));
            paramMap.put("EXISTING_PROBLEMS",list.get(7));
            paramMap.put("REMARK",list.get(8));
            paramMap.put("CREAT_TIME",date);
            paramMap.put("MODIFY_TIME",date);
            ElasticSearchUtils.insert("ep_case_gang","cgs",paramMap,id);
            String sql1=" ( '"+id+"'," +
                        "      "+Integer.parseInt(type)+",\n" +
                        "      '"+list.get(1)+"',\n" +
                        "      '"+list.get(2)+"',\n" +
                        "      '"+list.get(3)+"',\n" +
                        "      '"+list.get(4)+"',\n" +
                        "      '"+list.get(5)+"',\n" +
                        "      '"+list.get(6)+"',\n" +
                        "      '"+list.get(7)+"',\n" +
                        "      '"+list.get(8)+"',\n" +
                        "      '"+date+"',\n" +
                        "      '"+date+"'\n" +
                        "       )";
            JSONObject insert = JdbcHandler.insert(sql+sql1);
            if(!Boolean.parseBoolean(insert.get("flag")+"")){
                msg.append("Excel中第"+(i+1)+"行导入失败，"+insert.get("msg"));
            }
        }
        map1.put("flag",true);
        map1.put("msg","".equals(msg.toString())?"导入成功":msg.toString());
        map.put("data",map1);
        return map;
    }*/

    /**
     *       案件搜索
     *      /elastic/searchCase?page=1&pagesize=10
     */
    @RequestMapping("/searchCase")
    public @ResponseBody
    DatatablesResponse getSearch(@RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length, String param){
        if (StringUtils.isBlank(param)){
            DataSet<Map<String,Object>> dataSet = new DataSet<>(new ArrayList<Map<String,Object>>(), 0L, 0L);
            DatatablesResponse<Map<String,Object>> resp = DatatablesResponse.build(dataSet);
            return resp;
        }
        PageVO pageVO= new PageVO(start,length);
        SearchResponse response = ElasticSearchUtils.getSearch(param,pageVO.getStart(), pageVO.getLength(),"ep_case_gang","cgs",
                "CASE_DESC","CASE_SUSPECT","UNTREATED_PERSONNEL");
        SearchHits hits = response.getHits();
        int total=(int) hits.getTotalHits();//总记录数
        int totalPage = total/length;//总页数
        if(total % length != 0){
            totalPage += 1;
        }
        List<Map<String,Object>> objects = new ArrayList<>();
        List<EtCase> etCases = new ArrayList<>();
        for (SearchHit searchHit : hits) {
            objects.add(searchHit.getSourceAsMap());
        }
        DataSet<Map<String,Object>> dataSet = new DataSet<>(objects, (long) total, (long) total);
        DatatablesResponse<Map<String,Object>> resp = DatatablesResponse.build(dataSet);
        return resp;
    }







}
