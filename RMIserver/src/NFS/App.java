package NFS;

import NFS.Sockets.NotificationServer;
import NFS.interfaz.DocumentService;
import NFS.services.DocumentServiceImpl;


public class App {
    public static void main(String[] args) {
        try {
            String ip = "localhost";
            String port = "1099";
            Server server = new Server(ip, port);

            // Crear e implementar el servicio DocumentService
            DocumentService documentService = new DocumentServiceImpl();
            server.addService("DocumentService", documentService);

            // Desplegar todos los servicios
            if (server.deploy()) {
                System.out.println("Todos los servicios se han desplegado correctamente.");
            } else {
                System.out.println("Error al desplegar los servicios.");
            }
            
            // Despliega el notification server
            NotificationServer notificationServer = new NotificationServer();
            notificationServer.deploy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

