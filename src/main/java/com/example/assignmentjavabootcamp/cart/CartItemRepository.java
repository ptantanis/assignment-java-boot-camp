package com.example.assignmentjavabootcamp.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByUser_Id(int id);

    long deleteByUser_Id(int id);
}