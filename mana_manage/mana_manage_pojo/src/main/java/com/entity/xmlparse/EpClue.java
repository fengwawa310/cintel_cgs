package com.entity.xmlparse;

/**
 * 描述:ep_clue表的实体类
 * @version
 * @author:  admin
 * @创建时间: 2018-03-15
 */
public class EpClue {
    /**
     * 
     */
    private String id;

    /**
     * 团伙ID
     */
    private String gangId;

    /**
     * 线索类型
     */
    private String type;

    /**
     * 案件编号
     */
    private String caseNo;

    /**
     * 案件名称
     */
    private String caseName;

    /**
     * 简要案情
     */
    private String caseDetail;

    /**
     * 备注
     */
    private String remark;

    /**
     * 涉嫌人
     */
    private String suspect;

    /**
     * 
     * @return id 
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 团伙ID
     * @return gang_id 团伙ID
     */
    public String getGangId() {
        return gangId;
    }

    /**
     * 团伙ID
     * @param gangId 团伙ID
     */
    public void setGangId(String gangId) {
        this.gangId = gangId == null ? null : gangId.trim();
    }

    /**
     * 线索类型
     * @return type 线索类型
     */
    public String getType() {
        return type;
    }

    /**
     * 线索类型
     * @param type 线索类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 案件编号
     * @return case_no 案件编号
     */
    public String getCaseNo() {
        return caseNo;
    }

    /**
     * 案件编号
     * @param caseNo 案件编号
     */
    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo == null ? null : caseNo.trim();
    }

    /**
     * 案件名称
     * @return case_name 案件名称
     */
    public String getCaseName() {
        return caseName;
    }

    /**
     * 案件名称
     * @param caseName 案件名称
     */
    public void setCaseName(String caseName) {
        this.caseName = caseName == null ? null : caseName.trim();
    }

    /**
     * 简要案情
     * @return case_detail 简要案情
     */
    public String getCaseDetail() {
        return caseDetail;
    }

    /**
     * 简要案情
     * @param caseDetail 简要案情
     */
    public void setCaseDetail(String caseDetail) {
        this.caseDetail = caseDetail == null ? null : caseDetail.trim();
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 涉嫌人
     * @return suspect 涉嫌人
     */
    public String getSuspect() {
        return suspect;
    }

    /**
     * 涉嫌人
     * @param suspect 涉嫌人
     */
    public void setSuspect(String suspect) {
        this.suspect = suspect == null ? null : suspect.trim();
    }
}