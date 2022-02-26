package com.example.assignmentjavabootcamp.product;

import java.util.List;

public class SearchProductsResponse {
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    private List<Product> products;

}
