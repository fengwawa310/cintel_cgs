package com.controller.taskStu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.services.api.APIClientRequest;
import com.common.utils.*;
import com.entity.sys.SysUser;
import com.entity.taskStu.ApCaseSeriesEvent;
import com.entity.taskStu.ApCaseSeriesResult;
import com.entity.taskStu.ApJudgelog;
import com.util.SysUserHelp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.consts.Const;
import com.common.consts.Global;
import com.entity.taskStu.EtJudgeflow;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("etJudgeflowHan")
public class EtJudgeflowHandler {
	
	
	
	@RequestMapping(value = "/selectEJFList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject selectEJFList(HttpServletRequest request,HttpServletResponse response,Integer start,
			   Integer length) {
		String afdd = request.getParameter("afdd");
		String zbdw = request.getParameter("zbdw");
		String bjsjStart = request.getParameter("bjsjStart");
		String bjsjEnd = request.getParameter("bjsjEnd");
		String xfsjStart = request.getParameter("xfsjStart");
		String xfsjEnd = request.getParameter("xfsjEnd");
		String flowState = request.getParameter("flowState");
		String pageCon = request.getParameter("pageCon");//pageCon:"signList"
		if(pageCon!=null&&!"".equals(pageCon)){
			pageCon="5003,5004,5005,5006";
		}
		if(flowState!=null&&!"".equals(flowState)){
			String[] fsC=flowState.split(",");
			StringBuffer flowStateStr = new StringBuffer();
			for(int i = 0; i < fsC.length; i++){
				if(fsC[i]!=null&&!"".equals(fsC[i])){
					flowStateStr.append(fsC[i]).append(",");
				}
			}
			flowState=flowStateStr.toString().substring(0, flowStateStr.length()-1);
		}
		
		SysUser user = (SysUser) request.getAttribute("user");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("afdd",afdd);
		map.put("zbdw",zbdw);
		map.put("start",start);
		map.put("length",length);
		map.put("bjsjStart",bjsjStart);
		map.put("bjsjEnd",bjsjEnd==null||"".equals(bjsjEnd)?"":bjsjEnd+" 23:59:59");
		map.put("xfsjStart",xfsjStart);
		map.put("xfsjEnd",xfsjEnd==null||"".equals(xfsjEnd)?"":xfsjEnd+" 23:59:59");
		map.put("flowState",flowState);
		map.put("pageCon",pageCon);
		map.put("issuedUnit","1".equals(user.getLevel())?user.getProvince():"2".equals(user.getLevel())?user.getCity():"3".equals(user.getLevel())?user.getArea():user.getPoliceStation());
		
		String serviceUrl = "etJudgeflowCon/selectEJFList";
		String serviceCountUrl = "etJudgeflowCon/countSelectEJFList";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, serviceUrl, map);
		String countResult = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, serviceCountUrl, map);
		int total = 0;
		JSONObject json = JSONObject.fromObject(result);
		JSONArray jsonArray = JSONArray.fromObject(json.getString("data"));
		
		JSONObject countJson = JSONObject.fromObject(countResult);
		String countJsonStr = countJson.getString("data");
		JSONArray totalJson = JSONArray.fromObject(countJsonStr);
		total = totalJson.getInt(0);

		
		JSONObject returnJson= new JSONObject();
		returnJson.accumulate("data", jsonArray);
		returnJson.accumulate("recordsTotal", total);
		returnJson.accumulate("recordsFiltered", total);
//		@SuppressWarnings("unchecked")
//		List<EtJudgeflow> etJudgeflowList = JSONArray.toList(jsonArray, new EtJudgeflow(), new JsonConfig());
//		DataSet<EtJudgeflow> dataSet = new DataSet<EtJudgeflow>(etJudgeflowList, (long) total, (long) total);
//		DatatablesResponse<EtJudgeflow> resp = DatatablesResponse.build(dataSet);
//		
//		System.out.println(result);
//		System.out.println(countResult);
		
		return returnJson;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/selectEJFListFastEn", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JSONArray selectEJFListFastEn(HttpServletRequest request,HttpServletResponse response) {
//		String flowState = request.getParameter("flowState");
//		String pageCon = request.getParameter("pageCon");
		SysUser user = (SysUser) request.getAttribute("user");
		String file_iconOpen="/view/assets/images/gallery/file_open.png";
		String file_iconClose="/view/assets/images/gallery/file_close.png";
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("signCode",user.getId());
		
		String serviceUrl = "etJudgeflowCon/selectEJFListFastEn";
//		String serviceCountUrl = "etJudgeflowCon/countSelectEJFListFastEn";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, serviceUrl, map);
		JSONObject json = JSONObject.fromObject(result);
		JSONArray jsonArray = JSONArray.fromObject(json.getString("data"));
		JSONObject returnJson= new JSONObject();
		JSONArray treeBody= new JSONArray();
		List<EtJudgeflow> etJudgeflowList = JSONArray.toList(jsonArray, new EtJudgeflow(), new JsonConfig());
		for(int i=1;i<=4;i++){
			//1:待办案件，2：待办警情，3：已办案件，4：已办警情
			JSONObject treeData= new JSONObject();
			if(i==1){//1:待办案件
				treeData.accumulate("id", "1111");
				treeData.accumulate("pId", "0");
				treeData.accumulate("open", true);
				treeData.accumulate("name", "待办案件");
				treeData.accumulate("icon", file_iconOpen);
//				treeData.accumulate("iconClose", file_iconClose);
			}else if(i==2){//2：待办警情
				treeData.accumulate("id", "2222");
				treeData.accumulate("pId", "0");
				treeData.accumulate("open", false);
				treeData.accumulate("name", "待办警情");
				treeData.accumulate("icon", file_iconOpen);
//				treeData.accumulate("iconClose", file_iconClose);
			}else if(i==3){//3：已办案件
				treeData.accumulate("id","3333");
				treeData.accumulate("pId", "0");
				treeData.accumulate("open", false);
				treeData.accumulate("name", "已办案件");
				treeData.accumulate("icon", file_iconOpen);
//				treeData.accumulate("iconClose", file_iconClose);
			}else if(i==4){//4：已办警情
				treeData.accumulate("id","4444");
				treeData.accumulate("pId", "0");
				treeData.accumulate("open", false);
				treeData.accumulate("name", "已办警情");
				treeData.accumulate("icon", file_iconOpen);
//				treeData.accumulate("iconClose", file_iconClose);
			}
			treeBody.add(treeData);
		}
		for(EtJudgeflow egf:etJudgeflowList){
			JSONObject treeData= new JSONObject();
			treeData.accumulate("id", egf.getId());
			if(egf.getJudgeType()==0){//研判类型 0 案件；1警情
				if("5004".equals(egf.getFlowState().toString())){//5004为已签收，其他则为已处理
					treeData.accumulate("pId", "1111");
				}else{
					treeData.accumulate("pId", "3333");
				}
			}else{
				if("5004".equals(egf.getFlowState().toString())){//5004为已签收，其他则为已处理
					treeData.accumulate("pId", "2222");
				}else{
					treeData.accumulate("pId", "4444");
				}
				
			}
//			treeData.accumulate("open", false);
			treeData.accumulate("caseNo", egf.getCaseNo()==null?"":egf.getCaseNo());
			treeData.accumulate("judgeType", egf.getJudgeType()==null?"":egf.getJudgeType());
			treeData.accumulate("name", egf.getTitle()==null?"":egf.getTitle());
			treeData.accumulate("icon", file_iconOpen);
//			treeData.accumulate("iconClose", file_iconClose);
			
			treeBody.add(treeData);
		}
//		returnJson.accumulate("tree", treeBody);
		return treeBody;
	}

	/**
	 * 	/etJudgeflowHan/selectById
	 * @param httpSession
	 * @param request
	 * @param id
	 * @param userNoticId
     * @return
     */
	@RequestMapping(value="/selectById",produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String selectById(HttpSession httpSession, HttpServletRequest request, String id, String userNoticId){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id",id);
		String jsonStr = "";
		try {
			jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "/etJudgeflowCon/selectById", jsonObject);
			System.out.println(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * 			/etJudgeflowHan/selectJudgeDetailLog
     * @return
     */
	@RequestMapping(value="/selectJudgeDetailLog",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DatatablesResponse selectJudgeDetailLog(HttpSession httpSession, HttpServletRequest request, @RequestParam(value = "search[value]") String search, @RequestParam(required=true,defaultValue="1")Integer start,
												   @RequestParam(required=false,defaultValue="10")Integer length,String id){
		SysUser user = (SysUser) request.getAttribute("user");

//		ObjectToMapGen objectToMapGen = new ObjectToMapGen();
		Map map = new HashMap();
		map.put("id",id);
		map.put("start",""+start);
		map.put("length",""+length);
		PageHelpVO pageHelpVO = null;
		try {
			String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "/etJudgeflowCon/selectJudgeDetailLog", map);
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			JSONObject respContent = (JSONObject) jsonObject.get("data");
			pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataSet dataSet = new DataSet(pageHelpVO.getList(),pageHelpVO.getTotal(),pageHelpVO.getTotal());
		DatatablesResponse<ApJudgelog> resp = DatatablesResponse.build(dataSet);
		return resp;
	}


	/**
	 *   /etJudgeflowHan/updateJudgeOperType
	 * @param httpSession
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/updateJudgeOperType",produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public String updateJudgeOperType(HttpSession httpSession, HttpServletRequest request, String id,String operType,String auditOpinion,
									  String issuedUnit){
		SysUser user = (SysUser) request.getAttribute("user");
		//获取用户地区编码
		String userDicUnit = SysUserHelp.getSysUserDicUnit(user);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id",id);
		jsonObject.put("operType",operType);
		jsonObject.put("auditOpinion",auditOpinion);
		jsonObject.put("issuedUnit",issuedUnit);
		jsonObject.put("userId",user.getId());
		jsonObject.put("userDicUnit",userDicUnit);

		String jsonStr = "";
		try {
			jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "/etJudgeflowCon/updateJudgeOperType", jsonObject);
			System.out.println(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;
	}
	/**
	 * 手动触发案件串并
	 * @return
	 */
	@RequestMapping(value="/series",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject selectSeries(HttpServletRequest request, HttpServletResponse response, String ajbh,String similar){
		SysUser user = (SysUser) request.getAttribute("user");
		//获取用户地区编码
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("url",Global.getConfig("bys_caseSeriesUrl"));
		jsonObject.put("ajbh",ajbh);
		jsonObject.put("similar",similar);
		jsonObject.put("name",user.getName());
		jsonObject.put("code",user.getId());
		String jsonStr = "";
		try {
			jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "etJudgeflowCon/series", jsonObject);
			System.out.println(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.fromObject(jsonStr);
	}

	@RequestMapping(value="/selectSeriesResult",produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public DatatablesResponse selectSeriesResult(HttpSession httpSession, HttpServletRequest request, @RequestParam(value = "search[value]") String search, @RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length, String eventId){
		//分页条件
		PageVO pageVO = new PageVO(start, length);
		String pageVOStr = GsonUtils.getGson().toJson(pageVO);
		JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
		pageVOObj.put("eventId",eventId);
		PageHelpVO pageHelpVO =null;
		try {
			String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "etJudgeflowCon/selectSeriesResult", pageVOObj);
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			JSONObject respContent = (JSONObject) jsonObject.get("data");
			pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataSet dataSet = new DataSet(pageHelpVO.getList(),pageHelpVO.getTotal(),pageHelpVO.getTotal());
		DatatablesResponse<ApCaseSeriesResult> resp = DatatablesResponse.build(dataSet);
		return resp;
	}

	/**
	 * 查询案件串并事件
	 * @return
	 */
	@RequestMapping(value="/selectSeriesEvent",produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public DatatablesResponse selectSeriesEvent(HttpSession httpSession, HttpServletRequest request, @RequestParam(value = "search[value]") String search, @RequestParam(required=true,defaultValue="1")Integer start, @RequestParam(required=false,defaultValue="10")Integer length, String caseNo){
		//分页条件
		PageVO pageVO = new PageVO(start, length);
		String pageVOStr = GsonUtils.getGson().toJson(pageVO);
		JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
		pageVOObj.put("caseNo",caseNo);
		PageHelpVO pageHelpVO =null;
		try {
			String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_TASKSTU, "etJudgeflowCon/selectSeriesEvent", pageVOObj);
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			JSONObject respContent = (JSONObject) jsonObject.get("data");
			pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataSet dataSet = new DataSet(pageHelpVO.getList(),pageHelpVO.getTotal(),pageHelpVO.getTotal());
		DatatablesResponse<ApCaseSeriesEvent> resp = DatatablesResponse.build(dataSet);
		return resp;
	}

}
