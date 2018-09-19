package com.service.taskStu.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.task.EpHotel;
import com.mapper.taskJingzong.EpHotelMapper;
import com.service.taskStu.HotelService;

/**
 * @author admin
 * @create 2018-03-21 16:06
 **/
@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    @Resource
    private EpHotelMapper epHotelMapper;

    @Override
    public EpHotel selectByPrimaryKey(String id) {
        return epHotelMapper.selectByPrimaryKey(id);
    }

	@Override
	public EpHotel selectHotelByPersonId(String id) {
		return epHotelMapper.selectHotelByPersonId(id);
	}
}
