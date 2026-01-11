package org.example.DataBaseConnection;

import java.sql.Connection;
import java.sql.*;
interface DBOperations{
    //void updatePassword(String username, String newPassword);
    int getUserId(String userName);
    String getUserName(int user_id);
    String getPassword(int user_id);
}
public class DBOperation implements DBOperations{
    public static boolean insertUser(String name, String username, String password) {
        boolean flag = false;
        try (Connection conn = DBConnection.createConnection()) {
            try {
                String sql = "INSERT INTO users (name, username, password) VALUES (?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, username);
                pst.setString(3, password);

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    System.out.println("User '" + name + "' registered successfully.");
                    flag = true;
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean updatePassword(String username, String newPassword) {
        boolean flag= false;
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
                        flag= true;
                    }
                } else {
                    System.out.println("Update Failed: Username '" + username + "' not found in database.");
                }
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    @Override
    public int getUserId(String username) {
        int userId = 0;
        try (Connection conn = DBConnection.createConnection()) {
            try {
                String sql = "SELECT user_id FROM users WHERE username = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, username);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    userId = rs.getInt(1);
                } else System.out.println("User '" + username + "' not found in database.");
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userId;
    }

    @Override
    public String getUserName(int user_id) {
        Connection con = DBConnection.createConnection();
        String name = "";
        try{
            String query = "Select username from users where username = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, user_id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                name = rs.getString(1);
            }
            else {
                System.out.println("User not found in database.");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return name;
    }

    @Override
    public String getPassword(int user_id) {
        Connection conn = DBConnection.createConnection();
        String password = "";
        try{
            PreparedStatement pst = conn.prepareStatement("Select password from users where user_id = ?");
            pst.setInt(1, user_id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                password = rs.getString(1);
            }
            else {
                System.out.println("User not found in database.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return password;
    }

    public static String getName(int user_id) {
        String name = "";
        try (Connection con = DBConnection.createConnection()) {
            PreparedStatement pst = con.prepareStatement("Select name from users where user_id = ?");
            pst.setInt(1, user_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                name = rs.getString(1);
            } else {
                System.out.println("User not found in database.");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
    public String getCurrentTime(int chat_id) {
        Connection con = DBConnection.createConnection();
        String time = "";
        try{
            PreparedStatement pst = con.prepareStatement("Select chat_time from chatdata where chat_id = ?");
            pst.setInt(1, chat_id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                time = rs.getString(1);
            }
            else System.out.println("User not found in database.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        String[] arr = time.split(" ");
//        System.out.println(arr[1]);
        return arr[0];
    }

    public static Boolean loginAuth(String username, String password) throws SQLException {
        boolean flag;

        try (Connection conn = DBConnection.createConnection()) {
            String checkSql = "select name from users where username = ? and password =?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, username);
            checkStmt.setString(2, password);
            ResultSet rs = checkStmt.executeQuery();
            flag = rs.next();
            System.out.println("User Name: " + rs.getString(1) + " " + flag);
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

//    ***************** Testing purpose *****************

//    public static void main(String[] args) {
//        DBOperation dbo = new DBOperation();
//        dbo.getCurrentTime(101);
//    }

//    ***************** Testing purpose *****************
}

// Create Db connection
// String SQLQurey - like "select name, username from table"
// Create Statement st - Statement(limited queries can be run) / preparedStatement (Anytype of Query can be run)
// ResultSet rs = st.executeQuery();
// if(rs.next()){
//    String username = getString(2);
//    String name = getString(1);
//}
//return username;

