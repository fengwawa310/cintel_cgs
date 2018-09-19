package com.controller.task;

import com.common.consts.Global;
import com.common.utils.IDGenerator;
import com.service.task.DockingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: sky
 * @Description:分类结果保存服务：定时触发（默认10分钟），识读分类结果文件，逐行读取写入数据库表
 * @Date: Create in 下午3:57 2018/5/17
 */
@Component
public class ResultSaveController {

    @Resource
    private DockingService dockingService;

    /**
     * @Author: sky
     * @Description: 存储案件分类结果
     * @Date: 下午5:26 2018/5/17
     * @param:
     */
    @Scheduled(cron = "${saveTimeCron}")// 每10分钟执行一次
    public void resultSve() {
        //定义文件编码为UTF-8
        String encoding = "UTF-8";
        //获取文件列表
        List<String> filePaths = getSortFileNames();
        System.out.println("--filePaths的个数--"+filePaths.size());
        //循环处理文件操作
        for (String filePath : filePaths){
            //根据文件路径获取文件
            System.out.println("--filePath--"+filePath);
            if (filePath.endsWith(".TXT")){
                File fileReq = new File(filePath);
                InputStreamReader read = null;
                try {
                    read = new InputStreamReader(new FileInputStream(fileReq), encoding);
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTXT = null;
                    //按照行读取数据
                    while ((lineTXT = bufferedReader.readLine()) != null) {
                        HashMap<String, Object> paramMap = new HashMap<>();
                        //拆分一级"|"数组
                        String arr[] = lineTXT.split("\\|");
                        //案件或者警情编号
                        String CASE_NO = arr[0];
                        //案件分类集合
                        String[] sorts = arr[1].split("#");
                        //身份证集合
                        String[] idNums = arr[2].split("#");

                        //案件分类数据插入集合
                        List<Map<String, Object>> CS_SET = new ArrayList<>();

                        StringBuffer sql = new StringBuffer();
                        sql.append("insert into  ap_cassclassify (ID, CASE_NO, CS_DIME,CS_NAME,IS_CONFIRM,CREAT_TIME,MODIFY_TIME) values \n" );

                        //遍历分类集合
                        for(int i=0;i<sorts.length;i++){
                            Map<String,Object> caseMap = new HashMap<>();

                            //主键ID
                            String ID = IDGenerator.getorderNo();
                            //分类维度
                            String CS_DIME = sorts[i].split(",")[0];
                            //类别名称
                            String CS_NAME = sorts[i].split(",")[1];

                            caseMap.put("ID",ID);
                            caseMap.put("CASE_NO",CASE_NO);
                            caseMap.put("CS_DIME",CS_DIME);
                            caseMap.put("CS_NAME",CS_NAME);

                            if (i < (sorts.length-1)){
                                sql.append("('"+ID+"','"+CASE_NO+"','"+CS_DIME+"','"+CS_NAME+"',0,now(),now()),\n");
                            }else {
                                sql.append("('"+ID+"','"+CASE_NO+"','"+CS_DIME+"','"+CS_NAME+"',0,now(),now())");
                            }

                            CS_SET.add(caseMap);
                        }

                        //执行案件分类数据插入操作
                        if (CS_SET.size()>0){
//                        dockingService.caseClassifyInsert(CS_SET);
                            System.out.println(sql.toString());
                            TimerTask.insertMysql(sql.toString());
                        }

                        //案件身份证号码数据插入集合
                        List<Map<String, Object>> IDNUM_SET = new ArrayList<>();

                        StringBuffer idSql = new StringBuffer();
                        idSql.append("insert into ap_cassidnums (ID, CASE_NO, IDNUM,IS_CONFIRM,CREAT_TIME,MODIFY_TIME) values \n");

                        //遍历分类集合
                        for(int i=0;i<idNums.length;i++){
                            Map<String,Object> idNumMap = new HashMap<>();

                            //主键ID
                            String ID = IDGenerator.getorderNo();
                            //身份证号
                            String IDNUM = idNums[i];
                            idNumMap.put("ID",ID);
                            idNumMap.put("CASE_NO",CASE_NO);
                            idNumMap.put("IDNUM",IDNUM);

                            if (i < (idNums.length-1)){
                                idSql.append("('"+ID+"','"+CASE_NO+"','"+IDNUM+"',0,now(),now()),\n");
                            }else {
                                idSql.append("('"+ID+"','"+CASE_NO+"','"+IDNUM+"',0,now(),now())");
                            }

                            IDNUM_SET.add(idNumMap);
                        }
                        //执行身份证号数据插入操作
                        if (IDNUM_SET.size()>0){
//                        dockingService.caseIdNumInsert(IDNUM_SET);
                            System.out.println(idSql.toString());
                            TimerTask.insertMysql(idSql.toString());
                        }
                    }

                    fileReq.delete();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     * @Author: sky
     * @Description:
     * @Date: 下午4:09 2018/5/17
     * @param: path 读取文件夹的路径
     */
    //方法getFiles根据指定路径获取所有的文件
    public ArrayList<File> getFiles(String path) throws Exception {
        //目标集合fileList
        ArrayList<File> fileList = new ArrayList<File>();
        //获取目标路径的文件夹
        File file = new File(path);
        //判断是否为文件夹
        if(file.isDirectory()){
            //获取文件列表
            File[] files = file.listFiles();
            for(File fileIndex : files){
            //如果这个文件是目录，则进行递归搜索
                if(fileIndex.isDirectory()){
                    getFiles(fileIndex.getPath());
                }else {
            //如果文件是普通文件，则将文件句柄放入集合中
                    fileList.add(fileIndex);
                }
            }
        }
        return fileList;
    }


    /**
     * @Author: sky
     * @Description:获取当前目录下的文件  并按照时间由老到新的顺序排序
     * @Date: 下午4:57 2018/5/17
     * @param:
     */
    public List<String> getSortFileNames(){

        List<String> fileNames = new ArrayList<>();
        //获取当前文件夹下的所有文件
        List<File> files = new ArrayList<>();
        //配置文件中的文件夹路径
        String dirPath = Global.getTask("resultDirPath");
        System.out.println("--resultDirPath--"+dirPath);
        //文件的前半段路径
        String prePath = "";
        try {
            files = getFiles(dirPath);
//            //前半段文件路径
//            prePath = files.get(0).getPath();
//            System.out.println(prePath);

            for(int i=0;i < files.size();i++){
                //获取文件路径
                String currentPath = files.get(i).getPath();
                System.out.println(currentPath);
//                //获取除.TXT以外的文件名称
//                String fileName = currentPath.substring(currentPath.lastIndexOf("/")+1).split(".")[0];
//                System.out.println(fileName);

                fileNames.add(currentPath);
            }
            //因为文件名称为时间戳，进行排序 由小到大 时间由老到新
            Collections.sort(fileNames,new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if(o1 == null || o2 == null){
                        return -1;
                    }
                    if(o1.length() > o2.length()){
                        return 1;
                    }
                    if(o1.length() < o2.length()){
                        return -1;
                    }
                    if(o1.compareTo(o2) > 0){
                        return 1;
                    }
                    if(o1.compareTo(o2) < 0){
                        return -1;
                    }
                    if(o1.compareTo(o2) == 0){
                        return 0;
                    }
                    return 0;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileNames;
    }


}
