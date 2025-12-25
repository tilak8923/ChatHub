package org.example.DataBaseConnection;

import com.mysql.cj.protocol.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Messages {
    private String Name;
    private String time;
    private String message;
    private int user_id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public static void fetchMessage(){

        List <Messages>clients = new ArrayList<>();


        Connection conn = DBConnection.createConnection();
        String sql = "select u.name , c.message , c.chat_time from users u , chatdata c\n" +
                "where u.user_id = c.user_id;";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet st = stmt.executeQuery();
            while(st.next()){
                Messages obj = new Messages();
                obj.setName(st.getString("name"));
                obj.setTime(st.getString("chat_time"));
                obj.setMessage(st.getString("message"));
                clients.add(obj);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        for(Messages n : clients){
            System.out.println(n.message);
            System.out.println(n.Name);
            System.out.println(n.time);
        }
    }
//    insert - chatData - message , time, user_id

    public static void sendMessage(int user_id, String message ,String time ){
        try {
            String query = "Insert into chatdata(user_id , message, chat_time) values(?,?,?)";
            Connection con = DBConnection.createConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, user_id);
            stmt.setString(2, message);
            stmt.setString(3, time);

            int rows = stmt.executeUpdate();
            if(rows > 0){
                System.out.println("Message Sends Successfully");
            }else {
                System.out.println("Failed To Send Message");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }
    public static void main(String[] args){
        Messages.fetchMessage();
        Messages.sendMessage(2,"Hii Guys" , "2025-12-25T16:29");
    }
}



