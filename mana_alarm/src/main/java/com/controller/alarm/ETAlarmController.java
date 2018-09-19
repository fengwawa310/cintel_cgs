package com.controller.alarm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.alarm.EtAlarm;
import com.entity.suspect.EtSuspect;
import com.service.alarm.ETAlarmService;

@Controller
@RequestMapping("/ETAlarmCon")
public class ETAlarmController {

	@Resource
	private ETAlarmService eTAlarmService;

	@RequestMapping(value = "/insertEtAlarm", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int insertEtAlarm(EtAlarm etAlarm) {
		return eTAlarmService.insertEtAlarm(etAlarm);
	}

	@RequestMapping(value = "/updateEtAlarm", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int updateEtAlarm(EtAlarm etAlarm) {
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	    String format = sdf.format(etAlarm.getAlarmTime());
//            etAlarm.setAlarmTime(format);
		return eTAlarmService.updateEtAlarm(etAlarm);
	}

	/*
	 * 实体删除，数据将不会恢复
	 */
	@RequestMapping(value = "/deleteByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int deleteByPrimaryKey(String id) {
		return eTAlarmService.deleteByPrimaryKey(id);
	}

	/*
	 * 状态删除，更新数据删除字段状态 更新 is_abandon 字段成 1: '删除标识：0未删除；1已删除；默认0',
	 */
	@RequestMapping(value = "/abandonByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int abandonByPrimaryKey(String id) {
		return eTAlarmService.abandonByPrimaryKey(id);
	}

	/*
	 * 状态删除恢复，更新数据删除字段状态 更新 is_abandon 字段成 0: '删除标识：0未删除；1已删除；默认0',
	 */
	@RequestMapping(value = "/alarmDeleteReplyById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int alarmDeleteReplyById(String id) {
		return eTAlarmService.alarmDeleteReplyById(id);
	}

	/*
	 * 根据Id查询相关警情
	 */
	@RequestMapping(value = "/selectByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public EtAlarm selectByPrimaryKey(String alarmNo) {
		return eTAlarmService.selectByPrimaryKey(alarmNo);
	}

	/*
	 * 根据条件 查询所有未删除警情数据List
	 */
	@RequestMapping(value = "/selectEtAlarmList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<EtAlarm> selectEtAlarmList(EtAlarm etAlarm) {
		return eTAlarmService.selectEtAlarmList(etAlarm);
	}

	/*
	 * 根据条件 查询所有未删除警情数据List
	 */
	@RequestMapping(value = "/countEtAlarmList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<EtAlarm> countEtAlarmList(EtAlarm etAlarm) {
		return eTAlarmService.countEtAlarmList(etAlarm);
	}

	/*
	 * 根据条件 查询所有删除到回收站的警情数据List
	 */
	@RequestMapping(value = "/selectDelEtAlarmList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<EtAlarm> selectDelEtAlarmList(EtAlarm etAlarm) {
		return eTAlarmService.selectDelEtAlarmList(etAlarm);
	}

	/*
	 * 根据条件 查询所有删除到回收站的警情数据List统计
	 */
	@RequestMapping(value = "/countDelEtAlarmList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<EtAlarm> countDelEtAlarmList(EtAlarm etAlarm) {
		return eTAlarmService.countDelEtAlarmList(etAlarm);
	}

	/*
	 * 根据重点人员，查询重点人员已经人工确认过关联的警情列表
	 *
	 */
	@RequestMapping(value = "/suspectAlarmOpen", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageHelpVO suspectAlarmOpen(HttpSession httpSession, HttpServletRequest request, PageVO pageVO,
			EtSuspect etSuspect) {
		PageHelpVO pageHelpVO = null;
		try {
			pageHelpVO = eTAlarmService.suspectAlarmOpen(pageVO, etSuspect.getSuspectId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageHelpVO;

	}

	/*
	 * 根据重点人员，查询重点人员已经人工没有确认过的警情列表
	 *
	 */

	@RequestMapping(value = "/suspectAlarmClose", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageHelpVO suspectAlarmClose(HttpSession httpSession, HttpServletRequest request, PageVO pageVO,
			EtSuspect etSuspect) {

		PageHelpVO pageHelpVO = null;
		try {
			pageHelpVO = eTAlarmService.suspectAlarmClose(pageVO, etSuspect.getSuspectId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageHelpVO;
	}

}
