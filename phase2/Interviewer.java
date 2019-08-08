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
        this.applicantsInterviewingList = new ArrayList<Applicant>();
        this.recommendedList = new ArrayList<Applicant>();
        this.notRecommendedList = new ArrayList<Applicant>();

    }

    public void assignJob(JobPosting job) {
        this.assignedJob = job;
    }


    @Override
    public String toString() {
        return "username: " + this.username +
                "Name: " + this.name +
                "Number of Applicants to interview: " + this.applicantsInterviewingList.size();
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

    public void setInterviewType() {
        System.out.println("Enter the number of the interview (1, 2, or 3) that you wish to assign a type to");
        Scanner input = new Scanner(System.in);
        String interviewNum = input.nextLine();
        if (interviewNum.equals("1")) {
            System.out.println("Enter the interview type: \"phone\", \"in-person\", or \"video-call\":");
            String selectedType = input.nextLine();
            setInterviewTypeHelper(selectedType);
        }
        if (interviewNum.equals("2")) {
            System.out.println("Enter the interview type: \"phone\", \"in-person\", or \"video-call\":");
            String selectedType = input.nextLine();
            setInterviewTypeHelper(selectedType);
        }
        if (interviewNum.equals("3")) {
            System.out.println("Enter the interview type: \"phone\", \"in-person\", or \"video-call\":");
            String selectedType = input.nextLine();
            setInterviewTypeHelper(selectedType);
        }

    }

    public void setInterviewTypeHelper(String type) {
        ApplicantStatusMap currentMap = new ApplicantStatusMap("null", "null","null");

        if (type.equals("phone")) {
            currentMap.setInterview1Type("phone");
        }
        if (type.equals("in-person")) {
            currentMap.setInterview1Type("in-person");
        }
        if (type.equals("video-call")) {
            currentMap.setInterview1Type("video-call");
        }
    }

    public void assignApplicantToInteviewer() {
        //check if the interviewer has been assigned a job
        ApplicantHelperMethods helpers = new ApplicantHelperMethods();
        if (this.assignedJob == null) {
            System.out.println("Please Assign a job to the interviewer First");
        }else{

            // print the list of applicants in the job
            System.out.println(helpers.applicantArraytoString(this.assignedJob.applicantStatus.getAllApplicants()));
            // ask for the name of the applicant
            System.out.println("Please enter the name of the Applicant To view  his/her Documents");
            Scanner in2 = new Scanner(System.in);
            String name = in2.nextLine();
            Applicant applicant = helpers.getApplicant(name);
            // move the applicant to the next interview pool
            this.applicantsInterviewingList.add(applicant);
            this.assignedJob.applicantStatus.moveApplicantToNextPool(applicant);
        }
    }


}
