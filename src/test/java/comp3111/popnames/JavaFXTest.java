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
}
