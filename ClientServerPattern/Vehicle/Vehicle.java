package ClientServerPattern.Vehicle;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private String equipmentVersion;
    private String catalogPrice;

    public String getEquipmentVersion() {
        return equipmentVersion;
    }

    private void setEquipmentVersion(String equipmentVersion) {
        this.equipmentVersion = equipmentVersion;
    }

    public String getCatalogPrice() {
        return catalogPrice;
    }

    private void setCatalogPrice(String catalogPrice) {
        this.catalogPrice = catalogPrice;
    }

    /**
     * Constructor for creating new vehicle object.
     * @param equipmentVersion: the equipment version for the given vehicle
     * @param catalogPrice: the price for the car
     * @throws EmptyVehicleException: throws if the input is incorrect
     */
    public Vehicle(String equipmentVersion, String catalogPrice) throws EmptyVehicleException {
        if(checkInputStrings(equipmentVersion) && checkInputStrings(catalogPrice)) {
            this.setEquipmentVersion(equipmentVersion);
            this.setCatalogPrice(catalogPrice);
        } else {
            this.setEquipmentVersion(null);
            this.setCatalogPrice(null);
            throw new EmptyVehicleException("No vehicle given!");
        }
    }

    /**
     * Checks if the input is empty or null
     * @param input: input you want to check
     * @return
     * <ul>
     *     <li>True: input okay</li>
     *     <li>False: input incorrect</li>
     * </ul>
     */
    private boolean checkInputStrings(String input) {
        return input != null && !input.isEmpty();
    }
}
