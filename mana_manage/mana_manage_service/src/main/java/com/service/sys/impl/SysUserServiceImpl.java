package com.service.sys.impl;

import com.common.utils.PageHelpVO;
import com.entity.sys.SysMenu;
import com.entity.sys.SysUser;
import com.entity.sys.SysUserRoleKey;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.sys.SysUserMapper;
import com.mapper.sys.SysUserRoleMapper;
import com.param.sys.SysUserParam;
import com.service.sys.SysRoleService;
import com.service.sys.SysUserService;
import com.vo.sys.SysMenuVO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2017/11/28.
 */

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private SysRoleService sysRoleService;

    @Override
    public SysUser findUserById(String id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    /*shiro ---获取所有角色信息*/
    @Override
    public Set<String> findRoles(String username) {
        SysUser user =findUserByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        /*获取所有的用户角色*/
        List<SysUserRoleKey> sysUserRoleKeys=sysUserRoleMapper.findSysUserRoleListBySysUserId(user.getId());
        List<String> list = new ArrayList();
        for (SysUserRoleKey sysUserRoleKey : sysUserRoleKeys) {
            String roleId = sysUserRoleKey.getRoleId();
            list.add(roleId);
        }
        return sysRoleService.findRoles(list);
    }
    
    @Override
    public List<String> findRoleIds(String userid) {
    	if(StringUtils.isBlank(userid)) {
    		return Collections.emptyList();
    	}
    	/*获取所有的用户角色*/
    	List<SysUserRoleKey> sysUserRoleKeys=sysUserRoleMapper.findSysUserRoleListBySysUserId(userid);
    	List<String> result = new ArrayList<String>();
    	for (SysUserRoleKey sysUserRoleKey : sysUserRoleKeys) {
    		String roleId = sysUserRoleKey.getRoleId();
    		result.add(roleId);
    	}
    	return result;
    }


    /*shiro ---获取所有权限信息*/
    @Override
    public Set<String> findPermissions(String username) {
        SysUser user =findUserByUsername(username);
      /*获取所有的用户角色*/
        List<SysUserRoleKey> sysUserRoleKeys=sysUserRoleMapper.findSysUserRoleListBySysUserId(user.getId());
        List<String> list = new ArrayList();
        for (SysUserRoleKey sysUserRoleKey : sysUserRoleKeys) {
            String roleId = sysUserRoleKey.getRoleId();
            list.add(roleId);
        }
        return sysRoleService.findPermissions(list);
    }
    @Override
    public SysUser findUserByUsername(String username) {
        return sysUserMapper.findUserByUsername(username);
    }

    @Override
    public List<SysMenuVO> findUserMenu(SysUser user) {
        Set<String> permissions = this.findPermissions(user.getUserName());
        List<SysMenu> menus = sysRoleService.findMenus(permissions);
        List<SysMenuVO> allList = new ArrayList<>();
        List<SysMenuVO> resourceList = new ArrayList<>();
        for (SysMenu resourceTemp : menus) {//获得顶级菜单，封装到集合中。
            SysMenuVO sysMenuVO=SysMenuVO.valueOf(resourceTemp);
            allList.add(sysMenuVO);
            String parentId = resourceTemp.getParentId();
            if (parentId != null && parentId.equals("1")) {
                resourceList.add(sysMenuVO);
            }
        }
        for (SysMenuVO resource : resourceList) {//遍历顶层菜单，获得其子菜单，包装。
            String id = resource.getId();
            for (SysMenuVO resourceTemp : allList) {
                String parentId = resourceTemp.getParentId();
                if(parentId.equals(id)){
                    resource.getChildren().add(resourceTemp);
                }
            }
        }
        return resourceList;
    }

    @Override
    public PageHelpVO findSysUserListByParam(SysUserParam sysUserParam) {
        Integer start = sysUserParam.getPageVO().getStart();
        Integer length = sysUserParam.getPageVO().getLength();
        PageHelper.startPage(start, length);
        List<SysUser> sysUserList= sysUserMapper.findSysUserListByParam(sysUserParam);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(sysUserList);
        long total = pageInfo.getTotal();
        PageHelpVO pageHelpVO = new PageHelpVO<SysUser>(total,sysUserList);
        return pageHelpVO;
    }

    @Override
    public List<SysUser> findUserBySysDict(String grade,String code) {
        return sysUserMapper.findUserBySysDict(grade,code);
    }


}
