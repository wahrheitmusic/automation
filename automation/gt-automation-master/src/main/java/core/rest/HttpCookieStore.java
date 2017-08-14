package core.rest;

import org.apache.http.impl.client.BasicCookieStore;


public class HttpCookieStore extends BasicCookieStore {

    private static ThreadLocal<HttpCookieStore> THREAD_LOCAL_COOKIE_STORE = new ThreadLocal() {
        @Override
        protected HttpCookieStore initialValue() {
            return new HttpCookieStore();
        }
    };

    public static HttpCookieStore getInstance() {
        return THREAD_LOCAL_COOKIE_STORE.get();
    }
}
