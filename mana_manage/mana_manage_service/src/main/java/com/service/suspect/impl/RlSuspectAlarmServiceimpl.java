package com.service.suspect.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.suspect.RlSuspectAlarm;
import com.mapper.utils.RlSuspectAlarmMapper;
import com.service.suspect.RlSuspectAlarmService;

@Service
@Transactional
public class RlSuspectAlarmServiceimpl implements RlSuspectAlarmService{

	
	 @Resource
	    protected RlSuspectAlarmMapper rlSuspectAlarmMapper;

	@Override
	public int update(RlSuspectAlarm rlSuspectAlarm) {
		int updateByPrimaryKeySelective = rlSuspectAlarmMapper.updateByPrimaryKeySelective(rlSuspectAlarm);
		return updateByPrimaryKeySelective;
	}

	@Override
	public int insert(RlSuspectAlarm rlSuspectAlarm) {
		int insertSelective = rlSuspectAlarmMapper.insertSelective(rlSuspectAlarm);
		
		
		return insertSelective;
	}
	 

}
