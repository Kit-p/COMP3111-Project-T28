package comp3111.popnames;

import javafx.scene.control.TableView;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class NameTrendQueryTest {

    @Test
    public void testGetNameTrend() {
        NameTrendQuery query = new NameTrendQuery("M", 1881, 1882, 187);
        ArrayList<NameTrendQuery.NameTrendQueryDataRow> result = query.getNameTrend();
        assertTrue(result != null && result.size() > 0);
    }

    @Test
    public void testGetSummary() {
        NameTrendQuery query = new NameTrendQuery("M", 1881, 1882, 187);
        String summary = query.getSummary();
        assertTrue(summary != null && summary.length() > 0);
        query = new NameTrendQuery("M", 1881, 1882, 1);
        query.getNameTrend();
        summary = query.getSummary();
        assertTrue(summary != null && summary.length() > 0);
    }

    @Test
    public void testGetTableView() {
        NameTrendQuery query = new NameTrendQuery("M", 1881, 1882, 187);
        TableView<NameTrendQuery.NameTrendQueryDataRow> table = query.getTableView();
        assertTrue(table != null && table.getItems() != null && table.getItems().size() > 0);
        query = new NameTrendQuery("M", 1881, 1882, 1);
        query.getNameTrend();
        table = query.getTableView();
        assertTrue(table != null && table.getItems() != null && table.getItems().size() > 0);
    }

}
