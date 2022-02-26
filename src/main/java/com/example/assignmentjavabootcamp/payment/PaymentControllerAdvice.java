package com.example.assignmentjavabootcamp.payment;

import com.example.assignmentjavabootcamp.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentControllerAdvice {
    @ExceptionHandler(CreditCardPaymentFailException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse paymentFail(CreditCardPaymentGatewayRequest e) {
        return new ErrorResponse("Fail to pay with credit card");
    }
}
