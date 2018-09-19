package com.mapper.taskStu;

import com.entity.taskStu.EpCaseGang;

import java.util.List;
import java.util.Map;

public interface EpCaseGangMapper {

    int insert(List<EpCaseGang> list);

    List<EpCaseGang> findImportCase(Map<String,Object> map);

    int deleteByPrimaryKey(String id);

    int insertSelective(EpCaseGang record);

    EpCaseGang selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EpCaseGang record);

    int updateByPrimaryKey(EpCaseGang record);


}