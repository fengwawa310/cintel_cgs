package com.controller.task;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.common.utils.ElasticSearchUtils;
import com.common.utils.GetPropertiesUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.common.consts.Global;
import com.service.task.OracleService;

@Component
public class TimedTaskConroller {
	
	@Resource
	private OracleService oracleService;

	private static String blTaskSwitch=Global.getTask("blTaskSwitch");
	/*
	 * 笔录入mysql库开关：0：不入mysql ；1：入mysql
	 */
	private static String blIntoMySqlSwitch=Global.getTask("blIntoMySqlSwitch");
	/*
	 * 住宿信息入mysql库开关：0：不入mysql ；1：入mysql
	 */
	private static String hotelIntoMySqlSwitch=Global.getTask("hotelIntoMySqlSwitch");
	private static String hotelTaskSwitch=Global.getTask("hotelTaskSwitch");
	
	@Scheduled(cron = "${blTimeCron}")
	public void BiluDataSynch() {
//		笔录定时任务
		if("1".equals(blTaskSwitch)){
//			笔录定时任务启动
			Map<String,String> map= new HashMap<String,String>();
			map.put("blIntoMySqlSwitch", blIntoMySqlSwitch);
			System.out.println("笔录定时任务开始············"+blIntoMySqlSwitch);
//			map里面放的是查询条件，拉取数据用
			oracleService.findBlDataAndinsert(map);
		}
		
	}
	
	@Scheduled(cron = "${hotelTimeCron}")
	public void HotelTimeCron() {
		System.out.println("hotel定时任务进来了············");
		if("1".equals(hotelTaskSwitch)){
			Map<String,String> map= new HashMap<String,String>();
//			map里面放的是查询条件，拉取数据用
			System.out.println("酒店及住宿定时任务开始············"+hotelIntoMySqlSwitch);
			map.put("hotelIntoMySqlSwitch", hotelIntoMySqlSwitch);
			oracleService.findHotelDataAndInsert(map);
			//住宿同步es
			findAndInsertHotelPersonData(map);
		}
		
	}
	
	/**
	 * 酒店距离定时执行
	 * 1、先删除ep_hotel_distance之前所有数据
	 * 2、执行计算入库的sql
	 */
//	@Scheduled(cron = "${hotelDistanceTimeCron}")
//	public void HotelDistanceTimeCron() {
//		System.out.println("执行酒店距离计算");
//		oracleService.hotelDistanceTimeCron();
//	}

	@Scheduled(cron = "${vczrkjbxxCron}")
	public void vczrkjbxxCron() {
		System.out.println("同步 v_czrk_jbxx");
		SynchronousTask task = new SynchronousTask(1);
		Thread thread = new Thread(task);
		thread.start();
	}
	@Scheduled(cron = "${vczrkxxCron}")
	public void vczrkxxCron() {
		System.out.println("同步 v_czrkxx");
		SynchronousTask task = new SynchronousTask(2);
		Thread thread = new Thread(task);
		thread.start();
	}
	@Scheduled(cron = "${vwfzajxxbCron}")
	public void vwfzajxxbCron() {
		System.out.println("同步 v_wfz_ajxxb");
		SynchronousTask task = new SynchronousTask(3);
		Thread thread = new Thread(task);
		thread.start();
	}
	@Scheduled(cron = "${vwfzfzxxbCron}")
	public void vwfzfzxxbCron() {
		System.out.println("同步 v_wfz_fzxxb");
		SynchronousTask task = new SynchronousTask(4);
		Thread thread = new Thread(task);
		thread.start();
	}


	/*
	 * 获取oracle中ZHSJ_GNLK的数据
	 * 插入mysql数据库ep_hotel_person
	 */
	public void findAndInsertHotelPersonData(Map<String,String> mapCon) {
		Map<String, String> prosMap = GetPropertiesUtil.findProperties("resource/task.properties");
		String filedSql="";
		if(prosMap.get("personCcode")!=null&&!"".equals(prosMap.get("personCcode"))&&!"0".equals(prosMap.get("personCcode"))){
			filedSql=" 	AND CCODE > "+prosMap.get("personCcode");
		}
//		Map<String,String> map= new HashMap<>();
		int pageNum=0;
		int eachSearch=1000;
		while(true){
			try {
				String field ="CCODE, GCODE, NAME, NAMEPY, SEX, NATION, BDATE, IDTYPE, IDCODE, XZQH, ADDRESS, NOHOTEL, \n" +
						"    NOROOM, LTIME, LWAITER, ETIME, EWAITER, CARDTYPE, CARDNO, STIME, RTIME, CREATETIME, \n" +
						"    NUM, INPUTTIME, FLAG, RKSJ, SSPCS, REVSTATUS ";

				//sql
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT "+field+" FROM ( ");
				sql.append("	SELECT ROWNUM rnum, A .* FROM ( ");
				sql.append("		SELECT "+field);
				sql.append("		FROM V_GNLK WHERE 1 = 1 ");
				sql.append(filedSql);
				sql.append("		ORDER BY CCODE ASC ");
				sql.append("	) A ");
				sql.append("	WHERE ROWNUM <= (("+pageNum+"+1)*"+eachSearch+") ");
				sql.append(") A1 ");
				sql.append("WHERE A1.rnum  > ("+pageNum+"*"+eachSearch+") ");

				Integer columnCount= insertElasticSearch(sql.toString());
				if(columnCount<1000){
					break;
				}
				++pageNum;
			} catch (Exception e) {
				break;
			}
		}
	}

	/**
	 * 数据库信息
	 */
	public static String DRIVER_CLASSNAME = Global.getDb("oracle.gnlk.jdbc.driverClassName");
	public static String URL = Global.getDb("oracle.gnlk.jdbc.url");
	public static String USER = Global.getDb("oracle.gnlk.jdbc.username");
	public static String PASSWORD = Global.getDb("oracle.gnlk.jdbc.password");

	public static String CCODE = "0";

	public static Integer insertElasticSearch(String sql) throws Exception{
		Integer count=0;
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
						String object = rs.getObject(i)+"";
						rowData.put(md.getColumnName(i), object);//获取键名及值
					}
				}
				try {
					ElasticSearchUtils.insert("cgs_ep_hotel_person","cgs",rowData,rowData.get("CCODE").toString());
					++count;
					CCODE=rowData.get("CCODE").toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
			}
			System.out.println("住宿数据存储成功 CCODE="+CCODE);
			//把最近更新时间写入配置文件中
			Map<String, String> pros=new HashMap<>();
			pros.put("personCcode",CCODE);
			GetPropertiesUtil.updateProperties("resource/task.properties",pros);
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
		return count;
	}

}
