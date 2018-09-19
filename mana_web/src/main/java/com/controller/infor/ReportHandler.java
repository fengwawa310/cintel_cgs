package com.controller.infor;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.consts.Const;
import com.common.services.api.APIClientRequest;
import com.common.utils.DataSet;
import com.common.utils.DatatablesResponse;
import com.common.utils.GsonUtils;
import com.common.utils.IDGenerator;
import com.common.utils.PageHelpVO;
import com.entity.infor.EtTipoff;
import com.entity.infor.EtTipoffMemo;
import com.entity.infor.RequestVO;
import com.entity.infor.ResponseVO;
import com.entity.sys.SysUser;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

/**
 * 举报
 * Created by weipc on 2018/2/28.
 */
@RequestMapping("/reportHandler")
@Controller
public class ReportHandler {
    // 日志
    private static final Logger logger = LoggerFactory.getLogger(ReportHandler.class);

    /**
     * 举报添加
     */
    @RequestMapping(value="/insert",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject insert(HttpSession httpSession, HttpServletRequest request,String etTipoff){
        SysUser sysUser = (SysUser) request.getAttribute("user");
        EtTipoff etT = GsonUtils.toBean(etTipoff, EtTipoff.class);
        etT.setSysuser(JSONSerializer.toJSON(sysUser).toString());
        //主键
        etT.setTipoffId(IDGenerator.getorderNo());
        etT.setInputerId(sysUser.getId());
        String etTipoffStr = GsonUtils.getGson().toJson(etT);
        JSONObject jsonObject = JSONObject.fromObject(etTipoffStr);
        String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "report/insert", jsonObject);
        logger.info(jsonStr);
        //json字符串转换为json对象
        return JSONObject.fromObject(jsonStr);
    }

    /**
     * 查询单个举报信息
     * @return
     */
    @RequestMapping(value="/findReportById",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public Map<String,Object> findReportById(HttpSession httpSession, HttpServletRequest request,String tipoffId){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipoffId",tipoffId);
        String jsonStr="";
        try {
            jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "report/findReportById", jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("data",jsonStr);
        return map;
    }

    /**
     * 举报编辑
     */
    @RequestMapping(value="/update",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public JSONObject update(HttpSession httpSession, HttpServletRequest request,String etTipoff){
        SysUser user = (SysUser) request.getAttribute("user");
        EtTipoff etT = GsonUtils.toBean(etTipoff, EtTipoff.class);
        String etTipoffStr = GsonUtils.getGson().toJson(etT);
        JSONObject jsonObject = JSONObject.fromObject(etTipoffStr);
        String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "report/update", jsonObject);
        logger.info(jsonStr);
        //json字符串转换为json对象
        return JSONObject.fromObject(jsonStr);
    }
    
    /**
     * 举报列表
     *  /reportHandler/etTipoffList
     */
    @RequestMapping(value="/etTipoffList",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public DatatablesResponse<EtTipoff> etTipoffList(HttpServletRequest request,HttpServletResponse reponse,
			Integer start, Integer length){
    	 SysUser user = (SysUser) request.getAttribute("user");
    	 Map<String, Object> requestParam= new HashMap<>();
    	 requestParam.put("userId", user.getId());
//    	 requestParam.put("level", user.getLevel());
//   	 requestParam.put("code", "1".equals(user.getLevel())?"":"2".equals(user.getLevel())?user.getCity():"3".equals(user.getLevel())?user.getArea():user.getPoliceStation());
    	 requestParam.put("start", start);
    	 requestParam.put("length", length);
    	String belongPage = request.getParameter("belongPage");
    	if("addPage".equals(belongPage)){//新增页查的是自己录入的数据
    		requestParam.put("inputerId",user.getId());
    	}else if("signPage".equals(belongPage)){//签收下发页面查的是本部门的数据
    		requestParam.put("code", "1".equals(user.getLevel())?"":"2".equals(user.getLevel())?user.getCity():"3".equals(user.getLevel())?user.getArea():user.getPoliceStation());
    	}

        requestParam.put("startTime",request.getParameter("startTime"));
        requestParam.put("endTime",request.getParameter("endTime"));
        requestParam.put("caseClass",request.getParameter("caseClass"));
        requestParam.put("tipoffReporter",request.getParameter("tipoffReporter"));
        requestParam.put("beingReported",request.getParameter("beingReported"));
        requestParam.put("clueTitle",request.getParameter("clueTitle"));
        requestParam.put("operateType",request.getParameter("operateType"));

         String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "report/etTipoffList", requestParam);
         String countStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "report/etTipoffListCount", requestParam);
         int total = 0;
// 		JSONObject json = JSONObject.fromObject(jsonStr);
// 		JSONArray jsonArray = JSONArray.fromObject(json.getString("data"));
 		JSONObject countJson = JSONObject.fromObject(countStr);
 		String countJsonStr = countJson.getString("data");
 		JSONArray totalJson = JSONArray.fromObject(countJsonStr);
 		total = totalJson.getInt(0);
 		@SuppressWarnings("unchecked")
        PageHelpVO pageHelpVO =null;
        JSONObject jsonData = JSONObject.fromObject(jsonStr);
        JSONObject respContent = (JSONObject) jsonData.get("data");
        pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
// 		List<EtTipoff> etTipoffList = JSONArray.toList(jsonArray, new EtTipoff(), new JsonConfig());
 		DataSet<EtTipoff> dataSet = new DataSet<EtTipoff>(pageHelpVO.getList(), (long) total, (long) total);
 		DatatablesResponse<EtTipoff> resp = DatatablesResponse.build(dataSet);
 		return resp;
    }
    
    
    /**
     * 举报功能（按钮功能）
     */
    @RequestMapping(value="/etTipoffUpdate",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public String etTipoffUpdate(HttpServletRequest request,HttpServletResponse reponse){
    	 SysUser user = (SysUser) request.getAttribute("user");
    	 //操作标志：1:新增举报 2:审核举报 3:下发举报 4:签收举报 5:研判举报 6:查结反馈 
    	 String operationSign=request.getParameter("operationSign");
    	 //备注信息
    	 String remark=request.getParameter("remark");
    	 //下发状态会有 需要下发的     	案件区域（单位机构ID）
    	 String caseUnit=request.getParameter("caseUnit");
    	 //更新用的id
    	 String tipoffId=request.getParameter("tipoffId");
    	 Map<String, Object> requestParam= new HashMap<>();
    	 requestParam.put("operationSign", operationSign);
    	 requestParam.put("remark", remark);
    	 requestParam.put("caseUnit", caseUnit);
    	 requestParam.put("tipoffId", tipoffId);
    	 requestParam.put("userId", user.getId());
         String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "report/etTipoffUpdate", requestParam);
         logger.info(jsonStr);
    	return jsonStr;
    }
    
    
    
    
    /**
     * 详情列表
     */
    @RequestMapping(value="/findetTipoffMemoById",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public DatatablesResponse<EtTipoffMemo> findetTipoffMemoById(HttpServletRequest request,HttpServletResponse reponse,
			Integer start, Integer length,String tipoffId){
    	 SysUser user = (SysUser) request.getAttribute("user");
    	 Map<String, Object> requestParam= new HashMap<>();
    	 requestParam.put("userId", user.getId());
    	 requestParam.put("code", "1".equals(user.getLevel())?"":"2".equals(user.getLevel())?user.getCity():"3".equals(user.getLevel())?user.getArea():user.getPoliceStation());
    	 requestParam.put("start", start);
    	 requestParam.put("length", length);
    	 requestParam.put("tipoffId", tipoffId);
         String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "report/findetTipoffMemoByIdList", requestParam);
         String countStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "report/findetTipoffMemoByIdCount", requestParam);
         int total = 0;
 		JSONObject json = JSONObject.fromObject(jsonStr);
 		JSONArray jsonArray = JSONArray.fromObject(json.getString("data"));
 		JSONObject countJson = JSONObject.fromObject(countStr);
 		String countJsonStr = countJson.getString("data");
 		JSONArray totalJson = JSONArray.fromObject(countJsonStr);
 		total = totalJson.getInt(0);
 		@SuppressWarnings("unchecked")
 		List<EtTipoffMemo> ettipoffmemolist = JSONArray.toList(jsonArray, new EtTipoffMemo(), new JsonConfig());
 		DataSet<EtTipoffMemo> dataSet = new DataSet<EtTipoffMemo>(ettipoffmemolist, (long) total, (long) total);
 		DatatablesResponse<EtTipoffMemo> resp = DatatablesResponse.build(dataSet);
 		return resp;
    }
    
    
    //案件列表
    @RequestMapping(value="/findCaseInfo",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public DatatablesResponse<ResponseVO> findCaseInfo( 
			HttpSession httpSession, HttpServletRequest request,
			@RequestParam(required = true, defaultValue = "1") Integer start,
			@RequestParam(required = false, defaultValue = "10") Integer length) {

		try {
			String data = "";
			//获取举报人信息
			String reprotType = request.getParameter("reprotType");
			String tipoffReporter = request.getParameter("tipoffReporter");
			String reporterPhone = request.getParameter("reporterPhone");
			String reporterIdcard = request.getParameter("reporterIdcard");
			//获取被举报人信息
			String beingReported = request.getParameter("beingReported");
			String beingReportedPhone = request.getParameter("beingReportedPhone");
			String beingReportedIdcard = request.getParameter("beingReportedIdcard");

			
			if( "".equals(reprotType)|| reprotType==null){
				DataSet<ResponseVO> dataSet = new DataSet<ResponseVO>(new ArrayList<ResponseVO>(), 0L, 0L);
				DatatablesResponse<ResponseVO> resp = DatatablesResponse.build(dataSet);
				return resp;
			}else{
			SysUser user = (SysUser) request.getAttribute("user");
			Map<String, Object> requestParam = new HashMap<>();
			requestParam.put("userId", user.getId());
			requestParam.put("code",
					"1".equals(user.getLevel()) ? ""
							: "2".equals(user.getLevel()) ? user.getCity()
									: "3".equals(user.getLevel()) ? user.getArea() : user.getPoliceStation());
			requestParam.put("start", start);
			requestParam.put("length", length);
			
			requestParam.put("reprotType",reprotType);
			
			requestParam.put("tipoffReporter",tipoffReporter);
			requestParam.put("reporterPhone",reporterPhone);
			requestParam.put("reporterIdcard",reporterIdcard);
			requestParam.put("beingReported",beingReported);
			requestParam.put("beingReportedPhone",beingReportedPhone);
			requestParam.put("beingReportedIdcard",beingReportedIdcard);
			String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "report/findCaseByIdInfo", requestParam);
			String countStr= APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "report/findCaseByIdInfoCount", requestParam);
			int total = 0;
			JSONObject json = JSONObject.fromObject(jsonStr);
			JSONArray jsonArray = JSONArray.fromObject(json.getString("data"));
			JSONObject countJson = JSONObject.fromObject(countStr);
			String countJsonStr = countJson.getString("data");
			JSONArray totalJson = JSONArray.fromObject(countJsonStr);
			total = totalJson.getInt(0);
			@SuppressWarnings("unchecked")
			List<ResponseVO> ettipoffmemolist = JSONArray.toList(jsonArray, new ResponseVO(), new JsonConfig());
			DataSet<ResponseVO> dataSet = new DataSet<ResponseVO>(ettipoffmemolist, (long) total, (long) total);
			DatatablesResponse<ResponseVO> resp = DatatablesResponse.build(dataSet);
			return resp;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataSet<ResponseVO> dataSet = new DataSet<ResponseVO>(new ArrayList<ResponseVO>(), 0L, 0L);
			DatatablesResponse<ResponseVO> resp = DatatablesResponse.build(dataSet);
			return resp;
		}

	}
   
    //警情列表
    @RequestMapping(value="/findJingQingInfo",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public DatatablesResponse<ResponseVO> findJingQingInfo( 
			HttpSession httpSession, HttpServletRequest request,
			@RequestParam(value = "search[value]") String search,
			@RequestParam(required = true, defaultValue = "1") Integer start,
			@RequestParam(required = false, defaultValue = "10") Integer length){

		try {
			String data = "";
			String reprotType = request.getParameter("reprotType");
			//获取举报人信息
			String tipoffReporter = request.getParameter("tipoffReporter");
			String reporterPhone = request.getParameter("reporterPhone");
			String reporterIdcard = request.getParameter("reporterIdcard");
			//获取被举报人信息
			String beingReported = request.getParameter("beingReported");
			String beingReportedPhone = request.getParameter("beingReportedPhone");
			String beingReportedIdcard = request.getParameter("beingReportedIdcard");
			if( "".equals(reprotType)|| reprotType==null){
				DataSet<ResponseVO> dataSet = new DataSet<ResponseVO>(new ArrayList<ResponseVO>(), 0L, 0L);
				DatatablesResponse<ResponseVO> resp = DatatablesResponse.build(dataSet);
				return resp;
			}else{
			if(!"".equals(search) && search != null){
				String descStr = URLDecoder.decode(search, "UTF-8").replace("\\", "").replaceFirst("\"", "");
				if (descStr != null && !"".equals(descStr)) {
					data = descStr.substring(0, descStr.lastIndexOf("\""));
				}
			}
			
			SysUser user = (SysUser) request.getAttribute("user");
			Map<String, Object> requestParam = new HashMap<>();
			requestParam.put("userId", user.getId());
			requestParam.put("code",
					"1".equals(user.getLevel()) ? ""
							: "2".equals(user.getLevel()) ? user.getCity()
									: "3".equals(user.getLevel()) ? user.getArea() : user.getPoliceStation());
			requestParam.put("start", start);
			requestParam.put("length", length);
			requestParam.put("reprotType",reprotType);
			requestParam.put("tipoffReporter",tipoffReporter);
			requestParam.put("reporterPhone",reporterPhone);
			requestParam.put("reporterIdcard",reporterIdcard);
			requestParam.put("beingReported",beingReported);
			requestParam.put("beingReportedPhone",beingReportedPhone);
			requestParam.put("beingReportedIdcard",beingReportedIdcard);
			
			String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "report/findJingQingInfo", requestParam);
			String countStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_QB, "report/findJingQingInfoCount", requestParam);
			int total = 0;
			JSONObject json = JSONObject.fromObject(jsonStr);
			JSONArray jsonArray = JSONArray.fromObject(json.getString("data"));
			JSONObject countJson = JSONObject.fromObject(countStr);
			String countJsonStr = countJson.getString("data");
			JSONArray totalJson = JSONArray.fromObject(countJsonStr);
			total = totalJson.getInt(0);
			@SuppressWarnings("unchecked")
			List<ResponseVO> ettipoffmemolist = JSONArray.toList(jsonArray, new ResponseVO(), new JsonConfig());
			DataSet<ResponseVO> dataSet = new DataSet<ResponseVO>(ettipoffmemolist, (long) total, (long) total);
			DatatablesResponse<ResponseVO> resp = DatatablesResponse.build(dataSet);
			return resp;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataSet<ResponseVO> dataSet = new DataSet<ResponseVO>(new ArrayList<ResponseVO>(), 0L, 0L);
			DatatablesResponse<ResponseVO> resp = DatatablesResponse.build(dataSet);
			return resp;
		}

	}
}
