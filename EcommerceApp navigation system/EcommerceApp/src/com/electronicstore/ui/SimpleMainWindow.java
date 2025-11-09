package com.electronicstore.ui;

import com.electronicstore.controllers.CartController;
import com.electronicstore.controllers.ProductController;
import com.electronicstore.models.Product;
import javax.swing.*;
import java.awt.*;

public class SimpleMainWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private CartController cartController;

    public SimpleMainWindow() {
        cartController = CartController.getInstance();

        setTitle("Electronic Store");
        setSize(700, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        createNavigation();
        createContent();
    }

    private void createNavigation() {
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        navPanel.setBackground(Color.WHITE);
        navPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));

        JButton homeBtn = createNavButton("Home");
        JButton productsBtn = createNavButton("Products");
        JButton cartBtn = createNavButton("Cart");
        JButton checkoutBtn = createNavButton("Checkout");

        homeBtn.addActionListener(e -> showPage("detail"));
        productsBtn.addActionListener(e -> showPage("products"));
        cartBtn.addActionListener(e -> showPage("cart"));
        checkoutBtn.addActionListener(e -> showPage("login"));

        navPanel.add(homeBtn);
        navPanel.add(productsBtn);
        navPanel.add(cartBtn);
        navPanel.add(checkoutBtn);

        add(navPanel, BorderLayout.NORTH);
    }

    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 20));
        btn.setForeground(Color.BLACK);
        btn.setBackground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void createContent() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Get first product for detail page
        Product firstProduct = ProductController.getInstance().getAllProducts().get(0);

        mainPanel.add(new ProductDetailPage(this, firstProduct), "detail");
        mainPanel.add(new SimpleProductPage(this), "products");
        mainPanel.add(new SimpleCartPage(this), "cart");
        mainPanel.add(new SimpleLoginPage(this), "login");

        add(mainPanel, BorderLayout.CENTER);

        // Show detail page by default
        showPage("detail");
    }

    private void showPage(String page) {
        cardLayout.show(mainPanel, page);
        if (page.equals("cart")) {
            for (Component comp : mainPanel.getComponents()) {
                if (comp instanceof SimpleCartPage) {
                    ((SimpleCartPage) comp).refreshCartItems();
                }
            }
        }
    }

    public void updateCartBadge() {
        // Cart badge update if needed
    }
}

