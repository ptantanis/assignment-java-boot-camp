package com.example.assignmentjavabootcamp.cart;

import com.example.assignmentjavabootcamp.product.Product;
import com.example.assignmentjavabootcamp.product.ProductService;
import com.example.assignmentjavabootcamp.users.User;
import com.example.assignmentjavabootcamp.users.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private ProductService productService;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private UserService userService;

    @Test
    void add_product_to_cart_throw_product_not_found() {
        // Arrange
        int productId = 123;
        when(productService.findById(productId)).thenReturn(Optional.empty());

        // Act
        CartService cartService = new CartService();
        cartService.setProductService(productService);

        // Assert
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class,() -> cartService.addProduct(productId, "S"));

        assertThat(exception.getMessage(), is(containsString(String.valueOf(productId))));
    }

    @Test
    void add_product_to_cart_throw_product_size_not_found() {
        // Arrange
        int productId = 123;
        Product product = new Product(productId, "product name", 1234);
        product.setSize(new LinkedHashSet<>(Arrays.asList("M", "L")));
        when(productService.findById(productId)).thenReturn(Optional.of(product));

        // Act
        CartService cartService = new CartService();
        cartService.setProductService(productService);

        // Assert
        ProductSizeNotFoundException exception = assertThrows(ProductSizeNotFoundException.class,() -> cartService.addProduct(productId, "S"));

        assertThat(exception.getMessage(), is(containsString(String.valueOf(productId))));
        assertThat(exception.getMessage(), is(containsString("S")));
    }

    @Test
    void add_product_success() {
        // Arrange
        int productId = 123;
        Product expectedProduct = new Product(productId, "expectedProduct name", 1234);
        String expectedSize = "S";
        expectedProduct.setSize(new LinkedHashSet<>(Arrays.asList(expectedSize, "L")));
        when(productService.findById(productId)).thenReturn(Optional.of(expectedProduct));

        User expectedUser = new User();
        when(userService.getCurrentUser()).thenReturn(expectedUser);


        CartService cartService = new CartService();
        cartService.setProductService(productService);
        cartService.setCartItemRepository(cartItemRepository);
        cartService.setUserService(userService);

        // Act
        cartService.addProduct(productId, expectedSize);

        // Assert
        verify(cartItemRepository).save(argThat(cartItem -> {
            assertThat(cartItem.getProduct(), is(samePropertyValuesAs(expectedProduct)));
            assertThat(cartItem.getSize(), is(expectedSize));
            assertThat(cartItem.getUser(), is(samePropertyValuesAs(expectedUser)));
            return true;
        }));

    }
}