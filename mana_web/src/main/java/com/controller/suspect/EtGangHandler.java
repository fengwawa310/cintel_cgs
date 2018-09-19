package com.controller.suspect;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.consts.Const;
import com.common.services.api.APIClientRequest;
import com.common.utils.IDGenerator;
import com.entity.suspect.EtGang;
import com.entity.sys.SysUser;

@Controller
@RequestMapping("etGangHandler")
public class EtGangHandler {
	
	private static String servicesCode = Const.SUB_SYSTEM_CODE_SUSPECT;

	/*
	 * 删除该团伙时应该先删除其团伙下属的所有人员（rl_suspect_gang表）
	 */
	@RequestMapping(value = "/deleteByPrimaryKey")
	@ResponseBody
	public String deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);

		String serviceUrl = "/etGangCon/deleteByPrimaryKey";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);

		return result;
	}

	@RequestMapping(value = "/insert")
	@ResponseBody
	public String insert(HttpServletRequest request, HttpServletResponse response){
		
		SysUser user = (SysUser) request.getAttribute("user");
		
		String gangName = request.getParameter("gangName");
		String gangTag = request.getParameter("gangTag");
		String gangType = request.getParameter("gangType");
		String gangAddrCode = request.getParameter("gangAddrCode");
		String gangAddr = request.getParameter("gangAddr");
		String gangLead = request.getParameter("gangLead");
		String gangDemo = request.getParameter("gangDemo");
		
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("id", IDGenerator.getorderNo());
		map.put("gangName", gangName==null?"":gangName);
		map.put("gangTag", gangTag==null?"":gangTag);
		map.put("gangType", gangType==null?"":gangType);
		map.put("gangAddrCode", gangAddrCode==null?"":gangAddrCode);
		map.put("gangAddr", gangAddr==null?"":gangAddr);
		map.put("gangLead", gangLead==null?"":gangLead);
		map.put("gangDemo", gangDemo==null?"":gangDemo);
		map.put("userId",  user.getId());
		
		String serviceUrl = "etGangCon/insertSelective";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);
		
		return result;
	}


	@RequestMapping(value = "/selectByPrimaryKey")
	@ResponseBody
	public Map<String, String> selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response){
		Map<String, String> mapResult = new HashMap<>();
		String gangLead = request.getParameter("gangLead");
		HashMap<String, Object> map = new HashMap<>();
		map.put("gangLead", gangLead);

		String serviceUrl = "/etGangCon/selectByPrimaryKey";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);
		mapResult.put("data", result);
		return mapResult;
	}
	
	@RequestMapping(value = "/selectGangBySuspectNo")
	@ResponseBody
	public String selectGangBySuspectNo(HttpServletRequest request, HttpServletResponse response){
		SysUser user= (SysUser) request.getAttribute("user");
		String suspectNo = request.getParameter("suspectNo");
		String gangName = request.getParameter("gangName");
		HashMap<String, Object> map = new HashMap<>();
		map.put("suspectNo", suspectNo);
		map.put("gangName", gangName);
		map.put("userId", user.getId());
		
		String serviceUrl = "/etGangCon/selectGangBySuspectNo";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);
		return result;
	}
	
	@RequestMapping(value = "/selectByUserId") 
	@ResponseBody
	public String selectByUserId(HttpServletRequest request, HttpServletResponse response){
		SysUser user = (SysUser) request.getAttribute("user");
		HashMap<String, Object> map = new HashMap<>();
		map.put("userId", user.getId());
		
		String serviceUrl = "/etGangCon/selectByUserId";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);
		return result;
	}

	@RequestMapping(value = "/updateByPrimaryKeySelective")
	@ResponseBody
	public String updateByPrimaryKeySelective(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {// 注意：update的id 唯一标志符不能为空
			return null;
		}
		String gangName = request.getParameter("gangName");
		String gangTag = request.getParameter("gangTag");
		String gangType = request.getParameter("gangType");
		String gangAddrCode = request.getParameter("gangAddrCode");
		String gangAddr = request.getParameter("gangAddr");
		String gangLead = request.getParameter("gangLead");
		String gangDemo = request.getParameter("gangDemo");
		
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("id", id);
		map.put("gangName", gangName==null?"":gangName);
		map.put("gangTag", gangTag==null?"":gangTag);
		map.put("gangType", gangType==null?"":gangType);
		map.put("gangAddrCode", gangAddrCode==null?"":gangAddrCode);
		map.put("gangAddr", gangAddr==null?"":gangAddr);
		map.put("gangLead", gangLead==null?"":gangLead);
		map.put("gangDemo", gangDemo==null?"":gangDemo);
		String serviceUrl = "/etGangCon/updateByPrimaryKeySelective";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);
		
		return result;
	}
}
