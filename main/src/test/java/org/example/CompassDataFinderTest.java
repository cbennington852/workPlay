package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompassDataFinderTest {



    @Test
    public void testAccpetanceRate()
    {
        assertEquals("69.77%",CompassDataFinder.getAcceptanceRate("Gonzaga University", "Gonzaga University"));
    }

    @Test
    public void testTestPolicy()
    {
        assertEquals("Test Required", CompassDataFinder.getTestingPolicy("California Institute of Technology", "California Institute of Technology"));
    }

    @Test
    public void testReqs()
    {
        assertEquals("https://www.admissions.caltech.edu/apply/first-year-freshman-applicants/application-requirements", CompassDataFinder.getUniversityRequirments("California Institute of Technology", "California Institute of Technology"));
    }

    @Test
    public void testFunny()
    {
        assertEquals("1490–1580",CompassDataFinder.getSatStandardDeviationRange("Harvard College ", "Harvard College"));
    }


}