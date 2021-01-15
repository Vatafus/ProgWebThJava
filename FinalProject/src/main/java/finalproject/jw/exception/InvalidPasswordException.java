package finalproject.jw.exception;

public class InvalidPasswordException extends Exception{

    private static final long serialVersionUID = 1L;
    public InvalidPasswordException() {
        super();
    }

    public InvalidPasswordException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(Throwable cause) {
        super(cause);
    }
}
