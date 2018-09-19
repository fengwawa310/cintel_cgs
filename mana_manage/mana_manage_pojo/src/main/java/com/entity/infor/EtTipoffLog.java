package com.entity.infor;

import java.util.Date;

/**
 * 描述:et_topoff_log表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-02-28
 */
public class EtTipoffLog {
    /**
     * 主键
     */
    private String id;

    /**
     * 举报ID
     */
    private String tipoffId;

    /**
     * 操作类型 字典“举报操作类型”
     */
    private String operateType;

    /**
     * 操作人ID
     */
    private String operator;

    /**
     * 创建时间
     */
    private Date createTime;

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
     * 举报ID
     * @return TIPOFF_ID 举报ID
     */
    public String getTipoffId() {
        return tipoffId;
    }

    /**
     * 举报ID
     * @param tipoffId 举报ID
     */
    public void setTipoffId(String tipoffId) {
        this.tipoffId = tipoffId == null ? null : tipoffId.trim();
    }

    /**
     * 操作类型 字典“举报操作类型”
     * @return OPERATE_TYPE 操作类型 字典“举报操作类型”
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 操作类型 字典“举报操作类型”
     * @param operateType 操作类型 字典“举报操作类型”
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    /**
     * 操作人ID
     * @return OPERATOR 操作人ID
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 操作人ID
     * @param operator 操作人ID
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}