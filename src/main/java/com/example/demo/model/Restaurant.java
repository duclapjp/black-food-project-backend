package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String claim;

    private Double revenue;

    private String image;

    private Long userId;

    @ManyToOne(targetEntity = GeneralStatus.class)
    private GeneralStatus status;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Coupon> couponList;

    @OneToMany
    private List<Food> foodList;

    @OneToMany
    private List<FoodOrder> foodOrderList;

    public Restaurant() {
    }

    public Restaurant(String name, String address, String claim, Double revenue, Long userId, List<Food> foodList, GeneralStatus status, List<Coupon> couponList) {
        this.name = name;
        this.address = address;
        this.claim = claim;
        this.revenue = revenue;
        this.userId = userId;
        this.foodList = foodList;
        this.status = status;
        this.couponList = couponList;
    }

    public Restaurant(String name, String address, String image) {
        this.name = name;
        this.address = address;
        this.image = image;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getClaim() {
        return claim;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public List<FoodOrder> getFoodOrderList() {
        return foodOrderList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFoodOrderList(List<FoodOrder> foodOrderList) {
        this.foodOrderList = foodOrderList;
    }
}
