import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class HRPrompts implements Serializable {
    public HRPrompts() {
    }

    public void createNewHRPrompt() {
//        learn password encryption for phase 2 and requirements for password
//        Phase 2 will have a password class
        HRHelperMethods methods = new HRHelperMethods();
        System.out.println("To Create a new HRCoordinator Account Please enter a username:");
        Scanner in = new Scanner(System.in);
        String username = in.nextLine();
        if (!methods.usernameExists(username)) {
            System.out.println("Please enter a Password for your Account:");
            Scanner passwordInput = new Scanner(System.in);
            String password = passwordInput.nextLine();

            System.out.println("Please enter your full name:");
            Scanner nameInput = new Scanner(System.in);
            String name = nameInput.nextLine();

            System.out.println("Please enter the name Of the company you are working for :");
            Scanner companyInput = new Scanner(System.in);
            String CompanyName = companyInput.nextLine();

            HRCoordinator hrCoordinator = new HRCoordinator(username, password, name, CompanyName);
            //tell the user that he account has been created
            System.out.println("A new HR account has been created");
            System.out.println(hrCoordinator);

            FileWriter.writeToFile(hrCoordinator);
            hrOptionsPrompts(hrCoordinator);
        } else {
            System.out.println("This username already exists please choose a different username:");
            createNewHRPrompt();
        }
    }


    public void hrOptionsPrompts(HRCoordinator hrCoordinator) {

        System.out.println("\n"+hrCoordinator);
        ApplicantHelperMethods applicantMethods = new ApplicantHelperMethods();
        JobPostingHelperMethods jobPostingMethods = new JobPostingHelperMethods();
        InterviewerHelperMethods interviewerMethods = new InterviewerHelperMethods();
        JobApplicationSystem jbs = new JobApplicationSystem();
        System.out.println("Please select one of the options below:" +
                "\n1. View Applicant's Documents" +
                "\n2. View jobs applied by the applicant" +
                "\n3. Get a list of Interviewers" +
                "\n4. Get List of Applicants" +
                "\n5. Assign Job to interviewer" +
                "\n6. Assign Applicant to Interviewer" +
                "\n7. Create a new Job Posting" +
                "\n8. Get Results from interviewer" +
                "\n9. View All Job Postings" +
                "\n10. SignOut");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                Option1();
                break;
            case 2:
                Option2(hrCoordinator);
                break;
            case 3:
                System.out.println(jbs.getInterviewerList().values());
                break;
            case 4:
                System.out.println(jbs.getApplicantList().values());
                break;
            case 6:
                Option5();
                break;
            case 5:
                Option6();
                break;
            case 7:
                createJobPostingPrompts(hrCoordinator);
                break;
            case 8:
                System.out.println(jbs.getInterviewerList().values());
                getResultsFromInterviewerPrompt(hrCoordinator);
                break;
            case 9:
                JobPostingHelperMethods jbhm = new JobPostingHelperMethods();
                jbhm.viewAllPostings();
                break;
            case 10:
                LoginClass lg = new LoginClass();
                lg.startingPrompt2();
                break;
            default:
                System.out.println("Please choose one of the above options.");
                hrOptionsPrompts(hrCoordinator);
        }
        hrOptionsPrompts(hrCoordinator);
    }

    public void Option1() {
        JobApplicationSystem jbs = new JobApplicationSystem();
        ApplicantHelperMethods applicantMethods = new ApplicantHelperMethods();
        System.out.println(jbs.getApplicantList().values());
        System.out.println("Please enter the name of the Applicant To view  his/her Documents");
        Scanner in2 = new Scanner(System.in);
        String name = in2.nextLine();
        Applicant applicant = applicantMethods.getApplicant(name);
        if (applicant != null) {
            applicantMethods.printApplicantDocs(applicant);
        } else {
            System.out.println("Applicant with this name Doesn't exist");
            return;
        }
    }

    public void Option2(HRCoordinator hrCoordinator) {
        JobApplicationSystem jbs = new JobApplicationSystem();
        ApplicantHelperMethods applicantMethods = new ApplicantHelperMethods();
        System.out.println(jbs.getApplicantList().values());
        System.out.println("Please enter the name of the Applicant To view jobs applied by him/her");
        Scanner in3 = new Scanner(System.in);
        String name2 = in3.nextLine();
        Applicant applicant2 = applicantMethods.getApplicant(name2);
        if (applicant2 != null) {
            ArrayList<String> arrayList = applicantMethods.jobpostingArraytoString(applicant2.jobsAppliedTo);
            if (arrayList.size() == 0) {
                System.out.println("The applicant has not applied for a job yet");
            } else {
                System.out.println(arrayList);
            }
        } else {
            System.out.println("Applicant with this name Doesn't exist");
            return;
        }
    }

    public void Option5() {
        JobApplicationSystem jbs = new JobApplicationSystem();
        ApplicantHelperMethods applicantMethods = new ApplicantHelperMethods();
        InterviewerHelperMethods interviewerMethods = new InterviewerHelperMethods();
        System.out.println(jbs.getApplicantList().values());
        System.out.println("Please enter the name of the Applicant To view  his/her Documents");
        Scanner in2 = new Scanner(System.in);
        String name = in2.nextLine();
        Applicant applicant = applicantMethods.getApplicant(name);
        if (applicant != null) {
            System.out.println(jbs.getInterviewerList().values());
            System.out.println("Please enter the name of the Interviewer");
            Scanner getName2 = new Scanner(System.in);
            String interviewerName = getName2.nextLine();
            Interviewer interviewer = interviewerMethods.getInterviewerWithName(interviewerName);
            if (interviewer != null) {

                interviewerMethods.assignApplicantToInteviewer(interviewer);
            } else {
                System.out.println("Interviewer with this name doesn't exist");
                return;
            }
        } else {
            System.out.println("Applicant with this name Doesn't exist");
            return;
        }

    }


    public void Option6() {
        // Assign interview pool to Interviewer
        JobApplicationSystem jbs = new JobApplicationSystem();
        ApplicantHelperMethods applicantMethods = new ApplicantHelperMethods();
        InterviewerHelperMethods interviewerMethods = new InterviewerHelperMethods();
        JobPostingHelperMethods jphm = new JobPostingHelperMethods();
        System.out.println(jbs.getInterviewerList().values());
        System.out.println("Please enter the name of the Interviewer");
        Scanner getName2 = new Scanner(System.in);
        String interviewerName = getName2.nextLine();
        Interviewer interviewer = interviewerMethods.getInterviewerWithName(interviewerName);
        if (interviewer != null) {
            System.out.println(jbs.getJobPostingList());
            System.out.println("Please enter the name of the job to Assign to " + interviewer.name);
            Scanner in = new Scanner(System.in);
            String jobName = in.nextLine();
            JobPosting job = jphm.getPosting(jobName);
            if (job != null) {
                interviewer.assignJob(job);
                System.out.println(interviewerName+" has been assigned the job "+ jobName);
            } else {
                System.out.println("Job Posting With this name doesn't exist");
            }

        } else {
            System.out.println("Interviewer with this name doesn't exist");
            return;
        }
    }

    public void hrSignInPrompt() {
        HRHelperMethods methods = new HRHelperMethods();
        InterviewerHelperMethods interviewerHelperMethods = new InterviewerHelperMethods();
        System.out.println("Please enter the user name for your HRCoordinator Accoount:");
        Scanner staffUsernameInput = new Scanner(System.in);
        String username = staffUsernameInput.nextLine();
        if (methods.usernameExists(username)) {
            System.out.println("Please enter a Password for your Account:");
            Scanner passwordInput = new Scanner(System.in);
            String password = passwordInput.nextLine();
            HRCoordinator hr = methods.getHRwithUsername(username);
            if (hr.password.equals(password)) {
                hrOptionsPrompts(hr);
            } else {
                System.out.println("The password doesn't match");
                tryAgainPrompt();
            }
        } else {
            System.out.println("The username doesn't exist");
            tryAgainPrompt();

        }
    }

    public ArrayList<String> getRequirementsPrompt() {
        System.out.println("Please select 1 of the options below for requirements" +
                "\n1. CV" +
                "\n2. CoverLetter" +
                "\n3. Both");
        ArrayList<String> requirements = new ArrayList<String>();
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                requirements.add("CV");
                return requirements;

            case 2:
                requirements.add("CoverLetter");
                return requirements;

            case 3:
                requirements.add("CV");
                requirements.add("CoverLetter");
                return requirements;

            default:
                System.out.println("Please Select one of the options above");
                getRequirementsPrompt();
        }
        return null;
    }

    public void createJobPostingPrompts(HRCoordinator hrCoordinator) {
        System.out.println("Please enter the Job ID (a unique integer combination):");
        Scanner getid = new Scanner(System.in);
        int id = getid.nextInt();
        System.out.println("Please enter the Title of the Job");
        Scanner getTitle = new Scanner(System.in);
        String title = getTitle.nextLine();
        System.out.println("Please enter the name of the HiringCompany");
        Scanner getHiringCompanyName = new Scanner(System.in);
        String companyName = getHiringCompanyName.nextLine();
        System.out.println("Please enter the Date of closing (Format: YYYY-MM-DD)");
        Scanner getClosingDate = new Scanner(System.in);
        String closingDate = getClosingDate.nextLine();
        LocalDate date = LocalDate.parse(closingDate); // TODO: take care of exceptions
        ArrayList requirements = getRequirementsPrompt();
        JobPosting job = new JobPosting(id, title, companyName, date, requirements);
        System.out.println("New Job Post Created");

    }


    public void getResultsFromInterviewerPrompt(HRCoordinator hrCoordinator) {
        InterviewerHelperMethods interviewerMethods = new InterviewerHelperMethods();
        System.out.println("Please enter the name of the Interviewer");
        Scanner getName3 = new Scanner(System.in);
        String interviewerName2 = getName3.nextLine();
        Interviewer interviewer2 = interviewerMethods.getInterviewerWithName(interviewerName2);
        if (interviewer2 == null) {
            tryAgainPrompt2(hrCoordinator);
        } else {
            System.out.println("Rejected List: " + interviewerMethods.applicantArraytoString(interviewer2.notRecommendedList));
            System.out.println("Recommended List: " + interviewerMethods.applicantArraytoString(interviewer2.recommendedList));
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
            hrSignInPrompt();
        } else if (choice == 2) {
            LoginClass lg = new LoginClass();
            lg.startingPrompt2();
        } else if (choice == 3) {
            System.out.close();
        } else {
            hrSignInPrompt();
        }
    }

    public void tryAgainPrompt2(HRCoordinator hr) {
        System.out.println("Select 1 of the options below:" +
                "\n1. Try Again" +
                "\n2. Go Back" +
                "\n3. Exit");

        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                getResultsFromInterviewerPrompt(hr);
                break;
            case 2:
                hrOptionsPrompts(hr);
                break;
            case 3:
                System.exit(0);
                break;
            default:
                tryAgainPrompt2(hr);
        }
    }
}
