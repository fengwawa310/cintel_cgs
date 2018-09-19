package com.entity.integral;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 描述:et_unit_integrall表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-01-04
 */
public class EtUnitIntegral implements Serializable{
    /**
     * 主键 
     */
    private String id;

    /**
     * 单位编码
     */
    private String unitCode;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 基本积分
     */
    private Integer basePoints;

    /**
     * 判积分
     */
    private Integer judgePoints;

    /**
     * 案件积分
     */
    private Integer casePoints;
    /**
     * 警情积分
     */
    private Integer alarmPoints;

    /**
     * 嘉奖克扣积分
     */
    private Integer awardsDeducPoints;

    /**
     * 线索上报积分
     */
    private Integer clueReportPoints;

    /**
     * 人员上报积分
     */
    private Integer suspectReportPoints;

    /**
     * 情报上报积分
     */
    private Integer inforReportPoints;

    /**
     * 积分总合计
     */
    private Integer totalPoints;

    /**
     * 嘉奖次数
     */
    private Integer awardCount;

    /**
     * 立功次数
     */
    private Integer honourCount;

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
    
    
    public Integer getAlarmPoints() {
		return alarmPoints;
	}

	public void setAlarmPoints(Integer alarmPoints) {
		this.alarmPoints = alarmPoints;
	}

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
     * 单位编码
     * @return UNIT_CODE 单位编码
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * 单位编码
     * @param unitCode 单位编码
     */
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode == null ? null : unitCode.trim();
    }

    /**
     * 单位名称
     * @return UNIT_NAME 单位名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 单位名称
     * @param unitName 单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    /**
     * 基本积分
     * @return BASE_POINTS 基本积分
     */
    public Integer getBasePoints() {
        return basePoints;
    }

    /**
     * 基本积分
     * @param basePoints 基本积分
     */
    public void setBasePoints(Integer basePoints) {
        this.basePoints = basePoints;
    }

    /**
     * 判积分
     * @return JUDGE_POINTS 判积分
     */
    public Integer getJudgePoints() {
        return judgePoints;
    }

    /**
     * 判积分
     * @param judgePoints 判积分
     */
    public void setJudgePoints(Integer judgePoints) {
        this.judgePoints = judgePoints;
    }

    /**
     * 案件积分
     * @return CASE_POINTS 案件积分
     */
    public Integer getCasePoints() {
        return casePoints;
    }

    /**
     * 案件积分
     * @param casePoints 案件积分
     */
    public void setCasePoints(Integer casePoints) {
        this.casePoints = casePoints;
    }

    /**
     * 嘉奖克扣积分
     * @return AWARDS_DEDUC_POINTS 嘉奖克扣积分
     */
    public Integer getAwardsDeducPoints() {
        return awardsDeducPoints;
    }

    /**
     * 嘉奖克扣积分
     * @param awardsDeducPoints 嘉奖克扣积分
     */
    public void setAwardsDeducPoints(Integer awardsDeducPoints) {
        this.awardsDeducPoints = awardsDeducPoints;
    }

    /**
     * 线索上报积分
     * @return CLUE_REPORT_POINTS 线索上报积分
     */
    public Integer getClueReportPoints() {
        return clueReportPoints;
    }

    /**
     * 线索上报积分
     * @param clueReportPoints 线索上报积分
     */
    public void setClueReportPoints(Integer clueReportPoints) {
        this.clueReportPoints = clueReportPoints;
    }

    /**
     * 人员上报积分
     * @return SUSPECT_REPORT_POINTS 人员上报积分
     */
    public Integer getSuspectReportPoints() {
        return suspectReportPoints;
    }

    /**
     * 人员上报积分
     * @param suspectReportPoints 人员上报积分
     */
    public void setSuspectReportPoints(Integer suspectReportPoints) {
        this.suspectReportPoints = suspectReportPoints;
    }

    /**
     * 情报上报积分
     * @return INFOR_REPORT_POINTS 情报上报积分
     */
    public Integer getInforReportPoints() {
        return inforReportPoints;
    }

    /**
     * 情报上报积分
     * @param inforReportPoints 情报上报积分
     */
    public void setInforReportPoints(Integer inforReportPoints) {
        this.inforReportPoints = inforReportPoints;
    }

    /**
     * 积分总合计
     * @return TOTAL_POINTS 积分总合计
     */
    public Integer getTotalPoints() {
        return totalPoints;
    }

    /**
     * 积分总合计
     * @param totalPoints 积分总合计
     */
    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    /**
     * 嘉奖次数
     * @return AWARD_COUNT 嘉奖次数
     */
    public Integer getAwardCount() {
        return awardCount;
    }

    /**
     * 嘉奖次数
     * @param awardCount 嘉奖次数
     */
    public void setAwardCount(Integer awardCount) {
        this.awardCount = awardCount;
    }

    /**
     * 立功次数
     * @return HONOUR_COUNT 立功次数
     */
    public Integer getHonourCount() {
        return honourCount;
    }

    /**
     * 立功次数
     * @param honourCount 立功次数
     */
    public void setHonourCount(Integer honourCount) {
        this.honourCount = honourCount;
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