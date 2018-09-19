package com.entity;

import java.io.Serializable;

/**
 * 描述:dic_common表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-01-02
 */
public class DicCommon implements Serializable{
    private static final long serialVersionUID = 7247714666080613254L;
    /**
     * 主键 字典唯一编码
     */
    private String dicCode;

    /**
     * 字典含义
     */
    private String dicDes;
    
    private String dicValue;

    /**
     * 父级字典编码
     */
    private String parentCode;

    /**
     * 父级字典含义
     */
    private String parentDes;

    /**
     * 0正常,1已废弃,默认为0
     */
    private Integer isAbandon;

    /**
     * 主键 字典唯一编码
     * @return DIC_CODE 主键 字典唯一编码
     */
    public String getDicCode() {
        return dicCode;
    }

    /**
     * 主键 字典唯一编码
     * @param dicCode 主键 字典唯一编码
     */
    public void setDicCode(String dicCode) {
        this.dicCode = dicCode == null ? null : dicCode.trim();
    }

    /**
     * 字典含义
     * @return DIC_DES 字典含义
     */
    public String getDicDes() {
        return dicDes;
    }
    
    

    public String getDicValue() {
		return dicValue;
	}

	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}

	/**
     * 字典含义
     * @param dicDes 字典含义
     */
    public void setDicDes(String dicDes) {
        this.dicDes = dicDes == null ? null : dicDes.trim();
    }

    /**
     * 父级字典编码
     * @return PARENT_CODE 父级字典编码
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * 父级字典编码
     * @param parentCode 父级字典编码
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    /**
     * 父级字典含义
     * @return PARENT_DES 父级字典含义
     */
    public String getParentDes() {
        return parentDes;
    }

    /**
     * 父级字典含义
     * @param parentDes 父级字典含义
     */
    public void setParentDes(String parentDes) {
        this.parentDes = parentDes == null ? null : parentDes.trim();
    }

    /**
     * 0正常,1已废弃,默认为0
     * @return IS_ABANDON 0正常,1已废弃,默认为0
     */
    public Integer getIsAbandon() {
        return isAbandon;
    }

    /**
     * 0正常,1已废弃,默认为0
     * @param isAbandon 0正常,1已废弃,默认为0
     */
    public void setIsAbandon(Integer isAbandon) {
        this.isAbandon = isAbandon;
    }
}