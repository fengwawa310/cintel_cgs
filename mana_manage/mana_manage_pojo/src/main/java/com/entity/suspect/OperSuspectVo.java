package com.entity.suspect;

import java.util.Date;

/**
 * Created by Auri on 2018/2/6.
 * Desc:
 */
public class OperSuspectVo {

    private String userId;

    private Integer permissionCode;
    private String suspectId;
    private String name;
    private Date createTime;
    private Date modifyTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(Integer permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getSuspectId() {
        return suspectId;
    }

    public void setSuspectId(String suspectId) {
        this.suspectId = suspectId;
    }
}
