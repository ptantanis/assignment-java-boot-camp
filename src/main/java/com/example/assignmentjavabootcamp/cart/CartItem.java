package com.example.assignmentjavabootcamp.cart;

import com.example.assignmentjavabootcamp.product.Product;
import com.example.assignmentjavabootcamp.user.User;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class CartItem {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "size", nullable = false)
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}