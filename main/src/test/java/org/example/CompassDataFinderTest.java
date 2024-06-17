package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompassDataFinderTest {

    @Test
    public void testOne()
    {
        assertEquals(true,CompassDataFinder.nameEquals("Massachusetts Institute of Technology","Massachusetts Institute of Technology", "\t<td class=\"column-1\"><a href=\"https://mitadmissions.org/apply/firstyear/deadlines-requirements/\" target=\"_blank\" rel=\"noopener\">Massachusetts Institute of Technology</a></td><td class=\"column-2\"><a href=\"https://mitadmissions.org/apply/firstyear/tests-scores/\" target=\"_blank\" rel=\"noopener\">Test Required</a></td><td class=\"column-3\">MIT requires applicants to submit SAT or ACT scores.</td><td class=\"column-4\">33240</td><td class=\"column-5\">4.11%</td><td class=\"column-6\">1510–1570</td><td class=\"column-7\">34–36</td>"));
    }

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