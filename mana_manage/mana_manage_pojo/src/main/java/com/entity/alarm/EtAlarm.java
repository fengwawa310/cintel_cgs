package com.entity.alarm;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 描述:et_alarm表的实体类
 *
 * @author: weipc
 * @创建时间: 2018-01-02
 */
public class EtAlarm implements Serializable {

    /**
     * 接警ID
     */
    private String id;
    /**
     * 接警编号
     */
    private String alarmNo;

    /**
     * 案件编号
     */
    private String caseNo;

    /**
     * 接警人编号
     */
    private String officerCode;

    /**
     * 接警人姓名
     */
    private String officerName;

    /**
     * 接警单位编码
     */
    private String unitCode;

    /**
     * 接警单位名称
     */
    private String unitName;

    /**
     * 接警时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String alarmTime;
    /**
     * 接警时间Str
     */
    private String alarmTimeStr;

    /**
     * 报警人姓名
     */
    private String alarmPersonName;

    /**
     * 报警人联系电话
     */
    private String alarmPersonPhone;

    /**
     * 发案地点
     */
    private String locationCase;

    /**
     * 警情分类 字典“警情分类”
     */
    private Integer alarmClass;
    private String alarmClassName;

    /**
     * 数据来源 字典“数据来源”
     */
    private Integer sourceType;
    private String sourceTypeName;

    /**
     * 归档标识：0未归档；1已归档；默认0
     */
    private Integer isArchive;

    /**
     * 归档至历史的理由（说明）
     */
    private String archiveDesc;

    /**
     * 删除标识：0未删除；1已删除；默认0
     */
    private Integer isAbandon;

    /**
     * 删除理由（说明）
     */
    private String abandonDesc;


    /**
     * 警情描述
     */
    private String alarmDesc;
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
     * 预留字段 保存真实录入人编码
     */
    private String reserveA;

    /**
     * 预留字段 保存真实录入人姓名
     */
    private String reserveB;
    /**
     * 预留字段 保存真实录入单位编码
     */
    private String reserveC;

    /**
     * 预留字段 保存真实录入单位名称
     */
    private String reserveD;

    /**
     * 分页查询起始位置
     */
    private Integer start;
    /**
     * 分页查询每页个数
     */
    private Integer length;
    /**
     * 用户等级
     */
    private String level;

    /**
     * 分权分域标识
     */
    private String deceSigns;
    /**
     * 人工标记标志
     */
    private Integer lable;
    /**
     * 人工确认关联标志
     */
    private Integer relation;

    public Integer getLable() {
        return lable;
    }

    public void setLable(Integer lable) {
        this.lable = lable;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    public String getDeceSigns() {
        return deceSigns;
    }

    public void setDeceSigns(String deceSigns) {
        this.deceSigns = deceSigns;
    }

    /**
     * 接警编号
     *
     * @return ALARM_NO 接警编号
     */
    public String getAlarmNo() {
        return alarmNo;
    }

    /**
     * 接警编号
     *
     * @param alarmNo 接警编号
     */
    public void setAlarmNo(String alarmNo) {
        this.alarmNo = alarmNo == null ? null : alarmNo.trim();
    }

    /**
     * 案件编号
     *
     * @return CASE_NO 案件编号
     */
    public String getCaseNo() {
        return caseNo;
    }

    /**
     * 案件编号
     *
     * @param caseNo 案件编号
     */
    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo == null ? null : caseNo.trim();
    }

    /**
     * 接警人编号
     *
     * @return OFFICER_CODE 接警人编号
     */
    public String getOfficerCode() {
        return officerCode;
    }

    /**
     * 接警人编号
     *
     * @param officerCode 接警人编号
     */
    public void setOfficerCode(String officerCode) {
        this.officerCode = officerCode == null ? null : officerCode.trim();
    }

    /**
     * 接警人姓名
     *
     * @return OFFICER_NAME 接警人姓名
     */
    public String getOfficerName() {
        return officerName;
    }

    /**
     * 接警人姓名
     *
     * @param officerName 接警人姓名
     */
    public void setOfficerName(String officerName) {
        this.officerName = officerName == null ? null : officerName.trim();
    }

    /**
     * 接警单位编码
     *
     * @return UNIT_CODE 接警单位编码
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * 接警单位编码
     *
     * @param unitCode 接警单位编码
     */
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode == null ? null : unitCode.trim();
    }

    /**
     * 接警单位名称
     *
     * @return UNIT_NAME 接警单位名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 接警单位名称
     *
     * @param unitName 接警单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

/*    *//**
     * 接警时间
     * @return ALARM_TIME 接警时间
     *//*
    public Date getAlarmTime() {
        return alarmTime;
    }

    *//**
     * 接警时间
     * @param alarmTime 接警时间
     *//*
    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }*/


    /**
     * 报警人姓名
     *
     * @return ALARM_PERSON_NAME 报警人姓名
     */
    public String getAlarmPersonName() {
        return alarmPersonName;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    /**
     * 报警人姓名
     *
     * @param alarmPersonName 报警人姓名
     */
    public void setAlarmPersonName(String alarmPersonName) {
        this.alarmPersonName = alarmPersonName == null ? null : alarmPersonName.trim();
    }

    /**
     * 报警人联系电话
     *
     * @return ALARM_PERSON_PHONE 报警人联系电话
     */
    public String getAlarmPersonPhone() {
        return alarmPersonPhone;
    }

    /**
     * 报警人联系电话
     *
     * @param alarmPersonPhone 报警人联系电话
     */
    public void setAlarmPersonPhone(String alarmPersonPhone) {
        this.alarmPersonPhone = alarmPersonPhone == null ? null : alarmPersonPhone.trim();
    }

    /**
     * 发案地点
     *
     * @return LOCATION_CASE 发案地点
     */
    public String getLocationCase() {
        return locationCase;
    }

    /**
     * 发案地点
     *
     * @param locationCase 发案地点
     */
    public void setLocationCase(String locationCase) {
        this.locationCase = locationCase == null ? null : locationCase.trim();
    }

    /**
     * 警情分类 字典“警情分类”
     *
     * @return ALARM_CLASS 警情分类 字典“警情分类”
     */
    public Integer getAlarmClass() {
        return alarmClass;
    }

    /**
     * 警情分类 字典“警情分类”
     *
     * @param alarmClass 警情分类 字典“警情分类”
     */
    public void setAlarmClass(Integer alarmClass) {
        this.alarmClass = alarmClass;
    }

    /**
     * 数据来源 字典“数据来源”
     *
     * @return SOURCE_TYPE 数据来源 字典“数据来源”
     */
    public Integer getSourceType() {
        return sourceType;
    }

    /**
     * 数据来源 字典“数据来源”
     *
     * @param sourceType 数据来源 字典“数据来源”
     */
    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * 归档标识：0未归档；1已归档；默认0
     *
     * @return IS_ARCHIVE 归档标识：0未归档；1已归档；默认0
     */
    public Integer getIsArchive() {
        return isArchive;
    }

    /**
     * 归档标识：0未归档；1已归档；默认0
     *
     * @param isArchive 归档标识：0未归档；1已归档；默认0
     */
    public void setIsArchive(Integer isArchive) {
        this.isArchive = isArchive;
    }

    /**
     * 归档至历史的理由（说明）
     *
     * @return ARCHIVE_DESC 归档至历史的理由（说明）
     */
    public String getArchiveDesc() {
        return archiveDesc;
    }

    /**
     * 归档至历史的理由（说明）
     *
     * @param archiveDesc 归档至历史的理由（说明）
     */
    public void setArchiveDesc(String archiveDesc) {
        this.archiveDesc = archiveDesc == null ? null : archiveDesc.trim();
    }

    /**
     * 删除标识：0未删除；1已删除；默认0
     *
     * @return IS_ABANDON 删除标识：0未删除；1已删除；默认0
     */
    public Integer getIsAbandon() {
        return isAbandon;
    }

    /**
     * 删除标识：0未删除；1已删除；默认0
     *
     * @param isAbandon 删除标识：0未删除；1已删除；默认0
     */
    public void setIsAbandon(Integer isAbandon) {
        this.isAbandon = isAbandon;
    }

    /**
     * 删除理由（说明）
     *
     * @return ABANDON_DESC 删除理由（说明）
     */
    public String getAbandonDesc() {
        return abandonDesc;
    }

    /**
     * 删除理由（说明）
     *
     * @param abandonDesc 删除理由（说明）
     */
    public void setAbandonDesc(String abandonDesc) {
        this.abandonDesc = abandonDesc == null ? null : abandonDesc.trim();
    }

    /**
     * 警情描述
     *
     * @return ALARM_DESC 警情描述
     */
    public String getAlarmDesc() {
        return alarmDesc;
    }

    /**
     * 警情描述
     *
     * @param alarmDesc 警情描述
     */
    public void setAlarmDesc(String alarmDesc) {
        this.alarmDesc = alarmDesc == null ? null : alarmDesc.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getAlarmTimeStr() {
        return alarmTimeStr;
    }

    public void setAlarmTimeStr(String alarmTimeStr) {
        this.alarmTimeStr = alarmTimeStr;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAlarmClassName() {
        return alarmClassName;
    }

    public void setAlarmClassName(String alarmClassName) {
        this.alarmClassName = alarmClassName;
    }

    public String getSourceTypeName() {
        return sourceTypeName;
    }

    public void setSourceTypeName(String sourceTypeName) {
        this.sourceTypeName = sourceTypeName;
    }

    public String getReserveA() {
        return reserveA;
    }

    public void setReserveA(String reserveA) {
        this.reserveA = reserveA;
    }

    public String getReserveB() {
        return reserveB;
    }

    public void setReserveB(String reserveB) {
        this.reserveB = reserveB;
    }

    public String getReserveC() {
        return reserveC;
    }

    public void setReserveC(String reserveC) {
        this.reserveC = reserveC;
    }

    public String getReserveD() {
        return reserveD;
    }

    public void setReserveD(String reserveD) {
        this.reserveD = reserveD;
    }
}