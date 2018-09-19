package com.controller.taskStu;

import com.common.utils.PageHelpVO;
import com.common.utils.PageVO;
import com.entity.task.EpHotel;
import com.entity.task.EpHotelPerson;
import com.request.sys.PageVoReq;
import com.service.taskStu.HotelPersonService;
import com.service.taskStu.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/hotelPerson")
public class HotelPersonController {


	@Resource
	private HotelPersonService hotelPersonService;

	/**
	 *
	 * 按id查询公告详情
	 * @return
	 */
	@RequestMapping(value="/selectById",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public EpHotelPerson selectById(HttpSession httpSession, HttpServletRequest request, String id){
		EpHotelPerson epHotelPerson = new EpHotelPerson();
		epHotelPerson = hotelPersonService.selectByPrimaryKey(id);
//		EtJudgeflowVo etJudgeflowVo= EtJudgeflowVo.ValueOf(etJudgeflow);
//		EtJudgeflowVo etJudgeflowVo1 = EtjudgeFlowStateMethod(etJudgeflowVo);
		return epHotelPerson;
	}


	/**
	 *      /hotelPerson/findHotelPersonList
	 *
	 * 按用户查询公告列表
	 * @return
	 */
	@RequestMapping(value="/findHotelPersonList",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageHelpVO findHotelPersonList(HttpSession httpSession, HttpServletRequest request, PageVoReq pageVoReq,
										  @RequestParam HashMap<String, Object> map
	){

		PageVO pageVO = new PageVO(Integer.parseInt(pageVoReq.getStart()),Integer.parseInt(pageVoReq.getLength()));
		PageHelpVO pageHelpVO =null;
		try {
			pageHelpVO=hotelPersonService.findHotelPersonList(pageVO,map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageHelpVO;
	}

}
