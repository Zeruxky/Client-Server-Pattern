package ClientServerPattern.Server;

import ClientServerPattern.Vehicle.Vehicle;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

/**
 * ClientServerPattern.Client.Server for motorcycles.
 * @author philip
 * @version 1.0
 */
public class ServerMotorcycleSupplier extends AbstractServer {
    private boolean isServerSet;
    private ServerMotorcycleSupplier() {
        if(!this.isServerSet()) {
            try {
                final int serverPortNumber = 4445;
                ServerSocket serverSocket = new ServerSocket(serverPortNumber);
                this.setServerSocket(serverSocket);
                this.setServerPortNumber(serverPortNumber);
                this.setServerSet(true);
            } catch (IOException e) {
                System.err.println("IOError...");
                this.setServerSocket(null);
                this.setServerPortNumber(-1);
            }
        }
    }

    @Override
    public boolean isServerSet() {
        return isServerSet;
    }

    @Override
    public void setServerSet(boolean serverSet) {
        isServerSet = serverSet;
    }

    private static class ServerMotorcycleSupplierHolder {
        private static final ServerMotorcycleSupplier INSTANCE = new ServerMotorcycleSupplier();
    }

    /**
     * Singleton pattern. Only one instance is allowed.
     * @return instance of server motorcycle supplier
     */
    public static ServerMotorcycleSupplier getInstance() {
        return ServerMotorcycleSupplier.ServerMotorcycleSupplierHolder.INSTANCE;
    }

    /**
     * Get the discount depending on equipment version.
     * @param vehicle: vehicle you want to get the discounted price
     * @return discount of vehicle
     */
    @Override
    public int getDiscount(Vehicle vehicle) {
        String equipmentVersion = vehicle.getEquipmentVersion();
        int discount;
        if(equipmentVersion.equals("A") || equipmentVersion.equals("C")) {
            discount = 30;
        } else {
            Random random = new Random();
            discount = random.nextInt(10) + 5;
        }
        return discount;
    }
}
