/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package NFS.Sockets.JavaSocket;

import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaClientSocket {
    private int port;
    private String adress;

    public JavaClientSocket(int port, String adress){
        this.port = port;
        this.adress = adress;
    }

    public Socket get(){
        try {
            return new Socket (InetAddress.getByName(adress), this.port);
        } catch (Exception e){
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);    
			return null;
        }


    }
}
