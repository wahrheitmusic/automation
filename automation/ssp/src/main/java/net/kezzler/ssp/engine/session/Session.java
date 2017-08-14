package net.kezzler.ssp.engine.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.kezzler.ssp.domain.Order;
import net.kezzler.ssp.domain.Product;
import net.kezzler.ssp.domain.User;
import net.kezzler.ssp.engine.session.dictionary.Key;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.SessionMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

/**
 * Thread-safe static storage (Thucydides stores local session for each test-thread)
 */
public final class Session {

    private static final Logger LOG = LoggerFactory.getLogger(Session.class.getSimpleName());
    private static final String FAILED_TO_STRING_INVOCATION = "Failed toString() invocation on an object";

    private Session() {
    }

    /**
     * Method will warn if multiple keys equal by ignoreCase exist in Session
     */
    public static Object get(final Key key) {
        String byKey = key.toString();
        SessionMap<Object, Object> session = Serenity.getCurrentSession();
        Object exact = session.get(byKey);
        String warnMsg;

        if (exact == null) {
            warnMsg = format("Object was not matched by exact key [%s], and", byKey);
        } else {
            warnMsg = format("Object was matched by exact key [%s], but", byKey);
        }

        String anotherKey;
        for (Map.Entry<Object, Object> mapEntry : session.entrySet()) {
            anotherKey = (String) mapEntry.getKey();
            if (anotherKey.equalsIgnoreCase(byKey) && !anotherKey.equals(byKey)) {
                LOG.warn(warnMsg + " similar key exist in Session [{}] \nObject by this key: {}", mapEntry.getKey(),
                        mapEntry.getValue());
            }
        }
        LOG.info(String.format("Get object by key: %s, object: %s", byKey, exact));
        return exact;
    }

    public static String getS(final Key key) {
        Object value = get(key);
        return value == null ? null : value.toString();
    }

    public static <T extends Object> T get(final Key key, final Class<T> type) {
        Object object = get(key);
        return (object == null) ? null : (T) object;
    }

    public static void put(final Key key, final Object value) {
        LOG.info(String.format("Put object by key: %s, object: %s", key, value));
        Serenity.getCurrentSession().put(key.toString(), value);
    }

    public static void remove(final Key key) {
        Serenity.getCurrentSession().remove(key.toString());
    }

    public static boolean containsKey(final Key key) {
        return Serenity.getCurrentSession().containsKey(key.toString());
    }

    public static User getDefaultUser() {
        if (!Session.containsKey(Key.USER)) {
            Session.put(Key.USER, User.getNewDefaultUser());
        }
        return Session.get(Key.USER, User.class);
    }

    public static List<Order> getOrderList() {
        return Session.get(Key.ORDER_LIST, List.class);
    }

    public static List<Product> getProductList() {
        return Session.get(Key.PRODUCT_LIST, List.class);
    }

    public static void addOrderToList(final Order order) {
        if (!Session.containsKey(Key.ORDER_LIST)) {
            Session.put(Key.ORDER_LIST, new ArrayList<Order>());
        }
        Session.get(Key.ORDER_LIST, List.class).add(order);
    }

    public static void addProductToList(final Product product) {
        if (!Session.containsKey(Key.PRODUCT_LIST)) {
            Session.put(Key.PRODUCT_LIST, new ArrayList<Product>());
        }
        Session.get(Key.PRODUCT_LIST, List.class).add(product);
    }
}
