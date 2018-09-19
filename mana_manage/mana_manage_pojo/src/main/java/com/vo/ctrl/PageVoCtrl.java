package com.vo.ctrl;


import java.util.Date;

import com.common.utils.PageVO;

/**
 * Created by Auri on 2018/1/6.
 * Desc: 布控任务 分页多条件查询参数封装类
 */
public class PageVoCtrl extends PageVO {

    /**
     * 布控人员编号（重点人员编号）
     */
    private String suspectNo;

    /**
     * 布控人员（重点人员）身份证编号
     */
    private String suspectIDCardNo;

    /**
     * 布控人员（重点人员）姓名
     */
    private String suspectName;
    
    /**
     * 申请人编码
     */
    private String applicantCode;

    /**
     * 管控单位 编号
     */
    private String manaUnitCode;
    
    
    /**
     * 管控单位 名称
     */
    private String applyUnitName;
    

    public String getApplyUnitName() {
		return applyUnitName;
	}

	public void setApplyUnitName(String applyUnitName) {
		this.applyUnitName = applyUnitName;
	}

	/**
     * 布控生成时间上限
     */
    private Date ctrlCreateUpperLimitTime;

    private String upperLimitTimeStr;

    /**
     * 布控生成时间下限
     */
    private Date ctrlCreateLowerLimiteTime;

    private String lowerLimitTimeStr;

    /**
     * 数据分域 域编号字符串
     * 例如：广东省=44；深证市=4403；罗湖区=440303
     */
    private String regionCodeStr;
    
    public String getApplicantCode() {
		return applicantCode;
	}

	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}

	public PageVoCtrl() {
    }

    public String getSuspectNo() {
        return suspectNo;
    }

    public void setSuspectNo(String suspectNo) {
        this.suspectNo = suspectNo;
    }

    public String getSuspectIDCardNo() {
        return suspectIDCardNo;
    }

    public void setSuspectIDCardNo(String suspectIDCardNo) {
        this.suspectIDCardNo = suspectIDCardNo;
    }

    public String getSuspectName() {
        return suspectName;
    }

    public void setSuspectName(String suspectName) {
        this.suspectName = suspectName;
    }

    public String getManaUnitCode() {
        return manaUnitCode;
    }

    public void setManaUnitCode(String manaUnitCode) {
        this.manaUnitCode = manaUnitCode;
    }

    public Date getCtrlCreateUpperLimitTime() {
        return ctrlCreateUpperLimitTime;
    }

    public void setCtrlCreateUpperLimitTime(Date ctrlCreateUpperLimitTime) {
        this.ctrlCreateUpperLimitTime = ctrlCreateUpperLimitTime;
    }

    public String getUpperLimitTimeStr() {
        return upperLimitTimeStr;
    }

    public void setUpperLimitTimeStr(String upperLimitTimeStr) {
        this.upperLimitTimeStr = upperLimitTimeStr;
    }

    public Date getCtrlCreateLowerLimiteTime() {
        return ctrlCreateLowerLimiteTime;
    }

    public void setCtrlCreateLowerLimiteTime(Date ctrlCreateLowerLimiteTime) {
        this.ctrlCreateLowerLimiteTime = ctrlCreateLowerLimiteTime;
    }

    public String getLowerLimitTimeStr() {
        return lowerLimitTimeStr;
    }

    public void setLowerLimitTimeStr(String lowerLimitTimeStr) {
        this.lowerLimitTimeStr = lowerLimitTimeStr;
    }

    public String getRegionCodeStr() {
        return regionCodeStr;
    }

    public void setRegionCodeStr(String regionCodeStr) {
        this.regionCodeStr = regionCodeStr;
    }
}
