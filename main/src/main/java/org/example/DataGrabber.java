package org.example;

import ExelUtils.ExelUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class DataGrabber {

    private static DataGrabber dataGrabber;
    private final XSSFSheet workSheet;

    private final XSSFSheet govSheet;


    //FOR THE UNIVERSITY SHEET FUNNY

    private DataGrabber ()
    {
        String exelPath = "main/src/data/University and College Websites (1).xlsx";
        String sheetName = "Sheet1";
        ExelUtils utils = new ExelUtils(exelPath,sheetName);
        workSheet = utils.getSheet();

        String exelPath1 = "main/src/data/Big Exel File Source DATA GOV.xlsx";
        String sheetName1 = "Sheet1";
        ExelUtils utils1 = new ExelUtils(exelPath1,sheetName1);
        govSheet = utils1.getSheet();
    }

    public static DataGrabber getDataGrabber()
    {
        // Check if an instance exists
        if (dataGrabber == null) {
            // If no instance exists, create one
            dataGrabber = new DataGrabber();
        }
        // Return the existing instance
        return dataGrabber;
    }

    public XSSFSheet getWorkSheet() {
        return workSheet;
    }

    public XSSFSheet getGovSheet()
    {
        return govSheet;
    }
}
