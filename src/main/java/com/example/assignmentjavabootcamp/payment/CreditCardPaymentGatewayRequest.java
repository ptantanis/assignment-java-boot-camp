package com.example.assignmentjavabootcamp.payment;

public class CreditCardPaymentGatewayRequest {

    private String cardNo;

    private String name;

    private String cvv;

    private int amount;

    private int expiration_month;

    private int expiration_year;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getExpiration_month() {
        return expiration_month;
    }

    public void setExpiration_month(int expiration_month) {
        this.expiration_month = expiration_month;
    }

    public int getExpiration_year() {
        return expiration_year;
    }

    public void setExpiration_year(int expiration_year) {
        this.expiration_year = expiration_year;
    }

}
