package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Otp;

public interface OtpRepository extends JpaRepository<Otp, Integer>{

    Otp findTopByPhoneOrderByIdDesc(String phone);

}