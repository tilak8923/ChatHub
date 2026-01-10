package org.example.UI.error;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class ErrorUi extends JFrame {

    JLabel titleLabel, iconLabel;
    JButton tryAgainBtn;
    JTextPane textPane;

    public ErrorUi(String title, String message, String buttonText , String type) {

        setSize(550, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);

        // ===== Main Card Panel =====
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(new EmptyBorder(10, 40, 10, 30));

        // ===== LEFT ICON =====
        ImageIcon icon = new ImageIcon("logo.png");
        Image scaled = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        iconLabel = new JLabel(new ImageIcon(scaled));
        iconLabel.setBorder(new EmptyBorder(0, 0, 0, 20));

        cardPanel.add(iconLabel, BorderLayout.WEST);

        // ===== CENTER CONTENT =====
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setPreferredSize(new Dimension(400, 400));

        titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titleLabel.setForeground(setColor(type));

        contentPanel.add(titleLabel);

//      ========== Message ==============
        // 1. Use JTextPane instead of JTextArea for styling support
        textPane = new JTextPane();
        textPane.setText(message);
        textPane.setFont(new Font("Serif", Font.PLAIN, 16));
        textPane.setEditable(false);
        textPane.setBorder(new EmptyBorder(15, 10, 15, 10));

        // 2. Apply Center Alignment Style
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        // 3. Set up the Panel
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(textPane, BorderLayout.CENTER);

//      ========== Message ==============

        contentPanel.add(messagePanel);

        cardPanel.add(contentPanel, BorderLayout.CENTER);

        // ===== BUTTON (BOTTOM RIGHT) =====
        tryAgainBtn = new JButton(buttonText);
        tryAgainBtn.setFont(new Font("Serif", Font.BOLD, 18));
        tryAgainBtn.setBackground(new Color(0, 132, 198));
        tryAgainBtn.setForeground(Color.WHITE);
        tryAgainBtn.setFocusPainted(false);
        tryAgainBtn.setBorderPainted(false);
        tryAgainBtn.setOpaque(true);

        tryAgainBtn.addActionListener(e -> this.dispose());

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(tryAgainBtn);

        cardPanel.add(btnPanel, BorderLayout.SOUTH);
        add(cardPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    public Color setColor(String type){
        return switch (type) {
            case "error" -> Color.RED;
            case "warning" -> new Color(0xFFAC19);
            case "info" -> Color.BLUE;
            case "success" -> Color.GREEN;
            default -> null;
        };
    }

    //    ************************* For testing Purpose *************************
//    public static void main(String[] args) {
//        new ErrorUi("ERROR ‼", "User Not Exist", "Register", "error");
//
//        new ErrorUi("Warning ⚠️", "Incorrect Username or Password ", "Try Again", "warning");
//
//        new ErrorUi("Information", "Incorrect Username or Password ", "Try Again", "info");
//
//        new ErrorUi("All Set" , "You’re all set and ready to start" , "Open Chat" , "success");
//    }
    //    ************************* For testing Purpose *************************
}
