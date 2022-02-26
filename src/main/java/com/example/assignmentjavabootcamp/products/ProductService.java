package com.example.assignmentjavabootcamp.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findByName(String name) {
        return productRepository.findByNameContainsIgnoreCase(name);
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }
}
