package org.example.UI;
import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.time.LocalTime;

public class chatWindow extends ChatWindowBase {

     JScrollPane scrollPane;
     JPanel messagePanel, footer;
     JLabel messageName ,messageTime;
     JTextArea messageText;
     JTextField inputField;
     JButton sendButton;
     JPanel messageWrapper;
     protected String userName;

    public chatWindow(String username) {
        this.userName = username;

        setTitle("ChatHub - " + username);


        /* =====================
           MESSAGE AREA
        ===================== */
            messagePanel = new JPanel();
            messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
            messagePanel.setBackground(new Color(255, 255, 255));


//        for (int i = 0 ; i<10 ;i++) {
//            messageWrapper = new JPanel();
//            messageWrapper.setLayout(new BoxLayout(messageWrapper, BoxLayout.Y_AXIS));
//            messageWrapper.setBackground(new Color(239, 238, 238, 238));
//            messageWrapper.setBorder(new RoundedBorder(10));
//            messageWrapper.setMinimumSize(new Dimension(Integer.MAX_VALUE, 40));
//
//
////      Adding Properties Of Message Panel
////        - Message Name
//            messageName = new JLabel("Tilak");
//            messageName.setFont(new Font("Arial", Font.PLAIN, 10));
//
////        - Message Text
//            messageText = new JTextArea("hey what's up , this is tilak tiwari , I'm writing this message toI'm writing this message to,I'm writing this message to show the ");
////            messageText = new JTextArea("hey what's up ");
//            messageText.setFont(new Font("Arial", Font.PLAIN, 12));
//            messageText.setLineWrap(true);
//            messageText.setWrapStyleWord(true);
//            messageText.setEditable(false);
//            messageText.setOpaque(false); // Makes it look like a JLabel
//            messageText.setAlignmentX(Component.LEFT_ALIGNMENT);
//
////        - Message Time
//            messageTime = new JLabel("12:12");
//            messageTime.setFont(new Font("Arial", Font.PLAIN, 9));
//
//
//            messageWrapper.add(messageName);
//            messageWrapper.add(messageText);
//            messageWrapper.add(messageTime);
//
//
//            messagePanel.add(messageWrapper);
//            messagePanel.add(Box.createVerticalStrut(10));
//        }

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
        sendButton.addActionListener(e -> {
            String name = getUserName();
            String text = inputField.getText();
            String time = getCurrentTime();
            addMessage(name,text, time);
        });

        footer.add(inputField, BorderLayout.CENTER);
        footer.add(sendButton, BorderLayout.EAST);


        scrollPane = new JScrollPane(messagePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scrollPane);
        panel.add(Box.createVerticalStrut(10));
        panel.add(footer);
    }

    protected String getCurrentTime() {
        return LocalTime.now().toString();
    }

    protected String getUserName() {
        return userName;
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

    public void addMessage(String msg , String text , String time) {
        messageWrapper = new JPanel();
        messageWrapper.setLayout(new BoxLayout(messageWrapper, BoxLayout.Y_AXIS));
        messageWrapper.setBackground(new Color(239, 238, 238, 238));
        messageWrapper.setBorder(new RoundedBorder(10));
        messageWrapper.setMinimumSize(new Dimension(Integer.MAX_VALUE, 20));


//      Adding Properties Of Message Panel
//        - Message Name
        messageName = new JLabel(msg);
        messageName.setFont(new Font("Arial", Font.PLAIN, 10));

//        - Message Text
        messageText = new JTextArea(text);
        messageText.setFont(new Font("Arial", Font.PLAIN, 12));
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEditable(false);
        messageText.setOpaque(false);
        messageText.setAlignmentX(Component.LEFT_ALIGNMENT);

//        - Message Time
        messageTime = new JLabel(time);
        messageTime.setFont(new Font("Arial", Font.PLAIN, 9));

        messageWrapper.add(messageName);
        messageWrapper.add(messageText);
        messageWrapper.add(messageTime);

        messagePanel.add(messageWrapper);
        messagePanel.add(Box.createVerticalStrut(10));

        messagePanel.revalidate();
        messagePanel.repaint();
    }
//    public static void main(String[] args) {
//        new chatWindow("Sanskriti").setVisible(true);
//    }

}




