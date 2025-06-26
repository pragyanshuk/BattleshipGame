package exceptions;

public class ShipOverlapException extends RuntimeException {
    public ShipOverlapException(String message) {
        super(message);
    }
}
