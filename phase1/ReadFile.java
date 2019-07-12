import java.io.*;

public class ReadFile {
    public ReadFile(){}

    public void printIt(String fileName){
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

    public String returnIt(String fileName){
        String line = null;
        String file = "";
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine())!= null){
                file += line;
            }
            bufferedReader.close();
            return file;
        }catch (FileNotFoundException ex){
            System.out.println("unable to open file '"+fileName+"'");
            return null;
        }
        catch (IOException ex){
            System.out.println("Error reading file '"+fileName+"'");
            return null;
        }
    }

    public ReadFile(File file){

    }

    public void convertFileToApplicant(){}
}
