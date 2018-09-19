package com.mapper.taskStu;

import com.entity.taskStu.ApCaseSeriesResult;

import java.util.List;
import java.util.Map;

public interface ApCaseSeriesResultMapper {

    int deleteByPrimaryKey(String id);

    int insert(List<ApCaseSeriesResult> list);

    int insertSelective(ApCaseSeriesResult record);

    ApCaseSeriesResult selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ApCaseSeriesResult record);

    int updateByPrimaryKey(ApCaseSeriesResult record);
    /*查询案件串并结果*/
    List<ApCaseSeriesResult> selectSeriesResult(String eventId);
}