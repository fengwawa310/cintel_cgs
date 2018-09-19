package com.service.sys.impl;

import com.common.utils.StringHelp;
import com.entity.sys.SysMenu;
import com.mapper.sys.SysMenuMapper;
import com.service.sys.SysMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 2017/11/28.
 */
@Service
@Transactional
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public Set<String> findResources(Set<String> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for(String resourceId : resourceIds) {
            SysMenu resource = findResourceById(resourceId);
            if(resource != null && !StringHelp.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    @Override
    public SysMenu findSysMenuById(String id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    public SysMenu findResourceById(String id){
        return sysMenuMapper.selectByPrimaryKey(id);
    }
}
