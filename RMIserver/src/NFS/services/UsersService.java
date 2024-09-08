
package NFS.services;

import NFS.Controlador.Controlador;
import NFS.interfaz.RMIUsers;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author Jesus
 */
public class UsersService extends UnicastRemoteObject implements RMIUsers{
    public UsersService() throws RemoteException {
        super();
    }

    @Override
    public int loginUser(String name, String password) throws RemoteException {
        Controlador c= new Controlador();
        return c.login(name, password);
    }

    @Override
    public boolean RegisterUser(String name, String password,String rol) throws RemoteException {
        Controlador c = new Controlador();
        return c.agreagarUsuario(name, password,rol);
    }

    @Override
    public boolean DeleteUser(int id) throws RemoteException {
        Controlador c=new Controlador();
        return c.eliminarUsuario(id);
    }

    @Override
    public boolean UpdateUser(int id, String name, String password,String rol) throws RemoteException {
        Controlador c = new Controlador();
        return c.actualizarUsuario(id, name, password, rol);
    }

    @Override
    public List<Object[]> getUsuarios() throws RemoteException {
        Controlador c =new Controlador();
       return c.listar();
    }

    @Override
    public String getRole(int id) throws RemoteException {
        Controlador c= new Controlador();
        return c.getRol(id);
    }
}
