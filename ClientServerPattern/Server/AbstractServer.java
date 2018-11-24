package ClientServerPattern.Server;

import ClientServerPattern.ProtocolInterface;
import ClientServerPattern.Vehicle.Vehicle;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Abstract server. Abstract factory pattern.
 * @author philip
 * @version 1.0
 */
public abstract class AbstractServer implements InterfaceServer, Serializable, ProtocolInterface<String, Vehicle> {
    private ServerSocket serverSocket;
    private int serverPortNumber;

    @Override
    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public ServerSocket getServerSocket() {
        return this.serverSocket;
    }

    @Override
    public void setServerPortNumber(int serverPortNumber) {
        this.serverPortNumber = serverPortNumber;
    }

    @Override
    public int getServerPortNumber() {
        return this.serverPortNumber;
    }

    /**
     * Reading from client. The response from client is an object from type 'vehicle'
     * @param targetObject: client that is connected to server
     * @return vehicle: vehicle object
     */
    @Override
    public Vehicle readMessage(Socket targetObject) {
        try {
            System.out.println("Reading from client...");
            ObjectInputStream inputStream = new ObjectInputStream(targetObject.getInputStream());
            return (Vehicle)inputStream.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("No object transmitted");
            return null;
        } catch (IOException e) {
            System.err.println("Error at reading from client");
            return null;
        }
    }

    /**
     * Get the instance of wanted server
     * @param vehicleType: the vehicle type which you want
     * @return serverInstance: the server instance for the vehicle type
     */
    public static AbstractServer getRightServerInstance(String vehicleType) {
        switch (vehicleType.toUpperCase()) {
            case "CAR":
                return ServerCarSupplier.getInstance();
            case "MOTORCYCLE":
                return ServerMotorcycleSupplier.getInstance();
            default:
                return null;
        }
    }

    /**
     * Writing to the client. The server sends a string to the client, that contains the discounted price
     * @param targetObject: client that connected to server
     * @param message: the message you want to send
     */
    @Override
    public void writeMessage(Socket targetObject, String message) {
        try {
            System.out.println("Writing to client...");
            ObjectOutputStream outputStream = new ObjectOutputStream(new PrintStream(targetObject.getOutputStream()));
            outputStream.writeUTF(message);
            outputStream.flush();
        } catch (IOException e) {
            System.err.println("Error at connecting to client");
        }
        System.out.println("Connection terminated.");
    }

    /**
     * Connecting to a client. The server must run at this point!
     */
    public void connectingToClient() {
        while(this.getServerSocket() != null) {
            System.out.println("Waiting for the client...");
            Socket client;
            try {
                client = this.getServerSocket().accept(); //wait for the request from client
                System.out.println("Found a client");
                Vehicle vehicle = this.readMessage(client);
                int discountedPrice = calculateDiscountedPrice(vehicle);
                this.writeMessage(client, Integer.toString(discountedPrice));
            } catch (IOException e) {
                System.err.println("Error at connecting to client");
            }
        }
        System.out.println("Connection terminated.");
    }

    /**
     * Calculates the discounted price.
     * @param vehicle: vehicle, that should get the new price
     * @return discounted price
     */
    public int calculateDiscountedPrice(Vehicle vehicle) {
        int catalogPrice = Integer.parseInt(vehicle.getCatalogPrice());
        int discount = getDiscount(vehicle);
        return (catalogPrice - ((catalogPrice / 100) * discount));
    }

    public abstract int getDiscount(Vehicle vehicle);
}
