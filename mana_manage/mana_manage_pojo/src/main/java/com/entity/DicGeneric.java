package com.entity;

/**
 * 描述:dic_generic表的实体类
 * @version
 * @author:  weipc
 * @创建时间: 2018-01-02
 */
public class DicGeneric {
    /**
     * 主键 泛型类别字典唯一编码
     */
    private String typeCode;

    /**
     * 泛型类别字典含义
     */
    private String typeDes;

    /**
     * 主键 泛型类别字典唯一编码
     * @return TYPE_CODE 主键 泛型类别字典唯一编码
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 主键 泛型类别字典唯一编码
     * @param typeCode 主键 泛型类别字典唯一编码
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    /**
     * 泛型类别字典含义
     * @return TYPE_DES 泛型类别字典含义
     */
    public String getTypeDes() {
        return typeDes;
    }

    /**
     * 泛型类别字典含义
     * @param typeDes 泛型类别字典含义
     */
    public void setTypeDes(String typeDes) {
        this.typeDes = typeDes == null ? null : typeDes.trim();
    }
}