package com.uob.bank.service;

import com.uob.bank.dto.TransactionDto;
import com.uob.bank.model.Transaction;
import com.uob.bank.repository.TransactionRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getTransactionByUserId(Long userId) {
        return transactionRepository.findByUserIdOrderByIdDesc(userId);
    }

    @Override
    public String saveTransaction(TransactionDto transactionDto, Long userId) {
        if (transactionDto.getTransactionType().equals(Transaction.TransactionType.WITHDRAW)) {
            double checkSum = transactionRepository.findTotalSumById(userId) - transactionDto.getAmount();
            if (checkSum < 500.0) {
                return "redirect:/transaction?failure";
            }
            transactionDto.setAmount(transactionDto.getAmount() * -1);
        }
        Transaction transaction = new Transaction(
                userId,
                LocalDateTime.now(),
                transactionDto.getAmount(),
                transactionDto.getTransactionType(),
                transactionDto.getAccountType());
        transactionRepository.save(transaction);
        return "redirect:/transaction?success";
    }

    @Override
    public double getTotalSumById(Long userId) {
        return transactionRepository.findTotalSumById(userId);
    }
}
