package domain.sessionobj;

import rest.client.mybookingpal.json.prices.PricesResponse;
import rest.client.mybookingpal.json.quotes.QuotesResponse;
import rest.client.mybookingpal.json.search.Quote;
import rest.client.mybookingpal.json.search.SearchProductsResponse;

/**
 * Created by Someone on 29.01.2017.
 */
public class Order {
    private String locationId;
    private String productName;
    private String productId;
    private String dateFrom;
    private String currency;
    private SearchProductsResponse searchProductsResponse;
    private Quote quote;
    private PricesResponse pricesResponce;
    private QuotesResponse quotes;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(String numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    private String numberOfGuests;

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    private String dateTo;

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationId() {

        return locationId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {

        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public SearchProductsResponse getSearchProductsResponse() {
        return searchProductsResponse;
    }

    public void setSearchProductsResponse(SearchProductsResponse searchProductsResponse) {
        this.searchProductsResponse = searchProductsResponse;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public void setPricesResponce(PricesResponse pricesResponce) {
        this.pricesResponce = pricesResponce;
    }

    public PricesResponse getPricesResponce() {
        return pricesResponce;
    }

    public QuotesResponse getQuotesResponse() {
        return quotes;
    }

    public void setQuotesResponse(QuotesResponse quotes) {
        this.quotes = quotes;
    }
}
