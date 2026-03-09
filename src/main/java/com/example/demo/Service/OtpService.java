package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Entity.Otp;
import com.example.demo.Repository.OtpRepository;

@Service
public class OtpService {

    private final OtpRepository otpRepository;
    private final RestTemplate restTemplate;

    // Load Fast2SMS API key from application.properties
    @Value("${fast2sms.api.key}")
    private String apiKey;

    // Constructor-based injection
    public OtpService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
        this.restTemplate = new RestTemplate();
    }

    // Generate OTP, save to DB, send SMS
    public String generateOtp(String phone) {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);

        // Save OTP in DB
        Otp o = new Otp();
        o.setPhone(phone);
        o.setOtp(String.valueOf(otp));
        o.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpRepository.save(o);

        try {
            // Fast2SMS API URL
            String url = "https://www.fast2sms.com/dev/bulkV2";

            // Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("authorization", apiKey); // API key from properties

            // Prepare JSON payload
            String jsonPayload = "{"
                    + "\"route\":\"otp\","
                    + "\"sender_id\":\"TXTIND\","
                    + "\"message\":\"Your OTP is " + otp + "\","
                    + "\"language\":\"english\","
                    + "\"flash\":0,"
                    + "\"numbers\":\"" + phone + "\""
                    + "}";

            HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

            // Send POST request
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            System.out.println("SMS Response: " + response.getBody());
            return "OTP Sent Successfully";

        } catch (Exception e) {
            // Log OTP for testing if SMS fails
            System.out.println("SMS failed, OTP for " + phone + " : " + otp);
            e.printStackTrace();
            return "OTP Generated (SMS failed, check console)";
        }
    }

    // Verify OTP
    public boolean verifyOtp(String phone, String otp) {
        // Get the latest OTP for this phone
        Otp savedOtp = otpRepository.findTopByPhoneOrderByIdDesc(phone);

        if (savedOtp == null) return false;

        // Check expiry
        if (savedOtp.getExpiryTime().isBefore(LocalDateTime.now())) return false;

        // Check OTP match
        return savedOtp.getOtp().equals(otp);
    }
}