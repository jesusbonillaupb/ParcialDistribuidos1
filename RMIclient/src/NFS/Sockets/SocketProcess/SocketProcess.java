

package NFS.Sockets.SocketProcess;
import java.util.List;

public interface SocketProcess {
   
    // Crea el socket lo enlaza a un puerto especifico y una ip
    public boolean  connect();
    // Escucha al server y devuelve la respuesta mediante un array de objetos
    public List<Object> read();
    // Manda una peticion al servidor
    public boolean write(List<Object> data);
    // cierra la comunicacion
    public boolean close();


}
