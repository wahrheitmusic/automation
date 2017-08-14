package core.rest;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;


public class HttpClientFactory {
    private static final ThreadLocal<CloseableHttpClient> httpClientForThread = new ThreadLocal<CloseableHttpClient>() {
        protected CloseableHttpClient initialValue() {
            return HttpClientBuilder.create().setDefaultCookieStore(HttpCookieStore.getInstance()).build();
        }
    };

    /** Main instance for testing */
    public static CloseableHttpClient getHttpClient() {
        return httpClientForThread.get();
    }
}
