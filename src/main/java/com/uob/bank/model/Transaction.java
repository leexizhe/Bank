package com.uob.bank.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "transaction")
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime localDateTime;

    @Column(name = "user_id")
    private Long userId;

    @Column(precision = 10, scale = 2)
    double amount;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "account_type")
    private AccountType accountType;

    public Transaction(
            Long userId,
            LocalDateTime localDateTime,
            double amount,
            TransactionType transactionType,
            AccountType accountType) {
        this.userId = userId;
        this.localDateTime = localDateTime;
        this.amount = amount;
        this.transactionType = transactionType;
        this.accountType = accountType;
    }

    public Transaction(
            double amount, LocalDateTime localDateTime, TransactionType transactionType, AccountType accountType) {
        this.amount = amount;
        this.localDateTime = localDateTime;
        this.transactionType = transactionType;
        this.accountType = accountType;
    }

    public enum TransactionType {
        DEPOSIT,
        WITHDRAW
    };

    public enum AccountType {
        SAVING,
        FIXED,
        RECURRING
    };
}
