package org.example.UI;
import javax.swing.*;
import java.awt.*;

public class chatWindow extends ChatWindowBase {

     JScrollPane scrollPane;
     JPanel chatPanel , messagePanel, footer;
     JLabel messageName , messageText , messageTime;
     JTextField inputField;
     JButton sendButton;

    public chatWindow(String username) {

        setTitle("ChatHub - " + username);

        scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setPreferredSize(new Dimension(700, 300));

        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
//        chatPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        /* =====================
           MESSAGE AREA
        ===================== */

        messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBackground(new Color(38, 217, 92));

//      Adding Properties Of Message Panel
//        - Message Name
        messageName = new JLabel("Tilak");
//        messageName.setHorizontalAlignment(JLabel.LEFT);
        messageName.setFont(new Font("Arial", Font.PLAIN, 16));
        messagePanel.add(messageName);

//        - Message Text
        messageText = new JLabel("hello");
//        messageText.setHorizontalAlignment(JLabel.LEFT);
        messageText.setFont(new Font("Arial", Font.PLAIN, 26));
        messagePanel.add(messageText);

//        - Message Time
        messageTime = new JLabel("12:12");
//        messageTime.setHorizontalAlignment(JLabel.LEFT);
        messageTime.setFont(new Font("Arial", Font.PLAIN, 16));
        messagePanel.add(messageTime);

        chatPanel.add(messagePanel, BorderLayout.WEST);

        scrollPane.add(chatPanel);
        /* =====================
           FOOTER INPUT AREA
        ===================== */

        footer = new JPanel(new BorderLayout(8, 0));
//        footer.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        footer.setBackground(Color.WHITE);
        footer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        inputField = new JTextField();
        inputField.setBorder(new RoundedBorder(10));

        sendButton = new JButton("Send");
//        sendButton.addActionListener(e -> {
//            String msg = inputField.getText();
//            addMessage(msg, true);
//        });

        footer.add(inputField, BorderLayout.CENTER);
        footer.add(sendButton, BorderLayout.EAST);


        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(Box.createVerticalStrut(10));
        panel.add(footer, BorderLayout.SOUTH);
    }

    /* =====================
       MESSAGE BOX UI METHOD
       (Tu listener se yahi call karega)
    ===================== */

//    public void addMessage(String message, boolean isSelf) {
//
//        JPanel messageWrapper = new JPanel(new FlowLayout(
//                isSelf ? FlowLayout.RIGHT : FlowLayout.LEFT
//        ));
//        messageWrapper.setOpaque(false);
//
//        JLabel messageLabel = new JLabel("<html><p style='width:200px'>" + message + "</p></html>");
//        messageLabel.setOpaque(true);
//        messageLabel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
//
//        if (isSelf) {
//            messageLabel.setBackground(new Color(38, 217, 92));
//            messageLabel.setForeground(Color.WHITE);
//        } else {
//            messageLabel.setBackground(new Color(38, 217, 92));
//            messageLabel.setForeground(Color.WHITE);
//        }
//
//        messageWrapper.add(messageLabel);
//        messagePanel.add(messageWrapper);
//        messagePanel.add(Box.createVerticalStrut(5));
//
//        messagePanel.revalidate();
//        messagePanel.repaint();
//
//        JScrollBar vertical = scrollPane.getVerticalScrollBar();
//        vertical.setValue(vertical.getMaximum());
//    }
//
//    /* =====================
//       GETTERS (For Listener Use)
//    ===================== */
//
//    public JTextField getInputField() {
//        return inputField;
//    }
//
//    public JButton getSendButton() {
//        return sendButton;
//    }
    public static void main(String[] args) {
        new chatWindow("Tilak");
    }

}




