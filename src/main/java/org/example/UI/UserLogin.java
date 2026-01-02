package org.example.UI;

import org.example.DataBaseConnection.DBConnection;
import org.example.server.chatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;
import java.util.Objects;

public class UserLogin extends BaseFrame {
    JLabel  forgetPassword;
    JLabel usernameLabel, passwordLabel;
    JTextField usernameTextField, passwordTextField;
    JButton login;
    JPanel signupWrapper;
    JLabel signUpLabel , signUp;

    public UserLogin() {

        header.setText("Login to your account");

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

//      passwordLabel
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        passwordLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        passwordLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fieldBlock.add(passwordLabel);
        fieldBlock.add(Box.createVerticalStrut(6));
//      passwordLabel

//      password
        passwordTextField = new JPasswordField();
        passwordTextField.setFont(new Font("Serif", Font.PLAIN, 32));
        passwordTextField.setBackground(new Color(244, 244, 244));
        passwordTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        passwordTextField.setBorder(new RoundedBorder(10));
        fieldBlock.add(passwordTextField);
        fieldBlock.add(Box.createVerticalStrut(6));
//      password

//      Forget Password
        forgetPassword = new JLabel("<html><u>Forget Password?</u></html>");
        forgetPassword.setForeground(Color.BLUE);
        forgetPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgetPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new forgetPasswordPage();
            }
        });
        fieldBlock.add(forgetPassword);
//      Forget Password

        fieldWrapper.add(fieldBlock, BorderLayout.WEST);
        panel.add(fieldBlock);
        panel.add(Box.createVerticalStrut(40));

//      Login  Setup
        login = new JButton("Login");
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        login.setFont(new Font("Serif", Font.BOLD, 38));

        login.addActionListener(e -> {
            String name = usernameTextField.getText();
            String pass = passwordTextField.getText();
            if (name.isEmpty() || pass.isEmpty()){
                new ErrorPage("Error!!","Please enter your username and password" , "Ok");
            }else {
                boolean flag = checkLogin(name , pass);
                if(flag) {
                    new popUpPage("LOGIN SUCCESSFUL" , "You’re all set and ready to start" ,"Open Chat" , name);
                    this.dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "User Not Exist");
            }
        });
        panel.add(login);
//      Login Setup

        panel.add(Box.createVerticalStrut(15));

//      SignUp Wrapper
        signupWrapper = new JPanel();
        signupWrapper.setBackground(Color.WHITE);
        signupWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
        signupWrapper.setFont(new Font("Serif", Font.PLAIN, 24));
//      SignUp Wrapper

//      SignUp Label
        signUpLabel = new JLabel("Don’t have an account?");
        signupWrapper.add(signUpLabel);
//      SignUp Label

//      SignUp Button
        signUp = new JLabel("<html><u>Sign Up</u></html>");
        signUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUp.setForeground(Color.BLUE);
        signUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new RegistrationPage().setVisible(true);
                dispose();
            }
        });
        signupWrapper.add(signUp);
//      SignUp Button

        panel.add(signupWrapper);
//      SignUp Setup

    }

    public static boolean checkLogin(String username, String password){
        boolean flag = false;
        try (Socket sc = chatClient.createSocket()) {      // Create a new socket by using createSocket func of chatClient class
            PrintWriter out = new PrintWriter(
                    sc.getOutputStream(), true
            );          // PrintWriter used hota h network pr output bhejne ke liye
            out.println("LOGIN" + " " + username + " " + password);  // mtlb ye server recieve karega
            BufferedReader in = new BufferedReader(new InputStreamReader(sc.getInputStream())); // ye use hota h input lene ke liye from network jaise scanner use hota tha
            if(in.readLine().equals("LOGIN_SUCCESS")){
                System.out.println("Client received: "+ in.readLine());
                flag = true;
            }if(in.readLine().equals("LOGIN_FAILED")){
                System.out.println("Client received: "+ in.readLine());
                flag = false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
