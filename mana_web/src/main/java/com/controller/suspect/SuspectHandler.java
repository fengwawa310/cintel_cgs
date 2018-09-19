package com.controller.suspect;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.entity.suspect.EtSuspect;
import com.entity.suspect.EtVehicle;
import com.entity.suspect.RlSuspectAlarm;
import com.entity.suspect.RlSuspectCase;
import com.entity.sys.SysUser;
import com.entity.ticket.EtTicket;
import com.param.suspect.EtSuspectDTO;
import com.vo.suspect.OperPermissionVO;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("suspecthandler")
public class SuspectHandler {
	/*
	 * 新增
	 * String serverName = "suspect/save";
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("id", "a");
		hashMap.put("name", "b");
		hashMap.put("byname", "c");
		hashMap.put("suspectId", "c");
		hashMap.put("idcardNum", "c");
		hashMap.put("headPhotoUrl", "c");
		hashMap.put("birthdate", new Date());
		hashMap.put("gender", "1202");
		hashMap.put("nation", "汉");
		hashMap.put("educationDegree", "初中");
		hashMap.put("origin", "北京");
		hashMap.put("occupation", "开发");
		hashMap.put("mobilephone", "18666666666");
		hashMap.put("telephone", "18666666665");
		hashMap.put("height", "173");
		hashMap.put("bloodType", "O");
		hashMap.put("shape", "瘦");
	 */
	@RequestMapping("/handlersave")
	@ResponseBody
	public JSONObject save(HttpSession httpSession, HttpServletRequest request, String suspect, String vehicles) throws ParseException {
		//获取User 的ID
	   String uid =(String) request.getAttribute("uid");
	   //获取User 的对象
		SysUser user = (SysUser)request.getAttribute("user");
		String userId = user.getId();
		//录入人警号
        String code = user.getCode();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		EtSuspect etSuspect = GsonUtils.toBean(suspect, EtSuspect.class);
        etSuspect.setEntryPoliceNo(code);
		if (StringUtils.isNotBlank(etSuspect.getId())){//编辑

		}else {	//添加
//			etSuspect.setId(IDGenerator.getorderNo());
			etSuspect.setSuspectId(IDGenerator.getorderNo());
			etSuspect.setEntryUnit(user.getPoliceStation());
			etSuspect.setEntry(uid);
			etSuspect.setEntryName(user.getName());
			//录入单位
            etSuspect.setEntryUnit((user.getLevel()==null||"".equals(user.getLevel())?"":"1".equals(user.getLevel())?user.getProvince():"2".equals(user.getLevel())?user.getCity():"3".equals(user.getLevel())?user.getArea():user.getPoliceStation()));
            //录入单位名称
            etSuspect.setEntryUnitName((user.getLevel()==null||"".equals(user.getLevel())?"":"1".equals(user.getLevel())?user.getProvinceName():"2".equals(user.getLevel())?user.getCityName():"3".equals(user.getLevel())?user.getAreaName():user.getPoliceStationName()));
		}
		String etSuspectStr = GsonUtils.getGson().toJson(etSuspect);
		JSONObject etSuspectObj = JSONObject.fromObject(etSuspectStr);
		etSuspectObj.put("vehicles",vehicles);
		etSuspectObj.put("userId",userId);
//		HashMap<String, Object> hashMap = new HashMap<>();
//		hashMap.put("id",IDGenerator.getorderNo());
//		hashMap.put("name", name);
//		hashMap.put("byname", byname);
//		hashMap.put("suspectId", IDGenerator.getorderNo());
//		hashMap.put("idcardNum", idcardNum);
//		hashMap.put("headPhotoUrl", "暂无");
//		hashMap.put("birthdate", sdf.parse(birthdate));
//		hashMap.put("gender", gender);
//		hashMap.put("nation", nation);
//		hashMap.put("educationDegree", educationDegree);
//		hashMap.put("origin", origin);
//		hashMap.put("occupation", occupation);
//		hashMap.put("mobilephone", mobilephone);
//		hashMap.put("telephone", telephone);
//		hashMap.put("height", height);
//		hashMap.put("bloodType", bloodType);
//		hashMap.put("shape", shape);
//		hashMap.put("entryUnit", user.getPoliceStation());
//		hashMap.put("entry", uid);
//		hashMap.put("entryName", user.getName());
//		hashMap.put("suspectClass", suspectClass);
//		hashMap.put("sourceType","2102");
        String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/save", etSuspectObj);
        return JSONObject.fromObject(jsonStr);
    }

    /*
     * 删除
     */
    @RequestMapping("/handlerdelete")
    @ResponseBody
    public String delete(HttpServletRequest request) {
        String id = request.getParameter("id");
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        String serviceUrl = "suspect/delete";
        String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);
        return result;
    }

    /*
     * 删除恢复
     */
    @RequestMapping("/deleteReply")
    @ResponseBody
    public String deleteReply(HttpServletRequest request) {
        String id = request.getParameter("id");
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("isAbandon", 0);
        String serviceUrl = "suspect/edit";
        String result = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, serviceUrl, map);
        return result;
    }

    /*
     * 更新
     */
    @RequestMapping("/handlerupdate")
    public String update(HttpSession httpSession, HttpServletRequest request, String id, String name, String byname, String idcardNum,
                         String birthdate, String gender, String nation, String educationDegree, String origin, String occupation, String height, String shape,
                         String bloodType, String mobilephone, String telephone, String suspectClass, String entryUnitName, String entryUnit) throws ParseException {
        //获取User 的ID
        String uid = (String) request.getAttribute("uid");
        //获取User 的对象
        SysUser user = (SysUser) request.getAttribute("user");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(birthdate);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", id);
        hashMap.put("name", name);
        hashMap.put("byname", byname);
        hashMap.put("idcardNum", idcardNum);
        hashMap.put("birthdate", parse);
        hashMap.put("gender", gender);
        hashMap.put("nation", nation);
        hashMap.put("educationDegree", educationDegree);
        hashMap.put("origin", origin);
        hashMap.put("occupation", occupation);
        hashMap.put("mobilephone", mobilephone);
        hashMap.put("telephone", telephone);
        hashMap.put("height", height);
        hashMap.put("bloodType", bloodType);
        hashMap.put("shape", shape);
        hashMap.put("entry", uid);
        hashMap.put("entryName", user.getName());
        hashMap.put("suspectClass", suspectClass);
        String s = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/edit", hashMap);
        return s;
    }

    /*
     * 查询
     */
    @RequestMapping("/handlerfind")
    @ResponseBody
    public String find(HttpSession httpSession, HttpServletRequest request, String id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if(id!=null){
            hashMap.put("id", id);
        }
        String s = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/list", hashMap);
        return s;

	}

	/*
	 * 查询
	 */
	@RequestMapping("/listAllForOperator")
	@ResponseBody
	public String listAllForOperator(HttpSession httpSession, HttpServletRequest request,String id) {
		HashMap<String, Object> hashMap = new HashMap<>();
		String gangId=request.getParameter("gangId");
		String searchValue=request.getParameter("searchValue");
		SysUser user = (SysUser) request.getAttribute("user");
		hashMap.put("userId", user.getId());
		hashMap.put("gangId", gangId);
		hashMap.put("searchValue", searchValue);
		String s = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/listAllForOperator", hashMap);
		return s;

	}

    /*
     * 多条件查询
     */
    @RequestMapping("/handlerfindall")
    @ResponseBody
    public DatatablesResponse findAll(HttpSession httpSession, HttpServletRequest request,
                                      @RequestParam(value = "search[value]") String search,
                                      @RequestParam(required = true, defaultValue = "1") Integer start,
                                      @RequestParam(required = false, defaultValue = "10") Integer length) {
        EtSuspect etSuspect = GsonUtils.toBean(search, EtSuspect.class);
        //获取User 的对象
        SysUser user = (SysUser) request.getAttribute("user");
        String policeStation = user.getPoliceStation();
        if (policeStation.length() > 6) {
            String substring = policeStation.substring(0, 6);
            etSuspect.setEntryUnit(substring);
        } else {
            etSuspect.setEntryUnit(policeStation);
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
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/listAll", pageVOObj);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) jsonObject.get("data");
            pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
        DatatablesResponse<EtSuspect> resp = DatatablesResponse.build(dataSet);
        return resp;
    }

    @RequestMapping("/handlerfindrecylist")
    @ResponseBody
    public DatatablesResponse findRecyList(HttpSession httpSession, HttpServletRequest request,
                                           @RequestParam(required = true, defaultValue = "1") Integer start,
                                           @RequestParam(required = false, defaultValue = "10") Integer length) throws Exception {
        DataSet dataSet = new DataSet(new ArrayList(), 0L, 0L);
        DatatablesResponse<EtSuspect> resp = DatatablesResponse.build(dataSet);
        return resp;
    }

    /*
     * 多条件查询
     */
    @RequestMapping("/handlerfindlist")
    @ResponseBody
    public DatatablesResponse findList(HttpSession httpSession, HttpServletRequest request,
                                       @RequestParam(required = true, defaultValue = "1") Integer start,
                                       @RequestParam(required = false, defaultValue = "10") Integer length) throws Exception {
        //获取User 的对象
        SysUser user = (SysUser) request.getAttribute("user");
        EtSuspect etSuspect = new EtSuspect();
        String name = request.getParameter("name");
        if (name != null && !"".equals(name)) {
            etSuspect.setName(name);
        }
        String age = request.getParameter("age");
        if (age != null && !"".equals(age)) {
            etSuspect.setAge(Integer.parseInt(age));
        }
        String endTime = request.getParameter("endTime");
        if (endTime != null && !"".equals(endTime)) {
            etSuspect.setEndTime(endTime);
        }
        String startTime = request.getParameter("startTime");
        if (startTime != null && !"".equals(startTime)) {
            etSuspect.setStartTime(startTime);
        }
        String idcardNum = request.getParameter("idcardNum");
        if (idcardNum != null && !"".equals(idcardNum)) {
            etSuspect.setIdcardNum(idcardNum);
        }
        String suspectClass = request.getParameter("suspectClass");
        if (suspectClass != null && !"".equals(suspectClass)) {
            etSuspect.setSuspectClass(Integer.parseInt(suspectClass));
        }
        String suspectLevel = request.getParameter("suspectLevel");
        if (suspectLevel != null && !"".equals(suspectLevel)) {
            etSuspect.setSuspectLevel(Integer.parseInt(suspectLevel));
        }
        String suspectId = request.getParameter("suspectId");
        if (suspectId != null && !"".equals(suspectId)) {
            etSuspect.setSuspectId(suspectId);
        }
        String entryUnit = request.getParameter("entryUnit");
        //如果用户未输入所属机构代码，默认查询登录用户所属区的重点人员
        etSuspect.setEntry(user.getId());

        String isArchive = request.getParameter("isArchive");

        if (isArchive != null && !"".equals(isArchive)) {
            etSuspect.setIsArchive(Integer.parseInt(isArchive));
        }
        String isAbandon = request.getParameter("isAbandon");
        if (isAbandon != null && !"".equals(isAbandon)) {
            etSuspect.setIsAbandon(Integer.parseInt(isAbandon));
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
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/listAll", pageVOObj);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) jsonObject.get("data");
            pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
        DatatablesResponse<EtSuspect> resp = DatatablesResponse.build(dataSet);
        return resp;
    }

    /*
     * 查询话单记录查询
     */
    @RequestMapping("/handlerfindticket")
    @ResponseBody
    public DatatablesResponse findTicket(HttpSession httpSession, HttpServletRequest request,
                                         @RequestParam(required = true, defaultValue = "1") Integer start,
                                         @RequestParam(required = false, defaultValue = "10") Integer length) throws Exception {
        //获取User 的对象
        SysUser user = (SysUser) request.getAttribute("user");
        EtTicket etTicket = new EtTicket();
        String mobilephone = request.getParameter("mobilephone");
        if (mobilephone != null && !"".equals(mobilephone)) {
            etTicket.setCallingNumber(mobilephone);
        }

        PageVO pageVO = new PageVO(start, length);
        String pageVOStr = GsonUtils.getGson().toJson(pageVO);
        JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
        if (etTicket != null) {
            String etCaseStr = GsonUtils.getGson().toJson(etTicket);
            JSONObject etCaseObj = JSONObject.fromObject(etCaseStr);
            pageVOObj.putAll(etCaseObj);
        }
        PageHelpVO pageHelpVO = null;

        try {
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/findticket", pageVOObj);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) jsonObject.get("data");
            pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
        DatatablesResponse<EtTicket> resp = DatatablesResponse.build(dataSet);
        return resp;
    }


    /*
     * 更改状态
     */
    @RequestMapping("/handlereditstatus")
    @ResponseBody
    public String editStatus(HttpSession httpSession, HttpServletRequest request, String id, String isArchive) {
        String optUnitCode="";
        String optUnitName="";
        
        HashMap<String, Object> hashMap = new HashMap<>();
        //获取User 的对象
        SysUser user = (SysUser) request.getAttribute("user");
        if (user.getLevel().equals("1"))
        {
            optUnitCode=user.getProvince();
            optUnitName=user.getProvinceName();
        }
        {
             optUnitCode=user.getCity();
             optUnitName=user.getCityName();
        }
        if (user.getLevel().equals("3"))
        {
            optUnitCode=user.getArea();
            optUnitName=user.getAreaName();
        }
        if (user.getLevel().equals("4"))
        {
            optUnitCode=user.getPoliceStation();
            optUnitName=user.getPoliceStationName();
        }
        hashMap.put("id", id);
        hashMap.put("isArchive", isArchive);
        hashMap.put("optUnitCode", user.getPoliceStation());
        hashMap.put("optUnitName", user.getPoliceStationName());
        hashMap.put("optPCode", user.getId());
        hashMap.put("optPName", user.getName());
        hashMap.put("optTime", new Date());
        String s = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/editstatus", hashMap);
        return s;
    }

    /*
     * 查询积分信息
     */
    @RequestMapping("/findSuspectIntegralforIdCard")
    @ResponseBody
    public String findSuspectIntegralforIdCard(HttpSession httpSession, HttpServletRequest request, String idcardNum) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("idcardNum", idcardNum);
        //String s = HttpClientUtil.postPack("suspect", "integral/findSuspectIntegralforIdCard", hashMap);
        String servicesRequest = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_INTEGRAL, "integral/findSuspectIntegralforIdCard", hashMap);
        return servicesRequest;
    }

    /*
     * 可选择的树形结构列表
     */
    @RequestMapping(value = "/listSelectableTreeNodes", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String findSelectableListTreeNodes(HttpSession httpSession, HttpServletRequest request, String gangId, Integer paramStr) {
        //获取User 的对象
        SysUser user = (SysUser) request.getAttribute("user");
        if (user == null) {
//			LogUtil.error("用户信息不明");
            return "null";
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("userId", user.getId());
        hashMap.put("gangId", gangId);
        hashMap.put("paramStr", paramStr);
        String servicesRequest = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/listSelectableTreeNodes", hashMap);
//        ResponseObjectVO<String> responseObjectVO = new ResponseObjectVO(ResponseObjectVO.SUCCESS,"查询成功",servicesRequest);
        return servicesRequest;
    }

    /*
     * 人员树形结构列表
     */
    @RequestMapping(value = "/listTreeNodes", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String findListTreeNodes(HttpSession httpSession, HttpServletRequest request, String paramStr, Integer treeType) {
        //获取User 的对象
        SysUser user = (SysUser) request.getAttribute("user");
        if (user == null) {
//			LogUtil.error("用户信息不明");
            return "null";
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("paramStr", paramStr);
        hashMap.put("treeType", treeType);
        hashMap.put("userId",user.getId());
        hashMap.put("level", user.getLevel());
        //是领导时，可以看本单位中所有数据
        if("1".equals(user.getJobs())||Integer.parseInt(user.getJobs())==1){
            hashMap.put("entryUnit", (user.getLevel()==null||"".equals(user.getLevel())?"":"1".equals(user.getLevel())?user.getProvince():"2".equals(user.getLevel())?user.getCity():"3".equals(user.getLevel())?user.getArea():user.getPoliceStation()));
        }else{
            hashMap.put("entryUnit", "");
        }
        String servicesRequest = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/listTreeNodes", hashMap);
//        ResponseObjectVO<String> responseObjectVO = new ResponseObjectVO(ResponseObjectVO.SUCCESS,"查询成功",servicesRequest);
		return servicesRequest;
	}

	/*
	 * 多条件查询,重点人员警官权限授予
	 *
	 *   /suspecthandler/suspectAndpoliceList?search[value]={"suspectId":"160D4A3827BF01"}
	 */
	@RequestMapping(value = "/suspectAndpoliceList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DatatablesResponse suspectAndpoliceList(HttpSession httpSession, HttpServletRequest request,
									  @RequestParam(value = "search[value]") String search,
									  @RequestParam(required = true, defaultValue = "1") Integer start,
									  @RequestParam(required = false, defaultValue = "10") Integer length,
												   String suspectId,String nameOrIdcard) {
		EtSuspectDTO etSuspect = GsonUtils.toBean(search, EtSuspectDTO.class);
		if (etSuspect==null){
			etSuspect=new EtSuspectDTO();
		}
        //获取User 的对象
        SysUser user = (SysUser)request.getAttribute("user");
		etSuspect.setSuspectId(suspectId);
		etSuspect.setNameOrIdcard(nameOrIdcard);
        etSuspect.setUserId(user.getId());
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
			String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/suspectAndpoliceList", pageVOObj);
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			JSONObject respContent = (JSONObject) jsonObject.get("data");
			pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
		DatatablesResponse<OperPermissionVO> resp = DatatablesResponse.build(dataSet);
		return resp;
	}

	/*
	 * 更改状态重点人员的警官关系数据
	 *
	 * 	/suspecthandler/editSuspectAndpolice?suspectNo=111&userNo=2&type=2
	 */
	@RequestMapping("/editSuspectAndpolice")
	@ResponseBody
	public String  editSuspectAndpolice(HttpSession httpSession, HttpServletRequest request,String suspectNo,String userNo,
										String type) {
		HashMap<String, Object> hashMap = new HashMap<>();
		//获取User 的对象
		SysUser user = (SysUser)request.getAttribute("user");
		hashMap.put("suspectNo", suspectNo);
		hashMap.put("userNo", userNo);
		hashMap.put("type", type);
//		hashMap.put("sign", sign);
		String s = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/editSuspectAndpolice", hashMap);
		return s;
	}


	/*
	 * 重点人员多条件查询
	 *
	 * /suspecthandler/findSuspectList?search[value]={}
	 */
	@RequestMapping(value = "/findSuspectList",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DatatablesResponse findSuspectList(HttpSession httpSession, HttpServletRequest request,
											  @RequestParam(value = "search[value]") String search,
											  @RequestParam(required = true, defaultValue = "1") Integer start,
											  @RequestParam(required = false, defaultValue = "10") Integer length,
											  String  nameOrbyNameOrIdcardNum) throws Exception {
		//获取User 的对象
		SysUser user = (SysUser)request.getAttribute("user");
		EtSuspectDTO etSuspect = GsonUtils.toBean(search, EtSuspectDTO.class);
		if (etSuspect==null){
			etSuspect=new EtSuspectDTO();
		}
		etSuspect.setNameOrbyNameOrIdcardNum(nameOrbyNameOrIdcardNum);
        etSuspect.setUserId(user.getId());//嫌疑人录入数据
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
			String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/findSuspectList", pageVOObj);
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			JSONObject respContent = (JSONObject) jsonObject.get("data");
			pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
		DatatablesResponse<OperPermissionVO> resp = DatatablesResponse.build(dataSet);
		return resp;
	}

	/*
	 * 全息档案授权管理
	 */
	@RequestMapping(value = "/findSuspectVO",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DatatablesResponse etSuspectAlarm(HttpSession httpSession, HttpServletRequest request,
            @RequestParam(value = "search[value]") String search,
            @RequestParam(required = true, defaultValue = "1") Integer start,
            @RequestParam(required = false, defaultValue = "10") Integer length,String suspectId) {

	    PageVO pageVO = new PageVO(start, length);
        String pageVOStr = GsonUtils.getGson().toJson(pageVO);
        JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
        EtSuspect etSuspect=new EtSuspect();
        etSuspect.setSuspectId(suspectId);
        if (etSuspect != null) {
            String etCaseStr = GsonUtils.getGson().toJson(etSuspect);
            JSONObject etCaseObj = JSONObject.fromObject(etCaseStr);
            pageVOObj.putAll(etCaseObj);
        }
        PageHelpVO pageHelpVO = null;

        try {
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/findUserListForSuspect", pageVOObj);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) jsonObject.get("data");
            pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
        DatatablesResponse<OperPermissionVO> resp = DatatablesResponse.build(dataSet);
        return resp;

	}

	/*
	 * 警情关系确认和标记方法接口
	 */
	@RequestMapping(value = "/etSuspectAlarm", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String etSuspectAlarm(HttpSession httpSession, HttpServletRequest request, String suspectId, String alarmId,
			Integer lable, Integer relation, String demo,Integer type) {

		// 获取User 的对象
		SysUser user = (SysUser) request.getAttribute("user");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		RlSuspectAlarm rlSuspectAlarm = new RlSuspectAlarm();
		rlSuspectAlarm.setAlarmId(alarmId);
		rlSuspectAlarm.setId(IDGenerator.getorderNo());
		rlSuspectAlarm.setSuspectId(suspectId);
		rlSuspectAlarm.setLabel(lable);
		rlSuspectAlarm.setRelation(relation);
		rlSuspectAlarm.setDemo(demo);
		rlSuspectAlarm.setOptPCode(user.getId());
		rlSuspectAlarm.setOptPName(user.getName());
		String rlSuspectAlarmstr = GsonUtils.getGson().toJson(rlSuspectAlarm);
		JSONObject rlSuspectAlarmstrOBJ = JSONObject.fromObject(rlSuspectAlarmstr);
		String s ="";
		if (type==1||type==2) {
			 s = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/etSuspectAlarmQR",
					rlSuspectAlarmstrOBJ);
		}
		if (type==3) {
			s = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/etSuspectAlarm",
					rlSuspectAlarmstrOBJ);
		}
		return s;
	}

	/*
	 * 案件关系确认方法接口
	 */
	@RequestMapping(value = "/etSuspectCase", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String etSuspectCase(HttpSession httpSession, HttpServletRequest request,String id, String suspectId, String caseId,
			Integer lable, Integer relation, String demo) {
		SysUser user = (SysUser) request.getAttribute("user");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		RlSuspectCase rlSuspectCase = new RlSuspectCase();
		rlSuspectCase.setCaseId(caseId);
		rlSuspectCase.setSuspectId(suspectId);
		rlSuspectCase.setLabel(lable);
		rlSuspectCase.setRelation(relation);
		rlSuspectCase.setOptPCode(user.getId());
		rlSuspectCase.setOptPName(user.getName());
		rlSuspectCase.setDemo(demo);

		String rlSuspectCasestr = GsonUtils.getGson().toJson(rlSuspectCase);
		JSONObject rlSuspectCasestrOBJ = JSONObject.fromObject(rlSuspectCasestr);
		String s ="";
			 s = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/etSuspectCase",
				rlSuspectCasestrOBJ);
		return s;
	}


    /*
     * 车辆记录查询
     */
    @RequestMapping(value = "/handlerfindVehicle", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DatatablesResponse findVehicle(HttpSession httpSession, HttpServletRequest request,
                                          @RequestParam(required = true, defaultValue = "1") Integer start,
                                          @RequestParam(required = false, defaultValue = "10") Integer length) throws Exception {
        EtVehicle etVehicle = null;
        String suspectNo = (String) request.getParameter("suspectNo");
        PageHelpVO<EtTicket> pageHelpVO = new PageHelpVO<EtTicket>();

        if (StringUtils.isBlank(suspectNo)) {
            DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
            DatatablesResponse<EtTicket> resp = DatatablesResponse.build(dataSet);
            return resp;
        } else {
            etVehicle = new EtVehicle();
            etVehicle.setSuspectNo(suspectNo);
        }

        PageVO pageVO = new PageVO(start, length);
        String pageVOStr = GsonUtils.getGson().toJson(pageVO);
        JSONObject pageVOObj = JSONObject.fromObject(pageVOStr);
        if (etVehicle != null) {
            String str1 = GsonUtils.getGson().toJson(etVehicle);
            JSONObject obj1 = JSONObject.fromObject(str1);
            pageVOObj.putAll(obj1);
        }

        try {
            String jsonStr = APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/findVehicleForSuspect", pageVOObj);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) jsonObject.get("data");
            pageHelpVO = GsonUtils.toBean(respContent.toString(), PageHelpVO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataSet dataSet = new DataSet(pageHelpVO.getList(), pageHelpVO.getTotal(), pageHelpVO.getTotal());
        DatatablesResponse<EtTicket> resp = DatatablesResponse.build(dataSet);
        return resp;
    }

    /**
     * 查询单个重点人员信息
     * 	/suspecthandler/findPersonById?id=16169FC6C33F02
     * @return
     */
    @RequestMapping(value="/findPersonById",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public Map<String,Object> findPersonById(HttpSession httpSession, HttpServletRequest request,String id,String suspectId){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("suspectId", suspectId);
        String jsonStr="";
        try {
            jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/findPersonById", jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("data",jsonStr);
        return map;
    }

	/**
	 * 查询重点人员查看权限
	 * @return
	 */
	@RequestMapping(value="/suspectRole",produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public JSONObject suspectRole(HttpSession httpSession, HttpServletRequest request,String suspectId){
        SysUser user = (SysUser) request.getAttribute("user");
		JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", user.getId());
        jsonObject.put("suspectId", suspectId);
		String jsonStr="";
		try {
			jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/suspectRole", jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
        //json字符串转换为json对象
        return JSONObject.fromObject(jsonStr);
	}


    /**
     * @Author: sky
     * @Description:获取团伙数量  组织数
     * @Date: 10:01 2018/5/24
     * @param: httpSession
    request
     */
    @RequestMapping(value="/getGangNum",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public Map<String,Object> getGangNum(HttpSession httpSession, HttpServletRequest request){
        JSONObject object = new JSONObject();
        Map<String,Object> resultMap = new HashMap<>();
        try {
            String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "etGangCon/getGangNum", object);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) jsonObject.get("data");
            resultMap.put("gangNum",respContent.get("gangNum"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * @Author: sky
     * @Description:获取重点人员数量  人员数
     * @Date: 10:01 2018/5/24
     * @param: httpSession
    request
     */
    @RequestMapping(value="/getSuspectNum",produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public Map<String,Object> getSuspectNum(HttpSession httpSession, HttpServletRequest request){
        JSONObject object = new JSONObject();
        Map<String,Object> resultMap = new HashMap<>();
        try {
            String jsonStr =APIClientRequest.servicesRequest(Const.SUB_SYSTEM_CODE_SUSPECT, "suspect/getSuspectNum", object);
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject respContent = (JSONObject) jsonObject.get("data");
            resultMap.put("suspectNum",respContent.get("suspectNum"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
