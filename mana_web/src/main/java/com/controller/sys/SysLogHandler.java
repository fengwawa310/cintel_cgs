package com.controller.sys;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.suspect.EtSuspect;
import com.vo.taskStu.ResultData;
import com.vo.taskStu.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.consts.Const;
import com.common.services.api.APIClientRequest;
import com.common.utils.DataSet;
import com.common.utils.DatatablesResponse;
import com.common.utils.IDGenerator;
import com.common.utils.UdpGetClientMacAddr;
import com.entity.sys.SysLog;
import com.entity.sys.SysUser;
import com.util.SysUserHelp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("sysLog")
public class SysLogHandler {

	private static String servicesCode = Const.SUB_SYSTEM_CODE_SYS;

/*	@RequestMapping("insertSysLog")
	@ResponseBody
	public String insertSysLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysUser user = (SysUser) request.getAttribute("user");

		String operatoTypestr = request.getParameter("czLeixing");
		String remark = request.getParameter("beizhu");
		int operatoType = Integer.parseInt(operatoTypestr);
		SysLog sysLog = new SysLog();
		sysLog.setOperatoType(operatoType);
		sysLog.setRemark(remark);
		String result = insert(sysLog, user, request);
		return result;
	}*/

	@RequestMapping("findSysLogList")
	@ResponseBody
	public DatatablesResponse<SysLog> findSysLogList(HttpServletRequest request, HttpServletResponse response,
			Integer start, Integer length) {
		SysUser user = (SysUser) request.getAttribute("user");

		HashMap<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("length", length);
		
		/*
    	 * 分权分域-同级单位间不共享；
    	 * 数据对上级单位默认授权；
    	 */
		map.put("deceSigns",SysUserHelp.getUserDeceSigns(user, Const.DECE_SIGNS_TYPE_UNIT));
		
		String serviceUrl = "/sysLogCon/findSysLoglList";
		String serviceCountUrl = "/sysLogCon/countFindSysLoglList";
		int total = 0;
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SYS, serviceUrl, map);
		String countResult = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SYS, serviceCountUrl, map);

		JSONObject json = JSONObject.fromObject(result);
		JSONArray jsonArray = JSONArray.fromObject(json.getString("data"));
		JSONObject countJson = JSONObject.fromObject(countResult);
		String countJsonStr = countJson.getString("data");
		try{
			JSONArray totalJson = JSONArray.fromObject(countJsonStr);
			total = totalJson.getInt(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		@SuppressWarnings("unchecked")
		List<SysLog> SysLogList = JSONArray.toList(jsonArray, new SysLog(), new JsonConfig());
		DataSet<SysLog> dataSet = new DataSet<SysLog>(SysLogList, (long) total, (long) total);
		DatatablesResponse<SysLog> resp = DatatablesResponse.build(dataSet);

		return resp;
	}

/*	public String insert(SysLog sl, SysUser user, HttpServletRequest request) throws Exception {
		// 1省局、2市局、3分局、4派出所
		String ipAddr = UdpGetClientMacAddr.getIp(request);
		String macAddr = new UdpGetClientMacAddr(ipAddr).GetRemoteMacAddr();
		String unitCode = "1".equals(user.getLevel()) ? user.getProvince()
				: "2".equals(user.getLevel()) ? user.getCity()
						: "3".equals(user.getLevel()) ? user.getArea() : user.getPoliceStation();
		String unitCodeName = "1".equals(user.getLevel()) ? user.getProvinceName()
				: "2".equals(user.getLevel()) ? user.getCityName()
						: "3".equals(user.getLevel()) ? user.getAreaName() : user.getPoliceStationName();
		Map<String, Object> sysLog = new HashMap<>();
		sysLog.put("Id", IDGenerator.getorderNo());
		sysLog.put("OperatorCode", user.getId());
		sysLog.put("OperatorName", user.getUserName());
		sysLog.put("OperatoUnitCode", unitCode);
		sysLog.put("OperatoUnitName", unitCodeName);
		sysLog.put("OperatoType", sl.getOperatoType());
		sysLog.put("Remark", sl.getRemark());
		sysLog.put("OperatoTime", new Date());
		sysLog.put("CreatTime", new Date());
		sysLog.put("OperatoIp", ipAddr);
		sysLog.put("OperatoMac", macAddr);

		String serviceUrl = "/sysLogCon/insert";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SYS, serviceUrl, sysLog);

		return result;
	return null; }*/

	@RequestMapping(value = "/findLogList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultEntity findLogList(HttpServletRequest request){
		ResultEntity resultData = new ResultEntity();
		JSONObject objectOrg = new JSONObject();
		try {
			String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SYS, "sysLogCon/findLogList",objectOrg);
			JSONObject object = JSONObject.fromObject(jsonStr);
			JSONArray respContent = (JSONArray) object.get("data");
			resultData.setCode("200");
			resultData.setMsg("日志获取成功！");
			resultData.setData(respContent);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultData;
	}

}
