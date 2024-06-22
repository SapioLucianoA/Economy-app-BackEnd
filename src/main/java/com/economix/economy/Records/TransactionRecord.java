package com.economix.economy.Records;

import com.economix.economy.Models.TransactionType;

public record TransactionRecord(String description, Double amount, TransactionType transactionType) {
}
