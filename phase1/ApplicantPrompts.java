import java.util.Scanner;

public class ApplicantPrompts {
    public ApplicantPrompts(){}

    public void createNewApplicantPrompt(){
        System.out.println("To Create a new Applicant Account Plese enter a user name:");
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
            ApplicantPrompts.applicantOptionsPrompt(applicant);
        } else {
            System.out.println("This username already exists please choose a different username:");
            createNewApplicantPrompt();
        }
    }

    public static void applicantOptionsPrompt(Applicant applicant) {
        System.out.println("Please select one of the options below:" +
                "\n1. View status" +
                "\n2. View Job Posting" + // TODO: Should take care of the apply and withdraw
                "\n3. Search Job Posting" +
                "\n4. Withdraw from a Job Posting" +
                "\n5. Get Notifications" +
                "\n6. Get History" +
                "\n7. Back");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        if (choice == 1) {
            applicant.checkStatuses();
        } else if (choice == 2) {

        } else if (choice == 3) {
            member.getExtention();
        } else if (choice == 5) {
            startingPrompt();
        } else if (choice == 4) {
            member.issueFromLibraryWaitlist();
        } else {
            System.out.println("Please choose one of the above options.");
            applicantOptionsPrompt(applicant);
        }

    }


}
