package org.example.ui;
import org.example.DataBaseConnection.DBOperation;
import org.example.DataBaseConnection.Messages;
import org.example.ui.utils.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

public class chatWindow extends ChatWindowBase {

     JScrollPane scrollPane;
     JPanel messagePanel, footer;
     JLabel messageName ,messageTime;
     JTextArea messageText;
     JTextField inputField;
     JButton sendButton;
     JPanel messageWrapper;
     protected String userName;
     protected int user_id;

    public chatWindow(String username , int user_id) {
        System.out.println("Chat Window Created");
        this.user_id = user_id;
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

        footer = new JPanel(new BorderLayout(8, 0));
//        footer.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        footer.setBackground(Color.WHITE);
        footer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        inputField = new JTextField();
        inputField.setBorder(new RoundedBorder(10));

        sendButton = new JButton("Send");
        sendButton.addActionListener(e -> {
            DBOperation obj = new DBOperation();
            String name = obj.getName(user_id);
            String text = inputField.getText();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String time = sdf.format(new Date());

            Messages.sendMessage(user_id, text , time);
            String[] arr = time.split(" ");
            addMessage(name,text, arr[1]); // here addMessage add this msg in UI where we need only time
        });
//        here we take Name from users by user_id that is get in parameterized constructor
//        and then sendMessage with this data user_id , msg and time

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
//
//    public static void main(String[] args) {
//        new chatWindow("t" , 1).setVisible(true);
//    }
//
}




