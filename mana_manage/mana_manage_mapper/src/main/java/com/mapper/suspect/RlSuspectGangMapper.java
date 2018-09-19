package com.mapper.suspect;

import java.util.List;
import java.util.Map;

import com.entity.suspect.EtSuspect;
import org.apache.ibatis.annotations.Param;

import com.entity.suspect.RlSuspectGang;

public interface RlSuspectGangMapper {
    int deleteByPrimaryKey(String id);

    int insert(RlSuspectGang record);

    int insertSelective(RlSuspectGang record);

    List<Map<String, Object>> selectByPrimaryKey(@Param("id") String id);

    int updateByPrimaryKeySelective(RlSuspectGang record);

    int updateByPrimaryKey(RlSuspectGang record);

    int upgrade(String id);

    int deleteByGangId(String id);

    List<Map<String, Object>> selectByGangId(Map<String, Object> map);

    List<RlSuspectGang> selectRlsByGangId(@Param("gangId") String gangId);

    List<Map<String, Object>> fuzzeyQuery(@Param("paramStr") String paramStr,@Param("userId") String userId);

    int deleteSubordinateByPrimaryKey(String id);

    //根据重点人员id查询所有团伙id集合
    List<RlSuspectGang> findGangsBySuspectId(String suspectId);
}