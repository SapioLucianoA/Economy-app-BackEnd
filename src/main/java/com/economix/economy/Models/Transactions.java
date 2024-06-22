package com.economix.economy.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String description;
    private Double amount;
    private LocalDateTime time;
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Transactions() {
    }

    public Transactions(String description, Double amount, LocalDateTime time, boolean isActive) {
        this.description = description;
        this.amount = amount;
        this.time = time;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Person getPerson() {
        return person;
    }


    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
