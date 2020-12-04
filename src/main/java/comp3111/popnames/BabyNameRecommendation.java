package comp3111.popnames;

import com.sun.javafx.binding.StringFormatter;

public class BabyNameRecommendation {
    private final int dadYOB;
    private final int mumYOB;
    private final String dadName;
    private final String mumName;
    private final int vintageYear;

    BabyNameRecommendation(int dadYOB, int mumYOB, String dadName, String mumName, int vintageYear){
        this.dadYOB = dadYOB;
        this.mumYOB = mumYOB;
        this.dadName = dadName;
        this.mumName = mumName;
        this.vintageYear = vintageYear;
    }

    /**
     * getBabiesNames()
     * First get rank of the father's and mother's name at their YOB
     * Get a boy's name and a girl's name with the same rank as dadRank and mumRank in vintageYear
     * @return string with recommendation for boy's name and girl's name
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
