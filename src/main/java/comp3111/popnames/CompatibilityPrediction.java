package comp3111.popnames;

public class CompatibilityPrediction {
    String name;
    String gender;
    int yob;
    String mateName;
    String mateGender;
    int preference;


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
