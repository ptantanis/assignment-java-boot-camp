package com.example.assignmentjavabootcamp.products;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ProductService productService;

    @Test
    void search_products_success() {
        // Arrange
        Product expectedProduct = new Product();
        expectedProduct.setId(2134);
        expectedProduct.setName("expectedProduct name");

        List<Product> expectedProducts = Arrays.asList(expectedProduct);
        when(productService.findByName("test")).thenReturn(expectedProducts);

        // Act
        ResponseEntity<SearchProductsResponse> response = testRestTemplate.getForEntity("/api/products?name=test", SearchProductsResponse.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody().getProducts().get(0), is(samePropertyValuesAs(expectedProduct)));
    }
}