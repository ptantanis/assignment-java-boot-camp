package com.example.assignmentjavabootcamp.cart;

import com.example.assignmentjavabootcamp.product.Product;
import com.example.assignmentjavabootcamp.product.ProductRepository;
import com.example.assignmentjavabootcamp.users.User;
import com.example.assignmentjavabootcamp.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@DataJpaTest
class CartItemRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Test
    void findByUser_Id() {
        // Arrange
        User currentUser = new User();
        currentUser.setId(1);
        User anotherUser = new User();
        anotherUser.setId(2);

        userRepository.save(currentUser);
        userRepository.save(anotherUser);

        CartItem cartItem1 = generateCartItem(1, currentUser);
        CartItem cartItem2 = generateCartItem(2, currentUser);
        CartItem cartItem3 = generateCartItem(3, anotherUser);

        productRepository.save(cartItem1.getProduct());
        productRepository.save(cartItem2.getProduct());
        productRepository.save(cartItem3.getProduct());

        cartItemRepository.save(cartItem1);
        cartItemRepository.save(cartItem2);
        cartItemRepository.save(cartItem3);

        // Act
        List<CartItem> cartItems = cartItemRepository.findByUser_Id(1);

        // Assert
        assertThat(cartItems, is(hasSize(2)));
    }

    private CartItem generateCartItem(int id, User user) {
        Product product = new Product(id, "Product" + id, id * 100);
        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setSize("S");
        cartItem.setProduct(product);
        cartItem.setUser(user);
        return cartItem;
    }
}