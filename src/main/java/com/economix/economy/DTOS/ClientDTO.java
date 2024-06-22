package com.economix.economy.DTOS;

import com.economix.economy.Controllers.ClientController;
import com.economix.economy.Models.Client;
import com.economix.economy.Models.Payments;
import com.economix.economy.Models.Transactions;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private String id, name,lastName,email;
    private Set<TransactionDTO> transactionDTOS;
    private Set<PaymentDTO> paymentDTOS;

    public ClientDTO() {
    }
    public ClientDTO(Client client){
        this.id = client.getId();
        this.email = client.getEmail();
        this.name = client.getName();
        this.lastName = client.getLastName();

        this.transactionDTOS = client.getTransactions().stream().filter(transactions -> transactions.isActive()).map(transactions -> new TransactionDTO(transactions)).collect(Collectors.toSet());

        this.paymentDTOS = client.getPayments().stream().filter(payments -> payments.isActive()).map(payments -> new PaymentDTO(payments)).collect(Collectors.toSet());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<TransactionDTO> getTransactionDTOS() {
        return transactionDTOS;
    }
}
