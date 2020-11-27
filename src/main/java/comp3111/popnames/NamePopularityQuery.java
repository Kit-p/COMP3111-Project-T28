package comp3111.popnames;

import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;

public class NamePopularityQuery {
	private String name;
	private String gender;
	private int startYear;
	private int endYear;
	private HashMap<Integer, HashMap<String, Integer>> popularityTable = new HashMap<Integer, HashMap<String, Integer>>();
	
	
	public NamePopularityQuery(String inputName, String inputGender, int inputStartYear, int inputEndYear) {
		this.name = inputName;
		this.gender = inputGender;
		this.startYear = inputStartYear;
		this.endYear = inputEndYear;
	}
	
	
	public void getNamePopularity() {

	}
	
	
	public String getSummary() {
		return "";
	}
}
