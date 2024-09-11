/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package NFS.Sockets.SocketProcess;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// Clase sesion, la cual maneja la comunicacion 
// el flujo de entrada y de salida (inpout y outpout)
// tambien se encarga de cerrar la session(close)
public class Session {
    // mensaje del server al cliente
    private ObjectOutputStream objectOutputStream;
    // mensaje del cliente al servidor
    private ObjectInputStream objectInputStream;
    private Socket socket;

    // constructor
    public Session(Socket socket){
        try {
            this.socket = socket;
            // se obtiene el mensaje que se va a enviar al cliente
            this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            // se obtiene el mensaje que se recibe del cliente
            this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());
        // ocurrio un error al asignarle un token a la sesion
        } catch (IOException e){
            e.printStackTrace();
            this.objectOutputStream = null;
			this.objectInputStream = null;
			this.socket = null;
        }
    }

    // lee un objeto del Object input stream y lo devuelve
    public Object read(){
        try {
            return this.objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // escribe un objeto en el flujo de salida
    public boolean write(Object data){
        try {
            this.objectOutputStream .writeObject(data);
            // se asegura que los datos se hallan enviado inmediatamente
            this.objectOutputStream.flush();
            return true;
        } catch (IOException e) {
            
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean close(){
        try {
            // cierra el flujo de salida (server a cliente)
            this.objectOutputStream.close();
            // cierrar el flujo de entrada(cliente a server)
            this.objectInputStream.close();
            // cierra el socket
            this.socket.close();
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    

}
