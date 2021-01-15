package finalproject.jw.exception;

public class SellerException extends Exception {
    private static final long serialVersionUID = 1L;

    public SellerException() {
        super();
    }

    public SellerException(String message) {
        super(message);
    }

    public SellerException(Throwable cause) {
        super(cause);
    }
}
