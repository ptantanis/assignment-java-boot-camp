package com.example.assignmentjavabootcamp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    AddressRepository addressRepository;

    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public User getCurrentUser() {
        User currentUser = new User();
        currentUser.setId(1);
        return currentUser;
    }

    public Address getCurrentUserAddress() {
        return addressRepository.findByUser_Id(getCurrentUser().getId());
    }
}
