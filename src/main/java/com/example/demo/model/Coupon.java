package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToOne(targetEntity = GeneralStatus.class)
    private GeneralStatus status;


    @JsonIgnore
    @ManyToOne(targetEntity = Restaurant.class)
    private Restaurant restaurant;

    public Coupon() {
    }

    public Coupon(String name, GeneralStatus status, Restaurant restaurant) {
        this.name = name;
        this.status = status;
        this.restaurant = restaurant;
    }

    public Coupon(Long id, String name, GeneralStatus status, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.restaurant = restaurant;
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

    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
