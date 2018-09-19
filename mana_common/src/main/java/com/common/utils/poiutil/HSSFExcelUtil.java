package com.common.utils.poiutil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFAnchor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class HSSFExcelUtil
{
    // /**
    // * @param workbook
    // * poi识别结尾为xls的excel后获得的对象
    // * @param sheetIndex
    // * 需要识别的sheet下标
    // * @return
    // */
    // public static List<RowData> readExcelValues(HSSFWorkbook workbook,
    // int sheetIndex, ReadExcelCallBack lisnr)
    // {
    // if (workbook == null)
    // {
    // return null;
    // }
    // int sheets = workbook.getNumberOfSheets();
    // if (sheetIndex > (sheets - 1))
    // {
    // return null;
    // }
    // HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
    // List<RowData> rowDatas = readSheetValues(sheet, lisnr);
    // return rowDatas;
    // }

    /**
     * 读取Sheet每一行的数据
     * 
     * @param sheet
     * @param lisnr
     * @return
     */
    public static <K> List<K> readSheetValues(HSSFSheet sheet,
            ReadExcelCallBack.ReadValueLisnr<K> lisnr)
    {
        if (sheet == null)
        {
            return null;
        }
        List<K> rowDatas = new ArrayList<>();
        HSSFRow row;
        HSSFCell cell;
        int lastRowNum = sheet.getLastRowNum();
        for (int rowNum = 0; rowNum <= lastRowNum; rowNum++)
        {
            row = sheet.getRow(rowNum);
            if (row == null || row.getLastCellNum() < 0)
            {
                continue;
            }
            int lastCellNum = row.getLastCellNum();
            String[] values = new String[lastCellNum];
            for (int cellNum = 0; cellNum < lastCellNum; cellNum++)
            {
                cell = row.getCell(cellNum);
                if (cell == null)
                {
                    // rowData.setCellValue(cellNum, null);

                    // System.out.print(
                    // "" + (rowNum + 1) + "行" + (celNum + 1) + "列没数据;");
                    // logger.info("第" + (rowIndex + 1) + "行中第" + (col + 1)
                    // + "列没数据！");
                    continue;
                }
                String value = getFormatValue(cell);
                values[cellNum] = value;
            }
            if (lisnr != null)
            {
                K rowData = lisnr.handlerRowData(rowNum, values);
                if(rowData != null)
                {
                    rowDatas.add(rowData);
                }
            }
        }
        return rowDatas;
    }

    private static String getFormatValue(HSSFCell cell)
    {
        String value = "";
        switch (cell.getCellType())
        {
            case HSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell))
                {
                    Date date = cell.getDateCellValue();
                    if (date != null)
                    {
                        value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .format(date);
                        value = value.replaceAll("[年月]", "-").replace("日", "")
                                .trim();
                    }
                    else
                    {
                        value = "";
                    }
                }
                else
                {
                    value = new DecimalFormat("0")
                            .format(cell.getNumericCellValue());
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                if (!cell.getStringCellValue().equals(""))
                {
                    value = cell.getStringCellValue();
                }
                else
                {
                    value = cell.getNumericCellValue() + "";
                }
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = "";
                break;
            default:
                value = cell.toString();
        }
        return value;
    }

    public static <T> Map<Integer, T> findPicFromSheet(HSSFSheet sheet,
            ReadExcelCallBack.ReadPicLisnr<T> lisnr)
    {
        Map<Integer, T> theMap = new HashMap<>();
        // 处理sheet中的图形
        HSSFPatriarch hssfPatriarch = sheet.getDrawingPatriarch();
        // 获取所有的形状图
        List<HSSFShape> shapes = hssfPatriarch.getChildren();
        if (shapes == null || shapes.isEmpty())
        {
            return theMap;
        }
        HSSFShape shape;
        for (int i = 0; i < shapes.size(); i++)
        {
            shape = shapes.get(i);
            if (shape == null)
            {
                continue;
            }
            int rowNum = -1;
            T handlerPic = null;
            // 识别图片在sheet中的行数
            HSSFAnchor anchor = shape.getAnchor();
            if (anchor instanceof HSSFClientAnchor)
            {
                // 只有能够识别出行数的图片数据才有处理的必要
                HSSFClientAnchor hssfCAnchor = (HSSFClientAnchor) anchor;
                rowNum = hssfCAnchor.getRow1();
                // 获取图片数据
                if (shape instanceof HSSFPicture)
                {
                    // 转换 获取图片标识
                    HSSFPicture picture = (HSSFPicture) shape;
                    // 获取图片数据
                    HSSFPictureData pictureData = picture.getPictureData();
                    if (lisnr != null && pictureData != null)
                    {
                        byte[] picBytes = pictureData.getData();
                        // 图片数据交由调用者处理
                        handlerPic = lisnr.handlerPicData(rowNum, picBytes);
                        picBytes = null;
                        // handlerPic.setFdfsUrl(uploadUrl);
                    }
                }
            }
            if (rowNum > -1 && handlerPic != null)
            {
                theMap.put(rowNum, handlerPic);
            }
        }
        return theMap;
    }
}
