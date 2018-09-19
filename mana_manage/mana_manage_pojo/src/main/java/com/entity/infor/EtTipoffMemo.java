package com.entity.infor;

import java.util.Date;

/**
 * 描述:et_topoff_memo表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-02-28
 */
public class EtTipoffMemo {
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
    private String operatorName;
    
    public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	/**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 备注内容
     */
    private String memoContent;

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

 
    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
     * 备注内容
     * @return MEMO_CONTENT 备注内容
     */
    public String getMemoContent() {
        return memoContent;
    }

    /**
     * 备注内容
     * @param memoContent 备注内容
     */
    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent == null ? null : memoContent.trim();
    }
}