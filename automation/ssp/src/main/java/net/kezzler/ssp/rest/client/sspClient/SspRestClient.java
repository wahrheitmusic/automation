package net.kezzler.ssp.rest.client.sspClient;

import net.kezzler.ssp.engine.httpclient.HttpRequest;
import net.kezzler.ssp.engine.httpclient.HttpResponseWrapper;
import net.kezzler.ssp.rest.model.product.SendProductJson;
import net.kezzler.ssp.rest.parser.AbstractParser;

public class SspRestClient {

    private final static String host = System.getProperty("rest.base.secure");

    public HttpResponseWrapper sendGetAuthStatus(final String userName, final String password) {
        return HttpRequest
                .get(String.format(
                        "%s/ssp/security/authstatus?_CSRF_PROTECTION_ACTIVE=1&_spring_security_remember_me=no", host))
                .addBasicAuth(userName, password).addAccept(HttpRequest.ContentType.APPLICATION_JSON.toString())
                .sendAndGetResponse(200);
    }

    public HttpResponseWrapper sendGetOrders() {
        return HttpRequest.get(String.format("%s/ssp/kcengine-ws-rest/orders?max=15&offset=0", host))
                .addAccept(HttpRequest.ContentType.APPLICATION_JSON.toString()).sendAndGetResponse(200);
    }

    public HttpResponseWrapper sendPostOrders(final String description) {
        return HttpRequest.post(String.format("%s/ssp/kcengine-ws-rest/orders", host))
                .addBody("{\"description\":\"" + description + "\"}")
                .addAccept(HttpRequest.ContentType.APPLICATION_JSON.toString()).sendAndGetResponse(200);
    }

    public HttpResponseWrapper sendPostProducts(final SendProductJson productJson) {
        return HttpRequest.post(String.format("%s/ssp/kcengine-ws-rest/products", host))
                .addBody(AbstractParser.toJson(productJson, SendProductJson.class))
                .addAccept(HttpRequest.ContentType.APPLICATION_JSON.toString()).sendAndGetResponse(200);
    }

    public HttpResponseWrapper sendGetProducts() {
        return HttpRequest.get(String.format("%s/ssp/masterdata/v1/products?page=0&size=10", host))
                .addAccept(HttpRequest.ContentType.APPLICATION_JSON.toString()).sendAndGetResponse(200);
    }

    public HttpResponseWrapper sendPostMetadaSchema(final String name, final String description) {
        return HttpRequest.post(String.format("%s/ssp/kcengine-ws-rest/metadataschema/%s", host, name))
                .addBody(description).addAccept(HttpRequest.ContentType.APPLICATION_JSON.toString())
                .sendAndGetResponse(200);
    }
}
