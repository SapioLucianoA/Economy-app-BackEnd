package com.economix.economy.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String description;
    private Double amount;
    private Long totalPayments;
    private Long soldPayments;
    private boolean isActive;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Payments() {
    }

    public Payments(String description, Double amount, Long totalPayments, Long soldPayments, boolean isActive) {
        this.description = description;
        this.amount = amount;
        this.totalPayments = totalPayments;
        this.soldPayments = soldPayments;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(Long totalPayments) {
        this.totalPayments = totalPayments;
    }

    public Long getSoldPayments() {
        return soldPayments;
    }

    public void setSoldPayments(Long soldPayments) {
        this.soldPayments = soldPayments;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
