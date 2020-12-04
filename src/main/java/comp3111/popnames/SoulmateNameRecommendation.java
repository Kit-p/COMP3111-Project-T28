package comp3111.popnames;

import javafx.scene.control.Alert;

/**
 * Task 5 (Application 2) Class that predict the name of the compatible pairs (Soulmate) of the user
 */
public class SoulmateNameRecommendation {

	/**
	 * name of the user
	 */
	private final String userName;
	/**
	 * gender of the user
	 */
	private final String userGender;
	/**
	 * year of birth of the user
	 */
	private final int userYOB;
	/**
	 * the preferred gender of the compatible pair (soulmate)
	 */
	private final String soulmateGender;
	/**
	 * the preference of having a younger or older soulmate
	 */
	private final String soulmateAgePreference;


	/**
	 * Construct a SoulmateNameRecommendation object for soulmate name prediction
	 *
	 * @param inputname	the user name
	 * @param inputuserGender	the user gender
	 * @param inputuserYOB	the user year of birth
	 * @param inputsoulmateGender	preferred gender of the soulmate
	 * @param inputAgePreference	preference of having a younger or older soulmate
	 */
	public SoulmateNameRecommendation(String inputname, String inputuserGender, int inputuserYOB, String inputsoulmateGender, String inputAgePreference) {
		this.userName = inputname;
		this.userGender = inputuserGender;
		this.userYOB = inputuserYOB;
		this.soulmateGender = inputsoulmateGender;
		this.soulmateAgePreference = inputAgePreference;
	}


	/**
	 * Predict the name of the compatible pair (Soulmate) given the user input.
	 *
	 * <p>
	 * <strong>Algorithm:</strong>
	 * <br>
	 * The algorithm predicts the name of the soulmate of the user by finding the name that has the closest rank to the user's name rank
	 * with the preferred gender in the calculated soulmate's year of birth.
	 * <br>
	 * The algorithm first calculates the rank of the user's name in his/her year of birth.
	 * <br>
	 * Then, calculate the year of birth of the soulmate, if the preference is "younger", the soulmate's year of birth is (user's year of birth - 1),
	 * else if the preference is "older", the soulmate's year of birth is (user's year of birth + 1).
	 * <br>
	 * Finally, the algorithm finds the name in the soulmate's year of birth that has the same rank as the user's name rank,
	 * which the closest possible rank to the user's name rank, and returns that name.
	 * <br>
	 * If there do not exist that has the same rank as the user's name rank, either due to the user's name is not ranked, or
	 * the user's name rank is too low that there is not a name in the soulmate's year of birth with that rank,
	 * the algorithm returns lowest-ranked name in the soulmate's year of birth,
	 * which has the closest possible rank with name that is not ranked.
	 * </p>
	 *
	 * <p>
	 * <strong>Justification:</strong>
	 * <br>
	 * This algorithm predicts the soulmate's name by finding a name in the soulmate's year of birth
	 * with the closest rank to the user's name rank in the user's year of birth.
	 * <br>
	 * This is because the rank of the name basically represents the popularity of a name in the year (as higher rank name has larger occurrence).
	 * With so many possible names, it is very difficult for the name of 2 people to be of the same rank and be equally popular in their year of birth.
	 * <br>
	 * The closer the ranks of the 2 names are, the lower the possibility is.
	 * Such rarity resembles the concept of serendipity, which we use as the criteria for predicting the name of the soulmate of the user.
	 * <br>
	 * In addition, the soulmate's year of birth (user's year of birth - 1) or (user's year of birth + 1) because
	 * people in the same generation should have more common knowledge and interest, so age difference of 1 should make 2 people a good pair.
	 * <br>
	 * <br>
	 * The original NK-T5 Algorithm of Universal Compatibility also uses the above principle to predicts soulmate's name.
	 * <br>
	 * However, if the user's name is not ranked, or the user's name rank is too low that there is not a name in the soulmate's year of birth with that rank,
	 * the original algorithm uses the top-ranked name in the soulmate's year of birth as the predicted name.
	 * Thus, the predicted name will not have the closest rank to the user's name,
	 * but instead having the largest difference in rank and popularity.
	 * This does not resemble the concept of serendipity, and the name prediction is not coherent in all cases.
	 * <br>
	 * <br>
	 * Therefore, the modified algorithm returns the lowest-ranked name in the soulmate's year of birth in such case.
	 * As a name that is not ranked is below the lowest-ranked name, the lowest-ranked name will have the closest rank
	 * to the not ranked name.
	 * Thus, the modified algorithm will always recommend the name with the closest rank to the user's name rank,
	 * making coherent recommendations in all cases.
	 * <br>
	 * Therefore, the modified algorithm gives justifiable and coherent predictions for the soulmate name in all cases.
	 * </p>
	 *
	 * @return the predicted name for the soulmate
	 */
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
