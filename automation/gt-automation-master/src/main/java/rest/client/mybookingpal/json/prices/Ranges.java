package rest.client.mybookingpal.json.prices;

import java.util.List;

public class Ranges {
    private String messageCode;

    private String message;

    private String propertyId;

    private List<Range> range;

    private String is_error;

    private String currency;

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public List<Range> getRange() {
        return range;
    }

    public void setRange(List<Range> range) {
        this.range = range;
    }

    public String getIs_error() {
        return is_error;
    }

    public void setIs_error(String is_error) {
        this.is_error = is_error;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "ClassPojo [messageCode = " + messageCode + ", message = " + message + ", propertyId = " + propertyId
                + ", range = " + range + ", is_error = " + is_error + ", currency = " + currency + "]";
    }
}