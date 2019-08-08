import java.io.*;
import java.util.HashMap;

public class FileReader implements Serializable {
    public FileReader() {
    }

// TODO: Convert to generic

    public static HashMap<Integer, JobPosting> readJobPostingFile() {
        System.out.println("Job Posting File");
        HashMap<Integer, JobPosting> map = null;

        try {
            FileInputStream fis = new FileInputStream(JobApplicationSystem.jobPostingFileName + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }

        if (map == null) {
            System.out.println("Returning a null value");
        } // TODO: Remove later
        return map;
    }

    public static HashMap<String, Applicant> readApplicantFile() {
        System.out.println("Reading Applicant File");
        HashMap<String, Applicant> map = null;
        try {
            FileInputStream fis = new FileInputStream(JobApplicationSystem.applicantFileName + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }

        if (map == null) {
            System.out.println("Returning a null value");
        } // TODO: Remove later
        return map;
    }


    public static HashMap<String, Interviewer> readInterviewerFile() {
        System.out.println("Interviewer File");
        // should return a hashmap of all interviewers in the file
        HashMap<String, Interviewer> map = null;
        try {
            FileInputStream fis = new FileInputStream(JobApplicationSystem.interviewerFileName + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
        if (map == null) {
            System.out.println("Returning a null value");
        } // TODO: Remove later
        return map;
    }


    public static HashMap<String, HRCoordinator> readHRcoordinatorFile() {
        System.out.println("HR File");
        // should return a hashmap of all interviewers in the file
        HashMap<String, HRCoordinator> map = null;

        try {
            FileInputStream fis = new FileInputStream(JobApplicationSystem.HrFileName + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }

        if (map == null) {
            System.out.println("Returning a null value");
        } // TODO: Remove later
        return map;
    }



}

