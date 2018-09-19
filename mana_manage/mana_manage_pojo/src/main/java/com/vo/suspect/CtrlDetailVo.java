package com.vo.suspect;

import com.entity.suspect.EtCtrl;

/**
 * Created by Auri on 2018/1/19.
 * Desc: 用于 “布控”详情展示的模型类
 */
public class CtrlDetailVo extends EtCtrl {
    /**
     * 人员性别展示字符
     */
    private String bCtrlGenderStr;

    public String getbCtrlGenderStr() {
        return bCtrlGenderStr;
    }

    public void setbCtrlGenderStr(String bCtrlGenderStr) {
        this.bCtrlGenderStr = bCtrlGenderStr;
    }
}
