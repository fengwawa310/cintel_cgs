package com.mana.ticket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mana.ticket.model.TitleMapper;
import com.mana.ticket.util.PathUtil;
import com.mana.ticket.util.PropertyLoader;
import com.mana.ticket.util.TxtUtil;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONObject;

/**
 * 生成话单模板数据
 * 
 * @author 郑宁
 *
 */
public class CreateTemplateData {
	
	private static Log log = LogFactory.getLog(CreateTemplateData.class);
	
	private static final List<TitleMapper> titlemapper = PropertyLoader.loadTitleMapper();
	
	private static final String[] title = { "本机号码", "对方号码", "呼叫类型", "开始时间", "通话时长(秒)", "通话地" };

	private String filePath = null;
	
	int allCount = 0, SuccessCount = 0, failureCount = 0, Lie = 0, allHang = 0, hang = 0;
	
	private Map<String,TitleMapper> newTitleMapper;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		CreateTemplateData re = new CreateTemplateData();
		String path = args.length == 1 ? args[0] : null;
		re.findExcel(path);
	}
	public void findExcel(String path){
		String mubiaoURL = path == null || path.isEmpty() ? App.prop.getProperty("ticket.sourcepath"): path;
		File file = new File(mubiaoURL);
		System.out.println(mubiaoURL);
		if (file.isDirectory()) {
			readfile_(mubiaoURL);
			System.out.println("一共执行了" + allCount + "文件");
			System.out.println("成功" + SuccessCount + "文件");
			System.out.println("失败" + failureCount + "文件");
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
						File fileReq = new File(path); 
						// 扩展名
						String newName = filename.substring(filename.lastIndexOf("."));
						if (".xls".equals(newName) || ".xlsx".equals(newName)) {
							readExcel(fileReq, filename);
							allCount++;
						}
					} else if (readfile.isDirectory()) {
						readfile_(filepath + "/" + filelist[i]);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("readfile() Exception:" + e.getMessage());
		}
		return true;
	}

	public String readExcel(File fileReq, String filename) {
		filePath = fileReq.getAbsolutePath();
		String spath = App.prop.getProperty("ticket.sourcepath");
		String tpath = App.prop.getProperty("ticket.targetpath");
		String npath = filePath.replace(spath, tpath);
		//这里应该循环生成
		File fileNew = new File(npath);//+ fileReq.getName()
		if (!fileNew.getParentFile().exists()) {
			fileNew.getParentFile().mkdirs();
		}
		try {
			fileNew.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			// 先创建新excel
			// 创建工作簿
			WritableWorkbook wbb = Workbook.createWorkbook(fileNew);
			// 创建sheet
			WritableSheet sheetwb = wbb.createSheet("sheet1", 0);
			Label label = null;
			// 设置标题行
			for (int i = 0; i < title.length; i++) {
				label = new Label(i, 0, title[i]);
				sheetwb.addCell(label);
			}
			//
			newTitleMapper = new HashMap<>();
			// 读取源文件
			filePath = fileReq.getAbsolutePath();
			// 创建输入流，读取Excel
			InputStream is = new FileInputStream(fileReq.getAbsolutePath());
			// jxl提供的Workbook类
			Workbook wb = Workbook.getWorkbook(is);
			// Excel的页签数量
			int sheet_size = wb.getNumberOfSheets();
			StringBuffer sBuffer = new StringBuffer();
			for (int index = 0; index < sheet_size; index++) {
				// 每个页签创建一个Sheet对象
				Sheet sheet = wb.getSheet(index);
				int count_max = 0; // 最大列数
				// 计算最大列数
				int rows = sheet.getRows();
				if (rows == 0)
					continue;
				if (rows == 1) {
					TxtUtil.fileChaseFW(App.prop.getProperty("ticket.targetpath") + "一行记录.txt",
							fileReq.getAbsolutePath());
					return "一行记录";
				}
				for (int i = 0; i < rows; i++) {
					int count_ = 0;
					for (int j = 0; j < sheet.getColumns(); j++) { // 遍历所有列
						String cellinfo = sheet.getCell(j, i).getContents(); // 获取单元格
						if (!cellinfo.isEmpty()) {
							count_++;
						}
					}
					if (count_ > count_max) {
						count_max = count_;
					}
				}
				for (int i = 0; i < rows; i++) { // 遍历所有行数
					int count_1 = 0;
					String cellinfo = null;  
					sBuffer.delete(0, sBuffer.length());
					for (int j = 0; j < sheet.getColumns(); j++) { // 遍历所有列
						cellinfo = sheet.getCell(j, i).getContents().trim(); // 获取单元格
						String check = "(^[0-9]+$)";
						if (cellinfo != null && !cellinfo.isEmpty() && !cellinfo.matches(check)) { // 匹配数值直接过滤
							count_1++;
							sBuffer.append(cellinfo + ",");
							hang = i;
						}
					}
					if (count_1 == count_max) {
						break;
					} else {
						sBuffer.delete(0, sBuffer.length());
					}
				}
				
				
				String cellContent = null;
				String titleName = null;
				for (int q = hang + 1; q < sheet.getRows(); q++) {
					for (int q1 = 0; q1 < sheet.getColumns(); q1++) { // 遍历所有列
						cellContent = sheet.getCell(q1, q).getContents().trim(); // 获取单元格
						titleName = sheet.getCell(q1, hang).getContents().trim();
						if(StringUtils.isBlank(titleName) || StringUtils.isBlank(cellContent))
							continue;
						/**
						 * 坐标对应新Excel列说明 0 本机号码1 对方号码2 呼叫类型3 开始时间4 通话时长(秒)5 通话地
						 */						
						TitleMapper tm_new = location(titleName);
						if(tm_new != null) {
							int colIndex = Integer.parseInt(tm_new.getColindex());
							TitleMapper tm_old = newTitleMapper.get(tm_new.getColindex());
							if(tm_old == null) {
								newTitleMapper.put(tm_new.getColindex(), tm_new);
								
								sheetwb.addCell(new Label(colIndex, q - hang, cellContent));
							}else {
								int oldOrder = Integer.parseInt(tm_old.getOrder());
								int newOrder = Integer.parseInt(tm_new.getOrder());
								if(oldOrder > newOrder) {
									newTitleMapper.put(tm_new.getColindex(), tm_new);
									
									label = new Label(colIndex, q - hang, cellContent);
									sheetwb.addCell(label);									
								}else if(oldOrder == newOrder){//需拼接
									newTitleMapper.put(tm_new.getColindex(), tm_new);

									Cell tmp_cell = sheetwb.getCell(colIndex, q - hang);
									if(titleName.contains("呼叫日期")){
										label = new Label(colIndex, q - hang, cellContent +" " + tmp_cell.getContents().trim());
									}else {
										label = new Label(colIndex, q - hang, tmp_cell.getContents().trim() + " " + cellContent);
									}
									sheetwb.addCell(label);									
								}
							}
						}
					}
					
					//未找到本机号码列取文件名
					if(newTitleMapper.get("0") == null) {
						String[] split = fileNew.getName().substring(0, fileNew.getName().lastIndexOf("."))
								.split("_");
						label = new Label(0, q - hang, split[0]);
						sheetwb.addCell(label);
					}
				}
			}
			is.close();
			wbb.write();
			wbb.close();
			wb.close();
			SuccessCount++;
			System.out.println(fileNew.getAbsolutePath() + "生成成功。已处理文件数："+SuccessCount);
		} catch (Exception e) {
			failureCount++;
			e.printStackTrace();
			TxtUtil.fileChaseFW(App.prop.getProperty("ticket.targetpath")+"exception.txt", fileReq.getAbsolutePath());
		}
		return null;
	}

	// 通过JSON 获得坐标
	private TitleMapper location(String titlename) {
		TitleMapper tm = null;
		try {
			for(TitleMapper one : titlemapper) {
				if(one.getTitlename().equals(titlename)) {
					tm = one;
					//log.debug(one.getTitlename());
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return tm;
	}

}
