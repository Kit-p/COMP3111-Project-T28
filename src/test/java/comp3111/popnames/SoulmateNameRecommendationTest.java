package comp3111.popnames;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SoulmateNameRecommendationTest {

    @Test
    public void task5UserNotRanked() {
        SoulmateNameRecommendation query =
                new SoulmateNameRecommendation("tasdfasfd", "M", 2010, "F","younger");
        String soulmateName = query.getSoulmateName();
        assertEquals("Aaden", soulmateName);
    }

    @Test
    public void task5RankNotFound() {
        SoulmateNameRecommendation query =
                new SoulmateNameRecommendation("Adra", "F", 1901, "M","younger");
        String soulmateName = query.getSoulmateName();
        assertEquals("Abron", soulmateName);
    }

    @Test
    public void task5Normal1() {
        SoulmateNameRecommendation query =
                new SoulmateNameRecommendation("Adam", "M", 2009, "M","older");
        String soulmateName = query.getSoulmateName();
        assertEquals("Colton", soulmateName);
    }

    @Test
    public void task5Normal2() {
        SoulmateNameRecommendation query =
                new SoulmateNameRecommendation("Colton", "M", 2010, "M","younger");
        String soulmateName = query.getSoulmateName();
        assertEquals("Adam", soulmateName);
    }
}
