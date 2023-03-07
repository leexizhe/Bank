package com.uob.bank.dto;

import com.uob.bank.model.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TransactionDto {
    double amount;
    private Transaction.TransactionType transactionType;
    private Transaction.AccountType accountType;

    public TransactionDto(double amount, Transaction.TransactionType transactionType, Transaction.AccountType accountType) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.accountType = accountType;
    }

    public TransactionDto(Transaction.TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
