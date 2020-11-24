/**
 * Building on the sample skeleton for 'ui.fxml' COntroller Class generated by SceneBuilder 
 */
package comp3111.popnames;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {

    @FXML
    private Tab tabTaskZero;

    @FXML
    private TextField textfieldNameF;

    @FXML
    private TextField textfieldYear;

    @FXML
    private Button buttonRankM;

    @FXML
    private TextField textfieldNameM;

    @FXML
    private Button buttonRankF;

    @FXML
    private Button buttonTopM;

    @FXML
    private Button buttonTopF;

    @FXML
    private Button buttonSummary;
    
    @FXML
    private Tab tabReport1;

    @FXML
    private ToggleGroup T1;

    @FXML
    private Tab tabReport2;

    @FXML
    private TextField task2NameField;

    @FXML
    private RadioButton task2Male;

    @FXML
    private ToggleGroup T11;

    @FXML
    private RadioButton task2Female;

    @FXML
    private TextField task2EndYear;

    @FXML
    private TextField task2StartYear;

    @FXML
    private Button NamePopularityQueryButton;

    @FXML
    private Tab tabReport3;

    @FXML
    private RadioButton task3_maleBtn;

    @FXML
    private RadioButton task3_femaleBtn;

    @FXML
    private ToggleGroup T111;

    @FXML
    private TextField task3_year1Field;

    @FXML
    private TextField task3_year2Field;

    @FXML
    private TextField task3_topNField;

    @FXML
    private Button task3_reportBtn;

    @FXML
    private Tab tabApp1;

    @FXML
    private Tab tabApp2;

    @FXML
    private Tab tabApp3;

    @FXML
    private TextField task6_nameField;

    @FXML
    private RadioButton task6_maleBtn;

    @FXML
    private ToggleGroup T61;

    @FXML
    private RadioButton task6_femaleBtn;

    @FXML
    private TextField task6_yobField;

    @FXML
    private TextField task6_mateNameField;

    @FXML
    private RadioButton task6_mateMaleBtn;

    @FXML
    private ToggleGroup T62;

    @FXML
    private RadioButton task6_mateFemaleBtn;

    @FXML
    private RadioButton task6_youngerBtn;

    @FXML
    private ToggleGroup T63;

    @FXML
    private RadioButton task6_olderBtn;

    @FXML
    private Button task6_predictBtn;

    @FXML
    private TextArea textAreaConsole;
    

    /**
     *  Task Zero
     *  To be triggered by the "Summary" button on the Task Zero Tab 
     *  
     */
    @FXML
    void doSummary() {
    	int year = Integer.parseInt(textfieldYear.getText());
    	String oReport = AnalyzeNames.getSummary(year);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Rank (female)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doRankF() {
    	String oReport = "";
    	String iNameF = textfieldNameF.getText();
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	int oRank = AnalyzeNames.getRank(iYear, iNameF, "F");
    	if (oRank == -1)
    		oReport = String.format("The name %s (female) has not been ranked in the year %d.\n", iNameF, iYear);
    	else
    		oReport = String.format("Rank of %s (female) in year %d is #%d.\n", iNameF, iYear, oRank);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Rank (male)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doRankM() {
    	String oReport = "";
    	String iNameM = textfieldNameM.getText();
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	int oRank = AnalyzeNames.getRank(iYear, iNameM, "M");
    	if (oRank == -1)
    		oReport = String.format("The name %s (male) has not been ranked in the year %d.\n", iNameM, iYear);
    	else
    		oReport = String.format("Rank of %s (male) in year %d is #%d.\n", iNameM, iYear, oRank);
    	textAreaConsole.setText(oReport);
    }


    /**
     *  Task Zero
     *  To be triggered by the "Top 5 (female)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doTopF() {
    	String oReport = "";
    	final int topN = 5;
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	oReport = String.format("Top %d most popular names (female) in the year %d:\n", topN, iYear);
    	for (int i=1; i<=topN; i++)
    		oReport += String.format("#%d: %s\n", i, AnalyzeNames.getName(iYear, i, "F"));
    	textAreaConsole.setText(oReport);
    }


    /**
     *  Task Zero
     *  To be triggered by the "Top 5 (male)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doTopM() {
    	String oReport = "";
    	final int topN = 5;
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	oReport = String.format("Top %d most popular names (male) in the year %d:\n", topN, iYear);
    	for (int i=1; i<=topN; i++)
    		oReport += String.format("#%d: %s\n", i, AnalyzeNames.getName(iYear, i, "M"));
    	textAreaConsole.setText(oReport);
    }
    

    /**
     *  Reporting 2
     *  To be triggered by the "Report" button on the Reporting 2 Tab,
     *  creating a table to show the popularity of a particular name over a given period,
     *  and provide a summary in the textAreaConsole.
     */
    @FXML
    void doNamePopularityQuery() {

    }


    /**
     *  Reporting 3
     *  To be triggered by the "REPORT" button on the Reporting 3 Tab
     */
    @FXML
    void doNameTrendQuery() {

    }


    /**
     * Application 6
     * To be triggered by the "PREDICT" button on the Application 3 Tab
     */
    @FXML
    void doCompatabilityPrediction() {

    }


    /**
     * Common helper function for popping alert
     *
     * @param type      type of the alert
     * @param title     title of the alert
     * @param header    header of the alert
     * @param content   content of the alert
     */
    public static void popAlert(AlertType type, String title, String header, String content){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}

