/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NFS.Controlador;

import NFS.Modelo.User;
import NFS.Modelo.UserCrud;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesus
 */
public class Controlador {
    UserCrud crudUs =new UserCrud();
    
    
    public List<Object[]> listar() {
        List<Object[]> datos = new ArrayList<>();
        List<User> lista = crudUs.listar();

        for (User user : lista) {
            Object[] fila = new Object[4];
            fila[0] = user.getId();
            fila[1] = user.getName();
            fila[2] = user.getPassword();
            fila[3] = user.getRole();
            datos.add(fila);
        }

        return datos;
    }
    
    public int login(String name, String password) {
        List<Object[]> datos = new ArrayList<>();
        List<User> lista = crudUs.login(name, password);
        if (lista.isEmpty()) {
            // devuelve 0, indicando que no existe el usuario
            return 0;
        } else {
            // devuelve el id del usuario
            return lista.get(0).getId();
        }
    }
    public boolean agreagarUsuario(String nombre, String password,String rol) {
        return crudUs.agregar(nombre,password,rol);
    }
    public boolean actualizarUsuario(int id, String nombre, String password,String rol){
        return crudUs.actualizar(id, nombre, password,rol);
    }
    public boolean eliminarUsuario(int id){
        return crudUs.borrar(id);                        
    }
    public String getRol(int id){
        List<Object[]> datos = new ArrayList<>();
        List<User> lista = crudUs.rolUsuario(id);
        if (lista.isEmpty()) {
            
            return "error";
        } else {
            
            return lista.get(0).getRole();
        }
    }
}
