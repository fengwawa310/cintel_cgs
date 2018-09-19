package com.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t on 2017/1/15.
 */
public enum IsShowType {

    SHOW("显示", 1),
    HIDDEN("隐藏", 0);
    private String name;
    private int value;

    IsShowType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(int value) {
        for (IsShowType o : IsShowType.values()) {
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

    public static IsShowType valueof(int value) {
        IsShowType[] values = values();
        for (IsShowType orderType : values) {
            if (orderType.getValue() == value) {
                return orderType;
            }
        }
        throw new IllegalArgumentException("PayType定义，value=" + value + "的数据");
    }

    public static List<EnumTypeVO> valueList() {
        IsShowType[] values = values();
        List<EnumTypeVO> enumTypeVOList = new ArrayList<>();
        for (IsShowType businessIdentityType : values) {
            enumTypeVOList.add(new EnumTypeVO(businessIdentityType.getName(), String.valueOf(businessIdentityType.getValue())));
        }
        return enumTypeVOList;
    }
}
