package ExelUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;

public class ExelUtils {

    public static void main(String[] args) {
        String exelPath = "main/src/main/data/University and College Websites (1).xlsx";
        String sheetName = "Sheet1";
        ExelUtils blag = new ExelUtils(exelPath,sheetName);

        System.out.println(blag.getRowCount());
    }

    private String exelPath;
    private String sheetName;
    private XSSFWorkbook workbook;

    private  XSSFSheet sheet;

    public ExelUtils(String exelPath, String sheetName)
    {
        this.exelPath = exelPath;
        this.sheetName = sheetName;
        try {
            workbook = new XSSFWorkbook(this.exelPath);
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
