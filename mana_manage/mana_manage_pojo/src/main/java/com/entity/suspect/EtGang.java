package com.entity.suspect;

import java.util.Date;

/**
 * 描述:et_gang表的实体类
 *
 * @author: Administrator
 * @创建时间: 2018-01-31
 */
public class EtGang {
    /**
     * 主键
     */
    private String id;

    /**
     * 团伙名称
     */
    private String gangName;

    /**
     * 团伙标识
     */
    private String gangTag;

    /**
     * 团伙类型
     */
    private Integer gangType;

    /**
     * 团伙类型名称
     */
    private String gangTypeStr;

    /**
     * 团伙地址代码，记录行政区划号码
     */
    private String gangAddrCode;

    /**
     * 团伙地址名称，记录行政区划名称
     */
    private String gangAddr;

    /**
     * 团伙头目，记录重点人员编号
     */
    private String gangLead;

    /**
     * 备注
     */
    private String gangDemo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 主键
     *
     * @return ID 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 团伙名称
     *
     * @return GANG_NAME 团伙名称
     */
    public String getGangName() {
        return gangName;
    }

    /**
     * 团伙名称
     *
     * @param gangName 团伙名称
     */
    public void setGangName(String gangName) {
        this.gangName = gangName == null ? null : gangName.trim();
    }

    /**
     * 团伙标识
     *
     * @return GANG_TAG 团伙标识
     */
    public String getGangTag() {
        return gangTag;
    }

    /**
     * 团伙标识
     *
     * @param gangTag 团伙标识
     */
    public void setGangTag(String gangTag) {
        this.gangTag = gangTag == null ? null : gangTag.trim();
    }

    /**
     * 团伙地址代码，记录行政区划号码
     *
     * @return GANG_ADDR_CODE 团伙地址代码，记录行政区划号码
     */
    public String getGangAddrCode() {
        return gangAddrCode;
    }

    /**
     * 团伙地址代码，记录行政区划号码
     *
     * @param gangAddrCode 团伙地址代码，记录行政区划号码
     */
    public void setGangAddrCode(String gangAddrCode) {
        this.gangAddrCode = gangAddrCode == null ? null : gangAddrCode.trim();
    }

    /**
     * 团伙地址名称，记录行政区划名称
     *
     * @return GANG_ADDR 团伙地址名称，记录行政区划名称
     */
    public String getGangAddr() {
        return gangAddr;
    }

    /**
     * 团伙地址名称，记录行政区划名称
     *
     * @param gangAddr 团伙地址名称，记录行政区划名称
     */
    public void setGangAddr(String gangAddr) {
        this.gangAddr = gangAddr == null ? null : gangAddr.trim();
    }

    /**
     * 团伙头目，记录重点人员编号
     *
     * @return GANG_LEAD 团伙头目，记录重点人员编号
     */
    public String getGangLead() {
        return gangLead;
    }

    /**
     * 团伙头目，记录重点人员编号
     *
     * @param gangLead 团伙头目，记录重点人员编号
     */
    public void setGangLead(String gangLead) {
        this.gangLead = gangLead == null ? null : gangLead.trim();
    }

    /**
     * 备注
     *
     * @return GANG_DEMO 备注
     */
    public String getGangDemo() {
        return gangDemo;
    }

    /**
     * 备注
     *
     * @param gangDemo 备注
     */
    public void setGangDemo(String gangDemo) {
        this.gangDemo = gangDemo == null ? null : gangDemo.trim();
    }

    /**
     * 创建时间
     *
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     *
     * @return MODIFY_TIME 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getGangType() {
        return gangType;
    }

    public void setGangType(Integer gangType) {
        this.gangType = gangType;
    }

    public String getGangTypeStr() {
        return gangTypeStr;
    }

    public void setGangTypeStr(String gangTypeStr) {
        this.gangTypeStr = gangTypeStr;
    }
}