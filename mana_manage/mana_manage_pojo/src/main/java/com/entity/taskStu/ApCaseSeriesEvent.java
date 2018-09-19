package com.entity.taskStu;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 描述:ap_case_series_event表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-03-22
 */
public class ApCaseSeriesEvent {

    /**
     * 主键
     */
    private String id;

    /**
     * 主案件编号 
     */
    private String caseNo;

    /**
     * 相似度阈值
     */
    private String similar;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 操作人代码
     */
    private String operatorCode;

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
     * 主案件编号 
     * @return CASE_NO 主案件编号 
     */
    public String getCaseNo() {
        return caseNo;
    }

    /**
     * 主案件编号 
     * @param caseNo 主案件编号 
     */
    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo == null ? null : caseNo.trim();
    }

    /**
     * 相似度阈值
     * @return SIMILAR 相似度阈值
     */
    public String getSimilar() {
        return similar;
    }

    /**
     * 相似度阈值
     * @param similar 相似度阈值
     */
    public void setSimilar(String similar) {
        this.similar = similar == null ? null : similar.trim();
    }

    /**
     * 操作人姓名
     * @return OPERATOR_NAME 操作人姓名
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 操作人姓名
     * @param operatorName 操作人姓名
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    /**
     * 操作人代码
     * @return OPERATOR_CODE 操作人代码
     */
    public String getOperatorCode() {
        return operatorCode;
    }

    /**
     * 操作人代码
     * @param operatorCode 操作人代码
     */
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode == null ? null : operatorCode.trim();
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