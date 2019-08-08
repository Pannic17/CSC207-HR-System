import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreateGUI extends JFrame{
    JPanel panel;
    GUI frame;
    String passwordEnter;
    String confirmEnter;
    JTextField typeText;
    JTextField companyText;
    JTextField idText;
    JTextField yearText;
    JTextField monthText;
    JTextField dayText;
    JLabel infoLabel;
    JTextField requirementText;
    JobPosting newJob;

    public CreateGUI(GUI frame){
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.frame = frame;
        this.frame.add(this.panel);
        this.panel.setVisible(true);

        this.back();
        this.signUpInterface();
        this.createOperation();
        this.addRequirement();

    }

    void signUpInterface(){

        infoLabel = new JLabel("");
        infoLabel.setBounds(10,330,800,30);
        this.panel.add(infoLabel);

        JLabel idLabel = new JLabel("idText");
        idLabel.setBounds(10,60,120,25);
        panel.add(idLabel);

        JLabel typeLabel = new JLabel("Job Type");
        typeLabel.setBounds(10,90,120,25);
        panel.add(typeLabel);

        JLabel companyLabel = new JLabel("Company offering the job");
        companyLabel.setBounds(10,120,120,25);
        panel.add(companyLabel);

        JLabel dateLabel = new JLabel("Closing Date");
        dateLabel.setBounds(10,150,120,25);
        panel.add(dateLabel);

        JLabel yearLabel = new JLabel("Year");
        yearLabel.setBounds(140,150,50,25);
        panel.add(yearLabel);

        JLabel monthLabel = new JLabel("Month");
        monthLabel.setBounds(140,180,50,25);
        panel.add(monthLabel);

        JLabel dayLabel = new JLabel("Day");
        dateLabel.setBounds(140,210,50,25);
        panel.add(dayLabel);


        this.idText = new JTextField(20);
        this.idText.setBounds(150,90,210,25);
        this.panel.add(idText);


        this.typeText = new JTextField(20);
        this.typeText.setBounds(150,120,210,25);
        this.panel.add(typeText);


        this.companyText = new JTextField(20);
        this.companyText.setBounds(150,150,210,25);
        this.panel.add(companyText);

        this.yearText = new JTextField(4);
        this.yearText.setBounds(200,150,100,25);
        this.panel.add(yearText);

        this.monthText = new JTextField(2);
        this.monthText.setBounds(200,180,100,25);
        this.panel.add(monthText);

        this.dayText = new JTextField(2);
        this.dayText.setBounds(200,210,100,25);
        this.panel.add(dayText);

    }

    void createOperation(){

        JButton createButton = new JButton("Create Job Posting");
        createButton.setBounds(10,240,350,25);
        panel.add(createButton);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoLabel.setText("");
                int id = Integer.valueOf(idText.getText());
                String type = typeText.getText();
                String company = companyText.getText();
                int year = Integer.valueOf(yearText.getText());
                int month = Integer.valueOf(monthText.getText());
                int day = Integer.valueOf(dayText.getText());
                LocalDate closingDate = LocalDate.of(year, month, day);
                ArrayList<String> requirements = new ArrayList<>();
                newJob = new JobPosting(id, type, company,closingDate, requirements);
                FileWriter.writeToFile(newJob);
                infoLabel.setText("Successfully created");
            }
        });
    }

    void addRequirement(){

        requirementText = new JTextField();
        requirementText.setBounds(10,270,350,25);
        panel.add(requirementText);

        JButton addButton = new JButton("Add Requirement");
        addButton.setBounds(10,300,350,25);
        panel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoLabel.setText("");
                String requirement = requirementText.getText();
                newJob.requirementsList.add(requirement);
                FileWriter.writeToFile(newJob);
                infoLabel.setText("Successfully added requirement");
            }
        });

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
