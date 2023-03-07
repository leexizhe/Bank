package com.uob.bank;

import com.uob.bank.model.Role;
import com.uob.bank.model.Transaction;
import com.uob.bank.model.User;
import com.uob.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@SpringBootApplication
public class BankApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }


    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    // create admin account if not exist
    @Override
    public void run(String... args) throws Exception {
        if (Objects.isNull(userRepository.findByEmail("admin"))) {
            User admin = new User("admin",
                    "admin",
                    "admin",
                    passwordEncoder.encode("admin"),
                    1,
                    "admin",
                    "admin",
                    Arrays.asList(new Role("ROLE_ADMIN")),
                    Arrays.asList(new Transaction(500, LocalDateTime.now(), Transaction.TransactionType.DEPOSIT, Transaction.AccountType.SAVING)));
            userRepository.save(admin);
        }
    }
}

