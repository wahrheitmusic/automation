package net.kezzler.ssp.utils.exception;

public class ItemExistenceException extends ExtendedException {
    public ItemExistenceException() {
        super();

    }

    public ItemExistenceException(String message) {
        super(message);

    }

    public ItemExistenceException(Throwable cause) {
        super(cause);

    }

    public ItemExistenceException(String message, Throwable cause) {
        super(message, cause);

    }

    public ItemExistenceException(String msg, String... args) {
        super(String.format(msg, args));
    }

}