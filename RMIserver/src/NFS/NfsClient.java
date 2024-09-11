package NFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;
import NFS.interfaz.RMIUsers;
import java.io.FileOutputStream;
import java.rmi.Naming;
import java.util.List;
import NFS.InterfazArchivos.RMINfs;

public class NfsClient {

    private String url;
   

    public NfsClient(String ip, String port, String serviceName) {
        this.url = "rmi://" + ip + ":" + port + "/" + serviceName;
        
    }

  

    public boolean uploadFile(String fileName, byte[] fileData,String ruta) {
        try {
            RMINfs archService = (RMINfs) Naming.lookup(this.url);
            return archService.uploadFile(fileName, fileData,ruta);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return false;
    }

}