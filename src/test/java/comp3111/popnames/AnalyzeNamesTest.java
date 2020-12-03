package comp3111.popnames;

import org.junit.Test;
import static org.junit.Assert.*;

public class AnalyzeNamesTest {
	
    @Test 
    public void testGetRankNotFound() {
    	AnalyzeNames a = new AnalyzeNames();
    	int i = a.getRank(2019, "XXX", "M");
		assertEquals(i, -1);
    }
    
    @Test 
    public void testGetRankMale() {
    	AnalyzeNames a = new AnalyzeNames();
    	int i = a.getRank(2019, "David", "M");
    	assertTrue(i==27);
    }
    
    @Test 
    public void testGetRankFemale() {
    	AnalyzeNames a = new AnalyzeNames();
    	int i = a.getRank(2019, "Desire", "F");
    	assertTrue(i==2192);
    }
    	
    @Test 
    public void testGetNameMale() {
    	AnalyzeNames a = new AnalyzeNames();
    	String name = a.getName(2019, 27, "M");
    	assertTrue(name.equals("David"));
    }
    
    @Test 
    public void testGetNameFemale() {
    	AnalyzeNames a = new AnalyzeNames();
    	String name = a.getName(2019, 2192, "F");
    	assertTrue(name.equals("Desire"));
    }

    @Test
    public void testgetNameEnhanced() {
        String name = AnalyzeNames.getNameEnhanced(1949, 599, "M");
        assertEquals("Abundio", name);
        String notrankname = AnalyzeNames.getNameEnhanced(1949, 600, "M");
        assertEquals("information on the name at the specified rank is not available", notrankname);
    }

    @Test
    public void testgetCount() {
        int count = AnalyzeNames.getCount(1949, "Whitman", "M");
        assertEquals(5, count);
        int count2 = AnalyzeNames.getCount(1949, "adsfas", "M");
        assertEquals(0, count2);
    }


    @Test
    public void testgetGenderBirth() {
        int malebirth = AnalyzeNames.getGenderBirth(2018, "M");
        assertEquals(1809166, malebirth);
        int femalebirth = AnalyzeNames.getGenderBirth(2018, "F");
        assertEquals(1694640, femalebirth);
    }

    @Test
    public void testgetLowestName() {
        String malename = AnalyzeNames.getLowestName(1949, "M");
        assertEquals("Abundio", malename);
        String femalename = AnalyzeNames.getLowestName(1949, "F");
        assertEquals("Abbe", femalename);
    }

}
