package com.common.utils;

import java.io.File;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 话单数据处理工具包
 *
 */
public class App 
{
	private static Log log = LogFactory.getLog(App.class);
	public static final Properties prop = PropertyLoader.loadProperties(PathUtil.getProjectPath()+ File.separator +"system.properties");
	private static final String sm = "参数说明：\r\n1-校验话单文件格式并记录不合规文件；\r\n2-筛选不合规文件到目标目录；\r\n示例：java -jar TicketTools.jar 2 异常文件列表（第二个参数为要移动文件列表的txt文件名称）\r\n3-生成话单模板数据文件到目标目录；\r\n4-执行话单模板数据文件导入ES。 \r\n命令行示例：入库命令--java -jar TicketTools.jar 4 ";
}
