package comp3111.popnames;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CompatibilityPredictionTest {

    @Test
    public void testGetPrediction() {
        CompatibilityPrediction query = new CompatibilityPrediction("Alice", "F", 1881, "Ella", "F", 1);
        double result = query.getPrediction();
        assertTrue(result >= 0 && result <= 100);
        query = new CompatibilityPrediction("AAA", "F", 1881, "BBB", "F", -1);
        result = query.getPrediction();
        assertTrue(result >= 0 && result <= 100);
    }

}
