package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime time;

    @ManyToOne(targetEntity = GeneralStatus.class)
    private GeneralStatus status;

    @ManyToOne(targetEntity = User.class)
    private User sender;


    @ManyToOne(targetEntity = User.class)
    private User receiver;

    public Message() {
    }

    public Message(String content, LocalDateTime time, GeneralStatus status, User sender, User receiver) {
        this.content = content;
        this.time = time;
        this.status = status;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Message(Long id, String content, LocalDateTime time, GeneralStatus status, User sender, User receiver) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.status = status;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
