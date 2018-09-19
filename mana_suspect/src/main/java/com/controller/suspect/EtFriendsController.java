package com.controller.suspect;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.suspect.EtFriends;
import com.service.suspect.EtFriendsService;

@Controller
@RequestMapping("etFriendsCon")
public class EtFriendsController {
	
	@Resource
	private EtFriendsService etFriendsService;
	

	@RequestMapping(value = "/deleteByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int deleteByPrimaryKey(String id) {
		return etFriendsService.deleteByPrimaryKey(id);
	}

	@RequestMapping(value = "/insert", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int insert(EtFriends record) {
		return etFriendsService.insert(record);
	}

	@RequestMapping(value = "/insertSelective", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int insertSelective(EtFriends record) {
		return etFriendsService.insertSelective(record);
	}

	/**
	 * 	/etFriendsCon/selectByPrimaryKey
	 * @param id
	 * @return
     */
	@RequestMapping(value = "/selectByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public EtFriends selectByPrimaryKey(String id) {
		return etFriendsService.selectByPrimaryKey(id);
	}
	/**
	 * 	/etFriendsCon/selectFriendsByPrimaryKey
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/selectFriendsByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Map<String,Object>> selectFriendsByPrimaryKey(@RequestParam Map<String,Object> map) {
		return etFriendsService.selectFriendsByPrimaryKey(map);
	}

	@RequestMapping(value = "/updateByPrimaryKeySelective", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int updateByPrimaryKeySelective(EtFriends record) {
		return etFriendsService.updateByPrimaryKeySelective(record);
	}

	@RequestMapping(value = "/updateByPrimaryKey", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int updateByPrimaryKey(EtFriends record) {
		return etFriendsService.updateByPrimaryKey(record);
	}

}
