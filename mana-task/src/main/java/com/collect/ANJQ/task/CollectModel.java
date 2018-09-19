package com.collect.ANJQ.task;

import com.collect.cnf.CTCnf;
import com.collect.cnf.CTDate;
import com.collect.export.ExportModel;
import com.common.utils.LogUtils;
import com.common.utils.StringHelp;
import com.common.utils.TimeUtil;
import com.controller.task.DockingController;
import com.entity.task.CollectRecord;
import com.entity.task.SysTaskLog;
import com.entity.utils.FileType;
import com.service.task.CollectSrcDataServiceInfc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/11.
 */
public abstract class CollectModel {

    public static final String KEY_AJBHARR = "ajbhArr";
    public static final String KEY_JQBHARR = "jqbhArr";
    public static final String KEY_RYBHARR = "rybhArr";

    protected CollectSrcDataServiceInfc collectSrcDataService;

    /**
     * 新添数据导出组件
     */
    protected ExportModel insertProcExportModel;
    /**
     * 更新数据导出组件
     */
    protected ExportModel updateProcExportModel;

    /**
     * 采集记录模型
     */
    protected CollectRecord collectRecord;

    public CollectModel(CollectSrcDataServiceInfc collectSrcDataService, CTCnf cnf) {
        this.collectSrcDataService = collectSrcDataService;
        this.collectRecord = new CollectRecord(cnf.getReadTable(), cnf.getWriteTable());
//        exportModel = new ExportModel();
        insertProcExportModel = new ExportModel();
        updateProcExportModel = new ExportModel();
    }

    protected boolean checkTask(String taskName, CTCnf cnf) {
        if (StringHelp.isEmpty(taskName)) {
            if ("info".equals(DockingController.logLevel)){
                LogUtils.info("CT 无法执行 未知 采集任务");
            }
            return false;
        }
        if (taskName.equals(cnf.getName())) {//主案件
            if (cnf == null || !cnf.isEnable()) {
                if ("info".equals(DockingController.logLevel)){
                    LogUtils.info("CT 无法执行 " + cnf.getName() + " 采集任务");
                }
                return false;
            }
        }
        return true;
    }

    public void prepareToCollect(CTCnf cnf) {
        collectRecord.toZero();// 操作数目归零
        // 构造数据导出组件
        buildFileExport(cnf,insertProcExportModel);
        buildFileExport(cnf,updateProcExportModel);
    }

    private ExportModel buildFileExport(CTCnf cnf,ExportModel theExportModel) {
        if (cnf == null) {
            return null;
        }
        if (theExportModel == null) {
            theExportModel = new ExportModel();
        }
        theExportModel.setDataDestDir(cnf.getDirMv());
        theExportModel.setTempDir(cnf.getDirTemp());
        theExportModel.setMaxNum(cnf.getMaxNum());
        theExportModel.currNumToZero();
        theExportModel.setEnableToFile(cnf.isEnableToFile());
        return theExportModel;
    }

    /**
     * 将原始数据集合 以json的格式 写入一txt文件
     * txt文件先在临时目录中生成，待数据写入完毕后，移动至目标目录下
     * txt文件名称为：XX_IN_时间戳.TXT
     *
     * @param srcData
     * @param ctName
     */
    protected <T> int toJsonFileAfterInsert(List<T> srcData, String ctName) {
        ctName = ctName + "_IN";
        if (srcData == null || srcData.isEmpty() || insertProcExportModel == null) {
            return -1;
        }
        String timeStr = TimeUtil.formatDateToStr(new Date(), "yyyyMMddHHmmss");
        String fileName = ctName + "_" + timeStr;
        int exportTotal = insertProcExportModel.fileExport(srcData, fileName, FileType.TXT);
        if ("info".equals(DockingController.logLevel)){
            LogUtils.info("CT " + ctName + "json 写入到文件 exportTotal:" + exportTotal);
        }
        return exportTotal;
    }

    /**
     * 将原始数据集合 以json的格式 写入一txt文件中
     * txt文件先在临时目录中生成，待数据写入完毕后，移动至目标目录下
     * txt文件名称为：XX_UP_时间戳.TXT
     *
     * @param srcData
     * @param ctName
     */
    protected <T> int toJsonFileAfterUpdate(List<T> srcData, String ctName) {
        ctName = ctName + "_UP";
        if (srcData == null || srcData.isEmpty() || updateProcExportModel == null) {
            return -1;
        }
        String timeStr = TimeUtil.formatDateToStr(new Date(), "yyyyMMddHHmmss");
        String fileName = ctName + "_" + timeStr;
        int exportTotal = updateProcExportModel.fileExport(srcData, fileName, FileType.TXT);
        if ("info".equals(DockingController.logLevel)){
            LogUtils.info("CT " + ctName + "json 写入到文件 exportTotal:" + exportTotal);
        }
        return exportTotal;
    }

    protected Date parseTimeStr(String timeStr) {

        if (StringHelp.isEmpty(timeStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginTime = null;
        try {
            beginTime = sdf.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            if ("info".equals(DockingController.logLevel)){
                LogUtils.info("CT 采集任务 时间解析失败 错误的时间字符为：" + timeStr);
            }
        }
        return beginTime;
    }

    /**
     * 依照配置文件 执行采集新数据任务
     *
     * @param pageCollectLisnr
     * @param relatedKeys      关联标识 例如AJBH、SYSTEMID
     * @param cnf              配置文件模型
     */
    public abstract void pageCollectByCnf(CTCnf cnf, List<String> relatedKeys, CollectTaskLisnr pageCollectLisnr);

    /**
     * 依照配置文件 执行修订数据任务
     *
     * @param cnf
     * @param pageCollectLisnr
     */
    public abstract void pageReviseByCnf(CTCnf cnf, CollectTaskLisnr pageCollectLisnr);

    protected CTDate parseBeginDate(boolean isManual, String timeStr, String formatStr) {
        Date beginTime = null;
        if (isManual) {
            beginTime = TimeUtil.parseTimeStr(timeStr, formatStr);// 手动触发 开始时间由配置文件决定
        } else {
            beginTime = getLastExcuEndTime();// 自动触发时 开始时间由业务决定
            if (beginTime == null) {
                beginTime = TimeUtil.parseTimeStr(timeStr, formatStr);// 若是第一次执行同步，则开始时间由配置文件决定
            } else {
                timeStr = TimeUtil.formatDateToStr(beginTime, "yyyy-MM-dd HH:mm:ss");
            }
//            if (beginTime == null) {
//                beginTime = TimeUtil.parseTimeStr(timeStr, formatStr);// 手动触发 开始时间由配置文件决定
//            }
        }
        CTDate ctDate = new CTDate(timeStr, beginTime);
        return ctDate;
    }

    public Date getLastExcuEndTime() {
        SysTaskLog taslLog = collectSrcDataService.getLastTaskLog(collectRecord.getReadTableName(), collectRecord.getWriteTableName());
        if (taslLog == null) {
            return null;
        }
        return taslLog.getLastexcuEndtime();// 上一次的结束时间 是这一次的开始时间
    }

//    public abstract Date getLatestUpdateTime();

    protected CTDate parseEndDate(boolean isManual, String timeStr, String formatStr) {
        Date endTime = null;
        if (isManual) {
            endTime = TimeUtil.parseTimeStr(timeStr, formatStr);// 手动触发 开始时间由配置文件决定
        } else {
            endTime = new Date();// 自动触发时 当前时间为结束时间
            timeStr = TimeUtil.formatDateToStr(endTime, "yyyy-MM-dd HH:mm:ss");
            if (endTime == null) {
                endTime = TimeUtil.parseTimeStr(timeStr, formatStr);// 手动触发 开始时间由配置文件决定
            }
        }
        CTDate ctDate = new CTDate(timeStr, endTime);
        return ctDate;
    }

    public CollectRecord getCollectRecord() {
        return collectRecord;
    }

    public void forceMV() {
        if (insertProcExportModel != null) {
            insertProcExportModel.mvDataFile();// 任务结束 将当下的临时文件 转移
        }
        if (updateProcExportModel != null) {
            updateProcExportModel.mvDataFile();// 任务结束 将当下的临时文件 转移
        }
    }

    /**
     * 将jqbh 组装成 sql中的in语句
     *
     * @param relatedKeys
     * @return
     */
    protected String buildWhereInSql(List<String> relatedKeys) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, len = relatedKeys.size(); i < len; i++) {
            String str = relatedKeys.get(i);
            if (StringHelp.isEmpty(str)) {
                continue;
            }
            stringBuilder.append("'").append(str).append("',");
        }
        String resultStr = stringBuilder.toString();
        if (StringHelp.isEmpty(resultStr)) {
            return "''";
        }
        resultStr = resultStr.substring(0, resultStr.length() - 1);
        return resultStr;
    }
}
