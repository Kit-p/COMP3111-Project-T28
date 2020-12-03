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

public class TopNamesQueryTest extends ApplicationTest{
    @Test
    public void getTopNamesTest(){
        TopNamesQuery test = new TopNamesQuery(10, "M", 1880, 1900);
        assertNotNull(test.getTopNames());
    }

    @Test
    public void getSummaryTest(){
        TopNamesQuery test = new TopNamesQuery(10, "M", 1880, 1990);
        assertNotNull(test.getSummary());
    }

    @Test
    public void getTableView(){
        TopNamesQuery test = new TopNamesQuery(10, "M", 1880, 1990);
        assertNotNull(test.getTableView());
    }
}
