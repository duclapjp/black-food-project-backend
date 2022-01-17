package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
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

    private Long userId;


    @OneToMany(cascade = {CascadeType.ALL})
    List<FoodOrder> foodOrderList;

    @ManyToOne(targetEntity = GeneralStatus.class)
    @JsonBackReference
    private GeneralStatus status;

    @OneToMany(cascade = {CascadeType.ALL})
    List<Coupon> couponList;

    @OneToMany(cascade = {CascadeType.ALL})
    List<Food>foodList;

    public Restaurant() {
    }

    public Restaurant(Long id, String name, String address, String claim, Double revenue, GeneralStatus status, List<Coupon> couponList, List<Food> foodList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.claim = claim;
        this.revenue = revenue;
        this.status = status;
        this.couponList = couponList;
        this.foodList = foodList;
    }

    public Restaurant(String name, String address, String claim, Double revenue, GeneralStatus status, List<Coupon> couponList, List<Food> foodList) {
        this.name = name;
        this.address = address;
        this.claim = claim;
        this.revenue = revenue;
        this.status = status;
        this.couponList = couponList;
        this.foodList = foodList;
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

    public void setFoodOrderList(List<FoodOrder> foodOrderList) {
        this.foodOrderList = foodOrderList;
    }
}
