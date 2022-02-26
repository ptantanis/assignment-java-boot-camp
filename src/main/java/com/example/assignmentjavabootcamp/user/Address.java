package com.example.assignmentjavabootcamp.user;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    private int id;



    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "post_code", length = 100)
    private String postCode;

    @Column(name = "sub_province")
    private String subProvince;

    @Column(name = "province")
    private String province;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSubProvince() {
        return subProvince;
    }

    public void setSubProvince(String subProvince) {
        this.subProvince = subProvince;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}