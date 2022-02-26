package com.example.assignmentjavabootcamp.flow;

import com.example.assignmentjavabootcamp.product.Product;
import com.example.assignmentjavabootcamp.product.ProductRepository;
import com.example.assignmentjavabootcamp.product.SearchProductsResponse;
import org.junit.jupiter.api.BeforeEach;
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
public class PurchaseProductFlowTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        Product product1 = new Product(2134, "addidas", 1324);
        Product product2 = new Product(2135, "not addidas", 234);
        Product product3 = new Product(2136, "another", 432);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }

    @Test
    void purchase_product_flow() {
        ResponseEntity<SearchProductsResponse> response = testRestTemplate.getForEntity("/api/products?name=Addidas", SearchProductsResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Product> actualProducts = response.getBody().getProducts();
        assertThat(actualProducts, hasSize(2));
    }
}
