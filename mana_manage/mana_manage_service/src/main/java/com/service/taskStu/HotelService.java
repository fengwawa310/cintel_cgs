package com.service.taskStu;

import com.entity.task.EpHotel;
import com.entity.task.EpHotelPerson;

public interface HotelService {
    EpHotel selectByPrimaryKey(String id);

	EpHotel selectHotelByPersonId(String id);
}
