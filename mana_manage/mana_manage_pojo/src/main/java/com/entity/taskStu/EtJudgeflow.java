package com.entity.taskStu;

import java.util.Date;

/**
 * 描述:et_judgeflow表的实体类
 * @version
 * @author:  admin
 * @创建时间: 2018-03-06
 */
public class EtJudgeflow {
    /**
     *
     */
    private String id;

    /**
     * 关联的案件ID(et_case)
     */
    private String caseNo;

    /**
     * 研判类型 0 案件；1警情；
     */
    private Integer judgeType;

    /**
     * 256
     */
    private String title;

    /**
     * 研判任务流转状态 默认为0(刚刚生成记录，尚未进入流转)
     */
    private Integer flowState;

    /**
     * 审核单位编码
     */
    private String auditorUnit;

    /**
     * 审核人编码
     */
    private String auditorCode;

    /**
     * 审核意见
     */
    private String auditOpinion;

    /**
     * 审核操作时间
     */
    private Date auditTime;

    /**
     * 下发单位编码
     */
    private String issuedUnit;

    /**
     * 下发人编码
     */
    private String issuterCode;

    /**
     * 下发操作时间
     */
    private Date issuedTime;

    /**
     * 签收单位编码
     */
    private String signUnit;

    /**
     * 签收人编码
     */
    private String signCode;

    /**
     * 签收意见
     */
    private String signOpinion;

    /**
     * 签收操作时间
     */
    private Date signTime;

    /**
     *  研判人编码
     */
    private String judgeCode;

    /**
     * 研判人意见
     */
    private String judgeOpinion;

    /**
     * 研判操作时间
     */
    private Date judgeTime;

    /**
     * 系统创建时间
     */
    private Date creatTime;

    /**
     * 系统修改时间
     */
    private Date modifyTime;

    /**
     *
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 关联的案件ID(et_case)
     * @return CASE_NO 关联的案件ID(et_case)
     */
    public String getCaseNo() {
        return caseNo;
    }

    /**
     * 关联的案件ID(et_case)
     * @param caseNo 关联的案件ID(et_case)
     */
    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo == null ? null : caseNo.trim();
    }

    /**
     * 研判类型 0 案件；1警情；
     * @return JUDGE_TYPE 研判类型 0 案件；1警情；
     */
    public Integer getJudgeType() {
        return judgeType;
    }

    /**
     * 研判类型 0 案件；1警情；
     * @param judgeType 研判类型 0 案件；1警情；
     */
    public void setJudgeType(Integer judgeType) {
        this.judgeType = judgeType;
    }

    /**
     * 256
     * @return TITLE 256
     */
    public String getTitle() {
        return title;
    }

    /**
     * 256
     * @param title 256
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 研判任务流转状态 默认为0(刚刚生成记录，尚未进入流转)
     * @return FLOW_STATE 研判任务流转状态 默认为0(刚刚生成记录，尚未进入流转)
     */
    public Integer getFlowState() {
        return flowState;
    }

    /**
     * 研判任务流转状态 默认为0(刚刚生成记录，尚未进入流转)
     * @param flowState 研判任务流转状态 默认为0(刚刚生成记录，尚未进入流转)
     */
    public void setFlowState(Integer flowState) {
        this.flowState = flowState;
    }

    /**
     * 审核单位编码
     * @return AUDITOR_UNIT 审核单位编码
     */
    public String getAuditorUnit() {
        return auditorUnit;
    }

    /**
     * 审核单位编码
     * @param auditorUnit 审核单位编码
     */
    public void setAuditorUnit(String auditorUnit) {
        this.auditorUnit = auditorUnit == null ? null : auditorUnit.trim();
    }

    /**
     * 审核人编码
     * @return AUDITOR_CODE 审核人编码
     */
    public String getAuditorCode() {
        return auditorCode;
    }

    /**
     * 审核人编码
     * @param auditorCode 审核人编码
     */
    public void setAuditorCode(String auditorCode) {
        this.auditorCode = auditorCode == null ? null : auditorCode.trim();
    }

    /**
     * 审核意见
     * @return AUDIT_OPINION 审核意见
     */
    public String getAuditOpinion() {
        return auditOpinion;
    }

    /**
     * 审核意见
     * @param auditOpinion 审核意见
     */
    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion == null ? null : auditOpinion.trim();
    }

    /**
     * 审核操作时间
     * @return AUDIT_TIME 审核操作时间
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * 审核操作时间
     * @param auditTime 审核操作时间
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * 下发单位编码
     * @return ISSUED _UNIT 下发单位编码
     */
    public String getIssuedUnit() {
        return issuedUnit;
    }

    /**
     * 下发单位编码
     * @param issuedUnit 下发单位编码
     */
    public void setIssuedUnit(String issuedUnit) {
        this.issuedUnit = issuedUnit == null ? null : issuedUnit.trim();
    }

    /**
     * 下发人编码
     * @return ISSUTER_CODE 下发人编码
     */
    public String getIssuterCode() {
        return issuterCode;
    }

    /**
     * 下发人编码
     * @param issuterCode 下发人编码
     */
    public void setIssuterCode(String issuterCode) {
        this.issuterCode = issuterCode == null ? null : issuterCode.trim();
    }

    /**
     * 下发操作时间
     * @return ISSUED_TIME 下发操作时间
     */
    public Date getIssuedTime() {
        return issuedTime;
    }

    /**
     * 下发操作时间
     * @param issuedTime 下发操作时间
     */
    public void setIssuedTime(Date issuedTime) {
        this.issuedTime = issuedTime;
    }

    /**
     * 签收单位编码
     * @return SIGN_UNIT 签收单位编码
     */
    public String getSignUnit() {
        return signUnit;
    }

    /**
     * 签收单位编码
     * @param signUnit 签收单位编码
     */
    public void setSignUnit(String signUnit) {
        this.signUnit = signUnit == null ? null : signUnit.trim();
    }

    /**
     * 签收人编码
     * @return SIGN_CODE 签收人编码
     */
    public String getSignCode() {
        return signCode;
    }

    /**
     * 签收人编码
     * @param signCode 签收人编码
     */
    public void setSignCode(String signCode) {
        this.signCode = signCode == null ? null : signCode.trim();
    }

    /**
     * 签收意见
     * @return SIGN_OPINION 签收意见
     */
    public String getSignOpinion() {
        return signOpinion;
    }

    /**
     * 签收意见
     * @param signOpinion 签收意见
     */
    public void setSignOpinion(String signOpinion) {
        this.signOpinion = signOpinion == null ? null : signOpinion.trim();
    }

    /**
     * 签收操作时间
     * @return SIGN_TIME 签收操作时间
     */
    public Date getSignTime() {
        return signTime;
    }

    /**
     * 签收操作时间
     * @param signTime 签收操作时间
     */
    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    /**
     *  研判人编码
     * @return JUDGE_CODE  研判人编码
     */
    public String getJudgeCode() {
        return judgeCode;
    }

    /**
     *  研判人编码
     * @param judgeCode  研判人编码
     */
    public void setJudgeCode(String judgeCode) {
        this.judgeCode = judgeCode == null ? null : judgeCode.trim();
    }

    /**
     * 研判人意见
     * @return JUDGE_OPINION 研判人意见
     */
    public String getJudgeOpinion() {
        return judgeOpinion;
    }

    /**
     * 研判人意见
     * @param judgeOpinion 研判人意见
     */
    public void setJudgeOpinion(String judgeOpinion) {
        this.judgeOpinion = judgeOpinion == null ? null : judgeOpinion.trim();
    }

    /**
     * 研判操作时间
     * @return JUDGE_TIME 研判操作时间
     */
    public Date getJudgeTime() {
        return judgeTime;
    }

    /**
     * 研判操作时间
     * @param judgeTime 研判操作时间
     */
    public void setJudgeTime(Date judgeTime) {
        this.judgeTime = judgeTime;
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

    public EtJudgeflow() {
    }

    public EtJudgeflow(String id, String caseNo, Integer judgeType, String title, Integer flowState, String auditorUnit, String auditorCode, String auditOpinion, Date auditTime, String issuedUnit, String issuterCode, Date issuedTime, String signUnit, String signCode, String signOpinion, Date signTime, String judgeCode, String judgeOpinion, Date judgeTime, Date creatTime, Date modifyTime) {
        this.id = id;
        this.caseNo = caseNo;
        this.judgeType = judgeType;
        this.title = title;
        this.flowState = flowState;
        this.auditorUnit = auditorUnit;
        this.auditorCode = auditorCode;
        this.auditOpinion = auditOpinion;
        this.auditTime = auditTime;
        this.issuedUnit = issuedUnit;
        this.issuterCode = issuterCode;
        this.issuedTime = issuedTime;
        this.signUnit = signUnit;
        this.signCode = signCode;
        this.signOpinion = signOpinion;
        this.signTime = signTime;
        this.judgeCode = judgeCode;
        this.judgeOpinion = judgeOpinion;
        this.judgeTime = judgeTime;
        this.creatTime = creatTime;
        this.modifyTime = modifyTime;
    }
}