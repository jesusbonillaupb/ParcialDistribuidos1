package NFS.services;

import NFS.Modelo.UserCrud;
import NFS.Sockets.ClientHandler;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import NFS.interfaz.RMIDocuments;
import java.util.List;

public class DocumentsService extends UnicastRemoteObject implements RMIDocuments {

    private static final String NFS_MOUNT_POINT = "/mnt/nfs_share";

    public DocumentsService() throws RemoteException {
        super();
    }

    @Override
    public String readFile(String username, String filePath) throws RemoteException {
        if (UserCrud.checkUserPermission(username, filePath, "read")) {
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
        if (UserCrud.checkUserPermission(username, filePath, "write")) {
            try {
                Files.write(Paths.get(NFS_MOUNT_POINT, filePath), content.getBytes());
                notifyUsers(filePath);
            } catch (IOException e) {
                throw new RemoteException("Error writing file", e);
            }
        } else {
            throw new RemoteException("Permission denied");
        }
    }

    private void notifyUsers(String filePath) {
        // Notificar al propietario y a los usuarios compartidos
        String owner = UserCrud.getFileOwner(filePath);
        List<String> sharedUsers = UserCrud.getSharedUsers(filePath);

        ClientHandler.notifyClients(owner, "Your document " + filePath + " has been modified.");
        for (String user : sharedUsers) {
            ClientHandler.notifyClients(user, "The document " + filePath + " you have access to has been modified.");
        }
    }
}