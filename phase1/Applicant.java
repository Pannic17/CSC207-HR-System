import java.util.ArrayList;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

public class Applicant {
    public String Name;
    public String password;
    public ArrayList<JobPosting> appliedTo;
    public ArrayList<JobPosting> allJobsAppliedTo;
    String cv;
    String coverLetter;

    public Applicant(String name) {
        this.Name = name;
        this.appliedTo = new ArrayList<>();
        this.allJobsAppliedTo = new ArrayList<>();
        this.cv = "";
        this.coverLetter = "";
    }

    public Applicant(String name, String password) {
        this.Name = name;
        this.password = password;
        this.appliedTo = new ArrayList<>();
        this.allJobsAppliedTo = new ArrayList<>();
    }

    public String toString() {
        // TODO:return the username and any notification
        return "";
    }


    // TODO: Method added by Marko
//     Doesn't need to be initialized
    public static JobPosting findPosting(String title) {
        for (int i = 0; i <= JobApplicationSystem.Jobs.size(); i++) {    //TODO: check if <= is required or =
            if (JobApplicationSystem.Jobs.get(i).Title.equals(title)) {
                return JobApplicationSystem.Jobs.get(i);
            }
        }
        return null;
    }

    ArrayList<JobPosting> filterJobs(String tag)
    {
        ArrayList <JobPosting> filtered = new ArrayList<>();
        for (int i = 0; i <= JobApplicationSystem.Jobs.size(); i++)
        {
            if (JobApplicationSystem.Jobs.get(i).tags.contains(tag))
            {
                filtered.add(JobApplicationSystem.Jobs.get(i));
            }
        }
        return filtered;
    }

    // Added by leila
    public String getCV() {
        return this.cv;
    }

    // Added by leila
    public void setCV() {
        this.cv = this.submitDocuments();
    }

    // Added by leila
    public String getCoverLetter() {
        return this.coverLetter;
    }

    // Added by leila
    public void setCoverLetter() {
        this.coverLetter = this.submitDocuments();
    }

    // Added by leila
//     Doesn't need to be initialized
    private String submitDocuments() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Submit your document by copying the text and pasting here.");
        String doc = scan.nextLine();
        StringBuilder lines = new StringBuilder();
        int i = 0;
        while (i < doc.length()) {
            int endIndex = Math.min(i + 70, doc.length());
            lines.append(doc.substring(i, endIndex));
            lines.append('\n');
            i = endIndex;
        }

        return lines.toString();
    }

    // Added by leila
    public void resubmissionCheck() {
        int lastApplication = appliedTo.size() - 1;
        LocalDate closed = appliedTo.get(lastApplication).CloseDate;
        long dayDifference = ChronoUnit.DAYS.between(LocalDate.now(), closed);
        int dayDiff = ((int) dayDifference);
        if (dayDiff >= 30) {
            this.deleteDocuments();
            System.out.println("No CV or cover letter on file. Please resubmit next time you apply.");
        }
    }

    // Added by leila
    private void deleteDocuments() {
        this.coverLetter = "";
        this.cv = "";
    }

    // Added by leila
    private void updateDocuments() {
        System.out.println("Please submit your CV first.");
        this.setCV();
        System.out.println("Your application is almost complete. Please submit cover letter next.");
        this.setCoverLetter();
    }



    public void applyToPosting(JobPosting posting) {
        for (int i = 0; i <= JobApplicationSystem.Jobs.size(); i++) {
            if (JobApplicationSystem.Jobs.get(i).Title.equals(posting.Title)) {
                JobApplicationSystem.Jobs.get(i).applicantList.add(this);
                this.appliedTo.add(JobApplicationSystem.Jobs.get(i));
                this.allJobsAppliedTo.add(JobApplicationSystem.Jobs.get(i));
                // Edited by leila
                if (cv.isEmpty()) {
                    this.updateDocuments();
                }
                System.out.println("Applied");
            } else {
                System.out.println("Doesn't contain this job");
            }
        }
    }
    // check which list this applicant is in, for a particualar posting

    public void viewStatus(JobPosting posting) {
        for (int i = 0; i <= JobApplicationSystem.Jobs.size(); i++) {
            if (JobApplicationSystem.Jobs.get(i).Title.equals(posting.Title)) {
                if (JobApplicationSystem.Jobs.get(i).hiredList.contains(this)) {
                    System.out.println("Hired");
                } else if (JobApplicationSystem.Jobs.get(i).applicantList.contains(this)) {
                    System.out.println("Applied");
                }

            }
        }
    }

    public void withdraw(JobPosting posting) {
        for (int i = 0; i <= JobApplicationSystem.Jobs.size(); i++) {
            if (JobApplicationSystem.Jobs.get(i).Title.equals(posting.Title)) {
                JobApplicationSystem.Jobs.get(i).applicantList.remove(this);
                this.appliedTo.remove(JobApplicationSystem.Jobs.get(i));
                System.out.println("Withdrawn");
            } else {
//                TODO:
                System.out.println("Doesn't contain this job");
            }
        }
    }
    //     Doesn't need to be initialized
    public static boolean applicantExist(String username) {
        for (int i = 0; i < JobApplicationSystem.Applicants.size(); i++) {
            if (JobApplicationSystem.Applicants.get(i).Name.equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void checkStatuses() {
        // print out every job and its status next to it.
        for (int i = 0; i < this.appliedTo.size(); i++) {
            System.out.println(this.appliedTo.get(i).printForApplicant());
            System.out.println("Job Status: " + this.getJobStatus(this.allJobsAppliedTo.get(i)) + "\n");

        }
    }

    // doesn't need to be initialized
    public static boolean checkPasswordMatch(String username, String Password) {
//        read the file and check if the username exists -- Already checked
        // get the file as a string
        String str = new String();
        ReadFile rf = new ReadFile();
        str = rf.returnIt(JobApplicationSystem.applicantFileName);
        // check if the username and password match
        CharSequence charSequence = Password;
        return str.contains(charSequence);
    }

    public String getJobStatus(JobPosting job) {
        // check if the postion has been filled
        if (job.checkStatus().equals("Filled")) {
            // if yes then check if this applicant is hired
            if (job.checkApplicantHired(this)) {
                return "Hired";
            } else {
                return "Not Hired";
            }
        } else {// if no then return applied
            return "Applied";
        }
    }

    //  TODO: implement notifcation interface
    public void getNotifiations() {

    }

    public ArrayList<JobPosting> getHistory() {
        // TODO: need a prompt to print it
        return allJobsAppliedTo;
    }


    public void checkInterview() {


    }

}
