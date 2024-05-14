package ExelUtils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.example.DataGrabber;

public class DataLocater {

    public XSSFSheet sheet;

    DataLocater(XSSFSheet sheet)
    {
        this.sheet = sheet;
    }

    public String getCell(String horizantalName, String collegeName)
    {
        XSSFSheet workSheet = sheet;

        //find the position we are looking for
        int horizantalPosition = 0;
        XSSFRow names = sheet.getRow(0);
        System.out.println(names);
        for (int x = 0; x < names.getPhysicalNumberOfCells(); x++)
        {
            if (names.getCell(x).getStringCellValue().equalsIgnoreCase(horizantalName))
            {//found the right row to pull from
                horizantalPosition = x;
                x = 1000000;
            }
        }

        //cycles thru each cell
        for (int columnIndex = 1; columnIndex<getColumnSize(workSheet, 0); columnIndex++){
            int rowIndex = 0;
            XSSFCell cell = workSheet.getRow(columnIndex).getCell(rowIndex);
            //System.out.println(cell);
            if (collegeName.equalsIgnoreCase(cell.getStringCellValue()))
            {
                XSSFCell cell2 = workSheet.getRow(columnIndex).getCell(horizantalPosition);
                String ret = cell2.getStringCellValue();
                return ret;
            }
        }
        return "College Name Not Found";
    }


    private int getColumnSize(XSSFSheet workSheet, int columnNum)
    {
        int count = 0;
        for (Row row : workSheet) {
            if (row.getCell(columnNum) != null) {
                count += 1;
            }
        }
        return count;
    }
}
