import java.util.ArrayList;

public class Applicant {
    public String Name;
    public ArrayList<JobPosting> appliedTo;
    public  ArrayList<JobPosting> allJobsAppliedTo;

    public Applicant(String name){
        this.Name= name;
        this.appliedTo = new ArrayList<>();
        this.allJobsAppliedTo = new ArrayList<>();
    }

    //TODO: send the CV when applying
    public void applyToPosting(JobPosting posting){
        for (int i = 0; i<= JobApplicantionSystem.Jobs.size(); i++){
            if (JobApplicantionSystem.Jobs.get(i).Title.equals(posting.Title)){
                JobApplicantionSystem.Jobs.get(i).applicantList.add(this);
                this.appliedTo.add(JobApplicantionSystem.Jobs.get(i));
                this.allJobsAppliedTo.add(JobApplicantionSystem.Jobs.get(i));
                System.out.println("Applied");
            }else{
                System.out.println("Doesn't contain this job");
            }
        }
    }
    // check which list this applicant is in, for a particualar posting

    public void viewStatus(JobPosting posting){
        for (int i = 0; i<= JobApplicantionSystem.Jobs.size(); i++){
            if (JobApplicantionSystem.Jobs.get(i).Title.equals(posting.Title)){
                if (JobApplicantionSystem.Jobs.get(i).hiredList.contains(this)){
                    System.out.println("Hired");}
                else if(JobApplicantionSystem.Jobs.get(i).applicantList.contains(this)){
                    System.out.println("Applied");
                }

            }
    }}

    public void withdraw(JobPosting posting){
        for (int i = 0; i<= JobApplicantionSystem.Jobs.size(); i++){
            if (JobApplicantionSystem.Jobs.get(i).Title.equals(posting.Title)){
                JobApplicantionSystem.Jobs.get(i).applicantList.remove(this);
                this.appliedTo.remove(JobApplicantionSystem.Jobs.get(i));
                System.out.println("Withdrawn");
            }else{
                System.out.println("Doesn't contain this job");
            }
        }
    }

    public void createApplicants(){}

    public void checkStatuses(){}

    public ArrayList<JobPosting> getHistory(){return allJobsAppliedTo;}

    public void positionFilled(){}

    public void checkInterview(){}

}
