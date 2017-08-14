package rest.client.mybookingpal.json.quotes;

public class QuoteDetails
{
    private String amount;

    private String text;

    private String paymentInfo;

    private String description;

    private String entity;

    private String type;

    private String included;

    private String currency;

    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getPaymentInfo ()
    {
        return paymentInfo;
    }

    public void setPaymentInfo (String paymentInfo)
    {
        this.paymentInfo = paymentInfo;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getEntity ()
    {
        return entity;
    }

    public void setEntity (String entity)
    {
        this.entity = entity;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getIncluded ()
    {
        return included;
    }

    public void setIncluded (String included)
    {
        this.included = included;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [amount = "+amount+", text = "+text+", paymentInfo = "+paymentInfo+", description = "+description+", entity = "+entity+", type = "+type+", included = "+included+", currency = "+currency+"]";
    }
}