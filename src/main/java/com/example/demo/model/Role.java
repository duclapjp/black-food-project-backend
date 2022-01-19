package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private RoleName name;

    @ManyToOne(targetEntity = GeneralStatus.class)
    private GeneralStatus status;

    @ManyToOne(targetEntity = User.class)
    @JsonBackReference
    private User user;

    public Role() {
    }

    public Role(Long id, RoleName name, GeneralStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Role(RoleName name, GeneralStatus status) {
        this.name = name;
        this.status = status;
    }

    public Role(RoleName name, GeneralStatus status, User user) {
        this.name = name;
        this.status = status;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public GeneralStatus getStatus() {
        return status;
    }

    public void setStatus(GeneralStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
