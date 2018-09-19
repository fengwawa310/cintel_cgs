package com.vo.sys;

import com.entity.sys.SysMenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * sys_menu返回包装类
 *
 * @author admin
 * @create 2017-11-30 14:05
 **/

public class SysMenuVO extends SysMenu implements Serializable {

    List<SysMenu> children=new ArrayList<>();

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }


    public SysMenuVO(String id, String parentId, String parentIds, String name, Long sort, String href, String target, String icon, String isShow, String permission, String createBy, Date createDate, String updateBy, Date updateDate, String remarks, String delFlag) {
        super(id, parentId, parentIds, name, sort, href, target, icon, isShow, permission, createBy, createDate, updateBy, updateDate, remarks, delFlag);
    }

    public static SysMenuVO valueOf(SysMenu sysMenu) {
        SysMenuVO sysMenuVO = new SysMenuVO(
                sysMenu.getId(),
                sysMenu.getParentId(),
                sysMenu.getParentIds(),
                sysMenu.getName(),
                sysMenu.getSort(),
                sysMenu.getHref(),
                sysMenu.getTarget(),
                sysMenu.getIcon(),
                sysMenu.getIsShow(),
                sysMenu.getPermission(),
                sysMenu.getCreateBy(),
                sysMenu.getCreateDate(),
                sysMenu.getUpdateBy(),
                sysMenu.getUpdateDate(),
                sysMenu.getRemarks(),
                sysMenu.getDelFlag());
        return sysMenuVO;
    }
}
