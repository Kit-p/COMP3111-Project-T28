package comp3111.popnames;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Task 2 (Reporting 2) Class that generate a report on the popularity of a particular name over a given period
 */
public class NamePopularityQuery {
	/**
	 * name queried
	 */
	private final String name;
	/**
	 * gender of the name queried
	 */
	private final String gender;
	/**
	 * the year of the start of the queried period
	 */
	private final int startYear;
	/**
	 * the year of the end of the queried period
	 */
	private final int endYear;
	/**
	 * arraylist storing the popularity data (rank, count, percentage) of each year in the period
	 */
	private ArrayList<NamePopularityQueryDataRow> popularityTable = new ArrayList<>();
	/**
	 * the highest rank of the name in the period
	 */
	private int topYearRank;
	/**
	 * arraylist storing the years that the name have the highest rank in the period
	 */
	private ArrayList<NamePopularityQueryDataRow> topYears = new ArrayList<>();

	/**
	 * Task 2 Inner class storing popularity data of a name in a year for constructing report table
	 */
	public static class NamePopularityQueryDataRow{
		/**
		 * the year of the data retrieved
		 */
		private final IntegerProperty year;
		/**
		 * processed representation of rank
		 */
		private final StringProperty rankString;
		/**
		 * processed representation of count
		 */
		private final StringProperty countString;
		/**
		 * processed representation of percentage
		 */
		private final StringProperty percentageString;
		/**
		 * rank of the name
		 */
		private final int rank;
		/**
		 * occurrence of the name
		 */
		private final int count;
		/**
		 * percentage of gender birth with the name
		 */
		private final float percentage;

		/**
		 * Construct an NamePopularityQueryDataRow object storing popularity data of a name in a year
		 *
		 * @param year	year of the data retrieved
		 * @param rank	rank of the name
		 * @param count	occurrence of the name
		 * @param percentage	percentage of gender birth with the name
		 */
		public NamePopularityQueryDataRow(int year, int rank, int count, float percentage) {
			this.year = new SimpleIntegerProperty(year);
			this.rank = rank;
			this.count = count;
			this.percentage = percentage;
			if (rank == -1)
			{
				this.rankString = new SimpleStringProperty("Not Ranked");
				this.countString = new SimpleStringProperty("-");
				this.percentageString = new SimpleStringProperty("-");
			}else{
				this.rankString = new SimpleStringProperty(String.valueOf(rank));
				this.countString = new SimpleStringProperty(String.valueOf(count));
				this.percentageString = new SimpleStringProperty(String.format("%.2f%%", percentage));
			}
		}

		/**
		 * Get the year of the data corresponds to
		 *
		 * @return year of the data corresponds to
		 */
		public IntegerProperty yearProperty() {
			return year;
		}

		/**
		 * Get the string representation of rank of the name
		 *
		 * @return	string representation of rank of the name
		 */
		public StringProperty rankStringProperty() {
			return rankString;
		}

		/**
		 * Get the string representation of occurrence of the name
		 *
		 * @return	string representation of occurrence of the name
		 */
		public StringProperty countStringProperty() {
			return countString;
		}

		/**
		 * Get the string of percentage of gender birth with the name
		 *
		 * @return	string of percentage of gender birth with the name
		 */
		public String getPercentageString() {
			return percentageString.get();
		}

		/**
		 * Get the StringProperty of percentage of gender birth with the name
		 *
		 * @return	StringProperty of percentage of gender birth with the name
		 */
		public StringProperty percentageStringProperty() {
			return percentageString;
		}
	}


	/**
	 * Construct a NamePopularityQuery object for processing the popularity data of the queried name
	 *
	 * @param inputName	name to be queried
	 * @param inputGender	gender of name to be queried
	 * @param inputStartYear	start of the period to be queried
	 * @param inputEndYear	end of the period to be queried
	 */
	public NamePopularityQuery(String inputName, String inputGender, int inputStartYear, int inputEndYear) {
		this.name = inputName;
		this.gender = inputGender;
		this.startYear = inputStartYear;
		this.endYear = inputEndYear;
		this.topYearRank = 999999999;
		getNamePopularity();
	}


	/**
	 * Get the popularity data of the queried name of each year in the period, and store the data in the current object.
	 *
	 * Populate the array storing popularity data of each year in the period.
	 * Process the highest rank the name achieved in the period,
	 * and populate the array storing the year the name achieved that rank
	 */
	private void getNamePopularity() {
		for (int i=this.startYear; i<=endYear; i++) {
			//rank of name in year
			int rank = AnalyzeNames.getRankEnhanced(i, this.name, this.gender);

			//count of the name: not ranked -> 0
			int count = 0;
			//percentage of the name compare to total gender birth: not ranked -> 0
			float percentage = 0;

			if (rank==-1) {
				count = 0;
			}else {
				count = AnalyzeNames.getCount(i, this.name, this.gender);

				//total gender birth in the year
				int totalGenderBirth = AnalyzeNames.getGenderBirth(i, this.gender);
				percentage = Math.round((((float) count / (float) totalGenderBirth)*100)*100) / (float) 100;
			}

			NamePopularityQueryDataRow tempRow = new NamePopularityQueryDataRow(i, rank, count, percentage);
			if (rank != -1 && rank < topYearRank){
				topYearRank = rank;
				topYears.clear();
				topYears.add(tempRow);
			}else if (rank == topYearRank){
				topYears.add(tempRow);
			}

			popularityTable.add(tempRow);
		}
	}


	/**
	 * Get the summary text of the queried result of the popularity of a name
	 *
	 * @return	the summary text
	 */
	public String getSummary() {
		String genderlongVer = "";
		if (this.gender.equals("M")){
			genderlongVer = "male";
		}else{
			genderlongVer = "female";
		}

		if (topYearRank == 999999999 || topYears.size() == 0){
			return String.format("The name %s is not ranked in every year from %d to %d. There is no popularity data available on the name queried!"
					, this.name, this.startYear, this.endYear);
		}

		//there is rank for the name in the period
		//handle data in the endYear
		String summary = "";
		NamePopularityQueryDataRow endYearEntry = popularityTable.get(popularityTable.size()-1);
		if (endYearEntry.rank == -1){
			String endYearNotRanked =
					String.format("In the year %d, the name %s is not ranked, there is no corresponding data on the number of birth with this name & percentage of total %s birth this name represents. "
					, this.endYear, this.name, genderlongVer);
			summary += endYearNotRanked;
		}else{
			String subsummaryA = String.format("In the year %d, the number of birth with name %s is %d, which represents %s percent of total %s births in %d. ",
					endYear, this.name, endYearEntry.count, endYearEntry.getPercentageString(), genderlongVer, endYear);
			summary += subsummaryA;
		}

		//handle max years
		if (topYears.size() == 1){
			NamePopularityQueryDataRow maxYear = topYears.get(0);
			String subsummaryB = String.format("The year when the name %s was most popular is %d. In that year, the number of births is %d, which represents %s percent of the total %s birth in %d."
					, this.name, maxYear.year.intValue(), maxYear.count, maxYear.getPercentageString(), genderlongVer, maxYear.year.intValue());
			summary += subsummaryB;
		}else {
			//find all year with highest rank
			StringBuilder subsummaryB = new StringBuilder();
			subsummaryB.append(String.format("The years when the name %s was most popular are ", this.name));
			for (int j=0; j<topYears.size(); j++){
				subsummaryB.append(String.valueOf(topYears.get(j).year.intValue()));
				if (j < topYears.size()-2){
					subsummaryB.append(", ");
				}else if (j == topYears.size()-2){
					subsummaryB.append(" and ");
				}else{
					subsummaryB.append(". \n");
				}
			}

			for (int j=0; j<topYears.size(); j++){
				NamePopularityQueryDataRow eachmaxYear = topYears.get(j);
				String eachMaxYearSummary = String.format("In year %d, the number of births is %d, which represents %s percent of the total %s birth in %d. \n"
				, eachmaxYear.year.intValue(), eachmaxYear.count, eachmaxYear.getPercentageString(), genderlongVer, eachmaxYear.year.intValue());
				subsummaryB.append(eachMaxYearSummary);
			}

			summary += subsummaryB.toString();
		}

		return summary;
	}


	/**
	 * Get the table representation of the queried result of the popularity of a name
	 *
	 * @return the table view of the queried result
	 */
	public TableView<NamePopularityQuery.NamePopularityQueryDataRow> getTableView(){
		TableView<NamePopularityQuery.NamePopularityQueryDataRow> table = new TableView<>();
		table.setEditable(false);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setStyle("-fx-alignment: CENTER;");

		TableColumn<NamePopularityQuery.NamePopularityQueryDataRow, Number> yearCol = new TableColumn<>("Year");
		yearCol.setCellValueFactory(row -> row.getValue().yearProperty());
		yearCol.setStyle("-fx-alignment: CENTER;");

		TableColumn<NamePopularityQuery.NamePopularityQueryDataRow, String> rankCol = new TableColumn<>("Rank");
		rankCol.setCellValueFactory(row -> row.getValue().rankStringProperty());
		rankCol.setStyle("-fx-alignment: CENTER;");

		TableColumn<NamePopularityQuery.NamePopularityQueryDataRow, String> countCol = new TableColumn<>("Count");
		countCol.setCellValueFactory(row -> row.getValue().countStringProperty());
		countCol.setStyle("-fx-alignment: CENTER;");

		TableColumn<NamePopularityQuery.NamePopularityQueryDataRow, String> percentCol = new TableColumn<>("Percentage");
		percentCol.setCellValueFactory(row -> row.getValue().percentageStringProperty());
		percentCol.setStyle("-fx-alignment: CENTER;");

		table.setItems(FXCollections.observableArrayList(popularityTable));
		table.getColumns().addAll(Arrays.asList(yearCol, rankCol, countCol, percentCol));
		return table;
	}
}
