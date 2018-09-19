package com.entity.suspect;

import java.io.Serializable;
import java.util.Date;

import com.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 描述:et_suspect表的实体类
 * 
 * @version
 * @author: weipc
 * @创建时间: 2018-01-02
 */
public class EtSuspect implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7515246389196355522L;

	private String id;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date creatTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date modifyTime;

	/**
	 * 嫌疑人编号
	 */
	private String suspectId;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 别名
	 */
	private String byname;

	/**
	 * 身份证编号
	 */
	private String idcardNum;

	/**
	 * 头像图片下载地址
	 */
	private String headPhotoUrl;

	/**
	 * 出生日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthdate;
	/**
	 * 出生日期(临时变量)
	 */
	private String birthdateStr;

	/**
	 * 性别 字典“人员性别”
	 */
	private Integer gender;

	/**
	 * 民族
	 */
	private String nation;

	/**
	 * 文化程度
	 */
	private String educationDegree;

	/**
	 * 籍贯
	 */
	private String origin;

	/**
	 * 职业
	 */
	private String occupation;

	/**
	 * 手机联系电话
	 */
	private String mobilephone;

	/**
	 * 座机联系电话
	 */
	private String telephone;

	/**
	 * 身高
	 */
	private String height;

	/**
	 * 血型
	 */
	private String bloodType;

	/**
	 * 体型
	 */
	private String shape;

	/**
	 * 录入单位
	 */
	private String entryUnit;
	/**
	 * 录入单位名称
	 */
	private String entryUnitName;

	/**
	 * 例如人ID
	 */
	private String entry;

	/**
	 * 例如人ID
	 */

	private String entryName;

	/**
	 * 人员等级 0=未设置；1=蓝色；2=红色
	 */
	private Integer levelSet;

	/**
	 * 人员类型 字典“重点人员状态”：4200 在押，4201 在逃，4202取保候审，4203监视居住，4204刑满释放，4205其他
	 */
	private Integer suspectClass;
	/**
	 * 人员级别 字典“人员级别”
	 */
	private Integer suspectLevel;

	private Integer viewSign;
	
	private Integer editSign;
	

	/**
	 * 数据来源 字典“数据来源”
	 */
	private Integer sourceType;

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
	 * 操作单位编码
	 */
	private String optUnitCode;
	/**
	 * 操作单位名称
	 */
	private String optUnitName;
	/**
	 * 操作人编码
	 */
	private String optPCode;
	/**
	 * 操作人姓名
	 */
	private String optPName;
	
	/*
	 * 2018-2-03 V1.1.0版本新增字段  
	 * @OneDove
	 */
	
	/**
	 * 户籍地址
	 */
	private String permanet;
	
	/**
	 * 现住址
	 */
	private String permanetNow;
	/**
	 * 工作单位
	 */
	private String work;
	/**
	 * 微信号码，多个微信号码以#分割
	 */
	private String weixinNo;
	/**
	 * QQ号码，多个QQ号以#分割
	 */
	private String qqNo;
	/**
	 * 警号
	 */
	private String entryPoliceNo;
	/**
	 * 重点人员性质：字典"重点人员性质" 43重点人员性质： 4300 涉黑人员，4301 涉恶人员，4302 一般人员
	 */
	private Integer suspectType;
	/**
	 * 备注
	 */
	private String demo;
	/**
	 * 是否需要布控：0不需要，1需要，默认0
	 */
	private Integer isIntl;

	/**
	 * 重点人员被管控次数
	 */
	private Integer ctrlTimes;
	
	
	
	
	
	
	/**
	 * 操作时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date optTime;

	private Integer age;
	private String startTime;
	private String endTime;

	public Integer getLevelSet() {
		return levelSet;
	}

	public void setLevelSet(Integer levelSet) {
		this.levelSet = levelSet;
	}

	public Integer getAge() {
		return age;
	}

	public String getPermanet() {
		return permanet;
	}

	public void setPermanet(String permanet) {
		this.permanet = permanet;
	}

	public String getPermanetNow() {
		return permanetNow;
	}

	public void setPermanetNow(String permanetNow) {
		this.permanetNow = permanetNow;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getWeixinNo() {
		return weixinNo;
	}

	public void setWeixinNo(String weixinNo) {
		this.weixinNo = weixinNo;
	}

	public String getQqNo() {
		return qqNo;
	}

	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}

	public String getEntryPoliceNo() {
		return entryPoliceNo;
	}

	public void setEntryPoliceNo(String entryPoliceNo) {
		this.entryPoliceNo = entryPoliceNo;
	}

	public Integer getSuspectType() {
		return suspectType;
	}

	public void setSuspectType(Integer suspectType) {
		this.suspectType = suspectType;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	public Integer getIsIntl() {
		return isIntl;
	}

	public void setIsIntl(Integer isIntl) {
		this.isIntl = isIntl;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

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

	public Integer getSuspectLevel() {
		return suspectLevel;
	}

	public void setSuspectLevel(Integer suspectLevel) {
		this.suspectLevel = suspectLevel;
	}

	public String getEntryUnitName() {
		return entryUnitName;
	}

	public void setEntryUnitName(String entryUnitName) {
		this.entryUnitName = entryUnitName;
	}

	public String getOptUnitCode() {
		return optUnitCode;
	}

	public void setOptUnitCode(String optUnitCode) {
		this.optUnitCode = optUnitCode;
	}

	public String getOptUnitName() {
		return optUnitName;
	}

	public void setOptUnitName(String optUnitName) {
		this.optUnitName = optUnitName;
	}

	public String getOptPCode() {
		return optPCode;
	}

	public void setOptPCode(String optPCode) {
		this.optPCode = optPCode;
	}

	public String getOptPName() {
		return optPName;
	}

	public void setOptPName(String optPName) {
		this.optPName = optPName;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 嫌疑人编号
	 * 
	 * @return SUSPECT_ID 嫌疑人编号
	 */
	public String getSuspectId() {
		return suspectId;
	}

	/**
	 * 嫌疑人编号
	 * 
	 * @param suspectId
	 *            嫌疑人编号
	 */
	public void setSuspectId(String suspectId) {
		this.suspectId = suspectId == null ? null : suspectId.trim();
	}

	/**
	 * 姓名
	 * 
	 * @return NAME 姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 姓名
	 * 
	 * @param name
	 *            姓名
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * 别名
	 * 
	 * @return BYNAME 别名
	 */
	public String getByname() {
		return byname;
	}

	/**
	 * 别名
	 * 
	 * @param byname
	 *            别名
	 */
	public void setByname(String byname) {
		this.byname = byname == null ? null : byname.trim();
	}

	/**
	 * 身份证编号
	 * 
	 * @return IDCARD_NUM 身份证编号
	 */
	public String getIdcardNum() {
		return idcardNum;
	}

	/**
	 * 身份证编号
	 * 
	 * @param idcardNum
	 *            身份证编号
	 */
	public void setIdcardNum(String idcardNum) {
		this.idcardNum = idcardNum == null ? null : idcardNum.trim();
	}

	/**
	 * 头像图片下载地址
	 * 
	 * @return HEAD_PHOTO_URL 头像图片下载地址
	 */
	public String getHeadPhotoUrl() {
		return headPhotoUrl;
	}

	/**
	 * 头像图片下载地址
	 * 
	 * @param headPhotoUrl
	 *            头像图片下载地址
	 */
	public void setHeadPhotoUrl(String headPhotoUrl) {
		this.headPhotoUrl = headPhotoUrl == null ? null : headPhotoUrl.trim();
	}

	/**
	 * 出生日期
	 * 
	 * @return BIRTHDATE 出生日期
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * 出生日期
	 * 
	 * @param birthdate
	 *            出生日期
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * 性别 字典“人员性别”
	 * 
	 * @return GENDER 性别 字典“人员性别”
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * 性别 字典“人员性别”
	 * 
	 * @param gender
	 *            性别 字典“人员性别”
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * 民族
	 * 
	 * @return NATION 民族
	 */
	public String getNation() {
		return nation;
	}

	/**
	 * 民族
	 * 
	 * @param nation
	 *            民族
	 */
	public void setNation(String nation) {
		this.nation = nation == null ? null : nation.trim();
	}

	/**
	 * 文化程度
	 * 
	 * @return EDUCATION_DEGREE 文化程度
	 */
	public String getEducationDegree() {
		return educationDegree;
	}

	/**
	 * 文化程度
	 * 
	 * @param educationDegree
	 *            文化程度
	 */
	public void setEducationDegree(String educationDegree) {
		this.educationDegree = educationDegree == null ? null : educationDegree
				.trim();
	}

	/**
	 * 籍贯
	 * 
	 * @return ORIGIN 籍贯
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * 籍贯
	 * 
	 * @param origin
	 *            籍贯
	 */
	public void setOrigin(String origin) {
		this.origin = origin == null ? null : origin.trim();
	}

	/**
	 * 职业
	 * 
	 * @return OCCUPATION 职业
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * 职业
	 * 
	 * @param occupation
	 *            职业
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation == null ? null : occupation.trim();
	}

	/**
	 * 手机联系电话
	 * 
	 * @return MOBILEPHONE 手机联系电话
	 */
	public String getMobilephone() {
		return mobilephone;
	}

	/**
	 * 手机联系电话
	 * 
	 * @param mobilephone
	 *            手机联系电话
	 */
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone == null ? null : mobilephone.trim();
	}

	/**
	 * 座机联系电话
	 * 
	 * @return TELEPHONE 座机联系电话
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * 座机联系电话
	 * 
	 * @param telephone
	 *            座机联系电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone == null ? null : telephone.trim();
	}

	/**
	 * 身高
	 * 
	 * @return HEIGHT 身高
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * 身高
	 * 
	 * @param height
	 *            身高
	 */
	public void setHeight(String height) {
		this.height = height == null ? null : height.trim();
	}

	/**
	 * 血型
	 * 
	 * @return BLOOD_TYPE 血型
	 */
	public String getBloodType() {
		return bloodType;
	}

	/**
	 * 血型
	 * 
	 * @param bloodType
	 *            血型
	 */
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType == null ? null : bloodType.trim();
	}

	/**
	 * 体型
	 * 
	 * @return SHAPE 体型
	 */
	public String getShape() {
		return shape;
	}

	/**
	 * 体型
	 * 
	 * @param shape
	 *            体型
	 */
	public void setShape(String shape) {
		this.shape = shape == null ? null : shape.trim();
	}

	/**
	 * 录入单位
	 * 
	 * @return ENTRY_UNIT 录入单位
	 */
	public String getEntryUnit() {
		return entryUnit;
	}

	/**
	 * 录入单位
	 * 
	 * @param entryUnit
	 *            录入单位
	 */
	public void setEntryUnit(String entryUnit) {
		this.entryUnit = entryUnit == null ? null : entryUnit.trim();
	}

	/**
	 * 人员类型 字典“人员类别”
	 * 
	 * @return SUSPECT_CLASS 人员类型 字典“人员类别”
	 */
	public Integer getSuspectClass() {
		return suspectClass;
	}

	/**
	 * 人员类型 字典“人员类别”
	 * 
	 * @param suspectClass
	 *            人员类型 字典“人员类别”
	 */
	public void setSuspectClass(Integer suspectClass) {
		this.suspectClass = suspectClass;
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
	 * @param sourceType
	 *            数据来源 字典“数据来源”
	 */
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
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
	 * @param isArchive
	 *            归档标识：0未归档；1已归档；默认0
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
	 * @param archiveDesc
	 *            归档至历史的理由（说明）
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
	 * @param isAbandon
	 *            删除标识：0未删除；1已删除；默认0
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
	 * @param abandonDesc
	 *            删除理由（说明）
	 */
	public void setAbandonDesc(String abandonDesc) {
		this.abandonDesc = abandonDesc == null ? null : abandonDesc.trim();
	}

	/**
	 * 重点人员被管控次数
	 * @return CTRL_TIMES 重点人员被管控次数
	 */
	public Integer getCtrlTimes() {
		return ctrlTimes;
	}

	/**
	 * 重点人员被管控次数
	 * @param ctrlTimes 重点人员被管控次数
	 */
	public void setCtrlTimes(Integer ctrlTimes) {
		this.ctrlTimes = ctrlTimes;
	}

	public String getBirthdateStr() {
		return birthdateStr;
	}

	public void setBirthdateStr(String birthdateStr) {
		this.birthdateStr = birthdateStr;
	}

	public Integer getViewSign() {
		return viewSign;
	}

	public void setViewSign(Integer viewSign) {
		this.viewSign = viewSign;
	}

	public Integer getEditSign() {
		return editSign;
	}

	public void setEditSign(Integer editSign) {
		this.editSign = editSign;
	}
	
}