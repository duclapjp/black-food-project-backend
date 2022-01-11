package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class Restaurant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String claim;

    private Double revenue;

    @OneToOne(targetEntity = GeneralStatus.class)
    @JoinColumn(name = "status_id")
    private GeneralStatus status;

    @OneToMany
    List<Coupon> couponList;

    @OneToMany
    List<Food>foodList;

}
