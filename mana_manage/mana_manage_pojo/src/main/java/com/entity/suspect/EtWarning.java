package com.entity.suspect;

import java.util.Date;

/**
 * 描述:et_warning表的实体类
 *
 * @author: weipc
 * @创建时间: 2018-01-05
 */
public class EtWarning {
    /**
     * 主键
     */
    protected String id;

    /**
     * 预警信息编号
     */
    protected String warningId;

    /**
     * 布控任务ID
     */
    protected String ctrlTaskId;

    /**
     * 布控人员编号
     */
    protected String bCtrlPCode;

    /**
     * 布控人员姓名
     */
    protected String bCtrlName;

    /**
     * 布控人员身份证
     */
    protected String bCtrlIdcardNum;

    /**
     * 预警产生时间
     */
    protected Date warningTime;

    /**
     * 预警产生地址
     */
    protected String warningAddress;

    /**
     * 预警产生类别 字典“预警产生类别”
     */
    protected Integer warningClass;

    /**
     * 关联数据编号
     */
    protected String relationNo;

    /**
     * 管控单位编码
     */
    protected String manaUnitCode;

    /**
     * 管控单位名称
     */
    protected String manaUnitName;

    /**
     * 管控人编码
     */
    protected String manaPCode;

    /**
     * 管控人姓名
     */
    protected String manaPName;

    /**
     * 系统创建时间
     */
    protected Date creatTime;

    /**
     * 系统修改时间
     */
    protected Date modifyTime;

    /**
     * 预警详情
     */
    protected String warningDetal;

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
     * 预警信息编号
     *
     * @return WARNING_ID 预警信息编号
     */
    public String getWarningId() {
        return warningId;
    }

    /**
     * 预警信息编号
     *
     * @param warningId 预警信息编号
     */
    public void setWarningId(String warningId) {
        this.warningId = warningId == null ? null : warningId.trim();
    }

    /**
     * 布控任务ID
     *
     * @return CTRL_TASK_ID 布控任务ID
     */
    public String getCtrlTaskId() {
        return ctrlTaskId;
    }

    /**
     * 布控任务ID
     *
     * @param ctrlTaskId 布控任务ID
     */
    public void setCtrlTaskId(String ctrlTaskId) {
        this.ctrlTaskId = ctrlTaskId == null ? null : ctrlTaskId.trim();
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
     * 预警产生时间
     *
     * @return WARNING_TIME 预警产生时间
     */
    public Date getWarningTime() {
        return warningTime;
    }

    /**
     * 预警产生时间
     *
     * @param warningTime 预警产生时间
     */
    public void setWarningTime(Date warningTime) {
        this.warningTime = warningTime;
    }

    /**
     * 预警产生地址
     *
     * @return WARNING_ADDRESS 预警产生地址
     */
    public String getWarningAddress() {
        return warningAddress;
    }

    /**
     * 预警产生地址
     *
     * @param warningAddress 预警产生地址
     */
    public void setWarningAddress(String warningAddress) {
        this.warningAddress = warningAddress == null ? null : warningAddress.trim();
    }

    /**
     * 预警产生类别 字典“预警产生类别”
     *
     * @return WARNING_CLASS 预警产生类别 字典“预警产生类别”
     */
    public Integer getWarningClass() {
        return warningClass;
    }

    /**
     * 预警产生类别 字典“预警产生类别”
     *
     * @param warningClass 预警产生类别 字典“预警产生类别”
     */
    public void setWarningClass(Integer warningClass) {
        this.warningClass = warningClass;
    }

    /**
     * 管控单位编码
     *
     * @return MANA_UNIT_CODE 管控单位编码
     */
    public String getManaUnitCode() {
        return manaUnitCode;
    }

    /**
     * 管控单位编码
     *
     * @param manaUnitCode 管控单位编码
     */
    public void setManaUnitCode(String manaUnitCode) {
        this.manaUnitCode = manaUnitCode == null ? null : manaUnitCode.trim();
    }

    /**
     * 管控单位名称
     *
     * @return MANA_UNIT_NAME 管控单位名称
     */
    public String getManaUnitName() {
        return manaUnitName;
    }

    /**
     * 管控单位名称
     *
     * @param manaUnitName 管控单位名称
     */
    public void setManaUnitName(String manaUnitName) {
        this.manaUnitName = manaUnitName == null ? null : manaUnitName.trim();
    }

    /**
     * 管控人编码
     *
     * @return MANA_P_CODE 管控人编码
     */
    public String getManaPCode() {
        return manaPCode;
    }

    /**
     * 管控人编码
     *
     * @param manaPCode 管控人编码
     */
    public void setManaPCode(String manaPCode) {
        this.manaPCode = manaPCode == null ? null : manaPCode.trim();
    }

    /**
     * 管控人姓名
     *
     * @return MANA_P_NAME 管控人姓名
     */
    public String getManaPName() {
        return manaPName;
    }

    /**
     * 管控人姓名
     *
     * @param manaPName 管控人姓名
     */
    public void setManaPName(String manaPName) {
        this.manaPName = manaPName == null ? null : manaPName.trim();
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
     * 预警详情
     *
     * @return WARNING_DETAL 预警详情
     */
    public String getWarningDetal() {
        return warningDetal;
    }

    /**
     * 预警详情
     *
     * @param warningDetal 预警详情
     */
    public void setWarningDetal(String warningDetal) {
        this.warningDetal = warningDetal == null ? null : warningDetal.trim();
    }

    public String getbCtrlPCode() {
        return bCtrlPCode;
    }

    public void setbCtrlPCode(String bCtrlPCode) {
        this.bCtrlPCode = bCtrlPCode;
    }

    public String getbCtrlName() {
        return bCtrlName;
    }

    public void setbCtrlName(String bCtrlName) {
        this.bCtrlName = bCtrlName;
    }

    public String getRelationNo() {
        return relationNo;
    }

    public void setRelationNo(String relationNo) {
        this.relationNo = relationNo;
    }
}