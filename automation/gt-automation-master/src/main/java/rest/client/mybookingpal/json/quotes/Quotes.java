package rest.client.mybookingpal.json.quotes;

public class Quotes
{
    private String property_currency;

    private PropertyManagerSupportCC propertyManagerSupportCC;

    private String propertyName;

    private String paymentSupported;

    private String imageUrl;

    private String deposit;

    private String agentCommission;

    private String termsLink;

    private String fromTime;

    private String available;

    private String agentCommissionValue;

    private String minstay;

    private String toTime;

    private String is_error;

    private String secondPaymentDate;

    private String currency;

    private String message;

    private String messageCode;

    private String price;

    private Quote_details quote_details;

    private String firstPayment;

    private String quote;

    private String secondPayment;

    private String property_quote;

    private CancellationItems[] cancellationItems;

    public String getProperty_currency ()
    {
        return property_currency;
    }

    public void setProperty_currency (String property_currency)
    {
        this.property_currency = property_currency;
    }

    public PropertyManagerSupportCC getPropertyManagerSupportCC ()
    {
        return propertyManagerSupportCC;
    }

    public void setPropertyManagerSupportCC (PropertyManagerSupportCC propertyManagerSupportCC)
    {
        this.propertyManagerSupportCC = propertyManagerSupportCC;
    }

    public String getPropertyName ()
    {
        return propertyName;
    }

    public void setPropertyName (String propertyName)
    {
        this.propertyName = propertyName;
    }

    public String getPaymentSupported ()
    {
        return paymentSupported;
    }

    public void setPaymentSupported (String paymentSupported)
    {
        this.paymentSupported = paymentSupported;
    }

    public String getImageUrl ()
    {
        return imageUrl;
    }

    public void setImageUrl (String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getDeposit ()
    {
        return deposit;
    }

    public void setDeposit (String deposit)
    {
        this.deposit = deposit;
    }

    public String getAgentCommission ()
    {
        return agentCommission;
    }

    public void setAgentCommission (String agentCommission)
    {
        this.agentCommission = agentCommission;
    }

    public String getTermsLink ()
    {
        return termsLink;
    }

    public void setTermsLink (String termsLink)
    {
        this.termsLink = termsLink;
    }

    public String getFromTime ()
    {
        return fromTime;
    }

    public void setFromTime (String fromTime)
    {
        this.fromTime = fromTime;
    }

    public String getAvailable ()
    {
        return available;
    }

    public void setAvailable (String available)
    {
        this.available = available;
    }

    public String getAgentCommissionValue ()
    {
        return agentCommissionValue;
    }

    public void setAgentCommissionValue (String agentCommissionValue)
    {
        this.agentCommissionValue = agentCommissionValue;
    }

    public String getMinstay ()
    {
        return minstay;
    }

    public void setMinstay (String minstay)
    {
        this.minstay = minstay;
    }

    public String getToTime ()
    {
        return toTime;
    }

    public void setToTime (String toTime)
    {
        this.toTime = toTime;
    }

    public String getIs_error ()
    {
        return is_error;
    }

    public void setIs_error (String is_error)
    {
        this.is_error = is_error;
    }

    public String getSecondPaymentDate ()
    {
        return secondPaymentDate;
    }

    public void setSecondPaymentDate (String secondPaymentDate)
    {
        this.secondPaymentDate = secondPaymentDate;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getMessageCode ()
    {
        return messageCode;
    }

    public void setMessageCode (String messageCode)
    {
        this.messageCode = messageCode;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public Quote_details getQuote_details ()
    {
        return quote_details;
    }

    public void setQuote_details (Quote_details quote_details)
    {
        this.quote_details = quote_details;
    }

    public String getFirstPayment ()
    {
        return firstPayment;
    }

    public void setFirstPayment (String firstPayment)
    {
        this.firstPayment = firstPayment;
    }

    public String getQuote ()
    {
        return quote;
    }

    public void setQuote (String quote)
    {
        this.quote = quote;
    }

    public String getSecondPayment ()
    {
        return secondPayment;
    }

    public void setSecondPayment (String secondPayment)
    {
        this.secondPayment = secondPayment;
    }

    public String getProperty_quote ()
    {
        return property_quote;
    }

    public void setProperty_quote (String property_quote)
    {
        this.property_quote = property_quote;
    }

    public CancellationItems[] getCancellationItems ()
    {
        return cancellationItems;
    }

    public void setCancellationItems (CancellationItems[] cancellationItems)
    {
        this.cancellationItems = cancellationItems;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [property_currency = "+property_currency+", propertyManagerSupportCC = "+propertyManagerSupportCC+", propertyName = "+propertyName+", paymentSupported = "+paymentSupported+", imageUrl = "+imageUrl+", deposit = "+deposit+", agentCommission = "+agentCommission+", termsLink = "+termsLink+", fromTime = "+fromTime+", available = "+available+", agentCommissionValue = "+agentCommissionValue+", minstay = "+minstay+", toTime = "+toTime+", is_error = "+is_error+", secondPaymentDate = "+secondPaymentDate+", currency = "+currency+", message = "+message+", messageCode = "+messageCode+", price = "+price+", quote_details = "+quote_details+", firstPayment = "+firstPayment+", quote = "+quote+", secondPayment = "+secondPayment+", property_quote = "+property_quote+", cancellationItems = "+cancellationItems+"]";
    }
}