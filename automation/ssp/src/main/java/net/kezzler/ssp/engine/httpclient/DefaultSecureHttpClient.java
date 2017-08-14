package net.kezzler.ssp.engine.httpclient;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public final class DefaultSecureHttpClient {
    private static final org.jruby.util.log.Logger LOG = org.jruby.util.log.LoggerFactory
            .getLogger(DefaultSecureHttpClient.class.getSimpleName());
    private static HttpContext localContext = new BasicHttpContext();
    private static HttpClient secureHttpClient;
    private static CookieStore httpCookieStore = new BasicCookieStore();
    private static int timeout = Integer.parseInt(System.getProperty("default.http.timeout", "300000"));

    public static HttpContext getLocalContext() {
        return localContext;
    }

    public static HttpClient getSecureClient() {
        if (secureHttpClient == null) {
            createDefaultSSLClient();
            LOG.info("Secure client was initialized successfully");
            localContext.setAttribute(HttpClientContext.COOKIE_STORE, httpCookieStore);
        }
        return secureHttpClient;
    }

    private static synchronized void createDefaultSSLClient() {
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLSv1.2");
            TrustManager trustManager = new CustomX509TrustManager();
            sslContext.init(null, new TrustManager[] { trustManager }, null);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Cannot initialize client!", e);
        }
        SSLConnectionSocketFactory secureSocketFactory = new SSLConnectionSocketFactory(sslContext,
                new NoopHostnameVerifier());
        RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout).build();
        HttpClientBuilder builder = HttpClientBuilder.create().setDefaultRequestConfig(config)
                .setDefaultCookieStore(httpCookieStore);

        builder.setSSLSocketFactory(secureSocketFactory);
        secureHttpClient = builder.build();
    }

    public static void clearCookies() {
        httpCookieStore.clear();
    }

    public static void addCookie(final Cookie cookie) {
        httpCookieStore.addCookie(cookie);
    }

    public static List<Cookie> getCookies() {
        return httpCookieStore.getCookies();
    }

    public static class CustomX509TrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(final X509Certificate[] x509Certificates, final String s)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(final X509Certificate[] x509Certificates, final String s)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}