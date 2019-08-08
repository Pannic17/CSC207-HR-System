import java.io.Serializable;
import java.util.Scanner;

public class InterviewPrompts implements Serializable {
    public InterviewPrompts() {
    }



    public void createNewInterViewerPrompt() {
        //        learn password encryption for phase 2 and requirements for password
//        Phase 2 will have a password class
        InterviewerHelperMethods methods = new InterviewerHelperMethods();
        System.out.println("To Create a new Interviewer Account Please enter a username:");
        Scanner in = new Scanner(System.in);
        String username = in.nextLine();
        if (!methods.usernameExists(username)) {
            System.out.println("Please enter a Password for your Account:");
            Scanner passwordInput = new Scanner(System.in);
            String password = passwordInput.nextLine();
            System.out.println("Please enter your full name:");
            Scanner nameInput = new Scanner(System.in);
            String name = nameInput.nextLine();

            Interviewer interviewer = new Interviewer(username, password, name);
            //tell the user that he account has been created
            System.out.println("A new Interviewer account has been created");

            System.out.println(interviewer);

            FileWriter.writeToFile(interviewer);
            interviewerOptionsPrompt(interviewer);
        } else {
            System.out.println("This username already exists please choose a different username:");
            createNewInterViewerPrompt();
        }

    }

    public void interviwerSignIn(){
        ApplicantHelperMethods methods = new ApplicantHelperMethods();
        InterviewerHelperMethods interviewerHelperMethods = new InterviewerHelperMethods();
        System.out.println("Please enter the user name:");
        Scanner staffUsernameInput = new Scanner(System.in);
        String username = staffUsernameInput.nextLine();
        if (interviewerHelperMethods.usernameExists(username)) {
            System.out.println("Please enter a Password for your Account:");
            Scanner passwordInput = new Scanner(System.in);
            String password = passwordInput.nextLine();
            Interviewer interviewer = interviewerHelperMethods.getInterviewerWithUsername(username);
            if (interviewer.password.equals(password)) {
                interviewerOptionsPrompt(interviewer);
            } else {
                System.out.println("The password doesn't match");
                tryAgainPrompt();
            }
        } else {
            System.out.println("The username doesn't exist");
            tryAgainPrompt();

        }
    }

    public void interviewerOptionsPrompt(Interviewer interviewer){
        InterviewerHelperMethods interviewerHelperMethods = new InterviewerHelperMethods();
        JobPostingHelperMethods jobPostingMethods = new JobPostingHelperMethods();
        ApplicantHelperMethods applicantMethods = new ApplicantHelperMethods();
        System.out.println("Please select one of the options below:" +
                "\n1. View Applicants Interviewing" +
                "\n2. Recommend an Applicant" +
                "\n3. Reject an Applicant" +
                "\n4. SignOut");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                interviewer.printListOfApplicantsInterviewing();
                interviewerOptionsPrompt(interviewer);
                break;
            case 2:interviewer.printListOfApplicantsInterviewing();
                System.out.println("Please enter the name of the Applicant To Recommend");
                Scanner in2 = new Scanner(System.in);
                String name = in2.nextLine();
                Applicant applicant = applicantMethods.getApplicant(name);
                interviewer.recommendedList.add(applicant);
                interviewerOptionsPrompt(interviewer);
                break;
            case 3:
                interviewer.printListOfApplicantsInterviewing();
                System.out.println("Please enter the name of the Applicant To Reject");
                Scanner in3 = new Scanner(System.in);
                String name2 = in3.nextLine();
                Applicant applicant2 = applicantMethods.getApplicant(name2);
                interviewer.notRecommendedList.add(applicant2);
                interviewerOptionsPrompt(interviewer);
                break;
            case 4:
                LoginClass lg = new LoginClass();
                lg.startingPrompt2();
                break;
            default:
                System.out.println("Please choose one of the above options.");
                interviewerOptionsPrompt(interviewer);
        }
    }

    public void tryAgainPrompt() {
        System.out.println("Select 1 of the options below:" +
                "\n1. Try Again" +
                "\n2. Go Back" +
                "\n3. Exit");

        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        if (choice == 1) {// ask to try again
            interviwerSignIn();
        } else if (choice == 2) {
            LoginClass lg = new LoginClass();
            lg.startingPrompt2();
        } else if (choice == 3) {
            System.out.close();
        } else {
            interviwerSignIn();
        }
    }

}
