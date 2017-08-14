package domain.parameters;

/**
 * Created by Someone on 29.01.2017.
 */
public class BookingSearchParameters extends ParameterObj {
    private String location;
    private String dayFrom;
    private String daysToStay;
    private String dayTo;
    private String numberOfGuests;
    private String currency;

    public String getDaysToStay() {
        return daysToStay;
    }

    public String getLocation() {
        return location;
    }

    public String getDayFrom() {
        return dayFrom;
    }

    public String getDayTo() {
        return dayTo;
    }

    public String getNumberOfGuests() {
        return numberOfGuests;
    }

    public String getCurrency() {
        return currency;
    }
}
