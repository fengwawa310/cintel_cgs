package com.entity.sys;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述:sys_menu表的实体类
 * @version
 * @author:  admin
 * @创建时间: 2017-11-28
 */

public class SysMenu implements Serializable{

    /**
     * 编号
     */
    private String id;

    /**
     * 父级编号
     */
    private String parentId;

    /**
     * 所有父级编号
     */
    private String parentIds;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 链接
     */
    private String href;

    /**
     * 目标
     */
    private String target;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否在菜单中显示
     */
    private String isShow;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标记
     */
    private String delFlag;


    public boolean isRootNode() {
        return parentId == "0";
    }

    public SysMenu() {
    }
    /*全参构造*/
    public SysMenu(String id, String parentId, String parentIds, String name, Long sort, String href, String target, String icon, String isShow, String permission, String createBy, Date createDate, String updateBy, Date updateDate, String remarks, String delFlag) {
        this.id = id;
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.name = name;
        this.sort = sort;
        this.href = href;
        this.target = target;
        this.icon = icon;
        this.isShow = isShow;
        this.permission = permission;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.remarks = remarks;
        this.delFlag = delFlag;
    }

    /**
     * 编号
     * @return id 编号
     */
    public String getId() {
        return id;
    }

    /**
     * 编号
     * @param id 编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 父级编号
     * @return parent_id 父级编号
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 父级编号
     * @param parentId 父级编号
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 所有父级编号
     * @return parent_ids 所有父级编号
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * 所有父级编号
     * @param parentIds 所有父级编号
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 排序
     * @return sort 排序
     */
    public Long getSort() {
        return sort;
    }

    /**
     * 排序
     * @param sort 排序
     */
    public void setSort(Long sort) {
        this.sort = sort;
    }

    /**
     * 链接
     * @return href 链接
     */
    public String getHref() {
        return href;
    }

    /**
     * 链接
     * @param href 链接
     */
    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    /**
     * 目标
     * @return target 目标
     */
    public String getTarget() {
        return target;
    }

    /**
     * 目标
     * @param target 目标
     */
    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    /**
     * 图标
     * @return icon 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 图标
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 是否在菜单中显示
     * @return is_show 是否在菜单中显示
     */
    public String getIsShow() {
        return isShow;
    }

    /**
     * 是否在菜单中显示
     * @param isShow 是否在菜单中显示
     */
    public void setIsShow(String isShow) {
        this.isShow = isShow == null ? null : isShow.trim();
    }

    /**
     * 权限标识
     * @return permission 权限标识
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 权限标识
     * @param permission 权限标识
     */
    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    /**
     * 创建者
     * @return create_by 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 创建者
     * @param createBy 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * 创建时间
     * @return create_date 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 更新者
     * @return update_by 更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 更新者
     * @param updateBy 更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * 更新时间
     * @return update_date 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 备注信息
     * @return remarks 备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注信息
     * @param remarks 备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 删除标记
     * @return del_flag 删除标记
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标记
     * @param delFlag 删除标记
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }


}