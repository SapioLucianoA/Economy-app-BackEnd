package com.economix.economy.DTOS;

import com.economix.economy.Models.Payments;

public class PaymentDTO {
    private String id;
    private Double amount;
    private Long totalPayments;
    private Long soldPayments;
    private boolean isActive;
    private String client_id;

    public PaymentDTO() {
    }
    public PaymentDTO(Payments payments){
        this.id = payments.getId();
        this.amount = payments.getAmount();
        this.totalPayments = payments.getTotalPayments();
        this.soldPayments = payments.getSoldPayments();
        this.isActive = payments.isActive();
        this.client_id = payments.getPerson().getId();
    }

}
