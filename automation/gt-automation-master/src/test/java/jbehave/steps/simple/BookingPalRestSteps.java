package jbehave.steps.simple;

import core.rest.HttpResponseWrapper;
import rest.client.mybookingpal.BookingPalClient;
import rest.client.mybookingpal.json.getinfo.GetInfoResponse;
import rest.client.mybookingpal.json.prices.PricesResponse;
import rest.client.mybookingpal.json.quotes.QuotesResponse;
import rest.client.mybookingpal.json.search.SearchProductsResponse;
import rest.parser.Parser;

/**
 * Created by Someone on 29.01.2017.
 */
public class BookingPalRestSteps {
    BookingPalClient bookingPalClient = new BookingPalClient();

    public SearchProductsResponse getResultsFor(final String productId, final String dateFrom, final String dateTo,
            final String numberOfGuests, final String currency) {
        HttpResponseWrapper httpResponseWrapper = bookingPalClient.getResultsFor(productId, dateFrom, dateTo,
                numberOfGuests, currency);
        return Parser.fromJson(httpResponseWrapper.getBody(), SearchProductsResponse.class);
    }

    public PricesResponse getPrices(final String productId, final String fromDate, final String toDate,
            final String currency) {
        HttpResponseWrapper httpResponseWrapper = bookingPalClient.getPriceFor(productId, fromDate, toDate, currency);
        return Parser.fromJson(httpResponseWrapper.getBody(), PricesResponse.class);
    }

    public QuotesResponse getQuotes(final String productId, final String fromDate, final String toDate,
            final String currency, final String adults) {
        HttpResponseWrapper httpResponseWrapper = bookingPalClient.getQuotesFor(productId, fromDate, toDate, currency, adults);
        return Parser.fromJson(httpResponseWrapper.getBody(), QuotesResponse.class);
    }

    public GetInfoResponse getInfo(final String location) {
        HttpResponseWrapper httpResponseWrapper = bookingPalClient.getInfo(location);
        return Parser.fromJson(httpResponseWrapper.getBody(), GetInfoResponse.class);
    }

}