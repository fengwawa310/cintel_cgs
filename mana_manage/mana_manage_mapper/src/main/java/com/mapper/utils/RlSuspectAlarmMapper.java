package com.mapper.utils;

import java.util.List;

import com.entity.alarm.EtAlarm;
import com.entity.suspect.EtSuspect;
import com.entity.suspect.RlSuspectAlarm;

public interface RlSuspectAlarmMapper {
    /*人-警情对应关系查询*/
    List<EtAlarm> findAlarmByIDCardNum(EtSuspect entity);
    /*人-警情对应关系建立*/
    int insert(RlSuspectAlarm record);

    int deleteByPrimaryKey(String id);

    int insertSelective(RlSuspectAlarm record);

    RlSuspectAlarm selectByPrimaryKey(String id);
    
//    List<RlSuspectAlarm> selectByPrimaryKey(RlSuspectAlarm record);

    int updateByPrimaryKeySelective(RlSuspectAlarm record);

    int updateByPrimaryKey(RlSuspectAlarm record);


}