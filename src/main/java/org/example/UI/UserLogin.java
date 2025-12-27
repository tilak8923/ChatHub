package org.example.UI;

import org.example.DataBaseConnection.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

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
                JOptionPane.showMessageDialog(null, "Please enter your username and password");
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
        Connection conn = DBConnection.createConnection();
        String checkSql = "select name from users where username = ? and password =?";
        try {
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, username);
            checkStmt.setString(2, password);
            ResultSet rs = checkStmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
