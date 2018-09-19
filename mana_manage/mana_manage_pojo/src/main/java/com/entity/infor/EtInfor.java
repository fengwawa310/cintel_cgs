package com.entity.infor;

import java.util.Date;

/**
 * 描述:et_infor表的实体类
 * @version
 * @author:  admin
 * @创建时间: 2018-01-03
 */
public class EtInfor {
    /**
     * 主键 
     */
    private String id;

    /**
     * 情报编号
     */
    private String inforNo;

    /**
     * 情报标题
     */
    private String inforTitle;

    /**
     * 情报类型 字典“情报类型”
     */
    private Integer inforClass;

    /**
     * 发起单位编码
     */
    private String launchUnitCode;

    /**
     * 发起单位名称
     */
    private String launchUnitName;

    /**
     * 发起人编码
     */
    private String launchPCode;

    /**
     * 发起人姓名
     */
    private String launchPName;

    /**
     * 发起时间
     */
    private Date launchTime;

    /**
     * 主办单位编码
     */
    private String hostUnitCode;

    /**
     * 主办单位名称
     */
    private String hostUnitName;

    /**
     * 主办人编码
     */
    private String sponsorCode;

    /**
     * 主办人姓名
     */
    private String sponsorName;

    /**
     * 协办单位编码
     */
    private String assistUnitCode;

    /**
     * 协办单位名称
     */
    private String assistUnitName;

    /**
     * 协办人编码
     */
    private String assistantCode;

    /**
     * 协办人姓名
     */
    private String assistantName;

    /**
     * 查证结束时间
     */
    private Date finishTime;

    /**
     * 系统创建时间
     */
    private Date creatTime;

    /**
     * 系统修改时间
     */
    private Date modifyTime;

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
     * 情报编号
     * @return INFOR_NO 情报编号
     */
    public String getInforNo() {
        return inforNo;
    }

    /**
     * 情报编号
     * @param inforNo 情报编号
     */
    public void setInforNo(String inforNo) {
        this.inforNo = inforNo == null ? null : inforNo.trim();
    }

    /**
     * 情报标题
     * @return INFOR_TITLE 情报标题
     */
    public String getInforTitle() {
        return inforTitle;
    }

    /**
     * 情报标题
     * @param inforTitle 情报标题
     */
    public void setInforTitle(String inforTitle) {
        this.inforTitle = inforTitle == null ? null : inforTitle.trim();
    }

    /**
     * 情报类型 字典“情报类型”
     * @return INFOR_CLASS 情报类型 字典“情报类型”
     */
    public Integer getInforClass() {
        return inforClass;
    }

    /**
     * 情报类型 字典“情报类型”
     * @param inforClass 情报类型 字典“情报类型”
     */
    public void setInforClass(Integer inforClass) {
        this.inforClass = inforClass;
    }

    /**
     * 发起单位编码
     * @return LAUNCH_UNIT_CODE 发起单位编码
     */
    public String getLaunchUnitCode() {
        return launchUnitCode;
    }

    /**
     * 发起单位编码
     * @param launchUnitCode 发起单位编码
     */
    public void setLaunchUnitCode(String launchUnitCode) {
        this.launchUnitCode = launchUnitCode == null ? null : launchUnitCode.trim();
    }

    /**
     * 发起单位名称
     * @return LAUNCH_UNIT_NAME 发起单位名称
     */
    public String getLaunchUnitName() {
        return launchUnitName;
    }

    /**
     * 发起单位名称
     * @param launchUnitName 发起单位名称
     */
    public void setLaunchUnitName(String launchUnitName) {
        this.launchUnitName = launchUnitName == null ? null : launchUnitName.trim();
    }

    /**
     * 发起人编码
     * @return LAUNCH_P_CODE 发起人编码
     */
    public String getLaunchPCode() {
        return launchPCode;
    }

    /**
     * 发起人编码
     * @param launchPCode 发起人编码
     */
    public void setLaunchPCode(String launchPCode) {
        this.launchPCode = launchPCode == null ? null : launchPCode.trim();
    }

    /**
     * 发起人姓名
     * @return LAUNCH_P_NAME 发起人姓名
     */
    public String getLaunchPName() {
        return launchPName;
    }

    /**
     * 发起人姓名
     * @param launchPName 发起人姓名
     */
    public void setLaunchPName(String launchPName) {
        this.launchPName = launchPName == null ? null : launchPName.trim();
    }

    /**
     * 发起时间
     * @return LAUNCH_TIME 发起时间
     */
    public Date getLaunchTime() {
        return launchTime;
    }

    /**
     * 发起时间
     * @param launchTime 发起时间
     */
    public void setLaunchTime(Date launchTime) {
        this.launchTime = launchTime;
    }

    /**
     * 主办单位编码
     * @return HOST_UNIT_CODE 主办单位编码
     */
    public String getHostUnitCode() {
        return hostUnitCode;
    }

    /**
     * 主办单位编码
     * @param hostUnitCode 主办单位编码
     */
    public void setHostUnitCode(String hostUnitCode) {
        this.hostUnitCode = hostUnitCode == null ? null : hostUnitCode.trim();
    }

    /**
     * 主办单位名称
     * @return HOST_UNIT_NAME 主办单位名称
     */
    public String getHostUnitName() {
        return hostUnitName;
    }

    /**
     * 主办单位名称
     * @param hostUnitName 主办单位名称
     */
    public void setHostUnitName(String hostUnitName) {
        this.hostUnitName = hostUnitName == null ? null : hostUnitName.trim();
    }

    /**
     * 主办人编码
     * @return SPONSOR_CODE 主办人编码
     */
    public String getSponsorCode() {
        return sponsorCode;
    }

    /**
     * 主办人编码
     * @param sponsorCode 主办人编码
     */
    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode == null ? null : sponsorCode.trim();
    }

    /**
     * 主办人姓名
     * @return SPONSOR_NAME 主办人姓名
     */
    public String getSponsorName() {
        return sponsorName;
    }

    /**
     * 主办人姓名
     * @param sponsorName 主办人姓名
     */
    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName == null ? null : sponsorName.trim();
    }

    /**
     * 协办单位编码
     * @return ASSIST_UNIT_CODE 协办单位编码
     */
    public String getAssistUnitCode() {
        return assistUnitCode;
    }

    /**
     * 协办单位编码
     * @param assistUnitCode 协办单位编码
     */
    public void setAssistUnitCode(String assistUnitCode) {
        this.assistUnitCode = assistUnitCode == null ? null : assistUnitCode.trim();
    }

    /**
     * 协办单位名称
     * @return ASSIST_UNIT_NAME 协办单位名称
     */
    public String getAssistUnitName() {
        return assistUnitName;
    }

    /**
     * 协办单位名称
     * @param assistUnitName 协办单位名称
     */
    public void setAssistUnitName(String assistUnitName) {
        this.assistUnitName = assistUnitName == null ? null : assistUnitName.trim();
    }

    /**
     * 协办人编码
     * @return ASSISTANT_CODE 协办人编码
     */
    public String getAssistantCode() {
        return assistantCode;
    }

    /**
     * 协办人编码
     * @param assistantCode 协办人编码
     */
    public void setAssistantCode(String assistantCode) {
        this.assistantCode = assistantCode == null ? null : assistantCode.trim();
    }

    /**
     * 协办人姓名
     * @return ASSISTANT_NAME 协办人姓名
     */
    public String getAssistantName() {
        return assistantName;
    }

    /**
     * 协办人姓名
     * @param assistantName 协办人姓名
     */
    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName == null ? null : assistantName.trim();
    }

    /**
     * 查证结束时间
     * @return FINISH_TIME 查证结束时间
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * 查证结束时间
     * @param finishTime 查证结束时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * 系统创建时间
     * @return CREAT_TIME 系统创建时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 系统创建时间
     * @param creatTime 系统创建时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * 系统修改时间
     * @return MODIFY_TIME 系统修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 系统修改时间
     * @param modifyTime 系统修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}