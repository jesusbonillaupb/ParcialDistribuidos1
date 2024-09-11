package NFS.services;

import NFS.Modelo.UserCrud;
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

}