package com.controller.task;

import com.common.consts.Global;
import com.common.utils.ElasticSearchUtils;
import com.common.utils.GetPropertiesUtil;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by weipc on 2018/3/13.
 */
public class TimerTask implements Runnable {
    /**
     * 数据库信息
     */
    public static String DRIVER_CLASSNAME = Global.getDb("jdbc.driver");
    public static String URL = Global.getDb("jdbc.url");
    public static String USER = Global.getDb("jdbc.username");
    public static String PASSWORD = Global.getDb("jdbc.password");
    public static String NEWDATE = "0000-00-00 00:00:00";

    /**
     * 定时任务
     */
    @Override
    public void run() {
        Map<String, String> map = GetPropertiesUtil.findProperties("resource/task.properties");
        NEWDATE = map.get("updateDate");
        System.out.println("\n\nES定时任务开始。。。\n\n");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        //写入案件
        insertOrUpdate("et_case","cgs_et_case","cgs");
        //写入警情
        insertOrUpdate("et_alarm","cgs_et_alarm","cgs");
        //写入重点人员
        insertOrUpdate("et_suspect","cgs_et_suspect","cgs");
        //写入嫌疑人
        insertOrUpdate("ap_staff","cgs_ap_staff","cgs");
        NEWDATE =date;
        //把最近更新时间写入配置文件中
        Map<String, String> pros=new HashMap<>();
        pros.put("updateDate",date);
        GetPropertiesUtil.updateProperties("resource/task.properties",pros);
        System.out.println("\n\nES定时任务结束。。。\n\n");
    }

    /**
     * 从数据库中查出数据并写入es
     * @param tabName 表名
     * @param index 索引
     * @param type 类型
     */
    public void insertOrUpdate(String tabName,String index, String type){
        //声明Connection
        Connection conn = null;
        //声明PreparedStatement
        PreparedStatement pstat = null;
        //声明结果集ResultSet
        ResultSet rs = null;
        try {
            //第一步：加载驱动类
            Class.forName(DRIVER_CLASSNAME);
            //第二步：获取数据库连接Connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //sql
            String sql = "SELECT * FROM "+tabName+ " WHERE MODIFY_TIME>= '"+NEWDATE+"'";
            //第三步：获取PreparedStatement，充当信使的角色，向数据库发送sql语句
            pstat = conn.prepareStatement(sql);
            //第四步：向数据库发送sql语句，并且接收返回的数据列表
            rs = pstat.executeQuery();
            //第五步：解读结果集
            ResultSetMetaData md = rs.getMetaData();//获取键名
            int columnCount = md.getColumnCount();//获取行的数量
            while (rs.next()) {
                Map<String,Object> rowData = new HashMap<>();//声明Map
                for (int i = 1; i <= columnCount; i++) {
                    if(rs.getObject(i)!=null&&!"".equals(rs.getObject(i))&&!"null".equals(rs.getObject(i))){
                        String object = rs.getObject(i)+"";
//                        object=object.replace(".0","");
                        rowData.put(md.getColumnName(i), object);//获取键名及值
                    }
                }
                ElasticSearchUtils.insert(index,type,rowData,rowData.get("ID").toString());
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭资源，注意关闭顺序
            try {
                //关闭ResultSet
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                //关闭PreparedStatement
                pstat.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                //关闭Connection
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //向数据库中数据插入方法
    public static void insertMysql(String sql){
        //声明Connection
        Connection conn = null;
        //声明PreparedStatement
        PreparedStatement pstat = null;
        try {
            //第一步：加载驱动类
            Class.forName(DRIVER_CLASSNAME);
            //第二步：获取数据库连接Connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //第三步:获取PreparedStatement(充当信使的角色，专门向数据库发送信息)
            pstat = conn.prepareStatement(sql);
            //第四步：执行sql语句
            pstat.executeUpdate();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                //关闭PreparedStatement
                pstat.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                //关闭Connection
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
