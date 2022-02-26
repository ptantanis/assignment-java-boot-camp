package com.example.assignmentjavabootcamp.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByName() {
        // Arrange
        Product expectedProduct = new Product(2134, "name with keyword", 100);
        Product another = new Product(234, "another name", 100);

        productRepository.save(expectedProduct);
        productRepository.save(another);


        // Act
        List<Product> actualProducts = productRepository.findByNameContainsIgnoreCase("keyword");

        // Assert
        assertThat(actualProducts, hasSize(1));
        assertThat(actualProducts.get(0), is(samePropertyValuesAs(expectedProduct)));
    }
}