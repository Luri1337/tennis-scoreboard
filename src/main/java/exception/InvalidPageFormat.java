package exception;

public class InvalidPageFormat extends RuntimeException {
    public InvalidPageFormat(String message) {
        super(message);
    }
}
