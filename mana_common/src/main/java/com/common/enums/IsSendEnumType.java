package com.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *  是否已经发送
 * Created by t on 2017/1/15.
 */
public enum IsSendEnumType {

    NO_SEND("未发送", 0),
    HAS_SEND("已发送", 1);
    private String name;
    private int value;

    IsSendEnumType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(int value) {
        for (IsSendEnumType o : IsSendEnumType.values()) {
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

    public static IsSendEnumType valueof(int value) {
        IsSendEnumType[] values = values();
        for (IsSendEnumType orderType : values) {
            if (orderType.getValue() == value) {
                return orderType;
            }
        }
        throw new IllegalArgumentException("PayType定义，value=" + value + "的数据");
    }

    public static List<EnumTypeVO> valueList() {
        IsSendEnumType[] values = values();
        List<EnumTypeVO> enumTypeVOList = new ArrayList<>();
        for (IsSendEnumType businessIdentityType : values) {
            enumTypeVOList.add(new EnumTypeVO(businessIdentityType.getName(), String.valueOf(businessIdentityType.getValue())));
        }
        return enumTypeVOList;
    }
}
