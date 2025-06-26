package exceptions;

public class ShipBoundaryExceedException extends RuntimeException {
    public ShipBoundaryExceedException(String message) {
        super(message);
    }
}
