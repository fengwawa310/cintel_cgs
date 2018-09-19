package com.common.utils;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by weipc on 2018/1/31.
 */
public class ExceUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExceUtil.class);
    /**
     * 2003版Excel导入
     * @param file
     * @param colNum Excel 列数
     * @return
     * @throws IOException
     */
    public static List readExcel2003(MultipartFile file, int colNum)
            throws IOException {
        List result = new ArrayList<>();
        InputStream in = file.getInputStream();
        HSSFWorkbook workbook = new HSSFWorkbook(in);
        int sheets = workbook.getNumberOfSheets();
        HSSFSheet sheet;
        HSSFRow row;
        HSSFCell cell;
        for (int i = 0; i < sheets; i++) {
            sheet = workbook.getSheetAt(i);
            int rowNum = sheet.getLastRowNum();
            for (int rowIndex = 0; rowIndex <= rowNum; rowIndex++) {
                row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                List<String> values = new ArrayList<>();
                boolean hasValue = false;
                for (int col = 0; col < colNum; col++) {//4位列数或者是行中的值数
                    String value = "";
                    cell = row.getCell(col);
                    if (cell == null) {
                        values.add("");
                        logger.info("第"+(rowIndex+1)+"行中第"+(col+1)+"列没数据！");
                        continue;
                    }
                    value = getFormatValue2003(cell);
                    values.add(value);
                    hasValue = true;
                }
                if (hasValue) {
                    result.add(values);
                }
            }
        }
        return result;
    }

    public static String getFormatValue2003(HSSFCell cell) {
        String value = "";
        switch (cell.getCellType()) {
            case XSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    if (date != null) {
                        value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                        value = value.replaceAll("[年月]", "-").replace("日", "").trim();
                    } else {
                        value = "";
                    }
                } else {
                    value = new DecimalFormat("0").format(cell
                            .getNumericCellValue());
                }
                break;
            case XSSFCell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case XSSFCell.CELL_TYPE_FORMULA:
                if (!cell.getStringCellValue().equals("")) {
                    value = cell.getStringCellValue();
                } else {
                    value = cell.getNumericCellValue() + "";
                }
                break;
            case XSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case XSSFCell.CELL_TYPE_ERROR:
                value = "";
                break;
            default:
                value = cell.toString();
        }
        return value;
    }


    /**
     * 2007版Excel导入
     * @param file
     * @param colNum colNum Excel 列数
     * @return
     * @throws IOException
     */
    public static List  readExcel2007(MultipartFile file, int colNum)
            throws IOException {
        List result = new ArrayList<>();
        InputStream in = file.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        int sheets = workbook.getNumberOfSheets();
        XSSFSheet sheet;
        XSSFRow row;
        XSSFCell cell;
        for (int st = 0; st < sheets; st++) {
            sheet = workbook.getSheetAt(st);
            int rowNum = sheet.getLastRowNum();
            for (int rowIndex = 0; rowIndex <= rowNum; rowIndex++) {
                row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                List<String> values = new ArrayList<>();
                boolean hasValue = false;
                for (int col = 0; col < colNum; col++) {//4位列数或者是行中的值数
                    String value = "";
                    cell = row.getCell(col);
                    if (cell == null) {
                        values.add("");
                        logger.info("第"+(rowIndex+1)+"行中第"+(col+1)+"列没数据！");
                        continue;
                    }
                    value = getFormatValue2007(cell);
                    System.out.print(value + "\t");
                    values.add(value);

                }
                System.out.println();
                //一行数据不能全为空
                for (String value:values) {
                    if(!"".equals(value)){
                        hasValue = true;
                    }
                }
                if (hasValue) {
                    result.add(values);
                }
            }
        }
        return result;
    }

    public static String getFormatValue2007(XSSFCell cell) {
        String value = "";
        switch (cell.getCellType()) {
            case XSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    if (date != null) {
                        value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                        value = value.replaceAll("[年月]", "-").replace("日", "").trim();
                    } else {
                        value = "";
                    }
                } else {
                    value = new DecimalFormat("0").format(cell
                            .getNumericCellValue());
                }
                break;
            case XSSFCell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case XSSFCell.CELL_TYPE_FORMULA:
                if (!cell.getStringCellValue().equals("")) {
                    value = cell.getStringCellValue();
                } else {
                    value = cell.getNumericCellValue() + "";
                }
                break;
            case XSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case XSSFCell.CELL_TYPE_ERROR:
                value = "";
                break;
            default:
                value = cell.toString();
        }
        return value;
    }
}
