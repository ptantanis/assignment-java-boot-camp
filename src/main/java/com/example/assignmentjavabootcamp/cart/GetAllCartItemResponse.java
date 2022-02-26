package com.example.assignmentjavabootcamp.cart;

import java.util.List;

public class GetAllCartItemResponse {
    public GetAllCartItemResponse() {}

    public GetAllCartItemResponse(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    private List<CartItem> cartItems;
}
