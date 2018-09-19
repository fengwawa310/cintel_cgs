package com.entity.caseInfo;

import java.util.Date;

/**
 * 描述:ap_staff表的实体类
 *
 * @author: weipc
 * @创建时间: 2018-01-11
 */
public class ApStaff {
    /**
     * 主键
     */
    private String id;

    /**
     * 人员编号
     */
    private String staffId;

    /**
     * 关联案件编号
     */
    private String relationNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证种类
     */
    private Integer idcardType;

    /**
     * 身份证编号
     */
    private String idcardNum;

    /**
     * 性别 字典“人员性别”
     */
    private Integer gender;

    /**
     * 联系电话
     */
    private String phoneNum;

    /**
     * 录入单位编码
     */
    private String entryUnit;

    /**
     * 录入人编码
     */
    private String entry;

    /**
     * 人员类型 字典“人员类别”
     */
    private Integer suspectClass;

    /**
     * 数据来源 字典“数据来源”
     */
    private Integer sourceType;

    /**
     * 系统创建时间
     */
    private Date creatTime;

    /**
     * 系统修改时间
     */
    private Date modifyTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 涉案  涉警类型，1、涉案；2、涉警；3及涉案又涉警
     */
    private String tagType;

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
     * 人员编号
     *
     * @return STAFF_ID 人员编号
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * 人员编号
     *
     * @param staffId 人员编号
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    /**
     * 关联案件编号
     *
     * @return RELATION_NO 关联案件编号
     */
    public String getRelationNo() {
        return relationNo;
    }

    /**
     * 关联案件编号
     *
     * @param relationNo 关联案件编号
     */
    public void setRelationNo(String relationNo) {
        this.relationNo = relationNo == null ? null : relationNo.trim();
    }

    /**
     * 姓名
     *
     * @return NAME 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 身份证编号
     *
     * @return IDCARD_NUM 身份证编号
     */
    public String getIdcardNum() {
        return idcardNum;
    }

    /**
     * 身份证编号
     *
     * @param idcardNum 身份证编号
     */
    public void setIdcardNum(String idcardNum) {
        this.idcardNum = idcardNum == null ? null : idcardNum.trim();
    }

    /**
     * 性别 字典“人员性别”
     *
     * @return GENDER 性别 字典“人员性别”
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 性别 字典“人员性别”
     *
     * @param gender 性别 字典“人员性别”
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 联系电话
     *
     * @return PHONE_NUM 联系电话
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 联系电话
     *
     * @param phoneNum 联系电话
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    /**
     * 录入单位编码
     *
     * @return ENTRY_UNIT 录入单位编码
     */
    public String getEntryUnit() {
        return entryUnit;
    }

    /**
     * 录入单位编码
     *
     * @param entryUnit 录入单位编码
     */
    public void setEntryUnit(String entryUnit) {
        this.entryUnit = entryUnit == null ? null : entryUnit.trim();
    }

    /**
     * 录入人编码
     *
     * @return ENTRY 录入人编码
     */
    public String getEntry() {
        return entry;
    }

    /**
     * 录入人编码
     *
     * @param entry 录入人编码
     */
    public void setEntry(String entry) {
        this.entry = entry == null ? null : entry.trim();
    }

    /**
     * 人员类型 字典“人员类别”
     *
     * @return SUSPECT_CLASS 人员类型 字典“人员类别”
     */
    public Integer getSuspectClass() {
        return suspectClass;
    }

    /**
     * 人员类型 字典“人员类别”
     *
     * @param suspectClass 人员类型 字典“人员类别”
     */
    public void setSuspectClass(Integer suspectClass) {
        this.suspectClass = suspectClass;
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
     * 系统创建时间
     *
     * @return CREAT_TIME 系统创建时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 系统创建时间
     *
     * @param creatTime 系统创建时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * 系统修改时间
     *
     * @return MODIFY_TIME 系统修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 系统修改时间
     *
     * @param modifyTime 系统修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 备注
     *
     * @return REMARK 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIdcardType() {
        return idcardType;
    }

    public void setIdcardType(Integer idcardType) {
        this.idcardType = idcardType;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }
}