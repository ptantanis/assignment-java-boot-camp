package com.example.assignmentjavabootcamp.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/api/users/current/addresses")
    UserAddressResponse getCurrentUserAddress() {
        Address address = userService.getCurrentUserAddress();
        return new UserAddressResponse(address);
    }
}
