/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NFS.services;

import NFS.interfaz.RMIArchivosInterfaz;
import java.rmi.RemoteException;
import java.util.List;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Jesus
 */
public class ServicioArchivos extends UnicastRemoteObject implements RMIArchivosInterfaz{

  public ServicioArchivos() throws RemoteException {
    super();
  }
    
    /*
    @Override
    public List<Object[]> getNews() throws RemoteException {
        Controlador c = new Controlador();
        return c.listaNoticias();
    }*/
  

    

    

}