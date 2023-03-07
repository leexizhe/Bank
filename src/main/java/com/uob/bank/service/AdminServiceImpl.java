package com.uob.bank.service;

import com.uob.bank.dto.TransactionDto;
import com.uob.bank.model.Transaction;
import com.uob.bank.repository.TransactionRepository;
import com.uob.bank.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TransactionDto getTransactionDtoById(Long id) {
        Transaction transaction = transactionRepository.findById(id).get();
        return new TransactionDto(
                Math.abs(transaction.getAmount()), transaction.getTransactionType(), transaction.getAccountType());
    }

    @Override
    public String updateTransaction(Long id, TransactionDto transactionDto) {
        Transaction updatedTransaction = transactionRepository.findById(id).get();
        updatedTransaction.setTransactionType(transactionDto.getTransactionType());
        if (transactionDto.getTransactionType().equals(Transaction.TransactionType.WITHDRAW)) {
            updatedTransaction.setAmount(transactionDto.getAmount() * -1);
        } else {
            updatedTransaction.setAmount(transactionDto.getAmount());
        }
        updatedTransaction.setAccountType(transactionDto.getAccountType());
        transactionRepository.save(updatedTransaction);
        return "redirect:/admin?updated";
    }

    @Override
    public String deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
        return "redirect:/admin?deleted";
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAllByOrderByIdDesc();
    }
}
