package com.service.communal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.entity.sys.SysLog;
import com.entity.sys.SysUser;

public interface SysLogService {

	int insertSysLog(String user, HttpServletRequest request,String remark);

	int insertSysLog(SysUser user, HttpServletRequest request,String remark);
	

	List<SysLog> findSysLoglList(SysLog sysLog);

	List<SysLog> countFindSysLoglList(SysLog sysLog);

//	获取日志列表
	List<String> findLogList();

}
