package org.example;

// Import statements
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class College {

    private String inputName;

    private String name;

    private String alias;

    private String state;
    private String city;

    String[] stateNames = {
            "Alabama", "Alaska", "Arizona", "Arkansas", "California",
            "Colorado", "Connecticut", "Delaware", "Florida", "Georgia",
            "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
            "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
            "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri",
            "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
            "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio",
            "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
            "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
            "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
    };

    College (String inputName)
    {
        this.inputName = inputName;
        proccessInputName();
        locateBasicData();

    }

    private void proccessInputName()
    {
        //case 4: is normal, and on list.
        name = inputName;

        //Case 1: input is a state. tranform into state school


        //Case 2: input is an alias EX: UW, GU, EWU
        //Case 3: input is university without univeristy name, Gonzaga, Arizona

    }

    private boolean collegeNameExists(String collegeName)
    {
        String exelPath = "main/src/main/data/University and College Websites (1).xlsx";
        String sheetName = "Sheet1";
        ExelUtils utils = new ExelUtils(exelPath,sheetName);
        XSSFSheet workSheet = utils.getSheet();
        //itternates thru each cell
        //System.out.println(getColumnSize(workSheet, 0));
        for (int columnIndex = 1; columnIndex<getColumnSize(workSheet, 0); columnIndex++){
            int rowIndex = 0;

            XSSFCell cell = workSheet.getRow(columnIndex).getCell(rowIndex);
            if (cell.getStringCellValue().equals(collegeName))
            {
                return true;
            }
        }
        return false;
    }

    private void locateBasicData ()
    {
        String exelPath = "main/src/main/data/University and College Websites (1).xlsx";
        String sheetName = "Sheet1";
        ExelUtils utils = new ExelUtils(exelPath,sheetName);

        XSSFSheet workSheet = utils.getSheet();

        //itternates thru each cell

        //System.out.println(getColumnSize(workSheet, 0));
        for (int columnIndex = 1; columnIndex<getColumnSize(workSheet, 0); columnIndex++){
            int rowIndex = 0;

            XSSFCell cell = workSheet.getRow(columnIndex).getCell(rowIndex);
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

    public String getVirtualTourLink ()
    {

        File file = new File("main/src/main/data/virtualTourLinks.txt");

        try {
            Scanner scanner = new Scanner(file);

            //now read the file line by line...
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                lineNum++;
                //found line
                if(line.equals("<td>"+name+"</td>")) {
                    String result  = scanner.nextLine();

                    //chop off font gibberish
                    result = result.substring(13);


                    //remove the back (0_0)
                    int x = 0;
                    while (result.charAt(x) != '\"')
                    {
                        x++;
                    }
                    result = result.substring(0,x);
                    return result;
                }
            }
        } catch(FileNotFoundException e) {
            //handle this
        }
        return "Link Not Found";
    }





}
