package comp3111.popnames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.commons.csv.CSVRecord;

public class NamePopularityQuery {
	private final String name;
	private final String gender;
	private final int startYear;
	private final int endYear;
	private ArrayList<NamePopularityQueryDataRow> popularityTable = new ArrayList<>();
	private int topYearRank;
	private ArrayList<NamePopularityQueryDataRow> topYears = new ArrayList<>();


	public static class NamePopularityQueryDataRow{
		private final IntegerProperty year;
		private final StringProperty rankString;
		private final StringProperty countString;
		private final StringProperty percentageString;
		private final int rank;
		private final int count;
		private final float percentage;


		public NamePopularityQueryDataRow(int year, int rank, int count, float percentage) {
			this.year = new SimpleIntegerProperty(year);
			this.rank = rank;
			this.count = count;
			this.percentage = percentage;
			if (rank == -1)
			{
				this.rankString = new SimpleStringProperty("Out of Rank");
				this.countString = new SimpleStringProperty("-");
				this.percentageString = new SimpleStringProperty("-");
			}else{
				this.rankString = new SimpleStringProperty(String.valueOf(rank));
				this.countString = new SimpleStringProperty(String.valueOf(count));
				this.percentageString = new SimpleStringProperty(String.format("%.2f%%", percentage));
			}
		}

		public int getYear() {
			return year.get();
		}

		public IntegerProperty yearProperty() {
			return year;
		}

		public int getRank() {
			return rank;
		}

		public String getRankString() {
			return rankString.get();
		}

		public StringProperty rankStringProperty() {
			return rankString;
		}

		public int getCount() {
			return count;
		}

		public String getCountString() {
			return countString.get();
		}

		public StringProperty countStringProperty() {
			return countString;
		}

		public float getPercentage() {
			return percentage;
		}

		public String getPercentageString() {
			return percentageString.get();
		}

		public StringProperty percentageStringProperty() {
			return percentageString;
		}
	}


	public NamePopularityQuery(String inputName, String inputGender, int inputStartYear, int inputEndYear) {
		this.name = inputName;
		this.gender = inputGender;
		this.startYear = inputStartYear;
		this.endYear = inputEndYear;
		this.topYearRank = 99999999;
		getNamePopularity();
	}

	
	public void getNamePopularity() {
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
	
	
	public String getSummary() {
		String genderlongVer = "";
		if (this.gender.equals("M")){
			genderlongVer = "male";
		}else{
			genderlongVer = "female";
		}

		if (topYearRank == 99999999 || topYears.size() == 0){
			return String.format("The name %s is out of rank in every year from %d to %d. There is no specific popularity data on the name queried!"
					, this.name, this.startYear, this.endYear);
		}

		//there is rank for the name in the period
		//handle data in the endYear
		String summary = "";
		NamePopularityQueryDataRow endYearEntry = popularityTable.get(popularityTable.size()-1);
		if (endYearEntry.rank == -1){
			String endYearNotRanked =
					String.format("In the year %d, the name %s is out of rank, there is no corresponding data on the number of birth with this name & percentage of total %s birth this name represents. \n"
					, this.endYear, this.name, genderlongVer);
			summary += endYearNotRanked;
		}else{
			String subsummaryA = String.format("In the year %d, the number of birth with name %s is %d, which represents %s percent of total %s births in %d. \n",
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


	public TableView<NamePopularityQuery.NamePopularityQueryDataRow> getTableView(){
		if (popularityTable.isEmpty()){
			getNamePopularity();
		}
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
