package rest.client.mybookingpal.json.prices;

public class PricesResponse
{
    private Ranges ranges;

    public Ranges getRanges ()
    {
        return ranges;
    }

    public void setRanges (Ranges ranges)
    {
        this.ranges = ranges;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ranges = "+ranges+"]";
    }
}