
package NFS.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserCrud {

    static ConexionBD conectar = new ConexionBD();
    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;
    
    // retorna el id del usuario con el cual podra hacer las operaciones
    public List login(String name,String password){
        List<User>datos = new ArrayList<>();
        String query = "SELECT * FROM usuarios WHERE usNombre=? && usPassword=?";
        try {
            con= conectar.getConnection();
            ps=con.prepareStatement(query);
            ps.setString(1, name);         
            ps.setString(2, password);
            rs=ps.executeQuery();
            
            
            while (rs.next()) {  
                User us = new User();
                us.setId(rs.getInt(1));
                us.setName(rs.getString(2));
                us.setPassword(rs.getString(3));
                us.setRole(rs.getString(4));
                datos.add(us);    
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datos;    
    }
    
    public static boolean checkUserPermission(String username, String resource, String permissionType) {
        String query = "SELECT p.can_read, p.can_write FROM permissions p " +
                       "JOIN users u ON p.user_id = u.id " +
                       "WHERE u.username = ? AND p.resource = ?";
        try  {
            con =conectar.con= conectar.getConnection();
            ps=con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, resource);
            rs = ps.executeQuery();
            if (rs.next()) {
                boolean canRead = rs.getBoolean("can_read");
                boolean canWrite = rs.getBoolean("can_write");
                if ("read".equals(permissionType)) {
                    return canRead;
                } else if ("write".equals(permissionType)) {
                    return canWrite;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean checkUserCanRead(String username, String resource, String permissionType){
        String query = "SELECT p.can_read, p.can_write FROM permissions p " +
                       "JOIN users u ON p.user_id = u.id " +
                       "WHERE u.username = ? AND p.resource = ?";
        try  {
            con =conectar.con= conectar.getConnection();
            ps=con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, resource);
            rs = ps.executeQuery();
            if (rs.next()) {
                boolean canRead = rs.getBoolean("can_read");
                if ("read".equals(permissionType)) {
                    return canRead;
                } 
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean checkUserCanEdit(String username, String resource, String permissionType){
        String query = "SELECT p.can_read, p.can_write FROM permissions p " +
                       "JOIN users u ON p.user_id = u.id " +
                       "WHERE u.username = ? AND p.resource = ?";
        try  {
            con =conectar.con= conectar.getConnection();
            ps=con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, resource);
            rs = ps.executeQuery();
            if (rs.next()) {
                boolean canWrite = rs.getBoolean("can_write");
                if ("write".equals(permissionType)) {
                    return canWrite;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}