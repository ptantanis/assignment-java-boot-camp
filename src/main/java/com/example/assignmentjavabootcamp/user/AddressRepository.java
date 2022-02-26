package com.example.assignmentjavabootcamp.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findByUser_Id(int id);
}