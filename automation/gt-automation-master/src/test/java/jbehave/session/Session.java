package jbehave.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.sessionobj.Order;

/**
 * Created by Someone on 29.01.2017.
 */
public final class Session {

    private static Logger LOG = LoggerFactory.getLogger(Session.class.getSimpleName());

    private static final ThreadLocal<TestSession> storage = new ThreadLocal();

    private Session() {
    }

    public static void initNewSession() {
        storage.set(new TestSession());
    }

    public static void resetSession() {
        storage.remove();
        storage.set(new TestSession());
    }

    /**
     * Method will warn if multiple keys equal by ignoreCase exist in Session
     */
    public static Object get(final Key key) {
        String byKey = key.toString();
        TestSession session = storage.get();
        Object value = session.get(byKey);
        LOG.info("Session. GET. Key: {}. Value: {}", byKey, value);
        return value;
    }

    public static void put(final Key key, final Object value) {
        LOG.info("Session. PUT. Key: {}. Value: {}", key, value);
        storage.get().put(key.toString(), value);
    }

    public static <T> T getAs(final Key key, final Class<T> asType) {
        return asType.cast(get(key));
    }

    public static void putOrder(final Order order) {
        put(Key.ORDER, order);
    }

    public static Order getOrder() {
        return getAs(Key.ORDER, Order.class);
    }
}