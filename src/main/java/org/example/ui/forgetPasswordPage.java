package org.example.ui;

import org.example.DataBaseConnection.DBConnection;
import org.example.server.chatClient;
import org.example.ui.popups.ErrorUi;
import org.example.ui.popups.popUpPage;
import org.example.ui.utils.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;

public class forgetPasswordPage extends BaseFrame {
    JLabel usernameLabel, newPasswordLabel;
    JTextField usernameTextField, newPasswordTextField;
    JButton updatePassword;

    public forgetPasswordPage() {

        header.setText("Forgot Your Password?");

//      usernameLabel
        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        usernameLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        usernameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fieldBlock.add(usernameLabel);
        fieldBlock.add(Box.createVerticalStrut(6));
//      usernameLabel
//      username
        usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Serif", Font.PLAIN, 32));
        usernameTextField.setBackground(new Color(244, 244, 244));
        usernameTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        usernameTextField.setBorder(new RoundedBorder(10));
        fieldBlock.add(usernameTextField);
        fieldBlock.add(Box.createVerticalStrut(50));
//      username

//      newPasswordLabel
        newPasswordLabel = new JLabel("New Password");
        newPasswordLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        newPasswordLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        newPasswordLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fieldBlock.add(newPasswordLabel);
        fieldBlock.add(Box.createVerticalStrut(6));
//      newPasswordLabel

//      password
        newPasswordTextField = new JPasswordField();
        newPasswordTextField.setFont(new Font("Serif", Font.PLAIN, 32));
        newPasswordTextField.setBackground(new Color(244, 244, 244));
        newPasswordTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        newPasswordTextField.setBorder(new RoundedBorder(10));
        fieldBlock.add(newPasswordTextField);
        fieldBlock.add(Box.createVerticalStrut(6));
//      password


        fieldWrapper.add(fieldBlock, BorderLayout.WEST);
        panel.add(fieldBlock);
        panel.add(Box.createVerticalStrut(40));

//      Login  Setup
        updatePassword = new JButton("Reset My Password");
        updatePassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        updatePassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        updatePassword.setFont(new Font("Serif", Font.BOLD, 38));

        updatePassword.addActionListener(e -> {
            System.out.println("BUTTON CLICKED");
            String username = usernameTextField.getText();
            String newPass = newPasswordTextField.getText();
            if (username.isEmpty()){
                if(newPass.isEmpty()){
                    new ErrorUi("Warning⚠️","Please enter your username and password","Try Again","warning");
                }else {
                    new ErrorUi("Warning⚠️", "Please enter your username", "Try Again", "warning");
                }
            }else if (newPass.isEmpty()){
                new ErrorUi("Warning⚠️","Please enter password","Try Again","warning");
            }else {
                new SwingWorker<Boolean, Void>() {

                    @Override
                    protected Boolean doInBackground() {
                        return resetPass(username , newPass); // network call
                    }
                    @Override
                    protected void done() {
                        try {
                            boolean flag = get(); // result from background
                            if (flag) {
                                new popUpPage("Password Changed!","You’ve successfully completed your" +"password reset","Login");
//                                new ErrorUi("All Set", "You’re all set and ready to start", "Open Chat", "success");
                                dispose();
                            } else
                                new ErrorUi("Error", "Password Not Update", "Try Again", "error");
                        } catch (Exception e) {
                            e.printStackTrace();
                            new ErrorUi("Error","Server not responding","Try Again", "error");
                        }
                    }
                }.execute();
            }
        });
        panel.add(updatePassword);
//      Login Setup

        panel.add(Box.createVerticalStrut(15));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    public Boolean resetPass(String username , String newPass){
        boolean flag = false;
        try (Socket sc = chatClient.createSocket()) {
            PrintWriter out = new PrintWriter(sc.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(sc.getInputStream()));

            out.println("FORGET_PASSWORD" + "|" + username + "|" + newPass);

            String response = in.readLine();
            if("UPDATE_SUCCESS".equals(response)){
                System.out.println("Client received: "+ response);
                flag = true;
            }else if("UPDATE_FAILED".equals(response)){
                System.out.println("Client received: "+ response);
            }
        }catch (Exception e){e.printStackTrace();}
        return flag;
    }
}
