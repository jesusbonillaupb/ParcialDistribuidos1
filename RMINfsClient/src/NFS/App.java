package NFS;

import NFS.InterfazArchivos.RMINfs;
import NFS.services.NfsService;




public class App {
    public static void main(String[] args) {
        try {
            String ip = "localhost";
            String port = "4000";
            Server server = new Server(ip, port);
            
            // Crear e implementar el servicio DocumentService
            RMINfs nfsService = new NfsService();
            
            
            server.addService("NfsService", nfsService);
            
            

            // Desplegar todos los servicios
            if (server.deploy()) {
                System.out.println("Todos los servicios se han desplegado correctamente.");
            } else {
                System.out.println("Error al desplegar los servicios.");
            }

            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        /*
        // Aca comienza todo lo que tenga que ver con las notificaciones por medio de sockets
        JavaServerSocket javaServerSocket = new JavaServerSocket(1802, 100);
        ServerSocket serverSocket = javaServerSocket.get();
        if (serverSocket == null) {
            System.out.println("ServerSocket is null");
            return;
        }
        while (true) {
            SocketProcess server = new SocketServer(serverSocket);

            if (!server.bind()) {
                System.out.println("Server bind failed");
                return;
            } else {
                System.out.println("Se conectó un Usuario");
            }

            // Crear un hilo para manejar al cliente
            ClientHandler clientHandler = new ClientHandler(server);
            clientHandler.start();
            
        }
        */
    }
}