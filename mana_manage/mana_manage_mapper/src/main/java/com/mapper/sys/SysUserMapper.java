package com.mapper.sys;

import com.entity.sys.SysUser;
import com.param.sys.SysUserParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    /*根据用户名查找用户信息*/
    SysUser findUserByUsername(String username);
    /*根据用户参数查询用户信息*/
    List<SysUser> findSysUserListByParam(SysUserParam sysUserParam);

    List<SysUser> findUserBySysDict(@Param("grade") String grade, @Param("code")String code);
    /*根据用户参数查询用户信息--模糊查找*/
    List<SysUser> findSysUserListByParamBlue(SysUserParam sysUserParam);
}