package cn.gloryroad.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zhang Huijuan
 * @Date: 2021/8/12 22:43
 */
public class ExcelUtil {
    //主要实现扩展名为.xlsx的Excel操作
    private static XSSFSheet excelSheet;
    private static XSSFWorkbook excelBook;
    private static XSSFRow row;
    private static XSSFCell cell;

    //设定要操作的Excel文件路径和Excel文件中的sheet名称
    public static void setExcelFile(String path, String sheetName) throws Exception{
        FileInputStream excelFile;
        excelFile = new FileInputStream(path);
        excelBook = new XSSFWorkbook(excelFile);
        excelSheet = excelBook.getSheet(sheetName);
    }

    public static String getCellData(int rowNum, int colNum) {
        cell = excelSheet.getRow(rowNum).getCell(colNum);
        if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(Math.round(cell.getNumericCellValue()));
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

    public static void setCellData(int rowNum, int colNum, String result) {
        try {
            //获取Excel文件中的行对象
            row = excelSheet.getRow(rowNum);
            cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
                cell.setCellValue(result);
            } else {
                cell.setCellValue(result);
            }

            FileOutputStream fileOut = new FileOutputStream(Constant.TestDataExcelFilePath);
            excelBook.write(fileOut);
            fileOut.flush();//强制刷新写入文件
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
