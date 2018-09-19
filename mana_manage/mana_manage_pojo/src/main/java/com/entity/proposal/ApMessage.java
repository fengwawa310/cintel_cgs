package com.entity.proposal;

/**
 * 描述:ap_message表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-01-11
 */
public class ApMessage {
    /**
     * 主键 外键关联
     */
    private String msgNo;

    /**
     * 消息具体内容
     */
    private String msgText;

    /**
     * 主键 外键关联
     * @return MSG_NO 主键 外键关联
     */
    public String getMsgNo() {
        return msgNo;
    }

    /**
     * 主键 外键关联
     * @param msgNo 主键 外键关联
     */
    public void setMsgNo(String msgNo) {
        this.msgNo = msgNo == null ? null : msgNo.trim();
    }

    /**
     * 消息具体内容
     * @return MSG_TEXT 消息具体内容
     */
    public String getMsgText() {
        return msgText;
    }

    /**
     * 消息具体内容
     * @param msgText 消息具体内容
     */
    public void setMsgText(String msgText) {
        this.msgText = msgText == null ? null : msgText.trim();
    }
}