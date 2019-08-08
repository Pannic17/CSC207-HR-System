import java.io.Serializable;
import java.util.HashMap;

public class HRHelperMethods implements Serializable {
    public HRHelperMethods(){}

    public  boolean usernameExists(String HRName) {
        //TODO:  Consider the case where two applicants have the same name
        // read the file to get the applicant
        HashMap<String, HRCoordinator> hmap = FileReader.readHRcoordinatorFile();
        // if you find the same name then return all the things of the applicant
        if (hmap.containsKey(HRName)) {
            return true;
        } else {
            return false;
        }
    }

    public HRCoordinator getHRwithUsername(String username) {
        HashMap<String, HRCoordinator> hashMap = FileReader.readHRcoordinatorFile();
        for (HRCoordinator currentHR : hashMap.values()) {
            if (currentHR.username.equals(username)) {
                return currentHR;
            }
        }
        return null;
    }


}
