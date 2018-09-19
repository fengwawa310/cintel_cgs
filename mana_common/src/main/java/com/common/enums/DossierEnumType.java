package com.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *  是否已经读取
 * Created by t on 2017/1/15.
 */
public enum DossierEnumType {

    DUDANG_CLUE("赌档线索", 0),
    QITA_CLUE("其它", 1);
    private String name;
    private int value;

    DossierEnumType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(int value) {
        for (DossierEnumType o : DossierEnumType.values()) {
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

    public static DossierEnumType valueof(int value) {
        DossierEnumType[] values = values();
        for (DossierEnumType orderType : values) {
            if (orderType.getValue() == value) {
                return orderType;
            }
        }
        throw new IllegalArgumentException("PayType定义，value=" + value + "的数据");
    }

    public static List<EnumTypeVO> valueList() {
        DossierEnumType[] values = values();
        List<EnumTypeVO> enumTypeVOList = new ArrayList<>();
        for (DossierEnumType businessIdentityType : values) {
            enumTypeVOList.add(new EnumTypeVO(businessIdentityType.getName(), String.valueOf(businessIdentityType.getValue())));
        }
        return enumTypeVOList;
    }
}
