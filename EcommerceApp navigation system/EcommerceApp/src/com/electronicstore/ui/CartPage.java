package com.electronicstore.ui;

import com.electronicstore.controllers.CartController;
import com.electronicstore.models.CartItem;
import javax.swing.*;
import java.awt.*;
import java.util.List;


 
public class CartPage extends JPanel {
    private MainWindow mainWindow;
    private CartController cartController;
    private JPanel cartItemsPanel;
    private JLabel totalLabel;
    private JLabel emptyCartLabel;

  
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color ACCENT_COLOR = new Color(231, 76, 60);
    private static final Color SUCCESS_COLOR = new Color(39, 174, 96);

    public CartPage(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.cartController = CartController.getInstance();

        setLayout(new BorderLayout());
        setBackground(new Color(236, 240, 241));

        createHeader();
        createCartContent();
        createFooter();
    }

   
    private void createHeader() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("🛒 Shopping Cart");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(44, 62, 80));

        headerPanel.add(titleLabel, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);
    }

    
    private void createCartContent() {
        cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        cartItemsPanel.setBackground(new Color(236, 240, 241));
        cartItemsPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

       
        emptyCartLabel = new JLabel("<html><div style='text-align: center;'>" +
            "<h2>Your cart is empty</h2>" +
            "<p>Add some products to get started!</p></div></html>");
        emptyCartLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        emptyCartLabel.setForeground(new Color(127, 140, 141));
        emptyCartLabel.setHorizontalAlignment(SwingConstants.CENTER);

        refreshCartItems();

        JScrollPane scrollPane = new JScrollPane(cartItemsPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);
    }

    
    public void refreshCartItems() {
        cartItemsPanel.removeAll();

        List<CartItem> items = cartController.getCartItems();

        if (items.isEmpty()) {
            cartItemsPanel.add(emptyCartLabel);
        } else {
            for (CartItem item : items) {
                JPanel itemCard = createCartItemCard(item);
                cartItemsPanel.add(itemCard);
                cartItemsPanel.add(Box.createVerticalStrut(10));
            }
        }

        updateTotal();
        cartItemsPanel.revalidate();
        cartItemsPanel.repaint();
    }

    
    private JPanel createCartItemCard(CartItem item) {
        JPanel card = new JPanel(new BorderLayout(15, 0));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        
        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 0, 5));
        infoPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(item.getProduct().getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel priceLabel = new JLabel("$" + String.format("%.2f", item.getProduct().getPrice()) + " each");
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        priceLabel.setForeground(new Color(127, 140, 141));

        JLabel subtotalLabel = new JLabel("Subtotal: $" + String.format("%.2f", item.getSubtotal()));
        subtotalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        subtotalLabel.setForeground(SUCCESS_COLOR);

        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(subtotalLabel);

        
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        quantityPanel.setOpaque(false);

        JButton decreaseBtn = new JButton("-");
        JLabel quantityLabel = new JLabel(String.valueOf(item.getQuantity()));
        JButton increaseBtn = new JButton("+");

        styleQuantityButton(decreaseBtn);
        styleQuantityButton(increaseBtn);
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 16));
        quantityLabel.setPreferredSize(new Dimension(40, 30));
        quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);

        decreaseBtn.addActionListener(e -> {
            int newQty = item.getQuantity() - 1;
            cartController.updateQuantity(item.getProduct().getId(), newQty);
            refreshCartItems();
            mainWindow.updateCartBadge();
        });

        increaseBtn.addActionListener(e -> {
            int newQty = item.getQuantity() + 1;
            cartController.updateQuantity(item.getProduct().getId(), newQty);
            refreshCartItems();
            mainWindow.updateCartBadge();
        });

        quantityPanel.add(new JLabel("Quantity: "));
        quantityPanel.add(decreaseBtn);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(increaseBtn);

    
        JButton removeBtn = new JButton("🗑️ Remove");
        removeBtn.setFont(new Font("Arial", Font.BOLD, 12));
        removeBtn.setBackground(ACCENT_COLOR);
        removeBtn.setForeground(Color.WHITE);
        removeBtn.setFocusPainted(false);
        removeBtn.setBorderPainted(false);
        removeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        removeBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Remove " + item.getProduct().getName() + " from cart?",
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                cartController.removeFromCart(item.getProduct().getId());
                refreshCartItems();
                mainWindow.updateCartBadge();
            }
        });

        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        rightPanel.setOpaque(false);
        rightPanel.add(quantityPanel, BorderLayout.CENTER);
        rightPanel.add(removeBtn, BorderLayout.EAST);

        card.add(infoPanel, BorderLayout.WEST);
        card.add(rightPanel, BorderLayout.EAST);

        return card;
    }

    
    private void styleQuantityButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(40, 30));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    
    private void createFooter() {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(189, 195, 199)),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 24));
        totalLabel.setForeground(new Color(44, 62, 80));

        JButton checkoutBtn = new JButton("💳 Proceed to Checkout");
        checkoutBtn.setFont(new Font("Arial", Font.BOLD, 16));
        checkoutBtn.setBackground(SUCCESS_COLOR);
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.setFocusPainted(false);
        checkoutBtn.setBorderPainted(false);
        checkoutBtn.setPreferredSize(new Dimension(250, 50));
        checkoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        footerPanel.add(totalLabel, BorderLayout.WEST);
        footerPanel.add(checkoutBtn, BorderLayout.EAST);

        add(footerPanel, BorderLayout.SOUTH);
    }

    
    private void updateTotal() {
        double total = cartController.getTotalPrice();
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }
}
