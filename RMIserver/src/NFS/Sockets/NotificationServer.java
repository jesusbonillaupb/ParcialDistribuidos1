package NFS.Sockets;

import java.io.IOException;
import java.net.ServerSocket;

public class NotificationServer {

    private static final int PORT = 12345;

    public void deploy() {
        System.out.println("Notification Server is running...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}