package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.*;
import com.example.demo.Repository.*;

@Service
public class BankService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransferRepository transferRepository;


    // REGISTER
    public User register(User user){

        User savedUser = userRepository.save(user);

        Account account = new Account();
        account.setUserId(savedUser.getId());
        account.setBalance(0);

        accountRepository.save(account);

        return savedUser;
    }


    // LOGIN
    public User login(String username,String password){

        return userRepository.findByUsernameAndPassword(username,password);
    }


    // DEPOSIT
    public String deposit(int userId,double amount){

        Account account = accountRepository.findByUserId(userId);

        if(account == null){
            return "Account not found";
        }

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction tx = new Transaction();
        tx.setUserId(userId);
        tx.setAmount(amount);
        tx.setType("DEPOSIT");

        transactionRepository.save(tx);

        return "Deposit Successful";
    }


    // WITHDRAW
    public String withdraw(int userId,double amount){

        Account account = accountRepository.findByUserId(userId);

        if(account == null){
            return "Account not found";
        }

        if(account.getBalance() < amount){
            return "Insufficient Balance";
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction tx = new Transaction();
        tx.setUserId(userId);
        tx.setAmount(amount);
        tx.setType("WITHDRAW");

        transactionRepository.save(tx);

        return "Withdraw Successful";
    }


    // CHECK BALANCE
    public double getBalance(int userId){

        Account account = accountRepository.findByUserId(userId);

        if(account == null){
            return 0;
        }

        return account.getBalance();
    }


    // TRANSACTION HISTORY
    public List<Transaction> getTransactions(int userId){

        return transactionRepository.findByUserId(userId);
    }


    // TRANSFER MONEY
    public String transferMoney(int senderId,String receiverUsername,double amount){

        Account senderAccount = accountRepository.findByUserId(senderId);

        if(senderAccount == null){
            return "Sender account not found";
        }

        if(senderAccount.getBalance() < amount){
            return "Insufficient Balance";
        }

        User receiverUser = userRepository.findByUsername(receiverUsername);

        if(receiverUser == null){
            return "Receiver not found";
        }

        Account receiverAccount = accountRepository.findByUserId(receiverUser.getId());

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        Transfer transfer = new Transfer();
        transfer.setSenderId((long) senderId);
        transfer.setReceiverUsername(receiverUsername);
        transfer.setAmount(amount);

        transferRepository.save(transfer);

        Transaction senderTx = new Transaction();
        senderTx.setUserId(senderId);
        senderTx.setAmount(amount);
        senderTx.setType("TRANSFER SENT");

        transactionRepository.save(senderTx);

        Transaction receiverTx = new Transaction();
        receiverTx.setUserId(receiverUser.getId());
        receiverTx.setAmount(amount);
        receiverTx.setType("TRANSFER RECEIVED");

        transactionRepository.save(receiverTx);

        return "Transfer Successful";
    }


    // RESET PASSWORD
    public String resetPassword(String username,String password){

        User user = userRepository.findByUsername(username);

        if(user == null){
            return "User not found";
        }

        user.setPassword(password);

        userRepository.save(user);

        return "Password Reset Successful";
    }


    // ====================================================
    // QR CODE METHODS
    // ====================================================


    // Generate QR Code data for a user
   
    public String generateQRCode(int userId){

        User user = userRepository.findById((int) userId).orElse(null);

        if(user == null){
            return "User not found";
        }

        return "BANK_QR_USER_" + userId;
    }
    // Pay using QR Code
    public String qrPayment(int senderId,int receiverId,double amount){

        Account senderAccount = accountRepository.findByUserId(senderId);
        Account receiverAccount = accountRepository.findByUserId(receiverId);

        if(senderAccount == null || receiverAccount == null){
            return "Account not found";
        }

        if(senderAccount.getBalance() < amount){
            return "Insufficient Balance";
        }

        // Transfer money
        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);


        // Save transaction for sender
        Transaction senderTx = new Transaction();
        senderTx.setUserId(senderId);
        senderTx.setAmount(amount);
        senderTx.setType("QR PAYMENT SENT");

        transactionRepository.save(senderTx);


        // Save transaction for receiver
        Transaction receiverTx = new Transaction();
        receiverTx.setUserId(receiverId);
        receiverTx.setAmount(amount);
        receiverTx.setType("QR PAYMENT RECEIVED");

        transactionRepository.save(receiverTx);

        return "QR Payment Successful";
    }

}