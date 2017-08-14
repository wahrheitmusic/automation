package rest.client.mybookingpal.json.quotes;

public class CancellationItems
{
    private String cancellationDate;

    private String cancellationType;

    private String daysBeforeArrival;

    private String transactionFee;

    private String cancellationAmount;

    private String cancellationNights;

    private String cancellationPercentage;

    private String currency;

    public String getCancellationDate ()
    {
        return cancellationDate;
    }

    public void setCancellationDate (String cancellationDate)
    {
        this.cancellationDate = cancellationDate;
    }

    public String getCancellationType ()
    {
        return cancellationType;
    }

    public void setCancellationType (String cancellationType)
    {
        this.cancellationType = cancellationType;
    }

    public String getDaysBeforeArrival ()
    {
        return daysBeforeArrival;
    }

    public void setDaysBeforeArrival (String daysBeforeArrival)
    {
        this.daysBeforeArrival = daysBeforeArrival;
    }

    public String getTransactionFee ()
    {
        return transactionFee;
    }

    public void setTransactionFee (String transactionFee)
    {
        this.transactionFee = transactionFee;
    }

    public String getCancellationAmount ()
    {
        return cancellationAmount;
    }

    public void setCancellationAmount (String cancellationAmount)
    {
        this.cancellationAmount = cancellationAmount;
    }

    public String getCancellationNights ()
    {
        return cancellationNights;
    }

    public void setCancellationNights (String cancellationNights)
    {
        this.cancellationNights = cancellationNights;
    }

    public String getCancellationPercentage ()
    {
        return cancellationPercentage;
    }

    public void setCancellationPercentage (String cancellationPercentage)
    {
        this.cancellationPercentage = cancellationPercentage;
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
        return "ClassPojo [cancellationDate = "+cancellationDate+", cancellationType = "+cancellationType+", daysBeforeArrival = "+daysBeforeArrival+", transactionFee = "+transactionFee+", cancellationAmount = "+cancellationAmount+", cancellationNights = "+cancellationNights+", cancellationPercentage = "+cancellationPercentage+", currency = "+currency+"]";
    }
}