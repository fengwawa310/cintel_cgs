package com.mapper.taskJingzong;


import com.entity.task.EpHotelDistance;

import java.util.List;

public interface EpHotelDistanceMapper {
    int insert(EpHotelDistance record);

    int insertSelective(EpHotelDistance record);

    //根据酒店id获取附近酒店
    List<EpHotelDistance> selectByHotelId(String hotelId);
}