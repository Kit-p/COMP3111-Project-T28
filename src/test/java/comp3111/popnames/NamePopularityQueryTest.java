package comp3111.popnames;

import javafx.scene.control.TableView;
import org.junit.Test;

import static org.junit.Assert.*;

public class NamePopularityQueryTest {
    @Test
    public void task2Normaltest1() {
        NamePopularityQuery query = new NamePopularityQuery("David", "M", 1941, 2000);
        String summary = query.getSummary();
        assertFalse(summary.isEmpty());
    }


    @Test
    public void task2Normaltest2() {
        NamePopularityQuery query = new NamePopularityQuery("Angel", "F", 1940, 2000);
        String summary = query.getSummary();
        assertFalse(summary.isEmpty());
    }


    @Test
    public void task2OneTopYear() {
        NamePopularityQuery query = new NamePopularityQuery("David", "M", 1949, 1950);
        String summary = query.getSummary();
        assertFalse(summary.isEmpty());
    }


    @Test
    public void task2MultipleTopYear() {
        NamePopularityQuery query = new NamePopularityQuery("David", "M", 1941, 1945);
        String summary = query.getSummary();
        assertFalse(summary.isEmpty());
    }

    @Test
    public void task2endYearNotRanked() {
        NamePopularityQuery query = new NamePopularityQuery("Zeferino", "M", 1949, 1950);
        String summary = query.getSummary();
        assertFalse(summary.isEmpty());
    }

    @Test
    public void task2allnotRanked() {
        NamePopularityQuery query = new NamePopularityQuery("adsgfafsdf", "M", 1949, 1950);
        String summary = query.getSummary();
        assertFalse(summary.isEmpty());
    }

    @Test
    public void task2getTableView() {
        NamePopularityQuery query = new NamePopularityQuery("Angel", "F", 1940, 2000);
        TableView<NamePopularityQuery.NamePopularityQueryDataRow> table = query.getTableView();
        assertNotNull(table);
    }
}
