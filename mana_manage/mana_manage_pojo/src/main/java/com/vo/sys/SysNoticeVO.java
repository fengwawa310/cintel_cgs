package com.vo.sys;

import com.common.enums.EnumTypeVO;
import com.entity.sys.SysNotice;

import java.util.Date;

/**
 * 系统通知vo
 *
 * @author admin
 * @create 2018-01-23 10:06
 **/
public class SysNoticeVO extends SysNotice {

    private String isRead;

    /*用户消息关联表id*/
    private String userNoticId;


    private EnumTypeVO isSendEnum;

    public String getUserNoticId() {
        return userNoticId;
    }

    public void setUserNoticId(String userNoticId) {
        this.userNoticId = userNoticId;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public EnumTypeVO getIsSendEnum() {
        return isSendEnum;
    }

    public void setIsSendEnum(EnumTypeVO isSendEnum) {
        this.isSendEnum = isSendEnum;
    }

    public SysNoticeVO() {
    }

    public SysNoticeVO(String id, String title, String sendUnitCode, String sendUnitName, String senderNo, String senderName, String receiveUnitCode, String receiveUnitName, String receiverCode, String receiverName, Integer msgClass, Integer isSend, Date creatTime, Date sendingTime, String msgText) {
        super(id, title, sendUnitCode, sendUnitName, senderNo, senderName, receiveUnitCode, receiveUnitName, receiverCode, receiverName, msgClass, isSend, creatTime, sendingTime, msgText);
    }

    public SysNoticeVO(String id, String title, String sendUnitCode, String sendUnitName, String senderNo, String senderName, String receiveUnitCode, String receiveUnitName, String receiverCode, String receiverName, Integer msgClass, Integer isSend, Date creatTime, Date sendingTime, String msgText, String isRead) {
        super(id, title, sendUnitCode, sendUnitName, senderNo, senderName, receiveUnitCode, receiveUnitName, receiverCode, receiverName, msgClass, isSend, creatTime, sendingTime, msgText);
        this.isRead = isRead;
    }

    public static SysNoticeVO valueOf(SysNotice sysNotice) {
        SysNoticeVO sysNoticeVO = new SysNoticeVO(
                sysNotice.getId(),
                sysNotice.getTitle(),
                sysNotice.getSendUnitCode(),
                sysNotice.getSendUnitName(),
                sysNotice.getSenderNo(),
                sysNotice.getSenderName(),
                sysNotice.getReceiveUnitCode(),
                sysNotice.getReceiveUnitName(),
                sysNotice.getReceiverCode(),
                sysNotice.getReceiverName(),
                sysNotice.getMsgClass(),
                sysNotice.getIsSend(),
                sysNotice.getCreatTime(),
                sysNotice.getSendingTime(),
                sysNotice.getMsgText()
        );
        return sysNoticeVO;
    }
}
