package exception;

public class SelfMatchNotAllowedException extends RuntimeException {
    public SelfMatchNotAllowedException(String message) {
        super(message);
    }
}
