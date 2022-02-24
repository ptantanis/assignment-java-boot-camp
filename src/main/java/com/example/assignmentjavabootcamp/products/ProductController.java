package com.example.assignmentjavabootcamp.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/products")
    SearchProductsResponse searchProducts(@RequestParam("name") String searchName) {
        List<Product> result = productService.findByName(searchName);

        SearchProductsResponse response = new SearchProductsResponse();
        response.setProducts(result);

        return response;
    }
}
