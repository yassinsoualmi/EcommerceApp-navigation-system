package com.electronicstore.models;

/**
 * CartItem model class representing an item in the shopping cart
 * Follows proper naming conventions and encapsulation principles
 */
public class CartItem {
    private Product product;
    private int quantity;

    // Constructor
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Calculate subtotal for this cart item
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return product.getName() + " - $" + String.format("%.2f", product.getPrice()) + " x " + quantity;
    }
}