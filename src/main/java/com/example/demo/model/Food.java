package com.example.demo.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Long quantity;

    private String description;

    private String image;

    @ManyToOne(targetEntity = Restaurant.class)
    private Restaurant restaurant;

    @ManyToOne(targetEntity = FoodOrder.class)
    private FoodOrder foodOrder;
}
