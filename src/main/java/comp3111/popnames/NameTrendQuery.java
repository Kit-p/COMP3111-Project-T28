package comp3111.popnames;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class NameTrendQuery {
	private String gender;
	private int startYear;
	private int endYear;
	private int N;
	private HashMap<String, Integer[]> queryResult = null;


	public NameTrendQuery(String gender, int startYear, int endYear, int N) {
		this.gender = gender;
		this.startYear = startYear;
		this.endYear = endYear;
		this.N = N;
	}


	/**
	 * Get Top N names with gender between startYear and endYear
	 * @return Map of name to [minRank, minRankYear, maxRank, maxRankYear]
	 */
	public HashMap<String, Integer[]> getNameTrend() {
		queryResult = new HashMap<>();
		String[] names = AnalyzeNames.getTopNNamesInRangeYears(gender, startYear, endYear, N);
		for (String name : names) {
			Integer[] minRankYear = AnalyzeNames.getLowestRankInRangeYears(name, gender, startYear, endYear);
			Integer[] maxRankYear = AnalyzeNames.getHighestRankInRangeYears(name, gender, startYear, endYear);
			queryResult.put(name
					, Stream.concat(Arrays.stream(minRankYear), Arrays.stream(maxRankYear)).toArray(Integer[]::new));
		}
		return queryResult;
	}


	/**
	 * Get the summary text about the query result
	 * If query has not performed, perform the query
	 * @return the summary text
	 */
	public String getSummary() {
		if (queryResult == null) {
			getNameTrend();
		}
		int k = queryResult.size();
		String temp = "names are found to be maintained at a high level of popularity within Top";
		if (k < 2) {
			temp = "name is found to be maintained at a high level of popularity within Top";
		}
		return String.format("%d %s %d over the period from year %d to year %d.", k, temp, N, startYear, endYear);
	}
}
