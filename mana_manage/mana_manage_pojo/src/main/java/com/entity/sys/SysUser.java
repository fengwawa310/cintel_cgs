package com.entity.sys;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述:sys_user表的实体类
 *
 * @author: admin
 * @创建时间: 2017-12-15
 */
public class SysUser implements Serializable {
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
//    2018-2-2 业务数据删除
//    private String userType;

    /**
     * 用户名字
     */
    private String name;

    private String code;

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
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

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
     * 职务名称 1：领导，2：侦查员 ，3：其他'
     */
    private String jobs;


    /**
     * 枚举值，是否管理员：1、是；2、否
     */
    //    2018-2-2 业务数据删除
//    private Integer isAdmin;

    private String logonIp; //保存用户登录ip，

    private Date logonTime; //保存用户登录时间，用来判断登录用户先后顺序


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 登录名
     *
     * @return user_name 登录名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 登录名
     *
     * @param userName 登录名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 密码
     *
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 用户类型
     * @return user_type 用户类型
     */
   /* public String getUserType() {
        return userType;
    }*/

    /**
     * 用户类型
     * @param userType 用户类型
     */
   /* public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }*/

    /**
     * 用户名字
     *
     * @return name 用户名字
     */
    public String getName() {
        return name;
    }

    /**
     * 用户名字
     *
     * @param name 用户名字
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 电话
     *
     * @return tel 电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 电话
     *
     * @param tel 电话
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * 邮箱
     *
     * @return email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 身份证号
     *
     * @return idcard 身份证号
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 身份证号
     *
     * @param idcard 身份证号
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    /**
     * 所处单位级别(市局、分局、派出所)
     *
     * @return level 所处单位级别(市局、分局、派出所)
     */
    public String getLevel() {
        return level;
    }

    /**
     * 所处单位级别(市局、分局、派出所)
     *
     * @param level 所处单位级别(市局、分局、派出所)
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * 所属省
     *
     * @return province 所属省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 所属省
     *
     * @param province 所属省
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 所属市
     *
     * @return city 所属市
     */
    public String getCity() {
        return city;
    }

    /**
     * 所属市
     *
     * @param city 所属市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 所属区
     *
     * @return area 所属区
     */
    public String getArea() {
        return area;
    }

    /**
     * 所属区
     *
     * @param area 所属区
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * 所属派出所
     *
     * @return police_station 所属派出所
     */
    public String getPoliceStation() {
        return policeStation;
    }

    /**
     * 所属派出所
     *
     * @param policeStation 所属派出所
     */
    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation == null ? null : policeStation.trim();
    }

    /**
     * 详细地址
     *
     * @return address 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 详细地址
     *
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 创建人
     *
     * @return create_user 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建时间
     *
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 标记
     *
     * @return remark 标记
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 标记
     *
     * @param remark 标记
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 删除标记
     *
     * @return del_flag 删除标记
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标记
     *
     * @param delFlag 删除标记
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    /**
     * 登录标识
     *
     * @return login_flag 登录标识
     */
    public String getLoginFlag() {
        return loginFlag;
    }

    /**
     * 登录标识
     *
     * @param loginFlag 登录标识
     */
    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag == null ? null : loginFlag.trim();
    }

    /**
     * 职称
     *
     * @return position 职称
     */
    public String getPosition() {
        return position;
    }

    /**
     * 职称
     *
     * @param position 职称
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
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

    public Date getLogonTime() {
        return logonTime;
    }

    public void setLogonTime(Date logonTime) {
        this.logonTime = logonTime;
    }

    public String getLogonIp() {
        return logonIp;
    }

    public void setLogonIp(String logonIp) {
        this.logonIp = logonIp;
    }

    public SysUser() {
    }

    public SysUser(String id, String userName, String password, String name, String tel, String email, String idcard, String level, String province, String city, String area, String policeStation, String address, String createUser, Date createTime, String updateUser, Date updateTime, String remark, String delFlag, String loginFlag, String position, Integer isAdmin) {
        this.id = id;
        this.userName = userName;
        this.password = password;
//        this.userType = userType;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.idcard = idcard;
        this.level = level;
        this.province = province;
        this.city = city;
        this.area = area;
        this.policeStation = policeStation;
        this.address = address;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.remark = remark;
        this.delFlag = delFlag;
        this.loginFlag = loginFlag;
        this.position = position;
//        this.isAdmin = isAdmin;
    }

    public static String getUnitCode(SysUser sUser) {
        if (sUser == null || sUser.getLevel() == null) {
            return null;
        }
        int lev = Integer.parseInt(String.valueOf(sUser.getLevel()));
        String unitCode;
        switch (lev) {
            case 1: {
                unitCode = sUser.getProvince();
                break;
            }
            case 2: {
                unitCode = sUser.getCity();
                break;
            }
            case 3: {
                unitCode = sUser.getArea();
                break;
            }
            case 4: {
                unitCode = sUser.getPoliceStation();
                break;
            }
            default: {
                unitCode = null;
            }
        }
        return unitCode;
    }

//    /*用户添加时转换*/
//    public static SysUser valueOf(SysUserVO sysUser) {
//        SysUser sysUserV = new SysUser(
//                sysUser.getId(),
//                sysUser.getUserName(),
//                sysUser.getPassword(),
//                sysUser.getUserType(),
//                sysUser.getName(),
//                sysUser.getTel(),
//                sysUser.getEmail(),
//                sysUser.getIdcard(),
//                sysUser.getLevel(),
//                sysUser.getProvince(),
//                sysUser.getCity(),
//                sysUser.getArea(),
//                sysUser.getPoliceStation(),
//                sysUser.getAddress(),
//                sysUser.getCreateUser(),
//                sysUser.getCreateTime(),
//                sysUser.getUpdateUser(),
//                sysUser.getUpdateTime(),
//                sysUser.getRemark(),
//                sysUser.getDelFlag(),
//                sysUser.getLoginFlag(),
//                sysUser.getPosition(),
//                sysUser.getIsAdmin()
//        );
//        return sysUserV;
//    }
}