/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NFS.Sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesus
 */
public class ClientHandler extends Thread {

    private Socket socket;
    private PrintWriter out;
    private static List<PrintWriter> clientWriters = new ArrayList<>();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            synchronized (clientWriters) {
                clientWriters.add(out);
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
                    clientWriters.remove(out);
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void notifyClients(String message) {
        synchronized (clientWriters) {
            for (PrintWriter writer : clientWriters) {
                writer.println(message);
            }
        }
    }
}

