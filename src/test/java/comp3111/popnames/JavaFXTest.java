package comp3111.popnames;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class JavaFXTest extends ApplicationTest {

	private Scene s;
	private TextArea t;
	
	@Override
	public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/ui.fxml"));
   		VBox root = (VBox) loader.load();
   		Scene scene =  new Scene(root);
   		stage.setScene(scene);
   		stage.setTitle("Popular Names");
   		stage.show();
   		s = scene;
		t = (TextArea)s.lookup("#textAreaConsole");
	}

    
	@Test
	public void testButtonRankTrue() {	
		//clickOn("#tabTaskZero");
		clickOn("#buttonRankM");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonRankM");
		//sleep(1000);
		String s2 = t.getText();
		assertTrue(s1.equals(s2));
	}
	
	
	@Test
	public void testButtonRankFalse() {	
		//clickOn("#tabTaskZero");
		clickOn("#buttonRankM");
		//sleep(1000);
		String s1 = t.getText();
		clickOn("#buttonRankF");
		//sleep(1000);
		String s2 = t.getText();
		assertFalse(s1.equals(s2));
	}
	
	
	@Test
	public void testTextAreaConsole() {	
		t.setText("David");
		String s = t.getText();
		assertTrue(s.equals("David"));
	}

	/**
	 * UI Test for task 1
	 */
	@Test
	public void testTopNInvalidTask1(){
		clickOn("#tabReport1");
		TextField inputN = (TextField) s.lookup("#inputTopN");
		inputN.setText("");
		clickOn("#task1ReportBtn");
		assertTrue(t.getText().equals(""));
	}

	@Test
	public void testTopNValidTask1(){
		clickOn("#tabReport1");
		TextField inputN = (TextField) s.lookup("#inputTopN");
		inputN.setText("15");
		clickOn("#task1ReportBtn");
		assertEquals("Over the period 1941 to 1945, James for Male has hold the top spot most often for a total of 5 times",
				t.getText());
	}

	@Test
	public void testGenderMTask1(){
		clickOn("#tabReport1");
		RadioButton inputMale = (RadioButton) s.lookup("#inputMale");
		inputMale.setSelected(true);
		clickOn("#task1ReportBtn");
		assertEquals("Over the period 1941 to 1945, James for Male has hold the top spot most often for a total of 5 times", t.getText());
	}

	@Test
	public void testGenderFTask1(){
		clickOn("#tabReport1");
		RadioButton inputFemale = (RadioButton) s.lookup("#inputFemale");
		inputFemale.setSelected(true);
		clickOn("#task1ReportBtn");
		assertEquals("Over the period 1941 to 1945, Mary for Female has hold the top spot most often for a total of 5 times", t.getText());
	}

	@Test
	public void testStartYearInvalidTask1(){
		clickOn("#tabReport1");
		TextField startYear = (TextField) s.lookup("#inputStart");
		startYear.setText("aaa");
		clickOn("#task1ReportBtn");
		assertEquals("",
				t.getText());
	}

	@Test
	public void testStartYearValidTask1(){
		clickOn("#tabReport1");
		TextField startYear = (TextField) s.lookup("#inputStart");
		startYear.setText("1880");
		clickOn("#task1ReportBtn");
		assertEquals("Over the period 1880 to 1945, John for Male has hold the top spot most often for a total of 44 times",
				t.getText());
	}

	@Test
	public void testYearsInvalidTask1(){
		clickOn("#tabReport1");
		TextField startYear = (TextField) s.lookup("#inputStart");
		startYear.setText("1960");
		TextField EndYear = (TextField) s.lookup("#inputEnd");
		startYear.setText("1880");
		clickOn("#task1ReportBtn");
		assertEquals("Over the period 1880 to 1945, John for Male has hold the top spot most often for a total of 44 times",
				t.getText());
	}

	/**
 	* Test for application 1
 	*/
	@Test
	public void testInputFatherNameApp1(){
		clickOn("#tabApp1");
		TextField fatherName = (TextField) s.lookup("#inputFatherName");
		fatherName.setText("zzzz");
		clickOn("#application1Run");
		assertEquals("Recommendation for a boy's name: Aadav \n" +
						"Recommendation for a girl's name: Kahlani",
				t.getText());
	}

	@Test
	public void testInputFatherNameBlankApp1(){
		clickOn("#tabApp1");
		TextField fatherName = (TextField) s.lookup("#inputFatherName");
		fatherName.setText("");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void testInputMotherNameApp1(){
		clickOn("#tabApp1");
		TextField motherName = (TextField) s.lookup("#inputMotherName");
		motherName.setText("zzzz");
		clickOn("#application1Run");
		assertEquals("Recommendation for a boy's name: James \n" +
						"Recommendation for a girl's name: Aabha",
				t.getText());
	}

	@Test
	public void testInputMotherNameBlankApp1(){
		clickOn("#tabApp1");
		TextField motherName = (TextField) s.lookup("#inputMotherName");
		motherName.setText("");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void vintageYearBlankApp1(){
		clickOn("#tabApp1");
		TextField vintageYear = (TextField) s.lookup("#inputVintageYear");
		vintageYear.setText("");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void testInputDadYOBStringApp1(){
		clickOn("#tabApp1");
		TextField dadYOB = (TextField) s.lookup("#inputDadYOB");
		dadYOB.setText("aaa");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void testInputDadYOBInvalidApp1(){
		clickOn("#tabApp1");
		TextField dadYOB = (TextField) s.lookup("#inputDadYOB");
		dadYOB.setText("1879");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void testInputMumYOBStringApp1(){
		clickOn("#tabApp1");
		TextField dadYOB = (TextField) s.lookup("#inputMumYOB");
		dadYOB.setText("aaa");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void testInputMumYOBInvalidApp1(){
		clickOn("#tabApp1");
		TextField dadYOB = (TextField) s.lookup("#inputMumYOB");
		dadYOB.setText("1879");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void application1Test(){
		clickOn("#tabApp1");
		TextField fatherName = (TextField) s.lookup("#inputMotherName");
		fatherName.setText("Jonathan");
		TextField motherName = (TextField) s.lookup("#inputMotherName");
		motherName.setText("Jean");
		TextField dadYOB = (TextField) s.lookup("#inputDadYOB");
		dadYOB.setText("1985");
		TextField motherYOB = (TextField) s.lookup("#inputMumYOB");
		motherYOB.setText("1985");
		TextField vintageYear = (TextField) s.lookup("#inputVintageYear");
		vintageYear.setText("1880");
		clickOn("#application1Run");
		assertEquals("Recommendation for a boy's name: Frank \n" +
						"Recommendation for a girl's name: Adelle",
				t.getText());
	}


	/**
	 * Test for Task 3 (Reporting 3)
	 */
	@Test
	public void testTask3InvalidYears() {
		clickOn("#tabReport3");
		TextField N = (TextField) s.lookup("#task3_topNField");
		N.setText("1");
		TextField startYear = (TextField) s.lookup("#task3_year1Field");
		TextField endYear = (TextField) s.lookup("#task3_year2Field");
		startYear.setText("500");
		clickOn("#task3_reportBtn");
		assertTrue(t.getText().isEmpty());
		clickOn("OK");
		startYear.setText("5000");
		clickOn("#task3_reportBtn");
		assertTrue(t.getText().isEmpty());
		clickOn("OK");
		startYear.setText("2019");
		endYear.setText("500");
		clickOn("#task3_reportBtn");
		assertTrue(t.getText().isEmpty());
		clickOn("OK");
		endYear.setText("5000");
		clickOn("#task3_reportBtn");
		assertTrue(t.getText().isEmpty());
		clickOn("OK");
		endYear.setText("1881");
		clickOn("#task3_reportBtn");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void testTask3InvalidN() {
		clickOn("#tabReport3");
		TextField N = (TextField) s.lookup("#task3_topNField");
		N.setText("0");
		clickOn("#task3_reportBtn");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void testTask3MaleBtn() {
		clickOn("#tabReport3");
		TextField N = (TextField) s.lookup("#task3_topNField");
		N.setText("10");
		RadioButton maleBtn = (RadioButton) s.lookup("#task3_maleBtn");
		maleBtn.setSelected(true);
		clickOn("#task3_reportBtn");
		assertFalse(t.getText().isBlank());
	}


	@Test
	public void testTask3FemaleBtn() {
		clickOn("#tabReport3");
		TextField N = (TextField) s.lookup("#task3_topNField");
		N.setText("10");
		RadioButton femaleBtn = (RadioButton) s.lookup("#task3_femaleBtn");
		femaleBtn.setSelected(true);
		clickOn("#task3_reportBtn");
		assertFalse(t.getText().isBlank());
	}


	/**
	 * Test for Task 6 (Application 3)
	 */
	@Test
	public void testTask6InvalidNames() {
		clickOn("#tabApp3");
		TextField name = (TextField) s.lookup("#task6_nameField");
		name.setText("");
		clickOn("#task6_predictBtn");
		assertTrue(t.getText().isEmpty());
		clickOn("OK");
		name.setText("David");
		TextField mateName = (TextField) s.lookup("#task6_mateNameField");
		mateName.setText("");
		clickOn("#task6_predictBtn");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void testTask6InvalidYearOfBirth() {
		clickOn("#tabApp3");
		TextField yob = (TextField) s.lookup("#task6_yobField");
		yob.setText("500");
		clickOn("#task6_predictBtn");
		assertTrue(t.getText().isEmpty());
		clickOn("OK");
		yob.setText("5000");
		clickOn("#task6_predictBtn");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void testTask6BothMaleWithPreferences() {
		clickOn("#tabApp3");
		RadioButton maleBtn = (RadioButton) s.lookup("#task6_maleBtn");
		maleBtn.setSelected(true);
		RadioButton mateMaleBtn = (RadioButton) s.lookup("#task6_mateMaleBtn");
		mateMaleBtn.setSelected(true);
		RadioButton preference = (RadioButton) s.lookup("#task6_youngerBtn");
		preference.setSelected(true);
		clickOn("#task6_predictBtn");
		assertFalse(t.getText().isBlank());
		preference = (RadioButton) s.lookup("#task6_olderBtn");
		preference.setSelected(true);
		clickOn("#task6_predictBtn");
		assertFalse(t.getText().isBlank());
	}


	@Test
	public void testTask6BothFemaleWithPreferences() {
		clickOn("#tabApp3");
		RadioButton femaleBtn = (RadioButton) s.lookup("#task6_femaleBtn");
		femaleBtn.setSelected(true);
		RadioButton mateFemaleBtn = (RadioButton) s.lookup("#task6_mateFemaleBtn");
		mateFemaleBtn.setSelected(true);
		RadioButton preference = (RadioButton) s.lookup("#task6_youngerBtn");
		preference.setSelected(true);
		clickOn("#task6_predictBtn");
		assertFalse(t.getText().isBlank());
		preference = (RadioButton) s.lookup("#task6_olderBtn");
		preference.setSelected(true);
		clickOn("#task6_predictBtn");
		assertFalse(t.getText().isBlank());
	}


	@Test
	public void testTask6OneMaleOneFemaleWithPreferences() {
		clickOn("#tabApp3");
		RadioButton maleBtn = (RadioButton) s.lookup("#task6_maleBtn");
		maleBtn.setSelected(true);
		RadioButton mateFemaleBtn = (RadioButton) s.lookup("#task6_mateFemaleBtn");
		mateFemaleBtn.setSelected(true);
		RadioButton preference = (RadioButton) s.lookup("#task6_youngerBtn");
		preference.setSelected(true);
		clickOn("#task6_predictBtn");
		assertFalse(t.getText().isBlank());
		preference = (RadioButton) s.lookup("#task6_olderBtn");
		preference.setSelected(true);
		clickOn("#task6_predictBtn");
		assertFalse(t.getText().isBlank());
		RadioButton femaleBtn = (RadioButton) s.lookup("#task6_femaleBtn");
		femaleBtn.setSelected(true);
		RadioButton mateMaleBtn = (RadioButton) s.lookup("#task6_mateMaleBtn");
		mateMaleBtn.setSelected(true);
		preference = (RadioButton) s.lookup("#task6_youngerBtn");
		preference.setSelected(true);
		clickOn("#task6_predictBtn");
		assertFalse(t.getText().isBlank());
		preference = (RadioButton) s.lookup("#task6_olderBtn");
		preference.setSelected(true);
		clickOn("#task6_predictBtn");
		assertFalse(t.getText().isBlank());
	}


}
