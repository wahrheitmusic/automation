package jbehave.steps.scenario;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import domain.parameters.BookingSearchParameters;
import domain.sessionobj.Order;
import jbehave.session.Session;
import jbehave.steps.simple.BookingPalRestSteps;
import rest.client.mybookingpal.json.quotes.QuotesResponse;
import rest.client.mybookingpal.json.search.Quote;
import rest.client.mybookingpal.json.search.SearchProductsResponse;

/**
 * Created by Someone on 29.01.2017.
 */
public class BookingPalRestScenarioSteps {
    BookingPalRestSteps bookingPalRestSteps = new BookingPalRestSteps();

    /**
     * Standard date pattern is yyyy-MM-dd, or use day of week to get closest day of week date in standard format
     * 
     * @param bsp
     *            search parameters {@see BookingSearchParameters}
     */
    @Given("User searches for apartments via WS: $table")
    public void userSearchesForApartments(final BookingSearchParameters bsp) {
        Order order = new Order();
        String locationId = bookingPalRestSteps.getInfo(bsp.getLocation()).getData().getID();
        LocalDateTime from = getClosestDayOfWeekDate(bsp.getDayFrom());
        LocalDateTime to = from.plusDays(Long.parseLong(bsp.getDaysToStay()));
        String dateFrom = formatDate(from);
        String dateTo = formatDate(to);
        SearchProductsResponse searchProductsResponse = bookingPalRestSteps.getResultsFor(locationId, dateFrom, dateTo,
                bsp.getNumberOfGuests(), bsp.getCurrency());
        order.setLocationId(locationId);
        order.setDateFrom(dateFrom);
        order.setDateTo(dateTo);
        order.setNumberOfGuests(bsp.getNumberOfGuests());
        order.setCurrency(bsp.getCurrency());
        order.setSearchProductsResponse(searchProductsResponse);
        Session.putOrder(order);
    }

    @When("User selects '$apartment' apartments from WS search results")
    public void userSelectsApartmentFromResults(final String apartment) {
        Order order = Session.getOrder();
        List<Quote> quotes = order.getSearchProductsResponse().getSearch_response().getSearch_quotes().getQuote();
        Optional<Quote> qouteOpt = quotes.stream().filter(q -> q.getProductname().equals(apartment)).findFirst();
        if (!qouteOpt.isPresent())
            fail("No apartments found in search results for: %s", apartment);
        Quote quote = qouteOpt.get();
        order.setProductId(quote.getProductid());
        order.setQuote(quote);
    }

    @When("User requests quotes for selected apartment")
    public void userRequestsQuotesForApartment() {
        Order order = Session.getOrder();
        QuotesResponse quotes = bookingPalRestSteps.getQuotes(order.getProductId(), order.getDateFrom(),
                order.getDateTo(), order.getNumberOfGuests(), order.getCurrency());
        order.setQuotesResponse(quotes);
    }

    @Then("User checks that price is equal to quotes in Quote Response")
    public void userPerformsCheck() {
        Order order = Session.getOrder();
        QuotesResponse quotesResponse = order.getQuotesResponse();
        assertThat(quotesResponse.getQuotes().getPrice()).as("prices!=quotes")
                .isEqualTo(quotesResponse.getQuotes().getQuote());
    }

    private String formatDate(final LocalDateTime localDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDateTime.format(dtf);
    }

    private LocalDateTime getClosestDayOfWeekDate(final String dof) {
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(dof.toUpperCase());
        LocalDateTime currentDof = LocalDateTime.now();
        return currentDof.getDayOfWeek().equals(dayOfWeek) ? currentDof
                : currentDof.plusDays(7 - dayOfWeek.getValue() + 1);
    }
}
