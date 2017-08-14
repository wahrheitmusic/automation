package net.kezzler.ssp.utils.exception;

public class HttpClientException extends ExtendedException {
    public HttpClientException() {
        super();

    }

    public HttpClientException(String message) {
        super(message);

    }

    public HttpClientException(Throwable cause) {
        super(cause);

    }

    public HttpClientException(String message, Throwable cause) {
        super(message, cause);

    }

    public HttpClientException(int expectedCode, int errorCode, String errorDescription) {
        super(String.format("Expected status code: %s, but actual is %s. Response error message: [%s]", expectedCode,
                errorCode, errorDescription));

    }

    public HttpClientException(String msg, String... args) {
        super(String.format(msg, args));
    }

}