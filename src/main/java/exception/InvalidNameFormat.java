package exception;

public class InvalidNameFormat extends RuntimeException {
    public InvalidNameFormat(String message) {
        super(message);
    }
}
