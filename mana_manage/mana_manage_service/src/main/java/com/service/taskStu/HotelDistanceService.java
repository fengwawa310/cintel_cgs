package com.service.taskStu;

import com.entity.task.EpHotelDistance;

import java.util.List;

/**
 * Created by sky on 2018/4/3.
 */
public interface HotelDistanceService {

    //根据酒店id获取附近酒店
    List<EpHotelDistance> selectByHotelId(String hotelId);

}
