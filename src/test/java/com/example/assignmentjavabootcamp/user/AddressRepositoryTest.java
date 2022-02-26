package com.example.assignmentjavabootcamp.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void findByUser_Id() {
        // Arrange
        User currentUser = new User();
        currentUser.setId(1);
        User anotherUser = new User();
        anotherUser.setId(2);

        userRepository.save(currentUser);
        userRepository.save(anotherUser);

        Address address1 = new Address();
        address1.setId(1);
        address1.setAddress("that street");
        address1.setUser(currentUser);

        Address address2 = new Address();
        address2.setId(2);
        address2.setAddress("those street");
        address2.setUser(anotherUser);

        addressRepository.save(address1);
        addressRepository.save(address2);

        // Act
        Address address = addressRepository.findByUser_Id(currentUser.getId());

        // Assert
        assertThat(address, is(samePropertyValuesAs(address1, "user")));
    }
}