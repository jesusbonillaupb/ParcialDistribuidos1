/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NFS.Modelo;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Jesus
 */
public class ConexionBD {
    Connection con;
    public Connection getConnection(){
        //crear una bd llamada sgd en myworkbench 
        String url= "jdbc:mysql://localhost:3306/sgd";
        String user= "root";
        //cambiar por la contraseña que tenga su usuario root en mysqlWorkbench
        String pass= "1234";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,pass);
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
        
    }
    
    
}
