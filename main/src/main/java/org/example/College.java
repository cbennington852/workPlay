package org.example;

// Import statements
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

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

    public static String[] possibleStudentLifeUrls = {"studentlife", "student-life","campuslife","campus-life"};

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
        //some
        if (inputName.isEmpty())
        {
            name = "poop";
            return;
        }
        //remove spaces from the input (only the ending part!!!)
        /*
        if (inputName.charAt(inputName.length()-1) == ' ')
        {
            inputName = inputName.stripTrailing();
        }

         */

        //case 4: is normal, and on list.
        if (collegeNameSearch(inputName))
        {
            return; // found it funny!
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
        if (Objects.equals(val, "INSTNM"))
        {
            return "Net Price Calc";
        }
        return "=HYPERLINK(\"" + val + "\", \"" + "Net Price Calc" + "\")";
    }


    public String findStudentLifeUrl() throws IOException, InterruptedException {
        String ret = "";

        /*
        URL url = new URL("https://www.washington.edu/studentlife");
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        huc.setRequestMethod("HEAD");
        int responseCode = huc.getResponseCode();

        if (responseCode == 200) {
            System.out.println("GOOD");
        } else {
            System.out.println("BAD");
        }

         */

        //plan:
        //find student life URL from the sheet

        DataGrabber grabby =  DataGrabber.getDataGrabber();
        XSSFSheet workSheet = grabby.getGovSheet();
        DataLocater loc = new DataLocater(workSheet);
        String horizantalName = "INSTURL";
        String val =  loc.getCell(horizantalName,name).getStringCellValue();
        String urlName = "";
        if (Objects.equals(val, "INSTNM"))
        {
            //did not find the link
            return "Link not found";
        }
        else {
            //not found thingy
            urlName = val;
        }
        //found url
        URL url = new URL("");


        //search for the student life url
        for (int x = 0; x < possibleStudentLifeUrls.length; x++)
        {
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("HEAD");
            int responseCode = huc.getResponseCode();

            if (responseCode != 404) {
                //System.out.println("GOOD");
                ret = url.toString();
            } else {
                //System.out.println("BAD");
            }
            Thread.sleep(300);
        }

        return ret;
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
                //System.out.println(cell2.getStringCellValue());
                return cell2.getStringCellValue();
            }
        }
        return "College Name Not Found";

    }

    public boolean collegeNameSearch(String collegeName)
    {
        DataGrabber grabby =  DataGrabber.getDataGrabber();
        XSSFSheet workSheet = grabby.getWorkSheet();
        //itternates thru each cell
        //System.out.println(getColumnSize(workSheet, 0));
        //System.out.println(inputName);
        for (int columnIndex = 1; columnIndex<getColumnSize(workSheet, 0); columnIndex++){
            int rowIndex = 0;

            XSSFCell cell = workSheet.getRow(columnIndex).getCell(rowIndex);
            //checks if exact name
            String nameFromSheet = cell.getStringCellValue();

            //System.out.println(nameFromSheet);
            if (nameFromSheet.equalsIgnoreCase(collegeName))
            {
                //name exact

                name = collegeName;
                return true;
            }
            else if (nameFromSheet.equalsIgnoreCase(collegeName + " University"))
            {
                //name is same + Univerity
                name = collegeName + " University";
                return true;
            }
            else if (Pattern.compile(Pattern.quote(collegeName), Pattern.CASE_INSENSITIVE).matcher(nameFromSheet).find()) {
                //the name is in the string
                name = nameFromSheet;
                return true;
            }
        }
        return false;
    }

    public String isTestOptinal()
    {
        //System.out.println(name);
        DataGrabber grabby =  DataGrabber.getDataGrabber();
        XSSFSheet workSheet = grabby.getLegallyAquuiredSATData();
        DataLocater loc = new DataLocater(workSheet);
        String horizantalName = "TST";
        String val =  loc.getCell(horizantalName,name).getStringCellValue();
        //System.out.println(name);
        if (Objects.equals(val, "INSTNM"))
        {
            return "Data not found";
        }
        return val;
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

    public String getName() {
        return name;
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
        URL url = MainGUI.class.getResource("/org/example/virtualTourLinks.txt");
        System.out.println(url.getFile());

        try {
            Scanner scanner = new Scanner(url.openStream());

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
                    result = "=HYPERLINK(\"" + result + "\", \"" + "virtual tour" + "\")";
                    return result;
                }
            }
        } catch(FileNotFoundException e) {
            //handle this
            System.out.println("could not open virtual tour links file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "virtual tour";
    }





}
