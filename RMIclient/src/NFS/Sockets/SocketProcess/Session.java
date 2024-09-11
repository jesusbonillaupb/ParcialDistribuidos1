package NFS.Sockets.SocketProcess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Session {
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket socket;

    // Constructor
    public Session(Socket socket) {
        this.socket = socket;
        try {
            this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            this.close();  // Cierra la sesión si hay un error durante la inicialización
        }
    }

    // Lee un objeto del ObjectInputStream
    public Object read() {
        if (this.objectInputStream == null) {
            System.out.println("Error: La sesión es nula.");
            return null;
        }
        try {
            return this.objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            this.close();  // Cierra la sesión si ocurre una excepción
            return null;
        }
    }

    // Escribe un objeto en el ObjectOutputStream
    public boolean write(Object data) {
        if (this.objectOutputStream == null) {
            System.out.println("Error: La sesión es nula.");
            return false;
        }
        try {
            this.objectOutputStream.writeObject(data);
            this.objectOutputStream.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            this.close();  // Cierra la sesión si ocurre una excepción
            return false;
        }
    }

    // Cierra el ObjectOutputStream, ObjectInputStream y el Socket
    public boolean close() {
        boolean successful = true;
        try {
            if (this.objectOutputStream != null) {
                this.objectOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            successful = false;
        }
        try {
            if (this.objectInputStream != null) {
                this.objectInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            successful = false;
        }
        try {
            if (this.socket != null && !this.socket.isClosed()) {
                this.socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            successful = false;
        }
        return successful;
    }
}
