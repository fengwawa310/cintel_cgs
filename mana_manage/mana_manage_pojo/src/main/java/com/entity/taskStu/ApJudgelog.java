package com.entity.taskStu;

import com.common.enums.EnumTypeVO;

import java.util.Date;

/**
 * 描述:ap_judgelog表的实体类
 * @version
 * @author:  admin
 * @创建时间: 2018-03-06
 */
public class ApJudgelog {
    /**
     * 
     */
    private String id;

    /**
     * 外键 ET_JUDGEFLOW(主键)
     */
    private String relateNo;

    /**
     * 操作人编码
     */
    private String operatorCode;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 操作单位编码
     */
    private String operUnitCode;

    /**
     * 操作单位名称
     */
    private String operUnitName;

    /**
     * 操作时间
     */
    private Date operTime;

    /**
     * 操作类型 参考字典“案警研判任务流转状态”
     */
    private Integer operType;

    /**
     * 系统创建时间
     */
    private Date creatTime;

    /**
     * 系统修改时间
     */
    private Date modifyTime;

    /**
     * 备注
     */
    private String remark;

    /*操作名称*/
    private  EnumTypeVO operTypeEnum;

    public EnumTypeVO getOperTypeEnum() {
        return operTypeEnum;
    }

    public void setOperTypeEnum(EnumTypeVO operTypeEnum) {
        this.operTypeEnum = operTypeEnum;
    }

    /**
     * 
     * @return ID 
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
     * 外键 ET_JUDGEFLOW(主键)
     * @return RELATE_NO 外键 ET_JUDGEFLOW(主键)
     */
    public String getRelateNo() {
        return relateNo;
    }

    /**
     * 外键 ET_JUDGEFLOW(主键)
     * @param relateNo 外键 ET_JUDGEFLOW(主键)
     */
    public void setRelateNo(String relateNo) {
        this.relateNo = relateNo == null ? null : relateNo.trim();
    }

    /**
     * 操作人编码
     * @return OPERATOR_CODE 操作人编码
     */
    public String getOperatorCode() {
        return operatorCode;
    }

    /**
     * 操作人编码
     * @param operatorCode 操作人编码
     */
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode == null ? null : operatorCode.trim();
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
     * 操作单位编码
     * @return OPER_UNIT_CODE 操作单位编码
     */
    public String getOperUnitCode() {
        return operUnitCode;
    }

    /**
     * 操作单位编码
     * @param operUnitCode 操作单位编码
     */
    public void setOperUnitCode(String operUnitCode) {
        this.operUnitCode = operUnitCode == null ? null : operUnitCode.trim();
    }

    /**
     * 操作单位名称
     * @return OPER_UNIT_NAME 操作单位名称
     */
    public String getOperUnitName() {
        return operUnitName;
    }

    /**
     * 操作单位名称
     * @param operUnitName 操作单位名称
     */
    public void setOperUnitName(String operUnitName) {
        this.operUnitName = operUnitName == null ? null : operUnitName.trim();
    }

    /**
     * 操作时间
     * @return OPER_TIME 操作时间
     */
    public Date getOperTime() {
        return operTime;
    }

    /**
     * 操作时间
     * @param operTime 操作时间
     */
    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    /**
     * 操作类型 参考字典“案警研判任务流转状态”
     * @return OPER_TYPE 操作类型 参考字典“案警研判任务流转状态”
     */
    public Integer getOperType() {
        return operType;
    }

    /**
     * 操作类型 参考字典“案警研判任务流转状态”
     * @param operType 操作类型 参考字典“案警研判任务流转状态”
     */
    public void setOperType(Integer operType) {
        this.operType = operType;
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
}