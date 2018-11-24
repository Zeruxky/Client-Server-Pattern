package ClientServerPattern.Vehicle;

public class Car extends Vehicle {
    public Car(String equipmentVersion, String catalogPrice) throws EmptyVehicleException {
        super(equipmentVersion, catalogPrice);
    }
}
