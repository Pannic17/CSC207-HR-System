import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public SignUpGUI(GUI frame){
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.frame = frame;
        this.frame.add(this.panel);
        this.panel.setVisible(true);

        this.back();
        this.signUpInterface();
        this.signUpOperation();

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
        this.panel.add(passwordText);


        this.confirmText = new JPasswordField(20);
        this.confirmText.setBounds(150,150,210,25);
        this.panel.add(confirmText);

        this.inconsistentLabel = new JLabel("The passwords you entered do not match.");
        inconsistentLabel.setBounds(10,210,350,25);
        inconsistentLabel.setVisible(false);
        panel.add(inconsistentLabel);

    }

    void signUpOperation(){

        JButton signUpButton = new JButton("Sign-Up");
        signUpButton.setBounds(10,180,350,25);
        panel.add(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inconsistentLabel.setVisible(false);
                passwordEnter = new String(passwordText.getPassword());
                confirmEnter = new String(confirmText.getPassword());
                if (!confirmPassword()){
                    return;
                }
                usernameEnter = userText.getText();
                //checkUsername();
            }
        });
    }


    boolean confirmPassword(){
        if (this.passwordEnter.equals(this.confirmEnter)){
            inconsistentLabel.setVisible(false);
            return true;
        }else{
            inconsistentLabel.setVisible(true);
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
