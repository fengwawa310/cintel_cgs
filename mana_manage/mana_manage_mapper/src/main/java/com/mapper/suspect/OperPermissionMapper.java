package com.mapper.suspect;

import com.entity.suspect.EtSuspect;
import com.entity.suspect.OperPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OperPermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(OperPermission record);

    int insertSelective(OperPermission record);

    OperPermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OperPermission record);

    int updateByPrimaryKey(OperPermission record);

    List<OperPermission> suspectAndpoliceList(EtSuspect etSuspect);

    OperPermission selectOperPermissionBySuspectNoAndUserNo(@Param("suspectNo")String suspectNo, @Param("userNo")String userNo);

    void deleteBySuspectNoAndUserNo(@Param("suspectNo")String suspectNo, @Param("userNo")String userNo);

    void updateBySuspectNoAndUserNoSelective(OperPermission operPermission2);

    /*
     * 根据重点人员查询该重点人员的授权信息列表
     */
	List<OperPermission> selectBySuspectId(EtSuspect etSuspect);

}