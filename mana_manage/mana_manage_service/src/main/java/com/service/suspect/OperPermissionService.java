package com.service.suspect;

import com.entity.suspect.OperPermission;

public interface OperPermissionService {
	
	int deleteByPrimaryKey(String id);

    int insert(OperPermission record);

    int insertSelective(OperPermission record);

    OperPermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OperPermission record);

    int updateByPrimaryKey(OperPermission record);

    /*根据嫌疑人编号和警员编号查询关联表信息*/
    OperPermission selectOperPermissionBySuspectNoAndUserNo(String suspectNo, String userNo);
    /*根据嫌疑人编号和警员编号删除关联表信息*/
    void deleteBySuspectNoAndUserNo(String suspectNo, String userNo);
    /*根据嫌疑人编码警员编码更新关联表信息*/
    void updateBySuspectNoAndUserNoSelective(OperPermission operPermission2);
}
