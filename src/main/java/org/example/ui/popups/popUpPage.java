package org.example.ui.popups;

import org.example.DataBaseConnection.DBOperation;
import org.example.ui.UserLogin;
import org.example.ui.chatWindow;


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
            DBOperation obj = new DBOperation();

            new chatWindow(name, obj.getUserId(name));
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
