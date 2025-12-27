package org.example.UI;

import org.example.DataBaseConnection.DBOperation;
import org.example.DataBaseConnection.Messages;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

import static org.example.DataBaseConnection.DBOperation.getUserId;

public class popUpPage extends PopUpBase {
    public popUpPage(String message, String description, String buttonName){
        super(message,description,buttonName);
        Button.addActionListener(e ->{
            new UserLogin();
            this.dispose();
        });
    }
    public popUpPage(String message, String description, String buttonName , String name){
        super(message,description,buttonName);
        Button.addActionListener(e -> {

            new chatWindow(name, DBOperation.getUserId(name));
            this.dispose();
        });
    }

//    ************************* For testing Purpose *************************

    public static void main(String[] args) {
        new popUpPage("REGISTERED SUCCESSFULLY","Your details have been successfully submitted \n" +
                "You can now login","Login");
        new popUpPage("LOGIN SUCCESSFUL" , "You’re all set and ready to start" ,"Open Chat" , "name");
        new popUpPage("Password Changed!","You’ve successfully completed your\n" +
                "password reset","Login");
    }

//    ************************* For testing Purpose *************************
}
