import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginClass implements Serializable {
    public LoginClass() {
    }

//    public void firstPrompt(){
//        System.out.println("Please select one of the option Below" + "\n1. Using the Program for the first Time" + "\n2. Have Used it Before" + "\n3. Exit");
//        Scanner in = new Scanner(System.in);
//        int choice = in.nextInt();
//        switch (choice){
//            case 1: FirstTime();createNewUser();
//            case 2: startingPrompt2();
//            case 3: System.exit(0);
//            default: System.out.println("Please select one of the Options above"); firstPrompt();
//        }
//
//    }

    public void FirstTime(){
//        Write to all the files

        LocalDate date = LocalDate.now();
        ArrayList<String > arrayList = new ArrayList<>();
        ArrayList<String > reqList = new ArrayList<>();;
        reqList.add("CV");
        reqList.add("CoverLetter");
        ApplicantStatusMap applicantStatusMap = new ApplicantStatusMap();
        Applicant applicant2 = new Applicant("admin","check","name",date);
        JobPosting jp = new JobPosting(001,"Receptionist","Apple",1,date,date,reqList,"Open",applicantStatusMap,false);
        applicantStatusMap.interview1List.add(applicant2);
        CV cv = new CV();
        cv.add("");
        CoverLetter cl = new CoverLetter();
        cl.add("");
        ArrayList<JobPosting> appliedTo = new ArrayList<>();
        appliedTo.add(jp);
        Applicant applicant = new Applicant("admin","check","name",cv, cl, date,appliedTo,appliedTo);
        Interviewer iv = new Interviewer("admin","check","name");
        HRCoordinator hrCoordinator = new HRCoordinator("","","","");

//        Use the first time method
        FileWriter.firstWriteToFile(applicant);
        FileWriter.firstWriteToFile(jp);
        FileWriter.firstWriteToFile(iv);
        FileWriter.firstWriteToFile(hrCoordinator);
    }


    public void startingPrompt() {
        FirstTime();
        System.out.println("Please select one of the option Below" + "\n1. Login" + "\n2. Create new User" + "\n3. Exit");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        if (choice == 1) {

            System.out.println("Please select one of the options below:" + "\n1. Applicant" + "\n2. Interviewer" + "\n3. HR Coordinator" + "\n4. Back");
            Scanner loginTypeInput = new Scanner(System.in);
            int type = loginTypeInput.nextInt();
            if (type == 1) {
                applicantLogin();
            } else if (type == 2) {
                interviewerLogin();
                startingPrompt2();
            } else if (type == 3) {
                hrLogin();
                startingPrompt2();
            } else if (type == 4) {
                startingPrompt2();
            } else {
                System.out.println("Please choose one of the above options.");
                createNewUser();
            }
        } else if (choice == 2) {
            createNewUser();
            startingPrompt2();
        } else if (choice == 3) {
            System.exit(0);
        } else {
            System.out.println("Please select one of the Options above");
            startingPrompt2();
        }
    }

    public void startingPrompt2() {
        System.out.println("Please select one of the option Below" + "\n1. Login" + "\n2. Create new User" + "\n3. Exit");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        if (choice == 1) {

            System.out.println("Please select one of the options below:" + "\n1. Applicant" + "\n2. Interviewer" + "\n3. HR Coordinator" + "\n4. Back");
            Scanner loginTypeInput = new Scanner(System.in);
            int type = loginTypeInput.nextInt();
            if (type == 1) {
                applicantLogin();
            } else if (type == 2) {
                interviewerLogin();
                startingPrompt2();
            } else if (type == 3) {
                hrLogin();
                startingPrompt2();
            } else if (type == 4) {
                startingPrompt2();
            } else {
                System.out.println("Please choose one of the above options.");
                createNewUser();
            }
        } else if (choice == 2) {
            createNewUser();
            startingPrompt2();
        } else if (choice == 3) {
            System.exit(0);
        } else {
            System.out.println("Please select one of the Options above");
            startingPrompt2();
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
            startingPrompt2();
        } else {
            System.out.println("Please choose one of the above options.");
            createNewUser();
        }
    }


    public void applicantLogin() {
        ApplicantPrompts ap = new ApplicantPrompts();
        ap.applicantSignInPrompt();

    }

    public void hrLogin() {
        HRPrompts hrPrompts = new HRPrompts();
        hrPrompts.hrSignInPrompt();
    }

    public void interviewerLogin() {
        InterviewPrompts ip = new InterviewPrompts();
        ip.interviwerSignIn();
    }

    public void createNewApplicant() {
        ApplicantPrompts ap = new ApplicantPrompts();
        ap.createNewApplicantPrompt();
    }

    public void createNewHR() {
        HRPrompts hrPrompts = new HRPrompts();
        hrPrompts.createNewHRPrompt();
    }

    public void createNewInterviewer() {
        InterviewPrompts ip = new InterviewPrompts();
        ip.createNewInterViewerPrompt();
    }




}
