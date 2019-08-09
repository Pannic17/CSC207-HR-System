import java.io.Serializable;
import java.util.ArrayList;

public class Helpers implements Serializable {
    public Helpers(){}


    public static String arrayToString(String[] name){
//        convert an array of stings to a string
//        TODO: check if takes into account an empty list
        String ret = "[";
        for(int i=0; i<name.length ; i++){
            ret += " "+name[i] +", ";
        }
        ret = ret.substring(0,ret.length()-1);
        ret += " ]";
        return ret;
    }

    public static String arrayToString(ArrayList<String> name){
//        Convert an arraylist to a string
//        TODO: check if takes into account an empty list
        if (name.size() < 1){return "[]";}
        String ret = "[";
        for(int i=0; i<name.size() ; i++){
            ret += " "+name.get(i) +", ";
        }
        ret = ret.substring(0,ret.length()-1);
        ret += " ]";
        return ret;
    }

    public ArrayList<String> applicantArraytoString(ArrayList<Applicant> arrayList){
//        Convert an array of applicants to an array of their names
//        TODO: check if takes into account an empty list
        ArrayList<String> stringList = new ArrayList<>();
        for (int i = 0; i <arrayList.size() ; i++) {
            stringList.add( arrayList.get(i).name);
        }
        return stringList;
    }

    public ArrayList<String> jobpostingArraytoString(ArrayList<JobPosting> arrayList){
//        Convert an array of applicants to an array of their names
//        TODO: check if takes into account an empty list
        ArrayList<String> stringList = new ArrayList<>();
        for (int i = 0; i <arrayList.size() ; i++) {
            stringList.add( arrayList.get(i).toString());
        }

        return stringList;
    }

}
