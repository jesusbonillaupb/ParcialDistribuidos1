package NFS.services;


import NFS.InterfazArchivos.RMINfs;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


import java.io.FileOutputStream;

public class NfsService extends UnicastRemoteObject implements RMINfs {

    private static final String NFS_MOUNT_POINT = "/mnt/nfs_share";

    public NfsService() throws RemoteException {
        super();
    }

    @Override
    public boolean uploadFile(String fileName, byte[] fileData,String ruta) throws RemoteException {
         try (FileOutputStream fos = new FileOutputStream(ruta)) {
                fos.write(fileData);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        
    }

    

}