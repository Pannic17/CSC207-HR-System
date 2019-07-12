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
    // edited by tejbeer (made it static)
    public static JobPosting findPosting(String title) {
        for (int i = 0; i <= JobApplicationSystem.Jobs.size(); i++) {    //TODO: check if <= is required or =
            if (JobApplicationSystem.Jobs.get(i).Title.equals(title)) {
                return JobApplicationSystem.Jobs.get(i);
            }
        }
        return null;
    }

    public String getCV() {
        return this.cv;
    }

    public void setCV() {
        this.cv = this.submitDocuments();
    }

    public String getCoverLetter() {
        return this.coverLetter;
    }

    public void setCoverLetter() {
        this.coverLetter = this.submitDocuments();
    }

    private String submitDocuments()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Submit your document by copying the text and pasting here.");
        String doc = scan.nextLine();
        StringBuilder lines = new StringBuilder();
        int i = 0;
        while (i < doc.length())
        {
            int endIndex = Math.min(i + 70, doc.length());
            lines.append(doc.substring(i, endIndex));
            lines.append('\n');
            i = endIndex;
        }

        return lines.toString();
    }

    public void resubmissionCheck()
    {
        int lastApplication = appliedTo.size() - 1;
        LocalDate closed = appliedTo.get(lastApplication).CloseDate;
        long dayDifference = ChronoUnit.DAYS.between(LocalDate.now(), closed);
        int dayDiff = ((int) dayDifference);
        if (dayDiff >= 30)
        {
            this.deleteDocuments();
            System.out.println("No CV or cover letter on file. Please resubmit next time you apply.");
        }
    }

    private void deleteDocuments()
    {
        this.coverLetter = "";
        this.cv = "";
    }

    private void updateDocuments()
    {
        System.out.println("Please submit your CV first.");
        this.setCV();
        System.out.println("Your application is almost complete. Please submit cover letter next.");
        this.setCoverLetter();
    }

    //TODO: send the CV when applying

    public void applyToPosting(JobPosting posting) {
        for (int i = 0; i <= JobApplicationSystem.Jobs.size(); i++) {
            if (JobApplicationSystem.Jobs.get(i).Title.equals(posting.Title)) {
                JobApplicationSystem.Jobs.get(i).applicantList.add(this);
                this.appliedTo.add(JobApplicationSystem.Jobs.get(i));
                this.allJobsAppliedTo.add(JobApplicationSystem.Jobs.get(i));
                if (cv.isEmpty())
                {
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
            System.out.println("Job Status: "+this.getJobStatus(this.allJobsAppliedTo.get(i))+"\n");

        }
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
