package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class GeneralStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Coupon.class, cascade = CascadeType.ALL)
    List<Coupon> couponList;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Restaurant.class, cascade = CascadeType.ALL)
    List<Restaurant> restaurantList;

    @OneToMany
    List<Message> messageList;

    @OneToMany
    List<User> userList;

    @OneToMany
    List<Role> roles;

    public GeneralStatus() {
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

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
