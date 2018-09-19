package com.entity.ticket;

import java.util.Date;

/**
 * 描述:et_ticket表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-01-31
 */
public class EtTicket {
    /**
     * 消息ID主键
     */
    private String id;

    /**
     * 通话开始时间
     */
    private Date startTime;
    /**
     * 通话开始时间（临时变量）
     */
    private String startTimeStr;

    /**
     * 通话结束时间
     */
    private Date endTime;
    /**
     * 通话结束时间（临时变量）
     */
    private String endTimeStr;

    /**
     * 事件类型 参考字典“话单事件类型”
     */
    private Integer eventType;

    /**
     * 主叫号码
     */
    private String callingNumber;

    /**
     * 被叫号码
     */
    private String endNumber;

    /**
     * 主叫IMSI
     */
    private String callingNubImsi;

    /**
     * 被叫IMSI
     */
    private String endNubImsi;

    /**
     * 主叫IMEI
     */
    private String callingNubImei;

    /**
     * 被叫IMEI
     */
    private String endNubImei;

    /**
     * 主叫归属地
     */
    private String callingHome;

    /**
     * 被叫归属地
     */
    private String endHome;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 消息ID主键
     * @return ID 消息ID主键
     */
    public String getId() {
        return id;
    }

    /**
     * 消息ID主键
     * @param id 消息ID主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 通话开始时间
     * @return START_TIME 通话开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 通话开始时间
     * @param startTime 通话开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 通话结束时间
     * @return END_TIME 通话结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 通话结束时间
     * @param endTime 通话结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 事件类型 参考字典“话单事件类型”
     * @return EVENT_TYPE 事件类型 参考字典“话单事件类型”
     */
    public Integer getEventType() {
        return eventType;
    }

    /**
     * 事件类型 参考字典“话单事件类型”
     * @param eventType 事件类型 参考字典“话单事件类型”
     */
    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    /**
     * 主叫号码
     * @return CALLING_NUMBER 主叫号码
     */
    public String getCallingNumber() {
        return callingNumber;
    }

    /**
     * 主叫号码
     * @param callingNumber 主叫号码
     */
    public void setCallingNumber(String callingNumber) {
        this.callingNumber = callingNumber == null ? null : callingNumber.trim();
    }

    /**
     * 被叫号码
     * @return END_NUMBER 被叫号码
     */
    public String getEndNumber() {
        return endNumber;
    }

    /**
     * 被叫号码
     * @param endNumber 被叫号码
     */
    public void setEndNumber(String endNumber) {
        this.endNumber = endNumber == null ? null : endNumber.trim();
    }

    /**
     * 主叫IMSI
     * @return CALLING_NUB_IMSI 主叫IMSI
     */
    public String getCallingNubImsi() {
        return callingNubImsi;
    }

    /**
     * 主叫IMSI
     * @param callingNubImsi 主叫IMSI
     */
    public void setCallingNubImsi(String callingNubImsi) {
        this.callingNubImsi = callingNubImsi == null ? null : callingNubImsi.trim();
    }

    /**
     * 被叫IMSI
     * @return END_NUB_IMSI 被叫IMSI
     */
    public String getEndNubImsi() {
        return endNubImsi;
    }

    /**
     * 被叫IMSI
     * @param endNubImsi 被叫IMSI
     */
    public void setEndNubImsi(String endNubImsi) {
        this.endNubImsi = endNubImsi == null ? null : endNubImsi.trim();
    }

    /**
     * 主叫IMEI
     * @return CALLING_NUB_IMEI 主叫IMEI
     */
    public String getCallingNubImei() {
        return callingNubImei;
    }

    /**
     * 主叫IMEI
     * @param callingNubImei 主叫IMEI
     */
    public void setCallingNubImei(String callingNubImei) {
        this.callingNubImei = callingNubImei == null ? null : callingNubImei.trim();
    }

    /**
     * 被叫IMEI
     * @return END_NUB_IMEI 被叫IMEI
     */
    public String getEndNubImei() {
        return endNubImei;
    }

    /**
     * 被叫IMEI
     * @param endNubImei 被叫IMEI
     */
    public void setEndNubImei(String endNubImei) {
        this.endNubImei = endNubImei == null ? null : endNubImei.trim();
    }

    /**
     * 主叫归属地
     * @return CALLING_HOME 主叫归属地
     */
    public String getCallingHome() {
        return callingHome;
    }

    /**
     * 主叫归属地
     * @param callingHome 主叫归属地
     */
    public void setCallingHome(String callingHome) {
        this.callingHome = callingHome == null ? null : callingHome.trim();
    }

    /**
     * 被叫归属地
     * @return END_HOME 被叫归属地
     */
    public String getEndHome() {
        return endHome;
    }

    /**
     * 被叫归属地
     * @param endHome 被叫归属地
     */
    public void setEndHome(String endHome) {
        this.endHome = endHome == null ? null : endHome.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     * @return MODIFY_TIME 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }
}