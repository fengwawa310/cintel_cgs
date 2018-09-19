package com.entity.taskStu;

public class RelationalPeople {
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 团伙ID
	 */
	private String gangId;

	/**
	 * 姓名
	 */
	private String fullName;

	/**
	 * 别名
	 */
	private String aliasName;

	/**
	 * 家庭情况
	 */
	private String familySituation;

	/**
	 * 照片
	 */
	private String photoUrl;

	/**
	 * 使用的通讯工具
	 */
	private String communicationTool;

	/**
	 * 车辆
	 */
	private String vehicle;

	/**
	 * 前科
	 */
	private String criminalRecord;

	/**
	 * 涉嫌未处理犯罪事实
	 */
	private String untreatedCriminalFacts;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 证据情况
	 */
	private String evidence;

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
	 * @param id
	 *            主键
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * 团伙ID
	 * 
	 * @return GANG_ID 团伙ID
	 */
	public String getGangId() {
		return gangId;
	}

	/**
	 * 团伙ID
	 * 
	 * @param gangId
	 *            团伙ID
	 */
	public void setGangId(String gangId) {
		this.gangId = gangId == null ? null : gangId.trim();
	}

	/**
	 * 姓名
	 * 
	 * @return FULL_NAME 姓名
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 姓名
	 * 
	 * @param fullName
	 *            姓名
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName == null ? null : fullName.trim();
	}

	/**
	 * 别名
	 * 
	 * @return ALIAS_NAME 别名
	 */
	public String getAliasName() {
		return aliasName;
	}

	/**
	 * 别名
	 * 
	 * @param aliasName
	 *            别名
	 */
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName == null ? null : aliasName.trim();
	}

	/**
	 * 家庭情况
	 * 
	 * @return FAMILY_SITUATION 家庭情况
	 */
	public String getFamilySituation() {
		return familySituation;
	}

	/**
	 * 家庭情况
	 * 
	 * @param familySituation
	 *            家庭情况
	 */
	public void setFamilySituation(String familySituation) {
		this.familySituation = familySituation == null ? null : familySituation.trim();
	}

	/**
	 * 照片
	 * 
	 * @return PHOTO_URL 照片
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}

	/**
	 * 照片
	 * 
	 * @param photoUrl
	 *            照片
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl == null ? null : photoUrl.trim();
	}

	/**
	 * 使用的通讯工具
	 * 
	 * @return COMMUNICATION_TOOL 使用的通讯工具
	 */
	public String getCommunicationTool() {
		return communicationTool;
	}

	/**
	 * 使用的通讯工具
	 * 
	 * @param communicationTool
	 *            使用的通讯工具
	 */
	public void setCommunicationTool(String communicationTool) {
		this.communicationTool = communicationTool == null ? null : communicationTool.trim();
	}

	/**
	 * 车辆
	 * 
	 * @return VEHICLE 车辆
	 */
	public String getVehicle() {
		return vehicle;
	}

	/**
	 * 车辆
	 * 
	 * @param vehicle
	 *            车辆
	 */
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle == null ? null : vehicle.trim();
	}

	/**
	 * 前科
	 * 
	 * @return CRIMINAL_RECORD 前科
	 */
	public String getCriminalRecord() {
		return criminalRecord;
	}

	/**
	 * 前科
	 * 
	 * @param criminalRecord
	 *            前科
	 */
	public void setCriminalRecord(String criminalRecord) {
		this.criminalRecord = criminalRecord == null ? null : criminalRecord.trim();
	}

	/**
	 * 涉嫌未处理犯罪事实
	 * 
	 * @return UNTREATED_CRIMINAL_FACTS 涉嫌未处理犯罪事实
	 */
	public String getUntreatedCriminalFacts() {
		return untreatedCriminalFacts;
	}

	/**
	 * 涉嫌未处理犯罪事实
	 * 
	 * @param untreatedCriminalFacts
	 *            涉嫌未处理犯罪事实
	 */
	public void setUntreatedCriminalFacts(String untreatedCriminalFacts) {
		this.untreatedCriminalFacts = untreatedCriminalFacts == null ? null : untreatedCriminalFacts.trim();
	}

	/**
	 * 备注
	 * 
	 * @return REMARKS 备注
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 备注
	 * 
	 * @param remarks
	 *            备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	/**
	 * 证据情况
	 * 
	 * @return EVIDENCE 证据情况
	 */
	public String getEvidence() {
		return evidence;
	}

	/**
	 * 证据情况
	 * 
	 * @param evidence
	 *            证据情况
	 */
	public void setEvidence(String evidence) {
		this.evidence = evidence == null ? null : evidence.trim();
	}
}
