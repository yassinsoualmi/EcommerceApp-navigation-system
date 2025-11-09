package com.electronicstore.ui;

import com.electronicstore.controllers.CartController;
import javax.swing.*;
import java.awt.*;

public class LoginPage extends JPanel {
    private MainWindow mainWindow;
    private CartController cartController;
    private JTextField usernameField;
    private JPasswordField passwordField;


    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SUCCESS_COLOR = new Color(39, 174, 96);
    private static final Color ERROR_COLOR = new Color(231, 76, 60);

    public LoginPage(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.cartController = CartController.getInstance();

        setLayout(new BorderLayout());
        setBackground(new Color(236, 240, 241));

        createLoginForm();
    }

    
    private void createLoginForm() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(40, 50, 40, 50)
        ));
        formPanel.setPreferredSize(new Dimension(500, 600));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        JLabel iconLabel = new JLabel("🔐", SwingConstants.CENTER);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(iconLabel, gbc);

        
        JLabel titleLabel = new JLabel("Login to Checkout", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(44, 62, 80));
        gbc.gridy = 1;
        formPanel.add(titleLabel, gbc);

        
        JLabel subtitleLabel = new JLabel("Please enter your credentials to continue", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(127, 140, 141));
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 10, 20, 10);
        formPanel.add(subtitleLabel, gbc);

        
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 5, 10);
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(userLabel, gbc);

        
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        usernameField = new JTextField(25);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setPreferredSize(new Dimension(0, 40));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(usernameField, gbc);

        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(15, 10, 5, 10);
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(passLabel, gbc);

    
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 10, 10);
        passwordField = new JPasswordField(25);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setPreferredSize(new Dimension(0, 40));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        formPanel.add(passwordField, gbc);

        
        gbc.gridy = 7;
        gbc.insets = new Insets(10, 10, 10, 10);
        JCheckBox rememberCheckBox = new JCheckBox("Remember me");
        rememberCheckBox.setFont(new Font("Arial", Font.PLAIN, 12));
        rememberCheckBox.setOpaque(false);
        formPanel.add(rememberCheckBox, gbc);

        
        gbc.gridy = 8;
        gbc.insets = new Insets(20, 10, 10, 10);
        JButton loginBtn = new JButton("🔓 Login & Complete Order");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        loginBtn.setBackground(SUCCESS_COLOR);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorderPainted(false);
        loginBtn.setPreferredSize(new Dimension(0, 50));
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        
        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginBtn.setBackground(new Color(30, 150, 80));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginBtn.setBackground(SUCCESS_COLOR);
            }
        });

       
        loginBtn.addActionListener(e -> handleLogin());
        formPanel.add(loginBtn, gbc);

        
        gbc.gridy = 9;
        gbc.insets = new Insets(20, 10, 10, 10);
        JSeparator separator = new JSeparator();
        formPanel.add(separator, gbc);

        
        gbc.gridy = 10;
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel registerLabel = new JLabel("<html><center>Don't have an account?<br/><u>Register here</u></center></html>", SwingConstants.CENTER);
        registerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        registerLabel.setForeground(PRIMARY_COLOR);
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(
                    LoginPage.this,
                    "Registration feature coming soon!",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        formPanel.add(registerLabel, gbc);

        
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(236, 240, 241));
        centerPanel.add(formPanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        
        if (username.isEmpty()) {
            showError("Please enter your username");
            usernameField.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            showError("Please enter your password");
            passwordField.requestFocus();
            return;
        }

        if (cartController.isEmpty()) {
            showError("Your cart is empty! Please add items before checkout.");
            return;
        }


        if (username.equals("demo") && password.equals("demo")) {
            import javax.swing.*;

public class TestGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Window");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel label = new JLabel("If you see this, Java GUI works!", SwingConstants.CENTER);
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        frame.add(label);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        System.out.println("Window should be visible now!");
    }
}


            double total = cartController.getTotalPrice();
            int result = JOptionPane.showConfirmDialog(
                this,
                String.format("Complete order for $%.2f?", total),
                "Confirm Order",
                JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(
                    this,
                    "Order placed successfully!\nThank you for your purchase!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );

                
                cartController.clearCart();
                mainWindow.updateCartBadge();
                usernameField.setText("");
                passwordField.setText("");
            }
        } else {
            showError("Invalid username or password!\n\nHint: Use 'demo' / 'demo'");
        }
    }

    
    private void showError(String message) {
        JOptionPane.showMessageDialog(
            this,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
