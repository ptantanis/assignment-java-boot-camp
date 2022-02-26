package com.example.assignmentjavabootcamp.cart;

import com.example.assignmentjavabootcamp.product.Product;

public class ProductSizeNotFoundException extends RuntimeException {
    public ProductSizeNotFoundException(Product product, String size) {
        super("Product with ID " + product.getId() + " size " + size + "not found");
    }
}
