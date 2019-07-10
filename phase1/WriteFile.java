
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


public class WriteFile {
    private String path;
    private boolean appendToFile = false;

    public WriteFile(String filePath) {
        path = filePath;
    }

    public WriteFile(String filePath, boolean appendValue) {
        path = filePath;
        appendToFile = appendValue;
    }

    public void writeToFile(String textLine) throws IOException {
        //TODO:  check if the file exists


        // file writer takes care of opening the correct file and storing the text as bytes
        FileWriter write = new FileWriter(path, appendToFile);

        // since file writer writes bytes so we pass plain text using print writer
        PrintWriter printLine = new PrintWriter(write);
        printLine.printf("%s" + "%n", textLine);   // %s means a string of characters, %n means a new line
        printLine.close();
    }


    public void bufferedWriteToFile(String textLine) throws IOException {
        //TODO:  check if the file exists

        // file writer takes care of opening the correct file and storing the text as bytes
        FileWriter write = new FileWriter(path, appendToFile);

        // since file writer writes bytes so we pass plain text using print writer
        PrintWriter printLine = new PrintWriter(write);
        printLine.printf("%s" + "%n", textLine);   // %s means a string of characters, %n means a new line
        printLine.close();
    }


    public void createFile(String filename) {
        // check if the file already exists
        File file = new File(filename + ".txt");
    }


    // TODO: method  -   public boolean checkIfFileExists(){}


}
