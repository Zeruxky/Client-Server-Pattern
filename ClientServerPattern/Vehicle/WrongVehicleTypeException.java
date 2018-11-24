package ClientServerPattern.Vehicle;

/**
 * Exception for detecting a wrong entered vehicle
 */
public class WrongVehicleTypeException extends Exception {
    public WrongVehicleTypeException() {
        super();
    }
    public WrongVehicleTypeException(String message) {
        super(message);
    }
    public WrongVehicleTypeException(String message, Throwable cause) {
        super(message, cause);
    }
    public WrongVehicleTypeException(Throwable cause) {
        super(cause);
    }
}
