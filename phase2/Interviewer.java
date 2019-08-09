import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Interviewer implements Serializable {
    public String username;
    public String password;
    public String name;
    public ArrayList<Applicant> applicantsInterviewingList;
    public ArrayList<Applicant> recommendedList;
    public ArrayList<Applicant> notRecommendedList;
    public JobPosting assignedJob = null;

    public Interviewer(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.applicantsInterviewingList = new ArrayList<>();
        this.recommendedList = new ArrayList<>();
        this.notRecommendedList = new ArrayList<>();

    }

    public void assignJob(JobPosting job) {
        if (this.assignedJob != null) {
            System.out.println(this.name + " has already been assigned " + this.assignedJob.jobType);
        }
        this.assignedJob = job;
    }


    @Override
    public String toString() {
        return "     username: " + this.username +
                "   ,Name: " + this.name +
                "   ,Number of Applicants to interview: " + this.applicantsInterviewingList.size() + "\n";
    }

    public void resultsForHR() { //TODO: return HashMap<String, ArrayList<Applicant>>

    }

    public void printListOfApplicantsInterviewing() {
        InterviewerHelperMethods methods = new InterviewerHelperMethods();
        if (this.applicantsInterviewingList.size() < 1) {
            System.out.println("There are no Applicants to Interview");
        } else {
            System.out.println(methods.applicantArraytoString(applicantsInterviewingList));
        }
    }




}
