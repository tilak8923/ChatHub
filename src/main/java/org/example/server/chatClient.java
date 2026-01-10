package org.example.server;

import java.io.*;
import java.net.Socket;

public class chatClient{
// for creating socket , it always returns an object as a socket
    public static Socket createSocket(){
        Socket clientSocket = null;
        try{
            clientSocket = new Socket("localhost", 6000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return clientSocket;
    }

//  check client socket code in userLogin to understand what's going on Line no - 140


// ***************** Testing Purpose *****************
//    /*
    public static void main(String[] args) {

        String serverIp = "localhost"; // Railway URL later
        int port = 6000;

        try (Socket socket = new Socket(serverIp, port)) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true
            );

            BufferedReader keyboard = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            String input;
            while ((input = keyboard.readLine()) != null) {
                out.println(input);           // send to server
                System.out.println(in.readLine()); // receive from server
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    */
// ***************** Testing Purpose *****************
}

