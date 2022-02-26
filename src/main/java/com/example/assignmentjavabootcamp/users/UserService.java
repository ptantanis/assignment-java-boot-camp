package com.example.assignmentjavabootcamp.users;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getCurrentUser() {
        User currentUser = new User();
        currentUser.setId(1);
        return currentUser;
    }
}
