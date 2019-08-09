import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HRGUI {
    JPanel panel;
    GUI frame;
    HRPrompts hrPrompts;
    JTextField searchBar;
    private JTextArea infoText;
    private JList<String> infoList1;
    private JList<String> infoList2;
    private JScrollPane infoPane1;
    private JScrollPane infoPane2;
    private JButton viewDocuments;
    private JButton viewJobsApplied;
    String selected1;
    String selected2;
    JButton match;
    JButton assign;
    JButton getResult;
    JLabel infoLabel;
    DefaultListModel<String> model1;
    DefaultListModel<String> model2;
    HRCoordinator hrCoordinator;

    public HRGUI(GUI frame, HRCoordinator hrCoordinator){
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.frame = frame;
        this.frame.add(this.panel);
        this.panel.setVisible(true);
        this.hrPrompts = new HRPrompts();
        this.hrCoordinator = hrCoordinator;
        this.model1 = new DefaultListModel<>();
        this.model2 = new DefaultListModel<>();


        this.information();
        this.applicantInterface();
        this.interviewerInterface();
        this.jobInterface();
        this.logout();

    }

    void applicantInterface(){

        JLabel applicantLabel = new JLabel("Applicant");
        applicantLabel.setBounds(10,30,350,25);
        this.panel.add(applicantLabel);

        viewDocuments = new JButton("View Applicant's Documents");
        viewDocuments.setBounds(400,300,310,25);
        viewDocuments.setVisible(false);
        this.panel.add(viewDocuments);
        viewDocuments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInfo();
                infoPane1.setVisible(true);
                viewDocuments.setVisible(true);
                viewJobsApplied.setVisible(true);
                infoText.setVisible(true);
                ApplicantHelperMethods applicantMethods = new ApplicantHelperMethods();
                if (selected1==null){
                    infoLabel.setText("No applicant selected");
                }else {
                    Applicant applicant = applicantMethods.getApplicant(selected1);
                    if (applicant.coverLetter.equals(null) && applicant.cv.equals(null)) {
                        infoText.setText("The applicant's documents are not available.");
                    } else {
                        if (applicant.coverLetter != null) {
                            infoText.setText(applicant.coverLetter.letter);
                        }
                        if (applicant.cv != null) {
                            infoText.setText(applicant.cv.cvString);
                        }
                    }
                }
            }
        });

        viewJobsApplied = new JButton("View Jobs Applied");
        viewJobsApplied.setBounds(400,330,310,25);
        viewJobsApplied.setVisible(false);
        this.panel.add(viewJobsApplied);
        viewJobsApplied.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInfo();
                infoPane1.setVisible(true);
                viewDocuments.setVisible(true);
                viewJobsApplied.setVisible(true);
                infoText.setVisible(true);
                ApplicantHelperMethods applicantMethods = new ApplicantHelperMethods();
                if (selected1==null){
                    infoText.setText("No applicant selected");
                }else {
                    Applicant applicant = applicantMethods.getApplicant(selected1);
                    ArrayList<String> arrayList = applicantMethods.jobpostingArraytoString(hrCoordinator.applicantJobsAppliedInCompany(applicant));
                    if (arrayList.size() < 1) {
                        infoText.setText("The applicant has not applied for a job yet");
                    } else {
                        for (String applies: arrayList){
                            infoText.setText(applies+"\n");
                        }
                    }
                }
            }
        });

        this.searchBar = new JTextField();
        this.searchBar.setBounds(10,60,350,25);

        JButton showApplicant = new JButton("Show All Applicants");
        showApplicant.setBounds(10,60,350,25);
        this.panel.add(showApplicant);
        showApplicant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model1.clear();
                clearInfo();
                infoPane1.setVisible(true);
                viewDocuments.setVisible(true);
                viewJobsApplied.setVisible(true);
                JobApplicationSystem jbs = new JobApplicationSystem();
                for (Applicant applicant: jbs.getApplicantList().values()){
                    model1.addElement(applicant.name);
                    infoList1.setModel(model1);
                }
            }
        });

    }

    void interviewerInterface(){

        JLabel interviewerLabel = new JLabel("Interviewer");
        interviewerLabel.setBounds(10,120,350,20);
        this.panel.add(interviewerLabel);

        JButton showInterviewer = new JButton("Show All Interviewer");
        showInterviewer.setBounds(10,150,350,25);
        this.panel.add(showInterviewer);
        showInterviewer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!model1.isEmpty()){ clearModel();}
                clearInfo();
                getResult.setVisible(true);
                infoPane1.setVisible(true);
                JobApplicationSystem jbs = new JobApplicationSystem();
                for (Interviewer interviewer: jbs.getInterviewerList().values()){
                    model1.addElement(interviewer.name);
                    infoList1.setModel(model1);
                }
            }
        });

        JButton assignJobs = new JButton("Assign Job to Interviewer");
        assignJobs.setBounds(10,180,350,25);
        this.panel.add(assignJobs);
        assignJobs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInfo();
                model2.clear();
                getResult.setVisible(true);
                infoPane1.setVisible(true);
                JobApplicationSystem jbs = new JobApplicationSystem();
                for (JobPosting job: jbs.getJobPostingList().values()){
                    model2.addElement(job.jobType);
                    infoList2.setModel(model2);
                }
                infoPane2.setVisible(true);
                assign.setVisible(true);
            }
        });

        JButton showMatch = new JButton("Match Applicant with interviewer");
        showMatch.setBounds(10,210,350,25);
        this.panel.add(showMatch);
        showMatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInfo();
                model2.clear();
                getResult.setVisible(true);
                infoPane1.setVisible(true);
                JobApplicationSystem jbs = new JobApplicationSystem();
                for (Applicant applicant: jbs.getApplicantList().values()){
                    model2.addElement(applicant.name);
                    infoList2.setModel(model2);
                }
                infoPane2.setVisible(true);
                match.setVisible(true);
            }
        });

        getResult = new JButton("Get Result from Interviewer");
        getResult.setBounds(400,330,310,25);
        getResult.setVisible(false);
        this.panel.add(getResult);
        getResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getResult.setVisible(true);
                infoPane1.setVisible(true);
                infoText.setVisible(true);
                viewDocuments.setVisible(true);
                viewJobsApplied.setVisible(true);
                InterviewerHelperMethods interviewerMethods = new InterviewerHelperMethods();
                if (selected1==null){
                    infoLabel.setText("No interviewer selected");
                }else {
                    Interviewer interviewer = interviewerMethods.getInterviewerWithName(selected1);
                    infoText.setText("Rejected List: " + interviewerMethods.applicantArraytoString(interviewer.notRecommendedList) +
                            "\n" + "Recommended List: " + interviewerMethods.applicantArraytoString(interviewer.recommendedList));
                }

            }
        });

        match = new JButton("Match");
        match.setBounds(400,300,310,25);
        match.setVisible(false);
        this.panel.add(match);
        match.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInfo();
                getResult.setVisible(true);
                infoPane1.setVisible(true);
                infoPane2.setVisible(true);
                assign.setVisible(true);
                InterviewerHelperMethods interviewerMethods = new InterviewerHelperMethods();
                ApplicantHelperMethods applicantMethods = new ApplicantHelperMethods();
                if (selected1==null){
                    infoLabel.setText("No interviewer selected");
                }else {
                    if (selected2==null){
                        infoLabel.setText("No job selected");
                    }else {
                        Interviewer interviewer = interviewerMethods.getInterviewerWithName(selected1);
                        Applicant applicant =applicantMethods.getApplicant(selected2);
                        interviewer.applicantsInterviewingList.add(applicant);
                        interviewer.assignedJob.applicantStatus.moveApplicantToNextPool(applicant);
                        infoLabel.setText("Successfully Matched");
                    }
                }
            }
        });

        assign = new JButton("Assign");
        assign.setBounds(400,300,310,25);
        assign.setVisible(false);
        this.panel.add(assign);
        assign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInfo();
                getResult.setVisible(true);
                infoPane1.setVisible(true);
                infoPane2.setVisible(true);
                assign.setVisible(true);
                InterviewerHelperMethods interviewerMethods = new InterviewerHelperMethods();
                JobPostingHelperMethods jobPostingMethods = new JobPostingHelperMethods();
                if (selected1==null){
                    infoLabel.setText("No interviewer selected");
                }else {
                    if (selected2==null){
                        infoLabel.setText("No job selected");
                    }else {
                        Interviewer interviewer = interviewerMethods.getInterviewerWithName(selected1);
                        JobPosting job = jobPostingMethods.getPosting(selected2);
                        interviewer.assignJob(job);
                        infoLabel.setText("Successfully Assigned");
                    }
                }
            }
        });

    }

    void jobInterface(){

        JButton createJob = new JButton("Create a New Job");
        createJob.setBounds(10,270,350,25);
        this.panel.add(createJob);
        createJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                CreateGUI createGUI = new CreateGUI(frame);
            }
        });

    }

    void logout(){
        JButton logout = new JButton("Log-Out");
        logout.setBounds(10,330,350,25);
        this.panel.add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                LoginGUI loginGUI = new LoginGUI(frame);
            }
        });
    }

    void clearModel(){
        model1.clear();
        model2.clear();
        infoList1.setModel(model1);
        infoList2.setModel(model1);
    }

    void clearInfo(){

        infoLabel.setText("");
        infoText.setText("");
        infoText.setVisible(false);
        infoPane1.setVisible(false);
        infoPane2.setVisible(false);
        match.setVisible(false);
        assign.setVisible(false);
        getResult.setVisible(false);
        viewJobsApplied.setVisible(false);
        viewDocuments.setVisible(false);
    }

    void information(){

        infoText = new JTextArea();
        infoText.setBounds(720,30,310,270);
        infoText.setVisible(false);
        this.panel.add(infoText);

        infoLabel = new JLabel("");
        infoLabel.setBounds(10,300,800,30);
        this.panel.add(infoLabel);

        infoList1 = new JList<>();
        infoPane1 = new JScrollPane(infoList1);
        infoPane1.setBounds(400,30,310,270);
        infoPane1.setVisible(false);
        infoList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        infoList1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selected1 = infoList1.getSelectedValue();
            }
        });
        this.panel.add(infoPane1);

        infoList2 = new JList<>();
        infoPane2 = new JScrollPane(infoList2);
        infoPane2.setBounds(720,30,310,270);
        infoPane2.setVisible(false);
        infoList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        infoList2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selected2 = infoList2.getSelectedValue();
            }
        });
        this.panel.add(infoPane2);

    }
}
