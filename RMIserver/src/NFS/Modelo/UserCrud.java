
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
    
    public List listar(){
        List<User>datos = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try {
            con= conectar.getConnection();
            ps=con.prepareStatement(sql);
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
    
    public List rolUsuario(int id){
        List<User>datos = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE idUsuario=?";
        try {
            con= conectar.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);         
            
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
    
    public boolean agregar(String nombre, String password,String rol) {
    String sql = "INSERT INTO usuarios (usNombre, usPassword,usRol) VALUES (?, ?,?);";
    try {
        con = conectar.getConnection();  
        ps = con.prepareStatement(sql);  
        ps.setString(1, nombre);         
        ps.setString(2, password);
        ps.setString(3, rol);
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
    
    public boolean actualizar(int id, String name, String password,String rol) {
    String sql = "UPDATE usuarios SET usNombre=?, usPassword=?, usRol=? WHERE idUsuario=?;";
    try {
        con = conectar.getConnection();  
        ps = con.prepareStatement(sql);  
        ps.setString(1, name);         
        ps.setString(2, password);
        ps.setString(3, rol);
        ps.setInt(4,id);
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
    
    public boolean borrar(int id) {
    String sql = "DELETE FROM usuarios WHERE idUsuario=?;";
    try {
        con = conectar.getConnection();  
        ps = con.prepareStatement(sql);  
        ps.setInt(1,id);
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
    
    public static String getFileOwner(String filePath) {
        String query = "SELECT u.username FROM files f " +
                       "JOIN users u ON f.owner_id = u.id " +
                       "WHERE f.path = ?";
        try{
            con =conectar.con= conectar.getConnection();
            ps=con.prepareStatement(query);
            ps.setString(1, filePath);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getSharedUsers(String filePath) {
        String query = "SELECT u.username FROM shared_files sf " +
                       "JOIN users u ON sf.user_id = u.id " +
                       "JOIN files f ON sf.file_id = f.id " +
                       "WHERE f.path = ?";
        List<String> sharedUsers = new ArrayList<>();
        try {
            con =conectar.con= conectar.getConnection();
            ps=con.prepareStatement(query);
            ps.setString(1, filePath);
            rs = ps.executeQuery();
            while (rs.next()) {
                sharedUsers.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sharedUsers;
    }
}
