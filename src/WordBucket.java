import java.util.ArrayList;

public class WordBucket {

    private ArrayList<String> wordList = new ArrayList<String>();

    public WordBucket() {


    }

    public void add(String s) {
        wordList.add(s);
    }

    public void add(String s, long count) {
        for (long i = 0; i < count; i++) {
            wordList.add(s);
        }
    }

    public int getCountOf(String s) {
        int count = 0;

        for (String word : wordList) {

            if (s.equalsIgnoreCase(word)) count++;

        }
        return count;
    }

    public int size() {
        return wordList.size();
    }

    public int getNumUnique(ArrayList<String> wordList) {
        ArrayList<String> vocab = new ArrayList<String>();

        for (String word : wordList) {

            if (!vocab.contains(word)) {
                vocab.add(word);
            }

        }
        return vocab.size();
    }

    public String getMostFrequent() {
        if (size()==0) return "";
        String mostFrequentWord = "";
        int mostFrequentCount = 0;

        for (String word : wordList) {
            if (getCountOf(word) > mostFrequentCount) {
                mostFrequentCount = getCountOf(word);
                mostFrequentWord = word;
            }
        }

        return mostFrequentWord;
    }



}
