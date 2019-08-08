import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame{
    public static void main(String[] args){
        new GUI();
    }
    int height;
    int width;
    //LoginClass login;

    public GUI(){

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.height = dimension.height;
        this.width = dimension.width;

        this.setSize(this.width/2,this.height/2);
        int xPosition = this.width/4;
        int yPosition = this.height/4;
        this.setLocation(xPosition, yPosition);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Job Application System");

        JobApplicationSystem jobApplicationSystem = new JobApplicationSystem();

        LoginGUI loginGUI = new LoginGUI(this);

        this.setVisible(true);
    }


}



