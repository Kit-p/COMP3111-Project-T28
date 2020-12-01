package comp3111.popnames;

import javafx.scene.control.Alert;

public class SoulmateNameRecommendation {
		private String userName;
		private String userGender;
		private int userYOB;
		private String soulmateGender;
		private String soulmateAgePreference;
		
		public SoulmateNameRecommendation(String inputname, String inputuserGender, int inputuserYOB, String inputsoulmateGender, String inputAgePreference) {
			this.userName = inputname;
			this.userGender = inputuserGender;
			this.userYOB = inputuserYOB;
			this.soulmateGender = inputsoulmateGender;
			this.soulmateAgePreference = inputAgePreference;
		}
		
		public String getSoulmateName() {
			int oRank = AnalyzeNames.getRankEnhanced(userYOB, userName, userGender);

			int oYOB;
			if (soulmateAgePreference.equals("younger")) {
				oYOB = userYOB - 1;
			}else {
				oYOB = userYOB + 1;
			}

			if (oRank == -1) {
				return AnalyzeNames.getLowestName(oYOB, this.soulmateGender);
			}

			String oName = AnalyzeNames.getNameEnhanced(oYOB, oRank, soulmateGender);
			if (oName.contentEquals("information on the name at the specified rank is not available")) {
				return AnalyzeNames.getLowestName(oYOB, this.soulmateGender);
			}

			return oName;
		}
}
