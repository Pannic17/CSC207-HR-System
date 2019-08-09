import com.sun.javaws.security.AppPolicy;

import java.io.Serializable;

public class JobPostingHelperMethods extends Helpers implements Serializable {

    public JobPostingHelperMethods() {
    }

    public void viewAllPostings() {
        // should print out all the job postings
        JobApplicationSystem jbs = new JobApplicationSystem();
        for (JobPosting job : jbs.getJobPostingList().values()) {
            System.out.println(job);
        }
    }



    public JobPosting getPosting(String name) {
        JobApplicationSystem jbs = new JobApplicationSystem();
        for (JobPosting job : jbs.getJobPostingList().values()) {
            if(job.jobType.equals(name)){return job;}
        }
        System.out.println("Job Posting with this name cannot be found");
        return null;
    }


}

