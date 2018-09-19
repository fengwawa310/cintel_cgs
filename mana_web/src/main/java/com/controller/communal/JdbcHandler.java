package com.controller.communal;

import com.common.consts.Global;
import com.common.utils.ElasticSearchUtils;
import net.sf.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weipc on 2018/3/16.
 */
public class JdbcHandler {
    /**
     * 数据库信息
     */
    public static String DRIVER_CLASSNAME = Global.getDb("jdbc.driver");
    public static String URL = Global.getDb("jdbc.url");
    public static String USER = Global.getDb("jdbc.username");
    public static String PASSWORD = Global.getDb("jdbc.password");

    /**
     * 从数据库中查出数据并写入es
     * @param sql sql语句
     */
    public static JSONObject insert(String sql){
        JSONObject json =new JSONObject();
        json.put("flag",false);
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
            json.put("msg","导入成功");
            json.put("flag",true);
        } catch (ClassNotFoundException e) {
            json.put("msg","导入异常："+e);
            json.put("flag",false);
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            json.put("msg","导入异常："+e);
            json.put("flag",false);
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关闭资源，注意关闭顺序
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
        return json;
    }

    public static List<Map<String,Object>> selectTab(String sql){
        List<Map<String,Object>> list = new ArrayList<>();
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
                        String object = rs.getObject(i).toString();
                        object=object.replace(".0","");
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

}
