package com.service.taskStu.impl;

import com.entity.task.EpHotelDistance;
import com.mapper.taskJingzong.EpHotelDistanceMapper;
import com.service.taskStu.HotelDistanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sky on 2018/4/3.
 */
@Transactional
@Service
public class HotelDistanceServiceImp implements HotelDistanceService {

    @Resource
    EpHotelDistanceMapper epHotelDistanceMapper;

    //根据酒店id获取附近酒店
    @Override
    public List<EpHotelDistance> selectByHotelId(String hotelId) {
        return epHotelDistanceMapper.selectByHotelId(hotelId);
    }
}
