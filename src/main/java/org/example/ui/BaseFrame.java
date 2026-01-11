package org.example.ui;

import org.example.ui.utils.RoundedPanel;

import javax.swing.*;
import java.awt.*;
// This is the Actual Frame of Application Where User Register/Login and can Update their Password
public class BaseFrame extends JFrame {
    protected JLabel header;
    protected final JPanel panel , wrapper;
    protected JPanel  fieldBlock , fieldWrapper;
    public BaseFrame(){
        setTitle("ChatHub");
        setSize(1080, 1000);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(new Color(244, 244, 244));

        panel = new RoundedPanel(20);
        panel.setMinimumSize(new Dimension(750, 450));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        panel.setBackground(new Color(255, 255, 255));

        header = new JLabel();
        header.setFont(new Font("Serif", Font.BOLD, 48));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(header);
        panel.add(Box.createVerticalStrut(60));

//      FieldWrapper
        fieldWrapper = new JPanel(new BorderLayout());
        fieldWrapper.setBackground(Color.WHITE);
        fieldWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

//      FieldBlock: Initializing Field Block That aligned Fields on the left side
        fieldBlock = new JPanel();
        fieldBlock.setLayout(new BoxLayout(fieldBlock, BoxLayout.Y_AXIS));
        fieldBlock.setBackground(new Color(255, 255, 255));

//      Field Block

        wrapper.add(panel);
        add(wrapper, BorderLayout.CENTER);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
