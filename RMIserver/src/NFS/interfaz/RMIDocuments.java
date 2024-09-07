
package NFS.interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIDocuments extends Remote {
    String readFile(String username, String filePath) throws RemoteException;
    void writeFile(String username, String filePath, String content) throws RemoteException;
}
