import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.io.IOException;

public class ChangePassword extends JFrame implements ActionListener
{

    JFrame jf;
    JButton btnUpdate,btnCancel,btnBack;
    JLabel lbl1,lblPass,lblNewPass,lblConfirmPass,lbluser;
    JPasswordField txtPass,txtNewPass,txtConfirmPass;
    JTextField txtuser;
    Statement stmt;
    PreparedStatement ps;
    Connection conn;
    ResultSet rs;
    JPanel pan2;

    public ChangePassword()
    {
        Container cp = getContentPane();
        setSize(450,450);
        setLayout(null);
        setLayout(null);
        setTitle("LOGIN TO LIBRARY");
        cp.setLayout(null);
        JLabel jl = new JLabel("",new ImageIcon(""),JLabel.LEFT);
        jl.setSize(450,450);
        jl.setLocation(0,0);
        cp.add(jl);

        lbl1=new JLabel("Change Password");
        lbl1.setLocation(150,40);
        lbl1.setSize(300,50);
        lbl1.setFont(new Font("Times New Roman", Font.BOLD, 20 ));
        jl.add(lbl1);

        lbluser  = new JLabel("Username");
        lbluser.setSize(100,50);
        lbluser.setLocation(40,100);
        lbluser.setFont(new Font("Times New Roman", Font.BOLD, 16 ));
        jl.add(lbluser);

        txtuser = new JTextField(20);
        txtuser.setSize(200,25);
        txtuser.setLocation(220,110);
        jl.add(txtuser);

        lblPass = new JLabel("Password");
        lblPass.setSize(200,50);
        lblPass.setLocation(40,160);
        lblPass.setFont(new Font("Times New Roman", Font.BOLD, 16 ));
        jl.add(lblPass);

        txtPass = new JPasswordField(20);
        txtPass.setSize(200,25);
        txtPass.setLocation(220,170);
        jl.add(txtPass);

        lblNewPass = new JLabel("New Password");
        lblNewPass.setSize(150,50);
        lblNewPass.setLocation(40,220);
        lblNewPass.setFont(new Font("Times New Roman", Font.BOLD, 16 ));
        jl.add(lblNewPass);

        txtNewPass = new JPasswordField(20);
        txtNewPass.setSize(200,25);
        txtNewPass.setLocation(220,230);
        jl.add(txtNewPass);

        lblConfirmPass = new JLabel("Re-Type Password");
        lblConfirmPass.setSize(250,50);
        lblConfirmPass.setLocation(40,280);
        lblConfirmPass.setFont(new Font("Times New Roman", Font.BOLD, 16 ));
        jl.add(lblConfirmPass);

        txtConfirmPass = new JPasswordField(20);
        txtConfirmPass.setSize(200,25);
        txtConfirmPass.setLocation(220,290);
        jl.add(txtConfirmPass);

        btnUpdate = new JButton("Upadate");
        btnUpdate.setSize(85,25);
        btnUpdate.setLocation(80,350);

        btnUpdate.addActionListener(this);
        jl.add(btnUpdate);

        btnBack = new JButton("Back");
        btnBack.setSize(80,25);
        btnBack.setLocation(173,350);

        btnBack.addActionListener(this);
        jl.add(btnBack);


        btnCancel = new JButton("Clear");
        btnCancel.setSize(80,25);
        btnCancel.setLocation(260,350);

        btnCancel.addActionListener(this);
        jl.add(btnCancel);

        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            conn=DriverManager.getConnection("Jdbc:Odbc:Library","","");
            stmt=conn.createStatement();
        }
        catch(Exception e)
        {}

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()== btnUpdate)
        {
            try
            {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                conn=DriverManager.getConnection("Jdbc:Odbc:Library","","");
                String p1 = "";
                String pk="";
                //	String p = txtuser.getText();
                String p2 = txtPass.getText();
                String p3 = txtNewPass.getText();
                String p4 = txtConfirmPass.getText();
                rs=stmt.executeQuery("select * from Password where username ="+txtuser.getText());

                while(rs.next())
                {

                    p1 = rs.getString(2);
                    if(p2.equals(p1))
                    {
                        if(p3.equals(p4))
                        {
                            ps=conn.prepareStatement("Update Password SET password = ? where username ="+txtuser.getText());
                            ps.setString(2,p4);
                            String p = null;
                            ps.setString(1,p);
                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(null,"Password Changed");


                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Please check your Password n type Again");
                            txtPass.setText("");
                            txtNewPass.setText("");
                            txtuser.setText("");
                            txtConfirmPass.setText("");
                        }
                    }




                }


            }
            catch(Exception e)
            {

            }

        }
        if(ae.getSource()== btnCancel)
        {
            txtPass.setText("");
            txtuser.setText("");
            txtNewPass.setText("");
            txtConfirmPass.setText("");
        }
        if(ae.getSource() == btnBack)
        {
            this.dispose();

        }
    }
    public static void main(String args[])
    {
        JFrame frm = new ChangePassword();
        frm.setSize(450,450);
        frm.setLocation(100,90);
        BufferedImage image = null;
        try {
            image = ImageIO.read(frm.getClass().getResource("Usericon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frm.setIconImage(image);
        frm.setVisible(true);
        frm.show();
    }
}



