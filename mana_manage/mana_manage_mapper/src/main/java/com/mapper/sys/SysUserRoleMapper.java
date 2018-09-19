package com.mapper.sys;

import com.entity.sys.SysUserRoleKey;

import java.util.List;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(SysUserRoleKey key);

    int insert(SysUserRoleKey record);

    int insertSelective(SysUserRoleKey record);
    /*根据用户id获取用户所有的角色信息*/
    List<SysUserRoleKey> findSysUserRoleListBySysUserId(String id);
}