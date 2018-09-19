package com.mapper.taskStu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.entity.taskStu.RelationalPeople;

public interface RelationalPeopleMapper {
    int deleteByPrimaryKey(String id);

    int insert(RelationalPeople record);

    int insertSelective(RelationalPeople record);

    RelationalPeople selectByPrimaryKey(String id);
    
    List<RelationalPeople> selectByGangId(HashMap<String, Object> map);

    int updateByPrimaryKeySelective(RelationalPeople record);

    int updateByPrimaryKey(RelationalPeople record);

	int insertExcelRPData(@Param("listmap") List<Map<String, String>> listmap);

	long countSelectByGangId(HashMap<String, Object> map);

	List<RelationalPeople> selectBySuspectId(HashMap<String, Object> map);

	long countSelectBySuspectId(HashMap<String, Object> map);
}