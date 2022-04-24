package cn.gloryroad.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
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
    public static void setExcelFile(String path, String sheetName) throws Exception {
        FileInputStream excelFile;
        excelFile = new FileInputStream(path);
        excelBook = new XSSFWorkbook(excelFile);
        excelSheet = excelBook.getSheet(sheetName);
    }

    public static void setExcelFile(String path) {
        FileInputStream excelInput;
        try {
            excelInput = new FileInputStream(path);
            excelBook = new XSSFWorkbook(excelInput);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static String getCellData(String sheetName, int rowNum, int colNum) {
        try {
            excelSheet = excelBook.getSheet(sheetName);
            cell = excelSheet.getRow(rowNum).getCell(colNum);

            String cellData = cell.getCellType() == CellType.STRING
                    ? cell.getStringCellValue() : String.valueOf(Math.round(cell.getNumericCellValue()));
            return cellData;
        } catch (Exception e) {
            e.printStackTrace();
            return "";  //读取异常，返回空字符
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

    public static Object[][] getTestData(String excelFilePath, String sheetName) throws IOException {
        //根据传入参数的数据文件和文件名称，组合出Excel文件的绝对路径
        File file = new File(excelFilePath);
        //读取Excel文件
        FileInputStream read = new FileInputStream(file);

        Workbook workbook = null;

        //获取文件参数的拓展名，判断是xlsx还是xls
        String fileExtensionName = excelFilePath.substring(excelFilePath.indexOf("."));
        //文件类型如果是.xlsx，则使用XSSFWork实例化
        //文件类型如果是.xls，则使用HSSFWork实例化
        if (fileExtensionName.equals(".xlsx")) {
            workbook = new XSSFWorkbook(read);
        } else if (fileExtensionName.equals(".xls")) {
            workbook = new HSSFWorkbook(read);
        }
        //通过sheetName生成sheet对象
        Sheet sheet = workbook.getSheet(sheetName);

        /**获取Excel文件sheet1中数据的行数：用getLastRowNum()获取数据最后一行行号，
         用getFirstRowNum()获取第一行行号(),相减后算出数据的行数; 行列都是从0开始的*/
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

        //创建list对象来存储从Excel文件读取的内容
        List<String[]> records = new ArrayList<>();
        for (int i = 1; i <= rowCount; i++) {
            //获取行对象
            Row row = sheet.getRow(i);
            /**用String[]存储Excel文件每行的数据，因为Excel最后一单元格为测试执行结果，
             * 倒数第二个单元格为执行状态，这两列单元格不需要传入测试方法中，因此使用
             * getLastCellNum()-2去掉后两个单元格数据
             */
            String[] fields = new String[row.getLastCellNum() - 1];

            /**判断此条测试数据是否执行，Excel文件倒数第二列为数据行的状态位，“y”表示要执行，
             * 非“y”表示不会参与测试脚本的执行，跳过数据
             */
            if (row.getCell(row.getLastCellNum() - 1).getStringCellValue().equals("y")) {
                for (int j = 0; j <= row.getLastCellNum() - 2; j++) {
                    if (row.getCell(j).getCellType() == CellType.STRING) {
                        fields[j] = row.getCell(j).getStringCellValue();
                    } else if (row.getCell(j).getCellType() == CellType.NUMERIC) {
                        fields[j] = String.valueOf(Math.round(row.getCell(j).getNumericCellValue()));
                    } else if (row.getCell(j).getCellType() == CellType.BOOLEAN) {
                        fields[j] = String.valueOf(row.getCell(j).getBooleanCellValue());
                    }
                }
                records.add(fields);
            }
        }
        //定义函数返回值
        Object[][] result = new Object[records.size()][];
        //设置二维数组每行的值，每行是一个object对象
        for (int i = 0; i < records.size(); i++) {
            result[i] = records.get(i);
        }
        return result;
    }

    public static int getLastColumnNum() {
        //返回Excel最后一列的列号，如果有12列，返回11（因为是从0开始的）
        return excelSheet.getRow(0).getLastCellNum() - 1;
    }


}
