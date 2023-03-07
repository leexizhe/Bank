package com.uob.bank.controller;

import com.uob.bank.dto.TransactionDto;
import com.uob.bank.model.Transaction;
import com.uob.bank.service.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminTransaction() {
        return "admin-transaction";
    }

    @GetMapping("/admin/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editTransactionForm(@PathVariable Long id, Model model) {
        model.addAttribute("transactionDto", adminService.getTransactionDtoById(id));
        return "admin-edit-transaction";
    }

    @PostMapping("/admin/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateTransaction(
            @ModelAttribute("transactionDto") TransactionDto transactionDto, @ModelAttribute("id") Long id) {
        return adminService.updateTransaction(id, transactionDto);
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return adminService.deleteTransactionById(id);
    }

    @ModelAttribute("allTransactionsList")
    public List<Transaction> allTransactionsListAttribute() {
        return adminService.getAllTransaction();
    }
}
