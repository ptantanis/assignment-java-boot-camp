package com.example.assignmentjavabootcamp.cart;

public class AddProductToCartRequest {

    private int productId;

    private String size;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
