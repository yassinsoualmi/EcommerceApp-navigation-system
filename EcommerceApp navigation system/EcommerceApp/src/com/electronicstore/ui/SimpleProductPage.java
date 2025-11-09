package com.electronicstore.ui;

import javax.swing.*;
import java.awt.*;

public class SimpleProductPage extends JPanel {
    
    public SimpleProductPage(SimpleMainWindow mainWindow) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        JLabel label = new JLabel("Products Page", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);
    }
}

