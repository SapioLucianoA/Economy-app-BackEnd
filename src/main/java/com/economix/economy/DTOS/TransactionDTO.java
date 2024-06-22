package com.economix.economy.DTOS;

import com.economix.economy.Models.Person;
import com.economix.economy.Models.Transactions;

import java.time.LocalDateTime;

public class TransactionDTO {

    private String id;

    private String description;
    private Double amount;
    private LocalDateTime time;
    private String client_Id;

    public TransactionDTO(Transactions transactions) {
        this.id = transactions.getId();
        this.description = transactions.getDescription();
        this.amount = transactions.getAmount();
        this.time = transactions.getTime();
        this.client_Id = transactions.getPerson().getId();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getClient_Id() {
        return client_Id;
    }
}
