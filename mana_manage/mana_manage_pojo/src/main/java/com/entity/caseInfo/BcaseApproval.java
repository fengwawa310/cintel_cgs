package com.entity.caseInfo;

import java.io.Serializable;

/**
 * 描述:b_case_approval表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-01-02
 */
public class BcaseApproval {
    /**
     * 主键
     */
    private String id;

    /**
     * 案件ID
     */
    private String caseId;

    /**
     * 流程状态0:中队长初审1:大队长复审2:申请办结3:流程结束
     */
    private Integer processState;

    /**
     * 审批人编码
     */
    private String approverCode;

    /**
     * 审批人姓名
     */
    private String approverName;

    /**
     * 审批结果 字典“审批状态”
     */
    private Integer approverResult;

    /**
     * 审批意见
     */
    private String approverOpinion;

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
     * 案件ID
     * @return CASE_ID 案件ID
     */
    public String getCaseId() {
        return caseId;
    }

    /**
     * 案件ID
     * @param caseId 案件ID
     */
    public void setCaseId(String caseId) {
        this.caseId = caseId == null ? null : caseId.trim();
    }

    /**
     * 流程状态0:中队长初审1:大队长复审2:申请办结3:流程结束
     * @return PROCESS_STATE 流程状态0:中队长初审1:大队长复审2:申请办结3:流程结束
     */
    public Integer getProcessState() {
        return processState;
    }

    /**
     * 流程状态0:中队长初审1:大队长复审2:申请办结3:流程结束
     * @param processState 流程状态0:中队长初审1:大队长复审2:申请办结3:流程结束
     */
    public void setProcessState(Integer processState) {
        this.processState = processState;
    }

    /**
     * 审批人编码
     * @return APPROVER_CODE 审批人编码
     */
    public String getApproverCode() {
        return approverCode;
    }

    /**
     * 审批人编码
     * @param approverCode 审批人编码
     */
    public void setApproverCode(String approverCode) {
        this.approverCode = approverCode == null ? null : approverCode.trim();
    }

    /**
     * 审批人姓名
     * @return APPROVER_NAME 审批人姓名
     */
    public String getApproverName() {
        return approverName;
    }

    /**
     * 审批人姓名
     * @param approverName 审批人姓名
     */
    public void setApproverName(String approverName) {
        this.approverName = approverName == null ? null : approverName.trim();
    }

    /**
     * 审批结果 字典“审批状态”
     * @return APPROVER_RESULT 审批结果 字典“审批状态”
     */
    public Integer getApproverResult() {
        return approverResult;
    }

    /**
     * 审批结果 字典“审批状态”
     * @param approverResult 审批结果 字典“审批状态”
     */
    public void setApproverResult(Integer approverResult) {
        this.approverResult = approverResult;
    }

    /**
     * 审批意见
     * @return APPROVER_OPINION 审批意见
     */
    public String getApproverOpinion() {
        return approverOpinion;
    }

    /**
     * 审批意见
     * @param approverOpinion 审批意见
     */
    public void setApproverOpinion(String approverOpinion) {
        this.approverOpinion = approverOpinion == null ? null : approverOpinion.trim();
    }
}