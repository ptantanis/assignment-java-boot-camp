package com.example.assignmentjavabootcamp.products;

import com.jayway.jsonpath.Criteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByName() {
        // Arrange
        Product expectedProduct = new Product(2134, "name with keyword");
        Product another = new Product(234, "another name");

        productRepository.save(expectedProduct);
        productRepository.save(another);


        // Act
        List<Product> actualProducts = productRepository.findByNameContainsIgnoreCase("keyword");

        // Assert
        assertThat(actualProducts, hasSize(1));
        assertThat(actualProducts.get(0), is(samePropertyValuesAs(expectedProduct)));
    }
}