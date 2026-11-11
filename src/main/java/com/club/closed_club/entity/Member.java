package com.club.closed_club.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    public Member() {
    }

    public Member(String name, Boolean status) {
        this.name = name;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {

        return status;
    }

    public void setStatus(Boolean status) {

        this.status = status;
    }


}
