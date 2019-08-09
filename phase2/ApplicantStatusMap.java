// special type of map that creates the next Arraylist set when there is more than one person in the current list
// Submitted -> Completed phone Interview -> Interview 1 -> Interview 2 -> Interview 3

// will be created on creating a new job posting and initially every list will be empty


import java.io.Serializable;
import java.util.ArrayList;


public class ApplicantStatusMap implements Serializable {
    public ArrayList<Applicant> submittedList;
    public ArrayList<Applicant> phoneInterviewList;
    public ArrayList<Applicant> interview1List;
    public ArrayList<Applicant> interview2List;
    public ArrayList<Applicant> interview3List;

    public ApplicantStatusMap() {
        this.submittedList = new ArrayList<>();
        this.phoneInterviewList = new ArrayList<>();
        this.interview1List = new ArrayList<>();
        this.interview2List = new ArrayList<>();
        this.interview3List = new ArrayList<>();
    }


    public void clear() {
        this.submittedList.clear();
        this.phoneInterviewList.clear();
        this.interview1List.clear();
        this.interview2List.clear();
        this.interview3List.clear();

    }


    public Applicant get(String applicantName) {
        ApplicantHelperMethods applicantHelperMethods = new ApplicantHelperMethods();
        if (applicantHelperMethods.applicantExists(applicantName)) {
            Applicant applicant = applicantHelperMethods.getApplicant(applicantName);
            if (containsValue(applicant)) {
                return applicant;
            } else {
                System.out.println(applicantName + " has not applied for this job");
                return null;
            }
        }
        System.out.println("ApplicantStatusMap.get(): Applicant doesn't exist");
        return null;
    }

    public void moveApplicantToNextPool(Applicant applicant){
        if (this.submittedList.contains(applicant)) {
            this.submittedList.remove(applicant);
            this.phoneInterviewList.add(applicant);

        } else if (this.phoneInterviewList.contains(applicant)) {
            this.phoneInterviewList.remove(applicant);
            this.interview1List.add(applicant);

        } else if (this.interview1List.contains(applicant)) {
            this.interview1List.remove(applicant);
            this.interview2List.add(applicant);
        } else if (this.interview2List.contains(applicant)) {
            this.interview2List.remove(applicant);
            this.interview3List.add(applicant);
        } else if (this.interview3List.contains(applicant)) {
            System.out.println("The applicant is already in the third interview pool");

        }

    }



    public boolean remove(Applicant applicant) {
        if (this.submittedList.contains(applicant)) {
            this.submittedList.remove(applicant);
            return true;
        } else if (this.phoneInterviewList.contains(applicant)) {
            this.phoneInterviewList.remove(applicant);
            return true;
        } else if (this.interview1List.contains(applicant)) {
            this.interview1List.remove(applicant);
            return true;
        } else if (this.interview2List.contains(applicant)) {
            this.interview2List.remove(applicant);
            return true;
        } else if (this.interview3List.contains(applicant)) {
            this.interview3List.remove(applicant);
            return true;
        }
        return false;
    }


    public boolean checkIfOnlyOneApplicantLeft() {
        /* Note: should only call this method when the application close date has passed */
        ArrayList<Applicant> currentArray = this.submittedList;
        if (currentArray.size() == 0) { // no applicant in the submitted list
            return false;
        } else {
            while (currentArray.size() >= 1) {
                if (currentArray.size() == 1) {
//                   TODO: Hire the  Applicant
                    return true;
                } else if (currentArray == this.interview3List && currentArray.size() >= 2) {
                    return false;
                } else {
                    if (currentArray == this.submittedList) {
                        currentArray = this.phoneInterviewList;
                    } else if (currentArray == this.phoneInterviewList) {
                        currentArray = this.interview1List;
                    } else if (currentArray == this.interview1List) {
                        currentArray = this.interview2List;
                    } else if (currentArray == this.interview2List) {
                        currentArray = this.interview3List;
                    }
                }

            }
            return false;
        }
    }


    public boolean isEmpty() {
        return submittedList.isEmpty() && phoneInterviewList.isEmpty() &&
                interview1List.isEmpty() && interview2List.isEmpty() && interview3List.isEmpty();
    }

    public ArrayList<Applicant> getAllApplicants(){
        ArrayList<Applicant> toReturn = new ArrayList<Applicant>();
        toReturn.addAll(this.submittedList);
        toReturn.addAll(this.phoneInterviewList);
        toReturn.addAll(this.interview1List);
        toReturn.addAll(this.interview2List);
        toReturn.addAll(this.interview3List);
        return toReturn;
    }


    public boolean containsValue(Applicant applicant) {
        return submittedList.contains(applicant) |
                phoneInterviewList.contains(applicant) |
                interview1List.contains(applicant)
                | interview2List.contains(applicant) |
                interview3List.contains(applicant);
    }

    public String getPoolContainingValue(Applicant applicant) {
        if (this.submittedList.contains(applicant)) {
            return "Submitted Application";
        } else if (this.phoneInterviewList.contains(applicant)) {
            return "Phone interview";
        } else if (this.interview1List.contains(applicant)) {
            return "Interview 1";
        } else if (this.interview2List.contains(applicant)) {
            return "Interview 2";
        } else if (this.interview3List.contains(applicant)) {
            return "Interview 3";
        }
        return "Applicant has not applied for this job";
    }

}

