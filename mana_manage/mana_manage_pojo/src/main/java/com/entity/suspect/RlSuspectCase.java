package com.entity.suspect;

import java.util.Date;

/**
 * 描述:rl_suspect_case表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-02-03
 */
public class RlSuspectCase {
    /**
     * 主键 
     */
    private String id;

    /**
     * 重点人员编号
     */
    private String suspectId;

    /**
     * 案件编号
     */
    private String caseId;

    /**
     * 该重点人员在组织中的角色，字典数据“40涉案涉警标记”   4000：前科；4001：已处罚；4002：未处置
     */
    private Integer label;

    /**
     * 0标识未人工确认，1标识人工确认，默认0
     */
    private Integer relation;

    /**
     * 操作人编码
     */
    private String optPCode;

    /**
     * 操作人姓名
     */
    private String optPName;

    /**
     * 备注
     */
    private String demo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
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
     * 重点人员编号
     * @return SUSPECT_ID 重点人员编号
     */
    public String getSuspectId() {
        return suspectId;
    }

    /**
     * 重点人员编号
     * @param suspectId 重点人员编号
     */
    public void setSuspectId(String suspectId) {
        this.suspectId = suspectId == null ? null : suspectId.trim();
    }

    /**
     * 案件编号
     * @return CASE_ID 案件编号
     */
    public String getCaseId() {
        return caseId;
    }

    /**
     * 案件编号
     * @param caseId 案件编号
     */
    public void setCaseId(String caseId) {
        this.caseId = caseId == null ? null : caseId.trim();
    }

    /**
     * 该重点人员在组织中的角色，字典数据“40涉案涉警标记”   4000：前科；4001：已处罚；4002：未处置
     * @return LABEL 该重点人员在组织中的角色，字典数据“40涉案涉警标记”   4000：前科；4001：已处罚；4002：未处置
     */
    public Integer getLabel() {
        return label;
    }

    /**
     * 该重点人员在组织中的角色，字典数据“40涉案涉警标记”   4000：前科；4001：已处罚；4002：未处置
     * @param label 该重点人员在组织中的角色，字典数据“40涉案涉警标记”   4000：前科；4001：已处罚；4002：未处置
     */
    public void setLabel(Integer label) {
        this.label = label;
    }

    /**
     * 0标识未人工确认，1标识人工确认，默认0
     * @return RELATION 0标识未人工确认，1标识人工确认，默认0
     */
    public Integer getRelation() {
        return relation;
    }

    /**
     * 0标识未人工确认，1标识人工确认，默认0
     * @param relation 0标识未人工确认，1标识人工确认，默认0
     */
    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    /**
     * 操作人编码
     * @return OPT_P_CODE 操作人编码
     */
    public String getOptPCode() {
        return optPCode;
    }

    /**
     * 操作人编码
     * @param optPCode 操作人编码
     */
    public void setOptPCode(String optPCode) {
        this.optPCode = optPCode == null ? null : optPCode.trim();
    }

    /**
     * 操作人姓名
     * @return OPT_P_NAME 操作人姓名
     */
    public String getOptPName() {
        return optPName;
    }

    /**
     * 操作人姓名
     * @param optPName 操作人姓名
     */
    public void setOptPName(String optPName) {
        this.optPName = optPName == null ? null : optPName.trim();
    }

    /**
     * 备注
     * @return DEMO 备注
     */
    public String getDemo() {
        return demo;
    }

    /**
     * 备注
     * @param demo 备注
     */
    public void setDemo(String demo) {
        this.demo = demo == null ? null : demo.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     * @return MODIFY_TIME 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}