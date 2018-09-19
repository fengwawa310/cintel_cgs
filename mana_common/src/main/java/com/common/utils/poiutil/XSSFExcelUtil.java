package com.common.utils.poiutil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

public class XSSFExcelUtil
{
    // /**
    // * @param workbook
    // * poi识别结尾为xls的excel后获得的对象
    // * @param sheetIndex
    // * 需要识别的sheet下标
    // * @return
    // */
    // public static List<RowData> readExcelValues(XSSFWorkbook workbook,
    // int sheetIndex, ReadExcelCallBack.ReadValueLisnr lisnr)
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
    // XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
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
    public static <K> List<K> readSheetValues(XSSFSheet sheet,
            ReadExcelCallBack.ReadValueLisnr<K> lisnr)
    {
        if (sheet == null)
        {
            return null;
        }
        List<K> rowDatas = new ArrayList<>();
        XSSFRow row;
        XSSFCell cell;
        int lastRowNum = sheet.getLastRowNum();
        for (int rowNum = 0; rowNum <= lastRowNum; rowNum++)
        {
            row = sheet.getRow(rowNum);
            if (row == null)
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
                rowDatas.add(rowData);
            }
        }
        return rowDatas;
    }

    public static String getFormatValue(XSSFCell cell)
    {
        String value = "";
        switch (cell.getCellType())
        {
            case XSSFCell.CELL_TYPE_NUMERIC:
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
            case XSSFCell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case XSSFCell.CELL_TYPE_FORMULA:
                if (!cell.getStringCellValue().equals(""))
                {
                    value = cell.getStringCellValue();
                }
                else
                {
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
                value = "error";
                break;
            default:
                value = cell.toString();
        }
        return value;
    }

    public static <T> Map<Integer, T> findPicFromSheet(XSSFSheet sheet,
            ReadExcelCallBack.ReadPicLisnr<T> lisnr)
    {
        Map<Integer, T> theMap = new HashMap<>();
        // 处理sheet中的图形
        for (POIXMLDocumentPart dr : sheet.getRelations())
        {
            if (dr instanceof XSSFDrawing)
            {
                XSSFDrawing drawing = (XSSFDrawing) dr;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes)
                {
                    if (shape == null)
                    {
                        continue;
                    }
                    int rowNum = -1;
                    T handlerPic = null;
                    XSSFPicture pic = (XSSFPicture) shape;
                    // 识别图片在sheet中的行数
                    XSSFClientAnchor anchor = pic.getPreferredSize();
                    // CTMarker ctMarker = anchor.getFrom();
                    // String picIndex = String.valueOf(sheetNum) + "_"
                    // + ctMarker.getRow() + "_" + ctMarker.getCol();
                    // sheetIndexPicMap.put(picIndex, pic.getPictureData());

                    if (anchor instanceof XSSFClientAnchor)
                    {
                        // 只有能够识别出行数的图片数据才有处理的必要
                        // handlerPic = new PicData();
                        CTMarker ctMarker = anchor.getFrom();
                        rowNum = ctMarker.getRow();
                        rowNum = anchor.getRow1();
                        // handlerPic.setRowNum(rowNum);
                        // 获取图片数据
                        XSSFPictureData pictureData = pic.getPictureData();
                        if (lisnr != null && pictureData != null)
                        {
                            byte[] picBytes = pictureData.getData();
                            // 图片数据交由调用者处理 在这里设想的是上传操作 获取保存的url地址
                            handlerPic = lisnr.handlerPicData(rowNum, picBytes);
                        }
                    }
                    if (rowNum > -1 && handlerPic != null)
                    {
                        theMap.put(rowNum, handlerPic);
                    }
                }
            }
        }
        return theMap;
    }
}
