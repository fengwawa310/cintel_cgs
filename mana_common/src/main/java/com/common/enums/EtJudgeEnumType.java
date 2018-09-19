package com.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *  是否已经读取
 * Created by t on 2017/1/15.
 */
public enum EtJudgeEnumType {

    NO_SH("待审核", 5000),
    HAS_PASS("审核通过", 5001),
    NO_PASS("审核未通过", 5002),
    DQS("待签收", 5003),
    HAS_QS("已签收", 5004),
    JJQS("拒绝签收", 5005),
    HAS_YYP("已研判", 5006);
    private String name;
    private int value;

    EtJudgeEnumType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(int value) {
        for (EtJudgeEnumType o : EtJudgeEnumType.values()) {
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

    public static EtJudgeEnumType valueof(int value) {
        EtJudgeEnumType[] values = values();
        for (EtJudgeEnumType orderType : values) {
            if (orderType.getValue() == value) {
                return orderType;
            }
        }
        throw new IllegalArgumentException("PayType定义，value=" + value + "的数据");
    }

    public static List<EnumTypeVO> valueList() {
        EtJudgeEnumType[] values = values();
        List<EnumTypeVO> enumTypeVOList = new ArrayList<>();
        for (EtJudgeEnumType businessIdentityType : values) {
            enumTypeVOList.add(new EnumTypeVO(businessIdentityType.getName(), String.valueOf(businessIdentityType.getValue())));
        }
        return enumTypeVOList;
    }
}
