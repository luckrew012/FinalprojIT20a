
package Final;

import java.util.ArrayList;
import java.util.HashMap;


public class DataStore {
     public static ArrayList<Job> jobList = new ArrayList<>();

  
    public static ArrayList<Applicant> applicantList = new ArrayList<>();
    public static ArrayList<MatchResult> matchResults = new ArrayList<>();
    
    public static HashMap<String, Job> jobMap = new HashMap<>();
    public static HashMap<String, Applicant> applicantMap = new HashMap<>();

    public static void insertionSortJobs() {
    for (int i = 1; i < jobList.size(); i++) {
        Job key = jobList.get(i);
        int j = i - 1;

        while (j >= 0 && jobList.get(j).getTitle()
                .compareToIgnoreCase(key.getTitle()) > 0) {
            jobList.set(j + 1, jobList.get(j));
            j--;
        }
        jobList.set(j + 1, key);
    }
}
    public static void insertionSortApplicants() {
    for (int i = 1; i < applicantList.size(); i++) {
        Applicant key = applicantList.get(i);
        int j = i - 1;

        while (j >= 0 && applicantList.get(j).getName()
                .compareToIgnoreCase(key.getName()) > 0) {
            applicantList.set(j + 1, applicantList.get(j));
            j--;
        }
        applicantList.set(j + 1, key);
    }
}

}
