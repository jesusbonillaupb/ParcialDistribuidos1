/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package NFS.interfaz;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author Jesus
 */
public interface UserService extends Remote{
    int userLoging(String username, String filePath) throws RemoteException;
    
}
