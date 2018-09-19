package com.mapper.sys;

import com.entity.sys.SysRoleMenuKey;

import java.util.List;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(SysRoleMenuKey key);

    int insert(SysRoleMenuKey record);

    int insertSelective(SysRoleMenuKey record);

    List<SysRoleMenuKey> findSysRoleMenuListByRoleId(String id);
}