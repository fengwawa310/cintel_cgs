package com.mapper.taskgnlk;


import java.util.List;
import java.util.Map;

import com.entity.task.EpHotelPerson;

public interface OEpHotelPersonMapper {

    EpHotelPerson selectByPrimaryKey(String ccode);
    
    public List<EpHotelPerson> findEpHotelPersonData(Map<String, String> map);

	long findEpHotelPersonCount(Map<String, String> map);
}