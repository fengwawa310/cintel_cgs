package com.entity.sys;

public class SysUserRoleKey {
    /**
     * 用户编号
     */
    private String userId;

    /**
     * 角色编号
     */
    private String roleId;

    /**
     * 用户编号
     * @return user_id 用户编号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户编号
     * @param userId 用户编号
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 角色编号
     * @return role_id 角色编号
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 角色编号
     * @param roleId 角色编号
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}