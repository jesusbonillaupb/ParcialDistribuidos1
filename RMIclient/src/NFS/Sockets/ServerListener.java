/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NFS.Sockets;



import NFS.Sockets.SocketProcess.SocketProcess;
import java.util.ArrayList;

/**
 *
 * @author Jesus
 */
public class ServerListener implements Runnable {
    private SocketProcess serverInput;

    

    public ServerListener(SocketProcess serverInput) {
        this.serverInput = serverInput;
        // luego se debera agregar la vista archivos para que se pueda ejecutar el metodo actualizar vista
    }

    @Override
    public void run() {
        while (true) {
            
               ArrayList<Object> dataRequest = (ArrayList<Object>) serverInput.read();
               
               if (dataRequest != null && !dataRequest.isEmpty()) {
                   
                   String mensaje = dataRequest.get(0).toString();
                        mostrarNotificacion(mensaje);
                }
        }
    }
   
   private void mostrarNotificacion(String mensaje){
       // luego se cambiara la implementacion, para que pueda actualizar la lista 
       // de archivos
       System.out.println(mensaje);
       
   }
}