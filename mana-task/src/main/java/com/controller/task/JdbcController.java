package com.controller.task;

import com.common.consts.Global;
import com.common.utils.ElasticSearchUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询orcl和操作mysql
 * Created by weipc on 2018/5/22.
 */
public class JdbcController {
    /**
     * MySql数据库信息
     */
    public static String MYSQL_DRIVER_CLASSNAME = Global.getDb("jdbc.driver");
    public static String MYSQL_URL = Global.getDb("jdbc.url");
    public static String MYSQL_USER = Global.getDb("jdbc.username");
    public static String MYSQL_PASSWORD = Global.getDb("jdbc.password");

    /**
     * ORCL的CIDRAC数据库信息
     */
    public static String CIDRAC_DRIVER_CLASSNAME = Global.getDb("oracle.jdbc.driverClassName");
    public static String CIDRAC_URL = Global.getDb("oracle.jdbc.url");
    public static String CIDRAC_USER = Global.getDb("oracle.jdbc.username");
    public static String CIDRAC_PASSWORD = Global.getDb("oracle.jdbc.password");

    /**
     * ORCL的LYWBDB数据库信息
     */
    public static String LYWBDB_DRIVER_CLASSNAME = Global.getDb("oracle.gnlk.jdbc.driverClassName");
    public static String LYWBDB_URL = Global.getDb("oracle.gnlk.jdbc.url");
    public static String LYWBDB_USER = Global.getDb("oracle.gnlk.jdbc.username");
    public static String LYWBDB_PASSWORD = Global.getDb("oracle.gnlk.jdbc.password");


    /**
     * 查询MYSQL
     * @param sql
     */
    public static List<Map<String,Object>> selectMySql(String sql){
        List<Map<String,Object>> list = new ArrayList<>();
        //声明Connection
        Connection conn = null;
        //声明PreparedStatement
        PreparedStatement pstat = null;
        //声明结果集ResultSet
        ResultSet rs = null;
        try {
            //第一步：加载驱动类
            Class.forName(MYSQL_DRIVER_CLASSNAME);
            //第二步：获取数据库连接Connection
            conn = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
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
                        rowData.put(md.getColumnName(i), object);//获取键名及值
                    }
                }
                list.add(rowData);
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
        return list;
    }
    /**
     * 查询CIDRAC
     * @param sql
     */
    public static List<Map<String,Object>> selectCIDRAC(String sql){
        List<Map<String,Object>> list = new ArrayList<>();
        //声明Connection
        Connection conn = null;
        //声明PreparedStatement
        PreparedStatement pstat = null;
        //声明结果集ResultSet
        ResultSet rs = null;
        try {
            //第一步：加载驱动类
            Class.forName(CIDRAC_DRIVER_CLASSNAME);
            //第二步：获取数据库连接Connection
            conn = DriverManager.getConnection(CIDRAC_URL, CIDRAC_USER, CIDRAC_PASSWORD);
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
                        rowData.put(md.getColumnName(i), object);//获取键名及值
                    }
                }
                list.add(rowData);
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
        return list;
    }
    /**
     * 查询LYWBDB
     * @param sql
     */
    public static List<Map<String,Object>> selectLYWBDB(String sql){
        List<Map<String,Object>> list = new ArrayList<>();
        //声明Connection
        Connection conn = null;
        //声明PreparedStatement
        PreparedStatement pstat = null;
        //声明结果集ResultSet
        ResultSet rs = null;
        try {
            //第一步：加载驱动类
            Class.forName(LYWBDB_DRIVER_CLASSNAME);
            //第二步：获取数据库连接Connection
            conn = DriverManager.getConnection(LYWBDB_URL, LYWBDB_USER, LYWBDB_PASSWORD);
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
                        rowData.put(md.getColumnName(i), object);//获取键名及值
                    }
                }
                list.add(rowData);
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
        return list;
    }

    /**
     * 向MYSQL数据库插入数据
     * @param sql
     */
    public static void insertMysql(String sql){
        //声明Connection
        Connection conn = null;
        //声明PreparedStatement
        PreparedStatement pstat = null;
        try {
            //第一步：加载驱动类
            Class.forName(MYSQL_DRIVER_CLASSNAME);
            //第二步：获取数据库连接Connection
            conn = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
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
