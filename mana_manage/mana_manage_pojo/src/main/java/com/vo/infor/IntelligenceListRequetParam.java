package com.vo.infor;

import java.io.Serializable;

import com.entity.sys.SysUser;

//接收页面请求封装参数实体
public class IntelligenceListRequetParam implements Serializable {
	// 根据前台传进来的参数 进行封装

	private String sysUser;// sysuser 实体 使用json传输

	private String id;// 主键
	private String inforNo; // 情报研判编号
	private String inforTitle; // 研判标题
	private Integer inforClass; // 情报类型
	private String inforContent; // 查证内容
	private String hostUnitCode; // 查证主办单位编码
	private String hostUnitName; // 查证主办单位名称
	private String assistUnitCode;// 查证协办单位编码
	private String assistUnitName;// 查证协办单位名称
	private String undertakeName;
	private String undertakePersionName;
	private String creatTimeStart;
	private String creatTimeEnd;

	private String qianshouTimeStart; // 签收时间
	private String qianshouTimeEnd;

	private String province;// 所属省编码
	private String city;// 所属市编码
	private String area;// 所属区县编码
	private String paichusuo; // 当前派出所
	private String accessLv;// 必要权限级别
	private String level;// 权限等级
	private String receiveUnitName;
	private String receivePersonName;
	private String receiveUnitCode;
	private String launchUnitCode;

	private String launchUnitName;
	private String launchPname;
	private String xianyirenArray;
	private String liuchengType;

	private String handoutUnit; // 下发单位
	private String handoutUnitName; // 下发单位名称
	private String handoutPerson;// 下发人员编号
	private String handoutPersonName;// 下发人名称

	private String shangbaoTimeStart;// 上报时间区间
	private String shangbaoTimeEnd;

	private String shangbaoShenHeTimeStart; // 上报审核区间
	private String shangbaoShenHeTimeEnd;

	
	private String sysUserDicUnit; //公共方法 获取 该用户所在的单位名称
	public String getLaunchUnitCode() {
		return launchUnitCode;
	}

	public void setLaunchUnitCode(String launchUnitCode) {
		this.launchUnitCode = launchUnitCode;
	}



	public String getSysUserDicUnit() {
		return sysUserDicUnit;
	}

	public void setSysUserDicUnit(String sysUserDicUnit) {
		this.sysUserDicUnit = sysUserDicUnit;
	}

	public String getReceiveUnitCode() {
		return receiveUnitCode;
	}

	public void setReceiveUnitCode(String receiveUnitCode) {
		this.receiveUnitCode = receiveUnitCode;
	}

	public String getReceiveUnitName() {
		return receiveUnitName;
	}

	public void setReceiveUnitName(String receiveUnitName) {
		this.receiveUnitName = receiveUnitName;
	}

	public String getReceivePersonName() {
		return receivePersonName;
	}

	public void setReceivePersonName(String receivePersonName) {
		this.receivePersonName = receivePersonName;
	}

	public String getPaichusuo() {
		return paichusuo;
	}

	public void setPaichusuo(String paichusuo) {
		this.paichusuo = paichusuo;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAccessLv() {
		return accessLv;
	}

	public void setAccessLv(String accessLv) {
		this.accessLv = accessLv;
	}

	public String getSysUser() {
		return sysUser;
	}

	public void setSysUser(String sysUser) {
		this.sysUser = sysUser;
	}

	public String getQianshouTimeStart() {
		return qianshouTimeStart;
	}

	public void setQianshouTimeStart(String qianshouTimeStart) {
		this.qianshouTimeStart = qianshouTimeStart;
	}

	public String getQianshouTimeEnd() {
		return qianshouTimeEnd;
	}

	public void setQianshouTimeEnd(String qianshouTimeEnd) {
		this.qianshouTimeEnd = qianshouTimeEnd;
	}

	public String getShangbaoShenHeTimeStart() {
		return shangbaoShenHeTimeStart;
	}

	public void setShangbaoShenHeTimeStart(String shangbaoShenHeTimeStart) {
		this.shangbaoShenHeTimeStart = shangbaoShenHeTimeStart;
	}

	public String getShangbaoShenHeTimeEnd() {
		return shangbaoShenHeTimeEnd;
	}

	public void setShangbaoShenHeTimeEnd(String shangbaoShenHeTimeEnd) {
		this.shangbaoShenHeTimeEnd = shangbaoShenHeTimeEnd;
	}

	public String getShangbaoTimeStart() {
		return shangbaoTimeStart;
	}

	public void setShangbaoTimeStart(String shangbaoTimeStart) {
		this.shangbaoTimeStart = shangbaoTimeStart;
	}

	public String getShangbaoTimeEnd() {
		return shangbaoTimeEnd;
	}

	public void setShangbaoTimeEnd(String shangbaoTimeEnd) {
		this.shangbaoTimeEnd = shangbaoTimeEnd;
	}

	public String getHandoutUnit() {
		return handoutUnit;
	}

	public void setHandoutUnit(String handoutUnit) {
		this.handoutUnit = handoutUnit;
	}

	public String getHandoutUnitName() {
		return handoutUnitName;
	}

	public void setHandoutUnitName(String handoutUnitName) {
		this.handoutUnitName = handoutUnitName;
	}

	public String getHandoutPerson() {
		return handoutPerson;
	}

	public void setHandoutPerson(String handoutPerson) {
		this.handoutPerson = handoutPerson;
	}

	public String getHandoutPersonName() {
		return handoutPersonName;
	}

	public void setHandoutPersonName(String handoutPersonName) {
		this.handoutPersonName = handoutPersonName;
	}

	private String isAuthorize;// 签收人领导是否授权(含义等同于审核状态)

	public String getIsAuthorize() {
		return isAuthorize;
	}

	public void setIsAuthorize(String isAuthorize) {
		this.isAuthorize = isAuthorize;
	}

	public String getLiuchengType() {
		return liuchengType;
	}

	public void setLiuchengType(String liuchengType) {
		this.liuchengType = liuchengType;
	}

	public String getXianyirenArray() {
		return xianyirenArray;
	}

	public void setXianyirenArray(String xianyirenArray) {
		this.xianyirenArray = xianyirenArray;
	}

	public String getLaunchUnitName() {
		return launchUnitName;
	}

	public void setLaunchUnitName(String launchUnitName) {
		this.launchUnitName = launchUnitName;
	}

	public String getLaunchPname() {
		return launchPname;
	}

	public void setLaunchPname(String launchPname) {
		this.launchPname = launchPname;
	}

	public String getUndertakeName() {
		return undertakeName;
	}

	public void setUndertakeName(String undertakeName) {
		this.undertakeName = undertakeName;
	}

	public String getUndertakePersionName() {
		return undertakePersionName;
	}

	public void setUndertakePersionName(String undertakePersionName) {
		this.undertakePersionName = undertakePersionName;
	}

	public String getCreatTimeStart() {
		return creatTimeStart;
	}

	public void setCreatTimeStart(String creatTimeStart) {
		this.creatTimeStart = creatTimeStart;
	}

	public String getCreatTimeEnd() {
		return creatTimeEnd;
	}

	public void setCreatTimeEnd(String creatTimeEnd) {
		this.creatTimeEnd = creatTimeEnd;
	}

	private Integer isVerify; // 审核标识
	private Integer isReport; // 上报标识
	private Integer isReportType;// 上报审核状态

	public Integer getIsReportType() {
		return isReportType;
	}

	public void setIsReportType(Integer isReportType) {
		this.isReportType = isReportType;
	}

	public Integer getIsReport() {
		return isReport;
	}

	public void setIsReport(Integer isReport) {
		this.isReport = isReport;
	}

	public Integer getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(Integer isVerify) {
		this.isVerify = isVerify;
	}

	private String qianshouren;// 签收人

	public String getQianshouren() {
		return qianshouren;
	}

	public void setQianshouren(String qianshouren) {
		this.qianshouren = qianshouren;
	}

	private Integer isHandout;// 下发状态
	private Integer isReceive;// 签收状态

	public Integer getIsHandout() {
		return isHandout;
	}

	public void setIsHandout(Integer isHandout) {
		this.isHandout = isHandout;
	}

	public Integer getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(Integer isReceive) {
		this.isReceive = isReceive;
	}

	// 嫌疑人员信息
	private String advise; // 备注 用于存储工作意见

	public String getAdvise() {
		return advise;
	}

	public void setAdvise(String advise) {
		this.advise = advise;
	}

	public String getAssistUnitCode() {
		return assistUnitCode;
	}

	public void setAssistUnitCode(String assistUnitCode) {
		this.assistUnitCode = assistUnitCode;
	}

	public String getAssistUnitName() {
		return assistUnitName;
	}

	public void setAssistUnitName(String assistUnitName) {
		this.assistUnitName = assistUnitName;
	}

	public Integer getInforClass() {
		return inforClass;
	}

	public void setInforClass(Integer inforClass) {
		this.inforClass = inforClass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInforNo() {
		return inforNo;
	}

	public void setInforNo(String inforNo) {
		this.inforNo = inforNo;
	}

	public String getInforTitle() {
		return inforTitle;
	}

	public void setInforTitle(String inforTitle) {
		this.inforTitle = inforTitle;
	}

	public String getInforContent() {
		return inforContent;
	}

	public void setInforContent(String inforContent) {
		this.inforContent = inforContent;
	}

	public String getHostUnitCode() {
		return hostUnitCode;
	}

	public void setHostUnitCode(String hostUnitCode) {
		this.hostUnitCode = hostUnitCode;
	}

	public String getHostUnitName() {
		return hostUnitName;
	}

	public void setHostUnitName(String hostUnitName) {
		this.hostUnitName = hostUnitName;
	}

}
