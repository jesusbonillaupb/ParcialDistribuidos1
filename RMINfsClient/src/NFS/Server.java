package NFS;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.Remote;
import java.util.HashMap;
import java.util.Map;


public class Server {
    private String ip;
    private String port;
    private Map<String, Remote> services; 

    public Server(String ip, String port) {
        this.ip = ip;
        this.port = port;
        this.services = new HashMap<>();
    }

    // Método para agregar servicios
    public void addService(String serviceName, Remote service) {
        services.put(serviceName, service);
    }

    // Método para desplegar todos los servicios
    public boolean deploy() {
        try {
            System.setProperty("java.rmi.server.hostname", ip);
            LocateRegistry.createRegistry(Integer.parseInt(port));

            // Desplegar cada servicio en el mapa
            for (Map.Entry<String, Remote> entry : services.entrySet()) {
                String uri = "//" + ip + ":" + port + "/" + entry.getKey();
                Naming.rebind(uri, entry.getValue());
                System.out.println("Servicio desplegado: " + entry.getKey());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
