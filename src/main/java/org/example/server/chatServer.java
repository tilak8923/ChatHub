package org.example.server;

import org.example.DataBaseConnection.DBOperation;
import org.example.DataBaseConnection.Messages;

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

            BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
            // ye in me message lega jo client ne bheja hai

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            // ye client ko message bhejega

            String msg = in.readLine();
            if(msg != null && msg.startsWith("LOGIN")){
                String[] str = msg.split("\\|");
                String username = str[1];
                String password = str[2];
                boolean flag = DBOperation.loginAuth(username , password);  // ye jo function ye check karega DB me user hai ya nhi
                if(flag){
                    out.println("LOGIN_SUCCESS");  //sending to client
                    System.out.println("Server says: LOGIN_SUCCESS" + " "+ true);  // for Debugging
                }
                else {
                    out.println("LOGIN_FAILED");   //sending to client
                    System.out.println("Server says: LOGIN_FAILED" + " "+ false);
                }
            }
            else if (msg != null && msg.startsWith("REGISTER")){
                String[] str = msg.split("\\|");
                String name = str[1];
                String username = str[2];
                String password = str[3];
                boolean flag = DBOperation.insertUser(name, username, password);
                if(flag){
                    out.println("REGISTER_SUCCESS");  //sending to client
                    System.out.println("Server says: REGISTER_SUCCESS" + " "+ true);
                }
                else {
                    out.println("REGISTER_FAILED");   //sending to client
                    System.out.println("Server says: REGISTER_FAILED" + " "+ false);
                }
            }
            else if(msg != null && msg.startsWith("FORGOT_PASSWORD")){
                String[] str = msg.split("\\|");
                String username = str[1];
                String newPassword = str[2];
                boolean flag = DBOperation.updatePassword(username, newPassword);
                if(flag){
                    out.println("UPDATE_SUCCESS");
                    System.out.println("Server says: UPDATE_SUCCESS" + " " + true);
                }
                else{
                    out.println("UPDATE_FAILED");
                    System.out.println("Server says: UPDATE_FAILED" + " " + false);
                }
            }else if(msg != null && msg.startsWith("SEND_MESSAGE")){
                String[] str = msg.split("\\|");
                int user_id = Integer.parseInt(str[1]);
                String message = str[2];
                String time = str[3];

                String name = DBOperation.getName(user_id);
                System.out.println("User Name : " + name);
                Boolean flag = Messages.sendMessage(user_id, message, time);
                if(flag){
                    out.println("SEND_SUCCESS" + "|" + name);
                    System.out.println("Server says: SEND_SUCCESS" + " "+ true);
                }else  {
                    out.println("SEND_FAILED" + "|" + name);
                }
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
