package com.service.task;

import com.entity.alarm.EtAlarm;
import com.entity.caseInfo.ApScarela;
import com.entity.caseInfo.ApStaff;
import com.entity.caseInfo.EtCase;
import com.entity.task.CollectRecord;
import com.entity.task.SysTaskLog;
import com.service.alarm.ETAlarmService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/8.
 */
public interface CollectSrcDataServiceInfc {
    /**
     * 从JZ库中 采集数据通用
     */
    List<EtCase> pageCollectJZAJData(Map<String,Object> map);

    CollectRecord writeOrUpdateEtCase(List<EtCase> srcData,List<EtCase> needToAdd,List<EtCase> needToUpdate);
    /**
     * 获取本地库中 AJ数据的最新 创建时间
     *
     * @return
     */
    Date getEtCaseLatestCreateTime();
    /**
     * 获取本地库中 AJ数据的最新 修改时间
     *
     * @return
     */
    Date getEtCaseLatestUpdateTime();

    /**
     * 从JZ库中 采集数据通用
     */
    List<EtAlarm> pageCollectJZJQData(Map<String,Object> map);

    CollectRecord writeOrUpdateEtAlarm(List<EtAlarm> srcData,List<EtAlarm> needToAdd,List<EtAlarm> needToUpdate);

    Date getEtAlarmLatestCreateTime();

    Date getEtAlarmLatestUpdateTime();

    List<ApScarela> pageCollectJZRYQKData(Map<String, Object> queryParam);

    CollectRecord writeOrUpdateApScarela(List<ApScarela> srcData,List<ApScarela> needToAdd,List<ApScarela> needToUpdate);

    Date getApScarelaLatestCreateTime();

    Date getApScarelaLatestUpdateTime();

    List<ApStaff> pageCollectJZXYRData(Map<String, Object> queryParam);

    CollectRecord writeOrUpdateApStaff(List<ApStaff> srcData,List<ApStaff> needToAdd,List<ApStaff> needToUpdate);

    Date getApStaffLatestCreateTime();

    Date getApStaffLatestUpdateTime();

    /**
     * 记录采集操作日志
     * @param collectRecord
     */
    void recordTaslLog(CollectRecord collectRecord);

    /**
     * 获取上一次采集操作日志
     * @param readTableName
     * @param writeTableName
     * @return
     */
    SysTaskLog getLastTaskLog(String readTableName, String writeTableName);
}
