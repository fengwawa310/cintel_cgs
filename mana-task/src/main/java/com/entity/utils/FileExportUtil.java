package com.entity.utils;

import com.common.enums.Message;
import com.common.utils.FileUtil;
import com.common.utils.LogUtils;
import com.common.utils.StringHelp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.List;

/**
 * Created by Administrator on 2017/12/19.
 */
public class FileExportUtil {

    /**
     * @param fileName 只是文件名 不包含路径和后缀名
     * @param fileType 文件类型
     * @param destDir  文件在那个目录下生成 路径结尾不需要 /
     * @return
     */
    public static Message createNewFile(String fileName, FileType fileType, String destDir) {
        String theFilePath = destDir + File.separator + fileName + "." + fileType.getTypeName();
        return createNewFile(theFilePath);
    }

    /**
     * 生成一个文件
     *
     * @param filePath 一个包含文件名的完整路径
     * @return
     */
    public static Message createNewFile(String filePath) {
        if (StringHelp.isEmpty(filePath)) {// 文件路径不能为空
            return new Message(Message.Type.ERROR, "创建单个文件" + filePath + "失败，路径为空！", null);
        }
        if (filePath.endsWith(File.separator)) {// 如果传入的文件名是以文件分隔符结尾的，则说明此File对象是个目录而不是文件
            LogUtils.warn("创建单个文件" + filePath + "失败，目标文件不能为目录！");
            return new Message(Message.Type.ERROR, "创建单个文件" + filePath + "失败，目标文件不能为目录！", filePath); // 因为不是文件所以不可能创建成功，则返回错误
        }
//        File file = new File(filePath); // 根据指定的文件名创建File对象
//        if (file.exists()) { // 文件已存在 重构文件名称
//
//            LogUtils.warn("创建单个文件" + filePath + "失败，目标文件已存在！");
//            return new Message(Message.Type.ERROR, "创建单个文件" + filePath + "失败，目标文件已存在！", filePath); // 如果存在，则不需创建则返回错误
//        }
        filePath = FileUtil.checkAndReformDuplicateFileName(filePath);
        File file = new File(filePath); // 根据指定的文件名创建File对象
        // 判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            // 如果目标文件所在的文件夹不存在，则创建父文件夹
            LogUtils.info("创建" + file.getName() + "所在目录不存在，正在创建！");
            if (!file.getParentFile().mkdirs()) {// 判断父文件夹是否存在，如果存在则表示创建成功否则不成功
                LogUtils.warn("创建目标文件所在的目录失败！");
                return new Message(Message.Type.ERROR, "创建目标文件所在的目录失败！", filePath);
            }
        }
        // 创建目标文件
        try {
            if (file.createNewFile()) {// 调用createNewFile方法，判断创建指定文件是否成功
                LogUtils.info("创建单个文件" + filePath + "成功！");
                return new Message(Message.Type.SUCCESS, "创建成功！地址为：" + filePath, filePath);
            } else {
                LogUtils.warn("创建单个文件" + filePath + "失败！");
                return new Message(Message.Type.ERROR, "创建单个文件" + filePath + "失败！", filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.warn("创建单个文件" + filePath + "失败！");
            return new Message(Message.Type.ERROR, "创建文件" + filePath + "失败！" + e.getMessage(), filePath);
        }
    }

    /**
     * 向一确认存在的文件输入JSON内容
     *
     * @param objList
     * @param filePath
     * @param <T>
     * @return -1 导出失败 >-1 成功导出的数据数目
     */
    public static <T> int exportJsonDataToFile(List<T> objList, String filePath) {
        if (objList == null || objList.isEmpty()) {
//            return new Message(Message.Type.WORNING, "没有数据需要导出！", filePath);
            return -1;
        }
        File file = new File(filePath);
        // list转化为json
        JSONArray jsonArray = JsonUtil.listToJson(objList);
        RandomAccessFile randomFile = null;
        int total = 0;
        try {
            randomFile = new RandomAccessFile(file, "rw");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String str = jsonObject.toString() + "\r\n";
//                out.write(str.getBytes("utf-8"));
                // 文件长度，字节数
                long fileLength = randomFile.length();
                //将写文件指针移到文件尾。
                randomFile.seek(fileLength);
                randomFile.write(str.getBytes("utf-8"));
                total++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
//            return new Message(Message.Type.WORNING, "数据导出失败！", e.toString());
            return -1;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
//            return new Message(Message.Type.WORNING, "数据导出失败！", e.toString());
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
//            return new Message(Message.Type.WORNING, "数据导出失败！", e.toString());
            return -1;
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        return new Message(Message.Type.SUCCESS, "导出成功！导出地址为：" + filePath + "，请注意查看！", filePath);
        return total;
    }
}
