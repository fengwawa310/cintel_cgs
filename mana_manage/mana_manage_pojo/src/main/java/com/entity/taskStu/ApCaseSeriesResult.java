package com.entity.taskStu;

import java.util.Date;

/**
 * 描述:ap_case_series_result表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-03-22
 */
public class ApCaseSeriesResult {

    /**
     * 主键
     */
    private String id;

    /**
     * 案件串并事件ID
     */
    private String caseEventId;

    /**
     * 案件串并结果案件编号
     */
    private String ajbh;

    /**
     * 相似度
     */
    private String similar;

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
     * 案件串并事件ID
     * @return CASE_EVENT_ID 案件串并事件ID
     */
    public String getCaseEventId() {
        return caseEventId;
    }

    /**
     * 案件串并事件ID
     * @param caseEventId 案件串并事件ID
     */
    public void setCaseEventId(String caseEventId) {
        this.caseEventId = caseEventId == null ? null : caseEventId.trim();
    }

    /**
     * 案件串并结果案件编号
     * @return AJBH 案件串并结果案件编号
     */
    public String getAjbh() {
        return ajbh;
    }

    /**
     * 案件串并结果案件编号
     * @param ajbh 案件串并结果案件编号
     */
    public void setAjbh(String ajbh) {
        this.ajbh = ajbh == null ? null : ajbh.trim();
    }

    /**
     * 相似度
     * @return SIMILAR 相似度
     */
    public String getSimilar() {
        return similar;
    }

    /**
     * 相似度
     * @param similar 相似度
     */
    public void setSimilar(String similar) {
        this.similar = similar == null ? null : similar.trim();
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