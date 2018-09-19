package com.service.sys;

import com.common.utils.PageHelpVO;
import com.entity.sys.SysUser;
import com.param.sys.SysUserParam;
import com.vo.sys.SysMenuVO;

import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2017/11/28.
 */
public interface SysUserService {
    SysUser findUserById(String id);
    
    Set<String> findRoles(String username);
    
    List<String> findRoleIds(String userid);

    Set<String> findPermissions(String username);

    SysUser findUserByUsername(String username);
    /*获取当前用户菜单数据*/
    List<SysMenuVO> findUserMenu(SysUser user);
    /*根据查询参数获取用户的列表数据*/
    PageHelpVO findSysUserListByParam(SysUserParam sysUserParam);
    /*根据单位机构编码查询机构下用户*/
    List<SysUser> findUserBySysDict(String grade,String code);
}
