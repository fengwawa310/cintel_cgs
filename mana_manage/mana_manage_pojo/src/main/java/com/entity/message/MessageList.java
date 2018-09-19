package com.entity.message;

import java.io.Serializable;

public class MessageList implements Serializable {

	private String id;// '消息ID 主键',
	private String title;// '标题（主题）',
	private String receiveUnitCode;// '接收单位编号',
	private String receiveUnitName;// '接收单位名称',
	private String receiverCode;// '接收者编号',
	private String receiverName;// '接收者姓名',
	private String relationNo;// '关联数据编号',
	private String relationClass;// '消息关联数据类型 字典“消息关联数据类型”',
	private Integer isSend;// '是否发送 0未发送；1已发送。默认0',
	private Integer isRead;// '是否已阅 0未阅读；1已阅读。默认0',
	private String creatTime;// '系统创建时间',
	private String sendingTime;// '执行下发时间',

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

	public String getRelationNo() {
		return relationNo;
	}

	public void setRelationNo(String relationNo) {
		this.relationNo = relationNo;
	}

	public String getRelationClass() {
		return relationClass;
	}

	public void setRelationClass(String relationClass) {
		this.relationClass = relationClass;
	}

	public Integer getIsSend() {
		return isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
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
}
