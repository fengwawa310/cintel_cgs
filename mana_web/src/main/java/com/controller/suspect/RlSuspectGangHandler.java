package com.controller.suspect;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.consts.Const;
import com.common.consts.Global;
import com.common.services.api.APIClientRequest;
import com.common.utils.IDGenerator;
import com.google.gson.JsonObject;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("rlSuspectGangHandler")
public class RlSuspectGangHandler {

	private static String servicesCode = Const.SUB_SYSTEM_CODE_SUSPECT;

	/*
	 * 团伙人员删除功能：
	 * 功能描述：删除其中一个重点人员（非最大头目），
	 * 		需要删除人员的下级父ID调整为该人员的父ID: delFlag:0
	 * 	或者
	 * 		删除本id及其所有下属 : delFlag:1
	 * 本次只保留删除本身功能，删除下属功能后期开发
	 */
	@RequestMapping(value = "/deleteByPrimaryKey")
	@ResponseBody
	public String deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String delFlag = request.getParameter("delFlag");
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		String serviceUrl="";
		//if(delFlag!=null&&"0".equals(delFlag)){
			//删除本身
			serviceUrl = "/rlSuspectGangCon/deleteByPrimaryKey";
		/*}else{
			//删除本身及其下属
			serviceUrl = "/rlSuspectGangCon/deleteSubordinateByPrimaryKey";
		}*/
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);

		return result;
	}

	@RequestMapping(value = "/insert")
	@ResponseBody
	public String insert(HttpServletRequest request, HttpServletResponse response){
		
		String suspectIds = request.getParameter("suspectIds");
		String gangId = request.getParameter("gangId");
		String role = request.getParameter("role");
		String parentId = request.getParameter("parentId");
		String demo = request.getParameter("demo");
		//{ "firstName":"John" , "lastName":"Doe" }
		String rs="{ ";
		
		String[] suspectId = suspectIds.split(",");
		for(String s:suspectId){
			HashMap<String, Object> map = new HashMap<>();
			map.put("id", IDGenerator.getorderNo());
			map.put("suspectId", s.replace("\"", "").replace("[", "").replace("]", ""));
			map.put("gangId", gangId);
			map.put("role", role);
			map.put("parentId", parentId==null?"":parentId);
			map.put("demo", demo);
			map.put("createTime", new Date());
			map.put("modifyTime",  new Date());
			String serviceUrl = "/rlSuspectGangCon/insertSelective";
			String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);
			
			JSONObject jo= JSONObject.fromObject(result);
			if("1".equals(jo.get("data")+"")){
				rs=rs+"\""+s+"\":\"插入成功\",";
			}else{
				rs=rs+"\""+s+"\":\"插入失败\",";
			}
		}
		
		return rs.substring(0, rs.length()-1)+"}";
	}

	@RequestMapping("/selectByPrimaryKey")
	@ResponseBody
	public Map<String, String> selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response){
		Map<String, String> mapResult = new HashMap<>();
		String gangId = request.getParameter("gangId");
		HashMap<String, Object> map = new HashMap<>();
		map.put("gangId", gangId);

		String serviceUrl = "/rlSuspectGangCon/selectByPrimaryKey";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);
		mapResult.put("data", result);
		return mapResult;
	}
	
	@RequestMapping("/selectByGangId")
	@ResponseBody
	public Map<String, String> selectByGangId(HttpServletRequest request, HttpServletResponse response){
		Map<String, String> mapResult = new HashMap<>();
		String gangId = request.getParameter("gangId");
		String fastFileUrl="";//Global.getConfig("fastFileUrl");
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", gangId);
		map.put("fastFileUrl",fastFileUrl);
		
		String serviceUrl = "/rlSuspectGangCon/selectByGangId";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);
		mapResult.put("data", result);
		return mapResult;
	}

	
	@RequestMapping(value = "/updateByPrimaryKey")
	@ResponseBody
	public String updateByPrimaryKey(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {// 注意：update的id 唯一标志符不能为空
			return null;
		}
		String suspectId = request.getParameter("suspectId");
		String gangId = request.getParameter("gangId");
		String role = request.getParameter("role");
		String parentId = request.getParameter("parentId");
		String demo = request.getParameter("demo");
		
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("id", id);
		map.put("suspectId", suspectId);
		map.put("gangId", gangId);
		map.put("role", role);
		map.put("parentId", parentId);
		map.put("demo", demo);
		map.put("modifyTime",  new Date());
		String serviceUrl = "/rlSuspectGangCon/updateByPrimaryKeySelective";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);
		
		return result;
	}

}
