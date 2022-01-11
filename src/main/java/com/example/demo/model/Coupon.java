package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(targetEntity = GeneralStatus.class)
    @JoinColumn(name = "status_id")
    private GeneralStatus status;

    @ManyToOne(targetEntity = Restaurant.class)
    private Restaurant restaurant;


}
