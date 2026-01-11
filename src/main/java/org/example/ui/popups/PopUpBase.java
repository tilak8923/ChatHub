package org.example.ui.popups;

import javax.swing.*;
import java.awt.*;

public class PopUpBase extends JFrame {
    JLabel header , descriptionLabel , img;
    JButton Button;
    JPanel panel;
    ImageIcon icon;
    public PopUpBase(String message, String description, String buttonName){
        setSize(600, 450);
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setLocationRelativeTo(null);
//        setResizable(false);

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


        panel.add(Button);
        panel.add(Box.createVerticalStrut(40));

        add(panel,  BorderLayout.CENTER);
        setVisible(true);

    }
    public static void main(String[] args) {
        new PopUpBase("hello" , "this is error"  , "ok");
    }
}
