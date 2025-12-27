package org.example.UI;

import javax.swing.*;
import java.awt.*;

public class Testing extends ChatWindowBase {

    JScrollPane scrollPane;
    JPanel chatPanel, messagePanel, footer;
    JLabel messageName, messageText, messageTime;
    JTextField inputField;
    JButton sendButton;

    public Testing(String username) {

        setTitle("ChatHub - " + username);

        /* =====================
           CHAT PANEL (MAIN)
        ===================== */
        chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());

        /* =====================
           MESSAGE PANEL (SCROLLABLE)
        ===================== */
        messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBackground(Color.WHITE);

        // Sample message
        JPanel singleMessage = new JPanel();
        singleMessage.setLayout(new BoxLayout(singleMessage, BoxLayout.Y_AXIS));
        singleMessage.setBackground(new Color(38, 217, 92));
        singleMessage.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        messageName = new JLabel("Tilak");
        messageName.setFont(new Font("Arial", Font.PLAIN, 14));

        messageText = new JLabel("Hello");
        messageText.setFont(new Font("Arial", Font.BOLD, 18));

        messageTime = new JLabel("12:12");
        messageTime.setFont(new Font("Arial", Font.PLAIN, 12));

        singleMessage.add(messageName);
        singleMessage.add(messageText);
        singleMessage.add(messageTime);

        messagePanel.add(singleMessage);
        messagePanel.add(Box.createVerticalStrut(8));

        messagePanel.add(singleMessage);
        scrollPane = new JScrollPane(messagePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        /* =====================
           FOOTER
        ===================== */
        footer = new JPanel(new BorderLayout(8, 0));
        footer.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        footer.setBackground(Color.WHITE);

        inputField = new JTextField();
        inputField.setBorder(new RoundedBorder(10));

        sendButton = new JButton("Send");

        footer.add(inputField, BorderLayout.CENTER);
        footer.add(sendButton, BorderLayout.EAST);

        /* =====================
           ADD TO BASE PANEL
        ===================== */
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(footer, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new Testing("Tilak").setVisible(true);
    }
}
