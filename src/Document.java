import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Document {
    private String text = "";
    private ArrayList<String> sentences;
    private ArrayList<String> words;
    private int sizeOfVocab;
    private double fleschKinkaidScore;
    boolean areSentencesCurrent = true;
    boolean areWordsCurrent = true;
    boolean isFKScoreCurrent = true;
    boolean isNumOfSyllablesCurrent;
    boolean isSizeOfVocabCurrent;


    public Document(String text) {
        this.text = text;
        this.sentences = splitIntoSentences(text);
        this.sizeOfVocab = getVocabSize();
        this.fleschKinkaidScore = getFleschKinkaidScore(text);

    }

    public void setText(String newText) {
        this.text = newText;
        areSentencesCurrent = false;
        areWordsCurrent = false;
        isFKScoreCurrent = false;
        isNumOfSyllablesCurrent = false;
        isSizeOfVocabCurrent = false;

    }

    public static Document makeDocument(String text) {
        Document doc = new Document(text);
        return doc;
    }

    private int getWordCount() {
        int totalWords = 0;
        for (int i = 0; i < sentences.size(); i++) {

            ArrayList<String> wordList = splitIntoWords(sentences.get(i));
            totalWords += wordList.size();
        }

        return totalWords;
    }

    private int getSentenceCount() {
        return sentences.size();
    }

    public double averageWordsPerSentence() {
        return (double) getWordCount() / getSentenceCount();
    }

    public double averageNumOfCharsPerWord() {
        ArrayList<String> wordList;
        int totalChars = 0;
        double averagePerWord = 0;
        for (int i = 0; i < sentences.size(); i++) {

            wordList = splitIntoWords(sentences.get(i));


            for (int j = 0; j < wordList.size(); j++) {
                totalChars += wordList.get(j).length();
            }

            averagePerWord += (double) totalChars / wordList.size();
        }

        return averagePerWord / sentences.size();

    }

    private int getVocabSize() {

        ArrayList<String> wordList;
        ArrayList<String> vocab = new ArrayList<String>();
        for (int i = 0; i < sentences.size(); i++) {

            wordList = splitIntoWords(sentences.get(i));


            for (int j = 0; j < wordList.size(); j++) {

                if (!isInList(vocab, wordList.get(j))) {
                    vocab.add(wordList.get(j));
                }


            }

        }

        return vocab.size();

    }

    private boolean isInList(ArrayList<String> arr, String word) {

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<String> splitIntoSentences(String text) {
        ArrayList<String> output = new ArrayList<>();

        Locale locale = Locale.US;
        BreakIterator breakIterator = BreakIterator.getSentenceInstance(locale);
        breakIterator.setText(text);

        int prevIndex = 0;
        int boundaryIndex = breakIterator.first();
        while (boundaryIndex != BreakIterator.DONE) {
            String sentence = text.substring(prevIndex, boundaryIndex).trim();
            if (sentence.length() > 0)
                output.add(sentence);
            prevIndex = boundaryIndex;
            boundaryIndex = breakIterator.next();
        }

        String sentence = text.substring(prevIndex).trim();
        if (sentence.length() > 0)
            output.add(sentence);

        return output;
    }


    private double getFleschKinkaidScore(String text) {

        double fKScore;
        sentences = splitIntoSentences(text);
        int totalSentences = sentences.size();
        int totalWords = getWordCount();
        int totalSyllables = 0;

        for (int i = 0; i < sentences.size(); i++) {
            ArrayList<String> wordList = splitIntoWords(sentences.get(i));
            totalSyllables += getNumOfSyllables(wordList);

        }

        fKScore = 206.835 - 1.015 * ((double) totalWords / totalSentences) - 84.6 * ((double) totalSyllables / totalWords);

        return fKScore;

    }

    private static ArrayList<String> splitIntoWords(String sentence) {

        String[] words = sentence.split(" ");
        ArrayList<String> newWords = new ArrayList<String>();

        for (int i = 0; i < words.length; i++) {
            newWords.add(removePunctuation(words[i]));

        }


        return newWords;
    }

    private static int getNumOfSyllables(ArrayList<String> wordList) {
        int totalNumOfSyllables = 0;
        for (int i = 0; i < wordList.size(); i++) {
            totalNumOfSyllables += syllablesFor(wordList.get(i));
        }

        return totalNumOfSyllables;
    }

    private static String removePunctuation(String word) {
        String newWord = "";
        for (int i = 0; i < word.length(); i++) {

            String letter = word.substring(i, i + 1);
            if (!"!@#$%^&*<>?:;.,”“()[]{}".contains(letter)) {
                newWord += letter;
            }

        }

        return newWord;

    }

    private static int syllablesFor(String testWord) {
        int vowelChains = 0;
        int syllables = 0;
        ArrayList<Integer> syllablesList = new ArrayList<Integer>();


        vowelChains = 0;

        if (!testWord.equals("") && isVowel(testWord.substring(0, 1))) {
            vowelChains++;
        }

        for (int i = 1; i < testWord.length(); i++) {
            String previousLetter = testWord.substring(i - 1, i);
            String letter = testWord.substring(i, i + 1);

            if (isVowel(letter) && !isVowel(previousLetter)) {
                vowelChains++;
            }

        }
        syllables = vowelChains;

        for (int letter = 0; letter < testWord.length() - 2; letter++) {
            if (isVowel(testWord.substring(letter, letter + 1)) && !isVowel(testWord.substring(letter + 1, letter + 2)) && testWord.substring(letter + 2, letter + 3).equals("e") && letter + 3 == testWord.length()) {
                syllables--;
            }
        }

        if (testWord.contains("iu")) syllables++;
        if (testWord.contains("ia")) syllables++;
        if (testWord.contains("zool")) syllables++;
        if (testWord.contains("ered")) syllables--;
        if (testWord.contains("iest")) syllables++;
        if (testWord.contains("ier")) syllables++;
        if (testWord.contains("vio") && testWord.indexOf("vio") == 0) syllables++;


        syllablesList.add(syllables);


        return syllables;
    }

    private static boolean isVowel(String letter) {
        return "aeiouy".contains(letter);

    }

    private static ArrayList<WordFrequency> makeFrequencyList(String filename) {

        Scanner scanner;
        ArrayList<WordFrequency> WordFrequencyList = new ArrayList<WordFrequency>();

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                WordFrequency info = processLine(line);
                WordFrequencyList.add(info);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }

        return WordFrequencyList;
    }


    public static WordFrequency processLine(String line) {

        String[] values = line.split("\t");

        String filename = values[0].trim();
        long frequency = Long.parseLong(values[1].trim());

        return new WordFrequency(filename, frequency);


    }

    public static void testWordFrequencyMethod(){
        ArrayList<WordFrequency> test = makeFrequencyList("data/commonWords.txt");

        for (int i = 0; i < test.size(); i++) {

            System.out.println(test.get(i).getWord() + "\t" + test.get(i).getFrequency());

        }
        System.out.println(test.size());

    }


}

