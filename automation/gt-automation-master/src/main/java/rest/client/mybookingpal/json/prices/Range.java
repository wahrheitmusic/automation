package rest.client.mybookingpal.json.prices;

public class Range
{
    private String startDate;

    private String avgPrice;

    private String maxPrice;

    private String minstay;

    private String week;

    private String minPrice;

    public String getStartDate ()
    {
        return startDate;
    }

    public void setStartDate (String startDate)
    {
        this.startDate = startDate;
    }

    public String getAvgPrice ()
    {
        return avgPrice;
    }

    public void setAvgPrice (String avgPrice)
    {
        this.avgPrice = avgPrice;
    }

    public String getMaxPrice ()
    {
        return maxPrice;
    }

    public void setMaxPrice (String maxPrice)
    {
        this.maxPrice = maxPrice;
    }

    public String getMinstay ()
    {
        return minstay;
    }

    public void setMinstay (String minstay)
    {
        this.minstay = minstay;
    }

    public String getWeek ()
    {
        return week;
    }

    public void setWeek (String week)
    {
        this.week = week;
    }

    public String getMinPrice ()
    {
        return minPrice;
    }

    public void setMinPrice (String minPrice)
    {
        this.minPrice = minPrice;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [startDate = "+startDate+", avgPrice = "+avgPrice+", maxPrice = "+maxPrice+", minstay = "+minstay+", week = "+week+", minPrice = "+minPrice+"]";
    }
}