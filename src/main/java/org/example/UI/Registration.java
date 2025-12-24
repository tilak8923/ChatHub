package org.example.UI;

import org.example.DataBaseConnection.DBConnection;
import org.example.DataBaseConnection.DBOperation;


import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class Registration extends JFrame {
        private final JLabel usernameLabel , passwordLabel, nameLabel;
        private final JTextField username, password , name;
        private final JButton login , Register;
        private final JPanel panel;

    public Registration(){
        setSize(400, 500);
        setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setLayout(new GridLayout(4,2));

        nameLabel = new JLabel("Name");
        name = new JTextField(10);
        panel.add(nameLabel);
        panel.add(name);

        usernameLabel = new JLabel("Username");
        username = new JTextField(10);
        panel.add(usernameLabel);
        panel.add(username);

        passwordLabel = new JLabel("Password");
        password = new JTextField(10);
        panel.add(passwordLabel);
        panel.add(password);

        login = new JButton("Login");
        login.addActionListener(e -> new userlogin());
        panel.add(login);

        Register = new JButton("Register");
        Register.addActionListener(e -> {
            String name = username.getText();
            String pass = password.getText();
            if (name.isEmpty() || pass.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please Enter your Name, username and password");
            }else {
                addUser();
                JOptionPane.showMessageDialog(null, "User Registered Successfully");
                new userlogin();
                this.dispose();
            }
        });
        panel.add(Register);

        add(panel , BorderLayout.CENTER);
        setVisible(true);
    }
    public void addUser() {
        String Name = name.getText();
        String userName = username.getText();
        String  Password = password.getText();
        Connection conn = DBConnection.createConnection();

        DBOperation operation = new DBOperation();
        operation.insertUser(Name , userName, Password);
    }

}
