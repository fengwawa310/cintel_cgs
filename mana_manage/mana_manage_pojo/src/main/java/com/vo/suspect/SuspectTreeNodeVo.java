package com.vo.suspect;

import com.vo.TreeNodeVo;

import javax.swing.tree.TreeNode;

/**
 * Created by Auri on 2018/2/3.
 * Desc: 重点人员树形节点
 */
public class SuspectTreeNodeVo extends TreeNodeVo implements Comparable<String> {

    public static final int LIST_BY_SUSPECTTYPE = 3;

    public static final int LIST_BY_ENTRY_UNIT = 2;

    public static final int LIST_BY_GANG = 1;

    /**
     * 重点人员编号
     */
    private String suspectNo;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 操作权限编码 0表示没有查阅权限 1表示可以查阅
     */
    private int permissionCode;

//    /**
//     * 展开时图标
//     */
//    private String iconOpen;
//
//    /**
//     * 关闭时图标
//     */
//    private String iconClose;

    /**
     * 用于展示人员图标
     */
    private String icon;

    public String getSuspectNo() {
        return suspectNo;
    }

    public void setSuspectNo(String suspectNo) {
        this.suspectNo = suspectNo;
    }

    public int getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(int permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
