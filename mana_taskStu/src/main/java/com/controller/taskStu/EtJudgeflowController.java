package com.controller.taskStu;

import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.common.enums.EnumTypeVO;
import com.common.enums.EtJudgeEnumType;
import com.common.utils.IDGenerator;
import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.common.utils.httpclient.HttpClientUtil;
import com.entity.DicUnit;
import com.entity.caseInfo.CaseSeries;
import com.entity.sys.SysUser;
import com.entity.taskStu.ApJudgelog;
import com.request.sys.PageVoReq;
import com.service.communal.DicUtilsService;
import com.service.sys.SysUserService;
import com.service.taskStu.ApJudgelogService;
import com.vo.taskStu.EtJudgeflowVo;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.taskStu.EtJudgeflow;
import com.service.taskStu.EtJudgeflowService;

@Controller
@RequestMapping("etJudgeflowCon")
public class EtJudgeflowController {
	
	@Resource
	private EtJudgeflowService etJudgeflowService;

	@Resource
	private ApJudgelogService apJudgelogService;

	@Resource
	private SysUserService sysUserService;

	@Resource
	private DicUtilsService dicUtilsService;

	@RequestMapping(value = "/insertEJF", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int insertEJF(EtJudgeflow etJudgeflow) {
		return etJudgeflowService.insert(etJudgeflow);
	}
	
	@RequestMapping(value = "/selectEJFList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<EtJudgeflow> selectEJFList(@RequestParam HashMap<String, Object> map) {
		return etJudgeflowService.selectEJFList(map);
	}
	
	@RequestMapping(value = "/countSelectEJFList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<EtJudgeflow> countSelectEJFList(@RequestParam HashMap<String, Object> map) {
		return etJudgeflowService.countSelectEJFList(map);
	}
	
	@RequestMapping(value = "/selectEJFListFastEn", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<EtJudgeflow> selectEJFListFastEn(@RequestParam HashMap<String, Object> map) {
		return etJudgeflowService.selectEJFListFastEn(map);
	}
	
	@RequestMapping(value = "/countSelectEJFListFastEn", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<EtJudgeflow> countSelectEJFListFastEn(@RequestParam HashMap<String, Object> map) {
		return etJudgeflowService.countSelectEJFListFastEn(map);
	}


	/**
	 *
	 * 按id查询公告详情
	 * @return
	 */
	@RequestMapping(value="/selectById",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public EtJudgeflowVo selectById(HttpSession httpSession, HttpServletRequest request, String id){
		EtJudgeflow etJudgeflow = new EtJudgeflow();
		etJudgeflow = etJudgeflowService.selectByPrimaryKey(id);
		EtJudgeflowVo etJudgeflowVo= EtJudgeflowVo.ValueOf(etJudgeflow);
		EtJudgeflowVo etJudgeflowVo1 = EtjudgeFlowStateMethod(etJudgeflowVo);
		return etJudgeflowVo1;
	}
	/*判断案警的流转状态*/
	private EtJudgeflowVo EtjudgeFlowStateMethod(EtJudgeflowVo etJudgeflowVo) {
		Integer flowState = etJudgeflowVo.getFlowState();
		Integer shState=null;//审核状态
		if (flowState== EtJudgeEnumType.NO_SH.getValue()){
			shState=EtJudgeEnumType.NO_SH.getValue();
		}else if (flowState==EtJudgeEnumType.HAS_PASS.getValue()){//审核通过
			shState=EtJudgeEnumType.HAS_PASS.getValue();
		}else if (flowState==EtJudgeEnumType.NO_PASS.getValue()){//审核未通过
			shState=EtJudgeEnumType.NO_PASS.getValue();
		}else if(flowState>EtJudgeEnumType.NO_PASS.getValue()) {//其它
			shState=EtJudgeEnumType.HAS_PASS.getValue();
		}
		Integer qsState=null;//签收
		if (flowState<EtJudgeEnumType.DQS.getValue()){//不到签收没有数据
			qsState=null;
		}else if (flowState==EtJudgeEnumType.DQS.getValue()){//待签收
			qsState=EtJudgeEnumType.DQS.getValue();
		}else if (flowState==EtJudgeEnumType.HAS_QS.getValue()){//已签收
			qsState=EtJudgeEnumType.HAS_QS.getValue();
		}else if(flowState==EtJudgeEnumType.JJQS.getValue()) {//拒签收
			qsState=EtJudgeEnumType.JJQS.getValue();
		}else if(flowState==EtJudgeEnumType.HAS_YYP.getValue()) {//已研判
			qsState=EtJudgeEnumType.HAS_QS.getValue();
		}
		Integer ypState=null;//研判
		if (flowState<EtJudgeEnumType.HAS_YYP.getValue()){//不到研判没有数据
			ypState=null;
		}else if (flowState==EtJudgeEnumType.HAS_YYP.getValue()){//已研判
			ypState=EtJudgeEnumType.HAS_YYP.getValue();
		}

		EtJudgeEnumType sh = EtJudgeEnumType.valueof(shState);
		EnumTypeVO shVO = new EnumTypeVO(sh.getName(), String.valueOf(sh.getValue()));
		EnumTypeVO qsVO=null;
		if (qsState!=null) {
			EtJudgeEnumType qs = EtJudgeEnumType.valueof(qsState);
			qsVO = new EnumTypeVO(qs.getName(), String.valueOf(qs.getValue()));
		}
		EnumTypeVO ypVO=null;
		if (ypState!=null) {
			EtJudgeEnumType yp = EtJudgeEnumType.valueof(ypState);
			ypVO = new EnumTypeVO(yp.getName(), String.valueOf(yp.getValue()));
		}
		etJudgeflowVo.setShenHeState(shVO);
		etJudgeflowVo.setQianshouState(qsVO);
		etJudgeflowVo.setYanpanState(ypVO);
		return etJudgeflowVo;
	}


	/**
	 * 按用户查询公告列表
	 *
	 * 	etJudgeflowCon/selectJudgeDetailLog
	 * @return
	 */
	@RequestMapping(value="/selectJudgeDetailLog",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageHelpVO selectJudgeDetailLog(HttpSession httpSession, HttpServletRequest request, PageVoReq pageVoReq,
							   String id){

		PageVO pageVO = new PageVO(Integer.parseInt(pageVoReq.getStart()),Integer.parseInt(pageVoReq.getLength()));
		PageHelpVO pageHelpVO =null;
		try {
			pageHelpVO=etJudgeflowService.selectJudgeDetailLog(pageVO,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageHelpVO;
	}


	/**
	 *	etJudgeflowCon/updateJudgeOperType
	 * 按id查询公告详情
	 * @return
	 */
	@RequestMapping(value="/updateJudgeOperType",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateJudgeOperType(String userId, String userDicUnit,String id,String operType,String auditOpinion,String issuedUnit){
		EtJudgeflow etJudgeflow = new EtJudgeflow();
		etJudgeflow.setId(id);
		etJudgeflow.setFlowState(Integer.parseInt(operType));
		ApJudgelog apJudgelog=new ApJudgelog();//案警流程操作日志
		apJudgelog=judgeLogMeth(userId, userDicUnit, id, apJudgelog,operType,auditOpinion);//操作日志包装
		if (Integer.parseInt(operType)==EtJudgeEnumType.HAS_PASS.getValue()||Integer.parseInt(operType)==EtJudgeEnumType.NO_PASS.getValue()){//审核功能
			etJudgeflow.setAuditorUnit(userDicUnit);//主办单位编码
			etJudgeflow.setAuditorCode(userId);//审核人编码
			etJudgeflow.setAuditTime(new Date());//审核时间
			etJudgeflow.setAuditOpinion(auditOpinion);//审核意见
		}else if (Integer.parseInt(operType)==EtJudgeEnumType.DQS.getValue()){	//下载
			etJudgeflow.setIssuedUnit(issuedUnit);//下发单位--下发单位为前台页面选择
			etJudgeflow.setIssuterCode(userId);
			etJudgeflow.setIssuedTime(new Date());
		}else if (Integer.parseInt(operType)==EtJudgeEnumType.HAS_QS.getValue()){//签收
			etJudgeflow.setSignUnit(userDicUnit);
			etJudgeflow.setSignCode(userId);
			etJudgeflow.setSignTime(new Date());
			etJudgeflow.setSignOpinion(auditOpinion);//签收意见
		} else if (Integer.parseInt(operType)==EtJudgeEnumType.JJQS.getValue()){//拒绝签收
			etJudgeflow.setSignUnit(userDicUnit);
			etJudgeflow.setSignCode(userId);
			etJudgeflow.setSignTime(new Date());
			etJudgeflow.setSignOpinion(auditOpinion);//签收意见
		}else if (Integer.parseInt(operType)==EtJudgeEnumType.HAS_YYP.getValue()){//研判
			etJudgeflow.setJudgeCode(userId);
			etJudgeflow.setJudgeOpinion(auditOpinion);//签收意见
			etJudgeflow.setJudgeTime(new Date());
		}
		etJudgeflowService.updateByPrimaryKeySelective(etJudgeflow);

		apJudgelogService.insertApJudgelog(apJudgelog);
		//用戶与公告关联表
//		SysUserNotice sysUserNotice = new SysUserNotice();
//		String userNoticId = sysNoticeReq.getUserNoticId();
//		sysUserNotice.setId(userNoticId);
//		sysUserNotice.setIsRead("1");
//		int data = sysUserNoticeService.updateByPrimaryKeySelective(sysUserNotice);
		return "1";
	}

	private ApJudgelog judgeLogMeth(String userId, String userDicUnit, String id, ApJudgelog apJudgelog,String operType,String auditOpinion ) {
		apJudgelog.setId(IDGenerator.getorderNo());
		apJudgelog.setRelateNo(id);//外键
		apJudgelog.setOperatorCode(userId);
		SysUser sysUser = sysUserService.findUserById(userId);
		apJudgelog.setOperatorName(sysUser.getName());
		apJudgelog.setOperUnitCode(userDicUnit);
		DicUnit dicUnitByID = dicUtilsService.findDicUnitByID(userDicUnit);
		apJudgelog.setOperUnitName(dicUnitByID.getName());
		apJudgelog.setOperTime(new Date());
		apJudgelog.setOperType(Integer.parseInt(operType));
		apJudgelog.setRemark(auditOpinion);
		apJudgelog.setCreatTime(new Date());
		apJudgelog.setModifyTime(new Date());
		return  apJudgelog;
	}


    /**
     * 手动触发案件串并
     * @return
     */
	@RequestMapping(value="/series",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONObject selectSeries(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String,Object> paramMap ){
        JSONObject json=new JSONObject();
        Map<String,Object> map=new HashMap<>();
		map.put("OP_ID","0201");
		map.put("AJBH_M",paramMap.get("ajbh"));
		map.put("SIMILAR",paramMap.get("similar"));
        //调用贝业思串并接口
        Map<String, Object> resultMap = HttpClientUtil.sendHttpGet(paramMap.get("url").toString(),map,null);
        int code=Integer.parseInt(resultMap.get("code")+"");
        if(code==200){
            json = JSONObject.fromObject(resultMap.get("responseContent")+"");
        }
		json.put("flag",false);
		String op_id = json.get("OP_ID").toString();
		String ret_code = json.get("RET_CODE").toString();
		if(op_id.equals("0201")&&ret_code.equals("0")){
			try {
				String eventId = etJudgeflowService.insertCaseSeries(json, paramMap);
				json.put("flag",true);
				json.put("data",eventId);
			} catch (Exception e) {
				e.printStackTrace();
				json.put("msg","串并保存失败："+e);
			}
		}
        return json;
    }
	/**
	 * 查询案件串并结果
	 * @return
	 */
	@RequestMapping(value="/selectSeriesResult",produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public PageHelpVO selectSeriesResult(HttpSession httpSession, HttpServletRequest request,PageVO pageVO,String eventId){
		PageHelpVO pageHelpVO =null;
		try {
			pageHelpVO=etJudgeflowService.selectSeriesResult(pageVO,eventId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageHelpVO;
	}
	/**
	 * 查询案件串并事件
	 * @return
	 */
	@RequestMapping(value="/selectSeriesEvent",produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public PageHelpVO selectSeriesEvent(HttpSession httpSession, HttpServletRequest request,PageVO pageVO,String caseNo){
		PageHelpVO pageHelpVO =null;
		try {
			pageHelpVO=etJudgeflowService.selectSeriesEvent(pageVO,caseNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageHelpVO;
	}


	@RequestMapping(value="/result",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String,Object> selectresult(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String,Object> paramMap ){
		List< Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> obj1=new HashMap<>();
		obj1.put("AJBH","123123");
		obj1.put("SIMILAR","70");
		list.add(obj1);
		Map<String,Object> obj2=new HashMap<>();
		obj2.put("AJBH","1234567890");
		obj2.put("SIMILAR","80");
		list.add(obj2);
		Map<String,Object> json=new HashMap<>();
		json.put("OP_ID","0201");
		json.put("RET_CODE","0");
		json.put("ERR_TEXT","");
		json.put("RSP_SET",list);
		return json;
	}


}
