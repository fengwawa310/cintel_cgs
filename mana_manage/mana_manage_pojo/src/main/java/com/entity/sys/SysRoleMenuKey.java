package com.entity.sys;

public class SysRoleMenuKey {
    /**
     * 角色编号
     */
    private String roleId;

    /**
     * 菜单编号
     */
    private String menuId;

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

    /**
     * 菜单编号
     * @return menu_id 菜单编号
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 菜单编号
     * @param menuId 菜单编号
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }
}