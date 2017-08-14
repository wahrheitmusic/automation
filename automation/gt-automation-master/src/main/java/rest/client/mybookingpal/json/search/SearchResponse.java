package rest.client.mybookingpal.json.search;

public class SearchResponse {
    private String messageCode;

    private String message;

    private SearchQuotes search_quotes;

    private String is_error;

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

    public SearchQuotes getSearch_quotes() {
        return search_quotes;
    }

    public void setSearch_quotes(SearchQuotes search_quotes) {
        this.search_quotes = search_quotes;
    }

    public String getIs_error() {
        return is_error;
    }

    public void setIs_error(String is_error) {
        this.is_error = is_error;
    }

    @Override
    public String toString() {
        return "ClassPojo [messageCode = " + messageCode + ", message = " + message + ", search_quotes = "
                + search_quotes + ", is_error = " + is_error + "]";
    }
}