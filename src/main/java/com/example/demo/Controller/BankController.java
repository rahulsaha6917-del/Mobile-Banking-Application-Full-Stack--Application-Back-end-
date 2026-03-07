package com.example.demo.Controller;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.User;
import com.example.demo.Service.BankService;

@RestController
@RequestMapping("/api/bank")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class BankController {

    @Autowired
    private BankService bankService;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        User savedUser = bankService.register(user);

        if(savedUser != null){
            return ResponseEntity.ok("Registration Successful");
        }

        return ResponseEntity.badRequest().body("Registration Failed");
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> req){

        String username = req.get("username");
        String password = req.get("password");

        User user = bankService.login(username, password);

        if(user != null){
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(401).body("Invalid Login");
    }

    // DEPOSIT
    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody Map<String,Object> req){

        int userId = Integer.parseInt(req.get("userId").toString());
        double amount = Double.parseDouble(req.get("amount").toString());

        String result = bankService.deposit(userId, amount);

        return ResponseEntity.ok(result);
    }

    // WITHDRAW
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody Map<String,Object> req){

        int userId = Integer.parseInt(req.get("userId").toString());
        double amount = Double.parseDouble(req.get("amount").toString());

        String result = bankService.withdraw(userId, amount);

        return ResponseEntity.ok(result);
    }
    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody Map<String,String> req){

        String username = req.get("username");
        String password = req.get("password");

        return bankService.resetPassword(username,password);
    }

}