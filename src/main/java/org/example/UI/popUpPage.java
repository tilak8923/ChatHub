package org.example.UI;

import javax.swing.*;
import java.awt.*;

public class popUpPage extends JFrame {
    JLabel header , descriptionLabel , img;
    JButton Button;
    JPanel panel;
    ImageIcon icon;
    public popUpPage(String message, String description, String buttonName){
        setSize(600, 450);
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        icon = new ImageIcon("img.png");
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(80,80, Image.SCALE_SMOOTH);

        img = new JLabel(new ImageIcon(resizedImage));
        img.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(img);
        panel.add(Box.createVerticalStrut(20));

        header = new JLabel(message);
        header.setFont(new Font("Serif", Font.PLAIN, 32));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(header);
        panel.add(Box.createVerticalStrut(70));

        descriptionLabel = new JLabel("<html><div style='text-align:center; width:380px;'>"
                + description +
                "</div></html>");
        descriptionLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(descriptionLabel);
        panel.add(Box.createVerticalStrut(30));

        Button = new JButton(buttonName);
        Button.setFont(new Font("Serif", Font.PLAIN, 32));
        Button.setBackground(new Color(0, 156, 32));
        Button.setForeground(Color.WHITE);
        Button.setOpaque(true);
        Button.setBorderPainted(false);
        Button.setAlignmentX(Component.CENTER_ALIGNMENT);

        Button.addActionListener(e ->new userlogin());

        panel.add(Button);
        panel.add(Box.createVerticalStrut(40));
//        System.out.println("ICON WIDTH = " + icon.getIconWidth());  // to check Image Width if it's -1 occurs problems
        add(panel,  BorderLayout.CENTER);
        setVisible(true);

    }
    public popUpPage(String message, String description, String buttonName , String name){
        setSize(600, 450);
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        icon = new ImageIcon("img.png");
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(80,80, Image.SCALE_SMOOTH);

        img = new JLabel(new ImageIcon(resizedImage));
        img.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(img);
        panel.add(Box.createVerticalStrut(20));

        header = new JLabel(message);
        header.setFont(new Font("Serif", Font.PLAIN, 32));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(header);
        panel.add(Box.createVerticalStrut(70));

        descriptionLabel = new JLabel("<html><div style='text-align:center; width:380px;'>"
                + description +
                "</div></html>");
        descriptionLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(descriptionLabel);
        panel.add(Box.createVerticalStrut(30));

        Button = new JButton(buttonName);
        Button.setFont(new Font("Serif", Font.PLAIN, 32));
        Button.setBackground(new Color(0, 156, 32));
        Button.setForeground(Color.WHITE);
        Button.setOpaque(true);
        Button.setBorderPainted(false);
        Button.setAlignmentX(Component.CENTER_ALIGNMENT);

        Button.addActionListener(e -> new chatWindow(name));

        panel.add(Button);
        panel.add(Box.createVerticalStrut(40));
        add(panel,  BorderLayout.CENTER);
        setVisible(true);
    }
//    ************************* For testing Purpose *************************

//    public static void main(String[] args) {
//        new popUpPage("REGISTERED SUCCESSFULLY","Your details have been successfully submitted \n" +
//                "You can now login","Login");
//        new popUpPage("LOGIN SUCCESSFUL" , "You’re all set and ready to start" ,"Open Chat" , "name");
//        new popUpPage("Password Changed!","You’ve successfully completed your\n" +
//                "password reset","Login");
//    }

//    ************************* For testing Purpose *************************
}
