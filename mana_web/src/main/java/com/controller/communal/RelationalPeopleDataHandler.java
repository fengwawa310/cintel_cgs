package com.controller.communal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.common.consts.Const;
import com.common.services.api.APIClientRequest;
import com.common.utils.DataSet;
import com.common.utils.DatatablesResponse;
import com.common.utils.ElasticSearchUtils;
import com.common.utils.ExceUtil;
import com.common.utils.IDGenerator;
import com.common.utils.LogUtils;
import com.common.utils.StringHelp;
import com.common.utils.poiutil.PicData;
import com.common.utils.poiutil.PoiExcelUtil;
import com.common.utils.poiutil.ReadExcelCallBack;
import com.common.utils.poiutil.RowData;
import com.entity.suspect.EtSuspect;
import com.entity.sys.SysUser;
import com.entity.taskStu.RelationalPeople;
import com.entity.ticket.EtTicket;
import com.service.fastdfs.FileCRUDService;
import com.service.taskStu.RPDataService;
import com.ticket.model.CallRecord;
import com.util.ExcelException;
import com.util.ExcelUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/RPDHan")
public class RelationalPeopleDataHandler {
	@Resource
	private FileCRUDService fileCRUDService;

	@Resource
	private RPDataService rPDataService;

	/*
	 * 上传文件
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject upload(HttpServletRequest request, MultipartFile file) {
		// Map<String, Object> map = new HashMap<String, Object>();
		String gangId=request.getParameter("gangId");
		if (file == null) {
			return null;
		}
		JSONObject map= new JSONObject();
        JSONObject map1= new JSONObject();
		 //文件名
        String filename = file.getOriginalFilename();
        if("".equals(filename)||filename==null){
            map1.put("msg","请选择Excel文件！");
            map.put("data",map1);
            return map;
        }
		try {
			List<Map<String, String>> listmap = fileProcessing(file,gangId);
			// map.put("listmap", listmap);
			int a = rPDataService.insertExcelRPData(listmap);
			map1.put("flag",true);
            map1.put("msg","导入成功");
            map.put("data",map1);
			System.out.println("这是批量导入返回的结果：：：：：" + a);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			map1.put("flag",false);
            map1.put("msg","导入失败");
            map.put("data",map1);
		} catch (IOException e) {
			e.printStackTrace();
			map1.put("flag",false);
            map1.put("msg","导入失败");
            map.put("data",map1);
		}
		return map;
	}

	/*
	 * 根据gangID查询数据列表
	 */
	@RequestMapping(value = "selectByGangId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DatatablesResponse<RelationalPeople> selectByGangId(HttpServletRequest request, HttpServletResponse response,
			Integer start, Integer length) {

		String gangId = request.getParameter("gangId");

		HashMap<String, Object> map = new HashMap<>();
		map.put("gangId", gangId);
		map.put("start", start);
		map.put("length", length);
		String fastFileUrl="";//Global.getConfig("fastFileUrl");
		List<RelationalPeople> lRPD = rPDataService.selectByGangId(map);
		long countLRPD = rPDataService.countSelectByGangId(map);
		for(RelationalPeople rp:lRPD){
			if (rp.getPhotoUrl()!=null&&!"".equals(rp.getPhotoUrl())) {
				rp.setPhotoUrl(fastFileUrl+rp.getPhotoUrl());
			}
		}
		DataSet<RelationalPeople> dataSet = new DataSet<RelationalPeople>(lRPD, countLRPD, countLRPD);
		DatatablesResponse<RelationalPeople> resp = DatatablesResponse.build(dataSet);

		return resp;
	}
	
	/*
	 * 根据suspectId查询数据列表
	 */
	@RequestMapping(value = "selectBySuspectId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DatatablesResponse<RelationalPeople> selectBySuspectId(HttpServletRequest request, HttpServletResponse response,
			Integer start, Integer length) {
		
		String suspectId = request.getParameter("suspectId");
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("suspectId", suspectId);
		map.put("start", start);
		map.put("length", length);
		String fastFileUrl="";//Global.getConfig("fastFileUrl");
		List<RelationalPeople> lRPD = rPDataService.selectBySuspectId(map);
		long countLRPD = rPDataService.countSelectBySuspectId(map);
		for(RelationalPeople rp:lRPD){
			if (rp.getPhotoUrl()!=null&&!"".equals(rp.getPhotoUrl())) {
				rp.setPhotoUrl(fastFileUrl+rp.getPhotoUrl());
			}
		}
		DataSet<RelationalPeople> dataSet = new DataSet<RelationalPeople>(lRPD, countLRPD, countLRPD);
		DatatablesResponse<RelationalPeople> resp = DatatablesResponse.build(dataSet);
		
		return resp;
	}

	/**
	 * 循环excel各个sheet，获取文字信息
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public List<Map<String, String>> fileProcessing(MultipartFile file,String gangId) throws FileNotFoundException, IOException {
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
		HSSFSheet sheet = null;
		Map<Integer, String> picMap = null;
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
			sheet = workbook.getSheetAt(i);
			// 获取文件中的字符数据
			Map<Integer, String> titleMap = new HashMap<>();
			// 默认第一行为标题，查出标题对应的字段
			titleMap = findTitleMap(sheet.getRow(0));
			for (int j = 1; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum，获取最后一行的行标
				if (workbook.getSheetName(i).indexOf("关系人") != -1) {
					if (j == 1) {
						picMap = findAndUpdatePicDate(sheet);
					}
					HSSFRow row = sheet.getRow(j);
					if (row != null) {
						Map<String, String> map = new HashMap<>();
						map.put("id", IDGenerator.getorderNo());
						map.put("gangId", gangId);
						/*
						 * 第一行为标题，所有列以第一行为主 getLastCellNum，是获取最后一个不为空的列是第几个
						 */
						for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
							if (titleMap.get(k) == "photoUrl" && picMap != null) {
								map.put(titleMap.get(k), picMap.get(j));
							} else {
								if (row.getCell(k) != null) {
									row.getCell(k).setCellType(Cell.CELL_TYPE_STRING);
									map.put(titleMap.get(k), row.getCell(k).getStringCellValue());
								} else {
									map.put(titleMap.get(k), "");
								}
							}
						}
						listMap.add(map);
					}
				}
			}
			System.out.println("读取sheet表：" + workbook.getSheetName(i) + " 完成");
		}

		return listMap;
	}

	/**
	 * 循环当前sheet，获取所有
	 * 
	 * @param sheet
	 * @return
	 */
	public Map<Integer, String> findAndUpdatePicDate(HSSFSheet sheet) {

		Map<String, List<HSSFPictureData>> dataMap = null;
		// 处理sheet中的图形
		HSSFPatriarch hssfPatriarch = sheet.getDrawingPatriarch();
		// 获取所有的形状图
		List<HSSFShape> shapes = null;
		try {
			// 获取所有的形状图
			shapes = hssfPatriarch.getChildren();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		if (shapes != null && shapes.size() > 0) {
			dataMap = new HashMap<String, List<HSSFPictureData>>();
			List<HSSFPictureData> pictureDataList = null;
			for (HSSFShape sp : shapes) {
				if (sp instanceof HSSFPicture) {
					// 转换
					HSSFPicture picture = (HSSFPicture) sp;
					// 获取图片数据
					HSSFPictureData pictureData = picture.getPictureData();
					// 图形定位
					if (picture.getAnchor() instanceof HSSFClientAnchor) {
						HSSFClientAnchor anchor = (HSSFClientAnchor) picture.getAnchor();
						// 获取图片所在行作为key值,插入图片时，默认图片只占一行的单个格子，不能超出格子边界
						int row1 = anchor.getRow1();
						String rowNum = String.valueOf(row1);

						if (dataMap.get(rowNum) != null) {
							pictureDataList = dataMap.get(rowNum);
						} else {
							pictureDataList = new ArrayList<HSSFPictureData>();
						}
						pictureDataList.add(pictureData);
						dataMap.put(rowNum, pictureDataList);
					}
				}
			}
		}

		System.out.println("********将图片上传fastDFS 开始********");
		int t = 0;
		if (dataMap != null) {
			t = dataMap.keySet().size();
		}
		Map<Integer, String> map = new HashMap<>();
		if (t > 0) {
			for (String key : dataMap.keySet()) {
				// 默认只上传每行的第一个图片作为照片
				byte[] bytes = dataMap.get(key).get(0).getData();
				String ext_Name = dataMap.get(key).get(0).suggestFileExtension();
				Map<String, byte[]> files = new HashMap<>();
				files.put("bytes", bytes);
				files.put("ext_Name", ext_Name.getBytes());
				files.put("fileName", "nofileName".getBytes());
				String fileUrl = fileCRUDService.upload(files).split("\\$\\$")[0];
				map.put(Integer.parseInt(key), fileUrl);
			}
		} else {
			System.out.println("Excel表中没有图片!");
		}

		return map;
	}

	public Map<Integer, String> findTitleMap(HSSFRow row) {
		Map<Integer, String> map = new HashMap<>();
		if (row != null) {
			for (int k = 0; k < row.getLastCellNum(); k++) {
				if (row.getCell(k) != null) {
					String name = row.getCell(k).getStringCellValue();
					if ("序号".equals(name)) {
						map.put(k, "xuhao");
					} else if ("姓名".equals(name)) {
						map.put(k, "fullName");
					} else if ("别名".equals(name)) {
						map.put(k, "aliasName");
					} else if ("户籍地、身份证、居住地址、家庭情况".equals(name)) {
						map.put(k, "familySituation");
					} else if ("相片".equals(name)) {
						map.put(k, "photoUrl");
					} else if ("使用的通讯工具、车辆".equals(name)) {
						map.put(k, "communicationTool");
					} else if ("前科".equals(name)) {
						map.put(k, "criminalRecord");
					} else if ("涉嫌的未处理犯罪事实".equals(name)) {
						map.put(k, "untreatedCriminalFacts");
					} else if ("车辆".equals(name)) {
						map.put(k, "vehicle");
					} else if ("备注".equals(name)) {
						map.put(k, "remarks");
					} else if ("证据情况".equals(name)) {
						map.put(k, "evidence");
					}
				}
			}
		}
		return map;
	}
	
	@RequestMapping(value = "/importTicket", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject importTicket(HttpServletRequest request, 
			MultipartFile file) throws IOException {
		    
        CommonsMultipartFile cf= (CommonsMultipartFile)file;
        DiskFileItem fi = (DiskFileItem)cf.getFileItem();
        File f = fi.getStoreLocation(); 
        
      //错误提示
        JSONObject map= new JSONObject();
        JSONObject map1= new JSONObject();
        map1.put("flag",false);
        //文件名
        String filename = file.getOriginalFilename();
        if("".equals(filename)||filename==null){
            map1.put("msg","请选择Excel文件！");
            map.put("data",map1);
            return map;
        }
        //后缀名
        String subfix = filename.lastIndexOf(".") == -1 ? "" : filename
                .substring(filename.lastIndexOf(".") + 1);
        List result;
        if (subfix.equals("xls")) {
            result = ExceUtil.readExcel2003(file,7);
        } else if (subfix.equals("xlsx")) {
            result = ExceUtil.readExcel2007(file,7);
        }else{
            map1.put("msg","只能上传Excel文件！");
            map.put("data",map1);
            return map;
        }
        if(result.size()==0){
            map1.put("msg","Excel中没有数据！");
            map.put("data",map1);
            return map;
        }
        
		try {
			ticketToEs(f);
			map1.put("msg","文件上传成功！");
			map1.put("flag",true);
            map.put("data",map1);
		} catch (Exception e) {
			map1.put("msg","文件上传失败！失败信息："+e.getMessage());
			map1.put("flag",false);
            map.put("data",map1);
			e.printStackTrace();
		}
        return map;
	   
	}
	
	
	/**
	 * 话单excel转es
	 * @param file
	 * @return
	 * @throws Exception 
	 * @throws IOException
	 * @throws ExcelException
	 */
	private void ticketToEs(File file) throws Exception{
			LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
			fieldMap.put("本机号码","calling");
			fieldMap.put("对方号码","called");
			fieldMap.put("呼叫类型","calling_type");
			fieldMap.put("开始时间","start_time");
			fieldMap.put("通话时长(秒)","calling_time");
			fieldMap.put("通话地","calling_place");
			fieldMap.put("备注","remake");
			String[] uniqueFields = { "本机号码", "对方号码", "呼叫类型", "开始时间", "通话时长(秒)", "通话地", "备注" };
			
			FileInputStream in = new FileInputStream(file);
			List<CallRecord> callRecords = ExcelUtil.excelToList(in, "sheet1"
					, CallRecord.class, fieldMap, uniqueFields);
			
			ElasticSearchUtils.bulkInsertTicke(callRecords);
	}

}
