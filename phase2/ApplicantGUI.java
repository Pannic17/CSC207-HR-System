import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ApplicantGUI {
    JPanel panel;
    GUI frame;
    Applicant applicant;
    ApplicantPrompts applicantPrompts;
    ApplicantHelperMethods applicantMethods;
    JobPostingHelperMethods jobPostingMethods;
    JTextField searchBar;
    JTextArea infoText;
    JList<String> infoList;
    JLabel infoLabel;
    JScrollPane infoPane;
    DefaultListModel<String> model;
    String selectedJob;

    public ApplicantGUI(GUI frame, Applicant applicant){
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.frame = frame;
        this.frame.add(this.panel);
        this.panel.setVisible(true);
        this.applicant = applicant;
        this.applicantPrompts = new ApplicantPrompts();
        this.applicantMethods = new ApplicantHelperMethods();
        this.jobPostingMethods = new JobPostingHelperMethods();
        this.model = new DefaultListModel<>();

        this.information();
        this.applicantInterface();
        this.logout();

        model.addElement("");
    }

    void applicantInterface(){

        JButton viewStatus = new JButton("View Status");
        viewStatus.setBounds(10,30,170,25);
        this.panel.add(viewStatus);
        viewStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInfo();
                infoPane.setVisible(false);
                infoText.setVisible(true);
                infoText.setText(applicantMethods.checkStatuses(applicant));
            }
        });

        JButton getHistory = new JButton("Get History");
        getHistory.setBounds(190,30,170,25);
        this.panel.add(getHistory);
        getHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInfo();
                infoPane.setVisible(false);
                infoText.setVisible(true);
                infoText.setText(applicant.getHistoryGUI());
            }
        });

        this.searchBar = new JTextField();
        this.searchBar.setBounds(10,60,350,25);
        model = new DefaultListModel<>();
        this.panel.add(searchBar);

        JButton searchJob = new JButton("Search for a Job");
        searchJob.setBounds(10,90,350,25);
        this.panel.add(searchJob);
        searchJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clearInfo();
                selectedJob = searchBar.getText();
                JobPosting job = jobPostingMethods.getPosting(selectedJob);
                if (job!=null){
                    infoText.setVisible(false);
                    infoPane.setVisible(true);
                    model.addElement(selectedJob);
                    infoList.setModel(model);
                }else {
                    infoText.setVisible(true);
                    infoPane.setVisible(false);
                    infoLabel.setText("Cannot find the job");
                }
            }
        });

        JButton showJob = new JButton("Show All Jobs");
        showJob.setBounds(10,120,170,25);
        this.panel.add(showJob);
        showJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInfo();
                infoText.setVisible(false);
                infoPane.setVisible(true);
                JobApplicationSystem jbs = new JobApplicationSystem();
                for (JobPosting job : jbs.getJobPostingList().values()) {
                    model.addElement(job.jobType);
                    infoList.setModel(model);
                }
            }
        });

        JButton showApp = new JButton("Show Applied Jobs");
        showApp.setBounds(190,120,170,25);
        this.panel.add(showApp);
        showApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInfo();
                infoText.setVisible(false);
                infoPane.setVisible(true);
                ArrayList<String> arrayList = applicantMethods.jobpostingArraytoString(applicant.jobsAppliedTo);
                ArrayList<JobPosting> jobs = applicant.jobsAppliedTo;
                if (arrayList.size() < 1) {
                    infoLabel.setText("The applicant has not applied for a job yet");
                } else {
                    for (String applies : arrayList) {
                        infoLabel.setText(applies + "\n");
                    }
                    for (JobPosting job: jobs){
                        model.addElement(job.jobType);
                        infoList.setModel(model);
                    }
                }
            }
        });

        JButton applyJob = new JButton("Apply to the Job");
        applyJob.setBounds(10,180,350,25);
        this.panel.add(applyJob);
        applyJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                infoLabel.setText("");
                if (selectedJob == null){
                    infoLabel.setText("No job selected");
                    return;
                }
                JobPosting job = jobPostingMethods.getPosting(selectedJob);
                job.apply(applicant);
                FileWriter.writeToFile(applicant);
                infoLabel.setText("Successfully Applied");
            }
        });

        JButton withdrawJob = new JButton("Withdraw from the Job");
        withdrawJob.setBounds(10,210,350,25);
        this.panel.add(withdrawJob);
        withdrawJob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                infoLabel.setText("");
                if (selectedJob == null){
                    infoLabel.setText("No job selected");
                    return;
                }
                JobPosting job = jobPostingMethods.getPosting(selectedJob);
                job.withdraw(applicant);
                applicant.jobsAppliedTo.remove(job);
                FileWriter.writeToFile(applicant);
                infoLabel.setText("Successfully Withdrew");
            }
        });

    }

    void logout(){
        JButton logout = new JButton("Log-Out");
        logout.setBounds(10,270,350,25);
        this.panel.add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                LoginGUI loginGUI = new LoginGUI(frame);
            }
        });
    }

    void clearInfo(){
        model.clear();
        infoList.setModel(model);
        infoText.setText("");
        infoLabel.setText("");
        infoPane.setVisible(false);
        infoText.setVisible(false);
    }

    void information(){

        infoText = new JTextArea();
        infoText.setBounds(400,30,400,270);
        infoText.setVisible(false);
        this.panel.add(infoText);


        infoList = new JList<>();
        infoPane = new JScrollPane(infoList);
        infoPane.setBounds(400,30,400,270);
        infoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        infoList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedJob = infoList.getSelectedValue();
            }
        });
        infoPane.setVisible(false);
        this.panel.add(infoPane);

        infoLabel = new JLabel("");
        infoLabel.setBounds(10,300,800,30);
        this.panel.add(infoLabel);

    }
}
