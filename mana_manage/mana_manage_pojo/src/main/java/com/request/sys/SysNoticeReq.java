package com.request.sys;

import com.common.utils.DateHelp;
import com.entity.sys.SysNotice;

/**
 * 系统通知请求参数包装
 *
 * @author admin
 * @create 2018-01-19 16:28
 **/
public class SysNoticeReq {
    /**
     * 通知ID 主键
     */
    private String id;


    /*用户消息关联表id*/
    private String userNoticId;

    /**
     * 标题（主题）
     */
    private String title;

    /**
     * 发送单位编码
     */
    private String sendUnitCode;

    /**
     * 发送单位名称
     */
    private String sendUnitName;

    /**
     * 发送人编号
     */
    private String senderNo;

    /**
     * 发送人姓名
     */
    private String senderName;

    /**
     * 接收单位编号
     */
    private String receiveUnitCode;

    /**
     * 接收单位名称
     */
    private String receiveUnitName;

    /**
     * 接收者编号
     */
    private String receiverCode;

    /**
     * 接收者姓名
     */
    private String receiverName;

    /**
     * 通知类型 字典“通知类型”
     */
    private String msgClass;

    /**
     * 是否发送 0未发送；1已发送。默认0
     */
    private String isSend;

    /**
     * 消息创建时间
     */
    private String creatTime;

    /**
     * 执行下发时间
     */
    private String sendingTime;

    /**
     * 消息文本
     */
    private String msgText;

    private String userId;


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

    public String getReceiverCode() {
        return receiverCode;
    }

    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSendUnitCode() {
        return sendUnitCode;
    }

    public void setSendUnitCode(String sendUnitCode) {
        this.sendUnitCode = sendUnitCode;
    }

    public String getSendUnitName() {
        return sendUnitName;
    }

    public void setSendUnitName(String sendUnitName) {
        this.sendUnitName = sendUnitName;
    }

    public String getSenderNo() {
        return senderNo;
    }

    public void setSenderNo(String senderNo) {
        this.senderNo = senderNo;
    }

    public String getUserNoticId() {
        return userNoticId;
    }

    public void setUserNoticId(String userNoticId) {
        this.userNoticId = userNoticId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMsgClass() {
        return msgClass;
    }

    public void setMsgClass(String msgClass) {
        this.msgClass = msgClass;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(String sendingTime) {
        this.sendingTime = sendingTime;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public SysNoticeReq() {
    }

    public SysNoticeReq(String id, String title, String sendUnitCode, String sendUnitName, String senderNo, String senderName, String msgClass, String isSend, String creatTime, String sendingTime, String msgText) {
        this.id = id;
        this.title = title;
        this.sendUnitCode = sendUnitCode;
        this.sendUnitName = sendUnitName;
        this.senderNo = senderNo;
        this.senderName = senderName;
        this.msgClass = msgClass;
        this.isSend = isSend;
        this.creatTime = creatTime;
        this.sendingTime = sendingTime;
        this.msgText = msgText;
    }


    public static SysNotice valueOf(SysNoticeReq sysNoticeReq) {
        SysNotice sysNotice = new SysNotice(
                sysNoticeReq.getId(),
                sysNoticeReq.getTitle(),
                sysNoticeReq.getSendUnitCode(),
                sysNoticeReq.getSendUnitName(),
                sysNoticeReq.getSenderNo(),
                sysNoticeReq.getSenderName(),
                sysNoticeReq.getReceiveUnitCode(),
                sysNoticeReq.getReceiveUnitName(),
                sysNoticeReq.getReceiverCode(),
                sysNoticeReq.getReceiverName(),
                Integer.parseInt(sysNoticeReq.getMsgClass()),
                Integer.parseInt(sysNoticeReq.getIsSend()),
                DateHelp.str2Date(sysNoticeReq.getCreatTime(), DateHelp.DATATIMEF_STR),
                DateHelp.str2Date(sysNoticeReq.getSendingTime(), DateHelp.DATATIMEF_STR),
                sysNoticeReq.getMsgText()
        );
        return sysNotice;
    }
}
