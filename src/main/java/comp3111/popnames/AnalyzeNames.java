package comp3111.popnames;

import java.util.ArrayList;

import org.apache.commons.csv.*;
import edu.duke.*;

public class AnalyzeNames {

	public static CSVParser getFileParser(int year) {
     FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
     return fr.getCSVParser(false);
	}
 
	
	public static String getSummary(int year) {
		String oReport = "";	
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int totalNames = 0;
		int uniqueBoys = 0;
		int uniqueGirls = 0;
		
		oReport = String.format("Summary (Year of %d):\n", year);
		for (CSVRecord rec : getFileParser(year)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			totalNames += 1;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				uniqueBoys++;
			}
			else {
				totalGirls += numBorn;
				uniqueGirls++;
			}
		}
		
		oReport += String.format("Total Births = %,d\n", totalBirths);
		oReport += String.format("***Baby Girls = %,d\n", totalGirls);
		oReport += String.format("***Baby Boys = %,d\n", totalBoys);
		
		oReport += String.format("Total Number of Unique Names = %,d\n", totalNames);
		oReport += String.format("***Unique Names (baby girls) = %,d\n", uniqueGirls);
		oReport += String.format("***Unique Names (baby boys) = %,d\n", uniqueBoys);
		
		return oReport;
	}
	
	
	 public static int getRank(int year, String name, String gender) {
	     boolean found = false;
	     int oRank = 0;
	 	int rank = 1;
	     for (CSVRecord rec : getFileParser(year)) {
	         // Increment rank if gender matches param
	         if (rec.get(1).equals(gender)) {
	             // Return rank if name matches param
	             if (rec.get(0).equals(name)) {
	             	found = true;
	             	oRank = rank;
	             	break;
	             }
	             rank++;
	         }
	     }
	     if (found)
	     	return oRank;
	     else
	     	return -1;
	 }
	 
 
	 public static String getName(int year, int rank, String gender) {
	 	boolean found = false;
	     String oName = "";
	     int currentRank = 0;
	     
	     // For every name entry in the CSV file
	     for (CSVRecord rec : getFileParser(year)) {
	         // Get its rank if gender matches param
	         if (rec.get(1).equals(gender)) {
	             // Get the name whose rank matches param
	         	currentRank++;
	            if (currentRank == rank) {
	             	found = true;
	             	oName = rec.get(0);
	                break;
	            }
	         }
	     }     
	     if (found)
	     	return oName;
	     else
	     	return "information on the name at the specified rank is not available";
	 }


	/**
	 * Get rank but same rank for same occurrence
	 * @param year		target year
	 * @param name		target name
	 * @param gender	target gender
	 * @return	rank if found, otherwise -1
	 */
	public static int getRankEnhanced(int year, String name, String gender) {
		String currentRankOccurrence = "";
		int oRank = -1;
		int rank = 0;
		for (CSVRecord rec : getFileParser(year)) {
		// Increment rank if gender matches param
			if (rec.get(1).equals(gender)) {
				if (!currentRankOccurrence.equals(rec.get(2))) {
					currentRankOccurrence = rec.get(2);
					rank++;
				}

			// Return rank if name matches param
				if (rec.get(0).equals(name)) {
					oRank = rank;
					break;
				}
			}
		}
		return oRank;
	}

	/**
	 * Get name of the input gender with the input rank in the input year, return the first name found if names have same rank
	 *
	 * @param year		target year
	 * @param rank		target rank
	 * @param gender	target gender
	 * @return	name if found, otherwise "information on the name at the specified rank is not available"
	 */
	public static String getNameEnhanced(int year, int rank, String gender) {
		boolean found = false;
		String oName = "";
		int currentRank = 0;
		String currentRankOccurrence = "";

		// For every name entry in the CSV file
		for (CSVRecord rec : getFileParser(year)) {
			// Get its rank if gender matches param
			if (rec.get(1).equals(gender)) {
				// Get the name whose rank matches param
				if (!currentRankOccurrence.equals(rec.get(2))) {
					currentRankOccurrence = rec.get(2);
					currentRank++;
				}

				if (currentRank == rank) {
					found = true;
					oName = rec.get(0);
					break;
				}
			}
		}
		if (found)
			return oName;
		else
			return "information on the name at the specified rank is not available";
	}


	/**
      * Get the occurrence of the name of the gender in the year
      * @param year    target year
      * @param name    target name
      * @param gender  target gender
      * @return occurrence
      */
	 public static int getCount(int year, String name, String gender) {
	     for (CSVRecord rec : getFileParser(year)) {
	         if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
	        	 return Integer.parseInt(rec.get(2));
	         }
	     }
	     
	     return 0;
	 }

	 
     /**
      * Get the birth count of the gender in the year
      * @param year    target year
      * @param gender  target gender
      * @return birth count
      */
	 public static int getGenderBirth(int year, String gender) {
		 int totalGenderBirth = 0;
	     for (CSVRecord rec : getFileParser(year)) {
	         if (rec.get(1).equals(gender)) {
	        	 totalGenderBirth += Integer.parseInt(rec.get(2));
	         }
	     }
	     
	     return totalGenderBirth;
	 }


	/**
	 * Get the names that maintain in top N between start year and end year
	 *
	 * @param startYear  target start year
	 * @param endYear    target end year
	 * @param gender     target gender
	 * @param N          top N
	 * @return array of top N names
	 */
	 public static String[] getTopNNamesInRangeYears(String gender, int startYear, int endYear, int N) {
	 	ArrayList<String> names = new ArrayList<>();
	 	String currentRankOccurrence = "";
	 	int rank = 1;
	 	for (CSVRecord rec : getFileParser(startYear)) {
	 		if (rank > N)
	 			break;
	 		if (rec.get(1).equals(gender)) {
	 			names.add(rec.get(0));
	 			if (!currentRankOccurrence.equals(rec.get(2))) {
	 				currentRankOccurrence = rec.get(2);
	 				rank++;
				}
			}
		}
	 	for (int year = endYear; year > startYear; year--) {
	 		for (int i = names.size() - 1; i >= 0; i--) {
	 			rank = getRank(year, names.get(i), gender);
	 			if (rank <= 0 || rank > N) {
	 				names.remove(i);
				}
			}
		}
	 	return names.toArray(String[]::new);
	 }


	/**
	 * Get lowest rank of name between start year and end year with corresponding years
	 *
	 * <p>
	 * If multiple years share the lowest rank, the latest year is returned
	 * </p>
	 *
	 * @param name       target name
	 * @param gender     target gender
	 * @param startYear  target start year
	 * @param endYear    target end year
	 * @return Array of [rank, year]
	 */
	public static Integer[] getLowestRankInRangeYears(String name, String gender, int startYear, int endYear) {
		int minRank = getRankEnhanced(endYear, name, gender);
		int minRankYear = endYear;
		for (int year = startYear; year < endYear; year++) {
			int rank = getRankEnhanced(year, name, gender);
			if (rank >= minRank) {
				minRank = rank;
				minRankYear = year;
			}
		}
		return new Integer[]{minRank, minRankYear};
	}


	 /**
	  * Get highest rank of name between start year and end year with the corresponding year
	  *
	  * <p>
	  * If multiple years share the highest rank, the latest year is returned
	  * </p>
	  *
	  * @param name       target name
	  * @param gender     target gender
	  * @param startYear  target start year
	  * @param endYear    target end year
	  * @return Array of [rank, year]
	  */
	 public static Integer[] getHighestRankInRangeYears(String name, String gender, int startYear, int endYear) {
	 	int maxRank = getRankEnhanced(endYear, name, gender);
	 	int maxRankYear = endYear;
	 	for (int year = startYear; year < endYear; year++) {
	 		int rank = getRankEnhanced(year, name, gender);
	 		if (rank <= maxRank) {
	 			maxRank = rank;
	 			maxRankYear = year;
			}
		}
	 	return new Integer[]{maxRank, maxRankYear};
	 }


	/**
	 * Get a name with the lowest rank in the target year
	 * If there is multiple names with the lowest rank, the first name with the lowest rank in the dataset is returned
	 * @param year		target year
	 * @param gender	target gender
	 * @return	a name with the lowest rank in the target year
	 */
	 public static String getLowestName(int year, String gender){
		 String oName = "";
		 String currentRankOccurrence = "";
		 // For every name entry in the CSV file
		 for (CSVRecord rec : getFileParser(year)) {
			 if (rec.get(1).equals(gender)) {
				 if (!currentRankOccurrence.equals(rec.get(2))) {
					 currentRankOccurrence = rec.get(2);
					 oName = rec.get(0);
				 }
			 }
		 }

		 return oName;
	 }


	/**
	 * Get the lowest rank of the gender in the year
	 *
	 * @param gender  target gender
	 * @param year    target year
	 * @return lowest rank
	 */
	public static int getGenderLowestRankOfYear(String gender, int year) {
	 	int rank = 0;
		String currentRankOccurrence = "";
	 	for (CSVRecord rec : getFileParser(year)) {
	 		if (rec.get(1).equals(gender)) {
				if (!currentRankOccurrence.equals(rec.get(2))) {
					currentRankOccurrence = rec.get(2);
					rank++;
				}
			}
		}
	 	return rank;
	 }


}