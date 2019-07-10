
import java.io.*;


public class WriteFile {
    private String path;


    public WriteFile(String filePath) {
        path = filePath;
    }


    public void writeToFile(String textLine) throws IOException {
        //TODO:  check if the file exists


        // file writer takes care of opening the correct file and storing the text as bytes
        FileWriter write = new FileWriter(path);

        // since file writer writes bytes so we pass plain text using print writer
        PrintWriter printLine = new PrintWriter(write);
        printLine.printf("%s" + "%n", textLine);   // %s means a string of characters, %n means a new line
        printLine.close();
    }


    public void bufferedWriteToFile(File file, String content) throws IOException {
        //TODO:  Do I need to check if the file exists?
        try{
        // file writer takes care of opening the correct file and storing the text as bytes
        FileWriter filewriter= new FileWriter(file.getPath());

        // since file writer writes bytes so we pass plain text using print writer
        BufferedWriter bufferedWriter = new BufferedWriter(filewriter);

        bufferedWriter.append(content);
        bufferedWriter.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }


    public void createTextFile(String filename) {
        // check if the file already exists
        File file = new File(filename + ".txt");

    }

    public void createCSVFile(String filename) {
        // check if the file already exists
        File file = new File(filename + ".csv");
    }


    // TODO: method  -   public boolean checkIfFileExists(){}


}
