package com.example.assignmentjavabootcamp.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Test
    void getCurrentUserAddress() {
        // Arrange
        User currentUser = new User();
        currentUser.setId(1);

        Address address1 = new Address();
        address1.setId(1);
        address1.setAddress("that street");
        address1.setUser(currentUser);
        when(addressRepository.findByUser_Id(1)).thenReturn(address1);

        // Act
        UserService userService = new UserService();
        userService.setAddressRepository(addressRepository);
        Address actual = userService.getCurrentUserAddress();

        // Assert
        assertThat(actual, is(samePropertyValuesAs(address1, "user")));
    }
}