package org.example;

import javax.swing.*;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class CollegeList {

    public ArrayList<College> colleges;

    public CollegeList()
    {
        colleges = new ArrayList<>();
        //System.out.println("list made");
    }

    public void clearList()
    {
        colleges.clear();
    }

    public CollegeList(ArrayList<College> colleges)
    {
        this.colleges = colleges;
    }

    public void collegeListInput (String inp)
    {
        colleges.clear();
        int x = 0;

        String collegeName = "";
        while (x < inp.length())
        {
            if (inp.charAt(x) == '\n')
            {
                colleges.add(new College(collegeName));
                collegeName = "";
            }
            else
            {
                collegeName = collegeName += inp.charAt(x);
            }
            x++;
        }

        //System.out.println(colleges);
    }

    public static void updateStateAutoAppend(String inp)
    {
        College.stateAutoAppend = inp;
    }

    public static void readAliases(boolean inp)
    {
        College.readAliases = inp;
    }

    public static void appendUniversityToMatches(boolean inp)
    {
        College.appendUniversityToMatches = inp;
    }


    public String getAllAcceptanceRates(JProgressBar bar)
    {
        String result = "";
        int size = colleges.size();

        for (int x = 0; x < colleges.size(); x++)
        {
            result += colleges.get(x).getAcceptanceRate()+"\n";
            double proggress  = ((double) x /size) * 100;
            int val = (int) proggress;
            bar.setValue(val);
        }
        bar.setValue(100);
        return result;
    }

    public String getAllUniversityRequirmentsLink(JProgressBar bar)
    {
        String result = "";
        int size = colleges.size();

        for (int x = 0; x < colleges.size(); x++)
        {
            result += colleges.get(x).getUniversityRequirmentsLink()+"\n";
            double proggress  = ((double) x /size) * 100;
            int val = (int) proggress;
            bar.setValue(val);
        }
        bar.setValue(100);
        return result;
    }

    public String getAllSATDeviationRange(JProgressBar bar)
    {
        String result = "";
        int size = colleges.size();

        for (int x = 0; x < colleges.size(); x++)
        {
            result += colleges.get(x).getSATDeviationRange()+"\n";
            double proggress  = ((double) x /size) * 100;
            int val = (int) proggress;
            bar.setValue(val);
        }
        bar.setValue(100);
        return result;
    }


    public String getAllState(JProgressBar bar)
    {
        String result = "";
        int size = colleges.size();

        for (int x = 0; x < colleges.size(); x++)
        {
            result += colleges.get(x).getState()+"\n";
            double proggress  = ((double) x /size) * 100;
            int val = (int) proggress;
            bar.setValue(val);
        }
        bar.setValue(100);
        return result;
    }

    public String getAllSATPolicy(JProgressBar bar)
    {
        String result = "";
        int size = colleges.size();

        for (int x = 0; x < colleges.size(); x++)
        {
            result += colleges.get(x).getTestingPolicy()+"\n";
            double proggress  = ((double) x /size) * 100;
            int val = (int) proggress;
            bar.setValue(val);
        }
        bar.setValue(100);
        return result;
    }

    public String nameReconisedTest()
    {
        String result = "";
        int size = colleges.size();

        for (int x = 0; x < colleges.size(); x++)
        {
            result += colleges.get(x).getState()+"\n";
        }
        return result;
    }

    public String getAllEnrollement(JProgressBar bar)
    {
        String result = "";
        int size = colleges.size();
        for (int x = 0; x < colleges.size(); x++)
        {
            result += colleges.get(x).getEnrollment()+"\n";
            double proggress  = ((double) x /size) * 100;
            int val = (int) proggress;
            bar.setValue(val);
        }

        return result;
    }

    public String getAllNetPriceCalcLink(JProgressBar bar)
    {
        String result = "";
        int size = colleges.size();
        for (int x = 0; x < colleges.size(); x++)
        {
            result += colleges.get(x).getNetPriceCalcLink()+"\n";
            double proggress  = ((double) x /size) * 100;
            int val = (int) proggress;
            bar.setValue(val);
        }

        return result;
    }

    public String getAllCity(JProgressBar bar)
    {
        String result = "";
        int size = colleges.size();
        for (int x = 0; x < colleges.size(); x++)
        {
            result += colleges.get(x).getCity()+"\n";
            double proggress  = ((double) x /size) * 100;
            int val = (int) proggress;
            bar.setValue(val);
        }

        return result;
    }

    public String getAllVirtualTourLinks(JProgressBar bar)
    {
        String result = "";
        int size = colleges.size();
        for (int x = 0; x < colleges.size(); x++)
        {
            result += colleges.get(x).getVirtualTourLink()+"\n";
            double proggress  = ((double) x /size) * 100;
            int val = (int) proggress;
            bar.setValue(val);
        }

        return result;
    }

}
