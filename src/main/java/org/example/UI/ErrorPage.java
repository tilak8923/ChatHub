package org.example.UI;

import javax.swing.*;

public class ErrorPage extends popUpPage{
    public ErrorPage(String message, String description, String buttonName){
        super(message,description,buttonName);
        icon = new ImageIcon("logo.png");
        Button.addActionListener(e ->{
            this.dispose();
        });
    }
}
