package com.electronicstore.ui;

import com.electronicstore.controllers.CartController;
import com.electronicstore.models.Product;
import javax.swing.*;
import java.awt.*;

public class ProductDetailPage extends JPanel {
    private SimpleMainWindow mainWindow;
    private CartController cartController;
    private Product product;
    private JSpinner quantitySpinner;

    public ProductDetailPage(SimpleMainWindow mainWindow, Product product) {
        this.mainWindow = mainWindow;
        this.product = product;
        this.cartController = CartController.getInstance();
        
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        createContent();
    }
    
    private void createContent() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        
        // Product name
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 48));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Spacing
        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(nameLabel);
        contentPanel.add(Box.createVerticalStrut(40));
        
        // Product card
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        cardPanel.setMaximumSize(new Dimension(600, 800));
        
        // Image placeholder
        JLabel imageLabel = new JLabel("🔧", SwingConstants.CENTER);
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 120));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setPreferredSize(new Dimension(400, 300));
        
        // Price
        JLabel priceLabel = new JLabel("$" + String.format("%.2f", product.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 36));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Description
        JLabel descLabel = new JLabel("<html>" + product.getDescription() + "</html>");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Quantity section
        JLabel qtyLabel = new JLabel("Quantity");
        qtyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        qtyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Quantity spinner
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, product.getStockQuantity(), 1);
        quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setFont(new Font("Arial", Font.PLAIN, 18));
        quantitySpinner.setMaximumSize(new Dimension(150, 50));
        quantitySpinner.setAlignmentX(Component.LEFT_ALIGNMENT);
        ((JSpinner.DefaultEditor) quantitySpinner.getEditor()).getTextField().setEditable(false);
        
        // Add to Cart button
        JButton addToCartBtn = new JButton("Add to Cart");
        addToCartBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        addToCartBtn.setBackground(new Color(230, 230, 230));
        addToCartBtn.setFocusPainted(false);
        addToCartBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartBtn.setMaximumSize(new Dimension(600, 60));
        addToCartBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        addToCartBtn.addActionListener(e -> {
            int quantity = (Integer) quantitySpinner.getValue();
            cartController.addToCart(product, quantity);
            mainWindow.updateCartBadge();
            JOptionPane.showMessageDialog(this, 
                "Added " + quantity + " x " + product.getName() + " to cart!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
        });
        
        // Add components to card
        cardPanel.add(imageLabel);
        cardPanel.add(Box.createVerticalStrut(30));
        cardPanel.add(priceLabel);
        cardPanel.add(Box.createVerticalStrut(15));
        cardPanel.add(descLabel);
        cardPanel.add(Box.createVerticalStrut(30));
        cardPanel.add(qtyLabel);
        cardPanel.add(Box.createVerticalStrut(10));
        cardPanel.add(quantitySpinner);
        cardPanel.add(Box.createVerticalStrut(30));
        cardPanel.add(addToCartBtn);
        
        contentPanel.add(cardPanel);
        
        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollPane, BorderLayout.CENTER);
    }
}

