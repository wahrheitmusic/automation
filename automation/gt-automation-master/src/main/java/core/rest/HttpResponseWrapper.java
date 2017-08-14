/*
 * Canadian Tire Corporation, Ltd. Do not reproduce without permission in writing. Copyright (c) 2014 Canadian Tire
 * Corporation, Ltd. All rights reserved.
 */

package core.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not parse response");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not parse response");
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
