import java.io.Serializable;
import java.util.Scanner;

public class ApplicantPrompts implements Serializable {
    public ApplicantPrompts() {
    }

    public void createNewApplicantPrompt() {
//        learn password encryption for phase 2 and requirements for password
//        Phase 2 will have a password class
        ApplicantHelperMethods methods = new ApplicantHelperMethods();
        System.out.println("To Create a new Applicant Account Please enter a username:");
        Scanner in = new Scanner(System.in);
        String username = in.nextLine();
        if (!methods.usernameExists(username)) {
            System.out.println("Please enter a Password for your Account:");
            Scanner passwordInput = new Scanner(System.in);
            String password = passwordInput.nextLine();

            System.out.println("Please enter your full name:");
            Scanner nameInput = new Scanner(System.in);
            String name = nameInput.nextLine();

            Applicant applicant = new Applicant(username, password, name);
            //tell the user that he account has been created
            uploadCoverLetterPrompt(applicant);
            uploadCVPrompt(applicant);

            System.out.println(applicant);


            FileWriter.writeToFile(applicant);
            applicantOptionsPrompt(applicant);
        } else {
            System.out.println("This username already exists please choose a different username:");
            createNewApplicantPrompt();
        }
    }

    public void uploadCoverLetterPrompt(Applicant applicant) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Submit your Cover Letter by copying the text and pasting here.");
        String doc = scan.nextLine();
        StringBuilder lines = new StringBuilder();
        int i = 0;
        while (i < doc.length()) {
            int endIndex = Math.min(i + 70, doc.length());
            lines.append(doc.substring(i, endIndex));
            lines.append('\n');
            i = endIndex;
        }
        applicant.coverLetter = new CoverLetter();
        applicant.coverLetter.add(lines.toString());
    }


    public void uploadCVPrompt(Applicant applicant) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Submit your CV by copying the text and pasting here.");
        String doc = scan.nextLine();
        StringBuilder lines = new StringBuilder();
        int i = 0;
        while (i < doc.length()) {
            int endIndex = Math.min(i + 70, doc.length());
            lines.append(doc.substring(i, endIndex));
            lines.append('\n');
            i = endIndex;
        }
        applicant.cv = new CV();
        applicant.cv.add(lines.toString());

    }


    public void applicantSignInPrompt() {
        ApplicantHelperMethods methods = new ApplicantHelperMethods();
        System.out.println("Please enter the user name:");
        Scanner staffUsernameInput = new Scanner(System.in);
        String username = staffUsernameInput.nextLine();
        if (methods.usernameExists(username)) {
            System.out.println("Please enter a Password for your Account:");
            Scanner passwordInput = new Scanner(System.in);
            String password = passwordInput.nextLine();
            Applicant applicant = methods.getApplicantWithUsername(username);
            if (applicant.password.equals(password)) {
                applicantOptionsPrompt(applicant);
            } else {
                System.out.println("The password doesn't match");
                tryAgainPrompt();
            }
        } else {
            System.out.println("The username doesn't exist");
            tryAgainPrompt();

        }
    }


    public void applicantOptionsPrompt(Applicant applicant) {
//        TODO:View own documents like CV and Cover Letter
        ApplicantHelperMethods applicantMethods = new ApplicantHelperMethods();
        JobPostingHelperMethods jobPostingMethods = new JobPostingHelperMethods();
        JobApplicationSystem jbs = new JobApplicationSystem();
        System.out.println("Please select one of the options below:" +
                "\n1. View status" +
                "\n2. View Job Postings" +
                "\n3. Search Job Posting" +
                "\n4. Apply to a Job Posting" +
                "\n5. Withdraw from a Job Posting" +
                "\n6. Get History" +
                "\n7. SignOut");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                System.out.println(applicantMethods.checkStatuses(applicant));
                applicantOptionsPrompt(applicant);
                break;
            case 2:
                jobPostingMethods.viewAllPostings();
                break;
            case 3:
                System.out.println("Please enter the name of the Job Posting");
                Scanner in2 = new Scanner(System.in);
                String JobPostingName = in2.nextLine();
                JobPosting job = jobPostingMethods.getPosting(JobPostingName);
                if (job == null) {
                }// TODO : Try again
                applicantOptionsPrompt(applicant);
                break;
            case 4:
                System.out.println(jbs.getJobPostingList().values());
                System.out.println("Please enter the name of the Job Posting");
                Scanner getName = new Scanner(System.in);
                String name = getName.nextLine();
                JobPosting jobToApply = jobPostingMethods.getPosting(name);
                jobToApply.apply(applicant);
                applicantOptionsPrompt(applicant);
                break;
            case 5:
                System.out.println(applicantMethods.checkStatuses(applicant));
                System.out.println("Please enter the name of the Job Posting");
                Scanner getName2 = new Scanner(System.in);
                String name2 = getName2.nextLine();
                JobPosting jobToWithdraw = jobPostingMethods.getPosting(name2);
                Boolean withdrawn = jobToWithdraw.withdraw(applicant);
                System.out.println(withdrawn);// TODO: remove after changing type

                break;
            case 6:
                applicant.getHistory();

                break;
            case 7:
                LoginClass lg = new LoginClass();
                lg.startingPrompt2();
                break;
            default:
                System.out.println("Please choose one of the above options.");
                applicantOptionsPrompt(applicant);
        }
        applicantOptionsPrompt(applicant);
    }


    public void tryAgainPrompt() {
        System.out.println("Select 1 of the options below:" +
                "\n1. Try Again" +
                "\n2. Go Back" +
                "\n3. Exit");

        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch(choice){
            case 1: applicantSignInPrompt();break;
            case 2:  LoginClass lg = new LoginClass();lg.startingPrompt2();break;
            case 3:  System.exit(0);break;
            default: tryAgainPrompt();
        }
    }


}
