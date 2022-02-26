package com.example.assignmentjavabootcamp.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getCurrentUser() {
        User currentUser = new User();
        currentUser.setId(1);
        return currentUser;
    }
}
