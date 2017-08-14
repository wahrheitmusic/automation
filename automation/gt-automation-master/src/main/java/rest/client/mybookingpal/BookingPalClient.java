package rest.client.mybookingpal;

import org.apache.http.client.utils.URIBuilder;

import core.rest.HttpRequest;
import core.rest.HttpResponseWrapper;
import rest.CommonClient;

/**
 * Created by Someone on 29.01.2017.
 */
public class BookingPalClient extends CommonClient {

    private String scheme = "https";
    private String host = "www.mybookingpal.com";

    public HttpResponseWrapper getResultsFor(final String product, final String fromDate, final String toDate,
            final String numberOfGuests, final String currency) {
        String uri = uriBuilder()//
                .setPath(buildPath("/xml/services/json/reservation/products", product, fromDate, toDate))//
                .setParameter("pos", "a502d2c65c2f75d3")//
                .setParameter("guests", numberOfGuests)//
                .setParameter("amenity", "true")//
                .setParameter("currency", currency)//
                .setParameter("exec_match", "false")//
                .setParameter("display_inquire_only", "false")//
                .toString();

        HttpRequest rq = HttpRequest.get(uri);
        return rq.sendAndGetResponse();
    }

    public HttpResponseWrapper getPriceFor(final String productId, final String fromDate, final String toDate,
            final String currency) {
        String uri = uriBuilder()//
                .setPath("/xml/services/json/reservation/prices")//
                .setParameter("pos", "a502d2c65c2f75d3")//
                .setParameter("productid", productId)//
                .setParameter("fromdate", fromDate)//
                .setParameter("todate", toDate)//
                .setParameter("currency", currency)//
                .toString();

        HttpRequest rq = HttpRequest.get(uri);
        return rq.sendAndGetResponse();
    }

    public HttpResponseWrapper getQuotesFor(final String productId, final String fromDate, final String toDate,
            final String adults, final String currency) {
        String uri = uriBuilder()//
                .setPath("/xml/services/json/reservation/quotes")//
                .setParameter("pos", "a502d2c65c2f75d3")//
                .setParameter("productid", productId)//
                .setParameter("fromdate", fromDate)//
                .setParameter("todate", toDate)//
                .setParameter("currency", currency)//
                .setParameter("adults", adults)//
                .toString();
        HttpRequest rq = HttpRequest.get(uri);
        return rq.sendAndGetResponse();
    }

    public HttpResponseWrapper getInfo(final String location) {
        String uri = uriBuilder()//
                .setPath("/api/location/getinfo")//
                .setParameter("location", location)//
                .toString();
        HttpRequest rq = HttpRequest.get(uri);
        return rq.sendAndGetResponse();
    }

    @Override
    protected URIBuilder uriBuilder() {
        return new URIBuilder().setScheme(scheme).setHost(host);
    }
}
