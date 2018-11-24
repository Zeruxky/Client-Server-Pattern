package ClientServerPattern;

import java.net.Socket;

/**
 * Simple protocol, that handles the communication between server and client.
 * @param <U>: Type of message you want to write
 * @param <V>: Type of message you want to read
 */
public interface ProtocolInterface<U, V> {
    void writeMessage(Socket targetObject, U message);
    V readMessage(Socket targetObject);
}
