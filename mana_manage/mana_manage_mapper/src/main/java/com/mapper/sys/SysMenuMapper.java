package com.mapper.sys;


import com.entity.sys.SysMenu;

import java.util.List;
import java.util.Set;

public interface SysMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    /*根据资源id获取所有资源信息*/
    Set<String> findResources(Set<String> resourceIds);

    List<SysMenu> findAllMenu();
}