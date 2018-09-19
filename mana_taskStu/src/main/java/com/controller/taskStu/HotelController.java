package com.controller.taskStu;

import com.entity.task.EpHotel;
import com.entity.task.EpHotelPerson;
import com.service.taskStu.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/hotel")
public class HotelController {


	@Resource
	private HotelService hotelService;

	/**
	 *
	 * 按id查询公告详情
	 * @return
	 */
	@RequestMapping(value="/selectById",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public EpHotel selectById(HttpSession httpSession, HttpServletRequest request, String id){
		EpHotel epHotel = new EpHotel();
		epHotel = hotelService.selectByPrimaryKey(id);
//		EtJudgeflowVo etJudgeflowVo= EtJudgeflowVo.ValueOf(etJudgeflow);
//		EtJudgeflowVo etJudgeflowVo1 = EtjudgeFlowStateMethod(etJudgeflowVo);
		return epHotel;
	}
	
	/**
	 *
	 * 按住宿表id查询酒店详情
	 * @return
	 */
	@RequestMapping(value="/selectHotelByPersonId",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public EpHotel selectHotelByPersonId(HttpSession httpSession, HttpServletRequest request, String id){
		EpHotel epHotel = new EpHotel();
		epHotel = hotelService.selectHotelByPersonId(id);
//		EtJudgeflowVo etJudgeflowVo= EtJudgeflowVo.ValueOf(etJudgeflow);
//		EtJudgeflowVo etJudgeflowVo1 = EtjudgeFlowStateMethod(etJudgeflowVo);
		return epHotel;
	}

}
