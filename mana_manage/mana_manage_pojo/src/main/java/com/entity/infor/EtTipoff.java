package com.entity.infor;

import java.util.Date;

/**
 * 描述:et_tipoff表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-02-28
 */
public class EtTipoff {
	// 已Json格式传入用户SysUser 王占京
	private String sysuser; 
    public String getSysuser() {
		return sysuser;
	}

	public void setSysuser(String sysuser) {
		this.sysuser = sysuser;
	}

	/**
     * 举报ID、主键
     */
    private String tipoffId;

    /**
     * 举报人姓名
     */
    private String tipoffReporter;

    /**
     * 举报人电话
     */
    private String reporterPhone;

    /**
     * 举报人身份证号
     */
    private String reporterIdcard;

    /**
     * 涉案类别 字典“案件类别”
     */
    private Integer caseClass;
    
    private String caseClassName;

    /**
     * 涉案类别为其他时，备注
     */
    private String caseClassMemo;


    /**
     * 案件区域（单位机构ID）
     */
    private String caseUnit;
    
    private String caseUnitName;

    /**
     * 被举报人姓名
     */
    private String beingReported;

    /**
     * 被举报人电话
     */
    private String beingReportedPhone;

    /**
     * 被举报人身份证号
     */
    private String beingReportedIdcard;

    /**
     * 线索标题
     */
    private String clueTitle;

    /**
     * 线索内容
     */
    private String clueContent;

    /**
     * 研判结论
     */
    private String judgeContent;

    /**
     * 操作类型(1-未审核 2-已审核 3-已下发 4-已签收 5-研判上报 6-已复核)
     */
    private String operateType;

    /**
     * 研判人ID
     */
    private String judges;

    /**
     * 研判时间
     */
    private Date judgeTime;

    /**
     * 录入人ID
     */
    private String inputerId;

    /**
     * 举报时间
     */
    private Date tipoffTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 举报ID、主键
     * @return TIPOFF_ID 举报ID、主键
     */
    public String getTipoffId() {
        return tipoffId;
    }

    /**
     * 举报ID、主键
     * @param tipoffId 举报ID、主键
     */
    public void setTipoffId(String tipoffId) {
        this.tipoffId = tipoffId == null ? null : tipoffId.trim();
    }

    /**
     * 举报人姓名
     * @return TIPOFF_REPORTER 举报人姓名
     */
    public String getTipoffReporter() {
        return tipoffReporter;
    }

    /**
     * 举报人姓名
     * @param tipoffReporter 举报人姓名
     */
    public void setTipoffReporter(String tipoffReporter) {
        this.tipoffReporter = tipoffReporter == null ? null : tipoffReporter.trim();
    }

    /**
     * 举报人电话
     * @return REPORTER_PHONE 举报人电话
     */
    public String getReporterPhone() {
        return reporterPhone;
    }

    /**
     * 举报人电话
     * @param reporterPhone 举报人电话
     */
    public void setReporterPhone(String reporterPhone) {
        this.reporterPhone = reporterPhone == null ? null : reporterPhone.trim();
    }

    /**
     * 举报人身份证号
     * @return REPORTER_IDCARD 举报人身份证号
     */
    public String getReporterIdcard() {
        return reporterIdcard;
    }

    /**
     * 举报人身份证号
     * @param reporterIdcard 举报人身份证号
     */
    public void setReporterIdcard(String reporterIdcard) {
        this.reporterIdcard = reporterIdcard == null ? null : reporterIdcard.trim();
    }

    /**
     * 涉案类别 字典“案件类别”
     * @return CASE_CLASS 涉案类别 字典“案件类别”
     */
    public Integer getCaseClass() {
        return caseClass;
    }

    /**
     * 涉案类别 字典“案件类别”
     * @param caseClass 涉案类别 字典“案件类别”
     */
    public void setCaseClass(Integer caseClass) {
        this.caseClass = caseClass;
    }

    /**
     * 涉案类别为其他时，备注
     * @return CASE_CLASS_MEMO 涉案类别为其他时，备注
     */
    public String getCaseClassMemo() {
        return caseClassMemo;
    }

    /**
     * 涉案类别为其他时，备注
     * @param caseClassMemo 涉案类别为其他时，备注
     */
    public void setCaseClassMemo(String caseClassMemo) {
        this.caseClassMemo = caseClassMemo;
    }

    /**
     * 案件区域（单位机构ID）
     * @return CASE_UNIT 案件区域（单位机构ID）
     */
    public String getCaseUnit() {
        return caseUnit;
    }

    /**
     * 案件区域（单位机构ID）
     * @param caseUnit 案件区域（单位机构ID）
     */
    public void setCaseUnit(String caseUnit) {
        this.caseUnit = caseUnit == null ? null : caseUnit.trim();
    }

    /**
     * 被举报人姓名
     * @return BEING_REPORTED 被举报人姓名
     */
    public String getBeingReported() {
        return beingReported;
    }

    /**
     * 被举报人姓名
     * @param beingReported 被举报人姓名
     */
    public void setBeingReported(String beingReported) {
        this.beingReported = beingReported == null ? null : beingReported.trim();
    }

    /**
     * 被举报人电话
     * @return BEING_REPORTED_PHONE 被举报人电话
     */
    public String getBeingReportedPhone() {
        return beingReportedPhone;
    }

    /**
     * 被举报人电话
     * @param beingReportedPhone 被举报人电话
     */
    public void setBeingReportedPhone(String beingReportedPhone) {
        this.beingReportedPhone = beingReportedPhone == null ? null : beingReportedPhone.trim();
    }

    /**
     * 被举报人身份证号
     * @return BEING_REPORTED_IDCARD 被举报人身份证号
     */
    public String getBeingReportedIdcard() {
        return beingReportedIdcard;
    }

    /**
     * 被举报人身份证号
     * @param beingReportedIdcard 被举报人身份证号
     */
    public void setBeingReportedIdcard(String beingReportedIdcard) {
        this.beingReportedIdcard = beingReportedIdcard == null ? null : beingReportedIdcard.trim();
    }

    /**
     * 线索标题
     * @return CLUE_TITLE 线索标题
     */
    public String getClueTitle() {
        return clueTitle;
    }

    /**
     * 线索标题
     * @param clueTitle 线索标题
     */
    public void setClueTitle(String clueTitle) {
        this.clueTitle = clueTitle == null ? null : clueTitle.trim();
    }

    /**
     * 线索内容
     * @return CLUE_CONTENT 线索内容
     */
    public String getClueContent() {
        return clueContent;
    }

    /**
     * 线索内容
     * @param clueContent 线索内容
     */
    public void setClueContent(String clueContent) {
        this.clueContent = clueContent == null ? null : clueContent.trim();
    }

    /**
     * 研判结论
     * @return JUDGE_CONTENT 研判结论
     */
    public String getJudgeContent() {
        return judgeContent;
    }

    /**
     * 研判结论
     * @param judgeContent 研判结论
     */
    public void setJudgeContent(String judgeContent) {
        this.judgeContent = judgeContent == null ? null : judgeContent.trim();
    }

    /**
     * 操作类型
     * @return OPERATE_TYPE 操作类型
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 操作类型
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    /**
     * 研判人ID
     * @return JUDGES 研判人ID
     */
    public String getJudges() {
        return judges;
    }

    /**
     * 研判人ID
     * @param judges 研判人ID
     */
    public void setJudges(String judges) {
        this.judges = judges == null ? null : judges.trim();
    }

    /**
     * 研判时间
     * @return JUDGE_TIME 研判时间
     */
    public Date getJudgeTime() {
        return judgeTime;
    }

    /**
     * 研判时间
     * @param judgeTime 研判时间
     */
    public void setJudgeTime(Date judgeTime) {
        this.judgeTime = judgeTime;
    }

    /**
     * 录入人ID
     * @return INPUTER_ID 录入人ID
     */
    public String getInputerId() {
        return inputerId;
    }

    /**
     * 录入人ID
     * @param inputerId 录入人ID
     */
    public void setInputerId(String inputerId) {
        this.inputerId = inputerId == null ? null : inputerId.trim();
    }

    /**
     * 举报时间
     * @return TIPOFF_TIME 举报时间
     */
    public Date getTipoffTime() {
        return tipoffTime;
    }

    /**
     * 举报时间
     * @param tipoffTime 举报时间
     */
    public void setTipoffTime(Date tipoffTime) {
        this.tipoffTime = tipoffTime;
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

    public String getCaseClassName() {
		return caseClassName;
	}

	public void setCaseClassName(String caseClassName) {
		this.caseClassName = caseClassName;
	}

	public String getCaseUnitName() {
		return caseUnitName;
	}

	public void setCaseUnitName(String caseUnitName) {
		this.caseUnitName = caseUnitName;
	}

	/**
     * 修改时间
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}