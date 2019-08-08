import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;


public class JobApplicationSystem {

    public JobApplicationSystem() {
    }

    public static LocalDate today = LocalDate.now(); // Format is year-month-date
    public static String applicantFileName = "ApplicantFile";
    public static String interviewerFileName = "InterviewerFile";
    public static String HrFileName = "HRFile";
    public static String jobPostingFileName = "JobPostingFile";


//    public HashMap<Integer, JobPosting> JobPostingList = FileReader.readJobPostingFile();
//    public HashMap<String, Applicant> ApplicantList = FileReader.readApplicantFile();
//    public HashMap<String, Interviewer> InterviewerList = FileReader.readInterviewerFile();
//    public HashMap<String, HRCoordinator> HRCoordinatorList = FileReader.readHRcoordinatorFile();


    public HashMap<String, Applicant> getApplicantList() {
        return FileReader.readApplicantFile();
    }

    public HashMap<String, HRCoordinator> getHRCoordinatorList() {
        return FileReader.readHRcoordinatorFile();
    }

    public HashMap<String, Interviewer> getInterviewerList() {
        return FileReader.readInterviewerFile();
    }

    public HashMap<Integer, JobPosting> getJobPostingList() {
        return FileReader.readJobPostingFile();
    }


    public static void main(String[] args) {
        LoginClass lg = new LoginClass();
        System.out.println("Please select one of the option Below" + "\n1. Configure (For first time)" + "\n2. Enter" + "\n3. Exit");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                lg.startingPrompt();
                break;
            case 2:
                lg.startingPrompt2();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                lg.startingPrompt();
        }


    }
}
