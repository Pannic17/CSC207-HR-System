import java.io.*;
import java.util.HashMap;

public class FileReader implements Serializable {
    public FileReader() {
    }


    public static HashMap<Integer, JobPosting> readJobPostingFile() {
        HashMap<Integer, JobPosting> map = null;
        JobApplicationSystem jbs = new JobApplicationSystem();
        try {
            FileInputStream fis = new FileInputStream(jbs.jobPostingFileName + ".ser");
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
        HashMap<String, Applicant> map = null;
        JobApplicationSystem jbs = new JobApplicationSystem();
        try {
            FileInputStream fis = new FileInputStream(jbs.applicantFileName + ".ser");
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
        // should return a hashmap of all interviewers in the file
        HashMap<String, Interviewer> map = null;
        JobApplicationSystem jbs = new JobApplicationSystem();
        try {
            FileInputStream fis = new FileInputStream(jbs.interviewerFileName + ".ser");
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
        // should return a hashmap of all interviewers in the file
        HashMap<String, HRCoordinator> map = null;
        JobApplicationSystem jbs = new JobApplicationSystem();
        try {
            FileInputStream fis = new FileInputStream(jbs.HrFileName + ".ser");
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

