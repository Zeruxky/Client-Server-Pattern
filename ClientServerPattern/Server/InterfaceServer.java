package ClientServerPattern.Server;

import java.net.ServerSocket;

/**
 * Interface for creating a new Server.
 * @author philip
 * @version 1.0
 */
public interface InterfaceServer {
    ServerSocket getServerSocket();
    int getServerPortNumber();
    void setServerSocket(ServerSocket serverSocket);
    void setServerPortNumber(int serverPortNumber);
    void setServerSet(boolean isServerSet);
    boolean isServerSet();
}
