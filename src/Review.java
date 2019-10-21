public class Review {

    private String productName;
    private double stars;
    private int helpful;
    private String text;
    private boolean isReal;
    private int numOfWords;




    public Review(String productName, double stars, int helpful, String text, boolean isReal) {
        this.productName = productName;
        this.stars = stars;
        this.helpful = helpful;
        this.text = text;
        this.isReal = isReal;

    }

    public Review(String productName, double stars, int helpful, String review) {
        this.productName = productName;
        this.stars = stars;
        this.helpful = helpful;
        this.text = text;
        this.isReal = isReal;

    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public int getHelpful() {
        return helpful;
    }

    public void setHelpful(int helpful) {
        this.helpful = helpful;
    }

    public String getReview() {
        return text;
    }

    public void setReview(String review) {
        this.text = review;
    }

    public boolean isReal() {
        return isReal;
    }

    public void setReal(boolean real) {
        isReal = real;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNumOfWords() {
      String[] words = text.split(" ");
        return words.length;
    }

    public void setNumOfWords(int numOfWords) {
        this.numOfWords = numOfWords;
    }

}