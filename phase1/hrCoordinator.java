import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class hrCoordinator {
    public String name;
    public ArrayList<JobPosting> jobList;
    public ArrayList<Applicant> applicantList;
    public ArrayList<Interviewer> interviewerList;
    public Applicant choice;

    public hrCoordinator(String name) {
        this.name = name;
    }

    public ArrayList getApplyApplicant(JobPosting job) {
        this.applicantList.addAll(job.applicantList);
        return this.applicantList;

    }

    public void getApplicantApply(Applicant applicant){
        System.out.println("This applicant "+applicant.Name+" has applied to");
        for (JobPosting job : applicant.appliedTo){
            System.out.println(job.Title);
        }
    }

    public void matchApplicant(){
        JobPosting job = selectJob();
        Applicant applicant = selectApplicant(job);
        Interviewer interviewer = selectInterviwer();
        Scanner scan = new Scanner(System.in);
        System.out.println("Select interview type...(phone/in-person)");
        String choice = scan.next();
        switch (choice){
            case "phone":
                interviewer.phoneInterview.add(applicant);
                applicant.phoneInterview.add(interviewer);
            case "in-person":
                interviewer.inPersonInterview.add(applicant);
                applicant.inPersonInterview.add(interviewer);
            default:
                System.out.println("Wrong Choice, Please re-select");
                matchApplicant();
        }
    }

    public JobPosting createJobPosting(String title){
        JobPosting job = new JobPosting(title);
        this.jobList.add(job);
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
        this.choice = job.applicantList.get(choice-1);
        job.hiredList.add(this.choice);
        job.available = false;
        return this.choice;
    }

    public void recommendApplicant (){
        JobPosting job = selectJob();
        Applicant applicant = selectApplicant(job);
        Interviewer interviewer = selectInterviwer();
        interviewer.recommendApplicant.add(applicant);
        interviewer.recommendJob.add(job);
    }

    private Interviewer selectInterviwer (){
        Scanner scan = new Scanner(System.in);
        printInterviewer();
        System.out.println("Please enter the interviewer's name");
        String selectRecommend = scan.next();
        for (Interviewer interviewer : this.interviewerList){
            if (interviewer.name.equals(selectRecommend)){
                return interviewer;
            }
        }
        throw new NoSuchElementException();
    }

    private void printInterviewer(){
        for (Interviewer interviewer : this.interviewerList){
            System.out.println(interviewer.name);
        }
    }


    private Applicant selectApplicant (JobPosting job){
        Scanner scan = new Scanner(System.in);
        printApplicant(job);
        System.out.println("Please enter the recommend applicant's name");
        String selectApplicant = scan.next();
        for (Applicant applicant : job.applicantList){
            if (applicant.Name.equals(selectApplicant)){
                return applicant;
            }
        }
        throw new NoSuchElementException();
    }

    private void printApplicant (JobPosting job){
        System.out.println("Job "+job.Title+" has applicants");
        for (Applicant applicant : job.applicantList){
            System.out.println(applicant.Name);
        }
    }

    private JobPosting selectJob(){
        Scanner scan = new Scanner(System.in);
        printJob();
        String selectJob = scan.next();
        for (JobPosting job : this.jobList){
            if (job.Title.equals(selectJob)){
                return job;
            }
        }
        throw new NoSuchElementException();
    }

    private void printJob(){
        for (JobPosting job : this.jobList){
            System.out.println(job);
        }
    }
}

