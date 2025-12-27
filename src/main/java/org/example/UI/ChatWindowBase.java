package org.example.UI;

import javax.swing.*;
import java.awt.*;

public class ChatWindowBase extends JFrame {
    protected JLabel header;
    protected final JPanel panel , wrapper;
    public ChatWindowBase() {
        setSize(1080, 1000);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(244, 244, 244));

        panel = new RoundedPanel(20);
//        panel.setMinimumSize(new Dimension(750, 450));
        panel.setPreferredSize(new Dimension(750, 450));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        panel.setBackground(new Color(255, 255, 255));



        wrapper.add(panel);
        add(wrapper, BorderLayout.CENTER);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {
        new ChatWindowBase();
    }
}
