import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class SignUpGUI extends JFrame{
    JPanel panel;
    GUI frame;
    String selection;
    String usernameEnter;
    String passwordEnter;
    String confirmEnter;
    JPasswordField passwordText;
    JPasswordField confirmText;
    JTextField userText;
    JLabel inconsistentLabel;
    JLabel infoLabel;
    JLabel nameLabel;
    JLabel companyLabel;
    JTextField nameText;
    JTextField companyText;
    JTextArea clText;
    JTextArea cvText;
    JButton applicantUpdate;
    JButton interviewerUpdate;
    JButton hrUpdate;

    public SignUpGUI(GUI frame){
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.frame = frame;
        this.frame.add(this.panel);
        this.panel.setVisible(true);
        this.selection = "";

        this.back();
        this.signUpInterface();
        this.signUpInformation();
        this.signUpOperation();
        this.updateInfo();

    }

    void signUpInterface(){

        JLabel typeLabel = new JLabel("User Type");
        typeLabel.setBounds(10,60,120,25);
        panel.add(typeLabel);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(10,90,120,25);
        panel.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,120,120,25);
        panel.add(passwordLabel);

        JLabel confirmLabel = new JLabel("Confirm Password");
        confirmLabel.setBounds(10,150,120,25);
        panel.add(confirmLabel);


        JComboBox<String> typeBox = new JComboBox<>();
        typeBox.addItem("Applicant");
        typeBox.addItem("Interviewer");
        typeBox.addItem("HR Coordinator");
        typeBox.setBounds(150,60,210,25);
        panel.add(typeBox);
        typeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selection = typeBox.getSelectedItem().toString();
            }
        });

        this.userText = new JTextField(20);
        this.userText.setBounds(150,90,210,25);
        this.panel.add(userText);


        this.passwordText = new JPasswordField(20);
        this.passwordText.setBounds(150,120,210,25);
        this.passwordText.setEchoChar('*');//hide with '*'
        this.panel.add(passwordText);


        this.confirmText = new JPasswordField(20);
        this.confirmText.setBounds(150,150,210,25);
        this.passwordText.setEchoChar('*');//hide with '*'
        this.panel.add(confirmText);

        this.inconsistentLabel = new JLabel("");
        inconsistentLabel.setBounds(10,330,350,25);
        panel.add(inconsistentLabel);

        this.infoLabel = new JLabel("");
        infoLabel.setBounds(10,360,350,300);
        panel.add(infoLabel);

    }

    void signUpInformation(){

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(400,60,120,25);
        nameLabel.setVisible(false);
        panel.add(nameLabel);

        nameText = new JTextField(20);
        nameText.setBounds(530,60,350, 25);
        nameText.setVisible(false);
        panel.add(nameText);

        companyLabel = new JLabel("Name");
        companyLabel.setBounds(400,60,120,25);
        companyLabel.setVisible(false);
        panel.add(companyLabel);

        companyText = new JTextField(20);
        companyText.setBounds(530,60,350, 25);
        companyText.setVisible(false);
        panel.add(companyText);

        clText = new JTextArea("Submit your Cover Letter by copying the text and pasting here.");
        clText.setBounds(400,90,480, 85);
        clText.setVisible(false);
        panel.add(clText);

        cvText = new JTextArea("Submit your CV by copying the text and pasting here.");
        cvText.setBounds(400,185,480, 85);
        cvText.setVisible(false);
        panel.add(cvText);

    }

    void updateInfo(){

        applicantUpdate = new JButton("Update");
        applicantUpdate.setBounds(10,300,350,25);
        panel.add(applicantUpdate);
        applicantUpdate.setVisible(false);
        applicantUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordText.getPassword());
                String username = userText.getText();
                String name = nameText.getText();

                Applicant applicant = new Applicant(username, password, name);
                inconsistentLabel.setText("Successfully create a new applicant");
                //tell the user that he account has been created

                String cl = clText.getText();
                applicant.coverLetter = new CoverLetter();
                applicant.coverLetter.add(cl);

                String cv = cvText.getText();
                applicant.cv = new CV();
                applicant.cv.add(cv);

                infoLabel.setText(applicant.toString());

                FileWriter.writeToFile(applicant);
            }
        });

        interviewerUpdate = new JButton("Update");
        interviewerUpdate.setBounds(10,300,350,25);
        panel.add(interviewerUpdate);
        interviewerUpdate.setVisible(false);
        interviewerUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordText.getPassword());
                String username = userText.getText();
                String name = nameText.getText();
                Interviewer interviewer = new Interviewer(username, password, name);
                inconsistentLabel.setText("Successfully create a new interviewer");

                infoLabel.setText(interviewer.toString());

                FileWriter.writeToFile(interviewer);
            }
        });

        hrUpdate = new JButton("Update");
        hrUpdate.setBounds(10,300,350,25);
        panel.add(hrUpdate);
        hrUpdate.setVisible(false);
        hrUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordText.getPassword());
                String username = userText.getText();
                String name = nameText.getText();
                String company = companyText.getText();

                HRCoordinator hrCoordinator = new HRCoordinator(username, password, name, company);
                inconsistentLabel.setText("Successfully create a new HR Coordinator");

                infoLabel.setText(hrCoordinator.toString());

                FileWriter.writeToFile(hrCoordinator);
            }
        });
    }

    void signUpOperation(){

        JButton signUpButton = new JButton("Sign-Up");
        signUpButton.setBounds(10,270,350,25);
        panel.add(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inconsistentLabel.setText("");
                inconsistentLabel.setVisible(true);
                passwordEnter = new String(passwordText.getPassword());
                confirmEnter = new String(confirmText.getPassword());
                if (!confirmPassword()){
                    return;
                }
                usernameEnter = userText.getText();
                checkSignUp(usernameEnter, passwordEnter);
            }
        });
    }

    void checkSignUp(String username, String password){
        inconsistentLabel.setText("");
        if (selection.equals("Applicant")){ applicantSignUp(username, password); }
        else { if (selection.equals("Interviewer")){ interviewerSignUp(username, password); }
        else { if (selection.equals("HR Coordinator")){ hrSignUp(username, password); }
        else {
            inconsistentLabel.setText("Please select user type");
            userText.setText("");
            passwordText.setText("");
            confirmText.setText("");
        } }
        }
    }

    void clear(){

        inconsistentLabel.setText("");
        infoLabel.setText("");
        nameText.setVisible(false);
        nameLabel.setVisible(false);
        clText.setVisible(false);
        cvText.setVisible(false);
        companyText.setVisible(false);
        companyLabel.setVisible(false);
    }

    void applicantSignUp(String username, String password){
        inconsistentLabel.setText("");
        clear();
        nameLabel.setVisible(true);
        nameText.setVisible(true);
        clText.setVisible(true);
        cvText.setVisible(true);
        ApplicantHelperMethods methods = new ApplicantHelperMethods();
        if (!methods.usernameExists(username)) {
            applicantUpdate.setVisible(true);
        } else {
            inconsistentLabel.setText("This username already exists please choose a different username");
        }
    }

    void interviewerSignUp(String username, String password){
        inconsistentLabel.setText("");
        clear();
        nameLabel.setVisible(true);
        nameText.setVisible(true);
        InterviewerHelperMethods methods = new InterviewerHelperMethods();
        if (!methods.usernameExists(username)) {
            interviewerUpdate.setVisible(true);
        } else {
            inconsistentLabel.setText("This username already exists please choose a different username");
        }
    }

    void hrSignUp(String username, String password){
        clear();
        nameLabel.setVisible(true);
        nameText.setVisible(true);
        companyLabel.setVisible(true);
        companyText.setVisible(true);
        HRHelperMethods methods = new HRHelperMethods();
        if (!methods.usernameExists(username)) {
            hrUpdate.setVisible(true);
        } else {
            System.out.println("This username already exists please choose a different username");
        }
    }


    boolean confirmPassword(){
        if (this.passwordEnter.equals(this.confirmEnter)){
            return true;
        }else{
            inconsistentLabel.setVisible(true);
            inconsistentLabel.setText("The passwords you entered do not match.");
            passwordText.setText("");
            confirmText.setText("");
            return false;
        }
    }

    void back(){

        JButton backButton = new JButton("Back");
        backButton.setBounds(10,30,350,25);
        panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                LoginGUI loginGUI = new LoginGUI(frame);
            }
        });
    }
}
