package comp3111.popnames;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TopNamesQuery {
    private int NumberOfNames;
    private String gender;
    private int startYear;
    private int endYear;

    /** constructor for TopNamesQuery instance
         @param number
         @param endYear
         @param gender
         @param startYear
     */
    TopNamesQuery(int number, String gender, int startYear, int endYear){
        this.NumberOfNames = number;
        this.gender = gender;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public HashMap<Integer, String[]> getTopNames(){
        HashMap<Integer, String[]> result = new HashMap<>();
        for(int currentYear = this.startYear; currentYear <= this.endYear; currentYear++ ){
            String names[] = new String[10];
            for(int j = 0; j < 10; j++){
                names[j] = AnalyzeNames.getName(currentYear, j, this.gender);
            }
            result.put(currentYear, names);
        }
        return result;
    }

    public String[] getSummary(){
        HashMap<String, Integer> count = new HashMap<>();
        HashMap<Integer, String[]> source = getTopNames();
        for(Integer years: source.keySet()){
            String currentName = source.get(years)[0];
            if(count.containsKey(currentName)){
                count.put(currentName, count.get(currentName)+1);
            }else{
                count.put(currentName, 1);
            }
        }
        int max = 0;
        for(String name: count.keySet()){
            if(max > count.get(name)){
                max = count.get(name);
            }
        }
        ArrayList<String> result = new ArrayList<>();
        for(String name : count.keySet()){
            if(count.get(name) == max){
                result.add(name);
            }
        }
        return (String[]) result.toArray();
    }

}
