package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="food_order")
public class FoodOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime time;
    private Double totalPrice;
    private String note;

    @ManyToOne(targetEntity = GeneralStatus.class)
    private GeneralStatus generalStatus;

    @JsonIgnore
    @OneToMany
    private List<Food> food;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity =  Restaurant.class)
    private Restaurant restaurant;
//    private Long restaurantId;

    public FoodOrder() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }
}
