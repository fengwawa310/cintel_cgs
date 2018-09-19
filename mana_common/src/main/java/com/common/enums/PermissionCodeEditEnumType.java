package com.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t on 2017/1/15.
 */
public enum PermissionCodeEditEnumType {

    SURE("赋予权限", 1),
    NO("取消权限", 2);
    private String name;
    private int value;

    PermissionCodeEditEnumType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(int value) {
        for (PermissionCodeEditEnumType o : PermissionCodeEditEnumType.values()) {
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

    public static PermissionCodeEditEnumType valueof(int value) {
        PermissionCodeEditEnumType[] values = values();
        for (PermissionCodeEditEnumType orderType : values) {
            if (orderType.getValue() == value) {
                return orderType;
            }
        }
        throw new IllegalArgumentException("PayType定义，value=" + value + "的数据");
    }

    public static List<EnumTypeVO> valueList() {
        PermissionCodeEditEnumType[] values = values();
        List<EnumTypeVO> enumTypeVOList = new ArrayList<>();
        for (PermissionCodeEditEnumType businessIdentityType : values) {
            enumTypeVOList.add(new EnumTypeVO(businessIdentityType.getName(), String.valueOf(businessIdentityType.getValue())));
        }
        return enumTypeVOList;
    }
}
