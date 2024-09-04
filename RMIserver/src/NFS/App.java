package NFS;

import java.rmi.RemoteException;

public class App {
    public static void main(String[] args) throws RemoteException {
        System.out.println("Hello RMI!");

        Server server = new Server("localhost", "5000");
        /* ejemplo para instanciar servicios
        
        RMIUSERS usr =new UsersService();
        RMINEWS nws =new NewsService();
        
        */
        // Agregar diferentes tipos de servicios
        /*
        server.addService("usr", usr);
        server.addService("nws", nws);
        */
        // Desplegar los servicios
        if (server.deploy()) {
            System.out.println("Todos los servicios han sido desplegados exitosamente.");
        } else {
            System.out.println("Fallo al desplegar los servicios.");
        }
    }
}

