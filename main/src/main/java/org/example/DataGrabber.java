package org.example;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public class DataGrabber {

    private static DataGrabber dataGrabber;
    private final XSSFSheet workSheet;

    private final XSSFSheet govSheet;

    private final XSSFSheet legallyAquuiredSATData;

    //FOR THE UNIVERSITY SHEET FUNNY

    private DataGrabber ()
    {
        String exelPath = "University and College Websites (1).xlsx";
        String sheetName = "Sheet1";
        ExelUtils utils = new ExelUtils(exelPath,sheetName);
        workSheet = utils.getSheet();

        String exelPath1 = "Big Exel File Source DATA GOV.xlsx";
        String sheetName1 = "Sheet1";
        ExelUtils utils1 = new ExelUtils(exelPath1,sheetName1);
        govSheet = utils1.getSheet();
        System.out.println(govSheet.getPhysicalNumberOfRows());

        String exelPath2  = "Test Optinal Exel2.xlsx";
        String sheetName2 = "Sheet 1";
        ExelUtils utils2 = new ExelUtils(exelPath2,sheetName2);
        legallyAquuiredSATData = utils2.getSheet();
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

    public XSSFSheet getLegallyAquuiredSATData() {return legallyAquuiredSATData;}
}
