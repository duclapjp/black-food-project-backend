package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table

public class GeneralStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    public GeneralStatus() {
    }

    public GeneralStatus(String name) {
        this.name = name;
    }

    public GeneralStatus(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
