package com.entity.sys;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 描述:sys_notice表的实体类
 * @version
 * @author:  admin
 * @创建时间: 2018-01-19
 */
public class SysNotice {
    /**
     * 通知ID 主键
     */
    private String id;

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
    private Integer msgClass;

    /**
     * 是否发送 0未发送；1已发送。默认0
     */
    private Integer isSend;

    /**
     * 消息创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creatTime;

    /**
     * 执行下发时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendingTime;

    /**
     * 消息文本
     */
    private String msgText;
    
    /**
	 * 分页查询起始位置
	 */
	private Integer start;
	/**
	 * 分页查询每页个数
	 */
	private Integer length;
	/**
	 * 用户等级
	 */
	private String level;


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

    /**
     * 通知ID 主键
     * @return ID 通知ID 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 通知ID 主键
     * @param id 通知ID 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 标题（主题）
     * @return TITLE 标题（主题）
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题（主题）
     * @param title 标题（主题）
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 发送单位编码
     * @return SEND_UNIT_CODE 发送单位编码
     */
    public String getSendUnitCode() {
        return sendUnitCode;
    }

    /**
     * 发送单位编码
     * @param sendUnitCode 发送单位编码
     */
    public void setSendUnitCode(String sendUnitCode) {
        this.sendUnitCode = sendUnitCode == null ? null : sendUnitCode.trim();
    }

    /**
     * 发送单位名称
     * @return SEND_UNIT_NAME 发送单位名称
     */
    public String getSendUnitName() {
        return sendUnitName;
    }

    /**
     * 发送单位名称
     * @param sendUnitName 发送单位名称
     */
    public void setSendUnitName(String sendUnitName) {
        this.sendUnitName = sendUnitName == null ? null : sendUnitName.trim();
    }

    /**
     * 发送人编号
     * @return SENDER_NO 发送人编号
     */
    public String getSenderNo() {
        return senderNo;
    }

    /**
     * 发送人编号
     * @param senderNo 发送人编号
     */
    public void setSenderNo(String senderNo) {
        this.senderNo = senderNo == null ? null : senderNo.trim();
    }

    /**
     * 发送人姓名
     * @return SENDER_NAME 发送人姓名
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * 发送人姓名
     * @param senderName 发送人姓名
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    /**
     * 通知类型 字典“通知类型”
     * @return MSG_CLASS 通知类型 字典“通知类型”
     */
    public Integer getMsgClass() {
        return msgClass;
    }

    /**
     * 通知类型 字典“通知类型”
     * @param msgClass 通知类型 字典“通知类型”
     */
    public void setMsgClass(Integer msgClass) {
        this.msgClass = msgClass;
    }

    /**
     * 是否发送 0未发送；1已发送。默认0
     * @return IS_SEND 是否发送 0未发送；1已发送。默认0
     */
    public Integer getIsSend() {
        return isSend;
    }

    /**
     * 是否发送 0未发送；1已发送。默认0
     * @param isSend 是否发送 0未发送；1已发送。默认0
     */
    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    /**
     * 消息创建时间
     * @return CREAT_TIME 消息创建时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 消息创建时间
     * @param creatTime 消息创建时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * 执行下发时间
     * @return SENDING_TIME 执行下发时间
     */
    public Date getSendingTime() {
        return sendingTime;
    }

    /**
     * 执行下发时间
     * @param sendingTime 执行下发时间
     */
    public void setSendingTime(Date sendingTime) {
        this.sendingTime = sendingTime;
    }

    /**
     * 消息文本
     * @return MSG_TEXT 消息文本
     */
    public String getMsgText() {
        return msgText;
    }

    /**
     * 消息文本
     * @param msgText 消息文本
     */
    public void setMsgText(String msgText) {
        this.msgText = msgText == null ? null : msgText.trim();
    }

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}


    public SysNotice() {
    }

    public SysNotice(String id, String title, String sendUnitCode, String sendUnitName, String senderNo, String senderName, String receiveUnitCode, String receiveUnitName, String receiverCode, String receiverName, Integer msgClass, Integer isSend, Date creatTime, Date sendingTime, String msgText, Integer start, Integer length, String level) {
        this.id = id;
        this.title = title;
        this.sendUnitCode = sendUnitCode;
        this.sendUnitName = sendUnitName;
        this.senderNo = senderNo;
        this.senderName = senderName;
        this.receiveUnitCode = receiveUnitCode;
        this.receiveUnitName = receiveUnitName;
        this.receiverCode = receiverCode;
        this.receiverName = receiverName;
        this.msgClass = msgClass;
        this.isSend = isSend;
        this.creatTime = creatTime;
        this.sendingTime = sendingTime;
        this.msgText = msgText;
        this.start = start;
        this.length = length;
        this.level = level;
    }
    public SysNotice(String id, String title, String sendUnitCode, String sendUnitName, String senderNo, String senderName,  String receiveUnitCode, String receiveUnitName, String receiverCode, String receiverName, Integer msgClass, Integer isSend, Date creatTime, Date sendingTime, String msgText) {
        this.id = id;
        this.title = title;
        this.sendUnitCode = sendUnitCode;
        this.sendUnitName = sendUnitName;
        this.senderNo = senderNo;
        this.senderName = senderName;
        this.receiveUnitCode = receiveUnitCode;
        this.receiveUnitName = receiveUnitName;
        this.receiverCode = receiverCode;
        this.receiverName = receiverName;
        this.msgClass = msgClass;
        this.isSend = isSend;
        this.creatTime = creatTime;
        this.sendingTime = sendingTime;
        this.msgText = msgText;
    }
}