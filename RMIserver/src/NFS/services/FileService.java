
package NFS.services;

import NFS.Controlador.Controlador;
import NFS.interfaz.RMIFiles;
import NFS.interfaz.RMIUsers;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author Jesus
 */
public class FileService extends UnicastRemoteObject implements RMIFiles{
    public FileService() throws RemoteException {
        super();
    }

    @Override
    public boolean addFile(byte[] fileData, String tipo, String nombre, String autor, String ruta, String rutaCarpeta, String privacidad) throws RemoteException {
        Controlador c= new Controlador();
        return c.agregarArchivo(fileData, tipo, nombre, autor, ruta, rutaCarpeta, privacidad);
    }

    @Override
    public List<Object[]> getFolderFiles(String rutaCarpeta) throws RemoteException {
        Controlador c= new Controlador();
        return c.listarArchivosCarpeta(rutaCarpeta);
    }

}
