import java.io.Serializable;

public class CV implements Serializable {
    // on adding the CV of the applicant the CV id Should be the same as the applicant id
    public String cvString;

    public CV(){
        this.cvString = null;
    }

    public void add(String cv){this.cvString = cv;}

    public void remove(){this.cvString = null;}

    @Override
    public String toString() {
        return this.cvString;
    }
}
