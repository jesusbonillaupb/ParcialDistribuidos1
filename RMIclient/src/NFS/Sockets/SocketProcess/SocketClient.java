package NFS.Sockets.SocketProcess;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketClient implements SocketProcess {
    private Socket clientSocket;
    private Session session;
    private volatile boolean disconnectRequested = false; // Flag para solicitar desconexión
    

    public SocketClient(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.session = null;
        
    }

       
    
    @Override
    public boolean connect() {
        try {
            this.session = new Session(this.clientSocket);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Object> read() {
        ArrayList<Object> dataList = new ArrayList<>();
        boolean next = true;
        Object data = null;
        int flag = 1; 

        while (next) {
            if (this.session == null || disconnectRequested) {
                return null;
                
                 // Salir del bucle si la sesión es nula o desconexión solicitada
            }

            try {
                data = this.session.read();
                if (data != null) {
                    try {
                        flag = (int) data;
                    } catch (Exception e) {
                        flag = 1;
                    }
                    if (data instanceof String && "-/DISCONNECT".equals(data)) {
                        System.out.println("Se recibió el mensaje de desconexión.");
                        disconnectRequested = true; // Marcar la desconexión
                        this.close(); // Cerrar sesión y detener lectura
                        break;
                    }
                    next = flag != 0;
                    if (next) {
                        dataList.add(data);
                    }
                } else {
                    next= false;
                    this.close();
                    System.out.println("La sesión se ha cerrado inesperadamente.");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        return dataList;
    }

    @Override
    public boolean write(List<Object> data) {
        if (this.session != null) {
            data.forEach(d -> this.session.write(d));
            return true;
        } else {
            System.out.println("La sesión es nula, no se puede escribir.");
            return false;
        }
    }

    @Override
    public boolean close() {
        boolean successful = false;
        if (this.session != null) {
            successful = this.session.close();
            this.session = null; // Asegúrate de marcar la sesión como nula
        } else {
            System.out.println("La sesión ya está cerrada o nunca fue inicializada.");
        }
        return successful;
    }
}
