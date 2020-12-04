package comp3111.popnames;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

/**
 * Controller Class of the Application
 */
public class Controller {

    @FXML
    public Button task1ReportBtn;

    @FXML
    public Button application1Run;

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
    private TextField inputTopN;

    @FXML
    private RadioButton inputMale;

    @FXML
    private RadioButton inputFemale;

    @FXML
    private TextField inputStart;

    @FXML
    private TextField inputEnd;

    @FXML
    private TextField inputFatherName;

    @FXML
    private TextField inputMotherName;

    @FXML
    private TextField inputDadYOB;

    @FXML
    private TextField inputMumYOB;

    @FXML
    private TextField inputVintageYear;

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
    private TextField task5NameField;

    @FXML
    private TextField task5_YOB;

    @FXML
    private RadioButton task5_maleBtn;

    @FXML
    private ToggleGroup T51;

    @FXML
    private RadioButton task5_femaleBtn;

    @FXML
    private RadioButton task5_mateMaleBtn;

    @FXML
    private ToggleGroup T52;

    @FXML
    private RadioButton task5_mateFemaleBtn;

    @FXML
    private RadioButton task5_youngerBtn;

    @FXML
    private ToggleGroup T53;

    @FXML
    private RadioButton task5_olderBtn;

    @FXML
    private Button task5_predictBtn;

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
     * Task 1
     * when report button is clicked
     * get all values from input fields
     * then call the query functions
    */
    @FXML
    void doReport(){
            textAreaConsole.setText("");
            String oReport = "";
            String gender;
            if(inputMale.isSelected()){
                gender = "M";
            }else{
                gender = "F";
            }
            int topN, startYear, endYear;
            try{
                topN = Integer.parseInt(inputTopN.getText());
                startYear = Integer.parseInt(inputStart.getText());
                endYear = Integer.parseInt(inputEnd.getText());

            }catch(NumberFormatException e){
                popAlert(AlertType.ERROR, "Error", "Invalid Input", "Format of the input is invalid! Please enter a valid period");
                return;
            }
            if (startYear<1880 || startYear>2019 || endYear<1880 || endYear>2019) {
                popAlert(AlertType.ERROR, "Error", "Invalid Input", "The inputted Period is invalid! Please enter a period within the specified range");
                return;
            }

            if (topN < 1) {
                popAlert(AlertType.ERROR, "Error", "Invalid Input", "The range of the rank of names is invalid! Please enter a range larger or equal to 1");
                return;
             }

            if (endYear < startYear) {
                popAlert(AlertType.ERROR, "Error", "Invalid Input", "Period to be Queried is invalid! End of period cannot be earlier than Start of period");
                return;
            }
            TopNamesQuery query = new TopNamesQuery(topN, gender, startYear,endYear);
            query.getTopNames();
            //query.getSummary();
            oReport = query.getSummary();
            textAreaConsole.setText(oReport);
            popTable(query.getTableView(), "Top N names", null, oReport);
    }


    /**
     *  Reporting 2 (Task 2) Report the popularity of a particular name over a given period.
     *
     *  <p>
     *  To be triggered by the "REPORT" button on the Reporting 2 Tab.
     *  Perform input validation on user inputs on Reporting 2 tab,
     *  pop alert with reason if inputs are invalid.
     *
     *  Otherwise, pop out a table to show the popularity of a particular name over a given period,
     *  and provide a summary in the textAreaConsole.
     *  </p>
     */
    @FXML
    void doNamePopularityQuery() {
        textAreaConsole.setText("");
        String name = task2NameField.getText();
        if (name.isBlank()) {
            popAlert(AlertType.ERROR, "Error", "Invalid Input", "Name is blank! Please enter a valid name!");
            return;
        }

        String gender = "M";
        if (task2Female.isSelected()) {
            gender = "F";
        }

        int  startYear;
        int endYear;
        try {
            startYear = Integer.parseInt(task2StartYear.getText());
            endYear = Integer.parseInt(task2EndYear.getText());
            if (startYear<1880 || startYear>2019 || endYear<1880 || endYear>2019) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            popAlert(AlertType.ERROR, "Error", "Invalid Input", "The queried period is invalid! Year inputs must be between 1880 and 2019!");
            return;
        }
        if (startYear > endYear) {
            popAlert(AlertType.ERROR, "Error", "Invalid Input", "Period to be queried is invalid! Start year must not be greater than end year!");
            return;
        }

        NamePopularityQuery query = new NamePopularityQuery(name, gender, startYear, endYear);
        String summary = query.getSummary();
        textAreaConsole.setText(summary);
        popTable(query.getTableView(), "Reporting 2", null, summary);
    }


    /**
     *  Reporting 3 (Task 3) Get trend of Top N names between two years
     *
     *  <p>
     *  To be triggered by the "REPORT" button on the Reporting 3 Tab.
     *  Perform input validation on user inputs on Reporting 3 tab,
     *  pop alert with reason if user inputs are invalid.
     *
     *  Otherwise, show a summary of the name trend and pop table with detailed data.
     *  </p>
     */
    @FXML
    void doNameTrendQuery() {
        textAreaConsole.setText("");
        String gender = "M";
        if (task3_femaleBtn.isSelected()) {
            gender = "F";
        }
        int startYear, endYear, N;
        try {
            startYear = Integer.parseInt(task3_year1Field.getText());
            endYear = Integer.parseInt(task3_year2Field.getText());
            if (startYear < 1880 || startYear > 2019 || endYear < 1880 || endYear > 2019) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            popAlert(AlertType.ERROR, "Error", "Invalid Input", "Year must be between 1880 and 2019!");
            return;
        }
        if (startYear > endYear) {
            popAlert(AlertType.ERROR, "Error", "Invalid Input", "Start year must not be greater than end year!");
            return;
        }
        try {
            N = Integer.parseInt(task3_topNField.getText());
            if (N < 1) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            popAlert(AlertType.ERROR, "Error", "Invalid Input", "Must query at least top 1!");
            return;
        }
        NameTrendQuery query = new NameTrendQuery(gender, startYear, endYear, N);
        String oReport = query.getSummary();
        textAreaConsole.setText(oReport);
        popTable(query.getTableView(), "Reporting 3", null, oReport);
    }


    /**
     * Task 4
     * when report button is clicked
     * get all values from input fields
     * then call the query functions
     */
    @FXML
    void doBabyNameRecommendation(){
        textAreaConsole.setText("");
        String dadName = inputFatherName.getText();
        String mumName = inputMotherName.getText();
        if(dadName.isBlank()){
            popAlert(AlertType.ERROR, "Error", "Invalid Input", "The father's name cannot be blank!");
            return;
        }
        if(mumName.isBlank()){
            popAlert(AlertType.ERROR, "Error", "Invalid Input", "The mother's name cannot be blank!");
            return;
        }
        int dadYOB, mumYOB, vintageYear;
        try{
            dadYOB = Integer.parseInt(inputDadYOB.getText());
            mumYOB = Integer.parseInt(inputMumYOB.getText());
            if(dadYOB < 1880 || dadYOB > 2019 || mumYOB < 1880 || mumYOB > 2019){
                throw new NumberFormatException();
            }
        }catch(NumberFormatException e){
            popAlert(AlertType.ERROR, "Error", "Invalid Input", "Invalid date for YOB!");
            return;
        }
        try{
            vintageYear = Integer.parseInt(inputVintageYear.getText());
        }catch(NumberFormatException e){
            popAlert(AlertType.ERROR, "Error", "Invalid Input", "Invalid input for vintage year!");
            return;
        }
        if(vintageYear < 1880 || vintageYear > 2019){
            popAlert(AlertType.ERROR, "Error", "Invalid Input", "Vintage year is out of range!");
            return;
        }
        BabyNameRecommendation recommendation = new BabyNameRecommendation(dadYOB, mumYOB, dadName, mumName, vintageYear);
        textAreaConsole.setText(recommendation.getBabiesNames());

    }


    /**
     * Application 2 (Task 5) Predict name of a compatible pairs (soulmate name).
     *
     * <p>
     * To be triggered by the "Get Prediction" button on the Application 2 Tab.
     * Perform input validation on user inputs on Application 2 tab,
     * pop alert with reason if user inputs are invalid.
     *
     * Otherwise, show a name of a compatible pairs (soulmate) according to the inputs.
     * </p>
     */
    @FXML
    void doSoulmateRecommendation() {
        textAreaConsole.setText("");
        String username = task5NameField.getText();
        if (username.isBlank()) {
            popAlert(AlertType.ERROR, "Error", "Invalid Input", "Name is blank! Please enter a valid name!");
            return;
        }

        String usergender = "M";
        if (task5_femaleBtn.isSelected()) {
            usergender = "F";
        }

        int userYOB;
        try {
            userYOB = Integer.parseInt(task5_YOB.getText());
            if (userYOB < 1880 || userYOB > 2019){
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            popAlert(AlertType.ERROR, "Error", "Invalid Input",
                    "Year of Birth must be between 1880 and 2019!\n Sorry we cannot make any prediction on name of your compatible pairs(Soulmate)");
            return;
        }

        String mategender = "M";
        if (task5_mateFemaleBtn.isSelected()) {
            mategender = "F";
        }
        String mateAge = "younger";
        if (task5_olderBtn.isSelected()) {
            mateAge = "older";
        }

        if (userYOB == 1880 && mateAge.equals("younger")){
            popAlert(AlertType.ERROR, "Error", "Unable to Make Prediction",
                    "There is no data on names of people younger than you!\nSorry we cannot make any prediction on name of your compatible pairs(Soulmate)");
            return;
        }else if (userYOB == 2019 && mateAge.equals("older")){
            popAlert(AlertType.ERROR, "Error", "Unable to Make Prediction",
                    "There is no data on names of people older than you!\nSorry we cannot make any prediction on name of your compatible pairs(Soulmate)");
            return;
        }

        SoulmateNameRecommendation query = new SoulmateNameRecommendation(username, usergender, userYOB, mategender, mateAge);
        String soulmateName = query.getSoulmateName();
        String result = "The result of name prediction for your compatible pairs (i.e. Soulmate) is the following: \n";
        result += soulmateName;
        textAreaConsole.setText(result);
    }


    /**
     * Application 3 (Task 6) Predict compatibility of a pair
     *
     * <p>
     * To be triggered by the "PREDICT" button on the Application 3 Tab.
     * Perform input validation on user inputs on Application 3 Tab,
     * pop alert with reason if user inputs are invalid.
     *
     * Otherwise, show the compatibility in percentage (correct to 2 d.p.).
     * </p>
     */
    @FXML
    void doCompatibilityPrediction() {
        textAreaConsole.setText("");
        String gender = "M";
        if (task6_femaleBtn.isSelected()) {
            gender = "F";
        }
        String mateGender = "M";
        if (task6_mateFemaleBtn.isSelected()) {
            mateGender = "F";
        }
        int yob;
        try {
            yob = Integer.parseInt(task6_yobField.getText());
            if (yob < 1880 || yob > 2019) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            popAlert(AlertType.ERROR, "Error", "Invalid Input", "Year of birth must be between 1880 and 2019!");
            return;
        }
        int preference = -1;
        if (task6_olderBtn.isSelected()) {
            preference = 1;
        }
        if (task6_nameField.getText().isBlank() || task6_mateNameField.getText().isBlank()) {
            popAlert(AlertType.ERROR, "Error", "Invalid Input", "Name must not be empty!");
            return;
        }
        CompatibilityPrediction query = new CompatibilityPrediction(task6_nameField.getText(), gender, yob
                , task6_mateNameField.getText(), mateGender , preference);
        double score = query.getPrediction();
        textAreaConsole.setText(String.format("The compatibility is %.2f%%!", score));
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


    /**
     * Common helper function for popping table after query
     *
     * @param table     table representation of query data
     * @param title     title of dialog
     * @param header    header of dialog
     * @param content   content of dialog
     */
    public static void popTable(TableView<?> table, String title, String header, String content) {
        Alert dialog = new Alert(AlertType.INFORMATION);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);
        dialog.getDialogPane().setExpandableContent(table);
        dialog.getDialogPane().setExpanded(true);
        dialog.showAndWait();
    }


}

