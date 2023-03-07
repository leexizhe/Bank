package com.uob.bank.controller;

import com.uob.bank.dto.TransactionDto;
import com.uob.bank.model.Transaction;
import com.uob.bank.model.User;
import com.uob.bank.service.TransactionService;
import com.uob.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
public class MainController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/transaction")
    public String createTransactionForm() {
        return "create-transaction";
    }

    @PostMapping("/transaction")
    public String saveTransaction(@ModelAttribute("TransactionDto") TransactionDto transactionDto, @ModelAttribute("user") User user) {
        return transactionService.saveTransaction(transactionDto, user.getId());
    }

    @ModelAttribute("transactionsList")
    public List<Transaction> transactionsListAttribute(@ModelAttribute("user") User user) {
        if (Objects.nonNull(user)) {
            return transactionService.getTransactionByUserId(user.getId());
        } else
            return null;
    }

    @ModelAttribute("user")
    private User userAttribute(Principal principal) {
        if (Objects.nonNull(principal)) {
            return userService.getUserByEmail(principal.getName());
        } else
            return null;
    }

    @ModelAttribute("totalAmount")
    private double totalAmountAttribute(@ModelAttribute("user") User user) {
        if (Objects.nonNull(user)) {
            return transactionService.getTotalSumById(user.getId());
        } else
            return 0;
    }

    @ModelAttribute("transactionDto")
    private TransactionDto transactionDtoAttribute() {
        return new TransactionDto(Transaction.TransactionType.DEPOSIT);
    }

}
