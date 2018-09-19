package com.entity.sys;

/**
 * 描述:sys_user_notice表的实体类
 * @version
 * @author:  admin
 * @创建时间: 2018-01-19
 */
public class SysUserNotice {
    /**
     * 主键
     */
    private String id;

    /**
     * 消息通知主键
     */
    private String noticeId;

    /**
     * 人员主键
     */
    private String userId;

    /**
     * 是否已读（0未读，1已读）
     */
    private String isRead;

    public SysUserNotice() {
    }

    public SysUserNotice(String id, String noticeId, String userId, String isRead) {
        this.id = id;
        this.noticeId = noticeId;
        this.userId = userId;
        this.isRead = isRead;
    }

    /**
     * 主键
     * @return id 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 消息通知主键
     * @return notice_id 消息通知主键
     */
    public String getNoticeId() {
        return noticeId;
    }

    /**
     * 消息通知主键
     * @param noticeId 消息通知主键
     */
    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId == null ? null : noticeId.trim();
    }

    /**
     * 人员主键
     * @return user_id 人员主键
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 人员主键
     * @param userId 人员主键
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 是否已读（0未读，1已读）
     * @return is_read 是否已读（0未读，1已读）
     */
    public String getIsRead() {
        return isRead;
    }

    /**
     * 是否已读（0未读，1已读）
     * @param isRead 是否已读（0未读，1已读）
     */
    public void setIsRead(String isRead) {
        this.isRead = isRead == null ? null : isRead.trim();
    }
}