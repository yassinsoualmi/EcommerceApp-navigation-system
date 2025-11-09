package com.electronicstore.ui;

import com.electronicstore.controllers.CartController;
import com.electronicstore.controllers.ProductController;
import com.electronicstore.models.Product;
import javax.swing.*;
import java.awt.*;
import java.util.List;


public class ProductPage extends JPanel {
    private MainWindow mainWindow;
    private ProductController productController;
    private CartController cartController;
    private JPanel productsPanel;

    
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color CARD_BG = Color.WHITE;
    private static final Color BORDER_COLOR = new Color(189, 195, 199);

    public ProductPage(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.productController = ProductController.getInstance();
        this.cartController = CartController.getInstance();

        setLayout(new BorderLayout());
        setBackground(new Color(236, 240, 241));

        createHeader();
        createProductsGrid();
    }

   
    private void createHeader() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        
        JLabel titleLabel = new JLabel("Our Products");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(44, 62, 80));

    
        JLabel subtitleLabel = new JLabel("Browse our collection of premium electronics");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(127, 140, 141));

        JPanel titlePanel = new JPanel(new GridLayout(2, 1, 0, 5));
        titlePanel.setOpaque(false);
        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        headerPanel.add(titlePanel, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);
    }

  
    private void createProductsGrid() {
        productsPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        productsPanel.setBackground(new Color(236, 240, 241));
        productsPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        List<Product> products = productController.getAllProducts();

        for (Product product : products) {
            JPanel productCard = createProductCard(product);
            productsPanel.add(productCard);
        }

        JScrollPane scrollPane = new JScrollPane(productsPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    
    private JPanel createProductCard(Product product) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

       
        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 0, 5));
        infoPanel.setOpaque(false);

        
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(new Color(44, 62, 80));

        
        JLabel descLabel = new JLabel("<html>" + product.getDescription() + "</html>");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(new Color(127, 140, 141));

        
        JLabel priceLabel = new JLabel("$" + String.format("%.2f", product.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        priceLabel.setForeground(PRIMARY_COLOR);

        JLabel stockLabel = new JLabel("In Stock: " + product.getStockQuantity() + " units");
        stockLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        stockLabel.setForeground(product.getStockQuantity() > 0 ? new Color(39, 174, 96) : new Color(231, 76, 60));

        infoPanel.add(nameLabel);
        infoPanel.add(descLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(stockLabel);

        JButton addToCartBtn = new JButton("🛒 Add to Cart");
        addToCartBtn.setFont(new Font("Arial", Font.BOLD, 14));
        addToCartBtn.setBackground(PRIMARY_COLOR);
        addToCartBtn.setForeground(Color.WHITE);
        addToCartBtn.setFocusPainted(false);
        addToCartBtn.setBorderPainted(false);
        addToCartBtn.setPreferredSize(new Dimension(0, 40));
        addToCartBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

     
        addToCartBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addToCartBtn.setBackground(new Color(30, 100, 150));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addToCartBtn.setBackground(PRIMARY_COLOR);
            }
        });

        addToCartBtn.addActionListener(e -> {
            cartController.addToCart(product, 1);
            mainWindow.updateCartBadge();

            JOptionPane.showMessageDialog(
                this,
                product.getName() + " added to cart!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        card.add(infoPanel, BorderLayout.CENTER);
        card.add(addToCartBtn, BorderLayout.SOUTH);

        return card;
    }
}
