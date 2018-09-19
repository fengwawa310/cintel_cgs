package com.entity.ticket;

/**
 * 描述:et_communication表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-01-31
 */
public class EtCommunication {
    /**
     * 主键
     */
    private String id;

    /**
     * 本机号码
     */
    private String localNumber;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactNumber;



    /**
     * 主键
     * @return ID 主键
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
     * 本机号码
     * @return LOCAL_NUMBER 本机号码
     */
    public String getLocalNumber() {
        return localNumber;
    }

    /**
     * 本机号码
     * @param localNumber 本机号码
     */
    public void setLocalNumber(String localNumber) {
        this.localNumber = localNumber == null ? null : localNumber.trim();
    }

    /**
     * 联系人姓名
     * @return CONTACT_NAME 联系人姓名
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * 联系人姓名
     * @param contactName 联系人姓名
     */
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    /**
     * 联系人电话
     * @return CONTACT_NUMBER 联系人电话
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * 联系人电话
     * @param contactNumber 联系人电话
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber == null ? null : contactNumber.trim();
    }

    public EtCommunication() {
    }

    public EtCommunication(String id, String localNumber, String contactName, String contactNumber) {
        this.id = id;
        this.localNumber = localNumber;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "EtCommunication{" +
                "id='" + id + '\'' +
                ", localNumber='" + localNumber + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}