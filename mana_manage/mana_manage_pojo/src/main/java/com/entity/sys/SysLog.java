package com.entity.sys;

import java.util.Date;

/**
 * 描述:sys_log表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2018-01-15
 */
public class SysLog {
    /**
     * 主键
     */
    private String id;

    /**
     * 操作人姓名
     */
    private String operatorName;

    private String deceSigns;

    /**
     * 操作人员编号
     */
    private String operatorCode;

    /**
     * 操作单位编码
     */
    private String operatoUnitCode;

    /**
     * 操作单位名称
     */
    private String operatoUnitName;

    /**
     * 操作时间
     */
    private Date operatoTime;

    /**
     * 操作类型 参考字典“系统操作日志类型”
     */
    private Integer operatoType;

    /**
     * 操作IP
     */
    private String operatoIp;

    /**
     * 操作MAC地址
     */
    private String operatoMac;

    /**
     * 系统创建时间
     */
    private Date creatTime;

    /**
     * 备注
     */
    private String remark;
    
    /**
     * list查询开始位置
     */
    private Integer start;
    
    public String getDeceSigns() {
		return deceSigns;
	}

	public void setDeceSigns(String deceSigns) {
		this.deceSigns = deceSigns;
	}

	/**
     * list查询长度
     */
    private Integer length;

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
     * 操作人姓名
     * @return OPERATOR_NAME 操作人姓名
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 操作人姓名
     * @param operatorName 操作人姓名
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    /**
     * 操作人员编号
     * @return OPERATOR_CODE 操作人员编号
     */
    public String getOperatorCode() {
        return operatorCode;
    }

    /**
     * 操作人员编号
     * @param operatorCode 操作人员编号
     */
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode == null ? null : operatorCode.trim();
    }

    /**
     * 操作单位编码
     * @return OPERATO_UNIT_CODE 操作单位编码
     */
    public String getOperatoUnitCode() {
        return operatoUnitCode;
    }

    /**
     * 操作单位编码
     * @param operatoUnitCode 操作单位编码
     */
    public void setOperatoUnitCode(String operatoUnitCode) {
        this.operatoUnitCode = operatoUnitCode == null ? null : operatoUnitCode.trim();
    }

    /**
     * 操作单位名称
     * @return OPERATO_UNIT_NAME 操作单位名称
     */
    public String getOperatoUnitName() {
        return operatoUnitName;
    }

    /**
     * 操作单位名称
     * @param operatoUnitName 操作单位名称
     */
    public void setOperatoUnitName(String operatoUnitName) {
        this.operatoUnitName = operatoUnitName == null ? null : operatoUnitName.trim();
    }

    /**
     * 操作时间
     * @return OPERATO_TIME 操作时间
     */
    public Date getOperatoTime() {
        return operatoTime;
    }

    /**
     * 操作时间
     * @param operatoTime 操作时间
     */
    public void setOperatoTime(Date operatoTime) {
        this.operatoTime = operatoTime;
    }

    /**
     * 操作类型 参考字典“系统操作日志类型”
     * @return OPERATO_TYPE 操作类型 参考字典“系统操作日志类型”
     */
    public Integer getOperatoType() {
        return operatoType;
    }

    /**
     * 操作类型 参考字典“系统操作日志类型”
     * @param operatoType 操作类型 参考字典“系统操作日志类型”
     */
    public void setOperatoType(Integer operatoType) {
        this.operatoType = operatoType;
    }

    /**
     * 操作IP
     * @return OPERATO_IP 操作IP
     */
    public String getOperatoIp() {
        return operatoIp;
    }

    /**
     * 操作IP
     * @param operatoIp 操作IP
     */
    public void setOperatoIp(String operatoIp) {
        this.operatoIp = operatoIp == null ? null : operatoIp.trim();
    }

    /**
     * 操作MAC地址
     * @return OPERATO_MAC 操作MAC地址
     */
    public String getOperatoMac() {
        return operatoMac;
    }

    /**
     * 操作MAC地址
     * @param operatoMac 操作MAC地址
     */
    public void setOperatoMac(String operatoMac) {
        this.operatoMac = operatoMac == null ? null : operatoMac.trim();
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
     * 备注
     * @return REMARK 备注
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

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
    
    
}