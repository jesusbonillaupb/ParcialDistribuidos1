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
}
