import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class JobPosting implements Serializable {
    public static LocalDate today = LocalDate.now();
    public int jobId;
    public String jobType;
    public String hiringCompany;
    public int numOfPeopleHiring;
    public LocalDate dateOfPosting;
    public LocalDate dateOfClosing;
    public ArrayList<String> requirementsList; // should say CV and CoverLetter
    public String status;               //Can be open closed or filled TODO:  when the status changes to closed then go through the list of all applicants in the applicant statuss lists and remove the applicant
    public ApplicantStatusMap applicantStatus;
    public boolean isHired;         //TODO: should change to true if only 1 applicant is left in the applicant pool

    public JobPosting() {
    }

    public JobPosting(int id, String jobType, String hiringCompany, LocalDate dateOfClosing, ArrayList<String> requirementsList) {
        this.jobId = id;
        this.jobType = jobType;
        this.hiringCompany = hiringCompany;
        this.numOfPeopleHiring = 1;
        this.dateOfPosting = today;
        this.dateOfClosing = dateOfClosing;
        this.requirementsList = requirementsList;
        this.status = getStatus();
        this.applicantStatus = new ApplicantStatusMap();
        this.isHired = false;
        FileWriter.writeToFile(this);

//
    }


    //  Constructor that allows you to add everything
    public JobPosting(int id, String jobType, String hiringCompany, int numOfPeopleHiring, LocalDate dateOfPosting, LocalDate dateOfClosing,
                      ArrayList<String> requirementsList, String status, ApplicantStatusMap applicantStatus, boolean isHired) {

        this.jobId = id;
        this.jobType = jobType;
        this.hiringCompany = hiringCompany;
        this.numOfPeopleHiring = numOfPeopleHiring;
        this.dateOfPosting = dateOfPosting;
        this.dateOfClosing = dateOfClosing;
        this.requirementsList = requirementsList;
        this.status = status;
        this.applicantStatus = applicantStatus;
        this.isHired = isHired;
        FileWriter.firstWriteToFile(this);
    }

    @Override
    public String toString() {

        return "Job Type: " + this.jobType +
                ", Hiring Company: " + this.hiringCompany +
                ", Number of People Hiring: " + this.numOfPeopleHiring +
                ", Date Of posting: " + this.dateOfPosting.toString() +
                ", Date of Closing: " + this.dateOfClosing.toString() +
                ", Requirement List: " + Helpers.arrayToString(this.requirementsList) +
                ", Status: " + this.status + "\n";
    }

    public void viewJobPosting() {
        // Applicant should be able to see all the variables
        this.status = getStatus();
        System.out.println(this);
    }

    public void apply(Applicant applicant) {
        // when applying to a job posting an applicant will be added to the submitted list
        if (this.applicantStatus.containsValue(applicant)) {
            System.out.println("You have already applied for this Job");
            return;
        }
        if ((this.requirementsList.contains("CV") && applicant.cv != null) | (this.requirementsList.contains("CoverLetter") && applicant.coverLetter != null)) {
            this.applicantStatus.submittedList.add(applicant);
            System.out.println("Applied");
            applicant.jobsAppliedTo.add(this);
            applicant.jobsEverAppliedTo.add(this);
        }

    }

    public boolean withdraw(Applicant applicant) {
        if (!this.isHired) {
            applicant.jobsEverAppliedTo.remove(this);
            applicant.jobsAppliedTo.remove(this);
            return this.applicantStatus.remove(applicant);
        } else {
            System.out.println("JobPosting.withdraw(): Cannot withdraw because someone has been hired");
            return false;
        }

    }


    public String getStatus() {
        // if todays date is after the date of application closing then the status should be - closed
        if (this.dateOfClosing.isBefore(today)) {
            // if the position has been filled then the status should be - filled
            // TODO: if there is only one applicant left in the applicant pool after the date of closing then
            // hire that applicant and change the status to hired
            if (this.applicantStatus.checkIfOnlyOneApplicantLeft()) {
                this.status = "filled";
            } else {
                this.status = "Closed";
            }
        } else {
            // otherwise the status should be - open
            this.status = "open";
        }


        return this.status;
    }


}
