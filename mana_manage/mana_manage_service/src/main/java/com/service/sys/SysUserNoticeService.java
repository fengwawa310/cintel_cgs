package com.service.sys;

import com.entity.sys.SysUserNotice;

public interface SysUserNoticeService {
    void addSysUserNotice(SysUserNotice sysUserNotice);

    int updateByPrimaryKeySelective(SysUserNotice record);
}
