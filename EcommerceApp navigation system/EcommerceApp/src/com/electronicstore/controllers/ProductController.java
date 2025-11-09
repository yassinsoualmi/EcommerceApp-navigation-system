package com.electronicstore.controllers;

import com.electronicstore.models.Product;
import java.util.ArrayList;
import java.util.List;


public class ProductController {
    private List<Product> products;
    private static ProductController instance;

    
    private ProductController() {
        products = new ArrayList<>();
        initializeProducts();
    }

    
    public static ProductController getInstance() {
        if (instance == null) {
            instance = new ProductController();
        }
        return instance;
    }

    
    private void initializeProducts() {
        products.add(new Product(1, "Laptop", "High-performance laptop with 16GB RAM", 999.99, "Computers", 10));
        products.add(new Product(2, "Smartphone", "Latest smartphone with 5G support", 699.99, "Mobile", 25));
        products.add(new Product(3, "Headphones", "Wireless noise-canceling headphones", 199.99, "Audio", 50));
        products.add(new Product(4, "Tablet", "10-inch tablet with stylus support", 499.99, "Tablets", 15));
        products.add(new Product(5, "Smart Watch", "Fitness tracking smartwatch", 299.99, "Wearables", 30));
        products.add(new Product(6, "Wireless Mouse", "Ergonomic wireless mouse", 49.99, "Accessories", 100));
    }

    
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }


    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }


    public List<Product> getProductsByCategory(String category) {
        List<Product> categoryProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                categoryProducts.add(product);
            }
        }
        return categoryProducts;
    }

    
    public List<Product> searchProducts(String keyword) {
        List<Product> searchResults = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                product.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }
}
