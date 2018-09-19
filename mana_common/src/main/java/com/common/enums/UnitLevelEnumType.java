package com.common.enums;

/**
 * **************************************************************
 * @描述        :	用户类型枚举

 * ***************************************************************
 */
public enum UnitLevelEnumType {
    PROVINCE("省", "1"),
    CITY("市", "2"),
    COUNTY("区", "3"),
    PCS("派出所", "4");
    private String name;
    private String value;

    UnitLevelEnumType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(String value) {
        for (UnitLevelEnumType o : UnitLevelEnumType.values()) {
            if (o.getValue().equals(value)) {
                return o.name;
            }
        }
        return null;
    }
    public static UnitLevelEnumType valueof(String value) {
        UnitLevelEnumType[] values = values();
        for (UnitLevelEnumType orderType : values) {
            if (orderType.getValue().equals(value)) {
                return orderType;
            }
        }
        throw new IllegalArgumentException("PayType定义，value=" + value + "的数据");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StateType{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
