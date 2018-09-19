package com.controller.suspect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.service.communal.DicUtilsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.suspect.EtGang;
import com.service.suspect.EtGangService;

@Controller
@RequestMapping("etGangCon")
public class EtGangController {

	@Resource
	private EtGangService etGangService;

	@RequestMapping(value = "/deleteByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int deleteByPrimaryKey(String id) {
		return etGangService.deleteByPrimaryKey(id);
	}

	@RequestMapping(value = "/insert", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int insert(EtGang record) {
		return etGangService.insert(record);
	}
	
	@RequestMapping(value = "/insertMap", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int insertMap(@RequestParam HashMap<String,Object> map) {
		return etGangService.insertMap(map);
	}

	@RequestMapping(value = "/insertSelective", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int insertSelective(@RequestParam HashMap<String,Object> map) {
		return etGangService.insertSelective(map);
	}

	@RequestMapping(value = "/selectByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public EtGang selectByPrimaryKey(String gangLead) {
		return etGangService.selectByPrimaryKey(gangLead);
	}
	
	@RequestMapping(value = "/selectGangBySuspectNo", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<EtGang> selectGangBySuspectNo(@RequestParam Map<String,Object> map) {
		return etGangService.selectGangBySuspectNo(map);
	}
	
	@RequestMapping(value = "/selectByUserId", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<EtGang> selectByUserId(String userId) {
		return etGangService.selectByUserId(userId);
	}

	@RequestMapping(value = "/updateByPrimaryKeySelective", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int updateByPrimaryKeySelective(EtGang record) {
		return etGangService.updateByPrimaryKeySelective(record);
	}

	@RequestMapping(value = "/updateByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int updateByPrimaryKey(EtGang record) {
		return etGangService.updateByPrimaryKey(record);
	}

	/**
	 * @Author: sky
	 * @Description:获取团伙数量  组织数
	 * @Date: 10:01 2018/5/24
	 * @param: httpSession
	request
	 */
	@RequestMapping(value="/getGangNum",produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public Map<String,Object> getGangNum(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>();
		int gangNum = etGangService.getGangNum();
		resultMap.put("gangNum",gangNum);
		return resultMap;
	}
}
