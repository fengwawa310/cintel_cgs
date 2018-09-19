package com.entity.infor;

public class RequestVO {

	
	private String beingReported;//被举报人姓名
	private String beingReportedPhone;//被举报人电话
	private String beingReportedIdcard;//被举报人身份证号
	private String tipoffReporter;//举报人姓名
	private String reporterPhone;//举报人电话
	private String reporterIdcard;//举报人身份证号
	private String reprotType;//举报标识 1举报人 2 被举报人
	public String getBeingReported() {
		return beingReported;
	}
	public void setBeingReported(String beingReported) {
		this.beingReported = beingReported;
	}
	public String getBeingReportedPhone() {
		return beingReportedPhone;
	}
	public void setBeingReportedPhone(String beingReportedPhone) {
		this.beingReportedPhone = beingReportedPhone;
	}
	public String getBeingReportedIdcard() {
		return beingReportedIdcard;
	}
	public void setBeingReportedIdcard(String beingReportedIdcard) {
		this.beingReportedIdcard = beingReportedIdcard;
	}
	public String getReprotType() {
		return reprotType;
	}
	public void setReprotType(String reprotType) {
		this.reprotType = reprotType;
	}
	public String getTipoffReporter() {
		return tipoffReporter;
	}
	public void setTipoffReporter(String tipoffReporter) {
		this.tipoffReporter = tipoffReporter;
	}
	public String getReporterPhone() {
		return reporterPhone;
	}
	public void setReporterPhone(String reporterPhone) {
		this.reporterPhone = reporterPhone;
	}
	public String getReporterIdcard() {
		return reporterIdcard;
	}
	public void setReporterIdcard(String reporterIdcard) {
		this.reporterIdcard = reporterIdcard;
	}
	
	
	
}
