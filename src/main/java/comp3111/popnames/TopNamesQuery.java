package comp3111.popnames;

public class TopNamesQuery {
    private int NumberOfNames;
    private String gender;
    private int startYear;
    private int endYear;

    TopNamesQuery(int number, String gender, int startYear, int endYear){
        this.NumberOfNames = number;
        this.gender = gender;
        this.startYear = startYear;
        this.endYear = endYear;
    }



}
