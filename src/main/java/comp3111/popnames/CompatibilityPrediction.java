package comp3111.popnames;


/**
 * Task 6 (Application 3) Class that predicts the compatibility of a pair
 */
public class CompatibilityPrediction {
    /**
     * name queried
     */
    private final String name;
    /**
     * gender queried
     */
    private final String gender;
    /**
     * year of birth queried
     */
    private final int yob;
    /**
     * name of mate queried
     */
    private final String mateName;
    /**
     * gender of mate queried
     */
    private final String mateGender;
    /**
     * age preference of mate queried (+1 / -1)
     */
    private final int preference;


    /**
     * Constructor of the query
     *
     * @param name          name queried
     * @param gender        gender queried
     * @param yob           year of birth queried
     * @param mateName      name of mate queried
     * @param mateGender    gender of mate queried
     * @param preference    age preference of mate queried (+1 / -1)
     */
    public CompatibilityPrediction(String name, String gender, int yob
            , String mateName, String mateGender, int preference) {
        this.name = name;
        this.gender = gender;
        this.yob = yob;
        this.mateName = mateName;
        this.mateGender = mateGender;
        this.preference = preference;
    }


    /**
     * Get the compatibility prediction of the user and the mate
     *
     * <p>
     * <strong>Algorithm:</strong><br>
     * The algorithm computes the difference between the ranks of the names of the pair in their own year,
     * and the maximum possible difference between the ranks of the names of the pair in their own year.
     * <br>
     * The ratio implies how close their ranks are and is then converted to percentage (corrected to 2 d.p.).
     * The percentage is the predicted compatibility of the pair.
     * <br>
     * Exception case is when any of the names of the pair is not ranked on the corresponding year,
     * the algorithm will substitute it with the lowest possible rank of the corresponding gender of the corresponding year.
     * </p>
     *
     * <p>
     * <strong>Justification:</strong><br>
     * The original NK-T6 Algorithm of Universal Compatibility computes a variation of the percentage difference,
     * which will result in values that are smaller than 0% or greater than 100%.
     * <br>
     * The modified algorithm uses the rank difference as the numerator and the maximum difference as the denominator.
     * <br>
     * Since the difference is always greater than or equal to zero,
     * and is always smaller than or equal to the maximum difference,
     * the result will always be between 0% and 100%, which is consistent with the expected output format.
     * <br>
     * The algorithm is used because we believe it is difficult for the two names of the pair to have the same rank.
     * The closer the ranks are, the lower the possibility is.
     * Such rarity resembles the concept of serendipity,
     * which we use as the criteria for predicting compatibility.
     * <br>
     * The reason why the algorithm substitutes the rank of names that is not ranked with the lowest rank of the corresponding gender of the corresponding year,
     * is to ensure the user will always see a reasonable output (0% - 100%), no matter what names the input is,
     * so they will not be disappointed with an error. Choosing the lowest possible rank is because that is the best estimate of the rank,
     * given that names that are not ranked would be rare and low on occurrence if not none.
     * </p>
     *
     * @return the compatibility scaled by 100 (for percentage)
     */
    public double getPrediction() {
        double rank = AnalyzeNames.getRankEnhanced(yob, name, gender);
        double mateRank = AnalyzeNames.getRankEnhanced(yob + preference, mateName, mateGender);
        double lowestRank = AnalyzeNames.getGenderLowestRankOfYear(gender, yob);
        double mateLowestRank = AnalyzeNames.getGenderLowestRankOfYear(mateGender, yob + preference);
        if (rank < 1) {
            rank = lowestRank;
        }
        if (mateRank < 1) {
            mateRank = mateLowestRank;
        }
        double difference = Math.abs(rank - mateRank);
        double maxDifference = Math.max(lowestRank, mateLowestRank) - 1;
        return Math.round((1 - difference / maxDifference) * 10000) / 100.0;
    }
}
