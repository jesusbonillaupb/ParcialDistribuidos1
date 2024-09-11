/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NFS.Modelo;

import static NFS.Modelo.UserCrud.con;
import static NFS.Modelo.UserCrud.conectar;
import static NFS.Modelo.UserCrud.ps;
import static NFS.Modelo.UserCrud.rs;
import NFS.NfsClient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesus
 */
public class FileCrud {
    
    // metodo pa listar los archivos
    public List listarCarpeta(String rutaCarpeta){
        List<File>datos = new ArrayList<>();
        String sql = "SELECT * FROM archivos WHERE rutaCarpeta =? ";
        try {
            con= conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, rutaCarpeta);  
            rs=ps.executeQuery();
            while (rs.next()) {
                File file = new File();
                file.setId(rs.getInt(1));
                file.setTipo(rs.getString(2));
                file.setNombre(rs.getString(3));
                file.setAutor(rs.getString(4));
                file.setRuta(rs.getString(5));
                file.setRutaCarpeta(rs.getString(6));
                file.setPrivacidad(rs.getString(7));
                datos.add(file);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datos;    
    }
    
    public boolean agregar(String tipo, String nombre,String autor,String ruta, String rutaCarpeta,String privacidad) {
    String sql = "INSERT INTO archivos (tipo, nombre,autor,ruta,rutaCarpeta,privacidad) VALUES (?, ?, ?, ?, ?, ?);";
    try {
        con = conectar.getConnection();  
        ps = con.prepareStatement(sql);  
        ps.setString(1, tipo);         
        ps.setString(2, nombre);
        ps.setString(3, autor);
        ps.setString(4, ruta);
        ps.setString(5, rutaCarpeta);
        ps.setString(6, privacidad);
        ps.executeUpdate();              
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    } finally {
        try {
            if (ps != null) ps.close();  
            if (con != null) con.close(); 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    return true;
    }
    
    public boolean upload(String fileName, byte[] fileData,String nombre){
        NfsClient nfsClient = new NfsClient("localhost", "4000", "NfsService");
        if(nfsClient.uploadFile(fileName, fileData,nombre) ){
            return true;
        }else{
            return false;
        }
    }
}
