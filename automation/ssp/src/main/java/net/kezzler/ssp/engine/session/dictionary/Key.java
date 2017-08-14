package net.kezzler.ssp.engine.session.dictionary;

/**
 * Enumeration for test session keys.
 */
public enum Key {

    ROOT_ELEMENT_BEFORE_REFRESH("PAGE_ROOT_ELEMENT"), //
    BROWSER_FIRST_OPEN("BROWSER_FIRST_OPEN"), //
    USER("USER"), //
    DESCRIPTION("DESCRIPTION"), //
    PRODUCT_LIST("PRODUCT_LIST"), //
    SCHEMA_NAME("SCHEMA_NAME"), //
    ABSOLUTE_FILEPATH("ABSOLUTE_FILEPATH"), //
    ORDER_LIST("ORDER_LIST");
    private final String text;

    Key(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
