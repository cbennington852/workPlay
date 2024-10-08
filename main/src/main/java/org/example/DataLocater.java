package org.example;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.example.DataGrabber;

public class DataLocater {

    public XSSFSheet sheet;

    public DataLocater(XSSFSheet sheet)
    {
        this.sheet = sheet;
    }

    public XSSFCell getCell(String horizantalName, String collegeName)
    {
        XSSFSheet workSheet = sheet;

        //find the position we are looking for
        int horizantalPosition = 0;
        XSSFRow names = sheet.getRow(0);
        for (int x = 0; x < names.getPhysicalNumberOfCells(); x++)
        {
            //System.out.println(names.getCell(x).getStringCellValue());
            if (names.getCell(x).getStringCellValue().equalsIgnoreCase(horizantalName))
            {//found the right row to pull from
                horizantalPosition = x;
                x = 100000000;
            }
        }

        //cycles thru each cell
        for (int columnIndex = 1; columnIndex<getColumnSize(workSheet, 0); columnIndex++){
            int rowIndex = 0;
            XSSFCell cell = workSheet.getRow(columnIndex).getCell(rowIndex);
            String val = cell.getStringCellValue();
            //cut the dash off.
            if (val.contains("-"))
            {
                for (int x = 0; x < val.length(); x++)
                {
                    if (val.charAt(x) == '-')
                    {
                        val = val.substring(0,x);
                    }
                }
            }
            //System.out.println("data" + workSheet.getRow(columnIndex).getCell(horizantalPosition));
            //System.out.println("name" + val);
            //check to find name equality
            if (collegeName.equalsIgnoreCase(val))
            {
                XSSFCell cell2 = workSheet.getRow(columnIndex).getCell(horizantalPosition);
                return cell2;
            }
        }
        return workSheet.getRow(0).getCell(0);
    }

    public XSSFCell getCell(String horizantalName, String collegeName,String inputName)
    {
        XSSFSheet workSheet = sheet;

        //find the position we are looking for
        int horizantalPosition = 0;
        XSSFRow names = sheet.getRow(0);
        for (int x = 0; x < names.getPhysicalNumberOfCells(); x++)
        {
            if (names.getCell(x).getStringCellValue().equalsIgnoreCase(horizantalName))
            {//found the right row to pull from
                horizantalPosition = x;
                x = 100000000;
            }
        }

        //cycles thru each cell
        for (int columnIndex = 1; columnIndex<getColumnSize(workSheet, 0); columnIndex++){
            int rowIndex = 0;
            XSSFCell cell = workSheet.getRow(columnIndex).getCell(rowIndex);
            String val = cell.getStringCellValue();
            //cut the dash off.
            if (val.contains("-"))
            {
                for (int x = 0; x < val.length(); x++)
                {
                    if (val.charAt(x) == '-')
                    {
                        val = val.substring(0,x);
                    }
                }
            }
            //System.out.println("data" + workSheet.getRow(columnIndex).getCell(horizantalPosition));
            //System.out.println("name" + val);
            //check to find name equality
            if (collegeName.equalsIgnoreCase(val) || (inputName.equalsIgnoreCase(val)))
            {
                XSSFCell cell2 = workSheet.getRow(columnIndex).getCell(horizantalPosition);
                return cell2;
            }
        }
        return workSheet.getRow(0).getCell(0);
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
