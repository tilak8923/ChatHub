package org.example.server;

import java.io.PrintWriter;
import java.net.Socket;

public class chatClient extends Thread{
    private Socket socket;
    private PrintWriter writer;

    public chatClient(String var1) {
        try {
            this.socket = new Socket("localhost", 5000);
            this.writer = new PrintWriter(this.socket.getOutputStream(), true);
//            (new ClientListener(this.socket)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(String var1) {
        this.writer.println(var1);
    }
}

