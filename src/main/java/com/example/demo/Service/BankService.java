package com.example.demo.Service;

import java.time.LocalDateTime;
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

    // ================= CHECK DUPLICATE USER =================
    public boolean userExists(String username, String email, String phone){

        if(userRepository.findByUsername(username) != null)
            return true;

        if(userRepository.findByEmail(email) != null)
            return true;

        if(userRepository.findByPhone(phone) != null)
            return true;

        return false;
    }

    // ================= REGISTER =================
    public User register(User user){

        User savedUser = userRepository.save(user);

        Account account = new Account();
        account.setUserId(savedUser.getId());
        account.setBalance(0);

        accountRepository.save(account);

        return savedUser;
    }

    // ================= LOGIN =================
    public User login(String username,String password){

        return userRepository.findByUsernameAndPassword(username,password);

    }

    // ================= DEPOSIT =================
    public String deposit(int userId,double amount){

        if(amount <= 0)
            return "Invalid Amount";

        Account account = accountRepository.findByUserId(userId);

        if(account == null)
            return "Account not found";

        account.setBalance(account.getBalance() + amount);

        accountRepository.save(account);

        Transaction tx = new Transaction();

        tx.setUserId(userId);
        tx.setAmount(amount);
        tx.setType("DEPOSIT");
        tx.setDateTime(LocalDateTime.now());

        transactionRepository.save(tx);

        return "Deposit Successful";
    }

    // ================= WITHDRAW =================
    public String withdraw(int userId,double amount){

        if(amount <= 0)
            return "Invalid Amount";

        Account account = accountRepository.findByUserId(userId);

        if(account == null)
            return "Account not found";

        if(account.getBalance() < amount)
            return "Insufficient Balance";

        account.setBalance(account.getBalance() - amount);

        accountRepository.save(account);

        Transaction tx = new Transaction();

        tx.setUserId(userId);
        tx.setAmount(amount);
        tx.setType("WITHDRAW");
        tx.setDateTime(LocalDateTime.now());

        transactionRepository.save(tx);

        return "Withdraw Successful";
    }

    // ================= CHECK BALANCE =================
    public double getBalance(int userId){

        Account account = accountRepository.findByUserId(userId);

        if(account == null)
            return 0;

        return account.getBalance();
    }

    // ================= TRANSACTION HISTORY =================
    public List<Transaction> getTransactions(int userId){

        return transactionRepository.findByUserId(userId);

    }

    // ================= CLEAR TRANSACTION HISTORY =================
    public boolean clearTransactionHistory(int userId){

        List<Transaction> transactions =
                transactionRepository.findByUserId(userId);

        if(transactions == null || transactions.isEmpty())
            return false;

        transactionRepository.deleteAll(transactions);

        return true;
    }

    // ================= TRANSFER MONEY =================
    public String transferMoney(int senderId,String receiverUsername,double amount){

        if(amount <= 0)
            return "Invalid Amount";

        Account senderAccount = accountRepository.findByUserId(senderId);

        if(senderAccount == null)
            return "Sender account not found";

        if(senderAccount.getBalance() < amount)
            return "Insufficient Balance";

        User receiverUser = userRepository.findByUsername(receiverUsername);

        if(receiverUser == null)
            return "Receiver not found";

        if(receiverUser.getId() == senderId)
            return "Cannot transfer to yourself";

        Account receiverAccount =
                accountRepository.findByUserId(receiverUser.getId());

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        Transfer transfer = new Transfer();

        transfer.setSenderId((long)senderId);
        transfer.setReceiverUsername(receiverUsername);
        transfer.setAmount(amount);

        transferRepository.save(transfer);

        Transaction senderTx = new Transaction();

        senderTx.setUserId(senderId);
        senderTx.setAmount(amount);
        senderTx.setType("TRANSFER SENT");
        senderTx.setDateTime(LocalDateTime.now());

        transactionRepository.save(senderTx);

        Transaction receiverTx = new Transaction();

        receiverTx.setUserId(receiverUser.getId());
        receiverTx.setAmount(amount);
        receiverTx.setType("TRANSFER RECEIVED");
        receiverTx.setDateTime(LocalDateTime.now());

        transactionRepository.save(receiverTx);

        return "Transfer Successful";
    }

    // ================= RESET PASSWORD =================
    public String resetPassword(String username,String password){

        User user = userRepository.findByUsername(username);

        if(user == null)
            return "User not found";

        user.setPassword(password);

        userRepository.save(user);

        return "Password Reset Successful";
    }

    // ================= GENERATE QR =================
    public String generateQRCode(int userId){

        User user = userRepository.findById(userId).orElse(null);

        if(user == null)
            return "User not found";

        return "BANK_QR_USER_" + userId;
    }

    // ================= QR PAYMENT =================
    public String qrPayment(int senderId,int receiverId,double amount){

        if(amount <= 0)
            return "Invalid Amount";

        Account senderAccount = accountRepository.findByUserId(senderId);
        Account receiverAccount = accountRepository.findByUserId(receiverId);

        if(senderAccount == null || receiverAccount == null)
            return "Account not found";

        if(senderAccount.getBalance() < amount)
            return "Insufficient Balance";

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        Transaction senderTx = new Transaction();

        senderTx.setUserId(senderId);
        senderTx.setAmount(amount);
        senderTx.setType("QR PAYMENT SENT");
        senderTx.setDateTime(LocalDateTime.now());

        transactionRepository.save(senderTx);

        Transaction receiverTx = new Transaction();

        receiverTx.setUserId(receiverId);
        receiverTx.setAmount(amount);
        receiverTx.setType("QR PAYMENT RECEIVED");
        receiverTx.setDateTime(LocalDateTime.now());

        transactionRepository.save(receiverTx);

        return "QR Payment Successful";
    }
}