package com.example.demo.Service;




import org.springframework.stereotype.Service;

import com.example.demo.Entity.Transaction;

@Service
public class TransactionService {

    private final NotificationService notificationService;

    public TransactionService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

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
}
