package NFS.Sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler extends Thread {

    private Socket socket;
    private PrintWriter out;
    private String username;
    private static Map<String, PrintWriter> clientWriters = new HashMap<>();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            // Assume the first message from the client is the username
            username = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
            synchronized (clientWriters) {
                clientWriters.put(username, out);
            }
            // Keep the connection open
            while (true) {
                // Do nothing, just keep the connection alive
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                synchronized (clientWriters) {
                    clientWriters.remove(username);
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void notifyClients(String username, String message) {
        synchronized (clientWriters) {
            PrintWriter writer = clientWriters.get(username);
            if (writer != null) {
                writer.println(message);
            }
        }
    }
}
