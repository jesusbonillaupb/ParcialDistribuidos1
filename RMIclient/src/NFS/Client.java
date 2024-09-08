package NFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;
import NFS.interfaz.RMIUsers;
import java.rmi.Naming;
import java.util.List;

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

    public int loginUser(String name, String password) {
        try {
            RMIUsers usrService = (RMIUsers) Naming.lookup(this.url);
            return usrService.loginUser(name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean registerUser(String name, String password, String rol) {
        try {
            RMIUsers usrService = (RMIUsers) Naming.lookup(this.url);
            return usrService.RegisterUser(name, password, rol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int id) {
        try {
            RMIUsers usrService = (RMIUsers) Naming.lookup(this.url);
            return usrService.DeleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(int id, String name, String password, String rol) {
        try {
            RMIUsers usrService = (RMIUsers) Naming.lookup(this.url);
            return usrService.UpdateUser(id, name, password, rol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getRol(int id) {
        try {
            RMIUsers usrService = (RMIUsers) Naming.lookup(this.url);
            return usrService.getRole(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    public List<Object[]> getUsuarios() {
        try {
            RMIUsers usrService = (RMIUsers) Naming.lookup(this.url);
            return usrService.getUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", "1099", "DocService", "username"); // Reemplazar con el nombre de usuario real
        client.start();
    }
}