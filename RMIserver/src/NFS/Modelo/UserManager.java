
package NFS.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/sgd";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkUserPermission(String username, String resource, String permissionType) {
        String query = "SELECT p.can_read, p.can_write FROM permissions p " +
                       "JOIN users u ON p.user_id = u.id " +
                       "WHERE u.username = ? AND p.resource = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, resource);
            ResultSet rs = stmt.executeQuery();
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
}