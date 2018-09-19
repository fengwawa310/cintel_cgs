package com.service.suspect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.entity.suspect.EtGang;

public interface EtGangService {
	
	int deleteByPrimaryKey(String id);

    int insert(EtGang record);

    int insertSelective(HashMap<String, Object> map);

    EtGang selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EtGang record);

    int updateByPrimaryKey(EtGang record);

	int insertMap(HashMap<String, Object> record);

	List<EtGang> selectByUserId(String userId);

	List<EtGang> selectGangBySuspectNo(Map<String, Object> map);

//	获取团伙总数
	int getGangNum();

}
