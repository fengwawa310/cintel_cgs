package com.service.taskStu;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.task.EpHotel;
import com.entity.task.EpHotelPerson;

import java.util.Map;

public interface HotelPersonService {
    EpHotelPerson selectByPrimaryKey(String id);

    PageHelpVO findHotelPersonList(PageVO pageVO, Map map);

}
