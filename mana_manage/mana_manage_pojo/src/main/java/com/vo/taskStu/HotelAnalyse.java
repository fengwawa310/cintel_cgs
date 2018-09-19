package com.vo.taskStu;

import java.util.List;

/**
 * @Author: sky
 * @Description:
 * @Date: Create in 下午7:42 2018/4/2
 */
public class HotelAnalyse {


    /**
     * TAG : ["同住人","同酒店"]
     * HOTELINFO : {"NOHOTEL":"旅馆ID","HNAME":"名称","STARS":"星级","HADDRESS":"地址","SJD":"经度","SWD":"纬度"}
     * NOROOM : 房间ID
     * CASETAG :同案警标签 1、同案件，2、同警情，3、同案警
     * REMARK :备注
     * PERSONINFO : {"IDCODE":"身份证ID","NAME":"姓名","SEX":"性别","BDATE":"出生年月","ADDRESS":"户籍地"}
     * LTIME : 入住时间
     * ETIME : 离开时间
     * DAYS : 入住天数
     */

    private HOTELINFOBean HOTELINFO;
    private String NOROOM;
    private String CASETAG;
    private String REMARK;
    private PERSONINFOBean PERSONINFO;
    private String LTIME;
    private String ETIME;
    private String DAYS;
    private List<String> TAG;

    public HOTELINFOBean getHOTELINFO() {
        return HOTELINFO;
    }

    public void setHOTELINFO(HOTELINFOBean HOTELINFO) {
        this.HOTELINFO = HOTELINFO;
    }

    public String getNOROOM() {
        return NOROOM;
    }

    public void setNOROOM(String NOROOM) {
        this.NOROOM = NOROOM;
    }

    public PERSONINFOBean getPERSONINFO() {
        return PERSONINFO;
    }

    public void setPERSONINFO(PERSONINFOBean PERSONINFO) {
        this.PERSONINFO = PERSONINFO;
    }

    public String getLTIME() {
        return LTIME;
    }

    public void setLTIME(String LTIME) {
        this.LTIME = LTIME;
    }

    public String getETIME() {
        return ETIME;
    }

    public void setETIME(String ETIME) {
        this.ETIME = ETIME;
    }

    public String getDAYS() {
        return DAYS;
    }

    public void setDAYS(String DAYS) {
        this.DAYS = DAYS;
    }

    public List<String> getTAG() {
        return TAG;
    }

    public void setTAG(List<String> TAG) {
        this.TAG = TAG;
    }

    public String getCASETAG() {
        return CASETAG;
    }

    public void setCASETAG(String CASETAG) {
        this.CASETAG = CASETAG;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public static class HOTELINFOBean {
        /**
         * NOHOTEL : 旅馆ID
         * HNAME : 名称
         * STARS : 星级
         * HADDRESS : 地址
         * SJD : 经度
         * SWD : 纬度
         */

        private String NOHOTEL;
        private String HNAME;
        private String STARS;
        private String HADDRESS;
        private String SJD;
        private String SWD;

        public String getNOHOTEL() {
            return NOHOTEL;
        }

        public void setNOHOTEL(String NOHOTEL) {
            this.NOHOTEL = NOHOTEL;
        }

        public String getHNAME() {
            return HNAME;
        }

        public void setHNAME(String HNAME) {
            this.HNAME = HNAME;
        }

        public String getSTARS() {
            return STARS;
        }

        public void setSTARS(String STARS) {
            this.STARS = STARS;
        }

        public String getHADDRESS() {
            return HADDRESS;
        }

        public void setHADDRESS(String HADDRESS) {
            this.HADDRESS = HADDRESS;
        }

        public String getSJD() {
            return SJD;
        }

        public void setSJD(String SJD) {
            this.SJD = SJD;
        }

        public String getSWD() {
            return SWD;
        }

        public void setSWD(String SWD) {
            this.SWD = SWD;
        }
    }

    public static class PERSONINFOBean {
        /**
         * IDCODE : 身份证ID
         * NAME : 姓名
         * SEX : 性别
         * BDATE : 出生年月
         * ADDRESS : 户籍地
         */

        private String IDCODE;
        private String NAME;
        private String SEX;
        private String BDATE;
        private String ADDRESS;

        public String getIDCODE() {
            return IDCODE;
        }

        public void setIDCODE(String IDCODE) {
            this.IDCODE = IDCODE;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getSEX() {
            return SEX;
        }

        public void setSEX(String SEX) {
            this.SEX = SEX;
        }

        public String getBDATE() {
            return BDATE;
        }

        public void setBDATE(String BDATE) {
            this.BDATE = BDATE;
        }

        public String getADDRESS() {
            return ADDRESS;
        }

        public void setADDRESS(String ADDRESS) {
            this.ADDRESS = ADDRESS;
        }
    }
}
