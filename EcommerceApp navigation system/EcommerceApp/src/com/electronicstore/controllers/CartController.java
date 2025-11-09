package com.electronicstore.controllers;

import com.electronicstore.models.CartItem;
import com.electronicstore.models.Product;
import java.util.ArrayList;
import java.util.List;


public class CartController {
    private List<CartItem> cartItems;
    private static CartController instance;

    
    private CartController() {
        cartItems = new ArrayList<>();
    }

    
    public static CartController getInstance() {
        if (instance == null) {
            instance = new CartController();
        }
        return instance;
    }


    public void addToCart(Product product, int quantity) {
        
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        
        cartItems.add(new CartItem(product, quantity));
    }

    
    public void removeFromCart(int productId) {
        cartItems.removeIf(item -> item.getProduct().getId() == productId);
    }


    public void updateQuantity(int productId, int newQuantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == productId) {
                if (newQuantity <= 0) {
                    removeFromCart(productId);
                } else {
                    item.setQuantity(newQuantity);
                }
                return;
            }
        }
    }

    
    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    
    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getSubtotal();
        }
        return total;
    }


    public int getTotalItems() {
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getQuantity();
        }
        return total;
    }

    
    public void clearCart() {
        cartItems.clear();
    }


    public boolean isEmpty() {
        return cartItems.isEmpty();
    }
}
