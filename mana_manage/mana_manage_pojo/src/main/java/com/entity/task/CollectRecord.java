package com.entity.task;

import java.util.Date;

/**
 * 描述一次采集任务中 新录入多少数据 更新了多少数据
 * Created by Administrator on 2017/12/19.
 */
public class CollectRecord {

    /**
     * 描述从哪个表读取的数据
     */
    private String readTableName;

    /**
     * 描述把数据写入哪个表
     */
    private String writeTableName;

    /**
     * 采集的时间范围 开始时间
     */
    private Date excuBeginTime;
    /**
     * 采集的时间范围 结束时间
     */
    private Date excuEndTime;

    private int insertTotal = 0;

    private int updateTotal = 0;

    private int deleteTotal = 0;

    public CollectRecord(String readTableName, String writeTableName) {
        this.readTableName = readTableName;
        this.writeTableName = writeTableName;
    }
    public CollectRecord() {
    }

    public int getInsertTotal() {
        return insertTotal;
    }

    public void addInsertNum() {
        this.insertTotal++;
    }

    public void addInsertNum(int insertNum) {
        this.insertTotal += insertNum;
    }

    public int getUpdateTotal() {
        return updateTotal;
    }

    public void addUpdateNum() {
        this.updateTotal++;
    }

    public void addUpdateNum(int updateNum) {
        this.updateTotal += updateNum;
    }

    public int getDeleteTotal() {
        return deleteTotal;
    }

    public void addDeleteNum() {
        this.deleteTotal++;
    }

    public void addDeleteNum(int num) {
        this.deleteTotal += num;
    }

    public Date getExcuBeginTime() {
        return excuBeginTime;
    }

    public void setExcuBeginTime(Date excuBeginTime) {
        this.excuBeginTime = excuBeginTime;
    }

    public Date getExcuEndTime() {
        return excuEndTime;
    }

    public void setExcuEndTime(Date excuEndTime) {
        this.excuEndTime = excuEndTime;
    }

    public String getReadTableName() {
        return readTableName;
    }

    public String getWriteTableName() {
        return writeTableName;
    }

    public void toZero() {
        insertTotal = 0;
        updateTotal = 0;
        deleteTotal = 0;
        excuBeginTime = null;
        excuEndTime = null;
    }

    public void addRecord(CollectRecord theCollectRecord) {
        if (theCollectRecord == null) {
            return;
        }
        addInsertNum(theCollectRecord.getInsertTotal());
        addUpdateNum(theCollectRecord.getUpdateTotal());
        addDeleteNum(theCollectRecord.getDeleteTotal());
    }

    @Override
    public String toString() {
        return "CollectRecord{" +
                "读取数据表'" + readTableName + '\'' +
                ", 写入数据表'" + writeTableName + '\'' +
                ", 数据范围开始时间：" + excuBeginTime +
                ", 数据范围结束时间：" + excuEndTime +
                ", insertTotal=" + insertTotal +
                ", updateTotal=" + updateTotal +
                ", deleteTotal=" + deleteTotal +
                '}';
    }
}
