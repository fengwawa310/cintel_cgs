package com.mana.ticket;

import com.mana.ticket.model.CallRecord;
import com.mana.ticket.util.*;
import io.netty.util.internal.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

/**
 * 话单入库
 * @author 郑宁
 *
 */
public class TicketToES {

	private Logger logger = LoggerFactory.getLogger(TicketToES.class);

	private TransportClient client;
	
	private String esClusterName = "elasticsearch";
	private String esUserAndPwd = "elastic:bgavUKQZ5q5XtYwPfj9N";
	private String esIP = "127.0.0.1";
	private int esPort = 10050;
	
	private int allCount = 0,successCount = 0,failureCount = 0;
	
	public TicketToES() {
//		initEsClient();
//		
		String sourcepath = App.prop.getProperty("ticket.sourcepath");
		if(StringUtils.isBlank(sourcepath)) {
			logger.error("请配置ticket.sourcepath系统参数。");
			return;
		}
		
		this.findTicketFile(sourcepath);
		
		System.out.print("一共处理了" + allCount + "个文件，");
		System.out.print("成功" + successCount + "个文件，");
		System.out.println("失败" + failureCount + "个文件。");
//		
//		closeEsClient();
	}
	
	public TicketToES(String esClusterName, String esUserAndPwd,String esIP,int esPort) {
		if(!StringUtil.isNullOrEmpty(esClusterName)) {
			this.esClusterName = esClusterName;
		}
		if(!StringUtil.isNullOrEmpty(esUserAndPwd)) {
			this.esUserAndPwd = esUserAndPwd;
		}
		if(!StringUtil.isNullOrEmpty(esIP)) {
			this.esIP = esIP;
		}
		if(esPort > 0) {
			this.esPort = esPort;
		}
		
//		initEsClient();
	}
	
	
	private void findTicketFile(String filepath) {
		try {
			File file = new File(filepath);
			if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "/" + filelist[i]);
					if (!readfile.isDirectory()) {
						allCount++;
						
						String filename = filelist[i];
						String path = readfile.getPath();
						File fileReq = new File(path); // 遍历每一个文件
						//扩展名
						String newName = filename.substring(filename.lastIndexOf("."));
						if (".xls".equals(newName) || ".xlsx".equals(newName)) {
							this.ticketToEs(fileReq);
						}
					} else if (readfile.isDirectory()) {
						findTicketFile(filepath + "/" + filelist[i]);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("findTicketFile() Exception:" + e);
		}
	}
	
	private void initEsClient() {
		try {
			Settings settings = Settings.builder()
                .put("cluster.name", esClusterName)//设置ES实例的名称
                .put("xpack.security.transport.ssl.enabled", false)
                .put("xpack.security.user", esUserAndPwd)
                .put("client.transport.sniff", true)
                .build();

			TransportAddress node = new TransportAddress(InetAddress.getByName(esIP),esPort);
			client = new PreBuiltXPackTransportClient(settings);
			client.addTransportAddress(node);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void closeEsClient() {
		if(this.client != null)
			this.client.close();
	}

	/**
	 * 话单excel转es
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ExcelException
	 */
	private void ticketToEs(File file){
		try {
			LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
			fieldMap.put("本机号码","calling");
			fieldMap.put("对方号码","called");
			fieldMap.put("呼叫类型","calling_type");
			fieldMap.put("开始时间","start_time");
			fieldMap.put("通话时长(秒)","calling_time");
			fieldMap.put("通话地","calling_place");
			String[] uniqueFields = { "本机号码", "对方号码", "呼叫类型", "开始时间", "通话时长(秒)", "通话地" };
			
			FileInputStream in = new FileInputStream(file);
			List<CallRecord> callRecords = ExcelUtil.excelToList(in, "sheet1"
					, CallRecord.class, fieldMap, uniqueFields);
			
			ElasticSearchUtils.bulkInsertTicke(callRecords);
			
			successCount++;
			System.out.println(file.getAbsolutePath()+"。已处理文件数：" + successCount);
			
			logger.info("转es完成!");
		}catch(Exception e) {
			failureCount++;
			TxtUtil.fileChaseFW(App.prop.getProperty("ticket.targetpath")+"导入处理异常文件.txt",file.getAbsolutePath());
		}
	}

}
