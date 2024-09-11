
package NFS.InterfazArchivos;

import NFS.interfaz.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMINfs extends Remote {
    
    // metodo para subir un archivo al servidor en linux
    public boolean uploadFile(String fileName, byte[] fileData,String ruta) throws RemoteException; 
    
    
}
