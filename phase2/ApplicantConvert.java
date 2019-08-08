import java.time.LocalDate;

public class ApplicantConvert extends Applicant {
    ApplicantConvert(String Username, String Password, String Name, LocalDate date){
        super(Username, Password, Name, date);
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
}
