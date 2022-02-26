package com.example.assignmentjavabootcamp.cart;

import com.example.assignmentjavabootcamp.ErrorResponse;
import com.example.assignmentjavabootcamp.product.Product;
import com.example.assignmentjavabootcamp.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static com.example.assignmentjavabootcamp.cart.CartTestHelper.generateCartItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private CartService cartService;

    @Test
    void add_product_to_card_success() {
        // Arrange
        int expectedProductId = 123;
        String expectedSize = "S";

        // Act
        AddProductToCartRequest body = new AddProductToCartRequest();
        body.setProductId(expectedProductId);
        body.setSize(expectedSize);

        ResponseEntity response = testRestTemplate.postForEntity("/api/carts" , body, String.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cartService).addProduct(expectedProductId, expectedSize);
    }

    @Test
    void add_product_return_error_product_not_found() {
        // Arrange
        int expectedProductId = 123;
        String expectedSize = "S";

        doThrow(new ProductNotFoundException(expectedProductId)).when(cartService).addProduct(expectedProductId, expectedSize);

        // Act
        AddProductToCartRequest body = new AddProductToCartRequest();
        body.setProductId(expectedProductId);
        body.setSize(expectedSize);

        ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity("/api/carts" , body, ErrorResponse.class);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertThat(response.getBody().getMessage(), is(containsString(String.valueOf(expectedProductId))));
    }

    @Test
    void add_product_return_error_product_size_not_found() {
        // Arrange
        int expectedProductId = 123;
        String expectedSize = "S";

        Product product = new Product();
        product.setId(expectedProductId);

        doThrow(new ProductSizeNotFoundException(product, expectedSize)).when(cartService).addProduct(expectedProductId, expectedSize);

        // Act
        AddProductToCartRequest body = new AddProductToCartRequest();
        body.setProductId(expectedProductId);
        body.setSize(expectedSize);

        ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity("/api/carts" , body, ErrorResponse.class);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertThat(response.getBody().getMessage(), is(containsString(String.valueOf(expectedProductId))));
        assertThat(response.getBody().getMessage(), is(containsString(expectedSize)));
    }

    @Test
    void get_all_cart_item_of_current_user() {
        // Arrange
        User user = new User();
        user.setId(1234);

        CartItem cartItem1 = generateCartItem(1, user);
        CartItem cartItem2 = generateCartItem(2, user);

        when(cartService.getCurrentUserCartItem()).thenReturn(Arrays.asList(cartItem1, cartItem2));

        // Act
        ResponseEntity<GetAllCartItemResponse> response = testRestTemplate.getForEntity("/api/carts" , GetAllCartItemResponse.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody().getCartItems(), is(hasSize(2)));
    }

}