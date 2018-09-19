package com.entity.integral;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EtSuspectIntegral {
    /**
     * 主键
     */
    private String id;

    /**
	 *重点人员编号
     */
    private String suspectId;

    /**
     *身份证号码
     */
    private String idcardNum;

    /**
     *姓名
     */
    private String suspectName;
    
    private String deceSigns;

    /**
     *人员类型
     */
    private Integer suspectClass;

    private Integer levelSet;

    /**
     *危害总积分
     */
    private Integer integral;
    
    /**
     *人员预警标识
     */
    private Integer warningSign;

    /**
     *系统创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date creatTime;

    /**
     *系统修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifyTime;

    public Integer getLevelSet() {
		return levelSet;
	}

	public void setLevelSet(Integer levelSet) {
		this.levelSet = levelSet;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSuspectId() {
        return suspectId;
    }

    public void setSuspectId(String suspectId) {
        this.suspectId = suspectId == null ? null : suspectId.trim();
    }

    public String getIdcardNum() {
        return idcardNum;
    }

    public void setIdcardNum(String idcardNum) {
        this.idcardNum = idcardNum == null ? null : idcardNum.trim();
    }

    public String getSuspectName() {
        return suspectName;
    }


    public void setSuspectName(String suspectName) {
        this.suspectName = suspectName == null ? null : suspectName.trim();
    }


    public Integer getSuspectClass() {
        return suspectClass;
    }


    public void setSuspectClass(Integer suspectClass) {
        this.suspectClass = suspectClass;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	public Integer getWarningSign() {
		return warningSign;
	}

	public void setWarningSign(Integer warningSign) {
		this.warningSign = warningSign;
	}

	public String getDeceSigns() {
		return deceSigns;
	}

	public void setDeceSigns(String deceSigns) {
		this.deceSigns = deceSigns;
	}
    
}