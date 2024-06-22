package com.economix.economy.Models;
import jakarta.persistence.Entity;


@Entity
public class Client extends Person {

    public Client() {
    }


    public Client(String name, String lastName, String email, String password, Double totalAmount) {
        super(name, lastName, email, password, totalAmount);
    }
}
