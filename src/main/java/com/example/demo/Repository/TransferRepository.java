package com.example.demo.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Transfer;


public interface TransferRepository 
extends JpaRepository<Transfer, Integer>{

}
