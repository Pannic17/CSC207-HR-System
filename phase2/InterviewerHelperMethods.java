import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InterviewerHelperMethods extends Helpers implements Serializable {
    public InterviewerHelperMethods() {
    }

    public boolean interviewerExists(String interviewerName) {
        //TODO:  Consider the case where two applicants have the same name
        // read the file to get the interviewer
        HashMap<String, Interviewer> hmap = FileReader.readInterviewerFile();
        // if you find the same name then return all the things of the applicant
        if (hmap.containsKey(interviewerName)) {
            return true;
        } else {
            return false;
        }
    }
    public  void assignApplicantToInteviewer(Interviewer interviewer) {
        //check if the interviewer has been assigned a applicant pool
        ApplicantHelperMethods helpers = new ApplicantHelperMethods();
        if (interviewer.assignedJob == null) {
            System.out.println("Please Assign a job to the interviewer First");
        } else {

            // print the list of applicants in the applicant pool
            System.out.println(helpers.applicantArraytoString(interviewer.assignedJob.applicantStatus.getAllApplicants()));
            // ask for the name of the applicant
            System.out.println("Please enter the name of the Applicant To view  his/her Documents");
            Scanner in2 = new Scanner(System.in);
            String name = in2.nextLine();
            Applicant applicant = helpers.getApplicant(name);
            // move the applicant to the next interview pool
            interviewer.applicantsInterviewingList.add(applicant);
            interviewer.assignedJob.applicantStatus.moveApplicantToNextPool(applicant);
        }
    }
    public boolean usernameExists(String username) {
        HashMap<String, Interviewer> hashMap = FileReader.readInterviewerFile();
        if (hashMap.isEmpty()) {
            return false;
        }
        if (hashMap.containsKey(username)) {
            return true;
        } else {
            return false;
        }
    }


    public Interviewer getInterviewerWithUsername(String username) {
        HashMap<String, Interviewer> hashMap = FileReader.readInterviewerFile();
        if (hashMap.isEmpty()) {
            System.out.println("InterviewerHelperMethods.getInterviewerWithUsername(): Asking to get the user without checking if it exits");
        }for (Interviewer currentInterviewer : hashMap.values()) {
            if (currentInterviewer.username.equals(username)) {
                return currentInterviewer;
            }
        }
        return null;
    }

    public Interviewer getInterviewerWithName(String name) {
        HashMap<String, Interviewer> hashMap = FileReader.readInterviewerFile();

        for (Interviewer currentInterviewer : hashMap.values()) {
            if (currentInterviewer.name.equals(name)) {
                return currentInterviewer;
            }
        }
        System.out.println("Interviewer with this name doesn't exist.");
        return null;
    }


}
