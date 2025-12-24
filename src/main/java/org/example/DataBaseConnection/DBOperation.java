package org.example.DataBaseConnection;

import java.sql.Connection;
import java.sql.*;

public class DBOperation {
    public void insertUser(String name, String username, String password) {
        Connection conn = DBConnection.createConnection();
        try {
            String sql = "INSERT INTO users (name, username, password) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, username);
            pst.setString(3, password);

            int rows = pst.executeUpdate();
            if (rows > 0) System.out.println("User '" + name + "' registered successfully.");
        } catch (SQLException e) { e.printStackTrace(); }
    }


    public void updatePassword(String username, String newPassword) {
        String checkSql = "SELECT username FROM users WHERE username = ?";
        String updateSql = "UPDATE users SET password = ? WHERE username = ?";
        try (Connection conn = DBConnection.createConnection()) {
            try (PreparedStatement checkPst = conn.prepareStatement(checkSql)) {
                checkPst.setString(1, username);
                ResultSet rs = checkPst.executeQuery();
                if (rs.next()) {
                    try (PreparedStatement updatePst = conn.prepareStatement(updateSql)) {
                        updatePst.setString(1, newPassword);
                        updatePst.setString(2, username);
                        updatePst.executeUpdate();
                        System.out.println("Success: Password updated for " + username);
                    }
                } else {
                    System.out.println("Update Failed: Username '" + username + "' not found in database.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
