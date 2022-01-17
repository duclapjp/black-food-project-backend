package com.example.demo.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private Long code;

    private String name;

    private Double price;

    private Long quantity;

    private String description;

    private String image;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Restaurant.class)
    @JsonBackReference
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = FoodOrder.class)
    private FoodOrder foodOrder;

    public Food() {
    }

    public Food(Long id) {
        this.id = id;
    }


    public Food(Long code, String name, Double price, Long quantity, String description, String image, Restaurant restaurant, FoodOrder foodOrder) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
        this.restaurant = restaurant;
        this.foodOrder = foodOrder;
    }

    public Food(Long id, Long code, String name, Double price, Long quantity, String description, String image, Restaurant restaurant, FoodOrder foodOrder) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
        this.restaurant = restaurant;
        this.foodOrder = foodOrder;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public FoodOrder getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(FoodOrder foodOrder) {
        this.foodOrder = foodOrder;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
