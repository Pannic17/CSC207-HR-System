import com.sun.xml.internal.bind.v2.TODO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class LoginGUI {
    JPanel panel;
    GUI frame;
    LoginClass login;
    String selection;
    String usernameEnter;
    String passwordEnter;
    JTextField userText;
    JPasswordField passwordText;
    JLabel loginStatus;
    Serializable user;

    public LoginGUI(GUI frame){

        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.frame = frame;
        this.frame.add(this.panel);
        this.panel.setVisible(true);
        this.login = new LoginClass();
        this.selection = "";

        this.loginConfig(this.panel);
        this.loginInterface(this.panel);
        this.signUp(this.panel);
        this.loginStatus(this.panel);
    }

    public void loginConfig(JPanel configPanel){

        JButton configButton = new JButton("Configure");
        configButton.setBounds(10,30, 350, 25);
        configButton.setToolTipText("If it is your first time using the system, please press configure button.");
        configPanel.add(configButton);

        JLabel configLabel = new JLabel("Successfully Configured");
        configLabel.setBounds(10,30,350,25);
        configPanel.add(configLabel);
        configLabel.setVisible(false);

        configButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configButton.setVisible(false);
                configLabel.setVisible(true);
                login.FirstTime();
            }
        });

    }

    public void loginInterface(JPanel loginPanel){

        JLabel typeLabel = new JLabel("User Type");
        typeLabel.setBounds(10,60,120,25);
        loginPanel.add(typeLabel);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10,90,120,25);
        loginPanel.add(userLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,120,120,25);
        loginPanel.add(passwordLabel);

        JComboBox<String> typeBox = new JComboBox<>();
        typeBox.addItem("Applicant");
        typeBox.addItem("Interviewer");
        typeBox.addItem("HR Coordinator");
        typeBox.setBounds(150,60,210,25);
        loginPanel.add(typeBox);
        typeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selection = typeBox.getSelectedItem().toString();
            }
        });

        this.userText = new JTextField(20);
        userText.setBounds(150,90,210,25);
        loginPanel.add(userText);


        this.passwordText = new JPasswordField(20);
        passwordText.setBounds(150,120,210,25);
        loginPanel.add(passwordText);


        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10,150,350,25);
        loginPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
                passwordEnter = new String(passwordText.getPassword());
                usernameEnter = userText.getText();

                //LocalDate date =LocalDate.now();
                //Serializable userA = new Applicant("1","1","1", date);
                //Serializable userB = new Interviewer("1","1","1");
                //Serializable userC = new HRCoordinator("1","1","1","1");
                if (checkPassword(usernameEnter, passwordEnter)){
                    panel.setVisible(false);
                    checkType(user);
                }else {
                    userText.setText("");
                    passwordText.setText("");
                }
            }
        });

    }

    boolean applicantLogin(String username, String password){
        ApplicantHelperMethods methods = new ApplicantHelperMethods();
        if (methods.usernameExists(username)) {
            Applicant applicant = methods.getApplicantWithUsername(username);
            if (applicant.password.equals(password)) {
                user = applicant;
                return true;
            } else {
                loginStatus.setText("The password doesn't match");
                return false;
            }
        } else {
            loginStatus.setText("The username doesn't exist");
            return false;
        }
    }

    boolean interviewerLogin(String username, String password){
        InterviewerHelperMethods methods = new InterviewerHelperMethods();
        if (methods.usernameExists(username)) {
            Interviewer interviewer = methods.getInterviewerWithUsername(username);
            if (interviewer.password.equals(password)) {
                user = interviewer;
                return true;
            } else {
                loginStatus.setText("The password doesn't match");
                return false;
            }
        } else {
            loginStatus.setText("The username doesn't exist");
            return false;
        }
    }

    boolean hrLogin(String username, String password){
        HRHelperMethods methods = new HRHelperMethods();
        if (methods.usernameExists(username)) {
            HRCoordinator hr = methods.getHRwithUsername(username);
            if (hr.password.equals(password)) {
                user = hr;
                return true;
            } else {
                loginStatus.setText("The password doesn't match");
                return false;
            }
        } else {
            loginStatus.setText("The username doesn't exist");
            return false;
        }
    }

    boolean checkPassword(String username, String password){
        loginStatus.setText("");
        if (selection.equals("Applicant")){ return applicantLogin(username, password); }
        else { if (selection.equals("Interviewer")){ return interviewerLogin(username, password); }
        else { if (selection.equals("HR Coordinator")){ return hrLogin(username, password); }
        else { loginStatus.setText("Please select user type."); return false;} }
        }
    }

    void checkType(Serializable user){
        LocalDate date =LocalDate.now();
        if (this.selection.equals("Applicant")){
            Applicant applicant = (Applicant) user;
            Applicant applicantTest =  new Applicant("1","1","1", date);
            ApplicantGUI applicantGUI = new ApplicantGUI(frame, applicant);
        }else {
            if (this.selection.equals("Interviewer")){
                Interviewer interviewerTest = new Interviewer("1","1","1");
                Interviewer interviewer = (Interviewer) user;
                InterviewerGUI interviewerGUI = new InterviewerGUI(frame, interviewer);
            }else {
                if (this.selection.equals("HR Coordinator")){
                    HRCoordinator hrCoordinator = (HRCoordinator) user;
                    HRCoordinator hrTest = new HRCoordinator("1","1","1","1");
                    HRGUI hrGUI = new HRGUI(frame, hrCoordinator);
                }else {
                    LoginGUI loginGUI = new LoginGUI(frame);
                }
            }
        }
    }

    void loginStatus(JPanel loginPanel){

        JButton forgetPassword = new JButton("Forget Password");
        forgetPassword.setBounds(10,210,350,25);
        loginPanel.add(forgetPassword);
        forgetPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePassword();
            }
        });

        loginStatus = new JLabel("");
        loginStatus.setBounds(10,240,350,25);
        loginPanel.add(loginStatus);
    }

    public void signUp(JPanel loginPanel){
        JButton signUpButton = new JButton("Sign-Up");
        signUpButton.setBounds(10,180,350,25);
        loginPanel.add(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpHelper(loginPanel);
            }
        });
    }

    void signUpHelper(JPanel loginPanel){
        loginPanel.setVisible(false);
        SignUpGUI signUp = new SignUpGUI(this.frame);

    }
}

