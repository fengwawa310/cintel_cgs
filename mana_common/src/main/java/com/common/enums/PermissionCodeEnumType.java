package com.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *  重点人员警员权限
 * Created by t on 2017/1/15.
 */
public enum PermissionCodeEnumType {

    JDR("建档人", 0),
    KCY("可查阅", 1),
    KCYKBJ("可查阅可编辑", 2),
    WQX("无权限", 3);
    private String name;
    private int value;

    PermissionCodeEnumType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(int value) {
        for (PermissionCodeEnumType o : PermissionCodeEnumType.values()) {
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

    public static PermissionCodeEnumType valueof(int value) {
        PermissionCodeEnumType[] values = values();
        for (PermissionCodeEnumType orderType : values) {
            if (orderType.getValue() == value) {
                return orderType;
            }
        }
        throw new IllegalArgumentException("PayType定义，value=" + value + "的数据");
    }

    public static List<EnumTypeVO> valueList() {
        PermissionCodeEnumType[] values = values();
        List<EnumTypeVO> enumTypeVOList = new ArrayList<>();
        for (PermissionCodeEnumType businessIdentityType : values) {
            enumTypeVOList.add(new EnumTypeVO(businessIdentityType.getName(), String.valueOf(businessIdentityType.getValue())));
        }
        return enumTypeVOList;
    }
}
