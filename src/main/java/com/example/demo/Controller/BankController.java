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
    public ResponseEntity<String> register(@RequestBody User user) {

        try {

            boolean exists = bankService.userExists(
                    user.getUsername(),
                    user.getEmail(),
                    user.getPhone()
            );

            if (exists) {
                return ResponseEntity
                        .badRequest()
                        .body("Username / Email / Phone already exists");
            }

            bankService.register(user);

            return ResponseEntity.ok("Registration Successful");

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body("Registration Failed : " + e.getMessage());
        }
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {

        try {

            String username = req.get("username");
            String password = req.get("password");

            User user = bankService.login(username, password);

            if (user != null) {
                return ResponseEntity.ok(user);
            }

            return ResponseEntity.status(401)
                    .body("Invalid Username or Password");

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("Login Error");
        }
    }

    // ================= SEND OTP =================
    @PostMapping("/sendOtp")
    public ResponseEntity<String> sendOtp(@RequestParam String phone) {

        try {

            String result = otpService.generateOtp(phone);

            return ResponseEntity.ok(result);

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("OTP Sending Failed");
        }
    }

    // ================= VERIFY OTP =================
    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(
            @RequestParam String phone,
            @RequestParam String otp) {

        boolean valid = otpService.verifyOtp(phone, otp);

        if (valid) {

            return ResponseEntity.ok("OTP Verified Successfully");

        }

        return ResponseEntity.badRequest()
                .body("Invalid or Expired OTP");
    }

    // ================= DEPOSIT =================
    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody Map<String, String> req) {

        try {

            int userId = Integer.parseInt(req.get("userId"));
            double amount = Double.parseDouble(req.get("amount"));

            if (amount <= 0) {
                return ResponseEntity.badRequest()
                        .body("Invalid deposit amount");
            }

            String result = bankService.deposit(userId, amount);

            return ResponseEntity.ok(result);

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("Deposit Failed : " + e.getMessage());
        }
    }

    // ================= WITHDRAW =================
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody Map<String, String> req) {

        try {

            int userId = Integer.parseInt(req.get("userId"));
            double amount = Double.parseDouble(req.get("amount"));

            if (amount <= 0) {

                return ResponseEntity.badRequest()
                        .body("Invalid withdraw amount");
            }

            String result = bankService.withdraw(userId, amount);

            return ResponseEntity.ok(result);

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("Withdraw Failed : " + e.getMessage());
        }
    }

    // ================= CHECK BALANCE =================
    @GetMapping("/balance/{userId}")
    public ResponseEntity<Double> getBalance(@PathVariable int userId) {

        double balance = bankService.getBalance(userId);

        return ResponseEntity.ok(balance);
    }

    // ================= TRANSACTION HISTORY =================
    @GetMapping("/transactions/{userId}")
    public ResponseEntity<List<Transaction>> getTransactions(
            @PathVariable int userId) {

        List<Transaction> transactions =
                bankService.getTransactions(userId);

        return ResponseEntity.ok(transactions);
    }

    // ================= CLEAR TRANSACTION HISTORY =================
    @DeleteMapping("/transactions/{userId}/clear")
    public ResponseEntity<String> clearTransactionHistory(
            @PathVariable int userId) {

        try {

            boolean cleared =
                    bankService.clearTransactionHistory(userId);

            if (cleared) {

                return ResponseEntity.ok(
                        "Transaction history cleared successfully");
            }

            return ResponseEntity.badRequest()
                    .body("No transactions found");

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("Error clearing history");
        }
    }

    // ================= TRANSFER MONEY =================
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestBody Map<String, String> req) {

        try {

            int senderId =
                    Integer.parseInt(req.get("senderId"));

            String receiverUsername =
                    req.get("receiverUsername");

            double amount =
                    Double.parseDouble(req.get("amount"));

            if (amount <= 0) {

                return ResponseEntity.badRequest()
                        .body("Invalid transfer amount");
            }

            String result =
                    bankService.transferMoney(
                            senderId,
                            receiverUsername,
                            amount
                    );

            return ResponseEntity.ok(result);

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("Transfer Failed : " + e.getMessage());
        }
    }

    // ================= RESET PASSWORD =================
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(
            @RequestBody Map<String, String> req) {

        try {

            String username = req.get("username");
            String password = req.get("password");

            String result =
                    bankService.resetPassword(username, password);

            return ResponseEntity.ok(result);

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("Password Reset Failed");
        }
    }

    // ================= GENERATE QR =================
    @GetMapping("/generateQR/{userId}")
    public ResponseEntity<String> generateQR(
            @PathVariable int userId) {

        try {

            String qr =
                    bankService.generateQRCode(userId);

            return ResponseEntity.ok(qr);

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("QR generation failed");
        }
    }

    // ================= PAY USING QR =================
    @PostMapping("/payQR")
    public ResponseEntity<String> payUsingQR(
            @RequestBody Map<String, String> req) {

        try {

            int senderId =
                    Integer.parseInt(req.get("senderId"));

            int receiverId =
                    Integer.parseInt(req.get("receiverId"));

            double amount =
                    Double.parseDouble(req.get("amount"));

            if (amount <= 0) {

                return ResponseEntity.badRequest()
                        .body("Invalid payment amount");
            }

            String result =
                    bankService.qrPayment(
                            senderId,
                            receiverId,
                            amount
                    );

            return ResponseEntity.ok(result);

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("QR Payment Failed");
        }
    }

    // ================= EMAIL NOTIFICATION =================
    @PostMapping("/sendMail")
    public ResponseEntity<String> sendMail(
            @RequestBody Map<String, String> req) {

        try {

            String email = req.get("email");
            String message = req.get("message");

            notificationService.sendEmail(
                    email,
                    "Bank Transaction Alert",
                    message
            );

            return ResponseEntity.ok("Mail Sent Successfully");

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body("Email Sending Failed");
        }
    }

    // ================= LOGOUT =================
    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @RequestParam int userId) {

        System.out.println("User " + userId + " logged out");

        return ResponseEntity.ok("Logout Successful");
    }

}