package com.service.sys;

import com.entity.sys.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2017/11/28.
 */
public interface SysMenuService {
    Set<String> findResources(Set<String> resourceIds);
    /*根据id查询菜单资源*/
    SysMenu findSysMenuById(String id);
}