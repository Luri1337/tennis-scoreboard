package exception;

public class InvalidFilterFormat extends RuntimeException {
    public InvalidFilterFormat(String message) {
        super(message);
    }
}
