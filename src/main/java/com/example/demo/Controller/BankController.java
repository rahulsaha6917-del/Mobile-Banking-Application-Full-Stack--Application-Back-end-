package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Transaction;
import com.example.demo.Entity.User;
import com.example.demo.Service.BankService;
import com.example.demo.Service.NotificationService;

@RestController
@RequestMapping("/api/bank")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class BankController {

    @Autowired
    private BankService bankService;

    @Autowired
    private NotificationService notificationService;


    // ================= REGISTER =================
    @PostMapping("/register")
    public String register(@RequestBody User user){
        bankService.register(user);
        return "Registration Successful";
    }


    // ================= LOGIN =================
    @PostMapping("/login")
    public User login(@RequestBody Map<String,String> req){
        return bankService.login(req.get("username"), req.get("password"));
    }


    // ================= DEPOSIT =================
    @PostMapping("/deposit")
    public String deposit(@RequestBody Map<String,String> req){

        int userId = Integer.parseInt(req.get("userId"));
        double amount = Double.parseDouble(req.get("amount"));

        return bankService.deposit(userId, amount);
    }


    // ================= WITHDRAW =================
    @PostMapping("/withdraw")
    public String withdraw(@RequestBody Map<String,String> req){

        int userId = Integer.parseInt(req.get("userId"));
        double amount = Double.parseDouble(req.get("amount"));

        return bankService.withdraw(userId, amount);
    }


    // ================= CHECK BALANCE =================
    @GetMapping("/balance/{userId}")
    public double getBalance(@PathVariable int userId){
        return bankService.getBalance(userId);
    }


    // ================= TRANSACTION HISTORY =================
    @GetMapping("/transactions/{userId}")
    public List<Transaction> getTransactions(@PathVariable int userId){
        return bankService.getTransactions(userId);
    }


    // ================= TRANSFER MONEY =================
    @PostMapping("/transfer")
    public String transfer(@RequestBody Map<String,String> req){

        int senderId = Integer.parseInt(req.get("senderId"));
        String receiverUsername = req.get("receiverUsername");
        double amount = Double.parseDouble(req.get("amount"));

        return bankService.transferMoney(senderId, receiverUsername, amount);
    }


    // ================= RESET PASSWORD =================
    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody Map<String,String> req){

        String username = req.get("username");
        String password = req.get("password");

        return bankService.resetPassword(username, password);
    }


    // =========================================================
    // ================= QR CODE PAYMENT =======================
    // =========================================================

    // Generate QR Code
    @GetMapping("/generateQR/{userId}")
    public String generateQR(@PathVariable int userId){
        return bankService.generateQRCode(userId);
    }


    // Pay using QR Code
    @PostMapping("/payQR")
    public String payUsingQR(@RequestBody Map<String,String> req){

        int senderId = Integer.parseInt(req.get("senderId"));
        int receiverId = Integer.parseInt(req.get("receiverId"));
        double amount = Double.parseDouble(req.get("amount"));

        return bankService.qrPayment(senderId, receiverId, amount);
    }


    // =========================================================
    // ================= EMAIL NOTIFICATION ====================
    // =========================================================

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody Map<String,String> req){

        String email = req.get("email");
        String message = req.get("message");

        notificationService.sendEmail(
                email,
                "Bank Transaction Alert",
                message
        );

        return "Mail Sent Successfully";
    }

}