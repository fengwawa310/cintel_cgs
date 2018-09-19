package com.request.infor;

/**
 * 举报线索数据表的查询参数包装
 *
 * @author admin
 * @create 2018-03-01 11:18
 **/
public class EtTipoffReq {
//      开始日期
    private String startTime;
//    结束日期
    private String endTime;
//    涉案类别
    private String caseClass;
//    举报人姓名
    private String  tipoffReporter;
//    被举报人姓名
    private String  beingReported;
//    线索标题
    private String  clueTitle;

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

    public String getCaseClass() {
        return caseClass;
    }

    public void setCaseClass(String caseClass) {
        this.caseClass = caseClass;
    }

    public String getTipoffReporter() {
        return tipoffReporter;
    }

    public void setTipoffReporter(String tipoffReporter) {
        this.tipoffReporter = tipoffReporter;
    }

    public String getBeingReported() {
        return beingReported;
    }

    public void setBeingReported(String beingReported) {
        this.beingReported = beingReported;
    }

    public String getClueTitle() {
        return clueTitle;
    }

    public void setClueTitle(String clueTitle) {
        this.clueTitle = clueTitle;
    }
}
