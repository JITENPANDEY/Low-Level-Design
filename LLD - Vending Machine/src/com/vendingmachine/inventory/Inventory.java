package com.vendingmachine.inventory;

import com.vendingmachine.dto.Product;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<Product, Integer> productStock;

    public Inventory() {
        this.productStock = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        productStock.put(product, productStock.getOrDefault(product, 0) + quantity);
    }

    public int getQuantity(Product product) {
        return productStock.getOrDefault(product, 0);
    }

    public boolean isProductAvailable(Product product) {
        return getQuantity(product) > 0;
    }

    public void removeProduct(Product product, int quantity) {
        if (isProductAvailable(product)) {
            int currentQuantity = getQuantity(product);
            if (currentQuantity >= quantity) {
                productStock.put(product, currentQuantity - quantity);
            } else {
                throw new IllegalArgumentException("Not enough stock for product: " + product.getName());
            }
        } else {
            throw new IllegalArgumentException("Product not available: " + product.getName());
        }
    }

    public void reduceQuantity(Product product) {
        removeProduct(product, 1);
    }
}
