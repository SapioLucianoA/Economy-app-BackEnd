package com.economix.economy.Models;
import jakarta.persistence.Entity;

@Entity
public class Admin extends Person{

    public Admin() {
    }

    public Admin(String name, String lastName, String email, String password, Double totalAmount) {
        super(name, lastName, email, password, totalAmount);
    }


}
