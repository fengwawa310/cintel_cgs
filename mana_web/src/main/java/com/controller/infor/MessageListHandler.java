package com.controller.infor;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.consts.Const;
import com.common.utils.DataSet;
import com.common.utils.DatatablesResponse;
import com.common.utils.GsonUtils;
import com.common.utils.IDGenerator;
import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.caseInfo.EtCase;
import com.entity.message.MessageList;
import com.entity.sys.SysUser;
import com.service.communal.MessageListService;
import com.util.SysUserHelp;
import com.vo.infor.IntelligenceListRequetParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RequestMapping("/MessageListHandler")
@Controller
public class MessageListHandler {
	private static final Logger logger = LoggerFactory.getLogger(IntelligenceHandler.class);

	
	
	@Resource
	private MessageListService messageListService;
	
	// 消息列表
	@RequestMapping(value = "/list.action", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DatatablesResponse list(HttpSession httpSession, HttpServletRequest request,
			@RequestParam(value = "search[value]") String search,
			@RequestParam(required = true, defaultValue = "1") Integer start,
			@RequestParam(required = false, defaultValue = "10") Integer length) {
		try {
			String data = "";
			String descStr = URLDecoder.decode(search, "UTF-8").replace("\\", "").replaceFirst("\"", "");
			if (descStr != null && !"".equals(descStr)) {
				data = descStr.substring(0, descStr.lastIndexOf("\""));
			}
			logger.info("情报查询流程：<WEB层 分发站>获取到的前台的页面参数为：" + data);
			MessageList messageList = GsonUtils.toBean(data,MessageList.class);
			
			PageVO pageVO = new PageVO(start, length);
			String pageVOStr = GsonUtils.getGson().toJson(pageVO);
			JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
			if (messageList != null) {
				String intelligenceList = GsonUtils.getGson().toJson(messageList);
				JSONObject intelligenceListObj = JSONObject.fromObject(intelligenceList);
				pageVOObj.putAll(intelligenceListObj);
			}
			PageHelpVO pageHelpVO = null;
			SysUser sysUser = (SysUser) request.getAttribute("user");
			String sysUserDicUnit = SysUserHelp.getSysUserDicUnit(sysUser);
			try {
				pageHelpVO = list(pageVO, messageList,sysUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
			DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
			DatatablesResponse<EtCase> resp = DatatablesResponse.build(dataSet);
			return resp;
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
	}

	
	
	// 消息列表领导显示个数
	@RequestMapping(value = "/count.action", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Integer count(HttpSession httpSession, HttpServletRequest request) {
		SysUser user = (SysUser) request.getAttribute("user");
		Map<String,String> map = new HashMap<>();
		/*
    	 * 分权分域-同级单位间不共享；
    	 * 数据对上级单位默认授权；
    	 */
		map.put("deceSigns",SysUserHelp.getUserDeceSigns(user, Const.DECE_SIGNS_TYPE_USER));
		
		return messageListService.queryCount(map);
	}
		
		
	//删除
	@RequestMapping(value = "/shanchu.action", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Integer delete(HttpSession httpSession, HttpServletRequest request,String  relationNo) {
		return messageListService.delete(request.getParameter("relationNo"));
	}
		
	private PageHelpVO list(PageVO pageVO, MessageList messageList, SysUser sysUser) {
		PageHelpVO pageHelpVO = null;
		try {
			pageHelpVO = messageListService.queryByList(pageVO,messageList,SysUserHelp.getUserDeceSigns(sysUser, Const.DECE_SIGNS_TYPE_USER),null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageHelpVO;
	}
	
}
