package com.uob.bank.repository;

import com.uob.bank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findByUserIdOrderByIdDesc(Long user_id);

    @Query(value = "SELECT sum(t.amount) FROM transaction t WHERE t.user_id = :user_id and t.account_type = 0" , nativeQuery = true)
    double findTotalSumById(@Param("user_id") Long user_id);

    List<Transaction> findAllByOrderByIdDesc();
}
