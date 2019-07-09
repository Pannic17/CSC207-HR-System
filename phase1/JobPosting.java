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

    public void getPosting(){}

    public void setPosting(){}

    public void addPosting(){}

    public void removePosting(){}

    public void checkStatus(){}

    public void positionFilled(){}
}
