package com.economix.economy.Models;

import jakarta.persistence.*;

@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Double amount;
    private Long totalPayments;
    private Long soldPayments;
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Payments() {
    }

    public Payments(Double amount, Long totalPayments, Long soldPayments, boolean isActive) {
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
