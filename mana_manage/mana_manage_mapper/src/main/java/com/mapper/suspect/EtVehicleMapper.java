package com.mapper.suspect;

import com.entity.suspect.EtVehicle;

import java.util.List;

public interface EtVehicleMapper {
    /*添加重点人员车辆信息*/
    int insert(EtVehicle record);
    /*通过重点人员编号查询车辆信息*/
    List<EtVehicle> selectvehicleList(String suspectId);

    int deleteByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EtVehicle record);

    int updateByPrimaryKey(EtVehicle record);


}