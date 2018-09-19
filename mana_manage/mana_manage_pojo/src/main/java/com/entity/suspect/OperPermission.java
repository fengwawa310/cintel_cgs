package com.entity.suspect;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 描述:oper_permission表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-02-03
 */
public class OperPermission {

    public static final int PERMISSION_CREATOR = 0;

    public static final int PERMISSION_QUERYABLE = 1;

    public static final int PERMISSION_QUERYABLE_EDITABLE = 2;

    /**
     * 主键
     */
    private String id;

    /**
     * 关联的重点人员编号
     */
    private String suspectNo;

    /**
     * 系统用户编号
     */
    private String userNo;

    /**
     * 0建档人；1可查阅；2可查阅可编辑
     */
    private Integer permissionCode;

    /**
     * 系统创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date creatTime;

    /**
     * 系统修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    /**
     * 主键
     * @return ID 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 关联的重点人员编号
     * @return SUSPECT_NO 关联的重点人员编号
     */
    public String getSuspectNo() {
        return suspectNo;
    }

    /**
     * 关联的重点人员编号
     * @param suspectNo 关联的重点人员编号
     */
    public void setSuspectNo(String suspectNo) {
        this.suspectNo = suspectNo == null ? null : suspectNo.trim();
    }

    /**
     * 系统用户编号
     * @return USER_NO 系统用户编号
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * 系统用户编号
     * @param userNo 系统用户编号
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    /**
     * 0建档人；1可查阅；2可查阅可编辑
     * @return PERMISSION_CODE 0建档人；1可查阅；2可查阅可编辑
     */
    public Integer getPermissionCode() {
        return permissionCode;
    }

    /**
     * 0建档人；1可查阅；2可查阅可编辑
     * @param permissionCode 0建档人；1可查阅；2可查阅可编辑
     */
    public void setPermissionCode(Integer permissionCode) {
        this.permissionCode = permissionCode;
    }

    /**
     * 系统创建时间
     * @return CREAT_TIME 系统创建时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 系统创建时间
     * @param creatTime 系统创建时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * 系统修改时间
     * @return MODIFY_TIME 系统修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 系统修改时间
     * @param modifyTime 系统修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}