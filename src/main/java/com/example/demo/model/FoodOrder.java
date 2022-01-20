package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food_order")
public class FoodOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime time;
    private Double totalPrice;
    private String note;

    @ManyToOne(targetEntity = GeneralStatus.class)
    private GeneralStatus generalStatus;


    @OneToMany(fetch = FetchType.LAZY)
    private List<Food> foodList;

    private Long userId;

//    private Long restaurantId;


    public FoodOrder() {
    }

    public FoodOrder(LocalDateTime time, Double totalPrice, String note, GeneralStatus generalStatus, List<Food> foodList, Long userId) {
        this.time = time;
        this.totalPrice = totalPrice;
        this.note = note;
        this.generalStatus = generalStatus;
        this.foodList = foodList;
        this.userId = userId;
    }

    public FoodOrder(Double totalPrice, String note, GeneralStatus generalStatus, List<Food> foodList, Long userId) {
        this.totalPrice = totalPrice;
        this.note = note;
        this.generalStatus = generalStatus;
        this.foodList = foodList;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public GeneralStatus getGeneralStatus() {
        return generalStatus;
    }

    public void setGeneralStatus(GeneralStatus generalStatus) {
        this.generalStatus = generalStatus;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
