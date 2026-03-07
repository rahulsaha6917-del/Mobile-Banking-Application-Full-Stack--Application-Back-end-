package com.example.demo.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

    User findByUsernameAndPassword(String username,String password);

    User findByUsername(String username);

}