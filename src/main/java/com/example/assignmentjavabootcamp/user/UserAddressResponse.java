package com.example.assignmentjavabootcamp.user;

public class UserAddressResponse {
    public UserAddressResponse() {
    }

    public UserAddressResponse(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    Address address;
}
