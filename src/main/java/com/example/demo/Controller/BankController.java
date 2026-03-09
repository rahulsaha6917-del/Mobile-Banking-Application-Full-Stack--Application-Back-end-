package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Transaction;
import com.example.demo.Entity.User;
import com.example.demo.Service.BankService;
import com.example.demo.Service.NotificationService;
import com.example.demo.Service.OtpService;

@RestController
@RequestMapping("/api/bank")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class BankController {

    @Autowired
    private BankService bankService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private OtpService otpService;

    // ================= REGISTER =================
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        bankService.register(user);
        return ResponseEntity.ok("Registration Successful");
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody Map<String,String> req){
        User user = bankService.login(req.get("username"), req.get("password"));
        if(user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(401).build();
    }

    // ================= SEND OTP =================
    @PostMapping("/sendOtp")
    public ResponseEntity<String> sendOtp(@RequestParam String phone){
        String result = otpService.generateOtp(phone);
        return ResponseEntity.ok(result);
    }

    // ================= VERIFY OTP =================
    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestParam String phone, @RequestParam String otp){
        boolean valid = otpService.verifyOtp(phone, otp);
        if(valid){
            return ResponseEntity.ok("OTP Verified Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid or Expired OTP");
        }
    }

    // ================= DEPOSIT =================
    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody Map<String,String> req){
        try {
            int userId = Integer.parseInt(req.get("userId"));
            double amount = Double.parseDouble(req.get("amount"));
            String result = bankService.deposit(userId, amount);
            return ResponseEntity.ok(result);
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        }
    }

    // ================= WITHDRAW =================
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody Map<String,String> req){
        try {
            int userId = Integer.parseInt(req.get("userId"));
            double amount = Double.parseDouble(req.get("amount"));
            String result = bankService.withdraw(userId, amount);
            return ResponseEntity.ok(result);
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        }
    }

    // ================= CHECK BALANCE =================
    @GetMapping("/balance/{userId}")
    public ResponseEntity<Double> getBalance(@PathVariable int userId){
        double balance = bankService.getBalance(userId);
        return ResponseEntity.ok(balance);
    }

    // ================= TRANSACTION HISTORY =================
    @GetMapping("/transactions/{userId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable int userId){
        List<Transaction> transactions = bankService.getTransactions(userId);
        return ResponseEntity.ok(transactions);
    }

    // ================= TRANSFER MONEY =================
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody Map<String,String> req){
        try {
            int senderId = Integer.parseInt(req.get("senderId"));
            String receiverUsername = req.get("receiverUsername");
            double amount = Double.parseDouble(req.get("amount"));
            String result = bankService.transferMoney(senderId, receiverUsername, amount);
            return ResponseEntity.ok(result);
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        }
    }

    // ================= RESET PASSWORD =================
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String,String> req){
        String username = req.get("username");
        String password = req.get("password");
        String result = bankService.resetPassword(username, password);
        return ResponseEntity.ok(result);
    }

    // ================= QR CODE PAYMENT =================
    @GetMapping("/generateQR/{userId}")
    public ResponseEntity<String> generateQR(@PathVariable int userId){
        String qr = bankService.generateQRCode(userId);
        return ResponseEntity.ok(qr);
    }

    @PostMapping("/payQR")
    public ResponseEntity<String> payUsingQR(@RequestBody Map<String,String> req){
        try {
            int senderId = Integer.parseInt(req.get("senderId"));
            int receiverId = Integer.parseInt(req.get("receiverId"));
            double amount = Double.parseDouble(req.get("amount"));
            String result = bankService.qrPayment(senderId, receiverId, amount);
            return ResponseEntity.ok(result);
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        }
    }

    // ================= EMAIL NOTIFICATION =================
    @PostMapping("/sendMail")
    public ResponseEntity<String> sendMail(@RequestBody Map<String,String> req){
        String email = req.get("email");
        String message = req.get("message");
        notificationService.sendEmail(email, "Bank Transaction Alert", message);
        return ResponseEntity.ok("Mail Sent Successfully");
    }
}