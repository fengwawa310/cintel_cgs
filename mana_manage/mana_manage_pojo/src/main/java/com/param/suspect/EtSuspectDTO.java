package com.param.suspect;

import com.entity.suspect.EtSuspect;

/**
 * et_suspect表参数接收包装类
 *
 * @author admin
 * @create 2018-02-05 15:25
 **/
public class EtSuspectDTO extends EtSuspect {

    /*当前用户id*/
    private String userId;

    /*用户名和身份证号模糊搜索--重点人员对应警官列表显示时用*/
    private String nameOrIdcard;

    /*重点人员列表显示---重点人员搜索用*/
    private String nameOrbyNameOrIdcardNum;



    public EtSuspectDTO() {
    }

    public String getNameOrIdcard() {
        return nameOrIdcard;
    }

    public void setNameOrIdcard(String nameOrIdcard) {
        this.nameOrIdcard = nameOrIdcard;
    }

    public String getNameOrbyNameOrIdcardNum() {
        return nameOrbyNameOrIdcardNum;
    }

    public void setNameOrbyNameOrIdcardNum(String nameOrbyNameOrIdcardNum) {
        this.nameOrbyNameOrIdcardNum = nameOrbyNameOrIdcardNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
