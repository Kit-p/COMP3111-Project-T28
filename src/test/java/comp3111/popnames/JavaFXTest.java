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
	public void testTextAreaConsoleTask1() {
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

}
