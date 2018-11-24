package ClientServerPattern.Client;

import ClientServerPattern.ProtocolInterface;
import ClientServerPattern.Vehicle.*;

import java.io.*;
import java.net.Socket;

/**
 * The client program for the car dealer.
 * @author philip
 * @version 1.0
 */
public class ClientCarDealer implements Serializable, ProtocolInterface<Vehicle, Integer> {
    private ClientCarDealer() {}

    private static class ClientCarDealerHolder {
        private static final ClientCarDealer INSTANCE = new ClientCarDealer();
    }

    /**
     * Singleton pattern. Only one instance is allowed.
     * @return instance of client program
     */
    public static ClientCarDealer getInstance() {
        return ClientCarDealer.ClientCarDealerHolder.INSTANCE;
    }

    /**
     * Write to server. Just sending a vehicle.
     * @param message: The vehicle you want to send
     * @param targetObject: The server you want to contact
     */
    @Override
    public void writeMessage(Socket targetObject, Vehicle message) {
        try {
            System.out.println("Writing to server...");
            ObjectOutputStream outputStream = new ObjectOutputStream(new PrintStream(targetObject.getOutputStream()));
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            System.err.println("Error at sending to server");
        }
    }

    /**
     * Read from server. It get a string that contain the discounted price.
     * @param targetObject: server you want to contact
     * @return discounted price
     */
    @Override
    public Integer readMessage(Socket targetObject) {
        try {
            System.out.println("Reading from server...");
            ObjectInputStream objectInputStream = new ObjectInputStream(targetObject.getInputStream());
            String serverResponse = objectInputStream.readUTF();
            return Integer.parseInt(serverResponse);
        } catch (IOException e) {
            System.err.println("Error at connecting to server");
            return 0;
        }
    }

    /**
     * Connecting to a server. The server must run to this point!
     * @param portNumber: the server you want to connect. Must be a concrete implementation of AbstractServer
     * @param vehicle: vehicle you want to send
     */
    public void connectingToServer(int portNumber, Vehicle vehicle) {
        System.out.println("Trying to connect with server...");
        Socket server;
        try {
            server = new Socket("localhost", portNumber); //create the connection to server
            System.out.println("Found a server");
            this.writeMessage(server, vehicle);
            int discountedPriceString = this.readMessage(server);
            System.out.println("Discounted price: " + discountedPriceString);
            server.close(); //close the connection to server
        } catch (IOException e) {
            System.err.println("Error at connecting to server");
        }
        System.out.println("Connection terminated");
    }

    /**
     * Creating a new vehicle from the user input.
     * @param vehicleType: type of vehicle
     * @param equipmentVersion: equipment version
     * @param catalogPrice: catalog price
     * @return Vehicle
     * @throws EmptyVehicleException: empty input given
     * @throws WrongVehicleTypeException: no vehicle found for the given input
     */
    public Vehicle createVehicleFromUserInput(String vehicleType, String equipmentVersion, String catalogPrice)
            throws EmptyVehicleException, WrongVehicleTypeException {
        if(vehicleType.toUpperCase().equals("CAR")) {
            return new Car(equipmentVersion, catalogPrice);
        } else if(vehicleType.toUpperCase().equals("MOTORCYCLE")) {
            return new Motorcycle(equipmentVersion, catalogPrice);
        } else {
            throw new WrongVehicleTypeException("No supported vehicle given!");
        }
    }
}
