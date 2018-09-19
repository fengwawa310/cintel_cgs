package com.controller.caseInfo;

import com.common.consts.Const;
import com.common.services.api.APIClientRequest;
import com.common.utils.DataSet;
import com.common.utils.DatatablesResponse;
import com.common.utils.GsonUtils;
import com.common.utils.IDGenerator;
import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.controller.sys.SystemLog;
import com.entity.caseInfo.CaseSeries;
import com.entity.caseInfo.EtCase;
import com.entity.sys.SysUser;
import com.util.SysUserHelp;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weipc on 2018/1/3.
 */
@Controller
@RequestMapping("/case")
public class CaseHandler {

    /**
     * 案件添加
     */
    @RequestMapping(value="/save",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    @SystemLog(module = "案警管理",methods = "案件添加")
    public JSONObject save(HttpSession httpSession, HttpServletRequest request,String caseinfo,String apStaffs){
        SysUser user = (SysUser) request.getAttribute("user");
        EtCase etCase = GsonUtils.toBean(caseinfo, EtCase.class);
        
        //主键
        etCase.setId(IDGenerator.getorderNo());
        //数据来源2102:手动录入
        etCase.setSourceType(2102);
        //录入人姓名
        etCase.setInputerName(user.getName());
        //录入人编码
        etCase.setInputerCode(user.getId());
        //录入单位编码
        etCase.setInputUnitCode((user.getLevel()==null||"".equals(user.getLevel())?"":"1".equals(user.getLevel())?user.getProvince():"2".equals(user.getLevel())?user.getCity():"3".equals(user.getLevel())?user.getArea():user.getPoliceStation()));
        //录入单位名称
        etCase.setInputUnitName((user.getLevel()==null||"".equals(user.getLevel())?"":"1".equals(user.getLevel())?user.getProvinceName():"2".equals(user.getLevel())?user.getCityName():"3".equals(user.getLevel())?user.getAreaName():user.getPoliceStationName()));
        //审批标识 1已审批
        etCase.setIsApproval(1);
        
        //临时添加 userId 预警使用user
        etCase.setUserId(user.getId());
        
        
        String etCaseStr = GsonUtils.getGson().toJson(etCase);
        JSONObject etCaseObj = JSONObject.fromObject(etCaseStr);
        etCaseObj.put("apStaffs",apStaffs);
        String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_CASE, "case/save", etCaseObj);
        System.out.println(jsonStr);
        //json字符串转换为json对象
        return JSONObject.fromObject(jsonStr);
    }
    /**
     * 案件删除到回收站/恢复回收站中的案件
     */
    @RequestMapping(value="/setIsAbandon",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    @SystemLog(module = "案警管理",methods = "案件删除到回收站/恢复回收站中的案件")
    public JSONObject setIsAbandon(HttpSession httpSession, HttpServletRequest request,EtCase etCase){
        String etCaseStr = GsonUtils.getGson().toJson(etCase);
        JSONObject jsonObject = JSONObject.fromObject(etCaseStr);
        String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_CASE, "case/setIsAbandon", jsonObject);
        System.out.println(jsonStr);
        //json字符串转换为json对象
        return JSONObject.fromObject(jsonStr);
    }
    /**
     * 彻底删除案件
     */
    @RequestMapping(value="/delete",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    @SystemLog(module = "案警管理",methods = "彻底删除案件")
    public JSONObject delete(HttpSession httpSession, HttpServletRequest request, String id){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_CASE, "case/delete", jsonObject);
        System.out.println(jsonStr);
        //json字符串转换为json对象
        return JSONObject.fromObject(jsonStr);
    }
    /**
     * 案件关注和取消关注
     */
    @RequestMapping(value="/setIsFollow",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    @SystemLog(module = "案警管理",methods = "案件关注和取消关注")
    public JSONObject setIsFollow(HttpSession httpSession, HttpServletRequest request, EtCase etCase){
        String etCaseStr = GsonUtils.getGson().toJson(etCase);
        JSONObject jsonObject = JSONObject.fromObject(etCaseStr);
        String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_CASE, "case/setIsFollow", jsonObject);
        System.out.println(jsonStr);
        //json字符串转换为json对象
        return JSONObject.fromObject(jsonStr);
    }
    /**
     * 案件加入历史库
     */
    @RequestMapping(value="/setIsArchive",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    @SystemLog(module = "案警管理",methods = "案件加入历史库")
    public JSONObject setIsArchive(HttpSession httpSession, HttpServletRequest request, EtCase etCase){
        String etCaseStr = GsonUtils.getGson().toJson(etCase);
        JSONObject jsonObject = JSONObject.fromObject(etCaseStr);
        String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_CASE, "case/setIsArchive", jsonObject);
        System.out.println(jsonStr);
        //json字符串转换为json对象
        return JSONObject.fromObject(jsonStr);
    }

    /**
     * 案件编辑
     */
    @RequestMapping(value="/update",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    @SystemLog(module = "案件管理",methods = "案件编辑")
    public JSONObject update(HttpSession httpSession, HttpServletRequest request,String caseinfo,String apStaffs,String apStaffIds){
        SysUser user = (SysUser) request.getAttribute("user");
        JSONObject etCaseObj= new JSONObject();
        etCaseObj.put("caseinfo",caseinfo);
        etCaseObj.put("apStaffs",apStaffs);
        etCaseObj.put("apStaffIds",apStaffIds);
        etCaseObj.put("userId",user.getId());
        String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_CASE, "case/update", etCaseObj);
        System.out.println(jsonStr);
        //json字符串转换为json对象
        return JSONObject.fromObject(jsonStr);
    }

    /**
     * 查询单个案件信息
     * @return
     */
    @RequestMapping(value="/findCaseById",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    @SystemLog(module = "案件管理",methods = "案件详情")
    public Map<String,Object> findCaseById(HttpSession httpSession, HttpServletRequest request, String id,String caseNo){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("caseNo",caseNo);
        String jsonStr="";
        try {
            jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_CASE, "case/findCaseById", jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("data",jsonStr);
        return map;
    }
    /**
     * 查询所有案件信息
     * @return
     */
    @RequestMapping(value="/findCase",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    @SystemLog(module = "案警管理",methods = "查询案件列表")
    public DatatablesResponse findCase(HttpSession httpSession, HttpServletRequest request, @RequestParam(value = "search[value]") String search, @RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length,EtCase etCas){
        SysUser user = (SysUser) request.getAttribute("user");
        String parameter = request.getParameter("relation");
        //分页条件
        PageVO pageVO = new PageVO(start, length);
        String pageVOStr = GsonUtils.getGson().toJson(pageVO);
        JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
        if(etCas.getInputUnitCode()==null||"".equals(etCas.getInputUnitCode())){
        	/*
        	 * 同级单位间不共享；
        	 * 数据对上级单位默认授权；
        	 */
            etCas.setDeceSigns(SysUserHelp.getUserDeceSigns(user, Const.DECE_SIGNS_TYPE_UNIT));
        }else{
//        	pageVOObj.put("inputUnitCode",(user.getLevel()==null||"".equals(user.getLevel())?"":"1".equals(user.getLevel())?user.getProvince():"2".equals(user.getLevel())?user.getCity():"3".equals(user.getLevel())?user.getArea():user.getPoliceStation()));
        }
        
        String etCaseStr = GsonUtils.getGson().toJson(etCas);
        JSONObject etCaseObj = JSONObject.fromObject(etCaseStr);
        pageVOObj.putAll(etCaseObj);
        pageVOObj.put("search",search);

        PageHelpVO pageHelpVO =null;
        try {
            String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_CASE, "case/findCase", pageVOObj);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) jsonObject.get("data");
            pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataSet dataSet = new DataSet(pageHelpVO.getList(),pageHelpVO.getTotal(),pageHelpVO.getTotal());
        DatatablesResponse<EtCase> resp = DatatablesResponse.build(dataSet);
        return resp;
    }

    /**
     * 查询所有案件信息
     * @return
     */
    @RequestMapping(value="/findCaseInStatistic",produces = "application/json; charset=utf-8")
    @ResponseBody
    public DatatablesResponse findCaseInStatistic(HttpSession httpSession, HttpServletRequest request, @RequestParam(value = "search[value]") String search, @RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length,EtCase etCas){
       //模糊查询条件
//        EtCase etCase = GsonUtils.toBean(search, EtCase.class);
//        if(etCase==null){
//            DataSet dataSet = new DataSet(new ArrayList(),0L,0L);
//            DatatablesResponse<EtCase> resp = DatatablesResponse.build(dataSet);
//            return resp;
//        }
        //分页条件
        PageVO pageVO = new PageVO(start, length);
        String pageVOStr = GsonUtils.getGson().toJson(pageVO);
        JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
////        if(etCase==null){
////            etCase= new EtCase();
////            //审批标识
////            etCase.setIsApproval(etCas.getIsApproval());
////            //删除标识
////            etCase.setIsAbandon(etCas.getIsAbandon());
////            //归档标识
////            etCase.setIsArchive(etCas.getIsArchive());
////            //关注标识
////            etCase.setIsFollow(etCas.getIsFollow());
////        }
////        //当前台传来案件编号集合时
////        if(etCas.getCaseNos()!=null&&!"".equals(etCas.getCaseNos())){
////            etCase.setCaseNos(etCas.getCaseNos());
////        }
////        String etCaseStr = GsonUtils.getGson().toJson(etCase);
//        JSONObject etCaseObj = JSONObject.fromObject(etCaseStr);
        pageVOObj.put("search",search);
//        pageVOObj.putAll(etCaseObj);

        PageHelpVO pageHelpVO =null;
        try {
            String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_CASE, "case/findCase", pageVOObj);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) jsonObject.get("data");
            pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataSet dataSet = new DataSet(pageHelpVO.getList(),pageHelpVO.getTotal(),pageHelpVO.getTotal());
        DatatablesResponse<EtCase> resp = DatatablesResponse.build(dataSet);
        return resp;
    }
    /**
     * 案件串并
     * @return
     */
    @RequestMapping(value="/caseSeries",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    @SystemLog(module = "案警管理",methods = "案件串并")
    public DatatablesResponse caseSeries(HttpSession httpSession, HttpServletRequest request, @RequestParam(value = "search[value]") String search, @RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length,EtCase etCas){
       //模糊查询条件
        CaseSeries caseSeries = GsonUtils.toBean(search, CaseSeries.class);
        if(caseSeries==null||caseSeries.getOneLevel()==null||"".equals(caseSeries.getOneLevel())){
            DataSet dataSet = new DataSet(new ArrayList(),0L,0L);
            DatatablesResponse<EtCase> resp = DatatablesResponse.build(dataSet);
            return resp;
        }
        //分页条件
        PageVO pageVO = new PageVO(start, length);
        String pageVOStr = GsonUtils.getGson().toJson(pageVO);
        JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);

        String caseSeriesStr = GsonUtils.getGson().toJson(caseSeries);
        JSONObject caseSeriesObj = JSONObject.fromObject(caseSeriesStr);
        pageVOObj.putAll(caseSeriesObj);
        PageHelpVO pageHelpVO =null;
        try {
            String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_CASE, "case/caseSeries", pageVOObj);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) jsonObject.get("data");
            pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataSet dataSet = new DataSet(pageHelpVO.getList(),pageHelpVO.getTotal(),pageHelpVO.getTotal());
        DatatablesResponse<EtCase> resp = DatatablesResponse.build(dataSet);
        return resp;
    }


    /**
     * @Author: sky
     * @Description:获取案件数量  在研案件数
     * @Date: 10:01 2018/5/24
     * @param: httpSession
    request
     */
    @RequestMapping(value="/getCaseNum",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public Map<String,Object> getCaseNum(HttpSession httpSession, HttpServletRequest request){
        JSONObject object = new JSONObject();
        Map<String,Object> resultMap = new HashMap<>();
        try {
            String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_CASE, "case/getCaseNum", object);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) jsonObject.get("data");
            resultMap.put("caseNum",respContent.get("caseNum"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
