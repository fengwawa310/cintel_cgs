package com.service.sys;

import com.entity.sys.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2017/11/28.
 */
public interface SysRoleService {
    //根据角色id，获取用户的角色信息，
    Set<String> findRoles(List<String> list);
    //根据角色id，获取用户的权限信息，
    Set<String> findPermissions(List<String> list);
    /*根据用户的权限获取用户所有菜单*/
    List<SysMenu> findMenus(Set<String> permissions);
}
