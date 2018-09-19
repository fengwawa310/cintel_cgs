package com.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t on 2017/1/15.
 */
public enum ResponseType {

    NORMAL("正在配送", 1),
    ERROR("已收货", 2);
    private String name;
    private int value;

    ResponseType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(int value) {
        for (ResponseType o : ResponseType.values()) {
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

    public static ResponseType valueof(int value) {
        ResponseType[] values = values();
        for (ResponseType orderType : values) {
            if (orderType.getValue() == value) {
                return orderType;
            }
        }
        throw new IllegalArgumentException("PayType定义，value=" + value + "的数据");
    }

    public static List<EnumTypeVO> valueList() {
        ResponseType[] values = values();
        List<EnumTypeVO> enumTypeVOList = new ArrayList<>();
        for (ResponseType businessIdentityType : values) {
            enumTypeVOList.add(new EnumTypeVO(businessIdentityType.getName(), String.valueOf(businessIdentityType.getValue())));
        }
        return enumTypeVOList;
    }
}
