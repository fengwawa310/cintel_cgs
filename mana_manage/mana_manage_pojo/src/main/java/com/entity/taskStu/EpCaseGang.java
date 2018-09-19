package com.entity.taskStu;

import java.util.Date;

/**
 * 描述:ep_case_gang表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-03-20
 */
public class EpCaseGang {
    /**
     * 主键
     */
    private String id;

    /**
     * 作案人类型（字典数据：团伙、个人）
     */
    private Integer perpetratorClass;

    /**
     * 作案者
     */
    private String perpetrator;

    /**
     * 案件名称
     */
    private String caseTitle;

    /**
     * 案件编号
     */
    private String caseNo;

    /**
     * 简要案情
     */
    private String caseDesc;

    /**
     * 涉案嫌疑人
     */
    private String caseSuspect;

    /**
     * 未处理人员
     */
    private String untreatedPersonnel;

    /**
     * 受害人及证人
     */
    private String witness;

    /**
     * 存在问题
     */
    private String existingProblems;

    /**
     * 备注
     */
    private String remark;

    /**
     * 录入单位编码
     */
    private String inputUnitCode;

    /**
     * 录入单位名称
     */
    private String inputUnitName;

    /**
     * 录入人编码
     */
    private String inputerCode;

    /**
     * 录入人姓名
     */
    private String inputerName;

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
     * 作案人类型（字典数据：团伙、个人）
     * @return PERPETRATOR_CLASS 作案人类型（字典数据：团伙、个人）
     */
    public Integer getPerpetratorClass() {
        return perpetratorClass;
    }

    /**
     * 作案人类型（字典数据：团伙、个人）
     * @param perpetratorClass 作案人类型（字典数据：团伙、个人）
     */
    public void setPerpetratorClass(Integer perpetratorClass) {
        this.perpetratorClass = perpetratorClass;
    }

    /**
     * 作案者
     * @return PERPETRATOR 作案者
     */
    public String getPerpetrator() {
        return perpetrator;
    }

    /**
     * 作案者
     * @param perpetrator 作案者
     */
    public void setPerpetrator(String perpetrator) {
        this.perpetrator = perpetrator == null ? null : perpetrator.trim();
    }

    /**
     * 案件名称
     * @return CASE_TITLE 案件名称
     */
    public String getCaseTitle() {
        return caseTitle;
    }

    /**
     * 案件名称
     * @param caseTitle 案件名称
     */
    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle == null ? null : caseTitle.trim();
    }

    /**
     * 案件编号
     * @return CASE_NO 案件编号
     */
    public String getCaseNo() {
        return caseNo;
    }

    /**
     * 案件编号
     * @param caseNo 案件编号
     */
    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo == null ? null : caseNo.trim();
    }

    /**
     * 简要案情
     * @return CASE_DESC 简要案情
     */
    public String getCaseDesc() {
        return caseDesc;
    }

    /**
     * 简要案情
     * @param caseDesc 简要案情
     */
    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc == null ? null : caseDesc.trim();
    }

    /**
     * 涉案嫌疑人
     * @return CASE_SUSPECT 涉案嫌疑人
     */
    public String getCaseSuspect() {
        return caseSuspect;
    }

    /**
     * 涉案嫌疑人
     * @param caseSuspect 涉案嫌疑人
     */
    public void setCaseSuspect(String caseSuspect) {
        this.caseSuspect = caseSuspect == null ? null : caseSuspect.trim();
    }

    /**
     * 未处理人员
     * @return UNTREATED_PERSONNEL 未处理人员
     */
    public String getUntreatedPersonnel() {
        return untreatedPersonnel;
    }

    /**
     * 未处理人员
     * @param untreatedPersonnel 未处理人员
     */
    public void setUntreatedPersonnel(String untreatedPersonnel) {
        this.untreatedPersonnel = untreatedPersonnel == null ? null : untreatedPersonnel.trim();
    }

    /**
     * 受害人及证人
     * @return WITNESS 受害人及证人
     */
    public String getWitness() {
        return witness;
    }

    /**
     * 受害人及证人
     * @param witness 受害人及证人
     */
    public void setWitness(String witness) {
        this.witness = witness == null ? null : witness.trim();
    }

    /**
     * 存在问题
     * @return EXISTING_PROBLEMS 存在问题
     */
    public String getExistingProblems() {
        return existingProblems;
    }

    /**
     * 存在问题
     * @param existingProblems 存在问题
     */
    public void setExistingProblems(String existingProblems) {
        this.existingProblems = existingProblems == null ? null : existingProblems.trim();
    }

    /**
     * 备注
     * @return REMARK 备注
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

    /**
     * 录入单位编码
     * @return INPUT_UNIT_CODE 录入单位编码
     */
    public String getInputUnitCode() {
        return inputUnitCode;
    }

    /**
     * 录入单位编码
     * @param inputUnitCode 录入单位编码
     */
    public void setInputUnitCode(String inputUnitCode) {
        this.inputUnitCode = inputUnitCode == null ? null : inputUnitCode.trim();
    }

    /**
     * 录入单位名称
     * @return INPUT_UNIT_NAME 录入单位名称
     */
    public String getInputUnitName() {
        return inputUnitName;
    }

    /**
     * 录入单位名称
     * @param inputUnitName 录入单位名称
     */
    public void setInputUnitName(String inputUnitName) {
        this.inputUnitName = inputUnitName == null ? null : inputUnitName.trim();
    }

    /**
     * 录入人编码
     * @return INPUTER_CODE 录入人编码
     */
    public String getInputerCode() {
        return inputerCode;
    }

    /**
     * 录入人编码
     * @param inputerCode 录入人编码
     */
    public void setInputerCode(String inputerCode) {
        this.inputerCode = inputerCode == null ? null : inputerCode.trim();
    }

    /**
     * 录入人姓名
     * @return INPUTER_NAME 录入人姓名
     */
    public String getInputerName() {
        return inputerName;
    }

    /**
     * 录入人姓名
     * @param inputerName 录入人姓名
     */
    public void setInputerName(String inputerName) {
        this.inputerName = inputerName == null ? null : inputerName.trim();
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