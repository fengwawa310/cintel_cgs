package com.controller.taskStu;

import com.entity.task.EpHotelDistance;
import com.service.taskStu.HotelDistanceService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sky on 2018/4/3.
 */
@Controller
@RequestMapping("/hotelDistance")
public class HotelDistanceController {

    @Resource
    HotelDistanceService hotelDistanceService;

    @RequestMapping(value="/selectByHotelId",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> selectByHotelId(HttpServletRequest request, EpHotelDistance epHotelDistance){
        Map<String,Object> resultMap = new HashedMap();
        List<EpHotelDistance> hotelDistances = new ArrayList<>();
        hotelDistances = hotelDistanceService.selectByHotelId(epHotelDistance.getHotelId());
        resultMap.put("code","200");
        resultMap.put("hotelList",hotelDistances);
        return resultMap;
    }

}
