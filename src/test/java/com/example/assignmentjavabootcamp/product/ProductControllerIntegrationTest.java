package com.example.assignmentjavabootcamp.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void search_products_success_integration() {
        // Arrange
        Product expectedProduct = new Product(2134, "expectedProduct name", 1000);
        expectedProduct.setDescription("this is product description");
        expectedProduct.setSize(new LinkedHashSet<>(Arrays.asList("S", "M", "L")));
        expectedProduct.setPictureUrl("https://cataas.com/cat/orange");

        Product another = new Product(2135, "another product name", 1500);
        another.setDescription("this is another product description");
        another.setSize(new LinkedHashSet<>(Arrays.asList("S")));
        another.setPictureUrl("https://cataas.com/cat/small");

        productRepository.save(expectedProduct);
        productRepository.save(another);

        // Act
        ResponseEntity<SearchProductsResponse> response = testRestTemplate.getForEntity("/api/products?name=expected", SearchProductsResponse.class);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Product> actualProducts = response.getBody().getProducts();
        assertThat(actualProducts, hasSize(1));
        assertThat(actualProducts.get(0), is(samePropertyValuesAs(expectedProduct)));
    }
}
