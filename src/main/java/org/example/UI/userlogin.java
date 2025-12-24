package org.example.UI;

import org.example.DataBaseConnection.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class userlogin extends javax.swing.JFrame {
    JLabel header;
    JLabel loginLabel, passwordLabel;
    JTextField username, password;
    JButton login, Register;
    JPanel panel, wrapper, fieldBlock;

    public userlogin() {
        setSize(1512, 980);
        setLayout(new BorderLayout(1089, 679));

        wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(244, 244, 244));


        panel = new RoundedPanel(20);
        panel.setPreferredSize(new Dimension(1088, 679));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        panel.setBackground(new Color(255, 255, 255));

        header = new JLabel("Login To Your Account");
        header.setFont(new Font("Serif", Font.BOLD, 48));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(header);
        panel.add(Box.createVerticalStrut(60));

//      FieldBlock: Initializing Field Block That aligned Fields in the left side
        fieldBlock = new JPanel();
        fieldBlock.setLayout(new BoxLayout(fieldBlock, BoxLayout.Y_AXIS));
        fieldBlock.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fieldBlock.setBackground(new Color(255, 255, 255));
//      Field Block

//      loginLabel
        loginLabel = new JLabel("Username");
        loginLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        fieldBlock.add(loginLabel);
        fieldBlock.add(Box.createVerticalStrut(6));
//      loginLabel
//      username
        username = new JTextField();
        username.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        username.setBorder(new RoundedBorder(10));
        fieldBlock.add(username);
        fieldBlock.add(Box.createVerticalStrut(20));
//      username

//      passwordLabel
        passwordLabel = new JLabel("Password");
        passwordLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        fieldBlock.add(passwordLabel);
        fieldBlock.add(Box.createVerticalStrut(6));
//      passwordLabel

//      password
        password = new JPasswordField();
        password.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        password.setBorder(new RoundedBorder(10));
        fieldBlock.add(password);
        fieldBlock.add(Box.createVerticalStrut(25));
//      password

        panel.add(fieldBlock);
        panel.add(Box.createVerticalStrut(60));


        login = new JButton("Login");
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setMaximumSize(new Dimension(Integer.MAX_VALUE, 58));
        login.setFont(new Font("Serif", Font.BOLD, 48));

        login.addActionListener(e -> {
            String name = username.getText();
            String pass = password.getText();
            if (name.isEmpty() || pass.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter your username and password");
            }else {
                boolean flag = checkLogin(name , pass);
                if(flag) {
                    new chatWindow(name).setVisible(true);
                    this.dispose();
                }
                else
                    JOptionPane.showMessageDialog(null, "User Not Exist");
            }

        });

        panel.add(login);
        panel.add(Box.createVerticalStrut(15));

        Register = new JButton("Sign Up");
        Register.setAlignmentX(Component.CENTER_ALIGNMENT);

        Register.addActionListener(e -> {
            new Registration();
            this.dispose();
        });
        panel.add(Register);

        wrapper.add(panel);
        add(wrapper, BorderLayout.CENTER);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
