package com.example.assignmentjavabootcamp.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/api/carts")
    void addProductToCart(@RequestBody() AddProductToCartRequest request) {
        cartService.addProduct(request.getProductId(), request.getSize());
    }
}
