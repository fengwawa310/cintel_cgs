package com.mana.ticket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

import org.apache.commons.lang.StringUtils;

import com.mana.ticket.util.FileUtil;
import com.mana.ticket.util.TxtUtil;

import jxl.Sheet;
import jxl.Workbook;

/**
 * 话单文件筛选工具
 * @author 郑宁
 *
 */
public class TicketScreen {
		//列名称集合
		private HashSet<String> columnSet = new HashSet<String>();
		private String filePath = null;
		int allCount = 0 ,SuccessCount = 0,failureCount = 0;
		
		public static void main(String[] args){
			TicketScreen re = new TicketScreen();
			String path = args.length == 1 ? args[0]:null;
			re.findExcel(path);
		}
		
		public void findExcel(String path) {
			String mubiaoURL = path ==null || path.isEmpty() ? App.prop.getProperty("ticket.sourcepath"):path;
			File file = new File(mubiaoURL);
			if (!file.isDirectory()) {
				System.out.println("文件");
				System.out.println("path=" + file.getPath());
				System.out.println("absolutepath=" + file.getAbsolutePath());
				System.out.println("name=" + file.getName());
			} else if (file.isDirectory()) {
				readfile_(mubiaoURL);
				System.out.print("一共执行了" + allCount + "文件,");
				System.out.print("成功" + SuccessCount + "文件,");
				System.out.println("失败" + failureCount + "文件。");
				if (columnSet.size() != 0) {
					writeToTXT(columnSet.toString(), new File(App.prop.getProperty("ticket.targetpath")+"处理结果.txt"));
					System.out.println("集合对象" + columnSet.toString());
					System.out.println("集合对象" + columnSet.size() + "个");
				} else  {
					TxtUtil.fileChaseFW(App.prop.getProperty("ticket.targetpath")+"读取异常文件.txt", filePath);
				}
			}
		}


		/**
		 * 读取某个文件夹下的所有文件
		 */
		public boolean readfile_(String filepath) {
			try {
				
				File file = new File(filepath);
				if (file.isDirectory()) {
					String[] filelist = file.list();
					for (int i = 0; i < filelist.length; i++) {
						File readfile = new File(filepath + "/" + filelist[i]);
						if (!readfile.isDirectory()) {
							String filename = filelist[i];
							String path = readfile.getPath();
							File fileReq = new File(path); // 遍历每一个文件
							//文件名
							String REQNAME = filename.substring(0, filename.lastIndexOf("."));
							//扩展名
							String newName = filename.substring(filename.lastIndexOf("."));
							if (".xls".equals(newName) || ".xlsx".equals(newName)) {
								String readExcelT = readExcel(fileReq, filename);
								System.out.println(readExcelT+">>>>>>>>"+path);
								allCount ++ ;
								if(StringUtils.isNotBlank(readExcelT)) {
									columnSet.addAll(Arrays.asList(readExcelT.toString().split(",")));
								}
							
							}
						} else if (readfile.isDirectory()) {
							readfile_(filepath + "/" + filelist[i]);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("readfile() Exception:" + e.getMessage());
			}
			return true;
		}
		
		private void writeToTXT(String desc,File file) {
			try {
				TxtUtil.writeTxtFile(desc, file);
				System.out.println(file.getName() + "生成成功");
			} catch (Exception e) {
				 e.printStackTrace();
			}

		}


		private String readExcel(File file, String filename) {
			try {
				filePath  = file.getAbsolutePath();
				// 创建输入流，读取Excel
				InputStream is = new FileInputStream(file.getAbsolutePath());
				// jxl提供的Workbook类
				Workbook wb = Workbook.getWorkbook(is);
				// Excel的页签数量
				int sheet_size = wb.getNumberOfSheets();
				StringBuffer sBuffer = new StringBuffer();
				for (int index = 0; index < sheet_size; index++) {
					// 每个页签创建一个Sheet对象
					Sheet sheet = wb.getSheet(index);
					int count_max = 0; // 最大列数
					//计算最大列数
					int rows = sheet.getRows();
					if(rows == 0)
						continue;
//					if(rows < 8) {//处理空表格
//						TxtUtil.fileChaseFW(errorNamePath,file.getAbsolutePath());
//						return null;
//					}
					if(rows == 1) {
						TxtUtil.fileChaseFW(App.prop.getProperty("ticket.targetpath")+"只一行记录文件.txt",file.getAbsolutePath());
						return "一行记录";
					}
					for (int i = 0; i < rows; i++) {
						int count_ = 0;
						for (int j = 0; j < sheet.getColumns(); j++) { // 遍历所有列 9
							String cellinfo = sheet.getCell(j, i).getContents(); // 获取单元格
							if (!cellinfo.isEmpty()) {
								count_++;
							}
						}
						if(count_ > count_max){
							count_max  = count_;
						}
					}
					
					for (int i = 0; i < rows; i++) { // 遍历所有行数  
						int count_1 = 0;
						String cellinfo = null; // 自定义每个单元格的字段
						sBuffer.delete(0,sBuffer.length());//删除所有的数据
						for (int j = 0; j < sheet.getColumns(); j++) { // 遍历所有列  
							cellinfo = sheet.getCell(j, i).getContents().trim(); // 获取单元格
							String  check = "(^[0-9]+$)";  
							if (cellinfo != null && !cellinfo.isEmpty() && !cellinfo.matches(check)  ) { // 匹配数值直接过滤
								count_1++;
								sBuffer.append(cellinfo +",");
							}
						}
						if (count_1 == count_max) {
							break;
						}else {
							sBuffer.delete(0,sBuffer.length());//删除所有的数据
						}
					}
				}
				is.close();
				if(sBuffer.toString().contains("www.10010.com")) {
					TxtUtil.fileChaseFW(App.prop.getProperty("ticket.targetpath"),file.getAbsolutePath());
					return null;
				}else if(sBuffer.toString().startsWith("查询时段：")) {
					TxtUtil.fileChaseFW(App.prop.getProperty("ticket.targetpath")+"空文件.txt",file.getAbsolutePath());
				}else if(sBuffer.toString().startsWith("成员号码,")) {
					TxtUtil.fileChaseFW(App.prop.getProperty("ticket.targetpath")+"成员号码.txt",file.getAbsolutePath());
				}
				
				SuccessCount ++ ;
				
//				if(!"".equals(sBuffer.toString()) && sBuffer.toString() != null) {
//					TxtUtil.fileChaseFW(targetPath+"筛选20180314002.txt",file.getAbsolutePath());
//				}
				
				return sBuffer.toString();
			} catch (Exception e) {
				//e.printStackTrace();
				System.out.println(" 读取异常文件 --"+file.getAbsolutePath());
				failureCount++;
				TxtUtil.fileChaseFW(App.prop.getProperty("ticket.targetpath")+"读取异常文件.txt",file.getAbsolutePath());
			}
			return null;
		
		}
		
		//移动
		public void move(String filename) {
			if(StringUtils.isBlank(filename) || filename.contains(".")) {
				System.out.println("请输入合规的第二个参数");
				return;
			}
			try {
				InputStreamReader reader = new InputStreamReader(new FileInputStream(new File(App.prop.getProperty("ticket.targetpath")+ filename +".txt")), "utf-8");
				BufferedReader br = new BufferedReader(reader);
				String s = null;
				while ((s = br.readLine()) != null) {
					FileUtil.moveTotherFolders(s, "", App.prop.getProperty("ticket.targetpath") + "\\"+filename+"\\");
//					System.out.println(s);
				}
				br.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
}
