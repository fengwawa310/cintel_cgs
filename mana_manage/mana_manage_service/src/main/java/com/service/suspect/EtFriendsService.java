package com.service.suspect;

import java.util.List;
import java.util.Map;

import com.entity.suspect.EtFriends;

public interface EtFriendsService {
	int deleteByPrimaryKey(String id);

	int insert(EtFriends record);

	int insertSelective(EtFriends record);

	EtFriends selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(EtFriends record);

	int updateByPrimaryKey(EtFriends record);

	List<Map<String, Object>> selectFriendsByPrimaryKey(Map<String, Object> map);

}
