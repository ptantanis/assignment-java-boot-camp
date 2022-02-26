package com.example.assignmentjavabootcamp.cart;

import com.example.assignmentjavabootcamp.product.Product;
import com.example.assignmentjavabootcamp.product.ProductService;
import com.example.assignmentjavabootcamp.users.User;
import com.example.assignmentjavabootcamp.users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.example.assignmentjavabootcamp.cart.CartTestHelper.generateCartItem;
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

    private CartService cartService;

    @BeforeEach
    void setUp() {
        cartService = new CartService();
        cartService.setProductService(productService);
        cartService.setCartItemRepository(cartItemRepository);
        cartService.setUserService(userService);

    }

    @Test
    void add_product_to_cart_throw_product_not_found() {
        // Arrange
        int productId = 123;
        when(productService.findById(productId)).thenReturn(Optional.empty());

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

    @Test
    void get_all_cart_item_success() {
        // Arrange
        User user = new User();
        user.setId(1234);

        CartItem cartItem1 = generateCartItem(1, user);
        CartItem cartItem2 = generateCartItem(1, user);

        when(userService.getCurrentUser()).thenReturn(user);

        when(cartItemRepository.findByUser_Id(1234)).thenReturn(Arrays.asList(cartItem1, cartItem2));

        // Act
        List<CartItem> actual = cartService.getCurrentUserCartItem();

        // Assert
        assertThat(actual, is(hasSize(2)));
        assertThat(actual, is(hasItem(cartItem1)));
        assertThat(actual, is(hasItem(cartItem2)));
    }
}