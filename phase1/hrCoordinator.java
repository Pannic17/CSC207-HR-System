import java.util.ArrayList;
import java.util.Scanner;

public class hrCoordinator {
    public String Name;
    public ArrayList<JobPosting> JobList;
    public ArrayList<Applicant> ApplyApplicant;
    public ArrayList HiredApplicant;
    public Applicant Choosen;

    public hrCoordinator(String name) {
        this.Name = name;
    }

    public ArrayList getApplyApplicant(JobPosting job) {
        this.ApplyApplicant.addAll(job.applicantList);
        return this.ApplyApplicant;

    }

    public void matchApplicant(){

    }

    public JobPosting createJobPosting(String title){
        JobPosting job = new JobPosting(title);
        this.JobList.add(job);
        return job;
    }

    public void removeJobPosting(JobPosting job, ArrayList jobList){
        jobList.remove(job);
    }

    public hrCoordinator createHRCoodinator (String name){
        return new hrCoordinator(name);
    }

    public boolean checkStatus(JobPosting job){
        return job.available;
    }

    public ArrayList getHiredApplicant(JobPosting job){
        return job.hiredList;
    }

    public Applicant chooseApplicant(JobPosting job){
        int index = 0;
        for (Applicant applicant: job.applicantList){
            index = index + 1;
            System.out.println(index + "... " + applicant.Name);
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter integer...");
        int choice = scan.nextInt();
        this.Choosen = job.applicantList.get(choice-1);
        job.hiredList.add(this.Choosen);
        job.available = false;
        return this.Choosen;
    }
}

