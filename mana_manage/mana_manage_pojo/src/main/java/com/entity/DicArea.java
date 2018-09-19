package com.entity;

import java.util.Date;

/**
 * 描述:sys_area表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-01-12
 */
public class DicArea {
    /**
     * 编号(同区域编码相同)
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
     * 区域编码
     */
    private String code;

    /**
     * 区号
     */
    private String citycode;

    /**
     * 邮编
     */
    private String zoneCode;

    /**
     * 
     */
    private String pinyin;

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
     * 删除标记
     */
    private String delFlag;

    /**
     * 
     */
    private String jianpin;

    /**
     * 经度
     */
    private String lng;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 区域类型
     */
    private String type;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 编号(同区域编码相同)
     * @return ID 编号(同区域编码相同)
     */
    public String getId() {
        return id;
    }

    /**
     * 编号(同区域编码相同)
     * @param id 编号(同区域编码相同)
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 父级编号
     * @return PARENT_ID 父级编号
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
     * @return PARENT_IDS 所有父级编号
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
     * @return NAME 名称
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
     * @return SORT 排序
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
     * 区域编码
     * @return CODE 区域编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 区域编码
     * @param code 区域编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 区号
     * @return CITYCODE 区号
     */
    public String getCitycode() {
        return citycode;
    }

    /**
     * 区号
     * @param citycode 区号
     */
    public void setCitycode(String citycode) {
        this.citycode = citycode == null ? null : citycode.trim();
    }

    /**
     * 邮编
     * @return ZONE_CODE 邮编
     */
    public String getZoneCode() {
        return zoneCode;
    }

    /**
     * 邮编
     * @param zoneCode 邮编
     */
    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode == null ? null : zoneCode.trim();
    }

    /**
     * 
     * @return PINYIN 
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * 
     * @param pinyin 
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    /**
     * 创建者
     * @return CREATE_BY 创建者
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
     * @return CREATE_DATE 创建时间
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
     * @return UPDATE_BY 更新者
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
     * @return UPDATE_DATE 更新时间
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
     * 删除标记
     * @return DEL_FLAG 删除标记
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

    /**
     * 
     * @return JIANPIN 
     */
    public String getJianpin() {
        return jianpin;
    }

    /**
     * 
     * @param jianpin 
     */
    public void setJianpin(String jianpin) {
        this.jianpin = jianpin == null ? null : jianpin.trim();
    }

    /**
     * 经度
     * @return LNG 经度
     */
    public String getLng() {
        return lng;
    }

    /**
     * 经度
     * @param lng 经度
     */
    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    /**
     * 纬度
     * @return LAT 纬度
     */
    public String getLat() {
        return lat;
    }

    /**
     * 纬度
     * @param lat 纬度
     */
    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    /**
     * 区域类型
     * @return TYPE 区域类型
     */
    public String getType() {
        return type;
    }

    /**
     * 区域类型
     * @param type 区域类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 备注信息
     * @return REMARKS 备注信息
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
}