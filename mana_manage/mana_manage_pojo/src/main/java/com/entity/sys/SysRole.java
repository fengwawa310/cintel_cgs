package com.entity.sys;

import java.util.Date;

/**
 * 描述:sys_role表的实体类
 * @version
 * @author:  admin
 * @创建时间: 2017-11-28
 */
public class SysRole {
    /**
     * 
     */
    private String id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 数据范围
     */
    private String dataScope;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否可用
     */
    private String useable;

    /**
     * 备注
     */
    private String remark;

    /**
     * 
     * @return id 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 角色名
     * @return name 角色名
     */
    public String getName() {
        return name;
    }

    /**
     * 角色名
     * @param name 角色名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 数据范围
     * @return data_scope 数据范围
     */
    public String getDataScope() {
        return dataScope;
    }

    /**
     * 数据范围
     * @param dataScope 数据范围
     */
    public void setDataScope(String dataScope) {
        this.dataScope = dataScope == null ? null : dataScope.trim();
    }

    /**
     * 排序
     * @return sort 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 创建人
     * @return create_user 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
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
     * 更新人
     * @return update_user 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 是否可用
     * @return useable 是否可用
     */
    public String getUseable() {
        return useable;
    }

    /**
     * 是否可用
     * @param useable 是否可用
     */
    public void setUseable(String useable) {
        this.useable = useable == null ? null : useable.trim();
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}