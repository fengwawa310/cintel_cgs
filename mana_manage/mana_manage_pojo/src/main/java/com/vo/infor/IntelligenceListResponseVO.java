package com.vo.infor;

import java.util.List;

import com.entity.infor.ApInforStaff;

//响应页面封装后台返回参数实体
public class IntelligenceListResponseVO {
	private String inforNo;// 情报研判编号
	private String inforTitle;// 研判标题
	private String qianFaPersion;// 签发人姓名
	private String shenHePersion;// 审核人姓名
	private String shenHeType;// 审核状态
	private String liuZhuanType;// 流转状态
	private String creatTime;// 生成时间
	private String banJieType;// 单位办结状态
	private String banJieTime;// 单位办结时间
	private String hostUnitCode;// 查证主办单位编号
	private String hostUnitName;// 查证主办单位名称
	private String assistUnitCode;// 查证协办单位编号
	private String assistUnitName;// 查证协办单位名称
	private String undertakeCode;// 承办单位编号
	private String undertakeName;// 承办单位名称
	private String undertakePersionCode;// 承办人编号
	private String undertakePersionName;// 承办人名称
	private String fankuiType;// 反馈状态
	private String fujianUrl;// 附件地址
	private String process;// 查证过程
	private String result;// 查证结果
	private String receiveTime;// 情报接收时间
	private String receivePerson;// 情报接收人员编码
	private String receivePersonName;// 情报接收人员名称
	private String receiveUnit;// 情报接收单位编码
	private String receiveUnitName;// 情报接收单位名称
	private String inforClass;// 情报类型
	private String reportUnit;// 上报单位编码
	private String reportPerson;// 上报人编号
	private String reportTime;// 上报时间
	private String advise;
	private String launchUnitName;
	private String launchPname;
	private String receiveShenheUnit; // 情报接收审核单位
	private String receiveShenhePersion;// 情报接收审核人
	private String receiveShenheTime;// 情报接收审核时间
	private String isReportType;// 上报审核状态
	private String handoutUnit; // 下发单位
	private String handoutUnitName; // 下发单位名称
	private String handoutPerson;// 下发人员编号
	private String handoutPersonName;// 下发人名称
	private String handoutTime;

	private String authorizeUnit; // 情报接收审核单位编号
	private String authorizeUnitName;// 情报接收审核单位名称
	private String authorizePerson;// 情报接收审核人编号
	private String authorizePersonName;// 情报接收审核人姓名
	private String authorizeRemark;// 情报审核意见
	private String isAuthorize;

	private String reportUnitName; // <!-- 上报单位名称 -->
	private String reportPersonName; // <!-- 上报人名称 -->
	
	private String verifyUnitName;
	private String verifyPersonName;
	private String verifyTime;
	private String level;//响应等级
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getVerifyUnitName() {
		return verifyUnitName;
	}

	public void setVerifyUnitName(String verifyUnitName) {
		this.verifyUnitName = verifyUnitName;
	}

	public String getVerifyPersonName() {
		return verifyPersonName;
	}

	public void setVerifyPersonName(String verifyPersonName) {
		this.verifyPersonName = verifyPersonName;
	}

	public String getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getReportUnitName() {
		return reportUnitName;
	}

	public void setReportUnitName(String reportUnitName) {
		this.reportUnitName = reportUnitName;
	}

	public String getReportPersonName() {
		return reportPersonName;
	}

	public void setReportPersonName(String reportPersonName) {
		this.reportPersonName = reportPersonName;
	}

	public String getIsAuthorize() {
		return isAuthorize;
	}

	public void setIsAuthorize(String isAuthorize) {
		this.isAuthorize = isAuthorize;
	}

	public String getAuthorizeRemark() {
		return authorizeRemark;
	}

	public void setAuthorizeRemark(String authorizeRemark) {
		this.authorizeRemark = authorizeRemark;
	}

	public String getAuthorizeUnit() {
		return authorizeUnit;
	}

	public void setAuthorizeUnit(String authorizeUnit) {
		this.authorizeUnit = authorizeUnit;
	}

	public String getAuthorizeUnitName() {
		return authorizeUnitName;
	}

	public void setAuthorizeUnitName(String authorizeUnitName) {
		this.authorizeUnitName = authorizeUnitName;
	}

	public String getAuthorizePerson() {
		return authorizePerson;
	}

	public void setAuthorizePerson(String authorizePerson) {
		this.authorizePerson = authorizePerson;
	}

	public String getAuthorizePersonName() {
		return authorizePersonName;
	}

	public void setAuthorizePersonName(String authorizePersonName) {
		this.authorizePersonName = authorizePersonName;
	}

	public String getHandoutTime() {
		return handoutTime;
	}

	public void setHandoutTime(String handoutTime) {
		this.handoutTime = handoutTime;
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

	private List<ApInforStaff> xianyirenList;// 嫌疑人实体
	
	public List<ApInforStaff> getXianyirenList() {
		return xianyirenList;
	}

	public void setXianyirenList(List<ApInforStaff> xianyirenList) {
		this.xianyirenList = xianyirenList;
	}

	public String getIsReportType() {
		return isReportType;
	}

	public void setIsReportType(String isReportType) {
		this.isReportType = isReportType;
	}

	public String getReceiveShenheUnit() {
		return receiveShenheUnit;
	}

	public void setReceiveShenheUnit(String receiveShenheUnit) {
		this.receiveShenheUnit = receiveShenheUnit;
	}

	public String getReceiveShenhePersion() {
		return receiveShenhePersion;
	}

	public void setReceiveShenhePersion(String receiveShenhePersion) {
		this.receiveShenhePersion = receiveShenhePersion;
	}

	public String getReceiveShenheTime() {
		return receiveShenheTime;
	}

	public void setReceiveShenheTime(String receiveShenheTime) {
		this.receiveShenheTime = receiveShenheTime;
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

	public String getAdvise() {
		return advise;
	}

	public void setAdvise(String advise) {
		this.advise = advise;
	}

	private Integer isHandout;
	private Integer isReceive;// 查证单位接收状态
	private Integer isReport;// 上报状态
	private Integer isVerify;// 审核

	public Integer getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(Integer isReceive) {
		this.isReceive = isReceive;
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

	public Integer getIsHandout() {
		return isHandout;
	}

	public void setIsHandout(Integer isHandout) {
		this.isHandout = isHandout;
	}

	private String inforContent;// 查证内容

	public String getInforContent() {
		return inforContent;
	}

	public void setInforContent(String inforContent) {
		this.inforContent = inforContent;
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

	public String getQianFaPersion() {
		return qianFaPersion;
	}

	public void setQianFaPersion(String qianFaPersion) {
		this.qianFaPersion = qianFaPersion;
	}

	public String getShenHePersion() {
		return shenHePersion;
	}

	public void setShenHePersion(String shenHePersion) {
		this.shenHePersion = shenHePersion;
	}

	public String getShenHeType() {
		return shenHeType;
	}

	public void setShenHeType(String shenHeType) {
		this.shenHeType = shenHeType;
	}

	public String getLiuZhuanType() {
		return liuZhuanType;
	}

	public void setLiuZhuanType(String liuZhuanType) {
		this.liuZhuanType = liuZhuanType;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getBanJieType() {
		return banJieType;
	}

	public void setBanJieType(String banJieType) {
		this.banJieType = banJieType;
	}

	public String getBanJieTime() {
		return banJieTime;
	}

	public void setBanJieTime(String banJieTime) {
		this.banJieTime = banJieTime;
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

	public String getUndertakeCode() {
		return undertakeCode;
	}

	public void setUndertakeCode(String undertakeCode) {
		this.undertakeCode = undertakeCode;
	}

	public String getUndertakeName() {
		return undertakeName;
	}

	public void setUndertakeName(String undertakeName) {
		this.undertakeName = undertakeName;
	}

	public String getUndertakePersionCode() {
		return undertakePersionCode;
	}

	public void setUndertakePersionCode(String undertakePersionCode) {
		this.undertakePersionCode = undertakePersionCode;
	}

	public String getUndertakePersionName() {
		return undertakePersionName;
	}

	public void setUndertakePersionName(String undertakePersionName) {
		this.undertakePersionName = undertakePersionName;
	}

	public String getFankuiType() {
		return fankuiType;
	}

	public void setFankuiType(String fankuiType) {
		this.fankuiType = fankuiType;
	}

	public String getFujianUrl() {
		return fujianUrl;
	}

	public void setFujianUrl(String fujianUrl) {
		this.fujianUrl = fujianUrl;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}

	public String getReceivePersonName() {
		return receivePersonName;
	}

	public void setReceivePersonName(String receivePersonName) {
		this.receivePersonName = receivePersonName;
	}

	public String getReceiveUnit() {
		return receiveUnit;
	}

	public void setReceiveUnit(String receiveUnit) {
		this.receiveUnit = receiveUnit;
	}

	public String getReceiveUnitName() {
		return receiveUnitName;
	}

	public void setReceiveUnitName(String receiveUnitName) {
		this.receiveUnitName = receiveUnitName;
	}

	public String getInforClass() {
		return inforClass;
	}

	public void setInforClass(String inforClass) {
		this.inforClass = inforClass;
	}

	public String getReportUnit() {
		return reportUnit;
	}

	public void setReportUnit(String reportUnit) {
		this.reportUnit = reportUnit;
	}

	public String getReportPerson() {
		return reportPerson;
	}

	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
}
