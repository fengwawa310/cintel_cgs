package com.service.sys.impl;

import com.common.enums.IsShowType;
import com.common.utils.StringHelp;
import com.entity.sys.SysMenu;
import com.entity.sys.SysRole;
import com.entity.sys.SysRoleMenuKey;
import com.mapper.sys.SysMenuMapper;
import com.mapper.sys.SysRoleMapper;
import com.mapper.sys.SysRoleMenuMapper;
import com.service.sys.SysMenuService;
import com.service.sys.SysRoleService;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2017/11/28.
 */
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public Set<String> findRoles(List<String> roleIds) {
        Set<String> roles = new HashSet<String>();
        for(String roleId : roleIds) {
            SysRole role = findRoleById(roleId);
            if(role != null) {
                roles.add(role.getName());
            }
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(List<String> roleIds) {
        Set<String> resourceIds = new HashSet<String>();
        for(String roleId : roleIds) {
            SysRole role = findRoleById(roleId);
            //获取角色对应的资源信息。
            List<SysRoleMenuKey> sysRoleMenuKeys=sysRoleMenuMapper.findSysRoleMenuListByRoleId(role.getId());
            List<String> list = new ArrayList<String>();
            for (SysRoleMenuKey sysRoleMenuKey : sysRoleMenuKeys) {
                list.add(sysRoleMenuKey.getMenuId());
            }
            if(role != null) {
                resourceIds.addAll(list);
            }
        }
        return sysMenuService.findResources(resourceIds);
    }

    @Override
    public List<SysMenu> findMenus(Set<String> permissions) {
        List<SysMenu> allResources = findAllMenu();
        List<SysMenu> menus = new ArrayList<>();
        for(SysMenu resource : allResources) {
            if(resource.isRootNode()) {
                continue;
            }
            if(!resource.getIsShow().equals(String.valueOf(IsShowType.SHOW.getValue()))) {
                continue;
            }
            if(!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }

    public List<SysMenu> findAllMenu() {
        List<SysMenu> allResources= sysMenuMapper.findAllMenu();
        return allResources;
    }

    private boolean hasPermission(Set<String> permissions, SysMenu resource) {
        if(StringHelp.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }

    public SysRole findRoleById(String roleId) {
        return sysRoleMapper.selectByPrimaryKey(roleId);
    }
}
