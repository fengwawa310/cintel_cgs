package com.entity.integral;

import java.io.Serializable;

public class StatisticsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 区域编码
	 */
	private String zoneCode;
	
	private String zoneName;
	
	private String caseClass;
	
	private Integer caseNum;
	
	private Integer suspectNum;
	
	private Integer inforNum;
	
	private Integer alarmNum;
	
	public StatisticsEntity(){
		this.caseNum = Integer.valueOf(0);
		this.suspectNum = Integer.valueOf(0);
		this.inforNum = Integer.valueOf(0);
		this.alarmNum = Integer.valueOf(0);
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getCaseClass() {
		return caseClass;
	}

	public void setCaseClass(String caseClass) {
		this.caseClass = caseClass;
	}

	public Integer getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(Integer caseNum) {
		this.caseNum = caseNum;
	}

	public Integer getSuspectNum() {
		return suspectNum;
	}

	public void setSuspectNum(Integer suspectNum) {
		this.suspectNum = suspectNum;
	}

	public Integer getInforNum() {
		return inforNum;
	}

	public void setInforNum(Integer inforNum) {
		this.inforNum = inforNum;
	}

	public Integer getAlarmNum() {
		return alarmNum;
	}

	public void setAlarmNum(Integer alarmNum) {
		this.alarmNum = alarmNum;
	}
	
}
