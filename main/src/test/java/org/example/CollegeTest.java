package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CollegeTest {

    @Test
    public void testfindStudentLifeUrl() throws IOException, InterruptedException {
        //College c1 = new College("uw");

        //assertEquals(c1.findStudentLifeUrl(), "https://www.washington.edu/studentlife");
    }

    @Test
    public void nameExistsCheck()
    {
        College c1 = new College("uw");
        assertEquals(c1.getName(), "University of Washington");
    }

    public void testSuccessRate ()
    {
        CollegeList list = new CollegeList();
        list.collegeListInput("University of California, Berkeley (UCB)\n" +
                "University of Washington\n" +
                "Ohio State\n" +
                "University of Southern California (USC)\n" +
                "University of Wisconsin-Madison\n" +
                "University of Iowa\n" +
                "West Virginia University\n" +
                "University of Connecticut\n" +
                "Penn State University\n" +
                "University of Massachusetts - Amherst\n" +
                "Lock Haven University\n" +
                "Santa Clara University\n" +
                "University of Nevada, Reno\n" +
                "University of Utah\n" +
                "Boise State University\n" +
                "Shippensburg University\n" +
                "University of Hartford\n" +
                "Miami University (Ohio)\n" +
                "Iowa State University\n" +
                "University of Cincinnati\n" +
                "University of North Carolina");
        String listOfString = list.nameReconisedTest();
        int num = countAmountOf(listOfString, "null");
        double percent = (double) num / list.colleges.size();
        System.out.println("Percent of College names Recognised" + percent+"% ");
    }



    private int countAmountOf(String largeString, String toBeFound)
    {
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {

            lastIndex = largeString.indexOf(toBeFound, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += toBeFound.length();
            }
        }
        return count;
    }

}