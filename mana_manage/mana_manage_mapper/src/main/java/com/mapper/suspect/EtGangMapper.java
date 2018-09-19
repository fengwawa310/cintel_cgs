package com.mapper.suspect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.entity.suspect.EtGang;

public interface EtGangMapper {
    int deleteByPrimaryKey(String id);

    int insert(EtGang record);

    int insertSelective(HashMap<String, Object> record);

    EtGang selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EtGang record);

    int updateByPrimaryKey(EtGang record);

	int insertMap(HashMap<String, Object> record);

	List<EtGang> selectByUserId(String userId);

	List<EtGang> selectAll(@Param("paramStr")String paramStr);

	List<EtGang> selectGangBySuspectNo(Map<String, Object> map);
//    获取团伙总数
    int getGangNum();
}