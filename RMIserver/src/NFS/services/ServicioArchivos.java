/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NFS.services;

import NFS.interfaz.RMIArchivosInterfaz;
import NFS.Modelo.UserManager;
import NFS.Modelo.NotificationServer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Jesus
 */
public class ServicioArchivos extends UnicastRemoteObject implements RMIArchivosInterfaz{

 
}