package com.example.demo.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class FoodOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime time;
    private Double totalPrice;
    private String note;

    @OneToOne(targetEntity = GeneralStatus.class)
    @JoinColumn(name = "status_id")
    private GeneralStatus status;

    @ManyToOne(targetEntity = User.class)
    private User user;

}
