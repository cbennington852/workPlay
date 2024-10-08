package org.example;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

public class CompassDataFinder {
    public static URL url = MainGUI.class.getResource("/org/example/SAT and ACT webscraped data Compass.txt");
    ///////////////////////////////////////////////////////////////////
    //FACADE
    ///////////////////////////////////////////////////////////////////
    public static String getAcceptanceRate(String inputName, String name)
    {
        //return getAttibuteWithCode(inputName, name, 22, 1, '<'); //numm commas betwen there and other attributes
        return getInfo(inputName, name, "Arate");
    }

    public static String getTestingPolicy(String inputName, String name)
    {
        //return getAttibuteWithCode(inputName, name, 16, 1, '<'); //numm commas betwen there and other attributes
        return getInfo(inputName, name, "SATSummary");
    }

    public static String getUniversityRequirments(String inputName, String name)
    {
        return getAttibuteWithCode(inputName, name, 3, 0 ,'\"'); //numm commas betwen there and other attributes

    }


    public static String getSatStandardDeviationRange(String inputName, String name)
    {
        //return getAttibuteWithCode(inputName, name, 24, 1 ,'<');
        return getInfo(inputName, name, "SATDev");
    }

    ////////
    //get exel silly
    ////////

    public static String getInfo(String inputName, String name, String reasourceName)
    {
        DataGrabber grabby =  DataGrabber.getDataGrabber();
        XSSFSheet workSheet = grabby.getSATDataSheetSheet();
        System.out.println(workSheet);
        System.out.println(workSheet.getPhysicalNumberOfRows());
        DataLocater loc = new DataLocater(workSheet);
        String horizantalName = reasourceName;
        String val =  loc.getCell(horizantalName,name).getStringCellValue();
        if (Objects.equals(val, "INSTNM"))
        {
            return "data not found";
        }
        return val;
    }


    ////////////////////////////////////////////////////////////////////
    //START OF THE BACKEND GUTS
    ///////////////////////////////////////////////////////////////////

    private static String getAttibuteWithCode(String inputName,String name, int numCommas, int offSet, char endChar)
    {
        try {
            Scanner scanner = new Scanner(url.openStream());

            //now read the file line by line...
            int lineNum = 0;
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                //we have line....
                //We must now Find and Check the name!
                if (nameEquals(inputName,name,line))
                { //name is correct, we have found that college!!!
                    String starter = getAttribute(line, numCommas ,offSet, endChar);
                    return starter;
                }

                lineNum++;
            }
        } catch(FileNotFoundException e) {
            //handle this
            System.out.println("could not open compass data file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "College data Not Found";
    }

    private static String getAttribute(String input, int numCommas, int offSet)
    {
        String newLine = moveIntNumCommas(input,numCommas);
        int startOffSet = offSet;
        int keep = startOffSet;
        for (int x = startOffSet; x < newLine.length(); x++)
        {
            if (newLine.charAt(x) == '<')
            {
                return newLine.substring(offSet,x);
            }
        }
        return "> symbol not found";
    }

    private static String getAttribute(String input, int numCommas, int offSet, char endChar)
    {
        String newLine = moveIntNumCommas(input,numCommas);
        int startOffSet = offSet;
        int keep = startOffSet;
        for (int x = startOffSet; x < newLine.length(); x++)
        {
            if (newLine.charAt(x) == endChar)
            {
                return newLine.substring(offSet,x);
            }
        }
        return "> symbol not found";
    }

    /**
     * Helper method to move the number of commas down line.
     * @param input
     * @param numCommas
     * @return
     */
    private static String moveIntNumCommas(String input, int numCommas)
    {
        //find the name
        int dashCount = 0;
        for (int x = 0; x < input.length();x ++) {
            if (dashCount == numCommas) // we have found the index of the thingy, which is current +2
            {
                return input.substring(x);
            }

            if (input.charAt(x) == '"') { // there will be num-commas" inbetween the URL and the college name
                dashCount++;
            }
        }
        return "Comma Int INVALID";
    }

    /**
     * Returns if the name equals
     * @param inputName raw name
     * @param name filtered name
     * @param currentLine current line being searched
     * @return true or false
     */
    private static boolean nameEquals(String inputName, String name, String currentLine)
    {
        String currentLineCollegeName = "";
        if (currentLine.isEmpty()) //empty return false
        {
            return false;
        }
        if ((currentLine.charAt(1) == '/')) //not a college, just leftovers from webscrape
        {
            return false;
        }

        //find the name
        int dashCount = 0;
        for (int x = 0; x < currentLine.length();x ++)
        {
            if (dashCount == 8) // we have found the index of the thingy, which is current +2
            {
                //System.out.println(currentLine.substring(x));
                int nameStartIndex = x + 1;
                //loop thru until we find the ending of the name
                int i = nameStartIndex;
                while (currentLine.charAt(i) != '<')
                {
                    currentLineCollegeName = currentLineCollegeName + currentLine.charAt(i);
                    i++;
                }
                //System.out.println(currentLineCollegeName);
                //WE now have the name
                if (currentLineCollegeName.equalsIgnoreCase(inputName) || currentLineCollegeName.equalsIgnoreCase(name))
                {//we have found a name!!!
                    return true;
                }
            }

            if (currentLine.charAt(x) == '"')
            { // there will be 8 " inbetween the URL and the college name
                dashCount++;
            }
        }
        return false;
    }

}
