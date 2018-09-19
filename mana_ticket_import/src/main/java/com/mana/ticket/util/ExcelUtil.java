package com.mana.ticket.util;

import java.io.*;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

/**
 * poi工具类
 * 
 * @author panjizhe 2017-09-04
 */
public class ExcelUtil {

	// 指定SXSSFSheet.getRow(int)可以获得的行的数量
	protected static int DEFAULT_WINDOW_SIZE = 1000;
	// 下拉框 key: 属性名（英文） value: 下拉框内容（字符串数组）
	private static Map<String, String[]> comboBoxMap = null;
	private static Map<Object, String> exceptLogs = new HashMap<>();

	public static Map<Object, String> getExceptLogs() {
		return exceptLogs;
	}

	/**
	 * @MethodName : excelToList
	 * @Description : 将Excel转化为List
	 * @param in
	 *            ：承载着Excel的输入流
	 * @param entityClass
	 *            ：List中对象的类型（Excel中的每一行都要转化为该类型的对象）
	 * @param fieldMap
	 *            ：Excel中的中文列头和类的英文属性的对应关系Map
	 * @param uniqueFields
	 *            ：指定业务主键组合（即复合主键），这些列的组合不能重复
	 * @param helper
	 *            ：辅助，根据需求可以增加需要的参数
	 * @return ：List
	 * @throws ExcelException
	 */
	public static <T> List<T> excelToList(InputStream in, String sheetName, Class<T> entityClass,
			LinkedHashMap<String, String> fieldMap, String[] uniqueFields, Object... helper) throws ExcelException {

		// 定义要返回的list
		List<T> resultList = new ArrayList<T>();
		// 判断字段是否需要翻译，如果不翻译将翻译工具类置为null
		exceptLogs.clear(); // 清空异常日志信息
		try {

			Workbook wb = Workbook.getWorkbook(in);
			if (wb == null) {
				throw new ExcelException("excle文件读取异常。");
			}
			// 获取工作表
			Sheet sheet = wb.getSheet(sheetName);

			if (sheet == null) {
				throw new ExcelException("sheet的名称应为" + sheetName);
			}

			// 判断excel中的列数是否与fieldMap中的列数匹配
			int colNum = sheet.getColumns();
			if (colNum != fieldMap.size()) {
				throw new ExcelException("导入的excel文件中的列数不符合要求，请按下载模板进行导入");
			}

			// 获取工作表的有效行数
			int realRows = 0;
			for (int i = 0; i < sheet.getRows(); i++) { // 遍历所有行
				Cell[] cells = sheet.getRow(i);
				int nullCols = 0;
				if (cells != null) {
					for (int j = 0; j < cells.length; j++) { // 遍历单行的所有列
						Cell currentCell = cells[j];
						if (currentCell == null) {
							nullCols++;
						} else {
							if(StringUtils.isBlank(currentCell.getContents()))
								nullCols++;
						}
					}
				}
				if (cells != null) {
					if (nullCols != cells.length) { // 如果当前行全部为空值
						realRows++;
					}
				}
			}

			// 如果Excel中没有数据则提示错误
			if (realRows <= 1) {
				throw new ExcelException("Excel文件中没有任何数据");
			}

			Cell[] firstRow = sheet.getRow(0);
			String[] excelFieldNames = new String[firstRow.length];

			// 获取Excel中的列名
			for (int i = 0; i < excelFieldNames.length; i++) {
				Cell currentCell = firstRow[i];
				excelFieldNames[i] = currentCell.getContents().trim();
			}

			// 判断需要的字段在Excel中是否都存在
			boolean isExist = true;
			List<String> excelFieldList = Arrays.asList(excelFieldNames);
			for (String cnName : fieldMap.keySet()) {
				if (!excelFieldList.contains(cnName)) {
					isExist = false;
					break;
				}
			}

			// 如果有列名不存在，则抛出异常，提示错误
			if (!isExist) {
				throw new ExcelException("Excel中缺少必要的字段，或字段名称有误");
			}

			// 将列名和列号放入Map中,这样通过列名就可以拿到列号
			LinkedHashMap<String, Integer> colMap = new LinkedHashMap<String, Integer>();
			for (int i = 0; i < excelFieldNames.length; i++) {
				colMap.put(excelFieldNames[i], firstRow[i].getColumn());
			}

			// 判断是否有重复行

			// 将sheet转换为list
			for (int i = 1; i < realRows; i++) {
				// 新建要转换的对象
				T entity = entityClass.newInstance();

				// 给对象中的字段赋值
				for (Entry<String, String> entry : fieldMap.entrySet()) {
					// 获取中文字段名
					String cnNormalName = entry.getKey();
					// 获取英文字段名
					String enNormalName = entry.getValue();
					// 根据中文字段名获取列号
					int col = colMap.get(cnNormalName);

					// 获取当前单元格中的内容
					Cell[] currentRow = sheet.getRow(i);
					if(col >= currentRow.length)
						continue;
					Cell currentCell = currentRow[col];

					Object content = null;
					if (currentCell != null) {
						content = currentCell.getContents().trim();
					}

					try {
						setFieldValueByName(enNormalName, content, entity);
					} catch (Exception e) {
						String message = e.getMessage();
						if (exceptLogs.get(entity) != null) {
							StringBuffer sb = new StringBuffer(exceptLogs.get(entity));
							sb.append("," + message);
							exceptLogs.put(entity, sb.toString());
						} else {
							exceptLogs.put(entity, message);
						}
					}
				}

				resultList.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 如果是ExcelException，则直接抛出
			if (e instanceof ExcelException) {
				throw (ExcelException) e;

				// 否则将其它异常包装成ExcelException再抛出
			} else {
				e.printStackTrace();
				throw new ExcelException("导入Excel失败");
			}
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	/*
	 * <-------------------------辅助的私有方法--------------------------------------------
	 * --->
	 */

	/**
	 * @MethodName : getFieldValueByName
	 * @Description : 根据字段名获取字段值
	 * @param fieldName
	 *            字段名
	 * @param o
	 *            对象
	 * @return 字段值
	 */
	private static Object getFieldValueByName(String fieldName, Object o) throws Exception {

		Object value = null;
		Field field = getFieldByName(fieldName, o.getClass());

		if (field != null) {
			field.setAccessible(true);
			value = field.get(o);
		} else {
			throw new ExcelException(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
		}

		return value;
	}

	/**
	 * @MethodName : getFieldByName
	 * @Description : 根据字段名获取字段
	 * @param fieldName
	 *            字段名
	 * @param clazz
	 *            包含该字段的类
	 * @return 字段
	 */
	private static Field getFieldByName(String fieldName, Class<?> clazz) {
		// 拿到本类的所有字段
		Field[] selfFields = clazz.getDeclaredFields();

		// 如果本类中存在该字段，则返回
		for (Field field : selfFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}

		// 否则，查看父类中是否存在此字段，如果有则返回
		Class<?> superClazz = clazz.getSuperclass();
		if (superClazz != null && superClazz != Object.class) {
			return getFieldByName(fieldName, superClazz);
		}

		// 如果本类和父类都没有，则返回空
		return null;
	}

	/**
	 * @MethodName : getFieldValueByNameSequence
	 * @Description : 根据带路径或不带路径的属性名获取属性值
	 *              即接受简单属性名，如userName等，又接受带路径的属性名，如student.department.name等
	 * 
	 * @param fieldNameSequence
	 *            带路径的属性名或简单属性名
	 * @param o
	 *            对象
	 * @return 属性值
	 * @throws Exception
	 */
	private static Object getFieldValueByNameSequence(String fieldNameSequence, Object o) throws Exception {

		Object value = null;

		// 将fieldNameSequence进行拆分
		String[] attributes = fieldNameSequence.split("\\.");
		if (attributes.length == 1) { // 单个参数
			value = getFieldValueByName(fieldNameSequence, o);
			return value;
		} else { // 多个参数封装Map
			Map<String, Object> map = new HashMap<>();
			for (String attr : attributes) {
				map.put(attr, getFieldValueByName(attr, o));
			}
			return map;
		}
	}

	/**
	 * @MethodName : setFieldValueByName
	 * @Description : 根据字段名给对象的字段赋值
	 * @param fieldName
	 *            字段名
	 * @param fieldValue
	 *            字段值
	 * @param o
	 *            当前要处理的对象
	 * @throws Exception
	 */
	private static void setFieldValueByName(String fieldName, Object fieldValue, Object o) throws Exception {

		Field field = getFieldByName(fieldName, o.getClass());
		if (field != null) {
			field.setAccessible(true);
			// 获取字段类型
			Class<?> fieldType = field.getType();

			// 根据字段类型给字段赋值
			if (String.class == fieldType) {
				field.set(o, String.valueOf(fieldValue));
			} else if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
				field.set(o, Integer.parseInt(fieldValue.toString()));
			} else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
				field.set(o, Long.valueOf(fieldValue.toString()));
			} else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
				field.set(o, Float.valueOf(fieldValue.toString()));
			} else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
				field.set(o, Short.valueOf(fieldValue.toString()));
			} else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
				field.set(o, Double.valueOf(fieldValue.toString()));
			} else if (Character.TYPE == fieldType) {
				if ((fieldValue != null) && (fieldValue.toString().length() > 0)) {
					field.set(o, Character.valueOf(fieldValue.toString().charAt(0)));
				}
			} else if (Date.class == fieldType) {
				field.set(o, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fieldValue.toString()));
			} else {
				field.set(o, fieldValue);
			}
		} else {
			throw new ExcelException(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
		}
	}

}
