package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bank;
    private String code;

    private Long userId;

    public Card() {
    }

    public Card(String bank, String code, Long userId) {
        this.bank = bank;
        this.code = code;
        this.userId = userId;
    }

    public Card(Long id, String bank, String code, Long userId) {
        this.id = id;
        this.bank = bank;
        this.code = code;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
