package comp3111.popnames;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Task 3 (Reporting 3) Class that generate a report describing trend of names
 */
public class NameTrendQuery {
	/**
	 * gender queried
	 */
	private final String gender;
	/**
	 * start year queried
	 */
	private final int startYear;
	/**
	 * end year queried
	 */
	private final int endYear;
	/**
	 * level of popularity (top N) queried
	 */
	private final int N;
	/**
	 * result of query
	 */
	private ArrayList<NameTrendQueryDataRow> queryData = null;


	/**
	 * Inner-class to store each row of the query result
	 */
	public static class NameTrendQueryDataRow {
		/**
		 * name in the current row
		 */
		private final StringProperty name;
		/**
		 * minimum rank of the name in the current row
		 */
		private final IntegerProperty minRank;
		/**
		 * year of the minimum rank of the name in the current row
		 */
		private final IntegerProperty minRankYear;
		/**
		 * maximum rank of the name in the current row
		 */
		private final IntegerProperty maxRank;
		/**
		 * year of the maximum rank of the name in the current row
		 */
		private final IntegerProperty maxRankYear;


		/**
		 * Constructor of a row storing query data
		 *
		 * @param name			name in the current row
		 * @param minRank		minimum rank of the name in the current row
		 * @param minRankYear	year of the minimum rank of the name in the current row
		 * @param maxRank		maximum rank of the name in the current row
		 * @param maxRankYear	year of the maximum rank of the name in the current row
		 */
		public NameTrendQueryDataRow(String name, int minRank, int minRankYear, int maxRank, int maxRankYear) {
			this.name = new SimpleStringProperty(name);
			this.minRank = new SimpleIntegerProperty(minRank);
			this.minRankYear = new SimpleIntegerProperty(minRankYear);
			this.maxRank = new SimpleIntegerProperty(maxRank);
			this.maxRankYear = new SimpleIntegerProperty(maxRankYear);
		}


		/**
		 * Get name of row
		 *
		 * @return name of row
		 */
		public StringProperty nameProperty() {
			return this.name;
		}


		/**
		 * Get lowest rank and corresponding year of row
		 *
		 * @return lowest rank and corresponding year of row
		 */
		public StringProperty minRankProperty() {
			return new SimpleStringProperty(String.format("%d\n[in %d]", minRank.get(), minRankYear.get()));
		}


		/**
		 * Get lowest rank and corresponding year of row
		 *
		 * @return lowest rank and corresponding year of row
		 */
		public StringProperty maxRankProperty() {
			return new SimpleStringProperty(String.format("%d\n[in %d]", maxRank.get(), maxRankYear.get()));
		}


		/**
		 * Get gross trend of row
		 *
		 * @return gross trend of row
		 */
		public StringProperty trendProperty() {
			if (minRankYear.get() < maxRankYear.get()) {
				return new SimpleStringProperty("UP");
			} else if (minRankYear.get() > maxRankYear.get()) {
				return new SimpleStringProperty("DOWN");
			} else {
				return new SimpleStringProperty("FLAT");
			}
		}
	}


	/**
	 * Constructor of the query
	 *
	 * @param gender	gender queried
	 * @param startYear	start year queried
	 * @param endYear	end year queried
	 * @param N			level of popularity (top N) queried
	 */
	public NameTrendQuery(String gender, int startYear, int endYear, int N) {
		this.gender = gender;
		this.startYear = startYear;
		this.endYear = endYear;
		this.N = N;
	}


	/**
	 * Get Top N names with gender between startYear and endYear
	 *
	 * @return Map of name to [minRank, minRankYear, maxRank, maxRankYear]
	 */
	public ArrayList<NameTrendQueryDataRow> getNameTrend() {
		queryData = new ArrayList<>();
		String[] names = AnalyzeNames.getTopNNamesInRangeYears(gender, startYear, endYear, N);
		for (String name : names) {
			Integer[] minRankYear = AnalyzeNames.getLowestRankInRangeYears(name, gender, startYear, endYear);
			Integer[] maxRankYear = AnalyzeNames.getHighestRankInRangeYears(name, gender, startYear, endYear);
			queryData.add(new NameTrendQueryDataRow(name, minRankYear[0], minRankYear[1]
					, maxRankYear[0], maxRankYear[1]));
		}
		return queryData;
	}


	/**
	 * Get the summary text about the query result
	 *
	 * <p>
	 * If query has not performed, perform the query
	 * </p>
	 *
	 * @return the summary text
	 */
	public String getSummary() {
		if (queryData == null) {
			getNameTrend();
		}
		int k = queryData.size();
		String temp = "names are found to be maintained at a high level of popularity within Top";
		if (k < 2) {
			temp = "name is found to be maintained at a high level of popularity within Top";
		}
		return String.format("%d %s %d over the period from year %d to year %d.", k, temp, N, startYear, endYear);
	}


	/**
	 * Get the table representation about the query result
	 *
	 * <p>
	 * If query has not performed, perform the query
	 * </p>
	 *
	 * @return the table representation
	 */
	public TableView<NameTrendQueryDataRow> getTableView() {
		if (queryData == null) {
			getNameTrend();
		}
		TableView<NameTrendQueryDataRow> table = new TableView<>();
		table.setEditable(false);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setStyle("-fx-alignment: CENTER;");
		TableColumn<NameTrendQueryDataRow, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(row -> row.getValue().nameProperty());
		nameCol.setStyle("-fx-alignment: CENTER;");
		TableColumn<NameTrendQueryDataRow, String> minRankCol = new TableColumn<>("Lowest Rank\n[in year]");
		minRankCol.setCellValueFactory(row -> row.getValue().minRankProperty());
		minRankCol.setStyle("-fx-alignment: CENTER;");
		TableColumn<NameTrendQueryDataRow, String> maxRankCol = new TableColumn<>("Highest Rank\n[in year]");
		maxRankCol.setCellValueFactory(row -> row.getValue().maxRankProperty());
		maxRankCol.setStyle("-fx-alignment: CENTER;");
		TableColumn<NameTrendQueryDataRow, String> trendCol = new TableColumn<>("Gross Trend");
		trendCol.setCellValueFactory(row -> row.getValue().trendProperty());
		trendCol.setStyle("-fx-alignment: CENTER;");
		table.setItems(FXCollections.observableArrayList(queryData));
		table.getColumns().addAll(Arrays.asList(nameCol, minRankCol, maxRankCol, trendCol));
		return table;
	}
}
