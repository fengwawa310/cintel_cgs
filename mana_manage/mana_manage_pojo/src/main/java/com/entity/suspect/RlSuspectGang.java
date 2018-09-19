package com.entity.suspect;

import java.util.Date;

/**
 * 描述:rl_suspect_gang表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-01-31
 */
public class RlSuspectGang {
    /**
     * 主键
     */
    private String id;

    /**
     * 重点人员编号
     */
    private String suspectId;

    /**
     * 团伙主键
     */
    private String gangId;

    /**
     * 该重点人员在组织中的角色，字典数据“扮演角色”
     */
    private String role;

    /**
     * 该重点人员在该组织中的上级人员编号
     */
    private String parentId;

    /**
     * 备注
     */
    private String demo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
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
     * 重点人员编号
     * @return SUSPECT_ID 重点人员编号
     */
    public String getSuspectId() {
        return suspectId;
    }

    /**
     * 重点人员编号
     * @param suspectId 重点人员编号
     */
    public void setSuspectId(String suspectId) {
        this.suspectId = suspectId == null ? null : suspectId.trim();
    }

    /**
     * 团伙主键
     * @return GANG_ID 团伙主键
     */
    public String getGangId() {
        return gangId;
    }

    /**
     * 团伙主键
     * @param gangId 团伙主键
     */
    public void setGangId(String gangId) {
        this.gangId = gangId == null ? null : gangId.trim();
    }

    /**
     * 该重点人员在组织中的角色，字典数据“扮演角色”
     * @return ROLE 该重点人员在组织中的角色，字典数据“扮演角色”
     */
    public String getRole() {
        return role;
    }

    /**
     * 该重点人员在组织中的角色，字典数据“扮演角色”
     * @param role 该重点人员在组织中的角色，字典数据“扮演角色”
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 该重点人员在该组织中的上级人员编号
     * @return PARENT_ID 该重点人员在该组织中的上级人员编号
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 该重点人员在该组织中的上级人员编号
     * @param parentId 该重点人员在该组织中的上级人员编号
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 备注
     * @return DEMO 备注
     */
    public String getDemo() {
        return demo;
    }

    /**
     * 备注
     * @param demo 备注
     */
    public void setDemo(String demo) {
        this.demo = demo == null ? null : demo.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     * @return MODIFY_TIME 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}