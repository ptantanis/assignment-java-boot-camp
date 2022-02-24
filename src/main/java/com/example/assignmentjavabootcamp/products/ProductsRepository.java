package com.example.assignmentjavabootcamp.products;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    Optional<Products> findByNameIgnoreCase(String name);
}