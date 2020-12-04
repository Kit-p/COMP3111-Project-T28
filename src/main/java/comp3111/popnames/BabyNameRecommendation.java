package comp3111.popnames;

import com.sun.javafx.binding.StringFormatter;

/**
 * Task 4 (Application 1) Class that generate recommendations for names of newborn babies
 */
public class BabyNameRecommendation {
    /**
     * year of birth of Dad of the baby
     */
    private final int dadYOB;
    /**
     * year of birth of Mum of the baby
     */
    private final int mumYOB;
    /**
     * name of Dad of the baby
     */
    private final String dadName;
    /**
     * name of Mum of the baby
     */
    private final String mumName;
    /**
     * a random year chosen by user for baby name recommendation
     */
    private final int vintageYear;

    /**
     * Construct an BabyNameRecommendation object for querying recommendations for names of newborn babies
     * @param dadYOB    year of birth of Dad of the baby
     * @param mumYOB    year of birth of Mum of the baby
     * @param dadName   name of Dad of the baby
     * @param mumName   name of Mum of the baby
     * @param vintageYear   a random year chosen by user for baby name recommendation
     */
    BabyNameRecommendation(int dadYOB, int mumYOB, String dadName, String mumName, int vintageYear){
        this.dadYOB = dadYOB;
        this.mumYOB = mumYOB;
        this.dadName = dadName;
        this.mumName = mumName;
        this.vintageYear = vintageYear;
    }

    /**
     *Get the recommended names for a boy and a girl.
     *
     * <p>
     * <Strong>Algorithm:</Strong>
     * <br>
     * The algorithm returns 2 recommended names for the user, 1 for a boy, 1 for a girl based on the
     * the ranks of the names of the parents in their respective birth years. Then the algorithm will
     * choose 2 names with the same rank as the names of the parents and return the recommendated names.
     * If the rank of the parents' names are too low or out of bound, the algorithm will return the lowest
     * ranked names in the vintage year
     * </p>
     *
     * <p>
     * <strong>Justification:</strong>
     * The original NK-T4 algorithm of Universal compatibility will first compute the ranks of the parents,
     * which is the rank of the parents' names' in their respective year of birth.
     * Then it will compute a name for the boy and a name for the girl which has the same rank as their parents'
     * names' ranks in their respective year of birth.
     * <br>
     * However, the given algorithm will be unable to provide a recommendation when the ranks of the parents'
     * names are too low, or when the vintage year does not have a name at that rank.
     * <br>
     * Therefore, we modified the algorithm to make it return the name with the lowest rank in that year,
     * if there are multiple names with the same rank, it will return the first name in that rank.
     * </p>
     * @return recommendation for baby names
     */
    public String getBabiesNames() {
        int dadRank = AnalyzeNames.getRankEnhanced(dadYOB, dadName, "M");
        int mumRank = AnalyzeNames.getRankEnhanced(mumYOB, mumName, "F");
        String boyName = AnalyzeNames.getNameEnhanced(vintageYear, dadRank, "M");
        String girlName = AnalyzeNames.getNameEnhanced(vintageYear, mumRank, "F");
        if (boyName.equals("information on the name at the specified rank is not available")) {
            boyName = AnalyzeNames.getLowestName(vintageYear, "M");
        }
        if (girlName.equals("information on the name at the specified rank is not available")) {
            girlName = AnalyzeNames.getLowestName(vintageYear, "F");
        }
        String result = String.format("Recommendation for a boy's name: %s \nRecommendation for a girl's name: %s", boyName, girlName);
        return result;
    }

}
