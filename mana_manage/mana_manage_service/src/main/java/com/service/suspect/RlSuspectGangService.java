package com.service.suspect;

import java.util.List;
import java.util.Map;

import com.entity.suspect.RlSuspectGang;

public interface RlSuspectGangService {
	
	int deleteByPrimaryKey(String id);

    int insert(RlSuspectGang record);

    int insertSelective(RlSuspectGang record);

    List<Map<String, Object>> selectByPrimaryKey(String id);
    
    List<Map<String, Object>> selectByGangId(Map<String, Object> map);

    List<RlSuspectGang> selectRlsByGangId(String gangId);

    int updateByPrimaryKeySelective(RlSuspectGang record);

    int updateByPrimaryKey(RlSuspectGang record);
	int deleteSubordinateByPrimaryKey(String id);

	//根据重点人员id查询所有团伙id集合
    List<String> findGangsBySuspectId(String suspectId);
}
