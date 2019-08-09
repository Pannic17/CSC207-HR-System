
import java.io.*;
import java.util.HashMap;
import java.util.Hashtable;

public class FileWriter implements Serializable {


    public FileWriter() {
    }


    public static void writeToFile(Applicant applicant) {
//        HashMap<String, Applicant> hmap = new HashMap<String, Applicant>();

        JobApplicationSystem jbs = new JobApplicationSystem();

        HashMap<String, Applicant> hmap = jbs.getApplicantList();
        hmap.put(applicant.username, applicant);
        try {

            FileOutputStream fos = new FileOutputStream(jbs.applicantFileName + ".ser");
            OutputStream buffer = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(buffer);
            oos.writeObject(hmap);
            oos.close();
            buffer.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public static void writeToFile(Interviewer interviewer) {
        JobApplicationSystem jbs = new JobApplicationSystem();
        HashMap<String, Interviewer> hmap = jbs.getInterviewerList();
        hmap.put(interviewer.username, interviewer);

        try {

            FileOutputStream fos = new FileOutputStream(jbs.interviewerFileName + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(hmap);
            oos.close();
            fos.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void writeToFile(HRCoordinator hrCoordinator) {
        JobApplicationSystem jbs = new JobApplicationSystem();
        HashMap<String, HRCoordinator> hmap = jbs.getHRCoordinatorList();
        hmap.put(hrCoordinator.username, hrCoordinator);

        try {
            FileOutputStream fos = new FileOutputStream(jbs.HrFileName + ".ser");
            OutputStream buffer = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(buffer);
            oos.writeObject(hmap);
            oos.close();
            buffer.close();
            fos.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public static void writeToFile(JobPosting job) {
        JobApplicationSystem jbs = new JobApplicationSystem();
        HashMap<Integer, JobPosting> hmap = jbs.getJobPostingList();
        hmap.put(job.jobId, job);

        try {
            FileOutputStream fos = new FileOutputStream(jbs.jobPostingFileName + ".ser");
            OutputStream buffer = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(buffer);
            oos.writeObject(hmap);
            oos.close();
            buffer.close();
            fos.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void firstWriteToFile(Applicant applicant) {
        HashMap<String, Applicant> hmap = new HashMap<String, Applicant>();
        hmap.put(applicant.username, applicant);
        JobApplicationSystem jbs = new JobApplicationSystem();
        File file = new File(jbs.applicantFileName + ".ser");
        try {

            FileOutputStream fos = new FileOutputStream(file.getName());
            OutputStream buffer = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(buffer);
            oos.writeObject(hmap);
            oos.close();
            buffer.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void firstWriteToFile(Interviewer interviewer) {
        HashMap<String, Interviewer> hmap = new HashMap<String, Interviewer>();
        hmap.put(interviewer.username, interviewer);
        JobApplicationSystem jbs = new JobApplicationSystem();
        File file = new File(jbs.interviewerFileName + ".ser");
        try {
            FileOutputStream fos = new FileOutputStream(file.getName());
            OutputStream buffer = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(buffer);
            oos.writeObject(hmap);
            oos.close();
            buffer.close();
            fos.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void firstWriteToFile(HRCoordinator hrCoordinator) {
        HashMap<String, HRCoordinator> hmap = new HashMap<String, HRCoordinator>();
        hmap.put(hrCoordinator.username, hrCoordinator);
        JobApplicationSystem jbs = new JobApplicationSystem();
        File file = new File(jbs.HrFileName + ".ser");
        try {
            FileOutputStream fos = new FileOutputStream(file.getName());
            OutputStream buffer = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(buffer);
            oos.writeObject(hmap);
            oos.close();
            buffer.close();
            fos.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void firstWriteToFile(JobPosting job) {
        HashMap<Integer, JobPosting> hmap = new HashMap<Integer, JobPosting>();
        hmap.put(job.jobId, job);
        JobApplicationSystem jbs = new JobApplicationSystem();
        File file = new File(jbs.jobPostingFileName + ".ser");
        try {
            FileOutputStream fos = new FileOutputStream(file.getName());
            OutputStream buffer = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(buffer);
            oos.writeObject(hmap);
            oos.close();
            buffer.close();
            fos.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
