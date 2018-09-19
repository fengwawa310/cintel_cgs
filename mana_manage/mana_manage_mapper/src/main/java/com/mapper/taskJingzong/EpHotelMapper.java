package com.mapper.taskJingzong;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.task.EpHotel;

public interface EpHotelMapper {
    int deleteByPrimaryKey(String hnohotel);

    int insert(EpHotel record);

    int insertSelective(EpHotel record);

    EpHotel selectByPrimaryKey(String hnohotel);

    int updateByPrimaryKeySelective(EpHotel record);

    int updateByPrimaryKey(EpHotel record);

	int insertEpHotelList(@Param("ehlist") List<EpHotel> ehlist);

	String findLastZyRksj();

	void delHotelDistanceData();

	void calculationAndStorage();

	EpHotel selectHotelByPersonId(String id);
}