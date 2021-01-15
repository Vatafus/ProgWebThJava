package finalproject.jw.exception;

public class NotAdminException extends Exception{
    private static final long serialVersionUID = 1L;

    public NotAdminException() {
        super();
    }
    public NotAdminException(String message) {
        super(message);
    }
    public NotAdminException(Throwable cause) {
        super(cause);
    }
}
