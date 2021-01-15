package finalproject.jw.exception;

public class NotLoggedInException extends Exception {

    public NotLoggedInException() {
        super();
    }

    public NotLoggedInException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

    public NotLoggedInException(String message, Throwable cause) {
        super(message, cause);

    }

    public NotLoggedInException(String message) {
        super(message);

    }

    public NotLoggedInException(Throwable cause) {
        super(cause);

    }
}
