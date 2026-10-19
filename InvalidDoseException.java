package medicine;

// User-defined checked exception.
public class InvalidDoseException extends Exception {
    public InvalidDoseException(String message) {
        super(message);
    }
}
