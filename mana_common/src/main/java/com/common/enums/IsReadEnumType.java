package com.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *  是否已经读取
 * Created by t on 2017/1/15.
 */
public enum IsReadEnumType {

    NO_READ("未读", 0),
    HAS_READ("已读", 1);
    private String name;
    private int value;

    IsReadEnumType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(int value) {
        for (IsReadEnumType o : IsReadEnumType.values()) {
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

    public static IsReadEnumType valueof(int value) {
        IsReadEnumType[] values = values();
        for (IsReadEnumType orderType : values) {
            if (orderType.getValue() == value) {
                return orderType;
            }
        }
        throw new IllegalArgumentException("PayType定义，value=" + value + "的数据");
    }

    public static List<EnumTypeVO> valueList() {
        IsReadEnumType[] values = values();
        List<EnumTypeVO> enumTypeVOList = new ArrayList<>();
        for (IsReadEnumType businessIdentityType : values) {
            enumTypeVOList.add(new EnumTypeVO(businessIdentityType.getName(), String.valueOf(businessIdentityType.getValue())));
        }
        return enumTypeVOList;
    }
}
