import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InterviewerGUI {
    JPanel panel;
    GUI frame;
    Interviewer interviewer;
    InterviewPrompts interviewPrompts;
    JList<String> infoList;
    JScrollPane infoPane;
    JLabel infoLabel;
    JButton recommendApplicant;
    JButton rejectApplicant;
    DefaultListModel<String> model;
    String selectedApplicant;

    public InterviewerGUI(GUI frame, Interviewer interviewer){
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.frame = frame;
        this.frame.add(this.panel);
        this.panel.setVisible(true);
        this.interviewer = interviewer;
        this.interviewPrompts = new InterviewPrompts();
        this.model = new DefaultListModel<>();

        this.information();
        this.interviewerInterface();
        this.logout();

        model.addElement("");
    }

    void interviewerInterface(){

        JButton viewApplicant = new JButton("View Applicant Interviewing");
        viewApplicant.setBounds(10,30,350,25);
        this.panel.add(viewApplicant);
        viewApplicant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.clear();
                recommendApplicant.setVisible(true);
                rejectApplicant.setVisible(true);
                InterviewerHelperMethods methods = new InterviewerHelperMethods();
                if (interviewer.applicantsInterviewingList.size() < 1) {
                    infoLabel.setText("There are no Applicants to Interview");
                } else {
                    ArrayList<String> applicants = methods.applicantArraytoString(interviewer.applicantsInterviewingList);
                    for (String applicant: applicants){
                        model.addElement(applicant);
                        infoList.setModel(model);
                    }
                }
            }
        });

        recommendApplicant = new JButton("Recommend the Applicant");
        recommendApplicant.setBounds(10,90,350,25);
        recommendApplicant.setVisible(true);
        this.panel.add(recommendApplicant);
        recommendApplicant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoLabel.setText("");
                ApplicantHelperMethods applicantMethods = new ApplicantHelperMethods();
                if (selectedApplicant == null){
                    infoLabel.setText("No applicant selected");
                }else {
                    Applicant applicant = applicantMethods.getApplicant(selectedApplicant);
                    interviewer.recommendedList.add(applicant);
                    infoLabel.setText("Successfully Recommended");
                }
            }
        });

        rejectApplicant = new JButton("Reject the Applicant");
        rejectApplicant.setBounds(10,120,350,25);
        rejectApplicant.setVisible(true);
        this.panel.add(rejectApplicant);
        rejectApplicant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoLabel.setText("");
                ApplicantHelperMethods applicantMethods = new ApplicantHelperMethods();
                if (selectedApplicant == null) {
                    infoLabel.setText("No applicant selected");
                } else {
                    Applicant applicant = applicantMethods.getApplicant(selectedApplicant);
                    interviewer.recommendedList.add(applicant);
                    infoLabel.setText("Successfully Recommended");
                }
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

    void information(){

        infoList = new JList<>();
        infoPane = new JScrollPane(infoList);
        infoPane.setBounds(400,30,400,270);
        infoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        infoList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedApplicant = infoList.getSelectedValue();
            }
        });
        infoPane.setVisible(false);
        this.panel.add(infoPane);

        infoLabel = new JLabel("");
        infoLabel.setBounds(10,300,800,30);
        this.panel.add(infoLabel);

    }
}