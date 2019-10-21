import java.util.ArrayList;

public class testReview {
    public static void main(String[] args) {
        ArrayList<Integer> doubtScore = new ArrayList<>();
        doubtScore.add(0);
        Review temp = new Review("Flex Tape", 4.5, 4, "blah blah blah", false );
        starRatingDoubtability(temp.getStars(), doubtScore);

    }
    public static void starRatingDoubtability(double score, ArrayList<Integer> doubtScore){
        if(score == 5){
            doubtScore.set(0,doubtScore.get(0) + 6);
        }else if(score >= 4 && score < 5){
            doubtScore.set(0,doubtScore.get(0) + 3);
        }else if(score >= 2 && score < 3){
            doubtScore.set(0,doubtScore.get(0) + 3);
        }else if(score >= 0 && score < 2){
            doubtScore.set(0,doubtScore.get(0) + 6);
        }
    }


}
