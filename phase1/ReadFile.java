import java.io.*;

public class ReadFile {

    public ReadFile(String fileName){
        String line = null;

        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine())!= null){
                System.out.println(line);
            }
            bufferedReader.close();
        }catch (FileNotFoundException ex){
            System.out.println("unable to open file '"+fileName+"'");
        }
        catch (IOException ex){
            System.out.println("Error reading file '"+fileName+"'");
        }
    }

    public ReadFile(File file){

    }

    public void convertFileToApplicant(){}
}
