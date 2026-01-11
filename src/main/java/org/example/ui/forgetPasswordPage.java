package org.example.ui;

import org.example.DataBaseConnection.DBConnection;
import org.example.ui.popups.popUpPage;
import org.example.ui.utils.RoundedBorder;

import javax.swing.*;
import java.awt.*;
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
            String name = usernameTextField.getText();
            String pass = newPasswordTextField.getText();
            if (name.isEmpty() || pass.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter your username and password");
            }else {
                resetPass(name , pass);
            }
        });
        panel.add(updatePassword);
//      Login Setup

        panel.add(Box.createVerticalStrut(15));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    public void resetPass(String name , String pass){
        String Sql = "Update users set password = ? where username = ?";
        try {
            Connection conn = DBConnection.createConnection();
            PreparedStatement checkStmt = conn.prepareStatement(Sql);
            checkStmt.setString(1, pass);
            checkStmt.setString(2, name);
            int rows = checkStmt.executeUpdate();
            if (rows > 0) {
                new popUpPage("Password Changed!","You’ve successfully completed your\n" +
                        "password reset","Login");
            }
            else {
                JOptionPane.showMessageDialog(null, "Failed to reset your password");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
