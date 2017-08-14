package rest.client.mybookingpal.json.quotes;

public class QuotesResponse {
    private Quotes quotes;

    public Quotes getQuotes() {
        return quotes;
    }

    public void setQuotes(Quotes quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return "ClassPojo [quotes = " + quotes + "]";
    }
}
