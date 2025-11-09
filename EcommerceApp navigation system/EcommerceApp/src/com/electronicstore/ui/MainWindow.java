package com.electronicstore.ui;

import com.electronicstore.controllers.CartController;
import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JButton homeButton, productsButton, cartButton, checkoutButton;
    private JLabel cartBadge;
    private CartController cartController;

    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private static final Color ACCENT_COLOR = new Color(231, 76, 60);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color HOVER_COLOR = new Color(30, 100, 150);

    public MainWindow() {
        cartController = CartController.getInstance();

        
        setTitle("Electronic Store - Your One-Stop Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        try {
            setIconImage(Toolkit.getDefaultToolkit().createImage(""));
        } catch (Exception e) {
            
        }

        createHeader();
        createNavigationBar();
        createMainPanel();
        createFooter();
    }

    
    private void createHeader() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(0, 60));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        
        JLabel titleLabel = new JLabel("⚡ Electronic Store");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(TEXT_COLOR);

       
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setOpaque(false);

        JTextField searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setToolTipText("Search for products...");

        JButton searchButton = new JButton("🔍 Search");
        searchButton.setBackground(SECONDARY_COLOR);
        searchButton.setForeground(TEXT_COLOR);
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(searchPanel, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);
    }

    
    private void createNavigationBar() {
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        navPanel.setBackground(SECONDARY_COLOR);
        navPanel.setPreferredSize(new Dimension(0, 60));

        
        homeButton = createNavButton("🏠 Home", "Go to home page");
        productsButton = createNavButton("📦 Products", "Browse all products");
        cartButton = createNavButton("🛒 Cart", "View shopping cart");
        checkoutButton = createNavButton("💳 Checkout", "Proceed to checkout");

       
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setOpaque(false);
        cartBadge = new JLabel("0");
        cartBadge.setFont(new Font("Arial", Font.BOLD, 12));
        cartBadge.setForeground(TEXT_COLOR);
        cartBadge.setBackground(ACCENT_COLOR);
        cartBadge.setOpaque(true);
        cartBadge.setHorizontalAlignment(SwingConstants.CENTER);
        cartBadge.setPreferredSize(new Dimension(20, 20));
        cartBadge.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        cartPanel.add(cartButton, BorderLayout.CENTER);
        cartPanel.add(cartBadge, BorderLayout.EAST);

        navPanel.add(homeButton);
        navPanel.add(productsButton);
        navPanel.add(cartPanel);
        navPanel.add(checkoutButton);

       
        JPanel navContainer = new JPanel(new BorderLayout());
        navContainer.add(navPanel, BorderLayout.CENTER);
        add(navContainer, BorderLayout.NORTH);

        
        homeButton.addActionListener(e -> navigateTo("PRODUCTS", homeButton));
        productsButton.addActionListener(e -> navigateTo("PRODUCTS", productsButton));
        cartButton.addActionListener(e -> navigateTo("CART", cartButton));
        checkoutButton.addActionListener(e -> navigateTo("LOGIN", checkoutButton));
    }

    
    private JButton createNavButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(TEXT_COLOR);
        button.setBackground(SECONDARY_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(140, 40));
        button.setToolTipText(tooltip);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(SECONDARY_COLOR);
            }
        });

        return button;
    }

   
    private void navigateTo(String page, JButton activeButton) {
        cardLayout.show(mainPanel, page);

        
        updateCartBadge();

        
        resetButtonStyles();
        activeButton.setBackground(PRIMARY_COLOR);
    }

    
    private void resetButtonStyles() {
        homeButton.setBackground(SECONDARY_COLOR);
        productsButton.setBackground(SECONDARY_COLOR);
        cartButton.setBackground(SECONDARY_COLOR);
        checkoutButton.setBackground(SECONDARY_COLOR);
    }

   
    public void updateCartBadge() {
        int itemCount = cartController.getTotalItems();
        cartBadge.setText(String.valueOf(itemCount));
        cartBadge.setVisible(itemCount > 0);
    }

    
    private void createMainPanel() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(Color.WHITE);

        ProductPage productPage = new ProductPage(this);
        CartPage cartPage = new CartPage(this);
        LoginPage loginPage = new LoginPage(this);

        mainPanel.add(productPage, "PRODUCTS");
        mainPanel.add(cartPage, "CART");
        mainPanel.add(loginPage, "LOGIN");

        cardLayout.show(mainPanel, "PRODUCTS");
        add(mainPanel, BorderLayout.CENTER);

     
        homeButton.setBackground(PRIMARY_COLOR);
    }

  
    private void createFooter() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(44, 62, 80));
        footerPanel.setPreferredSize(new Dimension(0, 40));

        JLabel footerLabel = new JLabel("© 2025 Electronic Store | Contact: support@electronicstore.com | Help");
        footerLabel.setForeground(Color.LIGHT_GRAY);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);
    }
}