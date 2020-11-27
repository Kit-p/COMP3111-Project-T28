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
        double oRank = AnalyzeNames.getRankEnhanced(yob, name, gender);
        double oRankMate = AnalyzeNames.getRankEnhanced(yob + preference, mateName, mateGender);
        return (Math.abs((oRank - oRankMate) / oRank) - 1) * 100;
    }
}
