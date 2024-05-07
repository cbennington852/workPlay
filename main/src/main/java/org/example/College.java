package org.example;

// Import statements
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import ExelUtils.ExelUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;

public class College {

    private String inputName;

    private String name;

    private String alias;

    private String state;
    private String city;

    College (String inputName)
    {
        this.inputName = inputName;
        name = inputName;
        locateBasicData();


        //if alias conver to real name
        //if wrong name convert to real name
        //other gibberish
    }

    private void locateBasicData ()
    {
        String exelPath = "main/src/main/data/University and College Websites (1).xlsx";
        String sheetName = "Sheet1";
        ExelUtils utils = new ExelUtils(exelPath,sheetName);

        XSSFSheet workSheet = utils.getSheet();

        //itternates thru each cell

        System.out.println(getColumnSize(workSheet, 0));
        for (int columnIndex = 1; columnIndex<getColumnSize(workSheet, 0); columnIndex++){
            int rowIndex = 0;

            XSSFCell cell = workSheet.getRow(columnIndex).getCell(rowIndex);
            //System.out.println(cell.getStringCellValue());
            if (cell.getStringCellValue().equals(name))
            {
                //found college
                XSSFCell cityCell = workSheet.getRow(columnIndex ).getCell(rowIndex + 2);
                XSSFCell stateCell = workSheet.getRow(columnIndex).getCell(rowIndex + 3);

                city = cityCell.getStringCellValue();
                state = stateCell.getStringCellValue();
                columnIndex = 100000000;
                //breaks loop
            }
        }
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

    public String getState ()
    {
        return state;
    }

    public String getCity()
    {
        return city;
    }





}
