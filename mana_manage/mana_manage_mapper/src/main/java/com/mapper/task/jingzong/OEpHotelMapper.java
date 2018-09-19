package com.mapper.task.jingzong;

import java.util.List;
import java.util.Map;

import com.entity.task.EpHotel;

public interface OEpHotelMapper {
	EpHotel selectByPrimaryKey(String hnohotel);

	public List<EpHotel> findEpHotelData(Map<String, String> map);

	long findEpHotelCount(Map<String, String> map);
}