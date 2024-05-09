package org.example;

import java.util.ArrayList;

public class CollegeList {

    public ArrayList<College> colleges;

    public CollegeList()
    {
        colleges = new ArrayList<>();
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
        System.out.println(colleges);
    }

    public String getAllState()
    {
        String result = "";
        for (int x = 0; x < colleges.size(); x++)
        {
            result += colleges.get(x).getState()+"\n";
        }
        return result;
    }

    public String getAllNetPriceCalcLink()
    {
        String result = "";
        for (int x = 0; x < colleges.size(); x++)
        {
            result += colleges.get(x).getNetPriceCalcLink()+"\n";
        }
        return result;
    }

    public String getAllCity()
    {
        String result = "";
        for (int x = 0; x < colleges.size(); x++)
        {
            result += colleges.get(x).getCity() +"\n";
        }
        return result;
    }

    public String getAllVirtualTourLinks()
    {
        String result = "";
        for (int x = 0; x < colleges.size(); x++)
        {
            result += colleges.get(x).getVirtualTourLink()+"\n";
        }
        return result;
    }

}
