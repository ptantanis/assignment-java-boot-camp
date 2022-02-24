package com.example.assignmentjavabootcamp.products;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    public List<Product> findByName(String name) {
        return new ArrayList<>();
    }
}
