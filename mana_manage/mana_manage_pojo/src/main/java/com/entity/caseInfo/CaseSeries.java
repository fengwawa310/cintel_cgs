package com.entity.caseInfo;

/**
 * Created by weipc on 2018/1/10.
 */
public class CaseSeries {
    /**
     * 一级串并方式
     */
    private String oneLevel;
    /**
     * 一级串并值
     */
    private String oneLevelValue;
    /**
     * 二级串并方式
     */
    private String twoLevel;
    /**
     * 二级串并值
     */
    private String twoLevelValue;
    /**
     * 受理开始时间
     */
    private String accept_start_time;
    /**
     *受理结束时间
     */
    private String accept_end_time;
    /**
     * 案件ID
     */
    private String caseId;

    /**
     * 串并方式
     */
    private String mode;
    /**
     * 串并值
     */
    private String value;
    /**
     * 串并数量
     */
    private String number;

    public String getOneLevel() {
        return oneLevel;
    }

    public void setOneLevel(String oneLevel) {
        this.oneLevel = oneLevel;
    }

    public String getOneLevelValue() {
        return oneLevelValue;
    }

    public void setOneLevelValue(String oneLevelValue) {
        this.oneLevelValue = oneLevelValue;
    }

    public String getTwoLevel() {
        return twoLevel;
    }

    public void setTwoLevel(String twoLevel) {
        this.twoLevel = twoLevel;
    }

    public String getTwoLevelValue() {
        return twoLevelValue;
    }

    public void setTwoLevelValue(String twoLevelValue) {
        this.twoLevelValue = twoLevelValue;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getAccept_start_time() {
        return accept_start_time;
    }

    public void setAccept_start_time(String accept_start_time) {
        this.accept_start_time = accept_start_time;
    }

    public String getAccept_end_time() {
        return accept_end_time;
    }

    public void setAccept_end_time(String accept_end_time) {
        this.accept_end_time = accept_end_time;
    }
}
