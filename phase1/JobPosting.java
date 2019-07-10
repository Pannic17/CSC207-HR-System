import java.time.LocalDate;
import java.util.ArrayList;

public class JobPosting {
    public String Title;
    public String Description;
    public String Type;
    public LocalDate PostedDate;
    public String Requirements;
    public LocalDate CloseDate;
    public ArrayList<Applicant> applicantList;
    public ArrayList<Applicant> hiredList;

    public JobPosting(String title,String decription, String type, LocalDate postedDate, String requirements, LocalDate closeDate ){
        this.Title = title;
        this.Description = decription;
        this.Type = type;
        this.PostedDate = postedDate;
        this.Requirements = requirements;
        this.CloseDate = closeDate;
        this.applicantList = new ArrayList<>();
        this.hiredList = new ArrayList<>(hiredList);
    }

    public JobPosting getPosting(String title){
        for (int i = 0; i<= JobApplicationSystem.Jobs.size(); i++){
            if (JobApplicationSystem.Jobs.get(i).Title.equals(title)) {
                return JobApplicationSystem.Jobs.get(i);
            }
        }
    }

    public void setPosting(String title, String info, String newInfo){
        for (int i = 0; i<= JobApplicationSystem.Jobs.size(); i++){
            if (JobApplicationSystem.Jobs.get(i).Title.equals(title)) {
                if (info.equals("title")) {
                    JobApplicationSystem.Jobs.get(i).Title = newInfo;
                }
                if (info.equals("description")) {
                    JobApplicationSystem.Jobs.get(i).Description = newInfo;
                }
                if (info.equals("type")) {
                    JobApplicationSystem.Jobs.get(i).Type = newInfo;
                }
                if (info.equals("requirements")) {
                    JobApplicationSystem.Jobs.get(i).Requirements = newInfo;
                }
            }
        }
    }

    public void addPosting(String title,String decription, String type, LocalDate postedDate, String requirements, LocalDate closeDate ) {
        JobPosting newPosting = new JobPosting(title, decription, type, postedDate, requirements, closeDate);
        JobApplicationSystem.Jobs.add(newPosting);
    }

    public void removePosting(String title){
        for (int i = 0; i<= JobApplicationSystem.Jobs.size(); i++){
            if (JobApplicationSystem.Jobs.get(i).Title.equals(title)) {
                JobApplicationSystem.Jobs.remove(i);
            }
        }
    }

    public String checkStatus(){
        if (positionFilled() == true) {
            return "Available";
        }
        else {
            return "Filled";
        }
    }

    public boolean positionFilled(){
        if (hiredList == null) {
            return true;
        }
        else {
            return false;
        }
    }
}
