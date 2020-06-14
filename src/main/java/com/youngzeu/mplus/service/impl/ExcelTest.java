package com.youngzeu.mplus.service.impl;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.*;

public class ExcelTest {

    public static void main(String[] args) throws IOException {
        File f = new File("C:\\Users\\Administrator\\Desktop\\code\\source_excel.xls");
        InputStream inputStream = new FileInputStream(f);
        HSSFWorkbook xssfWorkbook = new HSSFWorkbook(inputStream);
//  XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0); //如果是.xlsx文件使用这个
        HSSFSheet sheet1 = xssfWorkbook.getSheetAt(4);
        CellRangeAddress ca = sheet1.getMergedRegion(1);
        for (Row row : sheet1) {
            int rowNum = row.getRowNum();
//            if (rowNum != 1) {
//                continue;
//            }
            // B15  == rowNum:14 columnIndex:1

            for (Cell hssfCell : row) {
                int columnIndex = hssfCell.getColumnIndex();
                if(rowNum == 13){
                    if(columnIndex == 0){
                        System.out.println(hssfCell.getStringCellValue() + "-------------");
                    }
                    if(rowNum == 13){
                        CellType cellType = hssfCell.getCellType();
                        if (cellType == CellType.FORMULA && (columnIndex == 1 || columnIndex == 2)) {
                            String cellFormula = hssfCell.getCellFormula();
                            String rv1 = String.valueOf(hssfCell.getNumericCellValue());
                            System.out.print(rv1 + "   ");
                            System.out.print(cellFormula + "_cellFormula  ");
                            hssfCell.setCellType( CellType.STRING);
                            hssfCell.setCellValue(rv1.substring(0, rv1.indexOf(".")));
                        }
                    }
                }
                System.out.print("");
            }

            FileOutputStream excelFileOutPutStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\code\\target_excel.xls");//写数据到这个路径上
            xssfWorkbook.write(excelFileOutPutStream);
            excelFileOutPutStream.flush();
            excelFileOutPutStream.close();

            System.out.println("");
        }
    }
}
