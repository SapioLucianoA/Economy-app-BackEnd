package com.economix.economy.Models;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name, lastName, email, password;

    private Double totalAmount;

    @OneToMany(mappedBy = "person")
    private Set<Transactions> transactions = new HashSet<>();

    @OneToMany(mappedBy = "person")
    private Set<Payments> payments = new HashSet<>();

    public Person() {
    }

    public Person(String name, String lastName, String email, String password, Double totalAmount) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.totalAmount = totalAmount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Set<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transactions> transactions) {
        this.transactions = transactions;
    }

    public Set<Payments> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payments> payments) {
        this.payments = payments;
    }

    public void addPayment (Payments payment){
        payment.setPerson(this);
        this.payments.add(payment);
    }

    @Transactional
    public void addTransaction (Transactions transaction){
        transaction.setPerson(this);
        this.transactions.add(transaction);
    }

}