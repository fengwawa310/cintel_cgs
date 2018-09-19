package com.collect.cnf;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 数据采集模块 任务控制模型
 */
@XmlRootElement(name = "cnf")
public class CTCnf {
    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "enable")
    private boolean enable;

    @XmlAttribute(name = "begintime")
    private String beginTime;

    @XmlAttribute(name = "endTime")
    private String endTime;

    @XmlAttribute(name = "size")
    private int size;

    @XmlAttribute(name = "tofile")
    private boolean enableToFile;

    /**
     * 临时存储目录
     */
    @XmlAttribute(name = "dir_temp")
    private String dirTemp;

    /**
     * 最终转移目录
     */
    @XmlAttribute(name = "dir_mv")
    private String dirMv;

    @XmlAttribute(name = "max_num")
    private int maxNum;

    @XmlAttribute(name = "readTable")
    private String readTable;

    @XmlAttribute(name = "writeTable")
    private String writeTable;

    /**
     * orcl表名
     */
    @XmlAttribute(name = "readSql")
    private String readSql;

    /**
     * 是否手动触发
     */
    private boolean isManual;

    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    @XmlTransient
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @XmlTransient
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @XmlTransient
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @XmlTransient
    public String getDirTemp() {
        return dirTemp;
    }

    public void setDirTemp(String dirTemp) {
        this.dirTemp = dirTemp;
    }

    @XmlTransient
    public String getDirMv() {
        return dirMv;
    }

    public void setDirMv(String dirMv) {
        this.dirMv = dirMv;
    }

    @XmlTransient
    public boolean isEnableToFile() {
        return enableToFile;
    }

    public void setEnableToFile(boolean enableToFile) {
        this.enableToFile = enableToFile;
    }

    @XmlTransient
    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public boolean isManual() {
        return isManual;
    }

    public void setManual(boolean manual) {
        isManual = manual;
    }

    @XmlTransient
    public String getReadTable() {
        return readTable;
    }

    public void setReadTable(String readTable) {
        this.readTable = readTable;
    }

    @XmlTransient
    public String getWriteTable() {
        return writeTable;
    }

    public void setWriteTable(String writeTable) {
        this.writeTable = writeTable;
    }

    @XmlTransient
    public String getReadSql() {
        return readSql;
    }

    public void setReadSql(String readSql) {
        this.readSql = readSql;
    }
}
