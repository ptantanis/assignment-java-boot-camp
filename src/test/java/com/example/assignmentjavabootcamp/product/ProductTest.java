package com.example.assignmentjavabootcamp.product;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void hasSize_true() {
        // Arrange
        Product product = new Product();
        product.setSize(new LinkedHashSet<>(Arrays.asList("S", "M", "L")));

        // Act
        boolean result = product.hasSize("S");

        // Assert
        assertTrue(result);
    }

    @Test
    void hasSize_false() {
        // Arrange
        Product product = new Product();
        product.setSize(new LinkedHashSet<>(Arrays.asList("S", "M", "L")));

        // Act
        boolean result = product.hasSize("XL");

        // Assert
        assertFalse(result);
    }
}
