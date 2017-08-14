package rest.client.mybookingpal.json.quotes;

public class Quote_details
{
    private String total;

    private QuoteDetails[] quoteDetails;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public QuoteDetails[] getQuoteDetails ()
    {
        return quoteDetails;
    }

    public void setQuoteDetails (QuoteDetails[] quoteDetails)
    {
        this.quoteDetails = quoteDetails;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [total = "+total+", quoteDetails = "+quoteDetails+"]";
    }
}
