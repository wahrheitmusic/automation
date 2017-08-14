package net.kezzler.ssp.engine.httpclient;

import java.io.IOException;

import net.kezzler.ssp.utils.exception.HttpClientException;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class HttpResponseWrapper {

    private final int statusCode;
    private String body;
    private String bodyEncoded;
    private HttpResponse rawResponse;

    public HttpResponseWrapper(final HttpResponse httpResponse) {
        rawResponse = httpResponse;
        statusCode = httpResponse.getStatusLine().getStatusCode();
        try {
            bodyEncoded = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            body = new String(bodyEncoded.getBytes("UTF-8"));

        } catch (IOException e) {
            throw new HttpClientException(e);
        }
    }

    public String getBody() {
        return body;
    }

    public String getBodyEncoded() {
        return bodyEncoded;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public HttpResponse getRawResponse() {
        return rawResponse;
    }
}
