package com.mapper.taskStu;

import com.entity.taskStu.ApCaseSeriesEvent;

import java.util.List;

public interface ApCaseSeriesEventMapper {
    int insert(ApCaseSeriesEvent record);

    int deleteByPrimaryKey(String id);

    ApCaseSeriesEvent selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ApCaseSeriesEvent record);

    int updateByPrimaryKey(ApCaseSeriesEvent record);
    /*查询案件串并事件*/
    List<ApCaseSeriesEvent> selectSeriesEvent(String caseNo);
}