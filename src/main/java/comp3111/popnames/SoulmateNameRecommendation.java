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
			return "";
		}
}
