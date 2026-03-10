package com.example.demo.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

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

    @Value("${fast2sms.api.key}")
    private String apiKey;

    public OtpService(OtpRepository otpRepository) {

        this.otpRepository = otpRepository;
        this.restTemplate = new RestTemplate();

    }

    // ================= GENERATE OTP =================
    public String generateOtp(String phone) {

        if(phone == null || phone.length() < 10){
            return "Invalid Phone Number";
        }

        // Prevent OTP spam (allow new OTP after 30 sec)
        Otp lastOtp = otpRepository.findTopByPhoneOrderByIdDesc(phone);

        if(lastOtp != null){

            LocalDateTime limit = lastOtp.getExpiryTime().minusMinutes(4);

            if(limit.isAfter(LocalDateTime.now())){
                return "Please wait before requesting another OTP";
            }
        }

        // Secure OTP generation
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);

        Otp otpEntity = new Otp();

        otpEntity.setPhone(phone);
        otpEntity.setOtp(String.valueOf(otp));
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5));

        otpRepository.save(otpEntity);

        try {

            String url = "https://www.fast2sms.com/dev/bulkV2";

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("authorization", apiKey);

            String jsonPayload =
                    "{"
                            + "\"route\":\"otp\","
                            + "\"sender_id\":\"TXTIND\","
                            + "\"message\":\"Your OTP is " + otp + "\","
                            + "\"language\":\"english\","
                            + "\"flash\":0,"
                            + "\"numbers\":\"" + phone + "\""
                            + "}";

            HttpEntity<String> entity =
                    new HttpEntity<>(jsonPayload, headers);

            ResponseEntity<String> response =
                    restTemplate.postForEntity(url, entity, String.class);

            System.out.println("SMS Response: " + response.getBody());

            return "OTP Sent Successfully";

        }
        catch (Exception e){

            // For development fallback
            System.out.println("SMS FAILED — OTP for " + phone + " : " + otp);

            return "OTP Generated (Check Console)";
        }
    }

    // ================= VERIFY OTP =================
    public boolean verifyOtp(String phone,String otp){

        Otp savedOtp =
                otpRepository.findTopByPhoneOrderByIdDesc(phone);

        if(savedOtp == null)
            return false;

        // Check expiry
        if(savedOtp.getExpiryTime().isBefore(LocalDateTime.now()))
            return false;

        // Check OTP match
        if(!savedOtp.getOtp().equals(otp))
            return false;

        // Delete OTP after successful verification
        otpRepository.delete(savedOtp);

        return true;
    }

}