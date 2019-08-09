import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class HRCoordinator implements Serializable {
    public String username;
    public String password;
    public String name;
    public String company;

    public HRCoordinator(String username, String password, String name, String company) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.company = company;
    }

    @Override
    public String toString() {
        return "   Username: " + this.username +
                "    ,Name: " + this.name +
                "    ,Company :" + this.company+" \n";
    }


    public HashMap<String, Interviewer> getInterviewerList() {
//        should return a list of all interviewers
        JobApplicationSystem jbs = new JobApplicationSystem();
        return jbs.getInterviewerList();
    }

//    TODO: move applicant from 1 pool to next
//    TODO: create a new job posting


    public ArrayList<JobPosting> applicantJobsAppliedInCompany(Applicant applicant) {
        ArrayList<JobPosting> toReturn = new ArrayList<JobPosting>();
        for (int i = 0; i < applicant.jobsAppliedTo.size(); i++) {
            if (applicant.jobsAppliedTo.get(i).hiringCompany.equals(this.company)) {
                toReturn.add(applicant.jobsAppliedTo.get(i));
            }
        }
        return toReturn;
    }






}
