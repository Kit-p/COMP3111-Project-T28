package comp3111.popnames;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.*;


/**
 * Task 1 (Reporting 1) Class that generate a report on the top names over a given period
 */
public class TopNamesQuery {
    /**
     * number of names to be queried
     */
    private final int numberOfNames;
    /**
     * gender queried
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
     * arraylist storing the top names in the period
     */
    private ArrayList<TopNamesQueryRankRow> queryData = null;


    /**
     * Task 1 Inner class storing popularity data of top names in a year for constructing report table
     */
    public static class TopNamesQueryRankRow{
        /**
         * the year of the data retrieved
         */
        private final IntegerProperty year;
        /**
         * the top names of the year retrieved
         */
        private final StringProperty[] names;

        /**
         * Get the year of the data corresponds to
         *
         * @return year of the data corresponds to
         */
        public IntegerProperty getYear(){ return this.year; }


        /**
         * Get the top names of the year
         *
         * @return top names of the year
         */
        public StringProperty[] getNames(){ return names; }


        /**
         * Construct an TopNamesQueryRankRow object storing the top names in a year
         *
         * @param year          year queried
         * @param names         top names of the queried year
         * @param numberOfNames number of names queried
         */
        TopNamesQueryRankRow(int year, String[] names, int numberOfNames){
            this.year = new SimpleIntegerProperty(year);
            this.names = new StringProperty[numberOfNames];
            for(int i = 0; i < numberOfNames; i++){
                this.names[i] = new SimpleStringProperty(names[i]);
            }
        }

    }
    /**
     * Construct a TopNamesQuery object for processing the popularity data of the query
     *
     * @param number    number of names to be queried
     * @param gender    gender of the names to be queried
     * @param startYear start of the period to be queried
     * @param endYear   end of the period to be queried
     */
    TopNamesQuery(int number, String gender, int startYear, int endYear){
        this.numberOfNames = number;
        this.gender = gender;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    /**
     * Iterate through all the years to get the top N names
     *
     * @return array list storing all the rows of the query result
     */
    public ArrayList<TopNamesQueryRankRow> getTopNames(){
        queryData = new ArrayList<>();
        //iterate through every year
        for(int currentYear = this.startYear; currentYear <= this.endYear; currentYear++ ){
            String[] names = new String[numberOfNames];
            for(int j = 1; j <= numberOfNames; j++){
                names[j-1] = AnalyzeNames.getName(currentYear, j, this.gender);
            }
            //add data row to output ArrayList
            queryData.add(new TopNamesQuery.TopNamesQueryRankRow(currentYear, names, this.numberOfNames));
        }
        return queryData;
    }

    /**
     * Create hashmap and store name and occurrence of the name in rank 1
     *
     * <p>
     * Iterate thorough hash map and find max occurrence
     * <br>
     * Get names with max occurrence
     * </p>
     *
     * @return the summary text
     */
    public String getSummary(){
        if (queryData == null) {
            getTopNames();
        }
        HashMap<String, Integer> count = new HashMap<>();
        for(int i = 0; i < queryData.size(); i++){
            String currentName = queryData.get(i).getNames()[0].get();
            if(count.containsKey(currentName)){
                count.put(currentName, count.get(currentName)+1);
            }else{
                count.put(currentName, 1);
            }
        }
        int max = -999;
        for(String name: count.keySet()){
            if(max < count.get(name)){
                max = count.get(name);
            }
        }
        ArrayList<String> result = new ArrayList<>();
        for(String name : count.keySet()){
            if(count.get(name) == max){
                result.add(name);
            }
        }
        String summary = String.format("Over the period %d to %d", startYear, endYear);
        for(int i = 0; i < result.size(); i++){
            summary += String.format(", %s", result.get(i));
        }
        String outputGender = "Male";
        if(gender.equals("F")){
            outputGender = "Female";
        }
        summary += String.format(" for %s has hold the top spot most often for a total of %d times",outputGender, max);
        return summary;
    }


    /**
     * Create new table view, create column for year
     *
     * <p>
     * For each rank, create a new column
     * </p>
     *
     * @return the table representation of the query result
     */
    public TableView<TopNamesQuery.TopNamesQueryRankRow> getTableView() {
        //check if query is done
        if (queryData == null) {
            getTopNames();
        }

        TableView<TopNamesQuery.TopNamesQueryRankRow> table = new TableView<>();
        table.setItems(FXCollections.observableArrayList(queryData));
        table.setEditable(false);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setStyle("-fx-alignment: CENTER;");

        TableColumn<TopNamesQuery.TopNamesQueryRankRow, String> yearCol = new TableColumn<>("Name");
        yearCol.setCellValueFactory(row ->  new SimpleStringProperty(Integer.toString(row.getValue().getYear().getValue())));
        yearCol.setStyle("-fx-alignment: CENTER;");
        table.getColumns().add(yearCol);

        for(int i = 0; i < numberOfNames; i++){
            TableColumn<TopNamesQuery.TopNamesQueryRankRow, String> nameCol = new TableColumn<>("Top" + (i+1));
            int finalI = i;
            nameCol.setCellValueFactory(row -> row.getValue().getNames()[finalI]);
            nameCol.setStyle("-fx-alignment: CENTER;");
            table.getColumns().add(nameCol);
        }
        return table;
    }

}
