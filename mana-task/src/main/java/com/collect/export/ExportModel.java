package com.collect.export;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.common.utils.ExecLinuxCMD;
import com.common.utils.FileUtil;
import com.common.utils.LogUtils;
import com.common.utils.StringHelp;
import com.common.utils.TimeUtil;
import com.entity.utils.FileExportUtil;
import com.entity.utils.FileType;

/**
 * Created by Administrator on 2017/12/19.
 */
public class ExportModel {

    /**
     * 当下临时文件完整路径
     */
    private String currTempFilePath;

    /**
     * 临时数据文件存放目录 完整路径
     */
    private String tempDir;

    /**
     * 数据文件最终存放目录 完整路径
     */
    private String dataDestDir;

    /**
     * 一个数据文件最大记录数
     */
    private int maxNum = 50000;

    /**
     * 当前数据文件记录数
     */
    private int currNum = 0;

    /**
     * 是否允许导出至文件
     */
    private boolean isEnableToFile = false;

//    protected abstract void setTempFilePath(String filePath);
//
//    protected abstract void setFinlFileDir(String finlDir);
//
//    protected abstract void addRecord();
//
//    protected abstract String getTempFilePath();
//
//    protected abstract boolean isNeedCreateNewOne();

//    /**
//     * 数据向文件导出
//     *
//     * @param objList
//     * @param fileType
//     * @param exportPath
//     * @param finalFileDir
//     * @param fileName
//     * @return -1 导出失败
//     */
//    public <T> int fileExport(List<T> objList, FileType fileType,
//                              String exportPath, String finalFileDir, String fileName) {
//        return fileExport(objList, fileName, fileType);
//    }

    /**
     * 数据向文件导出
     *
     * @param objList
     * @param fileType
     * @param fileName
     * @return -1 导出失败
     */
    public <T> int fileExport(List<T> objList, String fileName, FileType fileType) {
        if (!checkExportEnable()) {
            LogUtils.warn("不允许导出！条件未达标");
            return -1;
        }
        if (StringHelp.isEmpty(fileName)) {
            fileName = "Unknow_" + TimeUtil.formatDateToStr(new Date(), "yyyyMMddHHmmss");
        }
        boolean needCreateNewOne = isNeedCreateNewOne();
//        String filePath;
        if (needCreateNewOne) {
            // 若之前有文件 则转移
            if (StringHelp.isNotEmpty(currTempFilePath)) {
                FileUtil.createDir(dataDestDir);
                LogUtils.info("CT 文件超过最大数 开始转移 mv " + currTempFilePath + " " + dataDestDir);
                mvDataFile();
            }
            // 生成新的临时文件
            String temp = tempDir + File.separator + fileName + "." + fileType;
            currTempFilePath = FileUtil.checkAndReformDuplicateFileName(temp);
            FileExportUtil.createNewFile(currTempFilePath);
            currNum = 0;// 计数归零
        }
        File file = new File(currTempFilePath);
        if (!file.exists() || !file.isFile()) {
//            return new Message(Message.Type.ERROR, "创建单个文件" + filePath + "失败", filePath);
            return -1;
        }
        // 以json的格式输出到文件中
        int effectNum = FileExportUtil.exportJsonDataToFile(objList, currTempFilePath);
        if (effectNum < 0) {
            return -1;
        }
        currNum += effectNum;
        return effectNum;
    }

    /**
     * 检验是否可以执行导出动作
     *
     * @return
     */
    private boolean checkExportEnable() {
        if (StringHelp.isEmpty(tempDir) || StringHelp.isEmpty(dataDestDir) || !isEnableToFile) {
            return false;// 路径未指明
        }
        return true;
    }

    private boolean isNeedCreateNewOne() {
        // 如果还没有临时文件 则新建
        if (currTempFilePath == null || currTempFilePath.length() == 0) {
            LogUtils.info("CT AJ isNeedCreateNewOne:" + true + " currTempFilePath 为空");
            return true;
        }
        File file = new File(currTempFilePath);
        if (!file.exists()) {
            LogUtils.info("CT AJ isNeedCreateNewOne:" + true + " currTempFilePath 不存在" + currTempFilePath);
            return true;
        }
        boolean isNeed = (currNum >= maxNum) || currNum < 0;
        LogUtils.info("CT AJ isNeedCreateNewOne:" + isNeed + " currNum:" + currNum + " maxNum:" + maxNum);
        return isNeed;
    }

    public void mvDataFile() {
        if (StringHelp.isEmpty(currTempFilePath) || StringHelp.isEmpty(dataDestDir) || !isEnableToFile) {
            return;
        }
        ExecLinuxCMD.exec("mv " + currTempFilePath + " " + dataDestDir);
//        ExecLinuxCMD.exec("mv -f " + preFilePath + " " + theFilePath);
    }

    public void setTempDir(String tempDir) {
        this.tempDir = tempDir;
    }

    public void setDataDestDir(String dataDestDir) {
        this.dataDestDir = dataDestDir;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public void setEnableToFile(boolean enableToFile) {
        isEnableToFile = enableToFile;
    }

    public void currNumToZero() {
        this.currNum = 0;
    }
}
