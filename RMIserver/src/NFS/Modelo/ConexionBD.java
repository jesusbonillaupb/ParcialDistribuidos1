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
        String url= "jdbc:mysql://localhost:3306/sgd";
        String user= "root";
        String pass= "password";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,pass);
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
        
    }
    
    
}
