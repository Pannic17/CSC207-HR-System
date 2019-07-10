import java.util.ArrayList;
import java.util.Scanner;

public class TextGUI {



    public void textGUI () {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter \"applicant\", \"coordinator\", or \"interviewer\" to select your user-type:");
        String memberType = input.next();
        if (memberType.equals("applicant")) {

        }
        if (memberType.equals("coordinator")) {

        }
        if (memberType.equals("interviewer")) {

        }


        boolean continueProgram = true;
        while (continueProgram) {

            String nextOperation = input.next();

            if (nextOperation.equals("logout")) {
                continueProgram = false;
            }
        }
        System.out.println("You have logged out of your account.\n\n\n");
    }



    public static void main(String[] args) {

        TextGUI gui = new TextGUI();
        gui.textGUI();
    }
}
