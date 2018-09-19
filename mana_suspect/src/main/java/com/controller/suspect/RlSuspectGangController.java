package com.controller.suspect;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.BeanParam;
import javax.ws.rs.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.suspect.RlSuspectGang;
import com.service.suspect.RlSuspectGangService;

@Controller
@RequestMapping("rlSuspectGangCon")
public class RlSuspectGangController {

	@Resource
	private RlSuspectGangService rlSuspectGangService;

	@RequestMapping(value = "/deleteByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int deleteByPrimaryKey(String id) {
		//service中会有对数据的update和delete，因为下层有事物
		return rlSuspectGangService.deleteByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteSubordinateByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int deleteSubordinateByPrimaryKey(String id) {
		//service中会有对数据的update和delete，因为下层有事物
		return rlSuspectGangService.deleteSubordinateByPrimaryKey(id);
	}

	@RequestMapping(value = "/insert", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int insert(RlSuspectGang record) {
		return rlSuspectGangService.insert(record);
	}

	@RequestMapping(value = "/insertSelective", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int insertSelective(RlSuspectGang record) {
		return rlSuspectGangService.insertSelective(record);
	}

	@RequestMapping(value = "/selectByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Map<String,Object>> selectByPrimaryKey(String id) {
		return rlSuspectGangService.selectByPrimaryKey(id);
	}
	
	@RequestMapping(value = "/selectByGangId", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Map<String,Object>> selectByGangId(@RequestParam Map<String,Object> map) {
		return rlSuspectGangService.selectByGangId(map);
	}

	@RequestMapping(value = "/updateByPrimaryKeySelective", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int updateByPrimaryKeySelective(RlSuspectGang record) {
		return rlSuspectGangService.updateByPrimaryKeySelective(record);
	}

	@RequestMapping(value = "/updateByPrimaryKey", produces = "application/json;charset=UTF-8")
	public int updateByPrimaryKey(RlSuspectGang record) {
		return rlSuspectGangService.updateByPrimaryKey(record);
	}

}
