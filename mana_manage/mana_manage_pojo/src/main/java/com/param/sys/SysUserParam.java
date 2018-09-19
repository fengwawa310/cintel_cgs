package com.param.sys;

import com.common.utils.PageVO;
import com.entity.sys.SysUser;

/**
 * 系统用户查询参数包装类
 *
 * @author admin
 * @create 2017-12-04 9:36
 **/
public class SysUserParam {

    private SysUser sysUser;

    private PageVO pageVO;

    public SysUserParam() {
    }

    public SysUserParam(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public SysUserParam(SysUser sysUser, PageVO pageVO) {
        this.sysUser = sysUser;
        this.pageVO = pageVO;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public PageVO getPageVO() {
        return pageVO;
    }

    public void setPageVO(PageVO pageVO) {
        this.pageVO = pageVO;
    }
}
