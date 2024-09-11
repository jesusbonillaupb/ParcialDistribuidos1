

package NFS.Sockets.JavaSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;


// se crea la clase socket
public class JavaServerSocket {
    private int port;
    private int amountClients;
    
    // constructor 
    //cuando se inicializa un socket se debe definir el puerto y la cantidad de clientes
    public JavaServerSocket(int port,int amountClients){
        this.port= port;
        this.amountClients = amountClients;
    }
    
    // metodo para crear y devolver un socket
    public ServerSocket get() {
		try {
			return new ServerSocket(this.port, this.amountClients);
		} catch (IOException e) { 
			Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);    
			return null;
		}
	}

}
                