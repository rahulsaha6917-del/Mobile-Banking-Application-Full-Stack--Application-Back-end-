package com.example.demo.Service;

import org.springframework.stereotype.Service;
import com.example.demo.Entity.Transaction;
import com.example.demo.Repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {

    private final NotificationService notificationService;
    private final TransactionRepository transactionRepository;

    public TransactionService(NotificationService notificationService,
                              TransactionRepository transactionRepository) {
        this.notificationService = notificationService;
        this.transactionRepository = transactionRepository;
    }

    // ================= HANDLE TRANSACTION =================
    public void handleTransaction(Long userId, Transaction transaction, double currentBalance) {
        if (transaction.getStatus().equals("SUCCESS")) {
            notificationService.createNotification(userId, "SUCCESS",
                    "Your transaction of $" + transaction.getAmount() + " was successful.");
            notificationService.sendEmail("user@example.com", "Transaction Successful",
                    "Your transaction of $" + transaction.getAmount() + " was successful.");
        } else {
            notificationService.createNotification(userId, "FAILURE",
                    "Your transaction of $" + transaction.getAmount() + " failed.");
            notificationService.sendEmail("user@example.com", "Transaction Failed",
                    "Your transaction of $" + transaction.getAmount() + " failed.");
        }

        // Low balance alert
        if (currentBalance < 1000) { // set threshold
            notificationService.createNotification(userId, "LOW_BALANCE",
                    "Your account balance is low: $" + currentBalance);
            notificationService.sendEmail("user@example.com", "Low Balance Alert",
                    "Your account balance is low: $" + currentBalance);
        }
    }

    // ================= CLEAR TRANSACTION HISTORY =================
    public void clearTransactionHistory(Long userId) {
        // Fetch all transactions for the user
        List<Transaction> transactions = transactionRepository.findByUserId(userId.intValue());
        
        // Delete all transactions
        transactionRepository.deleteAll(transactions);

        // Optional: send notification/email about cleared history
        notificationService.createNotification(userId, "HISTORY_CLEARED",
                "Your transaction history has been cleared successfully.");
        notificationService.sendEmail("user@example.com", "Transaction History Cleared",
                "All your transaction history has been cleared successfully.");
    }
}