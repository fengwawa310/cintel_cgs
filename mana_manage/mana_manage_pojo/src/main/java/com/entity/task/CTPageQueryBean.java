package com.entity.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/9.
 */
public class CTPageQueryBean {

    /**
     * 指定的开始时间 格式“2016-01-01 00:00:00”
     */
    private String appointBeginTimeStr;

    /**
     * 指定的结束时间 格式“2016-01-01 00:00:00”
     */
    private String appointEndTimeStr;

    /**
     * 分页开始下标
     */
    private int beginIndex;

    /**
     * 分页结束下标
     */
    private int endIndex;

    public CTPageQueryBean() {
    }

    public CTPageQueryBean(Date appointBeginTime, Date appointEndTime, int beginIndex, int endIndex) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (appointBeginTime == null) {
            this.appointBeginTimeStr = null;
        } else {
            this.appointBeginTimeStr = sdf.format(appointBeginTime);
        }
        if (appointEndTime == null) {
            this.appointEndTimeStr = null;
        } else {
            this.appointEndTimeStr = sdf.format(appointEndTime);
        }
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
    }

    public CTPageQueryBean(String appointBeginTimeStr, String appointEndTimeStr, int beginIndex, int endIndex) {
        this.appointBeginTimeStr = appointBeginTimeStr;
        this.appointEndTimeStr = appointEndTimeStr;
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
    }

    public String getAppointBeginTimeStr() {
        return appointBeginTimeStr;
    }

    public void setAppointBeginTimeStr(String appointBeginTimeStr) {
        this.appointBeginTimeStr = appointBeginTimeStr;
    }

    public String getAppointEndTimeStr() {
        return appointEndTimeStr;
    }

    public void setAppointEndTimeStr(String appointEndTimeStr) {
        this.appointEndTimeStr = appointEndTimeStr;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
