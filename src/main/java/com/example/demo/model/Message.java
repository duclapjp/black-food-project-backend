package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime time;

    @OneToOne(targetEntity = GeneralStatus.class)
    @JoinColumn(name = "status_id")
    private GeneralStatus status;

    @ManyToOne(targetEntity = User.class)
    private User sender;


    @ManyToOne(targetEntity = User.class)
    private User receiver;
}
