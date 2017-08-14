package net.kezzler.ssp.engine.httpclient;

import org.apache.http.client.HttpClient;

public abstract class HttpClientFactory {

    private static HttpClient defaultSecureHttpClient;

    /** Main instance for testing */
    public static synchronized HttpClient getDefaultSecureClient() {
        if (defaultSecureHttpClient == null) {
            defaultSecureHttpClient = newSecureInstance();
        }
        return defaultSecureHttpClient;
    }

    public static HttpClient newSecureInstance() {
        return DefaultSecureHttpClient.getSecureClient();
    }

}
