package org.example.UI;

import javax.swing.*;
import org.example.server.chatClient;

import java.awt.*;

public class chatWindow extends JFrame {

//    private final chatClient client;
//    private final JPanel scrollablePanel;
//    private final JPanel footer;
//
//    public chatWindow(String username) {
//        setTitle("ChatHub - " + username);
//        setSize(400, 500);
//        setLayout(new BorderLayout());
//
//        footer = new JPanel();
//        footer.setLayout(new FlowLayout());
//        scrollablePanel = new JPanel();
////        scrollablePanel.add()
//
//
//        client = new chatClient(username);
//        JTextArea area = new JTextArea();
////        area.setEditable(false);
//
//        JTextField input = new JTextField();
////        input.setSize(100, 30);
//        JButton sendBtn = new JButton("Send");
//
//        sendBtn.addActionListener(e -> {
//            String msg = input.getText();
//            client.sendMessage(username + ": " + msg);
//            input.setText("");
//        });
//
////        add(new JScrollPane(area), "Center");
//        add(scrollablePanel , BorderLayout.CENTER);
//        footer.add(input);
//        footer.add(sendBtn);
//
//        add(footer , BorderLayout.SOUTH);
//
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
    private final JPanel messagesPanel;
    private final JScrollPane scrollPane;
    private final JPanel footer;
    private final JTextField inputField;
    private final JButton sendButton;

    public chatWindow(String username) {

        setTitle("ChatHub - " + username);
        setSize(400, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        /* =====================
           MESSAGE AREA
        ===================== */

        messagesPanel = new JPanel();
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        messagesPanel.setBackground(Color.white);

        scrollPane = new JScrollPane(messagesPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        add(scrollPane, BorderLayout.CENTER);

        /* =====================
           FOOTER INPUT AREA
        ===================== */

        footer = new JPanel(new BorderLayout(8, 0));
        footer.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        inputField = new JTextField();

        sendButton = new JButton("Send");
        sendButton.addActionListener(e -> {
            String msg = inputField.getText();
            addMessage(msg, true);
        });

        footer.add(inputField, BorderLayout.CENTER);
        footer.add(sendButton, BorderLayout.EAST);

        add(footer, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /* =====================
       MESSAGE BOX UI METHOD
       (Tu listener se yahi call karega)
    ===================== */

    public void addMessage(String message, boolean isSelf) {

        JPanel messageWrapper = new JPanel(new FlowLayout(
                isSelf ? FlowLayout.RIGHT : FlowLayout.LEFT
        ));
        messageWrapper.setOpaque(false);

        JLabel messageLabel = new JLabel("<html><p style='width:200px'>" + message + "</p></html>");
        messageLabel.setOpaque(true);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        if (isSelf) {
            messageLabel.setBackground(new Color(38, 217, 92));
            messageLabel.setForeground(Color.WHITE);
        } else {
            messageLabel.setBackground(new Color(38, 217, 92));
            messageLabel.setForeground(Color.WHITE);
        }

        messageWrapper.add(messageLabel);
        messagesPanel.add(messageWrapper);
        messagesPanel.add(Box.createVerticalStrut(5));

        messagesPanel.revalidate();
        messagesPanel.repaint();

        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    /* =====================
       GETTERS (For Listener Use)
    ===================== */

    public JTextField getInputField() {
        return inputField;
    }

    public JButton getSendButton() {
        return sendButton;
    }

}




