package com.mana.ticket;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mana.ticket.model.TitleMapper;
import com.mana.ticket.util.PathUtil;
import com.mana.ticket.util.PropertyLoader;
import com.mana.ticket.util.TxtUtil;

/**
 * 话单数据处理工具包
 *
 */
public class App 
{
	
	private static Log log = LogFactory.getLog(App.class);
	
	public static final Properties prop = PropertyLoader.loadProperties(PathUtil.getProjectPath()+ File.separator +"system.properties");
	
	private static final String sm = "参数说明：\r\n1-校验话单文件格式并记录不合规文件；\r\n2-筛选不合规文件到目标目录；\r\n示例：java -jar TicketTools.jar 2 异常文件列表（第二个参数为要移动文件列表的txt文件名称）\r\n3-生成话单模板数据文件到目标目录；\r\n4-执行话单模板数据文件导入ES。 \r\n命令行示例：入库命令--java -jar TicketTools.jar 4 ";
    

	
	public static void main( String[] args )
    {
//		new CreateTemplateData().findExcel(null);
//		new TicketToES();

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date starttime = new Date();
		if(args.length == 0 || args.length > 1) {
    		if(args.length == 2 && "2".equals(args[0])) {
    			System.out.println(format.format(starttime) +"开始校验话单文件格式并记录不合规文件........................");
    			TicketScreen re = new TicketScreen();
    			re.move(args[1]);

    			Date endtime = new Date();
    			System.out.println(format.format(new Date()) +"结束校验话单文件格式并记录不合规文件。耗时(分)：" + (endtime.getTime() - starttime.getTime())/(1000 * 60));
    		}else {
    			System.out.println(sm);
    		}
    	}else if(args.length == 1) {
    		String input = args[0];
    		if("1".equals(input)) {
    			System.out.println(format.format(starttime) +"开始校验话单文件格式并记录不合规文件........................");
    			TicketScreen re = new TicketScreen();
    			re.findExcel(null);
    			
    			Date endtime = new Date();
    			System.out.println(format.format(new Date()) +"结束校验话单文件格式并记录不合规文件。耗时(分)：" + (endtime.getTime() - starttime.getTime())/(1000 * 60));
    		}else if("3".equals(input)) {
    			System.out.println(format.format(starttime) +"开始生成话单模板数据文件........................");
    			CreateTemplateData re1 = new CreateTemplateData();
    			re1.findExcel(null);

    			Date endtime = new Date();
    			System.out.println(format.format(new Date()) +"生成话单模板数据文件完成。耗时(分)：" + (endtime.getTime() - starttime.getTime())/(1000 * 60));
    		}else if("4".equals(input)) {
    			System.out.println(format.format(starttime) +"开始话单模板数据文件导入ES......................");
    			new TicketToES();

    			Date endtime = new Date();
    			System.out.println(format.format(new Date()) +"结束话单模板数据文件导入ES。耗时(分)：" + (endtime.getTime() - starttime.getTime())/(1000 * 60));
    		}else {
    			System.out.println(sm);
    		}
    	}
    	
    }
}
