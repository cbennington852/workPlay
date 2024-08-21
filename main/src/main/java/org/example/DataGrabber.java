package org.example;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public class DataGrabber {

    private static DataGrabber dataGrabber;
    private final XSSFSheet workSheet;

    private final XSSFSheet govSheet;

    private final XSSFSheet realSheet;

    private final XSSFSheet legallyAquuiredSATData;

    private final XSSFSheet SATDataSheet;

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

        String exelPath2  = "Test Optinal Exel2.xlsx";
        String sheetName2 = "Sheet 1";
        ExelUtils utils2 = new ExelUtils(exelPath2,sheetName2);
        legallyAquuiredSATData = utils2.getSheet();

        String exelPath3 = "Aggrigrated college sheet only 2024.xlsx";
        String sheetName3 = "Sheet1";
        ExelUtils utils3 = new ExelUtils(exelPath3,sheetName3);
        realSheet = utils3.getSheet();

        String exelPath4 = "College SAT.ACT data as exel.xlsx";
        String sheetName4 = "Sheet 1";
        ExelUtils utils4 = new ExelUtils(exelPath4,sheetName4);
        SATDataSheet = utils4.getSheet();
    }

    public XSSFSheet getSATDataSheetSheet() {
        return SATDataSheet;
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

    public XSSFSheet getRealSheet() {
        return realSheet;
    }
}
