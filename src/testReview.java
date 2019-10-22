import java.util.ArrayList;

public class testReview {
    static ArrayList<Integer> doubtScore = new ArrayList<>();
    public static void main(String[] args) {
        doubtScore.add(0);
        Review temp = new Review("Flex Tape", 4.5, 4, "blah blah blah", false );
        starRatingDoubtability(temp.getStars());
        lengthDoubtability(temp.getReview());
        checkExclamation(temp.getReview());
        checkBias(temp.getReview());
        countParagraphs(temp.getReview());
        helpfulScore(temp.getHelpful());

    }
    public static void starRatingDoubtability(double score){
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

    public static void lengthDoubtability(String review){
        int length = 0;
        for (int i = 0; i < review.length(); i++) {
            if(review.substring(i,i+1).equals(" "))
                length++;
        }
        doubtScore.add(length);
        if(length < 20){
            doubtScore.set(0,doubtScore.get(0) + 10);
        }else if(length < 40){
            doubtScore.set(0,doubtScore.get(0) + 6);
        }else if(length < 60){
            doubtScore.set(0,doubtScore.get(0) + 3);
        }
    }

    public static void checkExclamation(String review){
        if(review.contains("!")){
            if(!review.substring(review.indexOf("!"),review.indexOf("!")+1).equals("!") && !review.substring(review.indexOf("!")-1,review.indexOf("!")).equals("!"))
                doubtScore.set(0,doubtScore.get(0) + 10);
        }
    }

    public static void checkBias(String review){
        if(review.contains("free") || review.contains("review"))
            doubtScore.set(0,doubtScore.get(0) + 10);
    }

    public static void countParagraphs(String review){
        String[] temp = review.split("\n");
        if(temp.length == 1){
            doubtScore.set(0,doubtScore.get(0) + 3);
        }
    }

    public static void helpfulScore(int helpful){
        if(helpful == 0){
            doubtScore.set(0,doubtScore.get(0) + 3);
        }
        if(helpful == 1){
            doubtScore.set(0,doubtScore.get(0) + 5);
        }
    }


}
