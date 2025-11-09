package com.electronicstore.ui;

import javax.swing.*;
import java.awt.*;

public class SimpleCartPage extends JPanel {
    
    public SimpleCartPage(SimpleMainWindow mainWindow) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        JLabel label = new JLabel("Cart Page", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);
    }
    
    public void refreshCartItems() {
        // Refresh cart
    }
}

