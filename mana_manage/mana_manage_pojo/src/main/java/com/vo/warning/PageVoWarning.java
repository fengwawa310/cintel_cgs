package com.vo.warning;


import java.util.Date;

import com.common.utils.PageVO;

/**
 * Created by Auri on 2018/1/6.
 * Desc: 预警信息 分页多条件查询参数封装类
 */
public class PageVoWarning extends PageVO {
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
     * 管控单位 编号
     */
    private String manaUnitCode;
    
    /**
     * 申请人编码
     */
    private String applicantCode;

    /**
     * 预警生成时间上限
     */
    private Date warningCreateUpperLimitTime;

    private String wCreateUpperLimtTimeStr;

    /**
     * 预警生成时间下限
     */
    private Date warningCreateLowerLimitTime;

    private String wCreateLowerLimtTimeStr;

    /**
     * 是否需要查询预警具体详情
     */
    private Boolean needDetail = false;

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

	public PageVoWarning() {
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

    public Date getWarningCreateUpperLimitTime() {
        return warningCreateUpperLimitTime;
    }

    public void setWarningCreateUpperLimitTime(Date warningCreateUpperLimitTime) {
        this.warningCreateUpperLimitTime = warningCreateUpperLimitTime;
    }

    public String getwCreateUpperLimtTimeStr() {
        return wCreateUpperLimtTimeStr;
    }

    public void setwCreateUpperLimtTimeStr(String wCreateUpperLimtTimeStr) {
        this.wCreateUpperLimtTimeStr = wCreateUpperLimtTimeStr;
    }

    public Date getWarningCreateLowerLimitTime() {
        return warningCreateLowerLimitTime;
    }

    public void setWarningCreateLowerLimitTime(Date warningCreateLowerLimitTime) {
        this.warningCreateLowerLimitTime = warningCreateLowerLimitTime;
    }

    public String getwCreateLowerLimtTimeStr() {
        return wCreateLowerLimtTimeStr;
    }

    public void setwCreateLowerLimtTimeStr(String wCreateLowerLimtTimeStr) {
        this.wCreateLowerLimtTimeStr = wCreateLowerLimtTimeStr;
    }

    public Boolean getNeedDetail() {
        return needDetail;
    }

    public void setNeedDetail(Boolean needDetail) {
        this.needDetail = needDetail;
    }

    public String getRegionCodeStr() {
        return regionCodeStr;
    }

    public void setRegionCodeStr(String regionCodeStr) {
        this.regionCodeStr = regionCodeStr;
    }
}
