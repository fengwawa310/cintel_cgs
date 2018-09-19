package com.entity.task;

/**
 * 描述:ep_hotel表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-03-15
 */
public class EpHotel {
    /**
     * 主键
     */
    private String hnohotel;

    /**
     * 酒店名称
     */
    private String hname;

    /**
     * 法人
     */
    private String legalperson;

    /**
     * 注册资金
     */
    private String principal;

    /**
     * 
     */
    private String socialduty;

    /**
     * 酒店地址
     */
    private String haddress;

    /**
     * 电话
     */
    private String telphone;

    /**
     * 
     */
    private String safetel;

    /**
     * 房间数
     */
    private Integer roomnum;

    /**
     * 床位数
     */
    private Integer bednum;

    /**
     * 星级
     */
    private String stars;

    /**
     * 等级
     */
    private String grade;

    /**
     * 国家
     */
    private String state;

    /**
     * 
     */
    private String statedate;

    /**
     * 酒店驻地
     */
    private String station;

    /**
     * 验证码
     */
    private String validatecode;

    /**
     * 
     */
    private String linkdate;

    /**
     * 
     */
    private String num;

    /**
     * 
     */
    private String inputtime;

    /**
     * 
     */
    private String flag;

    /**
     * 
     */
    private String thirdpart;

    /**
     * 类型
     */
    private String transtype;

    /**
     * 
     */
    private String hotelver;

    /**
     * 酒店类型
     */
    private String hoteltype;

    /**
     * 
     */
    private String fwbm;

    /**
     * 
     */
    private String pscode;

    /**
     * 
     */
    private String qtlx;

    /**
     * 
     */
    private String csfs;

    /**
     * 
     */
    private String jyxz;

    /**
     * 
     */
    private String sjd;

    /**
     * 
     */
    private String swd;

    /**
     * 
     */
    private String sthzh;

    /**
     * 
     */
    private String sgszzh;

    /**
     * 
     */
    private String psorgan;

    /**
     * 
     */
    private String sqjws;

    /**
     * 
     */
    private String hotelkind;

    /**
     * 备注，评论
     */
    private String remark;

    /**
     * 
     */
    private String floors;

    /**
     * 
     */
    private String dbencrypt;

    /**
     * 
     */
    private String zyRksj;

    /**
     * 
     */
    private Integer redflag;

    /**
     * 
     */
    private String id;

    /**
     * 主键
     * @return HNOHOTEL 主键
     */
    public String getHnohotel() {
        return hnohotel;
    }

    /**
     * 主键
     * @param hnohotel 主键
     */
    public void setHnohotel(String hnohotel) {
        this.hnohotel = hnohotel == null ? null : hnohotel.trim();
    }

    /**
     * 酒店名称
     * @return HNAME 酒店名称
     */
    public String getHname() {
        return hname;
    }

    /**
     * 酒店名称
     * @param hname 酒店名称
     */
    public void setHname(String hname) {
        this.hname = hname == null ? null : hname.trim();
    }

    /**
     * 法人
     * @return LEGALPERSON 法人
     */
    public String getLegalperson() {
        return legalperson;
    }

    /**
     * 法人
     * @param legalperson 法人
     */
    public void setLegalperson(String legalperson) {
        this.legalperson = legalperson == null ? null : legalperson.trim();
    }

    /**
     * 注册资金
     * @return PRINCIPAL 注册资金
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * 注册资金
     * @param principal 注册资金
     */
    public void setPrincipal(String principal) {
        this.principal = principal == null ? null : principal.trim();
    }

    /**
     * 
     * @return SOCIALDUTY 
     */
    public String getSocialduty() {
        return socialduty;
    }

    /**
     * 
     * @param socialduty 
     */
    public void setSocialduty(String socialduty) {
        this.socialduty = socialduty == null ? null : socialduty.trim();
    }

    /**
     * 酒店地址
     * @return HADDRESS 酒店地址
     */
    public String getHaddress() {
        return haddress;
    }

    /**
     * 酒店地址
     * @param haddress 酒店地址
     */
    public void setHaddress(String haddress) {
        this.haddress = haddress == null ? null : haddress.trim();
    }

    /**
     * 电话
     * @return TELPHONE 电话
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * 电话
     * @param telphone 电话
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    /**
     * 
     * @return SAFETEL 
     */
    public String getSafetel() {
        return safetel;
    }

    /**
     * 
     * @param safetel 
     */
    public void setSafetel(String safetel) {
        this.safetel = safetel == null ? null : safetel.trim();
    }

    /**
     * 房间数
     * @return ROOMNUM 房间数
     */
    public Integer getRoomnum() {
        return roomnum;
    }

    /**
     * 房间数
     * @param roomnum 房间数
     */
    public void setRoomnum(Integer roomnum) {
        this.roomnum = roomnum;
    }

    /**
     * 床位数
     * @return BEDNUM 床位数
     */
    public Integer getBednum() {
        return bednum;
    }

    /**
     * 床位数
     * @param bednum 床位数
     */
    public void setBednum(Integer bednum) {
        this.bednum = bednum;
    }

    /**
     * 星级
     * @return STARS 星级
     */
    public String getStars() {
        return stars;
    }

    /**
     * 星级
     * @param stars 星级
     */
    public void setStars(String stars) {
        this.stars = stars == null ? null : stars.trim();
    }

    /**
     * 等级
     * @return GRADE 等级
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 等级
     * @param grade 等级
     */
    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    /**
     * 国家
     * @return STATE 国家
     */
    public String getState() {
        return state;
    }

    /**
     * 国家
     * @param state 国家
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 
     * @return STATEDATE 
     */
    public String getStatedate() {
        return statedate;
    }

    /**
     * 
     * @param statedate 
     */
    public void setStatedate(String statedate) {
        this.statedate = statedate == null ? null : statedate.trim();
    }

    /**
     * 酒店驻地
     * @return STATION 酒店驻地
     */
    public String getStation() {
        return station;
    }

    /**
     * 酒店驻地
     * @param station 酒店驻地
     */
    public void setStation(String station) {
        this.station = station == null ? null : station.trim();
    }

    /**
     * 验证码
     * @return VALIDATECODE 验证码
     */
    public String getValidatecode() {
        return validatecode;
    }

    /**
     * 验证码
     * @param validatecode 验证码
     */
    public void setValidatecode(String validatecode) {
        this.validatecode = validatecode == null ? null : validatecode.trim();
    }

    /**
     * 
     * @return LINKDATE 
     */
    public String getLinkdate() {
        return linkdate;
    }

    /**
     * 
     * @param linkdate 
     */
    public void setLinkdate(String linkdate) {
        this.linkdate = linkdate == null ? null : linkdate.trim();
    }

    /**
     * 
     * @return NUM 
     */
    public String getNum() {
        return num;
    }

    /**
     * 
     * @param num 
     */
    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    /**
     * 
     * @return INPUTTIME 
     */
    public String getInputtime() {
        return inputtime;
    }

    /**
     * 
     * @param inputtime 
     */
    public void setInputtime(String inputtime) {
        this.inputtime = inputtime == null ? null : inputtime.trim();
    }

    /**
     * 
     * @return FLAG 
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 
     * @param flag 
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    /**
     * 
     * @return THIRDPART 
     */
    public String getThirdpart() {
        return thirdpart;
    }

    /**
     * 
     * @param thirdpart 
     */
    public void setThirdpart(String thirdpart) {
        this.thirdpart = thirdpart == null ? null : thirdpart.trim();
    }

    /**
     * 类型
     * @return TRANSTYPE 类型
     */
    public String getTranstype() {
        return transtype;
    }

    /**
     * 类型
     * @param transtype 类型
     */
    public void setTranstype(String transtype) {
        this.transtype = transtype == null ? null : transtype.trim();
    }

    /**
     * 
     * @return HOTELVER 
     */
    public String getHotelver() {
        return hotelver;
    }

    /**
     * 
     * @param hotelver 
     */
    public void setHotelver(String hotelver) {
        this.hotelver = hotelver == null ? null : hotelver.trim();
    }

    /**
     * 酒店类型
     * @return HOTELTYPE 酒店类型
     */
    public String getHoteltype() {
        return hoteltype;
    }

    /**
     * 酒店类型
     * @param hoteltype 酒店类型
     */
    public void setHoteltype(String hoteltype) {
        this.hoteltype = hoteltype == null ? null : hoteltype.trim();
    }

    /**
     * 
     * @return FWBM 
     */
    public String getFwbm() {
        return fwbm;
    }

    /**
     * 
     * @param fwbm 
     */
    public void setFwbm(String fwbm) {
        this.fwbm = fwbm == null ? null : fwbm.trim();
    }

    /**
     * 
     * @return PSCODE 
     */
    public String getPscode() {
        return pscode;
    }

    /**
     * 
     * @param pscode 
     */
    public void setPscode(String pscode) {
        this.pscode = pscode == null ? null : pscode.trim();
    }

    /**
     * 
     * @return QTLX 
     */
    public String getQtlx() {
        return qtlx;
    }

    /**
     * 
     * @param qtlx 
     */
    public void setQtlx(String qtlx) {
        this.qtlx = qtlx == null ? null : qtlx.trim();
    }

    /**
     * 
     * @return CSFS 
     */
    public String getCsfs() {
        return csfs;
    }

    /**
     * 
     * @param csfs 
     */
    public void setCsfs(String csfs) {
        this.csfs = csfs == null ? null : csfs.trim();
    }

    /**
     * 
     * @return JYXZ 
     */
    public String getJyxz() {
        return jyxz;
    }

    /**
     * 
     * @param jyxz 
     */
    public void setJyxz(String jyxz) {
        this.jyxz = jyxz == null ? null : jyxz.trim();
    }

    /**
     * 
     * @return SJD 
     */
    public String getSjd() {
        return sjd;
    }

    /**
     * 
     * @param sjd 
     */
    public void setSjd(String sjd) {
        this.sjd = sjd == null ? null : sjd.trim();
    }

    /**
     * 
     * @return SWD 
     */
    public String getSwd() {
        return swd;
    }

    /**
     * 
     * @param swd 
     */
    public void setSwd(String swd) {
        this.swd = swd == null ? null : swd.trim();
    }

    /**
     * 
     * @return STHZH 
     */
    public String getSthzh() {
        return sthzh;
    }

    /**
     * 
     * @param sthzh 
     */
    public void setSthzh(String sthzh) {
        this.sthzh = sthzh == null ? null : sthzh.trim();
    }

    /**
     * 
     * @return SGSZZH 
     */
    public String getSgszzh() {
        return sgszzh;
    }

    /**
     * 
     * @param sgszzh 
     */
    public void setSgszzh(String sgszzh) {
        this.sgszzh = sgszzh == null ? null : sgszzh.trim();
    }

    /**
     * 
     * @return PSORGAN 
     */
    public String getPsorgan() {
        return psorgan;
    }

    /**
     * 
     * @param psorgan 
     */
    public void setPsorgan(String psorgan) {
        this.psorgan = psorgan == null ? null : psorgan.trim();
    }

    /**
     * 
     * @return SQJWS 
     */
    public String getSqjws() {
        return sqjws;
    }

    /**
     * 
     * @param sqjws 
     */
    public void setSqjws(String sqjws) {
        this.sqjws = sqjws == null ? null : sqjws.trim();
    }

    /**
     * 
     * @return HOTELKIND 
     */
    public String getHotelkind() {
        return hotelkind;
    }

    /**
     * 
     * @param hotelkind 
     */
    public void setHotelkind(String hotelkind) {
        this.hotelkind = hotelkind == null ? null : hotelkind.trim();
    }

    /**
     * 备注，评论
     * @return REMARK 备注，评论
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注，评论
     * @param remark 备注，评论
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 
     * @return FLOORS 
     */
    public String getFloors() {
        return floors;
    }

    /**
     * 
     * @param floors 
     */
    public void setFloors(String floors) {
        this.floors = floors == null ? null : floors.trim();
    }

    /**
     * 
     * @return DBENCRYPT 
     */
    public String getDbencrypt() {
        return dbencrypt;
    }

    /**
     * 
     * @param dbencrypt 
     */
    public void setDbencrypt(String dbencrypt) {
        this.dbencrypt = dbencrypt == null ? null : dbencrypt.trim();
    }

    /**
     * 
     * @return ZY_RKSJ 
     */
    public String getZyRksj() {
        return zyRksj;
    }

    /**
     * 
     * @param zyRksj 
     */
    public void setZyRksj(String zyRksj) {
        this.zyRksj = zyRksj == null ? null : zyRksj.trim();
    }

    /**
     * 
     * @return REDFLAG 
     */
    public Integer getRedflag() {
        return redflag;
    }

    /**
     * 
     * @param redflag 
     */
    public void setRedflag(Integer redflag) {
        this.redflag = redflag;
    }

    /**
     * 
     * @return id 
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
}