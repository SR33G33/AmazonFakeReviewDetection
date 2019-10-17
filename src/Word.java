public class Word {
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    String word;
    int frequency;
    public Word(String word, int frequency) {
        this.frequency = frequency;
        this.word = word;


    }
}
