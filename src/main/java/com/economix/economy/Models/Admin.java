package com.economix.economy.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    public Admin() {
    }

    public Admin(String name, String lastName, String email, String password) {
        super(name, lastName, email, password);
    }


}
