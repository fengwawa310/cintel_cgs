package com.vo.suspect;

import com.common.enums.EnumTypeVO;
import com.entity.suspect.OperPermission;
import com.entity.sys.SysUser;

/**
 * 嫌疑人授权警员关系表
 *
 * @author admin
 * @create 2018-02-05 11:09
 **/
public class OperPermissionVO  {

    private OperPermission operPermission;

    private SysUser sysUser;

 /*   *//*嫌疑人-警员关联表 警员查阅权限数据*//*
    private EnumTypeVO permission_codeView;

    *//*嫌疑人-警员关联表 警员编辑权限数据*//*
    private EnumTypeVO permission_codeEdit;*/

    private EnumTypeVO permission_code;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public OperPermission getOperPermission() {
        return operPermission;
    }

    public void setOperPermission(OperPermission operPermission) {
        this.operPermission = operPermission;
    }

   /* public EnumTypeVO getPermission_codeView() {
        return permission_codeView;
    }

    public void setPermission_codeView(EnumTypeVO permission_codeView) {
        this.permission_codeView = permission_codeView;
    }

    public EnumTypeVO getPermission_codeEdit() {
        return permission_codeEdit;
    }

    public void setPermission_codeEdit(EnumTypeVO permission_codeEdit) {
        this.permission_codeEdit = permission_codeEdit;
    }*/

    public EnumTypeVO getPermission_code() {
        return permission_code;
    }

    public void setPermission_code(EnumTypeVO permission_code) {
        this.permission_code = permission_code;
    }

    public OperPermissionVO() {
    }

    public OperPermissionVO(SysUser sysUser, OperPermission operPermission) {
        this.sysUser = sysUser;
        this.operPermission = operPermission;
    }
}
