package com.mapper.sys;

import com.entity.sys.SysRole;

import java.util.List;
import java.util.Set;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    /*根据角色id获取所有角色信息*/
    Set<String> findRoles(List<String> list);
}