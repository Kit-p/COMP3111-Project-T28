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

public class BabyNameRecommendationTest extends ApplicationTest {
    @Test
    public void getBabyRecommendationTestDadRank(){
        BabyNameRecommendation test = new BabyNameRecommendation(1980, 1975, "aaaa", "Emilia", 2019);
        assertNotNull(test.getBabiesNames());
    }

    @Test
    public void getBabyRecommendationTestMumRank(){
        BabyNameRecommendation test = new BabyNameRecommendation(1980, 1975, "George", "aaaa", 2019);
        assertNotNull(test.getBabiesNames());
    }

    @Test
    public void getBabyRecommendationTestRank(){
        BabyNameRecommendation test = new BabyNameRecommendation(1980, 1975, "George", "Emilia", 2006);
        assertNotNull(test.getBabiesNames());
    }
}
