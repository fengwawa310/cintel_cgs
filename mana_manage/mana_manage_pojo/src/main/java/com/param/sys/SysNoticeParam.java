package com.param.sys;

import com.entity.sys.SysNotice;
import com.entity.sys.SysUser;

/**
 * 系统通知包装类
 *
 * @author admin
 * @create 2018-01-19 14:52
 **/
public class SysNoticeParam {

    private SysUser sysUser;

    private SysNotice sysNotice;

    public SysNoticeParam() {
    }

    public SysNoticeParam(SysUser sysUser, SysNotice sysNotice) {
        this.sysUser = sysUser;
        this.sysNotice = sysNotice;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public SysNotice getSysNotice() {
        return sysNotice;
    }

    public void setSysNotice(SysNotice sysNotice) {
        this.sysNotice = sysNotice;
    }
}
