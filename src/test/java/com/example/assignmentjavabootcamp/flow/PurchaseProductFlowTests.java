package com.example.assignmentjavabootcamp.flow;

import com.example.assignmentjavabootcamp.cart.AddProductToCartRequest;
import com.example.assignmentjavabootcamp.cart.GetAllCartItemResponse;
import com.example.assignmentjavabootcamp.payment.CreditCardGateway;
import com.example.assignmentjavabootcamp.payment.CreditCardPaymentRequest;
import com.example.assignmentjavabootcamp.product.Product;
import com.example.assignmentjavabootcamp.product.ProductRepository;
import com.example.assignmentjavabootcamp.product.SearchProductsResponse;
import com.example.assignmentjavabootcamp.user.*;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Product chosenProduct;

    private Address expectedAddress;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setId(1);
        userRepository.save(user);

        expectedAddress = new Address();
        expectedAddress.setId(1);
        expectedAddress.setUser(user);
        expectedAddress.setName("John doe");
        expectedAddress.setAddress("1/234 Mary road");
        expectedAddress.setPostCode("5000");
        expectedAddress.setSubProvince("Bang pra");
        expectedAddress.setProvince("krug thep");
        addressRepository.save(expectedAddress);


        chosenProduct = new Product(2134, "addidas", 1324);
        chosenProduct.setDescription("this is product description");
        chosenProduct.setSize(new LinkedHashSet<>(Arrays.asList("S", "M", "L")));
        chosenProduct.setPictureUrl("https://cataas.com/cat/orange");

        Product product2 = new Product(2135, "not addidas", 234);
        Product product3 = new Product(2136, "another", 432);

        productRepository.save(chosenProduct);
        productRepository.save(product2);
        productRepository.save(product3);
    }

    @Test
    void purchase_product_flow() {
        String chosenSize = "S";

        // Search Product
        // Act
        ResponseEntity<SearchProductsResponse> searchResponse = testRestTemplate.getForEntity("/api/products?name=Addidas", SearchProductsResponse.class);

        // Assert
        assertEquals(HttpStatus.OK, searchResponse.getStatusCode());
        List<Product> actualProducts = searchResponse.getBody().getProducts();
        assertThat(actualProducts, hasSize(2));

        // Add product to cart
        // Act
        AddProductToCartRequest addProductToCartRequest = new AddProductToCartRequest();
        addProductToCartRequest.setProductId(chosenProduct.getId());
        addProductToCartRequest.setSize(chosenSize);

        ResponseEntity addProductToCartResponse = testRestTemplate.postForEntity("/api/carts", addProductToCartRequest, SearchProductsResponse.class);

        // Assert
        assertEquals(HttpStatus.OK, addProductToCartResponse.getStatusCode());

        // Get all current user cart items
        // Act
        ResponseEntity<GetAllCartItemResponse> getAllCurrentUserCartItemResponse = testRestTemplate.getForEntity("/api/carts", GetAllCartItemResponse.class);

        // Assert
        assertEquals(HttpStatus.OK, getAllCurrentUserCartItemResponse.getStatusCode());
        assertThat(getAllCurrentUserCartItemResponse.getBody().getCartItems(), hasSize(1));

        // Get current user address
        // Act
        ResponseEntity<UserAddressResponse> getCurrentUserAddress = testRestTemplate.getForEntity("/api/users/current/addresses", UserAddressResponse.class);

        // Assert
        assertEquals(HttpStatus.OK, getCurrentUserAddress.getStatusCode());
        assertThat(getCurrentUserAddress.getBody().getAddress(), samePropertyValuesAs(expectedAddress, "user"));

        // Charge with credit card
        // Act
        CreditCardPaymentRequest paymentRequest = new CreditCardPaymentRequest();
        paymentRequest.setCardNo("4000 6200 0000 0007");
        paymentRequest.setName("John doe");
        paymentRequest.setCvv("737");
        paymentRequest.setExpiration_month(3);
        paymentRequest.setExpiration_year(2030);
        ResponseEntity paymentResponse = testRestTemplate.postForEntity("/api/payments/credit-card", paymentRequest, null);

        // Assert
        assertEquals(HttpStatus.OK, paymentResponse.getStatusCode());
    }
}
