package NFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;

public class Client {

    private String url;
    private String username;

    public Client(String ip, String port, String serviceName, String username) {
        this.url = "rmi://" + ip + ":" + port + "/" + serviceName;
        this.username = username;
    }

    public void start() {
        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Enviar el nombre de usuario al servidor
            out.println(username);

            // Escuchar notificaciones
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Notification: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", "1099", "DocumentService", "username"); // Reemplazar con el nombre de usuario real
        client.start();
    }
}