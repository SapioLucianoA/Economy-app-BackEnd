package com.economix.economy.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Client extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    public Client() {
    }


    public Client(String name, String lastName, String email, String password) {
        super(name, lastName, email, password);
    }

}
