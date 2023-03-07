package com.uob.bank.service;

import com.uob.bank.dto.TransactionDto;
import com.uob.bank.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactionByUserId(Long userId);

    String saveTransaction(TransactionDto transactionDto, Long userId);

    double getTotalSumById(Long userId);


}
