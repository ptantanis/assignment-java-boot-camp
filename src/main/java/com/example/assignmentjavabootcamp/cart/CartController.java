package com.example.assignmentjavabootcamp.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/api/carts")
    void addProductToCart(@RequestBody() AddProductToCartRequest request) {
        cartService.addProduct(request.getProductId(), request.getSize());
    }

    @GetMapping("/api/carts")
    GetAllCartItemResponse getAllCurrentUserCartItem() {
        List<CartItem> cartItems = cartService.getCurrentUserCartItem();
        return new GetAllCartItemResponse(cartItems);
    }
}
