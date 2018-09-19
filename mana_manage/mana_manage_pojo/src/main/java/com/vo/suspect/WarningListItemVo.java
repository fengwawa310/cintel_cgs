package com.vo.suspect;

import com.entity.suspect.EtWarning;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Auri on 2018/1/10.
 * Desc: 预警列表项 展示模型
 */
public class WarningListItemVo extends EtWarning implements Serializable {

    /**
     * 管控单位编码
     */
    private String manaUnitCode;

    /**
     * 管控单位名称
     */
    private String manaUnitName;

    /**
     * 管控人编号
     */
    private String manaPCode;

    /**
     * 管控人姓名
     */
    private String manaPName;

    /**
     * 布控人姓名
     */
    private String bCtrlPName;

    /**
     * 布控人性别
     */
    private Integer bCtrlGender;

    /**
     * 布控人性别字符
     */
    private String bCtrlGenderStr;

    /**
     * 布控人电话号码
     */
    private String bCtrlPhone;

    private Date ctrlTime;

    private String ctrlTimeStr;

    private String warningTimeStr;

    /**
     * 预警类别字典编码
     */
    private Integer warningClass;

    /**
     * 预警类别名称
     */
    private String warningClassStr;

    @Override
    public String getManaUnitCode() {
        return manaUnitCode;
    }

    @Override
    public void setManaUnitCode(String manaUnitCode) {
        this.manaUnitCode = manaUnitCode;
    }

    @Override
    public String getManaUnitName() {
        return manaUnitName;
    }

    @Override
    public void setManaUnitName(String manaUnitName) {
        this.manaUnitName = manaUnitName;
    }

    @Override
    public String getManaPCode() {
        return manaPCode;
    }

    @Override
    public void setManaPCode(String manaPCode) {
        this.manaPCode = manaPCode;
    }

    @Override
    public String getManaPName() {
        return manaPName;
    }

    @Override
    public void setManaPName(String manaPName) {
        this.manaPName = manaPName;
    }

    public String getbCtrlPName() {
        return bCtrlPName;
    }

    public void setbCtrlPName(String bCtrlPName) {
        this.bCtrlPName = bCtrlPName;
    }

    public Integer getbCtrlGender() {
        return bCtrlGender;
    }

    public void setbCtrlGender(Integer bCtrlGender) {
        this.bCtrlGender = bCtrlGender;
    }

    public String getbCtrlPhone() {
        return bCtrlPhone;
    }

    public void setbCtrlPhone(String bCtrlPhone) {
        this.bCtrlPhone = bCtrlPhone;
    }

    public Date getCtrlTime() {
        return ctrlTime;
    }

    public void setCtrlTime(Date ctrlTime) {
        this.ctrlTime = ctrlTime;
    }

    public String getCtrlTimeStr() {
        return ctrlTimeStr;
    }

    public void setCtrlTimeStr(String ctrlTimeStr) {
        this.ctrlTimeStr = ctrlTimeStr;
    }

    public String getWarningTimeStr() {
        return warningTimeStr;
    }

    public void setWarningTimeStr(String warningTimeStr) {
        this.warningTimeStr = warningTimeStr;
    }

    public String getWarningClassStr() {
        return warningClassStr;
    }

    public void setWarningClassStr(String warningClassStr) {
        this.warningClassStr = warningClassStr;
    }

    @Override
    public Integer getWarningClass() {
        return warningClass;
    }

    @Override
    public void setWarningClass(Integer warningClass) {
        this.warningClass = warningClass;
    }

    public String getbCtrlGenderStr() {
        return bCtrlGenderStr;
    }

    public void setbCtrlGenderStr(String bCtrlGenderStr) {
        this.bCtrlGenderStr = bCtrlGenderStr;
    }
}
