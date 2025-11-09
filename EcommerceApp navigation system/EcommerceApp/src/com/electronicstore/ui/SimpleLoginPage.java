package com.electronicstore.ui;

import javax.swing.*;
import java.awt.*;

public class SimpleLoginPage extends JPanel {
    
    public SimpleLoginPage(SimpleMainWindow mainWindow) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        JLabel label = new JLabel("Login Page", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);
    }
}

