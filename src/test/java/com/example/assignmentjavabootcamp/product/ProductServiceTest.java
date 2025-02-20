package com.example.assignmentjavabootcamp.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Test
    void findByName() {
        // Arrange
        Product expectedProduct = new Product(2134, "name with keyword", 1000);
        when(productRepository.findByNameContainsIgnoreCase("keyword")).thenReturn(Arrays.asList(expectedProduct));

        // Act
        ProductService service = new ProductService();
        service.setProductRepository(productRepository);

        List<Product> actualProducts = service.findByName("keyword");

        // Assert
        assertThat(actualProducts, hasSize(1));
        assertThat(actualProducts.get(0), is(samePropertyValuesAs(expectedProduct)));
    }

    @Test
    void findById() {
        // Arrange
        Product expectedProduct = new Product(2134, "name with keyword", 1000);
        when(productRepository.findById(2134)).thenReturn(Optional.of(expectedProduct));

        // Act
        ProductService service = new ProductService();
        service.setProductRepository(productRepository);

        Optional<Product> result = service.findById(2134);

        // Assert
        assertThat(result.isPresent(), is(true));
        assertThat(result.get(), is(samePropertyValuesAs(expectedProduct)));
    }
}