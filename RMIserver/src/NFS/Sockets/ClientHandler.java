/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NFS.Sockets;

import NFS.Sockets.SocketProcess.SocketProcess;
import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Jesus
 */
public class ClientHandler extends Thread {
    private SocketProcess serverSocket;
    
    private static List<ClientHandler> clientes = Collections.synchronizedList(new ArrayList<>());
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public ClientHandler(SocketProcess serverSocket) {
        this.serverSocket = serverSocket;
        
    }

    @Override
    public void run() {
        clientes.add(this);
        while (true) {
            ArrayList<Object> dataRequest = (ArrayList<Object>) serverSocket.listen();
            if (dataRequest != null && !dataRequest.isEmpty()) {
                String mensaje = dataRequest.get(0).toString();
                
                if ("-/DISCONNECT".equals(mensaje)) {
                    break;
                }
                
                
                broadcast(mensaje, this);
                
            }
        }
        clientes.remove(this);
    }



    

    private void broadcast(String mensaje, ClientHandler sender) {
        synchronized (clientes) {
            for (ClientHandler client : clientes) {
                client.enviarNotificacion(mensaje); 
            }
        }    
    }
    
   
    private void enviarNotificacion(String message) {
       
        ArrayList<Object> dataResponse = new ArrayList<>();
        dataResponse.add(message);
        dataResponse.add(0);
        serverSocket.response(dataResponse);
    }
    

    
}