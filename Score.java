import java.io.Serializable;
import java.util.Calendar;

public class Score implements Serializable {

    private int score=0;
    private String datetime="";

    public Score(int score) {
        this.score = score;
        datetime=Calendar.getInstance().getTime().toString();
    }

    public int getScore() {
        return score;
    }

    public String getDatetime() {
        return datetime;
    }
}
