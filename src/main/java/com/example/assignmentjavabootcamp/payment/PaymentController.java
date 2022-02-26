package com.example.assignmentjavabootcamp.payment;

import com.example.assignmentjavabootcamp.cart.CartItem;
import com.example.assignmentjavabootcamp.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    CartService cartService;

    @Autowired
    CreditCardGateway creditCardGateway;

    @PostMapping("/api/payments/credit-card")
    void payment(@RequestBody() CreditCardPaymentRequest request) {
        List<CartItem> currentUserCartItems = cartService.getCurrentUserCartItem();
        int amount = CartItem.totalPrice(currentUserCartItems);

        CreditCardPaymentGatewayRequest gatewayRequest = new CreditCardPaymentGatewayRequest();
        gatewayRequest.setAmount(amount);
        gatewayRequest.setCardNo(request.getCardNo());
        gatewayRequest.setCvv(request.getCvv());
        gatewayRequest.setExpiration_month(request.getExpiration_month());
        gatewayRequest.setExpiration_year(request.getExpiration_year());
        gatewayRequest.setName(request.getName());

        creditCardGateway.charge(gatewayRequest);
    }

}
