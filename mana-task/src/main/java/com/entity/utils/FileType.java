package com.entity.utils;

/**
 * Created by Administrator on 2017/12/19.
 */
public enum FileType {
    TXT("TXT"), DOCX("DOCX"), XLSX("XLSX"), PDF("PDF");

    private String typeName;

    FileType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
