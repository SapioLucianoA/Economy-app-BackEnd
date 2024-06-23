package com.economix.economy.DTOS;

import com.economix.economy.Models.Payments;

public class PaymentDTO {
    private String id;
    private String description;
    private Double amount;
    private Long totalPayments;
    private Long soldPayments;
    private boolean isActive;
    private String client_id;

    public PaymentDTO() {
    }
    public PaymentDTO(Payments payments){
        this.id = payments.getId();
        this.description = payments.getDescription();
        this.amount = payments.getAmount();
        this.totalPayments = payments.getTotalPayments();
        this.soldPayments = payments.getSoldPayments();
        this.isActive = payments.isActive();
        this.client_id = payments.getPerson().getId();
    }

    public String getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public Long getTotalPayments() {
        return totalPayments;
    }

    public Long getSoldPayments() {
        return soldPayments;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getDescription() {
        return description;
    }
}
