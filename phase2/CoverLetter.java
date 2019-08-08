import java.io.Serializable;

public class CoverLetter implements Serializable {
    public String letter;

    public CoverLetter(){
        this.letter = null;
    }

    public void add(String coverletter){
        this.letter = coverletter;
    }

    public void remove(){this.letter = null;}

    @Override
    public String toString() {
        return this.letter;
    }
}
