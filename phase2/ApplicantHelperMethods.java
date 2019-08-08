import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApplicantHelperMethods extends Helpers implements Serializable {
    public ApplicantHelperMethods() {
    }


    public Applicant getApplicant(String applicantName) {

        applicantName = applicantName.trim();
        HashMap<String, Applicant> hmap = FileReader.readApplicantFile();
        ArrayList<Applicant> list = new ArrayList<>();
        for (Map.Entry<String, Applicant> currentApplicant : hmap.entrySet()) {
            Applicant applicant = currentApplicant.getValue();
            if (applicant.username.equals(applicantName) | applicant.name.equals(applicantName)) {
                list.add(applicant);
            }
        }
        switch (list.size()) {
            case 0:
                return null;
            case 1:
                return list.get(0);
            default:
                System.out.println("there is more than one applicants with this name");//TODO:  Consider the case where two applicants have the same name
                return null;
        }
    }


    public boolean applicantExists(String applicantName) {
        //TODO:  Consider the case where two applicants have the same name
        // read the file to get the applicant
        applicantName = applicantName.trim();
        HashMap<String, Applicant> hmap = FileReader.readApplicantFile();
        for (Map.Entry<String, Applicant> currentApplicant : hmap.entrySet()) {
            Applicant applicant = currentApplicant.getValue();
            if (applicant.username.equals(applicantName) | applicant.name.equals(applicantName)) {
                return true;
            }
        }return false;
    }


    public void printApplicantDocs(Applicant applicant) {
        if (applicant == null) {
            System.out.println("");
            return;
        }
        if (applicant.coverLetter.equals(null) && applicant.cv.equals(null)) {
            System.out.println("The applicant's documents are not available.");
        } else {
            if (applicant.coverLetter != null) {
                System.out.println(applicant.coverLetter);
            }
            if (applicant.cv != null) {
                System.out.println(applicant.cv);
            }
        }
    }

    public boolean usernameExists(String username) {
        HashMap<String, Applicant> hashMap = FileReader.readApplicantFile();
        if (hashMap.isEmpty()) {
            return false;
        }
        for (Map.Entry<String, Applicant> currentApplicant : hashMap.entrySet()) {
            if (currentApplicant.getValue().username.equals(username)) {
                return true;
            }
        }
        return false;
    }

    public Applicant getApplicantWithUsername(String username) {
        HashMap<String, Applicant> hashMap = FileReader.readApplicantFile();
        if (hashMap.isEmpty()) {
            System.out.println("ApplicantHelperMethods.getApplicantWithUsername(): Asking to get the user without checking if it exits");
        }
        for (Applicant currentApplicant : hashMap.values()) {
            if (currentApplicant.username.equals(username)) {
                return currentApplicant;
            }
        }
        return null;
    }


    public String checkStatuses(Applicant applicant) {
        // should print the type of job posting and the company of hiring and the status
        String toreturn = "";
        if (applicant.jobsAppliedTo.size() < 1) {
            return "Cuurently you have not applied to any Job.";
        }


        for (int i = 0; i < applicant.jobsAppliedTo.size(); i++) {
            toreturn += "Hiring Company: " + applicant.jobsAppliedTo.get(i).hiringCompany +
                    ", Job title: " + applicant.jobsAppliedTo.get(i).jobType +
                    ", Status: " + applicant.jobsAppliedTo.get(i).applicantStatus.getPoolContainingValue(applicant) + "\n";
        }
        return toreturn;
    }

}
