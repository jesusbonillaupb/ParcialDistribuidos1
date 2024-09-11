/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package NFS.Sockets.SocketProcess;
import java.util.List;

// Interfaz del metodos del Socket
public interface SocketProcess {
    // Crea el socket y lo enlaza a un puerto en especifico
    public boolean  bind();
    // Escucha al cliente y devuelve la respuesta mediante un array de objetos
    public List<Object> listen();
    // Manda una respuesta al cliente
    public boolean response(List<Object> data);
    // cierra la comunicacion
    public boolean close();


}
