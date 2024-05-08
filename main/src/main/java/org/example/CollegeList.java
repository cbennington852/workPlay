package org.example;

import java.util.ArrayList;

public class CollegeList {

    public ArrayList<College> colleges;

    public CollegeList()
    {
        colleges = new ArrayList<>();
    }

    public CollegeList(ArrayList<College> colleges)
    {
        this.colleges = colleges;
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
