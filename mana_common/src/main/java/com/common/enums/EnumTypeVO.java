package com.common.enums;

/**
 * @描述 :
 * @作者 :	sqc
 * @日期 :	2016/5/4
 * @时间 :	15:56
 */
public class EnumTypeVO {

    private String name;
    private String value;

    public EnumTypeVO() {
    }

    public EnumTypeVO(String name, String value) {
        this.name = name;
        this.value = value;
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
        return "EnumTypeVO{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
