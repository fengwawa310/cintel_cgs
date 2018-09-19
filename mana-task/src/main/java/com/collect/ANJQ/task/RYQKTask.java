package com.collect.ANJQ.task;

import com.collect.cnf.CTCnf;
import com.collect.cnf.CTDate;
import com.collect.cnf.CTTaskCnf;
import com.common.utils.IDGenerator;
import com.common.utils.LogUtils;
import com.common.utils.StringHelp;
import com.common.utils.TimeUtil;
import com.controller.task.DockingController;
import com.entity.caseInfo.ApScarela;
import com.entity.task.CollectRecord;
import com.service.task.CollectSrcDataServiceInfc;

import java.util.*;

/**
 * Created by Auri on 2018/2/10.
 * Desc:
 */
public class RYQKTask extends CollectModel {
    public RYQKTask(CollectSrcDataServiceInfc collectSrcDataService, CTCnf ryqkCnf) {
        super(collectSrcDataService,ryqkCnf);
    }

    @Override
    public void pageCollectByCnf(CTCnf cnf, List<String> relatedKeys, CollectTaskLisnr pageCollectLisnr) {
        if (!checkTask(CTTaskCnf.CT_RYQK, cnf))// 校验是否可执行
        {
            return;
        }
        if (relatedKeys == null || relatedKeys.isEmpty()) {
            return;
        }
        // 识别数据时间范围
        CTDate beginTime = parseBeginDate(cnf.isManual(), cnf.getBeginTime(), "yyyy-MM-dd HH:mm:ss");
        CTDate endTime = parseEndDate(cnf.isManual(), cnf.getEndTime(), "yyyy-MM-dd HH:mm:ss");
        // 记录采集数据的时间范围
        collectRecord.setExcuBeginTime(beginTime.getTime());
        collectRecord.setExcuEndTime(endTime.getTime());
        // 识别分页大小
        int pageSize = cnf.getSize();
        int rowBegin = 1;// 分页技术 依据数据表rownum 故从1开始
        int total = pageCollectSrcData(cnf, relatedKeys, beginTime, endTime, rowBegin, pageSize, pageCollectLisnr);
    }

    private int pageCollectSrcData(CTCnf cnf, List<String> relatedKeys, CTDate beginTime, CTDate endTime, int rowBegin, int pageSize, CollectTaskLisnr pageCollectLisnr) {
        int rowEnd = rowBegin + pageSize - 1;// 分页采取 左闭右闭 原则
        String ctName = cnf.getName();
        int totalCollect = 0;// 统计本次成功采集到的数据数目

        long startL = System.currentTimeMillis();// 统计耗时
        Map<String, List<String>> mainKeys = pageReadAndWriteSrcData(cnf, relatedKeys, beginTime, endTime, rowBegin, rowEnd);// 采集到的案件编号集合
        if (mainKeys == null || mainKeys.isEmpty()) {
            if ("info".equals(DockingController.logLevel)){
                // 没有有效数据 故不再迭代
                LogUtils.info("CT " + ctName + " 没有有效数据 故不再迭代");
            }
            return totalCollect;
        }
        List<String> rybhs = mainKeys.get(KEY_RYBHARR);
        if (rybhs == null || rybhs.isEmpty()) {
            if ("info".equals(DockingController.logLevel)){
                // 没有有效数据 故不再迭代
                LogUtils.info("CT " + ctName + " 没有有效数据 故不再迭代");
            }
            return totalCollect;
        }
        // 一次采集结束 触发监听函数
        if (pageCollectLisnr != null) {
            pageCollectLisnr.whenPageCollectFinish(mainKeys);
        }
        totalCollect = rybhs.size();
        if ("info".equals(DockingController.logLevel)){
            LogUtils.info("CT PAGE " + cnf.getName() + " 成功采集原始数据 " + totalCollect + "条,耗费时间(毫秒)：" + (System.currentTimeMillis() - startL));
        }
        totalCollect += pageCollectSrcData(cnf, relatedKeys, beginTime, endTime, rowBegin + pageSize, pageSize, pageCollectLisnr);// 叠加
        return totalCollect;
    }

    private Map<String, List<String>> pageReadAndWriteSrcData(CTCnf cnf, List<String> relatedKeys, CTDate beginTime, CTDate endTime, int rowBegin, int rowEnd) {
        String ctName = cnf.getName();
        if ("info".equals(DockingController.logLevel)){
            LogUtils.info("CT 开始分页采集 JZ 库中 " + ctName + " 原始数据。beginTime:" + beginTime.getTimeToString() + ",endTime:" + endTime.getTimeToString() + ",rowBegin:" + rowBegin + ",rowEnd:" + rowEnd);
        }
        // 构建查询sql
        String baseReadSql = cnf.getReadSql();
//        String beginTimeWhereSql = buildWhereBeginTimeSql(beginTime.getTimeToString());
//        String whereEndTimeSql = buildWhereEndTimeSql(endTime.getTimeToString());
        String rybhArrStr = buildWhereInSql(relatedKeys);
        String readSql = String.format(baseReadSql, rybhArrStr, rowEnd, rowBegin);
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("sql", readSql);

        // 执行查询
        List<ApScarela> srcData = collectSrcDataService.pageCollectJZRYQKData(queryParam);
        if (srcData == null || srcData.isEmpty()) {
            if ("info".equals(DockingController.logLevel)){
                LogUtils.info("CT 没有采集到 JZ 库中的 " + ctName + " 原始数据!");
            }
            return null;
        }
        // 对其中录入单位、录入人、案件状态、案件类别进行特别处理
        srcData = specialTreatment(srcData);
        if ("info".equals(DockingController.logLevel)){
            LogUtils.info("CT 本次采集 JZ 库中的  " + ctName + " 原始数据条数：" + srcData.size());
        }

        // 执行写入或更新
        List<ApScarela> needToAdd = new ArrayList<>();
        List<ApScarela> needToUpdate = new ArrayList<>();
        CollectRecord theCollectRecord = collectSrcDataService.writeOrUpdateApScarela(srcData,needToAdd,needToUpdate);
        collectRecord.addRecord(theCollectRecord);

        // 执行写入文件
        if (cnf.isEnableToFile()) {
            int exportTotalA = toJsonFileAfterInsert(needToAdd, ctName);
            int exportTotalU = toJsonFileAfterUpdate(needToUpdate, ctName);
        } else {
            if ("info".equals(DockingController.logLevel)){
                LogUtils.info("CT 本次分页采集 " + ctName + " 无需写入文件");
            }
        }

        Map<String, List<String>> mainKey = new HashMap<>();
        List<String> rybhs = new ArrayList<>();
        int len = srcData.size();
        for (int i = 0; i < len; i++) {
            String rybh = srcData.get(i).getStaffId();
            if (StringHelp.isNotEmpty(rybh)) {
                rybhs.add(rybh);
            }
        }
        mainKey.put(KEY_RYBHARR, rybhs);
        return mainKey;
    }

    private List<ApScarela> specialTreatment(List<ApScarela> srcData) {
        List<ApScarela> apScarelas = new ArrayList<>();
        for (ApScarela et : srcData) {
            if(StringHelp.isEmpty(et.getId()))
            {
                et.setId(IDGenerator.getorderNo());
            }
            Date currDate = new Date();
            String currDateStr = TimeUtil.formatDateToStr(currDate,null);
            et.setCreatTime(currDateStr);
            et.setModifyTime(currDateStr);
            apScarelas.add(et);
        }
        return apScarelas;
    }

    @Override
    public void pageReviseByCnf(CTCnf cnf, CollectTaskLisnr pageCollectLisnr) {

    }

//    @Override
//    public Date getLatestCreateTime() {
//        return collectSrcDataService.getApScarelaLatestCreateTime();
//    }
//
//    @Override
//    public Date getLatestUpdateTime() {
//        return collectSrcDataService.getApScarelaLatestUpdateTime();
//    }
}
