/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package NFS.interfaz;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
/**
 *
 * @author Jesus
 */
public interface RMIUsers extends Remote{
    //Metoodos creacion usuarios
    // Metodos cliente
    public int loginUser(String name,String password) throws RemoteException;  
    // Crud admin 
    public boolean RegisterUser(String name,String password,String rol) throws RemoteException; 
    public boolean DeleteUser(int id) throws RemoteException;
    public boolean UpdateUser(int id,String name, String password, String rol) throws RemoteException; 
    
    // info usuarios
    // obtener todos los usuarios
    public List<Object[]> getUsuarios() throws RemoteException;
    public String getRole(int id) throws RemoteException;
    
}
