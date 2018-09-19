package com.mapper.taskJingzong;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.entity.task.EpAsjBl;

public interface EpAsjBlMapper {
    int deleteByPrimaryKey(String systemid);

    int insert(EpAsjBl record);

    int insertSelective(EpAsjBl record);

    EpAsjBl selectByPrimaryKey(String systemid);

    int updateByPrimaryKeySelective(EpAsjBl record);

    int updateByPrimaryKeyWithBLOBs(EpAsjBl record);

    int updateByPrimaryKey(EpAsjBl record);

	int insertEpAsjBlList(@Param("epAsjBlList") List<EpAsjBl> epAsjBlList);

	String findNearlyDateTime();
	
	List<EpAsjBl> findNotesList(Map map);
}