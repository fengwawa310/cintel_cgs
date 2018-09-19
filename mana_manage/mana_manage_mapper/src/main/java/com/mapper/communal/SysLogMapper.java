package com.mapper.communal;

import java.util.List;

import com.entity.sys.SysLog;

public interface SysLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysLog record);

    int insertSelective(SysLog syslog);

    SysLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKeyWithBLOBs(SysLog record);

    int updateByPrimaryKey(SysLog record);

	List<SysLog> findSysLoglList(SysLog sysLog);

	List<SysLog> countFindSysLoglList(SysLog sysLog);

//	查询日志列表
    List<String> findLogList();
}