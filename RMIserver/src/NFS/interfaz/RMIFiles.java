
package NFS.interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIFiles extends Remote {
    
    // metodo para primero registrar un archivo y luego si subir el archivo
   public boolean addFile(byte[] fileData,String tipo, String nombre, String autor, String ruta, String rutaCarpeta, String privacidad )throws RemoteException; 
   public List<Object[]> getFolderFiles(String rutaCarpeta) throws RemoteException;
}
