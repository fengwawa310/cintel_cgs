package com.request.sys;

/**
 * 系统用户请求类
 *
 * @author admin
 * @create 2018-01-19 16:30
 **/
public class SysUserReq {
    /**
     *
     */
    private String id;

    /**
     * 登录名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户名字
     */
    private String name;

    /**
     * 电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 所处单位级别(市局、分局、派出所)
     */
    private String level;

    /**
     * 所属省
     */
    private String province;

    /**
     * 所属市
     */
    private String city;

    /**
     * 所属区
     */
    private String area;

    /**
     * 所属派出所
     */
    private String policeStation;

    /**
     * 所属省名
     */
    private String provinceName;

    /**
     * 所属市名
     */
    private String cityName;

    /**
     * 所属区名
     */
    private String areaName;

    /**
     * 所属派出所名
     */
    private String policeStationName;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    /*设备创建时间*/
    private String createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    /*设备创建时间*/
    private String updateTime;

    /**
     * 标记
     */
    private String remark;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 登录标识
     */
    private String loginFlag;

    /**
     * 职称
     */
    private String position;

    /**
     * 枚举值，是否管理员：1、是；2、否
     */
    private String isAdmin;

    private String logonIp; //保存用户登录ip，
    /*设备创建时间*/
    private String logonTime; //保存用户登录时间，用来判断登录用户先后顺序

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getPoliceStationName() {
        return policeStationName;
    }

    public void setPoliceStationName(String policeStationName) {
        this.policeStationName = policeStationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getLogonIp() {
        return logonIp;
    }

    public void setLogonIp(String logonIp) {
        this.logonIp = logonIp;
    }

    public String getLogonTime() {
        return logonTime;
    }

    public void setLogonTime(String logonTime) {
        this.logonTime = logonTime;
    }
}
