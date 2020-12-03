package comp3111.popnames;

import javafx.scene.control.TableView;
import org.junit.Test;

import static org.junit.Assert.*;

public class NamePopularityQueryTest {
    @Test
    public void task2NormalTest1() {
        NamePopularityQuery query = new NamePopularityQuery("David", "M", 1941, 2000);
        String summary = query.getSummary();
        assertFalse(summary.isBlank());
    }


    @Test
    public void task2NormalTest2() {
        NamePopularityQuery query = new NamePopularityQuery("Angel", "F", 1940, 2000);
        String summary = query.getSummary();
        assertFalse(summary.isBlank());
    }


    @Test
    public void task2OneTopYear() {
        NamePopularityQuery query = new NamePopularityQuery("David", "M", 1949, 1950);
        String summary = query.getSummary();
        assertFalse(summary.isBlank());
    }


    @Test
    public void task2MultipleTopYear() {
        NamePopularityQuery query = new NamePopularityQuery("David", "M", 1941, 1945);
        String summary = query.getSummary();
        assertFalse(summary.isBlank());
    }

    @Test
    public void task2EndYearNotRanked() {
        NamePopularityQuery query = new NamePopularityQuery("Zeferino", "M", 1949, 1950);
        String summary = query.getSummary();
        assertFalse(summary.isBlank());
    }

    @Test
    public void task2AllNotRanked() {
        NamePopularityQuery query = new NamePopularityQuery("adsgfafsdf", "M", 1949, 1950);
        String summary = query.getSummary();
        assertFalse(summary.isBlank());
    }

    @Test
    public void task2GetTableView() {
        NamePopularityQuery query = new NamePopularityQuery("Angel", "F", 1940, 2000);
        TableView<NamePopularityQuery.NamePopularityQueryDataRow> table = query.getTableView();
        assertTrue(table != null && table.getItems() != null && table.getItems().size() > 0);
    }
}
