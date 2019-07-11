import java.util.ArrayList;
import java.util.Scanner;

public class ApplicantPrompts {
    public ApplicantPrompts() {
    }

    public void createNewApplicantPrompt() {
        System.out.println("To Create a new Applicant Account Plese enter a user name:");
//        TODO: save the username and pasword to an external file
//        learn password encryption for phase 2 and requirements for password
//        Phase 2 will have a password class
        System.out.println("To Create a new Applicant Account Plese enter a username:");
        Scanner in = new Scanner(System.in);
        String username = in.nextLine();
        if (!Applicant.applicantExist(username)) {
            System.out.println("Please enter a Password for your Account:");
            Scanner passwordInput = new Scanner(System.in);
            String password = passwordInput.nextLine();
            Applicant applicant = new Applicant(username, password);
            System.out.println("A new Applicant account has been created");
            System.out.println(applicant);
            JobApplicationSystem.Applicants.add(applicant);
            //
            applicantOptionsPrompt(applicant);
        } else {
            System.out.println("This username already exists please choose a different username:");
            createNewApplicantPrompt();
        }
    }

    public void applicantOptionsPrompt(Applicant applicant) {
        System.out.println("Please select one of the options below:" +
                "\n1. View status" +
                "\n2. View Job Posting" +
                "\n3. Search Job Posting" +
                "\n4. Apply to a Job Posting" +
                "\n5. Withdraw from a Job Posting" +
                "\n6. Get Notifications" +
                "\n7. Get History" +
                "\n8. Back");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        if (choice == 1) {
            applicant.checkStatuses();
        } else if (choice == 2) {
            JobPosting.viewAllPostings();
        } else if (choice == 3) {
            System.out.println("Plese enter the name of the Job Posting");
            Scanner in2 = new Scanner(System.in);
            String JobPostingName = in2.nextLine();
            JobPosting jobPosting = JobPosting.getPosting(JobPostingName);
            System.out.println(jobPosting);
//            TODO: create an option to apply from here
        } else if (choice == 4) {
            applyToPostingPrompt(applicant);
        } else if (choice == 5) {
            withdrawFromJobPostingPrompt(applicant);
        } else if (choice == 6) {
            applicant.getNotifiations();
        } else if (choice == 7) {
            ArrayList allJobs = applicant.getHistory();
            for (int i = 0; i < allJobs.size(); i++) {
                System.out.println(allJobs.get(i));
            }
        } else if (choice == 8) {
            LoginClass lc = new LoginClass();
            lc.startingPrompt();
        } else {
            System.out.println("Please choose one of the above options.");
            applicantOptionsPrompt(applicant);
        }

    }

    public void applyToPostingPrompt(Applicant applicant) {
        String jobName = getPostingNamePrompt();
        JobPosting job = Applicant.findPosting(jobName);
        if (job == null){cannotFindJobPostingPrompt(applicant);}
        else{applicant.applyToPosting(job);}
    }

    public void withdrawFromJobPostingPrompt(Applicant applicant){
        String jobName = getPostingNamePrompt();
        JobPosting job = Applicant.findPosting(jobName);
        if (job == null){cannotFindJobPostingPrompt(applicant);}
        else{applicant.withdraw(job);}
    }

    public String getPostingNamePrompt() {
        System.out.println("Please enter the name of the posting :");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void cannotFindJobPostingPrompt(Applicant applicant){
        System.out.println("Cannot Find a jobPosting with that name.");
        applicantOptionsPrompt(applicant);
    }


}
