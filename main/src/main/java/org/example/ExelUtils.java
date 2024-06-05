package org.example;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.net.URL;

public class ExelUtils {

    private String exelPath;
    private String sheetName;
    private XSSFWorkbook workbook;

    private  XSSFSheet sheet;

    public ExelUtils(String exelPath, String sheetName)
    {
        this.exelPath = exelPath;
        this.sheetName = sheetName;
        URL opener = MainGUI.class.getResource("/org/example/" + exelPath);
        try {
            workbook = new XSSFWorkbook(opener.openStream());
            sheet = workbook.getSheet(this.sheetName);
        }
        catch(Exception exp )
        {
            System.out.println(exp.getMessage());
        }

    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }

    /*
    public String getCell(int column, int row)
    {
        XSSFRow namesRow = sheet.getRow(column);
        return namesRow.getCell(row).getStringCellValue();
    }
    */


    public int getRowCount()
    {
        int rowCount = sheet.getPhysicalNumberOfRows();
        return rowCount;
    }
}
