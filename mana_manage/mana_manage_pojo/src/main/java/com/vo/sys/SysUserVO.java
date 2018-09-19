package com.vo.sys;

import com.common.utils.PageVO;
import com.entity.sys.SysUser;

import java.io.Serializable;

/**
 * 用户包装类
 *
 * @author admin
 * @create 2018-01-03 19:24
 **/
public class SysUserVO implements Serializable {

    private SysUser sysUser;

    private PageVO pageVO;


    public SysUserVO() {
    }

    public SysUserVO(SysUser sysUser, PageVO pageVO) {
        this.sysUser = sysUser;
        this.pageVO = pageVO;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
