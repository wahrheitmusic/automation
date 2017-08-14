package rest.client.mybookingpal.json.search;

import java.util.List;

public class SearchQuotes {
    private String id;

    private String xsl;

    private String quotes_per_page;

    private List<Quote> quote;

    private String page_number;

    private String quotes_count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXsl() {
        return xsl;
    }

    public void setXsl(String xsl) {
        this.xsl = xsl;
    }

    public String getQuotes_per_page() {
        return quotes_per_page;
    }

    public void setQuotes_per_page(String quotes_per_page) {
        this.quotes_per_page = quotes_per_page;
    }

    public List<Quote> getQuote() {
        return quote;
    }

    public void setQuote(List<Quote> quote) {
        this.quote = quote;
    }

    public String getPage_number() {
        return page_number;
    }

    public void setPage_number(String page_number) {
        this.page_number = page_number;
    }

    public String getQuotes_count() {
        return quotes_count;
    }

    public void setQuotes_count(String quotes_count) {
        this.quotes_count = quotes_count;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", xsl = " + xsl + ", quotes_per_page = " + quotes_per_page + ", quote = "
                + quote + ", page_number = " + page_number + ", quotes_count = " + quotes_count + "]";
    }
}