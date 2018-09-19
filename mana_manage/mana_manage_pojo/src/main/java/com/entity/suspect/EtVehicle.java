package com.entity.suspect;

import java.util.Date;

/**
 * 描述:et_vehicle表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-02-03
 */
public class EtVehicle {
    /**
     * 主键
     */
    private String id;

    /**
     * 重点人员编号
     */
    private String suspectNo;

    /**
     * 车牌号码
     */
    private String plateNum;

    /**
     * 驾驶证号码
     */
    private String driverLicense;

    /**
     * 车身颜色
     */
    private String carColor;

    /**
     * 车型
     */
    private String carType;

    /**
     * 备注
     */
    private String demo;

    /**
     * 最后出现地点区划编码
     */
    private String lastestAreaCode;

    /**
     * 最后出现地点区划名称
     */
    private String lastestAreaName;

    /**
     * 系统创建时间
     */
    private Date creatTime;

    /**
     * 系统修改时间
     */
    private Date modifyTime;

    /**
     * 主键
     * @return ID 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 重点人员编号
     * @return SUSPECT_NO 重点人员编号
     */
    public String getSuspectNo() {
        return suspectNo;
    }

    /**
     * 重点人员编号
     * @param suspectNo 重点人员编号
     */
    public void setSuspectNo(String suspectNo) {
        this.suspectNo = suspectNo == null ? null : suspectNo.trim();
    }

    /**
     * 车牌号码
     * @return PLATE_NUM 车牌号码
     */
    public String getPlateNum() {
        return plateNum;
    }

    /**
     * 车牌号码
     * @param plateNum 车牌号码
     */
    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum == null ? null : plateNum.trim();
    }

    /**
     * 驾驶证号码
     * @return DRIVER_LICENSE 驾驶证号码
     */
    public String getDriverLicense() {
        return driverLicense;
    }

    /**
     * 驾驶证号码
     * @param driverLicense 驾驶证号码
     */
    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense == null ? null : driverLicense.trim();
    }

    /**
     * 车身颜色
     * @return CAR_COLOR 车身颜色
     */
    public String getCarColor() {
        return carColor;
    }

    /**
     * 车身颜色
     * @param carColor 车身颜色
     */
    public void setCarColor(String carColor) {
        this.carColor = carColor == null ? null : carColor.trim();
    }

    /**
     * 车型
     * @return CAR_TYPE 车型
     */
    public String getCarType() {
        return carType;
    }

    /**
     * 车型
     * @param carType 车型
     */
    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    /**
     * 备注
     * @return DEMO 备注
     */
    public String getDemo() {
        return demo;
    }

    /**
     * 备注
     * @param demo 备注
     */
    public void setDemo(String demo) {
        this.demo = demo == null ? null : demo.trim();
    }

    /**
     * 最后出现地点区划编码
     * @return LASTEST_AREA_CODE 最后出现地点区划编码
     */
    public String getLastestAreaCode() {
        return lastestAreaCode;
    }

    /**
     * 最后出现地点区划编码
     * @param lastestAreaCode 最后出现地点区划编码
     */
    public void setLastestAreaCode(String lastestAreaCode) {
        this.lastestAreaCode = lastestAreaCode == null ? null : lastestAreaCode.trim();
    }

    /**
     * 最后出现地点区划名称
     * @return LASTEST_AREA_NAME 最后出现地点区划名称
     */
    public String getLastestAreaName() {
        return lastestAreaName;
    }

    /**
     * 最后出现地点区划名称
     * @param lastestAreaName 最后出现地点区划名称
     */
    public void setLastestAreaName(String lastestAreaName) {
        this.lastestAreaName = lastestAreaName == null ? null : lastestAreaName.trim();
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

    /**
     * 系统修改时间
     * @return MODIFY_TIME 系统修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 系统修改时间
     * @param modifyTime 系统修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}