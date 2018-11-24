package ClientServerPattern.Vehicle;

/**
 * Exception for detecting empty vehicles
 */
public class EmptyVehicleException extends Exception {
    public EmptyVehicleException() {
        super();
    }
    public EmptyVehicleException(String message) {
        super(message);
    }
    public EmptyVehicleException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmptyVehicleException(Throwable cause) {
        super(cause);
    }
}
