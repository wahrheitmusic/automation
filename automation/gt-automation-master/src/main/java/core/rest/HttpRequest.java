/*
 * Canadian Tire Corporation, Ltd. Do not reproduce without permission in writing. Copyright (c) 2014 Canadian Tire
 * Corporation, Ltd. All rights reserved.
 */

package core.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

public class HttpRequest {

    private static final Logger LOG = LoggerFactory.getLogger("HttpClient");
    private static final String DEFAULT_ENCODING = "UTF-8";
    private boolean withAuth;
    private String login;
    private String password;
    private HttpRequestBase rawRequest;
    private boolean logResponseBody = true;

    // ***************************************************************************************************************
    HttpRequest(final String url, final HttpMethod method) {
        if (method.equals(HttpMethod.GET)) {
            rawRequest = new HttpGet(url);
        } else if (method.equals(HttpMethod.PUT)) {
            rawRequest = new HttpPut(url);
        } else if (method.equals(HttpMethod.POST)) {
            rawRequest = new HttpPost(url);
        } else if (method.equals(HttpMethod.DELETE)) {
            rawRequest = new HttpDelete(url);
        } else {
            LOG.error(format("%s is unsupported for now!", method.toString()));
        }
    }

    public static HttpRequest get(final String url) {
        return new HttpRequest(url, HttpMethod.GET);
    }

    public static HttpRequest put(final String url) {
        return new HttpRequest(url, HttpMethod.PUT);
    }

    public static HttpRequest post(final String url) {
        return new HttpRequest(url, HttpMethod.POST);

    }

    public static HttpRequest delete(final String url) {
        return new HttpRequest(url, HttpMethod.DELETE);
    }

    private static boolean isBodyApplicableTo(final HttpRequestBase request) {
        return (request.getClass().equals(HttpPut.class) || request.getClass().equals(HttpPatch.class)
                || request.getClass().equals(HttpPost.class));
    }

    public HttpRequest addHeader(final String key, final String value) {
        rawRequest.addHeader(key, value);
        return this;
    }

    public HttpRequest addAccept(final String value) {
        return addHeader("Accept", value);
    }

    public HttpRequest addContentType(final String value) {
        return addHeader("Content-Type", value);
    }

    public HttpRequest addBasicAuth(final String login, final String password) {
        withAuth = true;
        this.login = login;
        this.password = password;
        String encodedAuthorization = "Basic " + Base64.encodeBase64String((login + ":" + password).getBytes());
        addHeader("Authorization", encodedAuthorization);
        return this;
    }

    public HttpRequest addBody(final String body) {
        if (isBodyApplicableTo(rawRequest)) {
            try {
                ((HttpEntityEnclosingRequestBase) rawRequest)
                        .setEntity(new ByteArrayEntity(body.getBytes(DEFAULT_ENCODING)));
            } catch (UnsupportedEncodingException e) {
                LOG.error("Exception during assigning body to request", e);
            }
        } else {
            LOG.error("Cannot assign body to this http method!");
        }
        return this;
    }

    public HttpRequest doNotLogResponseBody() {
        logResponseBody = false;
        return this;
    }

    public HttpResponseWrapper sendAndGetResponse() {
        HttpResponseWrapper response = null;
        try {
            logRequest(rawRequest);
            HttpResponse httpResponse = HttpClientFactory.getHttpClient().execute(rawRequest);
            response = new HttpResponseWrapper(httpResponse);
            logResponse(response);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        return response;
    }

    private void logRequest(final HttpRequestBase rawRequest) throws IOException {
        StringBuilder requestDescription = new StringBuilder("=== REQUEST ===\n");
        requestDescription.append(rawRequest.getRequestLine().toString()).append("\n");
        for (Header header : rawRequest.getAllHeaders()) {
            requestDescription.append(header).append("\n");
        }

        if (withAuth) {
            requestDescription.append("User/password: ").append(login).append("/").append(password).append("\n");
        }

        if (isBodyApplicableTo(rawRequest)) {
            HttpEntity entity = ((HttpEntityEnclosingRequestBase) rawRequest).getEntity();
            if (entity != null) {
                requestDescription.append(EntityUtils.toString(entity));
            }
        }
        requestDescription.append("\n");
        LOG.info(requestDescription.toString());
    }

    private void logResponse(final HttpResponseWrapper response) {
        StringBuilder responseDescription = new StringBuilder("=== RESPONSE ===\n");
        responseDescription.append(response.getRawResponse().getStatusLine().toString()).append("\n");
        for (Header header : response.getRawResponse().getAllHeaders()) {
            responseDescription.append(header).append("\n");
        }

        if (logResponseBody) {
            responseDescription.append(response.getBody()).append("\n");
        } else {
            responseDescription.append("-skip-body-\n");
        }

        List<Cookie> cookies = HttpCookieStore.getInstance().getCookies();
        if (!cookies.isEmpty()) {
            responseDescription.append("Cookies:\n");
            for (int i = 0; i < cookies.size(); i++) {
                responseDescription.append("Cookie: " + cookies.get(i) + "\n");
            }
        }

        LOG.info(responseDescription.toString());
    }

    public enum HttpMethod {
        GET, PUT, POST, DELETE, OPTIONS, HEAD, CONNECT, TRACE;
    }

    public enum ContentType {
        APPLICATION_XML("application/xml"), APPLICATION_JSON("application/json;charset=utf-8"), FORM_DATA(
                "application/x-www-form-urlencoded"), ANY("*/*");

        private String value;

        ContentType(final String value) {

            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
