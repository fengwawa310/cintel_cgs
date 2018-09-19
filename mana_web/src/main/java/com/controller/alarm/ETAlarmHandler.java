package com.controller.alarm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.sys.SystemLog;
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
import com.common.utils.PageVO;
import com.entity.alarm.EtAlarm;
import com.entity.suspect.EtSuspect;
import com.entity.sys.SysUser;
import com.util.SysUserHelp;
import com.vo.suspect.OperPermissionVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/*
 * 璀︽儏绠＄悊妯″潡鍔熻兘
 */
@Controller
@RequestMapping("/ETAlarmHandler")
public class ETAlarmHandler {

	/*
	 * 鍚屾牱鍏堝皢json瀛楃涓茶浆鎹负json瀵硅薄锛屽啀灏唈son瀵硅薄杞崲涓簀ava瀵硅薄锛屽涓嬫墍绀恒�� JSONObject obj = new
	 * JSONObject().fromObject(jsonStr);//灏唈son瀛楃涓茶浆鎹负json瀵硅薄 灏唈son瀵硅薄杞崲涓簀ava瀵硅薄
	 * Person jb =
	 * (Person)JSONObject.toBean(obj,Person.class);//灏嗗缓json瀵硅薄杞崲涓篜erson瀵硅薄
	 */
	
	@SuppressWarnings("unused")
	@RequestMapping("insertETAlaemData")
	@ResponseBody
	@SystemLog(module = "案警管理",methods = "查询警情录入")
	public String insertETAlaemData(HttpServletRequest request, HttpServletResponse response) {
		// TODO 寰呴〉闈㈡洿鏂板畬鍠勪紶杈撹繃鏉ョ殑浠ｇ爜

		SysUser user = (SysUser) request.getAttribute("user");
		String officerName = request.getParameter("officerName");
		String unitName = "";//request.getParameter("unitName");
		String unitCode = "";//request.getParameter("unitCode");
		if("1".equals(user.getLevel())){
			unitName=user.getProvinceName();
			unitCode=user.getProvince();
		}else if("2".equals(user.getLevel())){
			unitName=user.getCityName();
			unitCode=user.getCity();
		}else if("3".equals(user.getLevel())){
			unitName=user.getAreaName();
			unitCode=user.getArea();
		}else if("4".equals(user.getLevel())){
			unitName=user.getPoliceStationName();
			unitCode=user.getPoliceStation();
		}
		String alarmClass = request.getParameter("alarmClass");
		String alarmTime = request.getParameter("alarmTime");
		String alarmPersonName = request.getParameter("alarmPersonName");
		String alarmPersonPhone = request.getParameter("alarmPersonPhone");
		String locationCase = request.getParameter("locationCase");
		String alarmDesc = request.getParameter("alarmDesc");

		/*// String OfficerCode=user.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 灏忓啓鐨刴m琛ㄧず鐨勬槸鍒嗛挓
	
		Date date  = null;
		try {
		   date = sdf.parse(alarmTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/

		HashMap<String, Object> etAlarm = new HashMap<>();
		etAlarm.put("Id", IDGenerator.getorderNo());
		etAlarm.put("AlarmNo", IDGenerator.getorderNo());
		etAlarm.put("OfficerCode",user.getId());
		etAlarm.put("OfficerName", officerName);
		etAlarm.put("UnitCode", unitCode);
		etAlarm.put("UnitName", unitName);
		etAlarm.put("alarmClass", alarmClass);
		etAlarm.put("AlarmTime", alarmTime);
		etAlarm.put("AlarmPersonName", alarmPersonName == null || "".equals(alarmPersonName) ? "" : alarmPersonName);
		etAlarm.put("AlarmPersonPhone",
				alarmPersonPhone == null || "".equals(alarmPersonPhone) ? "" : alarmPersonPhone);
		etAlarm.put("LocationCase", locationCase == null || "".equals(locationCase) ? "" : locationCase);
		// etAlarm.put("AlarmClass",alarmClass);
		// etAlarm.put("SourceType",sourceType);
		etAlarm.put("IsArchive", 0);
		etAlarm.put("ArchiveDesc", "");
		etAlarm.put("IsAbandon", 0);
		etAlarm.put("AbandonDesc", "");
		etAlarm.put("AlarmDesc", alarmDesc == null || "".equals(alarmDesc) ? "" : alarmDesc);
		etAlarm.put("CreatTime", new Date());
		etAlarm.put("ModifyTime", new Date());

		String serviceUrl = "ETAlarmCon/insertEtAlarm";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, serviceUrl, etAlarm);

		return result;
	}

	/*
	 * 鏍规嵁ID鏇存柊璀︽儏鏁版嵁
	 */
	@RequestMapping("updateETAlaemData")
	@ResponseBody
	@SystemLog(module = "案警管理",methods = "查询警情编辑")
	public String updateETAlaemData(HttpServletRequest request, HttpServletResponse response) {
		// TODO 寰呴〉闈㈡洿鏂板畬鍠勪紶杈撹繃鏉ョ殑浠ｇ爜
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {// 娉ㄦ剰锛歶pdate鐨刬d 鍞竴鏍囧織绗︿笉鑳戒负绌�
			return null;
		}
		SysUser user = (SysUser) request.getAttribute("user");
		String officerName = request.getParameter("officerName");
		String unitName = "";//request.getParameter("unitName");
		String unitCode = "";//request.getParameter("unitCode");
		if("1".equals(user.getLevel())){
			unitName=user.getProvinceName();
			unitCode=user.getProvince();
		}else if("2".equals(user.getLevel())){
			unitName=user.getCityName();
			unitCode=user.getCity();
		}else if("3".equals(user.getLevel())){
			unitName=user.getAreaName();
			unitCode=user.getArea();
		}else if("4".equals(user.getLevel())){
			unitName=user.getPoliceStationName();
			unitCode=user.getPoliceStation();
		}
		String alarmClass = request.getParameter("alarmClass");
		String alarmTime = request.getParameter("alarmTime");
		String alarmPersonName = request.getParameter("alarmPersonName");
		String alarmPersonPhone = request.getParameter("alarmPersonPhone");
		String locationCase = request.getParameter("locationCase");
		String alarmDesc = request.getParameter("alarmDesc");

		// String OfficerCode=user.
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 灏忓啓鐨刴m琛ㄧず鐨勬槸鍒嗛挓
//		java.util.Date date = null;
//		try {
//			date = sdf.parse(alarmTime);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}

		HashMap<String, Object> etAlarm = new HashMap<>();
		etAlarm.put("Id", id);
		// etAlarm.put("OfficerCode",user.getId());
		etAlarm.put("OfficerName", officerName);
		etAlarm.put("UnitCode", unitCode);
		etAlarm.put("UnitName", unitName);
		etAlarm.put("alarmClass", alarmClass);
		etAlarm.put("AlarmTime", alarmTime);
		etAlarm.put("AlarmPersonName", alarmPersonName == null || "".equals(alarmPersonName) ? "" : alarmPersonName);
		etAlarm.put("AlarmPersonPhone",
				alarmPersonPhone == null || "".equals(alarmPersonPhone) ? "" : alarmPersonPhone);
		etAlarm.put("LocationCase", locationCase == null || "".equals(locationCase) ? "" : locationCase);
		// etAlarm.put("AlarmClass",alarmClass);
		// etAlarm.put("SourceType",sourceType);
		etAlarm.put("AlarmDesc", alarmDesc == null || "".equals(alarmDesc) ? "" : alarmDesc);

		String serviceUrl = "ETAlarmCon/updateEtAlarm";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, serviceUrl, etAlarm);

		return result;
	}

	/*
	 * 瀹炰綋鍒犻櫎锛屾暟鎹皢涓嶄細鎭㈠
	 */
	@RequestMapping("/deleteByPrimaryKey")
	@ResponseBody
	@SystemLog(module = "案警管理",methods = "删除警情")
	public String deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);

		String serviceUrl = "ETAlarmCon/deleteByPrimaryKey";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, serviceUrl, map);

		return result;
	}

	/*
	 * 鐘舵�佸垹闄わ紝鏇存柊鏁版嵁鍒犻櫎瀛楁鐘舵�� 鏇存柊 is_abandon 瀛楁鎴� 1: '鍒犻櫎鏍囪瘑锛�0鏈垹闄わ紱1宸插垹闄わ紱榛樿0',
	 */
	@RequestMapping("/abandonByPrimaryKey")
	@ResponseBody
	@SystemLog(module = "案警管理",methods = "警情加入回收站")
	public String abandonByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);

		String serviceUrl = "ETAlarmCon/abandonByPrimaryKey";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, serviceUrl, map);

		return result;
	}

	/*
	 * 鐘舵�佸垹闄ゆ仮澶嶏紝鏇存柊鏁版嵁鍒犻櫎瀛楁鐘舵�� 鏇存柊 is_abandon 瀛楁鎴� 0: '鍒犻櫎鏍囪瘑锛�0鏈垹闄わ紱1宸插垹闄わ紱榛樿0',
	 */
	@RequestMapping("/alarmDeleteReply")
	@ResponseBody
	@SystemLog(module = "案警管理",methods = "恢复警情")
	public String alarmDeleteReplyById(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);

		String serviceUrl = "ETAlarmCon/alarmDeleteReplyById";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, serviceUrl, map);

		return result;
	}

	/*
	 * 鏍规嵁Id鏌ヨ鐩稿叧璀︽儏
	 */
	@RequestMapping("/selectByPrimaryKey")
	@ResponseBody
	@SystemLog(module = "案警管理",methods = "查询警情详情")
	public Map<String, String> selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> mapResult = new HashMap<>();
		String alarmNo = request.getParameter("alarmNo");
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("alarmNo", alarmNo);

		String serviceUrl = "ETAlarmCon/selectByPrimaryKey";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, serviceUrl, map);
		mapResult.put("data", result);
		return mapResult;
	}

	/*
	 * 鏍规嵁鏉′欢 鏌ヨ鎵�鏈夎鎯呮暟鎹甃ist
	 */
	@RequestMapping("/selectEtAlarmList")
	@ResponseBody
	@SystemLog(module = "案警管理",methods = "查询警情列表")
	public DatatablesResponse<EtAlarm> selectEtAlarmList(HttpServletRequest request, HttpServletResponse response,
			Integer start, Integer length) {
		SysUser user = (SysUser) request.getAttribute("user");
		String alarmTime = request.getParameter("alarmTime");
		String unitCode = request.getParameter("unitCode");
		String alarmPersonName = request.getParameter("alarmPersonName");
		String alarmPersonPhone = request.getParameter("alarmPersonPhone");

		HashMap<String, Object> map = new HashMap<>();
		if (unitCode != null && !"".equals(unitCode)) {
			map.put("unitCode", unitCode);
		}else{
			/*
        	 * 鍚岀骇鍗曚綅闂翠笉鍏变韩锛�
        	 * 鏁版嵁瀵逛笂绾у崟浣嶉粯璁ゆ巿鏉冿紱
        	 */
			map.put("deceSigns",SysUserHelp.getUserDeceSigns(user, Const.DECE_SIGNS_TYPE_UNIT));
		}
		if (alarmTime != null && !"".equals(alarmTime)) {
			map.put("alarmTimeStr", alarmTime);
		}
		if (alarmPersonName != null && !"".equals(alarmPersonName)) {
			map.put("alarmPersonName", alarmPersonName);
		}
		if (alarmPersonPhone != null && !"".equals(alarmPersonPhone)) {
			map.put("alarmPersonPhone", alarmPersonPhone);
		}
		
		map.put("start", start);
		map.put("length", length);

		String serviceUrl = "ETAlarmCon/selectEtAlarmList";
		String serviceCountUrl = "ETAlarmCon/countEtAlarmList";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, serviceUrl, map);
		String countResult = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, serviceCountUrl, map);
		int total = 0;
		JSONObject json = JSONObject.fromObject(result);
		JSONArray jsonArray = JSONArray.fromObject(json.getString("data"));
		JSONObject countJson = JSONObject.fromObject(countResult);
		String countJsonStr = countJson.getString("data");
		JSONArray totalJson = JSONArray.fromObject(countJsonStr);
		total = totalJson.getInt(0);
		@SuppressWarnings("unchecked")
		List<EtAlarm> etAlarmList = JSONArray.toList(jsonArray, new EtAlarm(), new JsonConfig());
		DataSet<EtAlarm> dataSet = new DataSet<EtAlarm>(etAlarmList, (long) total, (long) total);
		DatatablesResponse<EtAlarm> resp = DatatablesResponse.build(dataSet);

		return resp;
	}

	/*
	 * 鏌ヨ璀︽儏TOP10 for pageHeader
	 */
	@RequestMapping(value = "/selectEtAlarmListTopTen", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DatatablesResponse<EtAlarm> selectEtAlarmListTopTen(HttpServletRequest request,
			HttpServletResponse response) {
		SysUser user = (SysUser) request.getAttribute("user");
		String alarmTime = request.getParameter("alarmTime");
		String unitName = request.getParameter("unitCode");
		String alarmPersonName = request.getParameter("alarmPersonName");
		String alarmPersonPhone = request.getParameter("alarmPersonPhone");

		HashMap<String, Object> map = new HashMap<>();
		if (unitName != null && !"".equals(unitName)) {
			map.put("unitCode", unitName);
		}
		if (alarmTime != null && !"".equals(alarmTime)) {
			map.put("alarmTimeStr", alarmTime);
		}
		if (alarmPersonName != null && !"".equals(alarmPersonName)) {
			map.put("alarmPersonName", alarmPersonName);
		}
		if (alarmPersonPhone != null && !"".equals(alarmPersonPhone)) {
			map.put("alarmPersonPhone", alarmPersonPhone);
		}

		map.put("start", 0);
		map.put("length", 10);

		String serviceUrl = "ETAlarmCon/selectEtAlarmList";
		String serviceCountUrl = "ETAlarmCon/countEtAlarmList";
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, serviceUrl, map);
		String countResult = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, serviceCountUrl, map);
		int total = 0;
		JSONObject json = JSONObject.fromObject(result);
		JSONArray jsonArray = JSONArray.fromObject(json.getString("data"));
		JSONObject countJson = JSONObject.fromObject(countResult);
		String countJsonStr = countJson.getString("data");
		JSONArray totalJson = JSONArray.fromObject(countJsonStr);
		total = totalJson.getInt(0);
		@SuppressWarnings("unchecked")
		List<EtAlarm> etAlarmList = JSONArray.toList(jsonArray, new EtAlarm(), new JsonConfig());
		DataSet<EtAlarm> dataSet = new DataSet<EtAlarm>(etAlarmList, (long) total, (long) total);
		DatatablesResponse<EtAlarm> resp = DatatablesResponse.build(dataSet);

		return resp;
	}

	/*
	 * 鏍规嵁鏉′欢 鏌ヨ鎵�鏈夎鎯呮暟鎹甃ist
	 */
	@RequestMapping("/selectDelEtAlarmList")
	@ResponseBody
	@SystemLog(module = "案警管理",methods = "查询警情回收站列表")
	public DatatablesResponse<EtAlarm> selectDelEtAlarmList(HttpServletRequest request, HttpServletResponse response,
			Integer start, Integer length) {
		SysUser user = (SysUser) request.getAttribute("user");
		String alarmTime = request.getParameter("alarmTime");
		String unitName = request.getParameter("unitName");
		String alarmPersonName = request.getParameter("alarmPersonName");
		String alarmPersonPhone = request.getParameter("alarmPersonPhone");

		HashMap<String, Object> map = new HashMap<>();
		if (unitName != null && !"".equals(unitName)) {
			map.put("unitCode", unitName);
		}else{
			/*
        	 * 鍚岀骇鍗曚綅闂翠笉鍏变韩锛�
        	 * 鏁版嵁瀵逛笂绾у崟浣嶉粯璁ゆ巿鏉冿紱
        	 */
			map.put("deceSigns",SysUserHelp.getUserDeceSigns(user, Const.DECE_SIGNS_TYPE_UNIT));
		}
		if (alarmTime != null && !"".equals(alarmTime)) {
			map.put("alarmTimeStr", alarmTime);
		}
		if (alarmPersonName != null && !"".equals(alarmPersonName)) {
			map.put("alarmPersonName", alarmPersonName);
		}
		if (alarmPersonPhone != null && !"".equals(alarmPersonPhone)) {
			map.put("alarmPersonPhone", alarmPersonPhone);
		}

		map.put("start", start);
		map.put("length", length);

		String serviceUrl = "ETAlarmCon/selectDelEtAlarmList";
		String serviceCountUrl = "ETAlarmCon/countDelEtAlarmList";
		int total = 0;
		String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, serviceUrl, map);
		String countResult = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, serviceCountUrl, map);

		JSONObject json = JSONObject.fromObject(result);
		JSONArray jsonArray = JSONArray.fromObject(json.getString("data"));
		JSONObject countJson = JSONObject.fromObject(countResult);
		String countJsonStr = countJson.getString("data");
		JSONArray totalJson = JSONArray.fromObject(countJsonStr);
		total = totalJson.getInt(0);
		@SuppressWarnings("unchecked")
		List<EtAlarm> etAlarmList = JSONArray.toList(jsonArray, new EtAlarm(), new JsonConfig());
		DataSet<EtAlarm> dataSet = new DataSet<EtAlarm>(etAlarmList, (long) total, (long) total);
		DatatablesResponse<EtAlarm> resp = DatatablesResponse.build(dataSet);

		return resp;
	}
	/*
	 * 鏍规嵁閲嶇偣浜哄憳缂栧彿鏌ヨ宸茬‘璁ゆ秹璀︿俊鎭�
	 */
	@RequestMapping(value = "/suspectAlarmOpen", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DatatablesResponse suspectAlarmOpen (HttpSession httpSession, HttpServletRequest request,
			@RequestParam(value = "search[value]") String search,
			@RequestParam(required = true, defaultValue = "1") Integer start,
			@RequestParam(required = false, defaultValue = "10") Integer length,String suspectId){
		EtSuspect etSuspect = GsonUtils.toBean(search, EtSuspect.class);
		String parameter = request.getParameter("suspectId");
		if (etSuspect==null&&parameter!=null&&!"".equals(parameter)) {
			etSuspect=new EtSuspect();
			etSuspect.setSuspectId(parameter);
		}
		PageVO pageVO = new PageVO(start, length);
		String pageVOStr = GsonUtils.getGson().toJson(pageVO);
		JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
		if (etSuspect != null) {
			String etCaseStr = GsonUtils.getGson().toJson(etSuspect);
			JSONObject etCaseObj = JSONObject.fromObject(etCaseStr);
			pageVOObj.putAll(etCaseObj);
		}
		PageHelpVO pageHelpVO = null;
		try {
			String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, "ETAlarmCon/suspectAlarmOpen", pageVOObj);
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			JSONObject respContent = (JSONObject) jsonObject.get("data");
			pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
		DatatablesResponse<OperPermissionVO> resp = DatatablesResponse.build(dataSet);
		return resp;
		//String countResult = APIClientRequest.servicesRequest("Const.SUB_SYSTEM_CODE_ALARM", "ETAlarmCon/countDelEtAlarmList", map);
		
	}
	/*
	 * 鏍规嵁閲嶇偣浜哄憳缂栧彿鏌ヨ鏈‘璁ゆ秹璀︿俊鎭�
	 */
	@RequestMapping(value = "/suspectAlarmClose", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DatatablesResponse suspectAlarmClose (HttpSession httpSession, HttpServletRequest request,
			@RequestParam(value = "search[value]") String search,
			@RequestParam(required = true, defaultValue = "1") Integer start,
			@RequestParam(required = false, defaultValue = "10") Integer length,String suspectId){
		EtSuspect etSuspect = GsonUtils.toBean(search, EtSuspect.class);
		String parameter = request.getParameter("suspectId");
		if (etSuspect==null&&parameter!=null&&!"".equals(parameter)) {
			etSuspect=new EtSuspect();
			etSuspect.setSuspectId(parameter);
		}
		PageVO pageVO = new PageVO(start, length);
		String pageVOStr = GsonUtils.getGson().toJson(pageVO);
		JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
		if (etSuspect != null) {
			String etCaseStr = GsonUtils.getGson().toJson(etSuspect);
			JSONObject etCaseObj = JSONObject.fromObject(etCaseStr);
			pageVOObj.putAll(etCaseObj);
		}
		PageHelpVO pageHelpVO = null;
		try {
			String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, "ETAlarmCon/suspectAlarmClose", pageVOObj);
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			JSONObject respContent = (JSONObject) jsonObject.get("data");
			pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
		DatatablesResponse<OperPermissionVO> resp = DatatablesResponse.build(dataSet);
		return resp;
		//String countResult = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_ALARM, "ETAlarmCon/countDelEtAlarmList", map);
		
	}
	
}
