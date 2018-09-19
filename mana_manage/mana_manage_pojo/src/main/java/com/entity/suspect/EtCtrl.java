package com.entity.suspect;

import com.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 描述:et_ctrl表的实体类
 *
 * @author: weipc
 * @创建时间: 2018-01-05
 */
public class EtCtrl {
    /**
     * 主键
     */
    private String id;

    /**
     * 布控任务编号
     */
    private String ctrlId;

    /**
     * 申请单位编码
     */
    private String applyUnitCode;

    /**
     * 申请单位名称
     */
    private String applyUnitName;

    /**
     * 申请人编码
     */
    private String applicantCode;

    /**
     * 申请人姓名
     */
    private String applicantName;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 布控任务级别 字典“布控任务级别”
     */
    private Integer ctrlTaskLevel;

    /**
     * 布控任务状态 字典“布控任务状态”
     */
    private Integer ctrlTaskState;

    /**
     * 布控任务类型 字典“布控任务类型”
     */
    private Integer ctrlTaskClass;

    /**
     * 被关注人员编号
     */
    private String bCtrlPCode;

    /**
     * 被关注人员姓名
     */
    private String bCtrlName;

    /**
     * 被关注人员身份证
     */
    private String bCtrlIdcardNum;

    /**
     * 被关注人员性别 字典“人员性别”
     */
    private Integer bCtrlGender;

    /**
     * 被关注人员手机号
     */
    private String bCtrlPhone;

    /**
     * 被关注人员车牌号
     */
    private String bCtrlPlateNum;

    /**
     * 被关注人员类型 字典“人员类别”
     */
    private Integer bCtrlPClass;

    /**
     * 系统创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date creatTime;

    /**
     * 系统修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifyTime;

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
     * 布控任务编号
     *
     * @return CTRL_ID 布控任务编号
     */
    public String getCtrlId() {
        return ctrlId;
    }

    /**
     * 布控任务编号
     *
     * @param ctrlId 布控任务编号
     */
    public void setCtrlId(String ctrlId) {
        this.ctrlId = ctrlId == null ? null : ctrlId.trim();
    }

    /**
     * 申请单位编码
     *
     * @return APPLY_UNIT_CODE 申请单位编码
     */
    public String getApplyUnitCode() {
        return applyUnitCode;
    }

    /**
     * 申请单位编码
     *
     * @param applyUnitCode 申请单位编码
     */
    public void setApplyUnitCode(String applyUnitCode) {
        this.applyUnitCode = applyUnitCode == null ? null : applyUnitCode.trim();
    }

    /**
     * 申请单位名称
     *
     * @return APPLY_UNIT_NAME 申请单位名称
     */
    public String getApplyUnitName() {
        return applyUnitName;
    }

    /**
     * 申请单位名称
     *
     * @param applyUnitName 申请单位名称
     */
    public void setApplyUnitName(String applyUnitName) {
        this.applyUnitName = applyUnitName == null ? null : applyUnitName.trim();
    }

    /**
     * 申请人编码
     *
     * @return APPLICANT_CODE 申请人编码
     */
    public String getApplicantCode() {
        return applicantCode;
    }

    /**
     * 申请人编码
     *
     * @param applicantCode 申请人编码
     */
    public void setApplicantCode(String applicantCode) {
        this.applicantCode = applicantCode == null ? null : applicantCode.trim();
    }

    /**
     * 申请人姓名
     *
     * @return APPLICANT_NAME 申请人姓名
     */
    public String getApplicantName() {
        return applicantName;
    }

    /**
     * 申请人姓名
     *
     * @param applicantName 申请人姓名
     */
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName == null ? null : applicantName.trim();
    }

    /**
     * 申请时间
     *
     * @return APPLY_TIME 申请时间
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * 申请时间
     *
     * @param applyTime 申请时间
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * 布控任务级别 字典“布控任务级别”
     *
     * @return CTRL_TASK_LEVEL 布控任务级别 字典“布控任务级别”
     */
    public Integer getCtrlTaskLevel() {
        return ctrlTaskLevel;
    }

    /**
     * 布控任务级别 字典“布控任务级别”
     *
     * @param ctrlTaskLevel 布控任务级别 字典“布控任务级别”
     */
    public void setCtrlTaskLevel(Integer ctrlTaskLevel) {
        this.ctrlTaskLevel = ctrlTaskLevel;
    }

    /**
     * 布控任务状态 字典“布控任务状态”
     *
     * @return CTRL_TASK_STATE 布控任务状态 字典“布控任务状态”
     */
    public Integer getCtrlTaskState() {
        return ctrlTaskState;
    }

    /**
     * 布控任务状态 字典“布控任务状态”
     *
     * @param ctrlTaskState 布控任务状态 字典“布控任务状态”
     */
    public void setCtrlTaskState(Integer ctrlTaskState) {
        this.ctrlTaskState = ctrlTaskState;
    }

    /**
     * 布控任务类型 字典“布控任务类型”
     *
     * @return CTRL_TASK_CLASS 布控任务类型 字典“布控任务类型”
     */
    public Integer getCtrlTaskClass() {
        return ctrlTaskClass;
    }

    /**
     * 布控任务类型 字典“布控任务类型”
     *
     * @param ctrlTaskClass 布控任务类型 字典“布控任务类型”
     */
    public void setCtrlTaskClass(Integer ctrlTaskClass) {
        this.ctrlTaskClass = ctrlTaskClass;
    }

    /**
     * 布控人员编号
     *
     * @return B_CTRL_P_CODE 布控人员编号
     */
    public String getbCtrlPCode() {
        return bCtrlPCode;
    }

    /**
     * 布控人员编号
     *
     * @param bCtrlPCode 布控人员编号
     */
    public void setbCtrlPCode(String bCtrlPCode) {
        this.bCtrlPCode = bCtrlPCode == null ? null : bCtrlPCode.trim();
    }

    /**
     * 布控人员姓名
     *
     * @return B_CTRL_NAME 布控人员姓名
     */
    public String getbCtrlName() {
        return bCtrlName;
    }

    /**
     * 布控人员姓名
     *
     * @param bCtrlName 布控人员姓名
     */
    public void setbCtrlName(String bCtrlName) {
        this.bCtrlName = bCtrlName == null ? null : bCtrlName.trim();
    }

    /**
     * 布控人员身份证
     *
     * @return B_CTRL_IDCARD_NUM 布控人员身份证
     */
    public String getbCtrlIdcardNum() {
        return bCtrlIdcardNum;
    }

    /**
     * 布控人员身份证
     *
     * @param bCtrlIdcardNum 布控人员身份证
     */
    public void setbCtrlIdcardNum(String bCtrlIdcardNum) {
        this.bCtrlIdcardNum = bCtrlIdcardNum == null ? null : bCtrlIdcardNum.trim();
    }

    /**
     * 布控人员性别 字典“人员性别”
     *
     * @return B_CTRL_GENDER 布控人员性别 字典“人员性别”
     */
    public Integer getbCtrlGender() {
        return bCtrlGender;
    }

    /**
     * 布控人员性别 字典“人员性别”
     *
     * @param bCtrlGender 布控人员性别 字典“人员性别”
     */
    public void setbCtrlGender(Integer bCtrlGender) {
        this.bCtrlGender = bCtrlGender;
    }

    /**
     * 布控人员手机号
     *
     * @return B_CTRL_PHONE 布控人员手机号
     */
    public String getbCtrlPhone() {
        return bCtrlPhone;
    }

    /**
     * 布控人员手机号
     *
     * @param bCtrlPhone 布控人员手机号
     */
    public void setbCtrlPhone(String bCtrlPhone) {
        this.bCtrlPhone = bCtrlPhone == null ? null : bCtrlPhone.trim();
    }

    /**
     * 布控人员车牌号
     *
     * @return B_CTRL_PLATE_NUM 布控人员车牌号
     */
    public String getbCtrlPlateNum() {
        return bCtrlPlateNum;
    }

    /**
     * 布控人员车牌号
     *
     * @param bCtrlPlateNum 布控人员车牌号
     */
    public void setbCtrlPlateNum(String bCtrlPlateNum) {
        this.bCtrlPlateNum = bCtrlPlateNum == null ? null : bCtrlPlateNum.trim();
    }

    /**
     * 人员类型 字典“人员类别”
     *
     * @return B_CTRL_P_CLASS 人员类型 字典“人员类别”
     */
    public Integer getbCtrlPClass() {
        return bCtrlPClass;
    }

    /**
     * 人员类型 字典“人员类别”
     *
     * @param bCtrlPClass 人员类型 字典“人员类别”
     */
    public void setbCtrlPClass(Integer bCtrlPClass) {
        this.bCtrlPClass = bCtrlPClass;
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
}