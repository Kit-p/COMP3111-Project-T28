package comp3111.popnames;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void testGetRankEnhanced() {
        int rankA = AnalyzeNames.getRankEnhanced(2009, "Nathan", "M");
        int rankB = AnalyzeNames.getRankEnhanced(2009, "Jackson", "M");
        assertEquals(rankA, rankB);
        int rank = AnalyzeNames.getRankEnhanced(2009, "", "");
        assertEquals(-1, rank);
    }

    @Test
    public void testGetTopNNamesInRangeYears() {
        String[] result = AnalyzeNames.getTopNNamesInRangeYears("M", 1881, 1882, 187);
        assertTrue(result.length > 0);
        result = AnalyzeNames.getTopNNamesInRangeYears("F", 1881, 1882, 0);
        assertEquals(0, result.length);
    }

    @Test
    public void testGetLowestRankInRangeYears() {
        Integer[] result = AnalyzeNames.getLowestRankInRangeYears("Emilia", "F", 1897, 1899);
        assertEquals(2, result.length);
    }

    @Test
    public void testGetHighestRankInRangeYears() {
        Integer[] result = AnalyzeNames.getHighestRankInRangeYears("Emilia", "F", 1897, 1899);
        assertEquals(2, result.length);
    }

    @Test
    public void testGetGenderLowestRankOfYear() {
        int lowestRank = AnalyzeNames.getGenderLowestRankOfYear("M", 1881);
        assertTrue(lowestRank > 1);
    }

}
