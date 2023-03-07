package com.uob.bank.service;

import com.uob.bank.dto.TransactionDto;
import com.uob.bank.model.Transaction;

import java.util.List;

public interface AdminService {
    TransactionDto getTransactionDtoById(Long id);

    List<Transaction> getAllTransaction();

    String updateTransaction(Long id, TransactionDto transactionDto);

    String deleteTransactionById(Long id);
}
