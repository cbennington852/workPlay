package org.example;

// Import statements
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import ExelUtils.ExelUtils;
import ExelUtils.*;
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

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class College {

    private String inputName;
    private String name;
    private String state;
    private String city;

    private DataGrabber dataGrabber;

    public static String[] stateNames = {
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

    public static String stateAutoAppend = " State University";

    public static boolean readAliases = true;
    public static boolean appendUniversityToMatches = true;

    College (String inputNameinput)
    {
        inputName = inputNameinput;
        proccessInputName();
        locateBasicData();
    }

    @Override
    public String toString() {
        return "College{" +
                "inputName='" + inputName + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city  +
                "}\n";
    }

    private void proccessInputName()
    {
        //case 4: is normal, and on list.
        if (collegeNameExists(inputName))
        {
            name = inputName;

        }
        //Case 2: input is an alias EX: UW, GU, EWU
        else if((inputName.length() < 5) && readAliases) // looks like an Alias
        {
            //Technique: search URLs, beacuse they usually have the Alias in them.
            name = locateNameViaAlias(inputName);
        }

        //Case 1: input is a state. tranform into state school
        else if (Arrays.asList(stateNames).contains(inputName))
        {
            //is a state, will now tranform into state school name
            name = inputName + stateAutoAppend;
        }
        //case 5: didnt add university to the thing
        else if (collegeNameExists(inputName + " University"))
        {
            name = inputName + " University";
        }
        else
        {
            name = "Not on List";

        }

        //Case 3: input is university without univeristy name, Gonzaga, Arizona
        //will be processed post
    }

    private static boolean allUpper(String input) {
        return input.equals(input.toUpperCase());
    }

    public String getEnrollment()
    {
        DataGrabber grabby =  DataGrabber.getDataGrabber();
        XSSFSheet workSheet = grabby.getGovSheet();
        DataLocater loc = new DataLocater(workSheet);
        String val =  loc.getCell("UGDS",name).getRawValue();
        double valInt = Double.parseDouble(val);
        DecimalFormat formatter = new DecimalFormat("#,###");
        if (val.equalsIgnoreCase("INSTNM"))
        {
            return "College not Found";
        }
        return formatter.format(valInt);
    }

    public String getNetPriceCalcLink()
    {
        DataGrabber grabby =  DataGrabber.getDataGrabber();
        XSSFSheet workSheet = grabby.getGovSheet();
        DataLocater loc = new DataLocater(workSheet);
        String horizantalName = "NPCURL";
        String val =  loc.getCell(horizantalName,name).getStringCellValue();
        if (val.equalsIgnoreCase(horizantalName))
        {
            return "College not Found";
        }
        return val;
    }


    private String locateNameViaAlias(String input)
    {
        DataGrabber grabby =  DataGrabber.getDataGrabber();
        XSSFSheet workSheet = grabby.getWorkSheet();
        //itternates thru each cell
        //System.out.println(getColumnSize(workSheet, 0));
        for (int columnIndex = 1; columnIndex<getColumnSize(workSheet, 0); columnIndex++){
            int rowIndex = 1;
            XSSFCell cell = workSheet.getRow(columnIndex).getCell(rowIndex);
            String temp = cell.getStringCellValue();
            temp = temp.substring(7);
            int x = 0;
            while ((temp.charAt(x) != '.'))
            {
                x++;
            }
            temp = temp.substring(0,x);
            if(temp.equalsIgnoreCase(inputName))
            {
                XSSFCell cell2 = workSheet.getRow(columnIndex).getCell(0);
                return cell2.getStringCellValue();
            }
        }
        return "College Name Not Found";

    }

    private boolean collegeNameExists(String collegeName)
    {
        DataGrabber grabby =  DataGrabber.getDataGrabber();
        XSSFSheet workSheet = grabby.getWorkSheet();
        //itternates thru each cell
        //System.out.println(getColumnSize(workSheet, 0));
        for (int columnIndex = 1; columnIndex<getColumnSize(workSheet, 0); columnIndex++){
            int rowIndex = 0;

            XSSFCell cell = workSheet.getRow(columnIndex).getCell(rowIndex);
            if (cell.getStringCellValue().equalsIgnoreCase(collegeName))
            {
                return true;
            }
        }
        return false;
    }

    private void locateBasicData ()
    {
        DataGrabber grabby =  DataGrabber.getDataGrabber();
        XSSFSheet workSheet = grabby.getWorkSheet();
        //itternates thru each cell

        //System.out.println(getColumnSize(workSheet, 0));
        for (int columnIndex = 1; columnIndex<getColumnSize(workSheet, 0); columnIndex++){
            int rowIndex = 0;

            XSSFCell cell = workSheet.getRow(columnIndex).getCell(rowIndex);
            if (cell.getStringCellValue().equalsIgnoreCase(name))
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
                if(line.equalsIgnoreCase("<td>"+name+"</td>")) {
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
