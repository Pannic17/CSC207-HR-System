import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Applicant implements Serializable {


    public String username;
    public String password;
    public String name;
    public CV cv;
    public CoverLetter coverLetter;
    public LocalDate dateCreated;
    public ArrayList<JobPosting> jobsAppliedTo;
    public ArrayList<JobPosting> jobsEverAppliedTo; // Doesn't need to consider the jobs from which you have withdrawn the application
    public LocalDate dateSinceLastApplicationClosed; // TODO: what should it initially be?

    public Applicant() {
    }

    public Applicant(String Username, String Password, String Name, LocalDate date) {

        this.username = Username;
        this.password = Password;
        this.name = Name;
        this.dateCreated = date;
        this.coverLetter = new CoverLetter();
        this.cv = new CV();
        this.jobsAppliedTo = new ArrayList<JobPosting>();
        this.jobsEverAppliedTo = new ArrayList<JobPosting>();

        this.dateSinceLastApplicationClosed = LocalDate.now();

    }

    public Applicant(String Username, String Password, String Name) {
        JobApplicationSystem jbs = new JobApplicationSystem();
        this.username = Username;
        this.password = Password;
        this.name = Name;
        this.dateCreated = jbs.today;
        this.coverLetter = new CoverLetter();
        this.cv = new CV();
        this.jobsAppliedTo = new ArrayList<JobPosting>();
        this.jobsEverAppliedTo = new ArrayList<JobPosting>();
        this.dateSinceLastApplicationClosed = getDateSinceLastApplicationClosed();
    }

    public Applicant(String Username, String Password, String Name, CV Cv, CoverLetter CoverLetter,
                     LocalDate DateCreated, ArrayList<JobPosting> JobsAppliedTo, ArrayList<JobPosting> JobsEverAppliedTo) {

        this.username = Username;
        this.password = Password;
        this.name = Name;
        this.cv = Cv;
        this.dateCreated = DateCreated;
        this.coverLetter = CoverLetter;
        this.jobsAppliedTo = JobsAppliedTo;
        this.jobsEverAppliedTo = JobsEverAppliedTo;
    }

    @Override
    public String toString() {
        return "  username :" + this.username +",   Name:" + this.name +",   Number of Jobs currently applied to :" + this.jobsAppliedTo.size()+"\n";
    }

    public LocalDate getDateSinceLastApplicationClosed() {
        // if there are no applications then return a sout saying that you have never applied for a job application
        try {
            LocalDate date = LocalDate.of(1998, 06, 17);
            if (this.jobsEverAppliedTo.size() < 1) {
                System.out.println(this.name + " has never applied for a job");
                return date;
            }


            for (int i = 0; i < this.jobsEverAppliedTo.size(); i++) {
                if (this.jobsEverAppliedTo.get(i).dateOfClosing.isAfter(date)) {
                    date = this.jobsEverAppliedTo.get(i).dateOfClosing;
                }
            }
            return date;
        } catch(NullPointerException ex){
            return LocalDate.of(1998, 06, 17);
        }

    }

    public void getStatus() {
//  should print the job and it status
    }


    public void getHistory() {
        JobPostingHelperMethods methods = new JobPostingHelperMethods();
        // return date account created
        System.out.println("Date Account Created: " + this.dateCreated);
        // jobs applied to in the past
        System.out.println("Jobs Currently Applied to:" + methods.jobpostingArraytoString(this.jobsAppliedTo));
        System.out.println("Jobs ever Applied to: " + methods.jobpostingArraytoString(this.jobsEverAppliedTo));
        if (this.jobsEverAppliedTo.size() > 0) {
            System.out.println("Date Since Last Application Closed: " + this.dateSinceLastApplicationClosed);
        } else {
            System.out.println("Date Since Last Application Closed: You have never applied for a Job");
        }


    }
    String getHistoryGUI(){
        JobPostingHelperMethods methods = new JobPostingHelperMethods();
        String history = ("Date Account Created" + this.dateCreated + "\n" +
                "Jobs Currently Applied to:" + methods.jobpostingArraytoString(this.jobsAppliedTo) + "\n" +
                "Jobs ever Applied to: " + methods.jobpostingArraytoString(this.jobsEverAppliedTo) + "\n");
        if (this.jobsEverAppliedTo.size() > 0){
            history += ("Date Since Last Application Closed: " + this.dateSinceLastApplicationClosed);
        }else {
            history += ("Date Since Last Application Closed: You have never applied for a Job");
        }
        return history;
    }


    public boolean Passed30days() {
        JobApplicationSystem jbs = new JobApplicationSystem();
        if (jbs.today.minusDays(30).isAfter(this.dateSinceLastApplicationClosed)) {
            return true;
        }
//        TODO: Should notify the applicant that he/she needs to upload their docs again
        return false;
    }


    public void deleteDocsAfter30Days() {
        // delete docs after 30days of last application closed
        if (Passed30days()) {
            this.coverLetter.remove();
            this.cv.remove();
        }
    }


}
