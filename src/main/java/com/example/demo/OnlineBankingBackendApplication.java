package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineBankingBackendApplication {

    public static void main(String[] args) {

        System.out.println("DB URL: " + System.getenv("SPRING_DATASOURCE_URL"));

        SpringApplication.run(OnlineBankingBackendApplication.class, args);
    }
}
