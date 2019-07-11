import java.util.Scanner;

public class LoginClass {
    public LoginClass() {
        startingPrompt();
    }

    public void startingPrompt() {
        System.out.println("Please select one of the option Below" + "\n1. Login" + "\n2. Create new User" + "\n3. Exit");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        if (choice == 1) {
            System.out.println("Please select one of the options below:" + "\n1. Applicant" + "\n2. Interviewer" + "\n3. HR Coordinator" + "\n4. Back");
            Scanner loginTypeInput = new Scanner(System.in);
            int type = loginTypeInput.nextInt();
            if (type == 1) {
                applicantLogin();
                startingPrompt();
            } else if (type == 2) {
                interviewerLogin();
                startingPrompt();
            } else if (type == 3) {
                hrLogin();
                startingPrompt();
            } else if (type == 4) {
                startingPrompt();
            } else {
                System.out.println("Please choose one of the above options.");
                createNewUser();
            }
        } else if (choice == 2) {
            createNewUser();
            startingPrompt();
        } else if (choice == 3) {
            System.exit(0);
        } else {
            System.out.println("Please select one of the Options above");
            startingPrompt();
        }
    }

    public void createNewUser() {
        System.out.println("To Create a new user, Please select one of the options below:" + "\n1. Applicant" + "\n2. Interviewer" + "\n3. HR-Coordinator" + "\n4. Back");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        if (choice == 1) {
            ApplicantPrompts ap = new ApplicantPrompts();
            ap.createNewApplicantPrompt();
        } else if (choice == 2) {
            createNewInterviewer();// TODO:
        } else if (choice == 3) {
            createNewHR();          // TODO:
        } else if (choice == 4) {
            startingPrompt();
        } else {
            System.out.println("Please choose one of the above options.");
            createNewUser();
        }
    }


    public void forgotPassword() {
    }

    public void newAccount() {
    }

    public void verifyPassword() {
    }

    public void applicantLogin() {
        ApplicantPrompts ap = new ApplicantPrompts();

    }

    public void hrLogin() {
    }

    public void interviewerLogin() {
    }

    public void createNewApplicant() {
    }

    public void createNewHR() {
    }

    public void createNewInterviewer() {
    }


}
