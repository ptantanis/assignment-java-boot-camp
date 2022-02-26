package com.example.assignmentjavabootcamp.cart;

import com.example.assignmentjavabootcamp.product.Product;
import com.example.assignmentjavabootcamp.user.User;

public class CartTestHelper {
    public static CartItem generateCartItem(int id, User user) {
        Product product = new Product(id, "Product" + id, id * 100);
        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setSize("S");
        cartItem.setProduct(product);
        cartItem.setUser(user);
        return cartItem;
    }
}
