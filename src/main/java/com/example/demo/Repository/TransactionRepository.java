package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.Transaction;
import java.util.List;
import jakarta.transaction.Transactional;  // Use this for @Transactional

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    // Fetch transactions by user
    List<Transaction> findByUserId(int userId);

    // ================= CLEAR TRANSACTION HISTORY =================
    @Transactional
    void deleteByUserId(int userId);
}