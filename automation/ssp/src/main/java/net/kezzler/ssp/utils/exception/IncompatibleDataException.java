package net.kezzler.ssp.utils.exception;

public class IncompatibleDataException extends ExtendedException {
    public IncompatibleDataException() {

    }

    public IncompatibleDataException(String message) {
        super(message);

    }

    public IncompatibleDataException(Throwable cause) {
        super(cause);

    }

    public IncompatibleDataException(String message, Throwable cause) {
        super(message, cause);

    }

    public IncompatibleDataException(IncompatibleDataError error) {
        super(error.toString());

    }

    public IncompatibleDataException(String msg, String... args) {
        super(String.format(msg, args));
    }

    public enum IncompatibleDataError {
        INCORRECT_PARAMETER("Proper parameter should be used for this step");
        private final String message;

        IncompatibleDataError(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }
}
