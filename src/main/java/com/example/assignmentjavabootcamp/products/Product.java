package com.example.assignmentjavabootcamp.products;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "select p from Product p")
})
public class Product {

    public Product() {
    }

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @Column(name = "size")
    @CollectionTable(name = "product_size", joinColumns = @JoinColumn(name = "owner_id"))
    private Set<String> size = new LinkedHashSet<>();

    public Set<String> getSize() {
        return size;
    }

    public void setSize(Set<String> size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}