package exception;

public class InvalidIdFormat extends RuntimeException {
    public InvalidIdFormat(String message) {
        super(message);
    }
}
