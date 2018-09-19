package com.sys.controller.log;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.sys.SysLog;
import com.service.communal.SysLogService;

@Controller
@RequestMapping("/sysLogCon")
public class SysLogController {
	
	@Resource 
	private SysLogService sysLogService;
	
/*	@RequestMapping(value = "insert", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int insertSysLog(SysLog sysLog){
		return sysLogService.insertSysLog(sysLog);
	}*/
	
	@RequestMapping(value = "/findSysLoglList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<SysLog> findSysLoglList(SysLog sysLog) {
		return sysLogService.findSysLoglList(sysLog);
	}

	@RequestMapping(value = "/countFindSysLoglList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<SysLog> countFindSysLoglList(SysLog sysLog) {
		return sysLogService.countFindSysLoglList(sysLog);
	}

	@RequestMapping(value = "/findLogList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<String> findLogList(HttpServletRequest request){
		List<String> logs = new ArrayList<>();
		logs = sysLogService.findLogList();
		return logs;
	}

}
