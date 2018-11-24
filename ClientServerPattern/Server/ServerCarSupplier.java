package ClientServerPattern.Server;

import ClientServerPattern.Vehicle.Vehicle;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

/**
 * ClientServerPattern.Client.Server for cars
 * @author philip
 * @version 1.0
 */
public class ServerCarSupplier extends AbstractServer {
    private boolean isServerSet;

    private ServerCarSupplier() {
        if(!this.isServerSet()) {
            try {
                int serverPortNumber = 4444;
                ServerSocket serverSocket = new ServerSocket(serverPortNumber); //create new server socket object
                this.setServerSocket(serverSocket); //save it at server car supplier object
                this.setServerPortNumber(serverPortNumber); //save the port number
                this.setServerSet(true); //for avoiding other instances of car server supplier
            } catch (IOException e) { //if an error occurs at creating server --> port is already used
                System.err.println("Error at creating server socket");
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

    private static class ServerCarSupplierHolder {
        private static final ServerCarSupplier INSTANCE = new ServerCarSupplier();
    }

    /**
     * Singleton pattern. Only one instance is allowed.
     * @return instance of server car supplier
     */
    public static ServerCarSupplier getInstance() {
        return ServerCarSupplierHolder.INSTANCE;
    }

    @Override
    public int getDiscount(Vehicle vehicle) {
        String equipmentVersion = vehicle.getEquipmentVersion();
        int discount;
        if(equipmentVersion.equals("A") || equipmentVersion.equals("B")) {
            Random random = new Random();
            discount = random.nextInt(20) + 10;
        } else {
            discount = 5;
        }
        return discount;
    }
}
