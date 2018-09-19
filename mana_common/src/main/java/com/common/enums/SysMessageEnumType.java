package com.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t on 2017/1/15.
 */
public enum SysMessageEnumType {

    GONG_GAO("公告", 3300),
    JIAN_YI("建议", 3301);
    private String name;
    private int value;

    SysMessageEnumType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(int value) {
        for (SysMessageEnumType o : SysMessageEnumType.values()) {
            if (o.getValue() == value) {
                return o.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static SysMessageEnumType valueof(int value) {
        SysMessageEnumType[] values = values();
        for (SysMessageEnumType orderType : values) {
            if (orderType.getValue() == value) {
                return orderType;
            }
        }
        throw new IllegalArgumentException("PayType定义，value=" + value + "的数据");
    }

    public static List<EnumTypeVO> valueList() {
        SysMessageEnumType[] values = values();
        List<EnumTypeVO> enumTypeVOList = new ArrayList<>();
        for (SysMessageEnumType businessIdentityType : values) {
            enumTypeVOList.add(new EnumTypeVO(businessIdentityType.getName(), String.valueOf(businessIdentityType.getValue())));
        }
        return enumTypeVOList;
    }
}
