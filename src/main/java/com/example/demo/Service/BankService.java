package com.example.demo.Service;



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

    public User register(User user){

        User savedUser = userRepository.save(user);

        Account account = new Account();
        account.setUserId(savedUser.getId());
        account.setBalance(0);

        accountRepository.save(account);

        return savedUser;
    }

    public User login(String username,String password){

        return userRepository.findByUsernameAndPassword(username,password);

    }

    public String deposit(int userId, double amount){

        Account account = accountRepository.findByUserId(userId);

        if(account == null){
            return "Account not found for this user";
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

    public String withdraw(int userId,double amount){

        Account account = accountRepository.findByUserId(userId);

        if(account == null){
            return "Account not found for this user";
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
    public String resetPassword(String username,String password){

        User user = userRepository.findByUsername(username);

        if(user == null){
            return "User not found";
        }

        user.setPassword(password);

        userRepository.save(user);

        return "Password Reset Successful";
    }

}
