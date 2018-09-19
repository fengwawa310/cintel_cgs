package com.entity.caseInfo;

import com.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 描述:et_case表的实体类
 *
 * @author: weipc
 * @创建时间: 2018-01-02
 */
public class EtCase /*extends BaseEntity<Long>*/ {
//    /**
//     *
//     */
//    private static final long serialVersionUID = 7515246389196355522L;
	/**
     *  用户ID(临时变量)
     */
    private String userId;
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
     * 案件编号集合(临时变量)
     */
    private String caseNos;
    /**
     * 重点人员姓名(临时变量)
     */
    private String suspectName;
    /**
     * 重点人员手机号(临时变量)
     */
    private String suspectPhoneNum;
    /**
     * 重点人员身份证号码(临时变量)
     */
    private String suspectIDCardNo;
    /**
     * 管控单位(临时变量)
     */
    private String manaUnitCode;
    /**
     * 开始时间(临时变量)
     */
    private String startTime;
    /**
     * 结束时间(临时变量)
     */
    private String endTime;

    /**
     * 主键
     */
    private String id;

    /**
     * 案件编号
     */
    private String caseNo;

    /**
     * 警情编号
     */
    private String alarmNo;

    /**
     * 案件名称
     */
    private String caseTitle;

    /**
     * 案件状态 字典“案件状态”
     */
    private Integer caseState;
//    /**
//     * 案件状态 字典“案件状态”(临时变量)
//     */
//    private String caseStateName;

    /**
     * 案件类别 字典“案件类别”
     */
    private String caseClass;
//    /**
//     * 案件类别 字典“案件类别”(临时变量)
//     */
//    private String caseClassName;

    /**
     * 案件来源
     */
    private String caseSource;

    /**
     * 数据来源 字典“数据来源”
     */
    private Integer sourceType;

    /**
     * 备注
     */
    private String caseRemarks;

    /**
     * 案发详细地址
     */
    private String detalAddress;

    /**
     * 发案时间上限
     */
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String happenTimeUp;

    /**
     * 发案时间下限
     */
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String happenTimeDown;

    /**
     * 案件所属区划编码
     */
    private String zoneCode;

    /**
     * 案件所属区划名称
     */
    private String zoneName;

    /**
     * 案件受理单位编码
     */
    private String acceptUnitCode;

    /**
     * 案件受理单位名称
     */
    private String acceptUnitName;

    /**
     * 案件受理时间
     */
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String acceptTime;

    /**
     * 案件主办单位编码
     */
    private String hostUnitCode;

    /**
     * 案件主办单位名称
     */
    private String hostUnitName;

    /**
     * 案件主办人编号
     */
    private String sponsorCode;

    /**
     * 案件主办人姓名
     */
    private String sponsorName;

    /**
     * 案件主办人联系电话
     */
    private String sponsorPhone;

    /**
     * 案件协办单位编码
     */
    private String assistUnitCode;

    /**
     * 案件协办单位名称
     */
    private String assistUnitName;

    /**
     * 案件协办人编号
     */
    private String assistantCode;

    /**
     * 案件协办人姓名
     */
    private String assistantName;

    /**
     * 案件协办人联系方式
     */
    private String assistantPhone;

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
     * 关注标识：0未关注；1已关注；默认0
     */
    private Integer isFollow;

    /**
     * 审批标识：0未审批；1已审批；默认0
     */
    private Integer isApproval;

    /**
     * 归档标识：0未归档；1已归档；默认0
     */
    private Integer isArchive;

    /**
     * 归档至历史的理由（说明）
     */
    private String archiveDesc;

    /**
     * 删除标识：0未删除；1已删除；默认0
     */
    private Integer isAbandon;

    /**
     * 删除理由（说明）
     */
    private String abandonDesc;

    /**
     * 系统创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date creatTime;

    /**
     * 系统修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    /**
     * 简要案情
     */
    private String caseDesc;

    /**
     * 预留字段 保存真实录入人编码
     */
    private String reserveA;

    /**
     * 预留字段 保存真实录入人姓名
     */
    private String reserveB;
    /**
     * 预留字段 保存真实录入单位编码
     */
    private String reserveC;

    /**
     * 预留字段 保存真实录入单位名称
     */
    private String reserveD;

    /**
     * 分权分域标识
     */
    private String deceSigns;

    /**
     * 2018-02-03   OneDove 临时变量，用以查询重点人员与案件的确认关系和标记（前科、已处罚、未处置）
     * relation   人工确认标识
     * lable 涉案标记标识
     */
    private Integer relation;
    private Integer lable;


    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    public Integer getLable() {
        return lable;
    }

    public void setLable(Integer lable) {
        this.lable = lable;
    }

    public String getDeceSigns() {
        return deceSigns;
    }

    public void setDeceSigns(String deceSigns) {
        this.deceSigns = deceSigns;
    }

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
     * 案件编号
     *
     * @return CASE_NO 案件编号
     */
    public String getCaseNo() {
        return caseNo;
    }

    /**
     * 案件编号
     *
     * @param caseNo 案件编号
     */
    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo == null ? null : caseNo.trim();
    }

    /**
     * 案件名称
     *
     * @return CASE_TITLE 案件名称
     */
    public String getCaseTitle() {
        return caseTitle;
    }

    /**
     * 案件名称
     *
     * @param caseTitle 案件名称
     */
    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle == null ? null : caseTitle.trim();
    }

    /**
     * 案件状态 字典“案件状态”
     *
     * @return CASE_STATE 案件状态 字典“案件状态”
     */
    public Integer getCaseState() {
        return caseState;
    }

    /**
     * 案件状态 字典“案件状态”
     *
     * @param caseState 案件状态 字典“案件状态”
     */
    public void setCaseState(Integer caseState) {
        this.caseState = caseState;
    }

    /**
     * 案件类别 字典“案件类别”
     *
     * @return CASE_CLASS 案件类别 字典“案件类别”
     */
    public String getCaseClass() {
        return caseClass;
    }

    /**
     * 案件类别 字典“案件类别”
     *
     * @param caseClass 案件类别 字典“案件类别”
     */
    public void setCaseClass(String caseClass) {
        this.caseClass = caseClass;
    }

    /**
     * 案件来源
     *
     * @return CASE_SOURCE 案件来源
     */
    public String getCaseSource() {
        return caseSource;
    }

    /**
     * 案件来源
     *
     * @param caseSource 案件来源
     */
    public void setCaseSource(String caseSource) {
        this.caseSource = caseSource == null ? null : caseSource.trim();
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
     * 备注
     *
     * @return CASE_REMARKS 备注
     */
    public String getCaseRemarks() {
        return caseRemarks;
    }

    /**
     * 备注
     *
     * @param caseRemarks 备注
     */
    public void setCaseRemarks(String caseRemarks) {
        this.caseRemarks = caseRemarks == null ? null : caseRemarks.trim();
    }

    /**
     * 案发详细地址
     *
     * @return DETAL_ADDRESS 案发详细地址
     */
    public String getDetalAddress() {
        return detalAddress;
    }

    /**
     * 案发详细地址
     *
     * @param detalAddress 案发详细地址
     */
    public void setDetalAddress(String detalAddress) {
        this.detalAddress = detalAddress == null ? null : detalAddress.trim();
    }

    /**
     * 发案时间上限
     *
     * @return HAPPEN_TIME_UP 发案时间上限
     */
    public String getHappenTimeUp() {
        return happenTimeUp;
    }

    /**
     * 发案时间上限
     *
     * @param happenTimeUp 发案时间上限
     */
    public void setHappenTimeUp(String happenTimeUp) {
        this.happenTimeUp = happenTimeUp;
    }

    /**
     * 发案时间下限
     *
     * @return HAPPEN_TIME_DOWN 发案时间下限
     */
    public String getHappenTimeDown() {
        return happenTimeDown;
    }

    /**
     * 发案时间下限
     *
     * @param happenTimeDown 发案时间下限
     */
    public void setHappenTimeDown(String happenTimeDown) {
        this.happenTimeDown = happenTimeDown;
    }

    /**
     * 案件所属区划编码
     *
     * @return ZONE_CODE 案件所属区划编码
     */
    public String getZoneCode() {
        return zoneCode;
    }

    /**
     * 案件所属区划编码
     *
     * @param zoneCode 案件所属区划编码
     */
    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode == null ? null : zoneCode.trim();
    }

    /**
     * 案件所属区划名称
     *
     * @return ZONE_NAME 案件所属区划名称
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     * 案件所属区划名称
     *
     * @param zoneName 案件所属区划名称
     */
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName == null ? null : zoneName.trim();
    }

    /**
     * 案件受理单位编码
     *
     * @return ACCEPT_UNIT_CODE 案件受理单位编码
     */
    public String getAcceptUnitCode() {
        return acceptUnitCode;
    }

    /**
     * 案件受理单位编码
     *
     * @param acceptUnitCode 案件受理单位编码
     */
    public void setAcceptUnitCode(String acceptUnitCode) {
        this.acceptUnitCode = acceptUnitCode == null ? null : acceptUnitCode.trim();
    }

    /**
     * 案件受理单位名称
     *
     * @return ACCEPT_UNIT_NAME 案件受理单位名称
     */
    public String getAcceptUnitName() {
        return acceptUnitName;
    }

    /**
     * 案件受理单位名称
     *
     * @param acceptUnitName 案件受理单位名称
     */
    public void setAcceptUnitName(String acceptUnitName) {
        this.acceptUnitName = acceptUnitName == null ? null : acceptUnitName.trim();
    }

    /**
     * 案件受理时间
     *
     * @return ACCEPT_TIME 案件受理时间
     */
    public String getAcceptTime() {
        return acceptTime;
    }

    /**
     * 案件受理时间
     *
     * @param acceptTime 案件受理时间
     */
    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    /**
     * 案件主办单位编码
     *
     * @return HOST_UNIT_CODE 案件主办单位编码
     */
    public String getHostUnitCode() {
        return hostUnitCode;
    }

    /**
     * 案件主办单位编码
     *
     * @param hostUnitCode 案件主办单位编码
     */
    public void setHostUnitCode(String hostUnitCode) {
        this.hostUnitCode = hostUnitCode == null ? null : hostUnitCode.trim();
    }

    /**
     * 案件主办单位名称
     *
     * @return HOST_UNIT_NAME 案件主办单位名称
     */
    public String getHostUnitName() {
        return hostUnitName;
    }

    /**
     * 案件主办单位名称
     *
     * @param hostUnitName 案件主办单位名称
     */
    public void setHostUnitName(String hostUnitName) {
        this.hostUnitName = hostUnitName == null ? null : hostUnitName.trim();
    }

    /**
     * 案件主办人编号
     *
     * @return SPONSOR_CODE 案件主办人编号
     */
    public String getSponsorCode() {
        return sponsorCode;
    }

    /**
     * 案件主办人编号
     *
     * @param sponsorCode 案件主办人编号
     */
    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode == null ? null : sponsorCode.trim();
    }

    /**
     * 案件主办人姓名
     *
     * @return SPONSOR_NAME 案件主办人姓名
     */
    public String getSponsorName() {
        return sponsorName;
    }

    /**
     * 案件主办人姓名
     *
     * @param sponsorName 案件主办人姓名
     */
    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName == null ? null : sponsorName.trim();
    }

    /**
     * 案件主办人联系电话
     *
     * @return SPONSOR_PHONE 案件主办人联系电话
     */
    public String getSponsorPhone() {
        return sponsorPhone;
    }

    /**
     * 案件主办人联系电话
     *
     * @param sponsorPhone 案件主办人联系电话
     */
    public void setSponsorPhone(String sponsorPhone) {
        this.sponsorPhone = sponsorPhone == null ? null : sponsorPhone.trim();
    }

    /**
     * 案件协办单位编码
     *
     * @return ASSIST_UNIT_CODE 案件协办单位编码
     */
    public String getAssistUnitCode() {
        return assistUnitCode;
    }

    /**
     * 案件协办单位编码
     *
     * @param assistUnitCode 案件协办单位编码
     */
    public void setAssistUnitCode(String assistUnitCode) {
        this.assistUnitCode = assistUnitCode == null ? null : assistUnitCode.trim();
    }

    /**
     * 案件协办单位名称
     *
     * @return ASSIST_UNIT_NAME 案件协办单位名称
     */
    public String getAssistUnitName() {
        return assistUnitName;
    }

    /**
     * 案件协办单位名称
     *
     * @param assistUnitName 案件协办单位名称
     */
    public void setAssistUnitName(String assistUnitName) {
        this.assistUnitName = assistUnitName == null ? null : assistUnitName.trim();
    }

    /**
     * 案件协办人编号
     *
     * @return ASSISTANT_CODE 案件协办人编号
     */
    public String getAssistantCode() {
        return assistantCode;
    }

    /**
     * 案件协办人编号
     *
     * @param assistantCode 案件协办人编号
     */
    public void setAssistantCode(String assistantCode) {
        this.assistantCode = assistantCode == null ? null : assistantCode.trim();
    }

    /**
     * 案件协办人姓名
     *
     * @return ASSISTANT_NAME 案件协办人姓名
     */
    public String getAssistantName() {
        return assistantName;
    }

    /**
     * 案件协办人姓名
     *
     * @param assistantName 案件协办人姓名
     */
    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName == null ? null : assistantName.trim();
    }

    /**
     * 案件协办人联系方式
     *
     * @return ASSISTANT_PHONE 案件协办人联系方式
     */
    public String getAssistantPhone() {
        return assistantPhone;
    }

    /**
     * 案件协办人联系方式
     *
     * @param assistantPhone 案件协办人联系方式
     */
    public void setAssistantPhone(String assistantPhone) {
        this.assistantPhone = assistantPhone == null ? null : assistantPhone.trim();
    }

    /**
     * 录入单位编码
     *
     * @return INPUT_UNIT_CODE 录入单位编码
     */
    public String getInputUnitCode() {
        return inputUnitCode;
    }

    /**
     * 录入单位编码
     *
     * @param inputUnitCode 录入单位编码
     */
    public void setInputUnitCode(String inputUnitCode) {
        this.inputUnitCode = inputUnitCode == null ? null : inputUnitCode.trim();
    }

    /**
     * 录入单位名称
     *
     * @return INPUT_UNIT_NAME 录入单位名称
     */
    public String getInputUnitName() {
        return inputUnitName;
    }

    /**
     * 录入单位名称
     *
     * @param inputUnitName 录入单位名称
     */
    public void setInputUnitName(String inputUnitName) {
        this.inputUnitName = inputUnitName == null ? null : inputUnitName.trim();
    }

    /**
     * 录入人编码
     *
     * @return INPUTER_CODE 录入人编码
     */
    public String getInputerCode() {
        return inputerCode;
    }

    /**
     * 录入人编码
     *
     * @param inputerCode 录入人编码
     */
    public void setInputerCode(String inputerCode) {
        this.inputerCode = inputerCode == null ? null : inputerCode.trim();
    }

    /**
     * 录入人姓名
     *
     * @return INPUTER_NAME 录入人姓名
     */
    public String getInputerName() {
        return inputerName;
    }

    /**
     * 录入人姓名
     *
     * @param inputerName 录入人姓名
     */
    public void setInputerName(String inputerName) {
        this.inputerName = inputerName == null ? null : inputerName.trim();
    }

    /**
     * 关注标识：0未关注；1已关注；默认0
     *
     * @return IS_FOLLOW 关注标识：0未关注；1已关注；默认0
     */
    public Integer getIsFollow() {
        return isFollow;
    }

    /**
     * 关注标识：0未关注；1已关注；默认0
     *
     * @param isFollow 关注标识：0未关注；1已关注；默认0
     */
    public void setIsFollow(Integer isFollow) {
        this.isFollow = isFollow;
    }

    /**
     * 审批标识：0未审批；1已审批；默认0
     *
     * @return IS_APPROVAL 审批标识：0未审批；1已审批；默认0
     */
    public Integer getIsApproval() {
        return isApproval;
    }

    /**
     * 审批标识：0未审批；1已审批；默认0
     *
     * @param isApproval 审批标识：0未审批；1已审批；默认0
     */
    public void setIsApproval(Integer isApproval) {
        this.isApproval = isApproval;
    }

    /**
     * 归档标识：0未归档；1已归档；默认0
     *
     * @return IS_ARCHIVE 归档标识：0未归档；1已归档；默认0
     */
    public Integer getIsArchive() {
        return isArchive;
    }

    /**
     * 归档标识：0未归档；1已归档；默认0
     *
     * @param isArchive 归档标识：0未归档；1已归档；默认0
     */
    public void setIsArchive(Integer isArchive) {
        this.isArchive = isArchive;
    }

    /**
     * 归档至历史的理由（说明）
     *
     * @return ARCHIVE_DESC 归档至历史的理由（说明）
     */
    public String getArchiveDesc() {
        return archiveDesc;
    }

    /**
     * 归档至历史的理由（说明）
     *
     * @param archiveDesc 归档至历史的理由（说明）
     */
    public void setArchiveDesc(String archiveDesc) {
        this.archiveDesc = archiveDesc == null ? null : archiveDesc.trim();
    }

    /**
     * 删除标识：0未删除；1已删除；默认0
     *
     * @return IS_ABANDON 删除标识：0未删除；1已删除；默认0
     */
    public Integer getIsAbandon() {
        return isAbandon;
    }

    /**
     * 删除标识：0未删除；1已删除；默认0
     *
     * @param isAbandon 删除标识：0未删除；1已删除；默认0
     */
    public void setIsAbandon(Integer isAbandon) {
        this.isAbandon = isAbandon;
    }

    /**
     * 删除理由（说明）
     *
     * @return ABANDON_DESC 删除理由（说明）
     */
    public String getAbandonDesc() {
        return abandonDesc;
    }

    /**
     * 删除理由（说明）
     *
     * @param abandonDesc 删除理由（说明）
     */
    public void setAbandonDesc(String abandonDesc) {
        this.abandonDesc = abandonDesc == null ? null : abandonDesc.trim();
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
     * 简要案情
     *
     * @return CASE_DESC 简要案情
     */
    public String getCaseDesc() {
        return caseDesc;
    }

    /**
     * 简要案情
     *
     * @param caseDesc 简要案情
     */
    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc == null ? null : caseDesc.trim();
    }

//    public String getCaseStateName() {
//        return caseStateName;
//    }
//
//    public void setCaseStateName(String caseStateName) {
//        this.caseStateName = caseStateName;
//    }
//
//    public String getCaseClassName() {
//        return caseClassName;
//    }
//
//    public void setCaseClassName(String caseClassName) {
//        this.caseClassName = caseClassName;
//    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSuspectIDCardNo() {
        return suspectIDCardNo;
    }

    public void setSuspectIDCardNo(String suspectIDCardNo) {
        this.suspectIDCardNo = suspectIDCardNo;
    }

    public String getManaUnitCode() {
        return manaUnitCode;
    }

    public void setManaUnitCode(String manaUnitCode) {
        this.manaUnitCode = manaUnitCode;
    }

    public String getSuspectName() {
        return suspectName;
    }

    public void setSuspectName(String suspectName) {
        this.suspectName = suspectName;
    }

    public String getSuspectPhoneNum() {
        return suspectPhoneNum;
    }

    public void setSuspectPhoneNum(String suspectPhoneNum) {
        this.suspectPhoneNum = suspectPhoneNum;
    }

    public String getAlarmNo() {
        return alarmNo;
    }

    public void setAlarmNo(String alarmNo) {
        this.alarmNo = alarmNo;
    }

    public String getReserveA() {
        return reserveA;
    }

    public void setReserveA(String reserveA) {
        this.reserveA = reserveA;
    }

    public String getReserveB() {
        return reserveB;
    }

    public void setReserveB(String reserveB) {
        this.reserveB = reserveB;
    }

    public String getReserveC() {
        return reserveC;
    }

    public void setReserveC(String reserveC) {
        this.reserveC = reserveC;
    }

    public String getReserveD() {
        return reserveD;
    }

    public void setReserveD(String reserveD) {
        this.reserveD = reserveD;
    }

    public String getCaseNos() {
        return caseNos;
    }

    public void setCaseNos(String caseNos) {
        this.caseNos = caseNos;
    }
}