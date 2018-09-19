package com.mapper.sys;


import com.entity.sys.SysUserNotice;

public interface SysUserNoticeMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserNotice record);

    int insertSelective(SysUserNotice record);

    SysUserNotice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserNotice record);

    int updateByPrimaryKey(SysUserNotice record);
}