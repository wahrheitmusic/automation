package net.kezzler.ssp.engine.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import net.kezzler.ssp.utils.exception.HttpClientException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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

public class HttpRequest {

    private static final Logger LOG = LoggerFactory.getLogger("HttpClient");
    private static final String DEFAULT_ENCODING = "UTF-8";
    private boolean withAuth;
    private String login;
    private String password;
    private HttpRequestBase rawRequest;
    private boolean logResponseBody = true;

    HttpRequest(final String url, final HttpMethod method) {
        if (method.equals(HttpMethod.GET)) {
            rawRequest = new HttpGet(url);
        } else if (method.equals(HttpMethod.PUT)) {
            rawRequest = new HttpPut(url);
        } else if (method.equals(HttpMethod.POST)) {
            rawRequest = new HttpPost(url);
        } else {
            throw new HttpClientException(method.toString() + " is unsupported for now!");
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
                throw new HttpClientException("Exception during assign body to request", e);
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
        return sendAndGetResponse(-1);
    }

    public HttpResponseWrapper sendAndGetResponse(final int expectedResponseCode) {
        HttpResponseWrapper response = null;
        try {
            // rawRequest.addHeader("User-Agent", "autotests");
            logRequest(rawRequest);
            HttpResponse httpResponse = HttpClientFactory.getDefaultSecureClient().execute(rawRequest,
                    DefaultSecureHttpClient.getLocalContext());
            response = new HttpResponseWrapper(httpResponse);
            logResponse(response);

            if (expectedResponseCode != -1) {
                int actualCode = response.getStatusCode();
                new HttpClientException(expectedResponseCode, response.getStatusCode(), response.getBody())
                        .throwIf(actualCode != expectedResponseCode);
            }

        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new HttpClientException(e.getMessage());
        }

        return response;
    }

    public void sendWithoutResponse(final int expectedResponseCode) {
        try {
            logRequest(rawRequest);
            HttpResponse httpResponse = HttpClientFactory.getDefaultSecureClient().execute(rawRequest,
                    DefaultSecureHttpClient.getLocalContext());
            int actualCode = httpResponse.getStatusLine().getStatusCode();
            new HttpClientException(expectedResponseCode, actualCode, httpResponse.getStatusLine().getReasonPhrase())
                    .throwIf(actualCode != expectedResponseCode);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new HttpClientException(e.getMessage());
        }
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

        List<Cookie> cookies = DefaultSecureHttpClient.getCookies();
        if (!cookies.isEmpty()) {
            responseDescription.append("Cookies:\n");
            for (int i = 0; i < cookies.size(); i++) {
                responseDescription.append("Cookie: " + cookies.get(i) + "\n");
            }
        }

        LOG.info(responseDescription.toString());

    }

    public enum HttpMethod {
        GET, PUT, POST
    }

    public enum ContentType {
        APPLICATION_JSON("application/json;charset=utf-8");

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
