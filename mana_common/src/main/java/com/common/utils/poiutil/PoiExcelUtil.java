package com.common.utils.poiutil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.common.utils.FileUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.web.multipart.MultipartFile;

public class PoiExcelUtil {

    private interface ReadExcelStream<T> {
        T xlsInputStream(InputStream in, int sheetIndex) throws IOException;

        T xlsxInputStream(InputStream in, int sheetIndex) throws IOException;
    }

    private static <T> T readExcelFile(MultipartFile file, final int sheetIndex,
                                       ReadExcelStream<T> lisnr) {
        if (file == null) {
            return null;
        }
        String fileName = file.getOriginalFilename();
        String subfix = FileUtil.getSuffixNameFromFileName(fileName);
        InputStream in = null;
        T result = null;
        try {// 通过文件名称 决定使用 HSSF 还是XSSF
            in = file.getInputStream();
            if (subfix.equals("xls")) {
                if (lisnr != null) {
                    result = lisnr.xlsInputStream(in, sheetIndex);
                }
            } else if (subfix.equals("xlsx")) {
                if (lisnr != null) {
                    result = lisnr.xlsxInputStream(in, sheetIndex);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                    in = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static <T> List<T> readExcelString(MultipartFile file, final int sheetIndex,
                                              final ReadExcelCallBack.ReadValueLisnr<T> readRowLisnr) {
        List<T> data = readExcelFile(file, sheetIndex,
                new ReadExcelStream<List<T>>() {

                    @Override
                    public List<T> xlsInputStream(InputStream in,
                                                  int sheetIndex) throws IOException {
                        HSSFWorkbook workbook = new HSSFWorkbook(in);
                        HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                        List<T> data = HSSFExcelUtil.readSheetValues(sheet,
                                readRowLisnr);
//                        workbook.close();
                        return data;
                    }

                    @Override
                    public List<T> xlsxInputStream(InputStream in,
                                                   int sheetIndex) throws IOException {
                        XSSFWorkbook workbook = new XSSFWorkbook(in);
                        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                        List<T> data = XSSFExcelUtil.readSheetValues(sheet,
                                readRowLisnr);
//                        workbook.close();
                        return data;
                    }
                });
        return data;
    }

    public static <T> Map<Integer, T> readExcelPic(MultipartFile file,
                                                   final int sheetIndex,
                                                   final ReadExcelCallBack.ReadPicLisnr<T> readPicLisnr) {
        Map<Integer, T> data = readExcelFile(file, sheetIndex,
                new ReadExcelStream<Map<Integer, T>>() {

                    @Override
                    public Map<Integer, T> xlsInputStream(InputStream in,
                                                          int sheetIndex) throws IOException {
                        HSSFWorkbook workbook = new HSSFWorkbook(in);
                        HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                        Map<Integer, T> data = HSSFExcelUtil
                                .findPicFromSheet(sheet, readPicLisnr);
//                        workbook.close();
                        return data;
                    }

                    @Override
                    public Map<Integer, T> xlsxInputStream(InputStream in,
                                                           int sheetIndex) throws IOException {
                        XSSFWorkbook workbook = new XSSFWorkbook(in);
                        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                        Map<Integer, T> data = XSSFExcelUtil
                                .findPicFromSheet(sheet, readPicLisnr);
//                        workbook.close();
                        return data;
                    }
                });
        return data;
    }

    // public static Map<Integer, PicData> readExcelPic(File file, int
    // sheetIndex,
    // ReadExcelCallBack.ReadPicLisnr lisnr)
    // {
    //
    // if (file == null || !file.exists())
    // {
    // return null;
    // }
    // Map<Integer, PicData> result = null;
    // String fileName = file.getName();
    // // 通过文件名称 决定使用 HSSF 还是XSSF
    // String subfix = FileUtil.getSuffixNameFromFileName(fileName);
    // InputStream in = null;
    // try
    // {
    // in = new FileInputStream(file);
    // if (subfix.equals("xls"))
    // {
    // HSSFWorkbook workbook = new HSSFWorkbook(in);
    // HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
    // result = HSSFExcelUtil.findPicFromSheet(sheet, lisnr);
    // }
    // else if (subfix.equals("xlsx"))
    // {
    // XSSFWorkbook workbook = new XSSFWorkbook(in);
    // XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
    // result = XSSFExcelUtil.findPicFromSheet(sheet, lisnr);
    // }
    // }
    // catch (FileNotFoundException e)
    // {
    // e.printStackTrace();
    // }
    // catch (IOException e)
    // {
    // e.printStackTrace();
    // }
    // finally
    // {
    // if (in != null)
    // {
    // try
    // {
    // in.close();
    // in = null;
    // }
    // catch (IOException e)
    // {
    // e.printStackTrace();
    // }
    // }
    // }
    // return result;
    // }
    //
    // public static List<RowData> readExcel(File file, int sheetIndex,
    // ReadExcelCallBack.ReadValueLisnr lisnr)
    // {
    // if (file == null || !file.exists())
    // {
    // return null;
    // }
    // List<RowData> rowData = null;
    // String fileName = file.getName();
    // // 通过文件名称 决定使用 HSSF 还是XSSF
    // String subfix = FileUtil.getSuffixNameFromFileName(fileName);
    // InputStream in = null;
    // try
    // {
    // in = new FileInputStream(file);
    // if (subfix.equals("xls"))
    // {
    // HSSFWorkbook workbook = new HSSFWorkbook(in);
    // rowData = HSSFExcelUtil.readExcelValues(workbook, sheetIndex,
    // lisnr);
    // }
    // else if (subfix.equals("xlsx"))
    // {
    // XSSFWorkbook workbook = new XSSFWorkbook(in);
    // rowData = XSSFExcelUtil.readExcelValues(workbook, sheetIndex,
    // lisnr);
    // }
    // }
    // catch (FileNotFoundException e)
    // {
    // e.printStackTrace();
    // }
    // catch (IOException e)
    // {
    // e.printStackTrace();
    // }
    // finally
    // {
    // if (in != null)
    // {
    // try
    // {
    // in.close();
    // in = null;
    // }
    // catch (IOException e)
    // {
    // e.printStackTrace();
    // }
    // }
    // }
    // return rowData;
    // }

}
