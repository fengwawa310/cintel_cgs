package com.mapper.suspect;

import java.util.List;
import java.util.Map;

import com.entity.suspect.EtFriends;

public interface EtFriendsMapper {
	int deleteByPrimaryKey(String id);

	int insert(EtFriends record);

	int insertSelective(EtFriends record);

	EtFriends selectByPrimaryKey(String id);
	
	List<Map<String, Object>> selectFriendsByPrimaryKey(Map<String, Object> map);

	int updateByPrimaryKeySelective(EtFriends record);

	int updateByPrimaryKey(EtFriends record);
}