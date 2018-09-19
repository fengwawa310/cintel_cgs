package com.entity;

/**
 * 描述:dic_unit表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-01-06
 */
public class DicUnit {
    /**
     * 主键 （行政编号）
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父级编号
     */
    private String parentId;

    /**
     * 区域编码
     */
    private String code;

    /**
     * 机构等级
     */
    private String grade;

    /**
     * 机构地址
     */
    private String address;

    /**
     * 主键 （行政编号）
     * @return ID 主键 （行政编号）
     */
    public String getId() {
        return id;
    }

    /**
     * 主键 （行政编号）
     * @param id 主键 （行政编号）
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 名称
     * @return NAME 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 父级编号
     * @return PARENT_ID 父级编号
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 父级编号
     * @param parentId 父级编号
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 区域编码
     * @return CODE 区域编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 区域编码
     * @param code 区域编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 机构等级
     * @return GRADE 机构等级
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 机构等级
     * @param grade 机构等级
     */
    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    /**
     * 机构地址
     * @return ADDRESS 机构地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 机构地址
     * @param address 机构地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}