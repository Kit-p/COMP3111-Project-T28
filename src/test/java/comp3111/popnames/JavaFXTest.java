package comp3111.popnames;

import static org.junit.Assert.*;
import org.junit.Test;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

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
	public void testTopNInvalid(){
		clickOn("#tabReport1");
		TextField inputN = (TextField) s.lookup("#inputTopN");
		inputN.setText("");
		clickOn("#task1ReportBtn");
		assertTrue(t.getText().equals(""));
	}

	@Test
	public void testTopNValid(){
		clickOn("#tabReport1");
		TextField inputN = (TextField) s.lookup("#inputTopN");
		inputN.setText("15");
		clickOn("#task1ReportBtn");
		assertEquals("Over the period 1941 to 1945, James for Male has hold the top spot most often for a total of 5 times",
				t.getText());
	}

	@Test
	public void testGenderM(){
		clickOn("#tabReport1");
		RadioButton inputMale = (RadioButton) s.lookup("#inputMale");
		inputMale.setSelected(true);
		clickOn("#task1ReportBtn");
		assertEquals("Over the period 1941 to 1945, James for Male has hold the top spot most often for a total of 5 times", t.getText());
	}

	@Test
	public void testGenderF(){
		clickOn("#tabReport1");
		RadioButton inputFemale = (RadioButton) s.lookup("#inputFemale");
		inputFemale.setSelected(true);
		clickOn("#task1ReportBtn");
		assertEquals("Over the period 1941 to 1945, Mary for Female has hold the top spot most often for a total of 5 times", t.getText());
	}

	@Test
	public void testStartYearInvalid(){
		clickOn("#tabReport1");
		TextField startYear = (TextField) s.lookup("#inputStart");
		startYear.setText("aaa");
		clickOn("#task1ReportBtn");
		assertEquals("",
				t.getText());
	}

	@Test
	public void testStartYearValid(){
		clickOn("#tabReport1");
		TextField startYear = (TextField) s.lookup("#inputStart");
		startYear.setText("1880");
		clickOn("#task1ReportBtn");
		assertEquals("Over the period 1880 to 1945, John for Male has hold the top spot most often for a total of 44 times",
				t.getText());
	}

	@Test
	public void testYearsInvalid(){
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
	public void testInputFatherName(){
		clickOn("#tabReport1");
		TextField fatherName = (TextField) s.lookup("#inputFatherName");
		fatherName.setText("zzzz");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void testInputFatherNameBlank(){
		clickOn("#tabReport1");
		TextField fatherName = (TextField) s.lookup("#inputFatherName");
		fatherName.setText("");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void testInputMotherName(){
		clickOn("#tabReport1");
		TextField motherName = (TextField) s.lookup("#inputMotherName");
		motherName.setText("zzzz");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void testInputMotherNameBlank(){
		clickOn("#tabReport1");
		TextField motherName = (TextField) s.lookup("#inputMotherName");
		motherName.setText("");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void vintageYearBlank(){
		clickOn("#tabReport1");
		TextField vintageYear = (TextField) s.lookup("#inputVintageYear");
		vintageYear.setText("");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void testInputDadYOBString(){
		clickOn("#tabReport1");
		TextField dadYOB = (TextField) s.lookup("#inputDadYOB");
		dadYOB.setText("aaa");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void testInputDadYOBInvalid(){
		clickOn("#tabReport1");
		TextField dadYOB = (TextField) s.lookup("#inputDadYOB");
		dadYOB.setText("1879");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void testInputMumYOBString(){
		clickOn("#tabReport1");
		TextField dadYOB = (TextField) s.lookup("#inputMumYOB");
		dadYOB.setText("aaa");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void testInputMumYOBInvalid(){
		clickOn("#tabReport1");
		TextField dadYOB = (TextField) s.lookup("#inputMumYOB");
		dadYOB.setText("1879");
		clickOn("#application1Run");
		assertEquals("",
				t.getText());
	}

	@Test
	public void application1Test(){
		clickOn("#tabReport1");
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
		assertEquals("",
				t.getText());
	}



	@Test
	public void task2blankname() {
		clickOn("#tabReport2");
		TextField task2NameField = (TextField) s.lookup("#task2NameField");
		task2NameField.setText("");
		clickOn("#NamePopularityQueryButton");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void task2TextstartYear() {
		clickOn("#tabReport2");
		TextField startYear = (TextField) s.lookup("#task2StartYear");
		startYear.setText("abcd");
		clickOn("#NamePopularityQueryButton");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void task2SmallInvalidstartYear() {
		clickOn("#tabReport2");
		TextField startYear = (TextField) s.lookup("#task2StartYear");
		startYear.setText("1000");
		TextField endYear = (TextField) s.lookup("#task2EndYear");
		endYear.setText("2000");
		clickOn("#NamePopularityQueryButton");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void task2LargeInvalidstartYear() {
		clickOn("#tabReport2");
		TextField startYear = (TextField) s.lookup("#task2StartYear");
		startYear.setText("3000");
		TextField endYear = (TextField) s.lookup("#task2EndYear");
		endYear.setText("3500");
		clickOn("#NamePopularityQueryButton");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void task2TextendYear() {
		clickOn("#tabReport2");
		TextField endYear = (TextField) s.lookup("#task2EndYear");
		endYear.setText("abcd");
		clickOn("#NamePopularityQueryButton");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void task2SmallInvalidendYear() {
		clickOn("#tabReport2");
		TextField startYear = (TextField) s.lookup("#task2StartYear");
		startYear.setText("1999");
		TextField endYear = (TextField) s.lookup("#task2EndYear");
		endYear.setText("1000");
		clickOn("#NamePopularityQueryButton");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void task2LargeInvalidendYear() {
		clickOn("#tabReport2");
		TextField startYear = (TextField) s.lookup("#task2StartYear");
		startYear.setText("1999");
		TextField endYear = (TextField) s.lookup("#task2EndYear");
		endYear.setText("3000");
		clickOn("#NamePopularityQueryButton");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void task2startLargerEnd() {
		clickOn("#tabReport2");
		TextField startYear = (TextField) s.lookup("#task2StartYear");
		startYear.setText("2000");
		TextField endYear = (TextField) s.lookup("#task2EndYear");
		endYear.setText("1999");
		clickOn("#NamePopularityQueryButton");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void task2clickFemale() {
		clickOn("#tabReport2");
		clickOn("#task2Female");
		clickOn("#NamePopularityQueryButton");
		assertFalse(t.getText().isEmpty());
	}


	@Test
	public void task2normal() {
		clickOn("#tabReport2");
		TextField task2NameField = (TextField) s.lookup("#task2NameField");
		task2NameField.setText("Oscar");
		TextField startYear = (TextField) s.lookup("#task2StartYear");
		startYear.setText("1990");
		TextField endYear = (TextField) s.lookup("#task2EndYear");
		endYear.setText("2000");
		clickOn("#task2Male");
		clickOn("#NamePopularityQueryButton");
		assertFalse(t.getText().isEmpty());
	}


	@Test
	public void task5blankName() {
		clickOn("#tabApp2");
		TextField task5NameField = (TextField) s.lookup("#task5NameField");
		task5NameField.setText("");
		TextField yob = (TextField) s.lookup("#task5_YOB");
		yob.setText("1990");
		clickOn("#task5_predictBtn");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void task5textYear() {
		clickOn("#tabApp2");
		TextField task5NameField = (TextField) s.lookup("#task5NameField");
		task5NameField.setText("David");
		TextField yob = (TextField) s.lookup("#task5_YOB");
		yob.setText("asdf");
		clickOn("#task5_predictBtn");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void task5smallYear() {
		clickOn("#tabApp2");
		TextField task5NameField = (TextField) s.lookup("#task5NameField");
		task5NameField.setText("James");
		TextField yob = (TextField) s.lookup("#task5_YOB");
		yob.setText("1879");
		clickOn("#task5_predictBtn");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void task5largeYear() {
		clickOn("#tabApp2");
		TextField task5NameField = (TextField) s.lookup("#task5NameField");
		task5NameField.setText("James");
		TextField yob = (TextField) s.lookup("#task5_YOB");
		yob.setText("2020");
		clickOn("#task5_predictBtn");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void task5younger1880() {
		clickOn("#tabApp2");
		TextField task5NameField = (TextField) s.lookup("#task5NameField");
		task5NameField.setText("James");
		TextField yob = (TextField) s.lookup("#task5_YOB");
		yob.setText("1880");
		clickOn("#task5_predictBtn");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void task5older2019() {
		clickOn("#tabApp2");
		TextField task5NameField = (TextField) s.lookup("#task5NameField");
		task5NameField.setText("James");
		TextField yob = (TextField) s.lookup("#task5_YOB");
		yob.setText("2019");
		clickOn("#task5_olderBtn");
		clickOn("#task5_predictBtn");
		assertTrue(t.getText().isEmpty());
	}


	@Test
	public void task5noraml1() {
		clickOn("#tabApp2");
		TextField task5NameField = (TextField) s.lookup("#task5NameField");
		task5NameField.setText("Angel");
		TextField yob = (TextField) s.lookup("#task5_YOB");
		yob.setText("1880");
		clickOn("#task5_femaleBtn");
		clickOn("#task5_mateFemaleBtn");
		clickOn("#task5_olderBtn");
		clickOn("#task5_predictBtn");
		assertFalse(t.getText().isBlank());
	}


	@Test
	public void task5noraml2() {
		clickOn("#tabApp2");
		TextField task5NameField = (TextField) s.lookup("#task5NameField");
		task5NameField.setText("Angel");
		TextField yob = (TextField) s.lookup("#task5_YOB");
		yob.setText("2019");
		clickOn("#task5_femaleBtn");
		clickOn("#task5_mateFemaleBtn");
		clickOn("#task5_youngerBtn");
		clickOn("#task5_predictBtn");
		assertFalse(t.getText().isBlank());
	}


	@Test
	public void task5noraml3() {
		clickOn("#tabApp2");
		TextField task5NameField = (TextField) s.lookup("#task5NameField");
		task5NameField.setText("James");
		TextField yob = (TextField) s.lookup("#task5_YOB");
		yob.setText("1990");
		clickOn("#task5_predictBtn");
		assertFalse(t.getText().isBlank());
	}
}
