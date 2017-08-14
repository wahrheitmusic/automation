package net.kezzler.ssp.rest.parser;

import net.kezzler.ssp.engine.httpclient.HttpResponseWrapper;
import net.kezzler.ssp.rest.model.orders.ReceiveOrdersJson;
import net.kezzler.ssp.rest.model.product.ReceiveProductsJson;

public class SspRestClientParser extends AbstractParser {

    public static ReceiveOrdersJson parseReceiveOrdersJson(final HttpResponseWrapper response) {
        return fromJson(response.getBody(), ReceiveOrdersJson.class);
    }

    public static ReceiveProductsJson parceReceiveProductsJson(final HttpResponseWrapper response) {
        return fromJson(response.getBody(), ReceiveProductsJson.class);
    }
}
