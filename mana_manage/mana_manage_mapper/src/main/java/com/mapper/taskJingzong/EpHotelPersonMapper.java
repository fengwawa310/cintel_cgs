package com.mapper.taskJingzong;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.entity.task.EpHotelPerson;

public interface EpHotelPersonMapper {
    int deleteByPrimaryKey(String ccode);

    int insert(EpHotelPerson record);

    int insertSelective(EpHotelPerson record);

    EpHotelPerson selectByPrimaryKey(String ccode);

    int updateByPrimaryKeySelective(EpHotelPerson record);

    int updateByPrimaryKey(EpHotelPerson record);

	int insertEpHotelPersonList(@Param("ehplist")  List<EpHotelPerson> ehplist);

	String findLastRksj();

    List<EpHotelPerson> findHotelPersonList(Map map);
}