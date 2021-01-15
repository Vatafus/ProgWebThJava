package finalproject.jw.exception;

public class NotLoggedInException extends Exception {

    public NotLoggedInException(String message) {
        super(message);

    }

    public NotLoggedInException(Throwable cause) {
        super(cause);

    }
}
