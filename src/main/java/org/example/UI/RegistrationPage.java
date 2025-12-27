package org.example.UI;

import org.example.DataBaseConnection.DBConnection;
import org.example.DataBaseConnection.DBOperation;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class RegistrationPage extends BaseFrame {
        JLabel usernameLabel , passwordLabel, nameLabel;
        JTextField usernameTextField, passwordTextField , nameTextField;
        JButton Register;
        JPanel signInWrapper;
        JLabel signInLabel, signIn;

    public RegistrationPage(){

//      Header
        header.setText("Register To Continue");
//      Header

//      FieldWrapper
        fieldWrapper = new JPanel(new BorderLayout());
        fieldWrapper.setBackground(Color.WHITE);
        fieldWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

//      FieldBlock: Initializing Field Block That aligned Fields in the left side
        fieldBlock = new JPanel();
        fieldBlock.setLayout(new BoxLayout(fieldBlock, BoxLayout.Y_AXIS));
        fieldBlock.setBackground(new Color(255, 255, 255));

//      Field Block


//      nameLabel
        nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        nameLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        nameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fieldBlock.add(nameLabel);
        fieldBlock.add(Box.createVerticalStrut(6));
//      usernameLabel

//      name TextField
        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Serif", Font.PLAIN, 32));
        nameTextField.setBackground(new Color(244, 244, 244));
        nameTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        nameTextField.setBorder(new RoundedBorder(10));
        fieldBlock.add(nameTextField);
        fieldBlock.add(Box.createVerticalStrut(50));
//      name TextField

//      usernameLabel
        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        usernameLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        usernameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fieldBlock.add(usernameLabel);
        fieldBlock.add(Box.createVerticalStrut(6));
//      usernameLabel

//      username TextField
        usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Serif", Font.PLAIN, 32));
        usernameTextField.setBackground(new Color(244, 244, 244));
        usernameTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        usernameTextField.setBorder(new RoundedBorder(10));
        fieldBlock.add(usernameTextField);
        fieldBlock.add(Box.createVerticalStrut(50));
//      username TextField

//      passwordLabel
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        passwordLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        passwordLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fieldBlock.add(passwordLabel);
        fieldBlock.add(Box.createVerticalStrut(6));
//      passwordLabel

//      Password TextField
        passwordTextField = new JPasswordField();
        passwordTextField.setFont(new Font("Serif", Font.PLAIN, 32));
        passwordTextField.setBackground(new Color(244, 244, 244));
        passwordTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        passwordTextField.setBorder(new RoundedBorder(10));
        fieldBlock.add(passwordTextField);
        fieldBlock.add(Box.createVerticalStrut(6));
//      password TextField

        fieldWrapper.add(fieldBlock, BorderLayout.WEST);
        panel.add(fieldBlock);
        panel.add(Box.createVerticalStrut(40));

//      Registration Setup

        Register = new JButton("Register");
        Register.setAlignmentX(Component.CENTER_ALIGNMENT);
        Register.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        Register.setFont(new Font("Serif", Font.BOLD, 38));

        Register.addActionListener(e -> {
            String name = usernameTextField.getText();
            String pass = passwordTextField.getText();
            if (name.isEmpty() || pass.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please Enter your Name, username and password");
            }else {
                addUser();
                new popUpPage("REGISTERED SUCCESSFULLY","Your details have been successfully submitted \n" +
                        "You can now login","Login");
                this.dispose();
            }
        });
        panel.add(Register);
//      Registration Setup

        panel.add(Box.createVerticalStrut(15));


//      **SignIn Setup**
//      SignIn Wrapper
        signInWrapper = new JPanel();
        signInWrapper.setBackground(Color.WHITE);
        signInWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
        signInWrapper.setFont(new Font("Serif", Font.PLAIN, 24));
//      SignIn Wrapper


//      SignIn Label
        signInLabel = new JLabel("Already Registered?");
        signInWrapper.add(signInLabel);
//      SignIn Label

//      SignIn Button
        signIn = new JLabel("<html><u>Sign In</u></html>");
        signIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signIn.setForeground(Color.BLUE);
        signIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new UserLogin().setVisible(true);
                dispose();
            }
        });
        signInWrapper.add(signIn);
//      SignIn Button
        panel.add(signInWrapper);
//      **SignIn Setup**

//        wrapper.add(panel);
//        add(wrapper, BorderLayout.CENTER);
//        setVisible(true);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void addUser() {
        String Name = nameTextField.getText();
        String userName = usernameTextField.getText();
        String  Password = passwordTextField.getText();
        Connection conn = DBConnection.createConnection();

        DBOperation operation = new DBOperation();
        operation.insertUser(Name , userName, Password);
    }
}
