package comp3111.popnames;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.*;

public class TopNamesQuery {
    private final int numberOfNames;
    private final String gender;
    private final int startYear;
    private final int endYear;
    private ArrayList<TopNamesQueryRankRow> queryData = null;

    public static class TopNamesQueryRankRow{
        private final IntegerProperty year;
        private final StringProperty[] names;

        public IntegerProperty getYear(){ return this.year; }
        public StringProperty[] getNames(){return names;}

        /** constructor for TopNamesQueryRankRow instance
         @param year
         @param names
         @param numberOfNames
         */
        TopNamesQueryRankRow(int year, String[] names, int numberOfNames){
            this.year = new SimpleIntegerProperty(year);
            this.names = new StringProperty[numberOfNames];
            for(int i = 0; i < numberOfNames; i++){
                this.names[i] = new SimpleStringProperty(names[i]);
            }
        }

    }
    /** constructor for TopNamesQuery instance
         @param number
         @param endYear
         @param gender
         @param startYear
     */
    TopNamesQuery(int number, String gender, int startYear, int endYear){
        this.numberOfNames = number;
        this.gender = gender;
        this.startYear = startYear;
        this.endYear = endYear;
    }
    /**
     * get TopNames, using the N given by the user, iterate through all the years to get the topN names
     */
    public ArrayList<TopNamesQueryRankRow> getTopNames(){
        queryData = new ArrayList<>();
        //iterate through every year
        for(int currentYear = this.startYear; currentYear <= this.endYear; currentYear++ ){
            //create new string array of size N to store names, iterate through N ranks and assign names to array
            String names[] = new String[numberOfNames];
            //System.out.println(currentYear);
            for(int j = 1; j <= numberOfNames; j++){
                names[j-1] = AnalyzeNames.getName(currentYear, j, this.gender);
            }
            //add data row to output ArrayList
            queryData.add(new TopNamesQuery.TopNamesQueryRankRow(currentYear, names, this.numberOfNames));
        }
        return queryData;
    }

    public String getSummary(){
        /**
         * Create hashmap and store Name and occurrence of the name in rank 1
         * Iterate thorough hash map and find max occurrence
         * Get names with max occurrence
         */
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
            summary +=  String.format(", %s", result.get(i));
        }
        String outputGender = "Male";
        if(gender == "F"){
            outputGender = "Female";
        }
        summary += String.format(" for %s has hold the top spot most often for a total of %d times",outputGender, max);
        System.out.println(summary);
        return summary;
    }

    public TableView<TopNamesQuery.TopNamesQueryRankRow> getTableView() {
        //check if query is done
        if (queryData == null) {
            getTopNames();
        }
        /**
         * Create new table view, create column for year
         * For each rank, create a new column
         */
        TableView<TopNamesQuery.TopNamesQueryRankRow> table = new TableView<>();
        table.setItems(FXCollections.observableArrayList(queryData));
        table.setEditable(false);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setStyle("-fx-alignment: CENTER;");

        TableColumn<TopNamesQuery.TopNamesQueryRankRow, String> yearCol = new TableColumn<>("Name");
        yearCol.setCellValueFactory(row ->  new SimpleStringProperty(Integer.toString(row.getValue().getYear().getValue())));
        yearCol.setStyle("-fx-alignment: CENTER;");
        TableColumn<TopNamesQuery.TopNamesQueryRankRow, String>[] NameCols = new TableColumn[numberOfNames];
        table.getColumns().add(yearCol);

        for(int i = 0; i < numberOfNames; i++){
            TableColumn<TopNamesQuery.TopNamesQueryRankRow, String> tempNameCol = new TableColumn<>("Top" + (i+1));
            int finalI = i;
            tempNameCol.setCellValueFactory(row -> row.getValue().getNames()[finalI]);
            tempNameCol.setStyle("-fx-alignment: CENTER;");
            NameCols[i] = tempNameCol;
            table.getColumns().add(NameCols[i]);
        }

        return table;
    }

}
