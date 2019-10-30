import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class testReview {
    private static ArrayList<Integer> doubtScore = new ArrayList<>();
    private static boolean append_value = false;

    public static void main(String[] args) {
        doubtScore.add(0);
        ArrayList<Review> reviews = makeReviewListV2();
        int counter = 0;
        int writeCounter = 0;
        for (int i = 0; i < reviews.size(); i++) {
            boolean check = runTest(reviews.get(i));
            if (check == reviews.get(i).isReal())
                counter++;
            else {
                if(writeCounter != 0)
                    append_value = true;
                writeCounter++;
                try {
                    WriteFile data = new WriteFile("data/wrong_trials.txt", append_value);
                    data.writeToFile(reviews.get(i).getReview(), doubtScore.get(0));
                }catch(IOException e){
                    System.out.println("encountered writing problem");
                }
                System.out.println("trial failed:" + (i + 1));
            }
        }
        System.out.println((double)counter / reviews.size());

    }

    private static ArrayList<Review> makeReviewListV2() {

        Scanner scanner;
        ArrayList<Review> fileInfoList = new ArrayList<Review>();

        try {
            scanner = new Scanner(new FileInputStream("data/amazon_reviews.txt"), "UTF-8");

            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                Review review = processLineV2(line);
                fileInfoList.add(review);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + "data/reviewListText.txt");
        }

        return fileInfoList;
    }

    private static Review processLineV2(String line) {

        String[] values = line.split("\t");

        double numOfStars = Double.parseDouble(values[2].trim());
        String text = values[8].trim();
        boolean realOrFake = values[1].equalsIgnoreCase("__label1__");



        return new Review(numOfStars, text, realOrFake);


    }
    private static ArrayList<Review> makeReviewList() {

        Scanner scanner;
        ArrayList<Review> fileInfoList = new ArrayList<Review>();

        try {
            scanner = new Scanner(new FileInputStream("data/reviewListText.txt"), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                Review review = processLine(line);
                fileInfoList.add(review);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + "data/reviewListText.txt");
        }

        return fileInfoList;
    }

    private static Review processLine(String line) {

        String[] values = line.split("~");

        double numOfStars = Double.parseDouble(values[0].trim());
        String text = values[1].trim();
        int numOfHelpful = Integer.parseInt(values[2].trim());
        boolean realOrFake = values[3].equalsIgnoreCase("real");



        return new Review(numOfStars, text, numOfHelpful, realOrFake);


    }

    private static boolean runTest(Review review) {
        doubtScore.remove(0);
        doubtScore.add(0);
        starRatingDoubtability(review.getStars());
        lengthDoubtability(review.getReview());
        checkExclamation(review.getReview());
        checkBias(review.getReview());
        countParagraphs(review.getReview());
        //helpfulScore(review.getHelpful());
        compareWords(review.getWordsList(), review.getStars());
        boolean check = doubtScore.get(0) <= 32;
        System.out.println(doubtScore.get(0));
        return check;
    }

    private static void starRatingDoubtability(double score) {
        if (score == 5) {
            doubtScore.set(0, doubtScore.get(0) + 6);
        } else if (score >= 4 && score < 5) {
            doubtScore.set(0, doubtScore.get(0) + 3);
        } else if (score >= 2 && score < 4) {
            doubtScore.set(0, doubtScore.get(0) + 3);
        } else if (score >= 0 && score < 2) {
            doubtScore.set(0, doubtScore.get(0) + 6);
        }
    }

    private static void lengthDoubtability(String review) {
        int length = 0;
        for (int i = 0; i < review.length(); i++) {
            if (review.substring(i, i + 1).equals(" "))
                length++;
        }
        if (length < 20) {
            doubtScore.set(0, doubtScore.get(0) + 32);
        } else if (length < 30) {
            doubtScore.set(0, doubtScore.get(0) + 4);
        } else if (length < 77) {
            doubtScore.set(0, doubtScore.get(0) + 3);
        }else{
            doubtScore.set(0, doubtScore.get(0) + 32);
        }
    }

    private static void checkExclamation(String review) {
        if (review.contains("!")) {
            if (review.substring(review.indexOf("!"), review.indexOf("!") + 1).equals("!") && !review.substring(review.indexOf("!") - 1, review.indexOf("!")).equals("!"))
                doubtScore.set(0, doubtScore.get(0) + 10);
        }
    }

    private static void checkBias(String review) {
        if (review.contains("free") || review.contains("review"))
            doubtScore.set(0, doubtScore.get(0) + 20);
    }

    private static void countParagraphs(String review) {
        String[] temp = review.split("\n");
        if (temp.length == 1) {
            doubtScore.set(0, doubtScore.get(0) + 3);
        }
    }

    private static void helpfulScore(int helpful) {
        if (helpful == 0) {
            doubtScore.set(0, doubtScore.get(0) + 3);
        }
        if (helpful == 1) {
            doubtScore.set(0, doubtScore.get(0) + 5);
        }
    }

    private static void compareWords(String[] review, double stars) {
        String[] positiveWordList = {"quality","Positive", "Great", "Excellent", "mind Blowing", "Terrific", "Good", "Amazing", "fabulous", "guaranteed", "risk-free", "best seller", "satisfy", "safe", "premium", "unique", "authentic", "extrordinary", "remarkable", "dazzling", "brilliant", "staggering", "Terrific", "titanic", "easy", "value", "incredible", "convinient", "stunning", "breathtaking", "awesome", "spectacular", "wonderful", "marvelous", "fantastic", "pleasant"};
        String[] negativeWordList = {"negative", "Bad", "annoying", "damaged", "filthy", "hate", "boring", "dirty", "dreadful", "dishonest", "disgusting", "gross", "harmful", "horrible", "hideous", "lousy", "messy", "nasty", "negative", "never", "old", "poor", "plain", "terrible", "ugly", "unfavorable", "unsatisfactory", "unlucky", "unpleaseant", "worthless"};
        if (stars > 3) {
            for (String temp : review) {
                for (int i = 0; i < positiveWordList.length; i++) {
                    if (positiveWordList[i].equalsIgnoreCase(temp))
                        doubtScore.set(0, doubtScore.get(0) + 2);
                }
            }
        } else {
            for (String temp : review) {
                for (int i = 0; i < negativeWordList.length; i++) {
                    if (negativeWordList[i].equalsIgnoreCase(temp))
                        doubtScore.set(0, doubtScore.get(0) + 2);
                }
            }
        }
    }
}
