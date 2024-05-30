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

}