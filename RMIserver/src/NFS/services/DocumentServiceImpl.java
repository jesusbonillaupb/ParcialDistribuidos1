
package NFS.services;

import NFS.Modelo.UserManager;
import NFS.interfaz.DocumentService;
import NFS.Modelo.NotificationServer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DocumentServiceImpl extends UnicastRemoteObject implements DocumentService {

    private static final String NFS_MOUNT_POINT = "/mnt/nfs_share";

    public DocumentServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String readFile(String username, String filePath) throws RemoteException {
        if (UserManager.checkUserPermission(username, filePath, "read")) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(NFS_MOUNT_POINT, filePath));
                return new String(bytes);
            } catch (IOException e) {
                throw new RemoteException("Error reading file", e);
            }
        } else {
            throw new RemoteException("Permission denied");
        }
    }

    @Override
    public void writeFile(String username, String filePath, String content) throws RemoteException {
        if (UserManager.checkUserPermission(username, filePath, "write")) {
            try {
                Files.write(Paths.get(NFS_MOUNT_POINT, filePath), content.getBytes());
                NotificationServer.notifyClients("File created/modified: " + filePath);
            } catch (IOException e) {
                throw new RemoteException("Error writing file", e);
            }
        } else {
            throw new RemoteException("Permission denied");
        }
    }
}