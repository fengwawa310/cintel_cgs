package com.service.suspect.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.suspect.EtFriends;
import com.mapper.suspect.EtFriendsMapper;
import com.service.suspect.EtFriendsService;

@Service
@Transactional
public class EtFriendsServiceImpl implements EtFriendsService {
	
	@Resource
	private EtFriendsMapper etFriendsMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return etFriendsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(EtFriends record) {
		return etFriendsMapper.insert(record);
	}

	@Override
	public int insertSelective(EtFriends record) {
		return etFriendsMapper.insertSelective(record);
	}

	@Override
	public EtFriends selectByPrimaryKey(String id) {
		return etFriendsMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Map<String, Object>> selectFriendsByPrimaryKey(Map<String,Object> map) {
		return etFriendsMapper.selectFriendsByPrimaryKey(map);
	}

	@Override
	public int updateByPrimaryKeySelective(EtFriends record) {
		return etFriendsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EtFriends record) {
		return etFriendsMapper.updateByPrimaryKey(record);
	}

}
