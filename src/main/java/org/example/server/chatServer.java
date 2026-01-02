package org.example.server;

import org.example.DataBaseConnection.DBConnection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class chatServer {
    static ServerSocket serverSocket = null;
//    function to start a server
//    temporary issi me login logic add hai
    public static void startServer(){
        int port = 6000;
        System.out.println("Starting server on port " + port);
        try {
            serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept(); // wait for client
            System.out.println("Client connected");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );    // ye in me message lega jo client ne bheja hai
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true
            );    // ye client ko message bhejega
            String msg = in.readLine();
            if(msg.startsWith("LOGIN")){
                String[] str = msg.split(" ");
                String username = str[1];
                String password = str[2];
                Boolean flag = loginAuth(username , password);  // ye jo function ye check karega DB me user hai ya nhi
                if(flag){
                    out.println("LOGIN_SUCCESS");  //sending to client
                    System.out.println("Server says: LOGIN_SUCCESS" + " "+ true);
                }
                else {
                    out.println("LOGIN_FAILED");   //sending to client
                    System.out.println("Server says: LOGIN_FAILED" + " "+ false);
                }
            }
            else {
                out.println("LOGIN_FAILED");
                System.out.println("Server says: LOGIN_FAILED");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Boolean loginAuth(String username, String password) {
        boolean flag;

        Connection conn = DBConnection.createConnection();
        String checkSql = "select name from users where username = ? and password =?";
        try {
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, username);
            checkStmt.setString(2, password);
            ResultSet rs = checkStmt.executeQuery();
            flag = rs.next();
            System.out.println("User Name: " + rs.getString(1)+" "+ flag);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }
// ***************** Testing Purpose *****************
    /*
    public static void main(String[] args) {
        int port = 6000;

        System.out.println("Server starting on port " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            Socket clientSocket = serverSocket.accept(); // wait for client
            System.out.println("Client connected");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true
            );

            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("Client: " + msg);
                out.println("Server received: " + msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
// ***************** Testing Purpose *****************
}
