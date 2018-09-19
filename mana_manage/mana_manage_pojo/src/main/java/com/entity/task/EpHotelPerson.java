package com.entity.task;

import java.util.Date;
import java.util.List;

/**
 * 描述:ep_hotel_person表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-03-15
 */
public class EpHotelPerson {

    /**
     * 新添加 标签集合
     */
    private List<String> tags;

    /**
     * 编号
     */
    private String ccode;

    /**
     * 
     */
    private String gcode;

    /**
     * 姓名
     */
    private String name;

    /**
     * 
     */
    private String namepy;

    /**
     * 性别
     */
    private String sex;

    /**
     * 民族
     */
    private String nation;

    /**
     * 出生日期
     */
    private String bdate;

    /**
     * 证件类型
     */
    private String idtype;

    /**
     * 身份证号
     */
    private String idcode;

    /**
     * 行政区划
     */
    private String xzqh;

    /**
     * 常住地址
     */
    private String address;

    /**
     * 入住旅馆
     */
    private String nohotel;

    /**
     * 入住房号
     */
    private String noroom;

    /**
     * 入住时间
     */
    private Date ltime;

    /**
     * 入住当班人员
     */
    private String lwaiter;

    /**
     * 退房时间
     */
    private String etime;

    /**
     * 离开当班人员
     */
    private String ewaiter;

    /**
     * 
     */
    private String cardtype;

    /**
     * 
     */
    private String cardno;

    /**
     * 
     */
    private String stime;

    /**
     * 
     */
    private String rtime;

    /**
     * 
     */
    private String createtime;

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
    private Date rksj;

    /**
     * 
     */
    private String sspcs;

    /**
     * 
     */
    private String revstatus;

    /**
     * 
     */
    private String id;

    /**
     * PICTURELEN字段
     */
    private Float pic;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * 编号
     * @return CCODE 编号
     */
    public String getCcode() {
        return ccode;
    }

    /**
     * 编号
     * @param ccode 编号
     */
    public void setCcode(String ccode) {
        this.ccode = ccode == null ? null : ccode.trim();
    }

    /**
     * 
     * @return GCODE 
     */
    public String getGcode() {
        return gcode;
    }

    /**
     * 
     * @param gcode 
     */
    public void setGcode(String gcode) {
        this.gcode = gcode == null ? null : gcode.trim();
    }

    /**
     * 姓名
     * @return NAME 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 
     * @return NAMEPY 
     */
    public String getNamepy() {
        return namepy;
    }

    /**
     * 
     * @param namepy 
     */
    public void setNamepy(String namepy) {
        this.namepy = namepy == null ? null : namepy.trim();
    }

    /**
     * 性别
     * @return SEX 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 民族
     * @return NATION 民族
     */
    public String getNation() {
        return nation;
    }

    /**
     * 民族
     * @param nation 民族
     */
    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    /**
     * 出生日期
     * @return BDATE 出生日期
     */
    public String getBdate() {
        return bdate;
    }

    /**
     * 出生日期
     * @param bdate 出生日期
     */
    public void setBdate(String bdate) {
        this.bdate = bdate == null ? null : bdate.trim();
    }

    /**
     * 证件类型
     * @return IDTYPE 证件类型
     */
    public String getIdtype() {
        return idtype;
    }

    /**
     * 证件类型
     * @param idtype 证件类型
     */
    public void setIdtype(String idtype) {
        this.idtype = idtype == null ? null : idtype.trim();
    }

    /**
     * 身份证号
     * @return IDCODE 身份证号
     */
    public String getIdcode() {
        return idcode;
    }

    /**
     * 身份证号
     * @param idcode 身份证号
     */
    public void setIdcode(String idcode) {
        this.idcode = idcode == null ? null : idcode.trim();
    }

    /**
     * 行政区划
     * @return XZQH 行政区划
     */
    public String getXzqh() {
        return xzqh;
    }

    /**
     * 行政区划
     * @param xzqh 行政区划
     */
    public void setXzqh(String xzqh) {
        this.xzqh = xzqh == null ? null : xzqh.trim();
    }

    /**
     * 常住地址
     * @return ADDRESS 常住地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 常住地址
     * @param address 常住地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 入住旅馆
     * @return NOHOTEL 入住旅馆
     */
    public String getNohotel() {
        return nohotel;
    }

    /**
     * 入住旅馆
     * @param nohotel 入住旅馆
     */
    public void setNohotel(String nohotel) {
        this.nohotel = nohotel == null ? null : nohotel.trim();
    }

    /**
     * 入住房号
     * @return NOROOM 入住房号
     */
    public String getNoroom() {
        return noroom;
    }

    /**
     * 入住房号
     * @param noroom 入住房号
     */
    public void setNoroom(String noroom) {
        this.noroom = noroom == null ? null : noroom.trim();
    }

    /**
     * 入住时间
     * @return LTIME 入住时间
     */
    public Date getLtime() {
        return ltime;
    }

    /**
     * 入住时间
     * @param ltime 入住时间
     */
    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }

    /**
     * 入住当班人员
     * @return LWAITER 入住当班人员
     */
    public String getLwaiter() {
        return lwaiter;
    }

    /**
     * 入住当班人员
     * @param lwaiter 入住当班人员
     */
    public void setLwaiter(String lwaiter) {
        this.lwaiter = lwaiter == null ? null : lwaiter.trim();
    }

    /**
     * 退房时间
     * @return ETIME 退房时间
     */
    public String getEtime() {
        return etime;
    }

    /**
     * 退房时间
     * @param etime 退房时间
     */
    public void setEtime(String etime) {
        this.etime = etime == null ? null : etime.trim();
    }

    /**
     * 离开当班人员
     * @return EWAITER 离开当班人员
     */
    public String getEwaiter() {
        return ewaiter;
    }

    /**
     * 离开当班人员
     * @param ewaiter 离开当班人员
     */
    public void setEwaiter(String ewaiter) {
        this.ewaiter = ewaiter == null ? null : ewaiter.trim();
    }

    /**
     * 
     * @return CARDTYPE 
     */
    public String getCardtype() {
        return cardtype;
    }

    /**
     * 
     * @param cardtype 
     */
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype == null ? null : cardtype.trim();
    }

    /**
     * 
     * @return CARDNO 
     */
    public String getCardno() {
        return cardno;
    }

    /**
     * 
     * @param cardno 
     */
    public void setCardno(String cardno) {
        this.cardno = cardno == null ? null : cardno.trim();
    }

    /**
     * 
     * @return STIME 
     */
    public String getStime() {
        return stime;
    }

    /**
     * 
     * @param stime 
     */
    public void setStime(String stime) {
        this.stime = stime == null ? null : stime.trim();
    }

    /**
     * 
     * @return RTIME 
     */
    public String getRtime() {
        return rtime;
    }

    /**
     * 
     * @param rtime 
     */
    public void setRtime(String rtime) {
        this.rtime = rtime == null ? null : rtime.trim();
    }

    /**
     * 
     * @return CREATETIME 
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * 
     * @param createtime 
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
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
     * @return RKSJ 
     */
    public Date getRksj() {
        return rksj;
    }

    /**
     * 
     * @param rksj 
     */
    public void setRksj(Date rksj) {
        this.rksj = rksj;
    }

    /**
     * 
     * @return SSPCS 
     */
    public String getSspcs() {
        return sspcs;
    }

    /**
     * 
     * @param sspcs 
     */
    public void setSspcs(String sspcs) {
        this.sspcs = sspcs == null ? null : sspcs.trim();
    }

    /**
     * 
     * @return REVSTATUS 
     */
    public String getRevstatus() {
        return revstatus;
    }

    /**
     * 
     * @param revstatus 
     */
    public void setRevstatus(String revstatus) {
        this.revstatus = revstatus == null ? null : revstatus.trim();
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
     * PICTURELEN字段
     * @return PIC PICTURELEN字段
     */
    public Float getPic() {
        return pic;
    }

    /**
     * PICTURELEN字段
     * @param pic PICTURELEN字段
     */
    public void setPic(Float pic) {
        this.pic = pic;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof EpHotelPerson)) {
            return false;
        }
        EpHotelPerson epHotelPerson = (EpHotelPerson) o;
        return epHotelPerson.ccode.equals(this.ccode) ;
    }
    //Idea from effective Java : Item 9
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + ccode.hashCode();
        return result;
    }

    }