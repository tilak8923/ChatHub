package org.example.DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/ChatAppDB";
//    private static final String DB_USER = "root";
//    private static final String DB_PASSWORD = "tilak@head";
    private static final String DB_URL = "jdbc:mysql://f988uc.h.filess.io:61002/ChatHub_hangpageme";
    private static final String DB_USER = "ChatHub_hangpageme";
    private static final String DB_PASSWORD = "8b6e8fbb4ff5235ad02326512074b3ce7f4f610e";
    public static Connection createConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            if (con != null) {
                System.out.println("Connected to Database successfully!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Not Found! Please attach the MySQL Connector JAR.");
        } catch (SQLException e) {
            System.out.println("SQL Error: Connection failed. Check if MySQL Server is running.");
            e.printStackTrace();
        }
        return con;
    }
}
