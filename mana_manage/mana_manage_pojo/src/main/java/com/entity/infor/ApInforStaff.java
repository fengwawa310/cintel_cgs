package com.entity.infor;

import java.util.Date;

/**
 * 描述:ap_infor_staff表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-01-09
 */
public class ApInforStaff {
    /**
     * 主键
     */
    private String id;

    /**
     * 人员编号
     */
    private String staffNo;

    /**
     * 情报编号
     */
    private String inforNo;

    /**
     * 姓名
     */
    private String name;

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
     * 系统创建时间
     */
    private Date creatTime;

    /**
     * 系统修改时间
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
     * 人员编号
     * @return STAFF_NO 人员编号
     */
    public String getStaffNo() {
        return staffNo;
    }

    /**
     * 人员编号
     * @param staffNo 人员编号
     */
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo == null ? null : staffNo.trim();
    }

    /**
     * 情报编号
     * @return INFOR_NO 情报编号
     */
    public String getInforNo() {
        return inforNo;
    }

    /**
     * 情报编号
     * @param inforNo 情报编号
     */
    public void setInforNo(String inforNo) {
        this.inforNo = inforNo == null ? null : inforNo.trim();
    }

    /**
     * 姓名
     * @return NAME 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 身份证编号
     * @return IDCARD_NUM 身份证编号
     */
    public String getIdcardNum() {
        return idcardNum;
    }

    /**
     * 身份证编号
     * @param idcardNum 身份证编号
     */
    public void setIdcardNum(String idcardNum) {
        this.idcardNum = idcardNum == null ? null : idcardNum.trim();
    }

    /**
     * 性别 字典“人员性别”
     * @return GENDER 性别 字典“人员性别”
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 性别 字典“人员性别”
     * @param gender 性别 字典“人员性别”
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 联系电话
     * @return PHONE_NUM 联系电话
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 联系电话
     * @param phoneNum 联系电话
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    /**
     * 录入单位编码
     * @return ENTRY_UNIT 录入单位编码
     */
    public String getEntryUnit() {
        return entryUnit;
    }

    /**
     * 录入单位编码
     * @param entryUnit 录入单位编码
     */
    public void setEntryUnit(String entryUnit) {
        this.entryUnit = entryUnit == null ? null : entryUnit.trim();
    }

    /**
     * 录入人编码
     * @return ENTRY 录入人编码
     */
    public String getEntry() {
        return entry;
    }

    /**
     * 录入人编码
     * @param entry 录入人编码
     */
    public void setEntry(String entry) {
        this.entry = entry == null ? null : entry.trim();
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