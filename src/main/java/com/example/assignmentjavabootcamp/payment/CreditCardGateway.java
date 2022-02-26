package com.example.assignmentjavabootcamp.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CreditCardGateway {
    @Autowired
    private RestTemplate restTemplate;

    private String paymentUrl;

    public CreditCardGateway(@Value("${payment.credit_card_url}") String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public void charge(CreditCardPaymentGatewayRequest request) throws CreditCardPaymentFailException {
        ResponseEntity result = restTemplate.postForEntity(paymentUrl + "/charge", request, null);

        if (result.getStatusCode() != HttpStatus.OK) {
            throw new CreditCardPaymentFailException();
        }
    }
}
