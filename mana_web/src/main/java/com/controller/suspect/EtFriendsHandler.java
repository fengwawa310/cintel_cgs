package com.controller.suspect;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.consts.Const;
import com.common.consts.Global;
import com.common.services.api.APIClientRequest;
import com.common.utils.IDGenerator;
import com.entity.sys.SysUser;

@Controller
@RequestMapping("etFriendsHandler")
public class EtFriendsHandler {

	private static String servicesCode = Const.SUB_SYSTEM_CODE_SUSPECT;

	@RequestMapping(value = "/deleteByPrimaryKey")
	@ResponseBody
	public String deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);

		String serviceUrl = "/etFriendsCon/deleteByPrimaryKey";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);

		return result;
	}

	@RequestMapping(value = "/insert")
	@ResponseBody
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		
		SysUser user = (SysUser) request.getAttribute("user");
		String suspectNo = request.getParameter("suspectNo");
		String roleType = request.getParameter("roleType");
		String roleDes = request.getParameter("roleDes");
		String name = request.getParameter("name");
		String idcard = request.getParameter("idcard");
		String relation = request.getParameter("relation");
		String address = request.getParameter("address");
		String headUrl = request.getParameter("headUrl");
		String remarks = request.getParameter("remarks");
		
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("id", IDGenerator.getorderNo());
		map.put("suspectNo",suspectNo);
		map.put("roleType",roleType);
		map.put("roleDes",roleDes);
		map.put("name",name);
		map.put("idcard",idcard);
		map.put("relation",relation);
		map.put("address",address);
		map.put("headUrl",headUrl);
		map.put("remarks",remarks);
		map.put("userId",user.getId());
		map.put("createTime", new Date());
		map.put("modifyTime",  new Date());
		String serviceUrl = "/etFriendsCon/insertSelective";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);
		
		return result;
	}

	/*
	 * 根据重点人员编号查询相关的亲友关系
	 *
	 * 	/etFriendsHandler/selectFriendsByPrimaryKey?suspectNo=111
	 */
	@RequestMapping(value = "/selectFriendsByPrimaryKey")
	@ResponseBody
	public String selectFriendsByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String fastFileUrl="";//Global.getConfig("fastFileUrl");
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("fastFileUrl", fastFileUrl);

		String serviceUrl = "/etFriendsCon/selectFriendsByPrimaryKey";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);
		return result;
	}
	
	/*
	 * 根据重点人员编号查询相关的亲友关系
	 */
	@RequestMapping(value = "/selectByPrimaryKey")
	@ResponseBody
	public String selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		String serviceUrl = "/etFriendsCon/selectByPrimaryKey";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);
		return result;
	}

	@RequestMapping(value = "/updateByPrimaryKeySelective")
	@ResponseBody
	public String updateByPrimaryKeySelective(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String suspectNo = request.getParameter("suspectNo");
		String roleType = request.getParameter("roleType");
		String roleDes = request.getParameter("roleDes");
		String name = request.getParameter("name");
		String idcard = request.getParameter("idcard");
		String relation = request.getParameter("relation");
		String address = request.getParameter("address");
		String headUrl = request.getParameter("headUrl");
		String remarks = request.getParameter("remarks");
		
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("id", id);
		map.put("suspectNo",suspectNo);
		map.put("roleType",roleType);
		map.put("roleDes",roleDes);
		map.put("name",name);
		map.put("idcard",idcard);
		map.put("relation",relation);
		map.put("address",address);
		map.put("headUrl",headUrl);
		map.put("remarks",remarks);
		map.put("modifyTime",  new Date());
		String serviceUrl = "/etFriendsCon/updateByPrimaryKeySelective";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);
		
		return result;
	}


}
