package com.example.assignmentjavabootcamp.cart;

import com.example.assignmentjavabootcamp.ErrorResponse;
import com.example.assignmentjavabootcamp.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
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

}