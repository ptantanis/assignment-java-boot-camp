package com.example.assignmentjavabootcamp.cart;

import com.example.assignmentjavabootcamp.product.Product;
import com.example.assignmentjavabootcamp.product.ProductService;
import com.example.assignmentjavabootcamp.user.User;
import com.example.assignmentjavabootcamp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private CartItemRepository cartItemRepository;

    public void setCartItemRepository(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Autowired
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void addProduct(int productId, String size) {
        Optional<Product> productOptional = productService.findById(productId);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }

        Product product = productOptional.get();

        if (!product.hasSize(size)) {
            throw new ProductSizeNotFoundException(product, size);
        }

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setSize(size);
        cartItem.setUser(userService.getCurrentUser());

        cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCurrentUserCartItem() {
        User user = userService.getCurrentUser();
        return cartItemRepository.findByUser_Id(user.getId());
    }
}
