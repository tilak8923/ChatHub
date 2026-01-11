package org.example.server;

import org.example.DataBaseConnection.DBOperation;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class chatServer {
    static ServerSocket serverSocket = null;
//    function to start a server
//    temporary issi me login logic add hai
    private static final int port = 6000;
    public static void startServer(){
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
            if(msg != null && msg.startsWith("LOGIN")){
                String[] str = msg.split(" ");
                String username = str[1];
                String password = str[2];
                Boolean flag = DBOperation.loginAuth(username , password);  // ye jo function ye check karega DB me user hai ya nhi
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
        }
        catch (IOException e) { e.printStackTrace(); }
        catch (SQLException e) { throw new RuntimeException(e); }
    }

// ***************** Testing Purpose *****************
    /*
    public static void main(String[] args) {

        System.out.println("Server starting on port " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket clientSocket = serverSocket.accept(); // wait for client
            System.out.println("Client connected");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("Client: " + msg);
                out.println("Server received: " + msg);
            }
        } catch (IOException e) {e.printStackTrace(); }
    }
    */
// ***************** Testing Purpose *****************
}
