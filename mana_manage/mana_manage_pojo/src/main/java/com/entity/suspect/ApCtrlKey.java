package com.entity.suspect;

import com.entity.BaseEntity;

import java.util.Date;

/**
 * 描述:ap_ctrl_key表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-01-05
 */
public class ApCtrlKey {
    /**
     * 主键 布控任务编号 与布控任务一一对应 
     */
    private String ctrlId;

    /**
     * 重点人员身份证
     */
    private String idcardNum;

    /**
     * 系统创建时间
     */
    private Date creatTime;

    /**
     * 主键 布控任务编号 与布控任务一一对应 
     * @return CTRL_ID 主键 布控任务编号 与布控任务一一对应 
     */
    public String getCtrlId() {
        return ctrlId;
    }

    /**
     * 主键 布控任务编号 与布控任务一一对应 
     * @param ctrlId 主键 布控任务编号 与布控任务一一对应 
     */
    public void setCtrlId(String ctrlId) {
        this.ctrlId = ctrlId == null ? null : ctrlId.trim();
    }

    /**
     * 重点人员身份证
     * @return IDCARD_NUM 重点人员身份证
     */
    public String getIdcardNum() {
        return idcardNum;
    }

    /**
     * 重点人员身份证
     * @param idcardNum 重点人员身份证
     */
    public void setIdcardNum(String idcardNum) {
        this.idcardNum = idcardNum == null ? null : idcardNum.trim();
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
}